package com.jlt.wikiReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation for reading comma separated keywords in a file
 * 
 * @author Prabal Ghura
 *
 */
public class CSFileReader extends FileWikiReader{
	
	private static final Logger log = Logger.getLogger(CSFileReader.class.getName());

	public CSFileReader(File file) {
		super(file);
	}

	@Override
	public List<String> getKeywords() {
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(file));
			//because first line contains just description
			br.readLine();
			line = br.readLine();
		} catch (FileNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		String[] keywordsRaw = line.split(",");
		List<String> keywords = new ArrayList<String>();
		for(String s:keywordsRaw) {
			if(s.length()>0)
			keywords.add(s);
		}
		return keywords;
	}

}
