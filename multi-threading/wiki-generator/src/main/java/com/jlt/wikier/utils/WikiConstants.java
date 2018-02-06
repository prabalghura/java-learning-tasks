package com.jlt.wikier.utils;

import java.io.File;

import com.jlt.wikiReader.FileReaderType;

/**
 * Utils class for providing files and paths for reading input and providing output
 * TODO: to be replaced by properties file soon.
 * 
 * @author Prabal Ghura
 *
 */
public class WikiConstants {
	public static final String basePath = "/home/prabhalg/Documents/Projects/Java/Task2";
	
	private static final String LineSeparated = "Multithreading_Task2_ProgrammingLanguages.txt";
	
	private static final String CommaSeparated = "Multithreading_Task_2_java Keywords.txt";
	
	private static final String LineTabSeparated = "Multithreading_Task_2_fortune1000companies.txt";
	
	public static File getFile(FileReaderType type) {
		switch (type) {
		case LineSeparated:
			return new File(basePath, LineSeparated);
		case CommaSeparated:
			return new File(basePath, CommaSeparated);
		case LineTabSeparated:
			return new File(basePath, LineTabSeparated);
		default:
			return null;
		}
	}
}
