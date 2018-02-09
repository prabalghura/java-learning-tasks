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
		if (type == FileExtractorType.STREAM) 
			return new StreamFileWordExtractor(filesPath);
		else
			throw new FileCounterException("Extractor not found");
	}

	private FileWordExtractorFactory() {
		super();
	}
}
