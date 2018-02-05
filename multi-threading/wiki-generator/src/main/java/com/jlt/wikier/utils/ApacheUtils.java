package com.jlt.wikier.utils;

import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.nio.DefaultHttpClientIODispatch;
import org.apache.http.impl.nio.pool.BasicNIOConnFactory;
import org.apache.http.impl.nio.pool.BasicNIOConnPool;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.protocol.HttpAsyncRequestExecutor;
import org.apache.http.nio.protocol.HttpAsyncRequester;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOEventDispatch;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;

public class ApacheUtils {
	public static HttpAsyncRequester getHttpAsyncRequester() {
		// Create HTTP protocol processing chain
		HttpProcessor httpproc =  HttpProcessorBuilder.create()
				// Use standard client-side protocol interceptors
                .add(new RequestContent())
                .add(new RequestTargetHost())
                .add(new RequestConnControl())
                .add(new RequestUserAgent("Test/1.1"))
                .add(new RequestExpectContinue(true)).build();
		
		// Create HTTP requester
        return new HttpAsyncRequester(httpproc);
	}
	
	public static IOEventDispatch getIOEventDispatch() {
		final HttpAsyncRequestExecutor protocolHandler = new HttpAsyncRequestExecutor();
        // Create client-side I/O event dispatch
        return new DefaultHttpClientIODispatch<>(protocolHandler,
                ConnectionConfig.DEFAULT);
	}
	
	public static ConnectingIOReactor getConnectingIOReactor() {
		// Create client-side I/O reactor
		ConnectingIOReactor ioReactor = null;
		try {
			ioReactor = new DefaultConnectingIOReactor(IOReactorConfig.DEFAULT);
		} catch (IOReactorException e) {
			e.printStackTrace();
		}
		return ioReactor;
	}
	
	public static BasicNIOConnPool getBasicNIOConnPool(ConnectingIOReactor ioReactor,int connectTimeout) {
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getDefault();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		// Create HTTP connection pool
        BasicNIOConnFactory nioConnFactory = new BasicNIOConnFactory(sslContext, null, ConnectionConfig.DEFAULT);
        return new BasicNIOConnPool(ioReactor, nioConnFactory, connectTimeout);
	}
}
