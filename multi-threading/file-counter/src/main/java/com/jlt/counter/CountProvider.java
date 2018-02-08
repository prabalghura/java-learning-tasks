package com.jlt.counter;

/**
 * Executor class for providing word count function.
 * 
 * @author Prabal Ghura
 *
 */
import java.text.DecimalFormat;
import org.apache.log4j.Logger;

import com.jlt.counter.exception.FileCounterException;
import com.jlt.counter.extractor.FileExtractorType;
import com.jlt.counter.extractor.FileWordExtractor;
import com.jlt.counter.extractor.FileWordExtractorFactory;
import com.jlt.counter.utils.CounterConstants;

public class CountProvider {
	private static final Logger log = Logger.getLogger(CountProvider.class.getName());
	
	public static void execute() {
		DecimalFormat df = new DecimalFormat("#000");
		long time = System.currentTimeMillis();
		FileWordCounter counter = null;
		FileWordExtractor extractor = null;
		try {
			counter = WordCounterFactory.getCounter(FileWordCounterType.Stream);
			extractor = FileWordExtractorFactory.getExtractor(FileExtractorType.Stream, CounterConstants.INPUT_FOLDER);
		} catch (FileCounterException e) {
			log.error(e.getMessage());
		}
		extractor.writeWords();
		counter.countWords();
		long timetaken = System.currentTimeMillis() - time;
		System.out.println(("Time Taken "+ (timetaken/1000) + "." + df.format(timetaken%1000) + " seconds"));
	}
}
