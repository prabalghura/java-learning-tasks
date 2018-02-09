package com.jlt.wikier;

import com.jlt.wikier.utils.WikierTypeNotFound;
import com.jlt.wikireader.FileWikiReader;

/**
 * Factory class for creating Wikier of different types
 * 
 * @author Prabal Ghura
 *
 */
public class WikierFactory {
	public static Wikier getFileReader(WikierType type, FileWikiReader reader, String outputFolder) throws WikierTypeNotFound {
		switch (type) {
		case BRUTEFORCE:
			return new BFWikier(reader, outputFolder);
		case EXECUTOR:
			return new ExecutorWikier(reader, outputFolder);
		default:
			throw new WikierTypeNotFound();
		}
	}

	private WikierFactory() {
		super();
	}
}
