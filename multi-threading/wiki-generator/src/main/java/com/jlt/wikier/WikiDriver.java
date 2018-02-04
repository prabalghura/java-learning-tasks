package com.jlt.wikier;

import java.io.File;
import java.text.DecimalFormat;

import com.jlt.wikiReader.FileReaderFactory;
import com.jlt.wikiReader.FileWikiReader;

/**
 * Driver class for making a wiki calls based on keywords in 
 * different formatted files and creating files for the descriptions.
 * 
 * @author Prabal Ghura
 *
 */
public class WikiDriver {
	
	private static final String basePath = "/home/prabhalg/Documents/Projects/Java/Task2";
	
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#000");
		String[] readerTypes = {"LineSeparated", "CommaSeparated", "LineTabSeparated"};
		String[] files = {"Multithreading_Task2_ProgrammingLanguages.txt",
				"Multithreading_Task_2_java Keywords.txt",
				"Multithreading_Task_2_fortune1000companies.txt"};
		String[] types = {"BruteForce"};
		for(String type: types) {
			System.out.println("By " + type);
			for(int i=0; i<readerTypes.length; i++) {
				long time = System.currentTimeMillis();
				String reader = readerTypes[i];
				File file = new File(basePath, files[i]);
				FileWikiReader wikireader = FileReaderFactory.getFileReader(reader, file);
				Wikier wikier = new BFWikier(wikireader, basePath + "/" + type + "/" + reader);
				wikier.makeCallsAndCreateFiles();
				long timetaken = System.currentTimeMillis() - time;
				System.out.println("For " + reader + " Time Taken "+ (timetaken/1000) + 
						"." + df.format(timetaken%1000) + " seconds");
			}
		}
	}
}
