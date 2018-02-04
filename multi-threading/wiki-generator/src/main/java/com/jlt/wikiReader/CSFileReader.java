package com.jlt.wikiReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for reading comma separated keywords in a file
 * 
 * @author Prabal Ghura
 *
 */
public class CSFileReader extends FileWikiReader{

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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
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
