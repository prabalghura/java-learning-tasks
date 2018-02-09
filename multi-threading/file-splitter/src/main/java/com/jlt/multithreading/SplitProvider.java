package com.jlt.multithreading;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.multithreading.utils.PropertiesUtils;
import com.jlt.multithreading.utils.SplitterNotFound;

/**
 * Executor class for providing split functionality
 * 
 * @author Prabal Ghura
 *
 */
public class SplitProvider {
	
	private SplitProvider() {
		super();
	}

	private static final Logger log = Logger.getLogger(SplitProvider.class.getName());
	
	public static void execute() throws SplitterNotFound {
		File tfile = new File(PropertiesUtils.getProperty("basePath") + File.separator + PropertiesUtils.getProperty("inputFileName"));
		String outputFolderPath = PropertiesUtils.getProperty("basePath") + File.separator + PropertiesUtils.getProperty("outputFolder");
		SplitterType[] types = SplitterType.values();
		int[] linesPerFile = {1000, 500, 100, 50, 10};
		for(SplitterType type: types)
		{
			log.log(Level.INFO, () -> "By "+type);
			for(int lines: linesPerFile)
			{
				Splitter splitter = SplitterFactory.getSplitter(type, tfile, lines, outputFolderPath);
				splitter.split();
			}
		}
	}
}
