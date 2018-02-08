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
		switch (type) {
		case Stream:
			return new StreamFileWordCounter();
		default:
			throw new FileCounterException("Counter not found");
		}
	}
}
