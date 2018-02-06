package com.jlt.wikier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.jlt.wikiReader.FileWikiReader;

/**
 * Implementation for making wiki calls using Brute Force technique and creating files.
 * 
 * @author Prabal Ghura
 *
 */
public class BFWikier extends Wikier{

	private static final Logger log = Logger.getLogger(BFWikier.class.getName());
	
	public BFWikier(FileWikiReader reader, String outputFolder) {
		super(reader, outputFolder);
	}

	@Override
	public void makeCallsAndCreateFiles() {
		InputStream is = null;
		String response = null;
		BufferedReader rd = null;
		List<String> keywords = reader.getKeywords();
		List<String> notFound = new ArrayList<String>();
		List<String> invalidChars = new ArrayList<String>();
		for(String keyword: keywords) {
			try {
				is = new URL(Wikier.baseUrl+keyword.replaceAll(" ", "_")).openStream();
				rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
				response = rd.readLine();
			} catch (FileNotFoundException e) {
				notFound.add(keyword);
				continue;
			} catch (IOException e) {
				invalidChars.add(keyword);
				continue;
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
		try {
			Path path = Paths.get(outputFolder+"/"+"log", "notFound.txt");
			Files.write(path, notFound, Charset.defaultCharset());
			path = Paths.get(outputFolder+"/"+"log", "invalidChars.txt");
			Files.write(path, invalidChars, Charset.defaultCharset());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
}
