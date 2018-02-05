package com.jlt.wikier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.pool.BasicNIOConnPool;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.nio.protocol.BasicAsyncRequestProducer;
import org.apache.http.nio.protocol.BasicAsyncResponseConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequester;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOEventDispatch;
import org.apache.http.protocol.HttpCoreContext;

import com.jlt.wikiReader.FileWikiReader;
import com.jlt.wikier.utils.ApacheUtils;

public class AsyncWikier extends Wikier{

	public AsyncWikier(FileWikiReader reader, String outputFolder) {
		super(reader, outputFolder);
	}

	@Override
	public void makeCallsAndCreateFiles() {
		// Create I/O reactor and Event Dispatch
        IOEventDispatch ioEventDispatch = ApacheUtils.getIOEventDispatch();
        ConnectingIOReactor ioReactor = ApacheUtils.getConnectingIOReactor();
        
        // Run the I/O reactor in a separate thread
        Thread t = new Thread(new Runnable() {

            public void run() {
                try {
                    ioReactor.execute(ioEventDispatch);
                } catch (final InterruptedIOException ex) {
                    System.err.println("Interrupted");
                } catch (final IOException e) {
                    System.err.println("I/O error: " + e.getMessage());
                }
            }

        });
        t.start();
		
		// Create HTTP requester
        String urlParts[] = getBaseUrl();
		HttpAsyncRequester requester = ApacheUtils.getHttpAsyncRequester();
		HttpHost target = new HttpHost(urlParts[1], -1, urlParts[0]);
		BasicNIOConnPool pool = ApacheUtils.getBasicNIOConnPool(ioReactor, 30000);
		HttpCoreContext context = HttpCoreContext.create();
        CountDownLatch latch = new CountDownLatch(1);
        
        //Create producers & consumers for supplied keywords
        List<String> keywords = reader.getKeywords();
        List<BasicAsyncRequestProducer> requestProducers = new ArrayList<BasicAsyncRequestProducer>();
        List<BasicAsyncResponseConsumer> responseConsumers = new ArrayList<BasicAsyncResponseConsumer>();
		for(String keyword: keywords) {
			requestProducers.add(new BasicAsyncRequestProducer(target, 
					new BasicHttpRequest("GET", urlParts[2]+keyword)));
			responseConsumers.add(new BasicAsyncResponseConsumer());
		}
		
		// Limit total number of connections to just two
		pool.setDefaultMaxPerRoute(2);
		pool.setMaxTotal(2);
		
        requester.executePipelined(
                target, requestProducers, responseConsumers, pool, context,
                new FutureCallback<List<HttpResponse>>() {

                    @Override
                    public void completed(final List<HttpResponse> result) {
                        latch.countDown();
                        for (HttpResponse response: result) {
                        	try {
                        		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                        		String line = rd.readLine();
                        		if(line == null)
                        			System.out.println(target+"->"+response.getStatusLine());
                        		else
                        			System.out.println(line);
							} catch (UnsupportedOperationException | IOException e) {
								e.printStackTrace();
							}
                        }
                    }

                    @Override
                    public void failed(final Exception ex) {
                        latch.countDown();
                        System.err.println(target + "->" + ex);
                    }

                    @Override
                    public void cancelled() {
                        latch.countDown();
                        System.err.println(target + "-> cancelled");
                    }

                });

        try {
			latch.await();
			ioReactor.shutdown();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private String[] getBaseUrl() {
		String url[] = new String[3];
		String raw[] = Wikier.baseUrl.split("://");
		String remainder;
		if(raw.length == 1) {
			url[0] = null;
			remainder = raw[0];
		} else {
			url[0] = raw[0];
			remainder = raw[1];
		}
		int index = remainder.indexOf("/");
		url[1] = remainder.substring(0, index);
		url[2] = remainder.substring(index);
		return url;
	}
}
