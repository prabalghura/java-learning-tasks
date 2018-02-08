package com.jlt.counter;

/**
 * Implementation for Word count function using Streaming technique
 * 
 * @author Prabal Ghura
 *
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;

import com.jlt.counter.exception.FileCounterException;
import com.jlt.counter.utils.CounterConstants;
import com.jlt.counter.utils.FileUtils;

public class StreamFileWordCounter implements FileWordCounter{

	private static final Logger log = Logger.getLogger(StreamFileWordCounter.class);

	@Override
	public void countWords() {
		Path outputFile = Paths.get(CounterConstants.OUTPUT_FILE);
		try {
			if (Files.exists(outputFile)) {
				Files.delete(outputFile);
			}
			Files.createFile(outputFile);
		} catch (IOException e) {
			log.error("Problem in writeWords() "+e.getMessage());
		}
		try {
			Map<String, Integer> counter = Files.lines(Paths.get(CounterConstants.INTERIM_FILE))
					.collect(Collectors.toList()).stream()
					.collect(Collectors.toMap(word -> word.toLowerCase(), word -> 1, Integer::sum));
			StringBuilder stb = new StringBuilder();
			for (Map.Entry<String, Integer> entry : counter.entrySet()) {
				stb.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
			}
			FileUtils.write(CounterConstants.OUTPUT_FILE, stb.toString());
		} catch (IOException e) {
			log.error("Problem while reading from interim file");
		} catch (FileCounterException e) {
			log.error("Problem while writing to output file");
		}

	}
}
