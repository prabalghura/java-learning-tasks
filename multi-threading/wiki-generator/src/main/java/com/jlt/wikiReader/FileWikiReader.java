package com.jlt.wikiReader;

import java.io.File;
import java.util.List;

/**
 * Abstract class for reading various file formats and providing list of keywords.
 * 
 * @author Prabal Ghura
 *
 */
public abstract class FileWikiReader {
	
	protected File file;
	
	public abstract List<String> getKeywords();

	public FileWikiReader(File file) {
		super();
		this.file = file;
	}
}
