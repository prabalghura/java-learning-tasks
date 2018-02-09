package com.jlt.counter;

import com.jlt.counter.exception.FileCounterException;

/**
 * Factory class for creating Word count objects of different type implementations
 * 
 * @author Prabal Ghura
 *
 */
public class WordCounterFactory {
	public static FileWordCounter getCounter(FileWordCounterType type) throws FileCounterException {
		if (type == FileWordCounterType.STREAM)
			return new StreamFileWordCounter();
		else
			throw new FileCounterException("Counter not found");
	}

	private WordCounterFactory() {
		super();
	}
}
