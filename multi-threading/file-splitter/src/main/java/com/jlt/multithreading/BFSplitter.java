package com.jlt.multithreading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation class for Splitting up using Brute Force Technique
 * 
 * @author Prabal Ghura
 *
 */
public class BFSplitter extends Splitter{
	
	private static final Logger log = Logger.getLogger(BFSplitter.class.getName());
	
	public BFSplitter(File file, int linesPerFile, String outputFolder) {
		super(file, linesPerFile, outputFolder);
	}

	@Override
	public void split() {
		long time = System.currentTimeMillis();
		BufferedReader fileReader = null;
		List<String> fileText = new ArrayList<String>();
		String fileLine = "";
		int linecounter = 0;
		int filecounter = 0;
		try {
			fileReader = new BufferedReader(new FileReader(file));
			fileLine = fileReader.readLine();
			while(fileLine != null) {
				linecounter++;
				fileText.add(fileLine);
				if(linecounter==linesPerFile) {
					linecounter=0;
					write(fileText, filecounter++);
					fileText.clear();
				}
				fileLine = fileReader.readLine();
			}
			write(fileText, filecounter);
		} catch (FileNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		long timetaken = System.currentTimeMillis() - time;
		log.log(Level.INFO, "For " + linesPerFile + " Time Taken "+ (timetaken/1000) + "." + (timetaken%1000) + " seconds");
	}
	
	private void write(List<String>  text, int filecount) {
		try {
			Path path = Paths.get(outputFolder, "data_"+ filecount +".csv");
			Files.write(path, text, Charset.defaultCharset());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
}
