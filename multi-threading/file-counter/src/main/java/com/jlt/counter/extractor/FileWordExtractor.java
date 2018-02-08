package com.jlt.counter.extractor;

/**
 * Abstract class for Writing all File Words into one file
 * 
 * @author Prabal Ghura
 *
 */
public abstract class FileWordExtractor {
	protected String filesPath;

	public FileWordExtractor(String filesPath) {
		super();
		this.filesPath = filesPath;
	}
	
	public abstract void writeWords();
}
