package com.jlt.wikier;

import com.jlt.wikiReader.FileWikiReader;

/**
 * Factory class for creating Wikier of different types
 * 
 * @author Prabal Ghura
 *
 */
public class WikierFactory {
	public static Wikier getFileReader(String type, FileWikiReader reader, String outputFolder) {
		switch (type) {
		case "BruteForce":
			return new BFWikier(reader, outputFolder);
		case "Async":
			return new AsyncWikier(reader, outputFolder);
		default:
			return null;
		}
	}
}
