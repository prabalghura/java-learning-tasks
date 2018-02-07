package com.jlt.wikier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import org.json.JSONObject;

import com.jlt.wikiReader.FileWikiReader;
import com.jlt.wikier.utils.ApacheUtils;

/**
 * Implementation for making wiki calls (using apache API) and creating files.
 * 
 * @author Prabal Ghura
 *
 */
public class AsyncWikier extends Wikier{
	
	private static final Logger log = Logger.getLogger(AsyncWikier.class.getName());

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
                	log.log(Level.SEVERE, ex.getMessage());
                } catch (final IOException e) {
                	log.log(Level.SEVERE, e.getMessage());
                }
            }

        });
        t.start();
		
		// Create HTTP requester
        String urlParts[] = getBaseUrl();
		HttpAsyncRequester requester = ApacheUtils.getHttpAsyncRequester();
		HttpHost target = new HttpHost(urlParts[1], -1, urlParts[0]);
		BasicNIOConnPool pool = ApacheUtils.getBasicNIOConnPool(ioReactor, 300000);
		HttpCoreContext context = HttpCoreContext.create();
		
		List<String> keywords = reader.getKeywords();
		int packSize = 60;
        
        //Create producers & consumers for supplied keywords
        int counter = 0;
        while(counter<keywords.size()) {
        	CountDownLatch latch = new CountDownLatch(1);
        	List<String> partKeywords;
        	if(counter+packSize<keywords.size())
        		partKeywords = keywords.subList(counter, counter+packSize);
        	else
        		partKeywords = keywords.subList(counter, keywords.size());
        	counter+=packSize;
        	
        	List<BasicAsyncRequestProducer> requestProducers = new ArrayList<BasicAsyncRequestProducer>();
        	List<BasicAsyncResponseConsumer> responseConsumers = new ArrayList<BasicAsyncResponseConsumer>();
        	for(String keyword: partKeywords) {
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
        					int size = result.size();
        					for (int i=0; i<size ;i++) {
        						try {
        							HttpResponse response = result.get(i);
        							String word = partKeywords.get(i);
        							BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        							String line = rd.readLine();
        							if(line == null)
        								log.log(Level.WARNING, word+"->"+response.getStatusLine().getStatusCode());
        							else {
        								JSONObject json = new JSONObject(response);				
        								if(json.has("title") && json.has("extract"))
        								{
        									String extract = json.getString("extract");
        									List<String> lines = Arrays.asList(extract.split("\\n"));
        									String title = json.getString("title");
        									try {
        						        		Path path = Paths.get(outputFolder, title + ".txt");
        										Files.write(path, lines, Charset.defaultCharset());
        									} catch (IOException e) {
        										log.log(Level.SEVERE, e.getMessage());
        									}
        								}
        							}
        						} catch (UnsupportedOperationException | IOException e) {
        							log.log(Level.SEVERE, e.getMessage());
        						}
        					}
        				}
        				
        				@Override
        				public void failed(final Exception ex) {
        					latch.countDown();
        					log.log(Level.SEVERE, target + "->" + ex);
        				}
        				
        				@Override
        				public void cancelled() {
        					latch.countDown();
        					log.log(Level.SEVERE, target + "-> cancelled");
        				}
        				
        			});
        	try {
        		latch.await();
        	} catch (InterruptedException e) {
        		log.log(Level.SEVERE, e.getMessage());
        	}
        }
        try {
			ioReactor.shutdown();
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
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
