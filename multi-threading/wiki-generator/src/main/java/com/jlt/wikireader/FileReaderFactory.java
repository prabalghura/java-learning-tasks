package com.jlt.wikireader;

import java.io.File;

import com.jlt.wikier.utils.FileReaderException;

/**
 * Factory class for creating File Readers of different types
 * 
 * @author Prabal Ghura
 *
 */
public class FileReaderFactory {
	
	public static FileWikiReader getFileReader(FileReaderType type, File file) throws FileReaderException {
		switch (type) {
		case LINESEPARATED:
			return new LSFileReader(file);
		case COMMASEPARATED:
			return new CSFileReader(file);
		case LINETABSEPARATED:
			return new LSTSFileReader(file);
		default:
			throw new FileReaderException("File Reader Not Found");
		}
	}

	private FileReaderFactory() {
		super();
	}
}
