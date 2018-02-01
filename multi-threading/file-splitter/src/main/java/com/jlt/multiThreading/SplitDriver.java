package com.jlt.multiThreading;

import java.io.File;

public class SplitDriver {
	
	private static final String basePath = "/home/prabhalg/Documents/Projects/Java";
	
	private static final String outputFolder = "fileContainer";
	
	private static final String inputFileName = "Multithreading_Task1_Books.csv"; 
	
	public static void main(String[] args) {
		File tfile = new File(basePath + "/" + inputFileName);
		String outputFolderPath = basePath + "/" + outputFolder;
		Splitter splitter = new BruteForce(tfile, 1000, outputFolderPath);
		test(splitter);
	}
	
	private static void test(Splitter splitter) {
		int[] a = {1000, 500, 100, 50, 10};
		System.out.println("By " + splitter.getClass().getSimpleName());
		for(int i : a) {
			splitter.setLinesPerFile(i);
			splitter.split();
		}
		System.out.println();
	}
}
