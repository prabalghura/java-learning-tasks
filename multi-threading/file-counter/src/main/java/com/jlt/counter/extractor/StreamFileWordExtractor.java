package com.jlt.counter.extractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.jlt.counter.utils.CounterConstants;
import com.jlt.counter.utils.FileUtils;

/**
 * Implementation of Word Extractor using stream technique.
 * 
 * @author Prabal Ghura
 *
 */
public class StreamFileWordExtractor extends FileWordExtractor{
	
	private static final Logger log = Logger.getLogger(StreamFileWordExtractor.class.getName());

	public StreamFileWordExtractor(String filesPath) {
		super(filesPath);
	}

	@Override
	public void writeWords() {
		Path interimFile = Paths.get(CounterConstants.INTERIM_FILE);
		try {
			if (interimFile.toFile().exists()) {
				Files.delete(interimFile);
			}
			Files.createFile(interimFile);
		} catch (IOException e) {
			log.log(Level.SEVERE, "Problem in writeWords() "+e.getMessage());
		}
		
		try (Stream<Path> filePaths = Files.walk(Paths.get(filesPath)) ) {
			filePaths.forEach(file -> {
				if (file.toFile().isFile() && file.toFile().getPath().endsWith(".txt")) {
					try (Stream<String> words = Files.lines(file).map(line -> line.split(" +")).flatMap(Arrays::stream)){
						FileUtils.append(CounterConstants.INTERIM_FILE, words);
					} catch (Exception e) {
						log.log(Level.SEVERE, "Error while writing to the file :" + e.getMessage());
					}
				}
			});
		} catch (IOException e) {
			log.log(Level.SEVERE, "Error while streaming the files : " + e.getMessage());
		}
	}
}
