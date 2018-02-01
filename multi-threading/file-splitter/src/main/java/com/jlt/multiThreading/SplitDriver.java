package com.jlt.multiThreading;

import java.io.File;

/**
 * Driver class for running various Techniques
 * 
 * @author Prabal Ghura
 *
 */
public class SplitDriver {
	
	private static final String basePath = "/home/prabhalg/Documents/Projects/Java";
	
	private static final String outputFolder = "fileContainer";
	
	private static final String inputFileName = "Multithreading_Task1_Books.csv";
	
	public static void main(String[] args) {
		File tfile = new File(basePath + "/" + inputFileName);
		String outputFolderPath = basePath + "/" + outputFolder;
		String[] type = {"ForkJoin", "BruteForce"};
		int[] linesPerFile = {1000, 500, 100, 50, 10};
		for(String sType: type)
		{
			System.out.println("By "+sType);
			for(int lines: linesPerFile)
			{
				Splitter splitter = SplitterFactory.getSplitter(sType, tfile, lines, outputFolderPath);
				splitter.split();
			}
			System.out.println();
		}
	}
}
