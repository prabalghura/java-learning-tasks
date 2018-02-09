package com.jlt.wikireader;

import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.wikier.utils.FileReaderException;
import com.jlt.wikier.utils.WikiConstants;

/**
 * Executor class for reading various file formats for keywords
 * 
 * @author Prabal Ghura
 *
 */
public class FileReaderProvider {
	private static final Logger log = Logger.getLogger(FileReaderProvider.class.getName());
	
	public static void execute() throws FileReaderException {
		DecimalFormat df = new DecimalFormat("#000");
		FileReaderType[] readerTypes = FileReaderType.values();
		for(FileReaderType readerType: readerTypes) {
			long time = System.currentTimeMillis();
			FileWikiReader reader = FileReaderFactory.getFileReader(readerType, WikiConstants.getFile(readerType));
			List<String> keywords = reader.getKeywords();
			for(String s: keywords) {
				log.log(Level.INFO, s);
			}
			long timetaken = System.currentTimeMillis() - time;
			log.log(Level.INFO, () -> "Time Taken "+ (timetaken/1000) + "." + df.format(timetaken%1000) + " seconds");
		}
	}

	private FileReaderProvider() {
		super();
	}
}
