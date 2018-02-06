package com.jlt.multithreading;

import java.io.File;

import com.jlt.multithreading.utils.SplitterNotFound;

/**
 * Factory class for creating Splitters of different types
 * 
 * @author Prabal Ghura
 *
 */
public class SplitterFactory {
	
	public static Splitter getSplitter(SplitterType type, File file, int linesPerFile, String outputFolder) throws SplitterNotFound {
		switch (type) {
		case ForkJoin:
			return new FJSplitter(file, linesPerFile, outputFolder);
		case BruteForce:
			return new BFSplitter(file, linesPerFile, outputFolder);
		default:
			throw new SplitterNotFound();
		}
	}
}
