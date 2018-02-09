package com.jlt.wikier;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.wikier.utils.FileReaderException;
import com.jlt.wikier.utils.PropertiesUtils;
import com.jlt.wikier.utils.WikiConstants;
import com.jlt.wikier.utils.WikierTypeNotFound;
import com.jlt.wikireader.FileReaderFactory;
import com.jlt.wikireader.FileReaderType;
import com.jlt.wikireader.FileWikiReader;

/**
 * Executor class for making a wiki calls based on keywords in 
 * different formatted files and creating files for the descriptions.
 * 
 * @author Prabal Ghura
 *
 */
public class WikiProvider {
	
	private static final Logger log = Logger.getLogger(WikiProvider.class.getName());
	
	public static void execute() throws WikierTypeNotFound, FileReaderException {
		DecimalFormat df = new DecimalFormat("#000");
		FileReaderType[] readerTypes = FileReaderType.values();
		WikierType[] implementationTypes = WikierType.values();
		for(WikierType implementationType: implementationTypes) {
			log.log(Level.INFO, () -> "By " + implementationType);
			for(FileReaderType readerType: readerTypes) {
				long time = System.currentTimeMillis();
				FileWikiReader wikireader = FileReaderFactory.getFileReader(readerType, WikiConstants.getFile(readerType));
				Wikier wikier = WikierFactory.getFileReader(implementationType, wikireader, 
						PropertiesUtils.getProperty("basePath") + "/" + implementationType + "/" + readerType);
				wikier.makeCallsAndCreateFiles();
				long timetaken = System.currentTimeMillis() - time;
				log.log(Level.INFO, () -> "For " + readerType + " Time Taken "+ (timetaken/1000) + 
						"." + df.format(timetaken%1000) + " seconds");
			}
		}
	}

	private WikiProvider() {
		super();
	}
}
