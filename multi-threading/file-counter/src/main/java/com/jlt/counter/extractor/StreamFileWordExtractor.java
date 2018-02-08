package com.jlt.counter.extractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.jlt.counter.utils.CounterConstants;
import com.jlt.counter.utils.FileUtils;

/**
 * Implementation of Word Extractor using stream technique.
 * 
 * @author Prabal Ghura
 *
 */
public class StreamFileWordExtractor extends FileWordExtractor{
	
	private static final Logger log = Logger.getLogger(StreamFileWordExtractor.class);

	public StreamFileWordExtractor(String filesPath) {
		super(filesPath);
	}

	@Override
	public void writeWords() {
		Path interimFile = Paths.get(CounterConstants.INTERIM_FILE);
		try {
			if (Files.exists(interimFile)) {
				Files.delete(interimFile);
			}
			Files.createFile(interimFile);
		} catch (IOException e) {
			log.error("Problem in writeWords() "+e.getMessage());
		}
		
		try (Stream<Path> filePaths = Files.walk(Paths.get(filesPath))) {
			filePaths.forEach(file -> {
				if (!file.endsWith(".txt")) {
					try {
						Stream<String> fileLines = Files.lines(file).map(line -> line.split("\\s+")).flatMap(Arrays::stream).sorted();
						FileUtils.append(CounterConstants.INTERIM_FILE, fileLines);
					} catch (Exception e) {
						log.error("Error while writing to the file :" + e.getMessage());
					}
				}
			});
		} catch (IOException e) {
			log.error("Error while streaming the files : " + e.getMessage());
		}
	}
}
