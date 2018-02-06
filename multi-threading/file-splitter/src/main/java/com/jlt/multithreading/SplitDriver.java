package com.jlt.multithreading;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.multithreading.utils.SplitterConstants;
import com.jlt.multithreading.utils.SplitterNotFound;

/**
 * Driver class for running various Techniques
 * 
 * @author Prabal Ghura
 *
 */
public class SplitDriver {
	
	private static final Logger log = Logger.getLogger(SplitDriver.class.getName());
	
	public static void main(String[] args) {
		File tfile = new File(SplitterConstants.basePath + "/" + SplitterConstants.inputFileName);
		String outputFolderPath = SplitterConstants.basePath + "/" + SplitterConstants.outputFolder;
		SplitterType[] types = SplitterType.values();
		int[] linesPerFile = {1000, 500, 100, 50, 10};
		for(SplitterType type: types)
		{
			log.log(Level.INFO, "By "+type);
			for(int lines: linesPerFile)
			{
				Splitter splitter = null;
				try {
					splitter = SplitterFactory.getSplitter(type, tfile, lines, outputFolderPath);
				} catch (SplitterNotFound e) {
					log.log(Level.SEVERE, e.getMessage());
				}
				splitter.split();
			}
		}
	}
}
