package com.jlt.counter.extractor;

import com.jlt.counter.exception.FileCounterException;

/**
 * Factory class for creating Word Extractor of different types
 * 
 * @author Prabal Ghura
 *
 */
public class FileWordExtractorFactory {
	public static FileWordExtractor getExtractor(FileExtractorType type, String filesPath) throws FileCounterException {
		switch (type) {
		case Stream:
			return new StreamFileWordExtractor(filesPath);
		default:
			throw new FileCounterException("Extractor not found");
		}
	}
}
