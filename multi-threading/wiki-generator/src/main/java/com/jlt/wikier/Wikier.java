package com.jlt.wikier;

import com.jlt.wikiReader.FileWikiReader;

/**
 * Abstract class for making a wiki call for a list of keywords and create files with descriptions.
 * 
 * @author Prabal Ghura
 *
 */
public abstract class Wikier {
	
	protected static final String baseUrl = "https://en.wikipedia.org/api/rest_v1/page/summary/";
	
	protected FileWikiReader reader;
	
	protected String outputFolder;
	
	public Wikier(FileWikiReader reader, String outputFolder) {
		super();
		this.reader = reader;
		this.outputFolder = outputFolder;
	}

	public abstract void makeCallsAndCreateFiles();
}
