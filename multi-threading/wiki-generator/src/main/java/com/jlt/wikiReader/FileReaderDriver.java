package com.jlt.wikiReader;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Driver class for reading various file formats for keywords
 * 
 * @author Prabal Ghura
 *
 */
public class FileReaderDriver {
	
	private static final String basePath = "/home/prabhalg/Documents/Projects/Java/Task2";
	
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#000");
		String[] types = {"LineSeparated", "CommaSeparated", "LineTabSeparated"};
		String[] files = {"Multithreading_Task2_ProgrammingLanguages.txt",
				"Multithreading_Task_2_java Keywords.txt",
				"Multithreading_Task_2_fortune1000companies.txt"};
		for(int i=0; i<types.length; i++) {
			String type = types[i];
			long time = System.currentTimeMillis();
			File file = new File(basePath, files[i]);
			FileWikiReader reader = FileReaderFactory.getFileReader(type, file);
			List<String> keywords = reader.getKeywords();
//			for(String s: keywords) {
//				System.out.println(s);
//			}
			long timetaken = System.currentTimeMillis() - time;
			System.out.println("Time Taken "+ (timetaken/1000) + "." + df.format(timetaken%1000) + " seconds");
		}
	}
}
