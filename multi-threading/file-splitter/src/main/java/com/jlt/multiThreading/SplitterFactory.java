package com.jlt.multiThreading;

import java.io.File;

/**
 * Factory class for creating Splitters of different types
 * 
 * @author Prabal Ghura
 *
 */
public class SplitterFactory {
	
	public static Splitter getSplitter(String type, File file, int linesPerFile, String outputFolder) {
		switch (type) {
		case "ForkJoin":
			return new ForkJoin(file, linesPerFile, outputFolder);
		case "BruteForce":
			return new BruteForce(file, linesPerFile, outputFolder);
		default:
			return null;
		}
	}
}
