package com.jlt.wikier;

import com.jlt.wikiReader.FileWikiReader;
import com.jlt.wikier.utils.WikierTypeNotFound;

/**
 * Factory class for creating Wikier of different types
 * 
 * @author Prabal Ghura
 *
 */
public class WikierFactory {
	public static Wikier getFileReader(WikierType type, FileWikiReader reader, String outputFolder) throws WikierTypeNotFound {
		switch (type) {
		case BruteForce:
			return new BFWikier(reader, outputFolder);
		default:
			throw new WikierTypeNotFound();
		}
	}
}
