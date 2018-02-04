package com.jlt.wikiReader;

import java.io.File;

/**
 * Factory class for creating File Readers of different types
 * 
 * @author Prabal Ghura
 *
 */
public class FileReaderFactory {
	
	public static FileWikiReader getFileReader(String type, File file) {
		switch (type) {
		case "LineSeparated":
			return new LSFileReader(file);
		case "CommaSeparated":
			return new CSFileReader(file);
		case "LineTabSeparated":
			return new LSTSFileReader(file);
		default:
			return null;
		}
	}
}
