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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jlt.counter.exception.FileCounterException;
import com.jlt.counter.utils.CounterConstants;
import com.jlt.counter.utils.FileUtils;

public class StreamFileWordCounter implements FileWordCounter{

	private static final Logger log = Logger.getLogger(StreamFileWordCounter.class.getName());

	@Override
	public void countWords() {
		Path outputFile = Paths.get(CounterConstants.OUTPUT_FILE);
		try {
			if (outputFile.toFile().exists()) {
				Files.delete(outputFile);
			}
			Files.createFile(outputFile);
		} catch (IOException e) {
			log.log(Level.SEVERE, "Problem in writeWords() "+e.getMessage());
		}
		try (Stream<String> wordStream = Files.lines(Paths.get(CounterConstants.INTERIM_FILE));) {
			Map<String, Integer> counter = wordStream.collect(Collectors.toList()).stream()
					.collect(Collectors.toMap(String::toLowerCase, word -> 1, Integer::sum));
			StringBuilder stb = new StringBuilder();
			for (Map.Entry<String, Integer> entry : counter.entrySet()) {
				stb.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
			}
			FileUtils.write(CounterConstants.OUTPUT_FILE, stb.toString());
		} catch (IOException e) {
			log.log(Level.SEVERE, "Problem while reading from interim file");
		} catch (FileCounterException e) {
			log.log(Level.SEVERE, "Problem while writing to output file");
		}

	}
}
