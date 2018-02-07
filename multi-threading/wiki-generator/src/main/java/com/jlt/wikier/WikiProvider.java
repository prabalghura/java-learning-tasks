package com.jlt.wikier;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.wikiReader.FileReaderFactory;
import com.jlt.wikiReader.FileReaderType;
import com.jlt.wikiReader.FileWikiReader;
import com.jlt.wikier.utils.FileReaderTypeNotFound;
import com.jlt.wikier.utils.WikiConstants;
import com.jlt.wikier.utils.WikierTypeNotFound;

/**
 * Executor class for making a wiki calls based on keywords in 
 * different formatted files and creating files for the descriptions.
 * 
 * @author Prabal Ghura
 *
 */
public class WikiProvider {
	
	private static final Logger log = Logger.getLogger(WikiProvider.class.getName());
	
	public static void execute() {
		DecimalFormat df = new DecimalFormat("#000");
		FileReaderType[] readerTypes = FileReaderType.values();
		WikierType[] implementationTypes = WikierType.values();
		for(WikierType implementationType: implementationTypes) {
			log.log(Level.INFO, "By " + implementationType);
			for(FileReaderType readerType: readerTypes) {
				long time = System.currentTimeMillis();
				FileWikiReader wikireader = null;
				Wikier wikier = null;
				try {
					wikireader = FileReaderFactory.getFileReader(readerType, WikiConstants.getFile(readerType));
					wikier = WikierFactory.getFileReader(implementationType, wikireader, 
							WikiConstants.basePath + "/" + implementationType + "/" + readerType);
				} catch (FileReaderTypeNotFound | WikierTypeNotFound e) {
					log.log(Level.SEVERE, e.getMessage());
				}
				wikier.makeCallsAndCreateFiles();
				long timetaken = System.currentTimeMillis() - time;
				log.log(Level.INFO, "For " + readerType + " Time Taken "+ (timetaken/1000) + 
						"." + df.format(timetaken%1000) + " seconds");
			}
		}
	}
}
