package com.jlt.wikier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.jlt.wikiReader.FileWikiReader;

/**
 * Implementation for making wiki calls using ExecutorService and creating files.
 * 
 * @author Prabal Ghura
 *
 */
public class ExecutorWikier extends Wikier{
	
	private static final Logger log = Logger.getLogger(ExecutorWikier.class.getName());

	public ExecutorWikier(FileWikiReader reader, String outputFolder) {
		super(reader, outputFolder);
	}

	@Override
	public void makeCallsAndCreateFiles() {
		List<String> keywords = reader.getKeywords();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for (String keyword : keywords) {
			executor.execute(new ExecWikier(keyword, outputFolder));
		}
		executor.shutdown();
		try {
			executor.awaitTermination(300, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	private static class ExecWikier implements Runnable {

		private String word;
		private String outputFolder;
		
		public ExecWikier(String word, String outputFolder) {
			super();
			this.word = word;
			this.outputFolder = outputFolder;
		}

		@Override
		public void run() {
			String response = null;
			try {
				URL url = new URL(baseUrl+word);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.connect();
				BufferedReader rd = new BufferedReader(new InputStreamReader((InputStream) conn.getContent()));
				response = rd.readLine();
			} catch (MalformedURLException e) {
				log.log(Level.SEVERE, e.getMessage());
				return;
			} catch (FileNotFoundException e) {
				return;
			} catch (IOException e) {
				log.log(Level.SEVERE, e.getMessage());
				return;
			}
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
		
	}
}
