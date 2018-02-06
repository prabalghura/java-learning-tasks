package com.jlt.wikiReader;

import java.io.File;

import com.jlt.wikier.utils.FileReaderTypeNotFound;

/**
 * Factory class for creating File Readers of different types
 * 
 * @author Prabal Ghura
 *
 */
public class FileReaderFactory {
	
	public static FileWikiReader getFileReader(FileReaderType type, File file) throws FileReaderTypeNotFound {
		switch (type) {
		case LineSeparated:
			return new LSFileReader(file);
		case CommaSeparated:
			return new CSFileReader(file);
		case LineTabSeparated:
			return new LSTSFileReader(file);
		default:
			throw new FileReaderTypeNotFound();
		}
	}
}
