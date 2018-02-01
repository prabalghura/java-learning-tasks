package com.jlt.multiThreading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Implementation class for Splitting up using Brute Force Technique
 * 
 * @author Prabal Ghura
 *
 */
public class BruteForce extends Splitter{
	
	public BruteForce(File file, int linesPerFile, String outputFolder) {
		super(file, linesPerFile, outputFolder);
	}

	@Override
	public void split() {
		long time = System.currentTimeMillis();
		BufferedReader fileReader = null;
		StringBuilder fileText = new StringBuilder("");
		String fileLine = "";
		int linecounter = 0;
		int filecounter = 0;
		try {
			fileReader = new BufferedReader(new FileReader(file));
			fileLine = fileReader.readLine();
			while(fileLine != null) {
				linecounter++;
				fileText.append(fileLine).append("/n");
				if(linecounter==linesPerFile) {
					linecounter=0;
					write(fileText, filecounter++);
					fileText.setLength(0);
				}
				fileLine = fileReader.readLine();
			}
			write(fileText, filecounter);
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		} catch (IOException e) {
			System.err.println("Unable to read Line");
		}
		finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long timetaken = System.currentTimeMillis() - time;
		System.out.println("For " + linesPerFile + " Time Taken "+ (timetaken/1000) + "." + (timetaken%1000) + " seconds");
	}
	
	private void write(StringBuilder text, int filecount) {
		PrintWriter writer = null;
		String fileName = outputFolder + "/data_" + filecount + ".txt";
		try {
			writer = new PrintWriter(new FileWriter(new File(fileName)));
			writer.println(text);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
