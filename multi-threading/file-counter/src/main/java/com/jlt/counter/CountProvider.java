package com.jlt.counter;

/**
 * Executor class for providing word count function.
 * 
 * @author Prabal Ghura
 *
 */
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.counter.exception.FileCounterException;
import com.jlt.counter.extractor.FileExtractorType;
import com.jlt.counter.extractor.FileWordExtractor;
import com.jlt.counter.extractor.FileWordExtractorFactory;
import com.jlt.counter.utils.CounterConstants;

public class CountProvider {
	private static final Logger log = Logger.getLogger(CountProvider.class.getName());
	
	public static void execute() throws FileCounterException {
		DecimalFormat df = new DecimalFormat("#000");
		long time = System.currentTimeMillis();
		FileWordCounter counter = WordCounterFactory.getCounter(FileWordCounterType.STREAM);
		FileWordExtractor extractor = FileWordExtractorFactory.getExtractor(FileExtractorType.STREAM, CounterConstants.INPUT_FOLDER);
		extractor.writeWords();
		counter.countWords();
		long timetaken = System.currentTimeMillis() - time;
		log.log(Level.INFO, () -> "Time Taken "+ (timetaken/1000) + "." + df.format(timetaken%1000) + " seconds");
	}

	private CountProvider() {
		super();
	}
}
