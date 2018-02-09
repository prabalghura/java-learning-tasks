package com.jlt.counter.utils;

import java.io.IOException;
/**
 * Util class for writing to files
 * 
 * @author Prabal Ghura
 *
 */
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.jlt.counter.exception.FileCounterException;

public class FileUtils {
	
	public static final Logger log = Logger.getLogger(FileUtils.class.getName());
	
	public static final void append(String file, Stream<String> stream) throws FileCounterException{
		try {
			Files.write(Paths.get(file),(Iterable<String>) stream::iterator,StandardOpenOption.APPEND);
		} catch (IOException e) {
			log.log(Level.SEVERE, "write() Error while writing stream to file : "+file+"\n"+e.getMessage());
			throw new FileCounterException(e.getMessage());
		}
	}
	
	public static void append(String file, String input) throws FileCounterException{
		try {
			Files.write(Paths.get(file), input.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			log.log(Level.SEVERE, "write() Error while writing to file : "+file+e.getMessage());
			throw new FileCounterException(e.getMessage());
		}
	}
	
	public static void write(String file, String input) throws FileCounterException{
		try {
			Files.write(Paths.get(file), input.getBytes());
		} catch (IOException e) {
			log.log(Level.SEVERE, "write() Error while writing to file : "+file+e.getMessage());
			throw new FileCounterException(e.getMessage());
		}
	}

	private FileUtils() {
		super();
	}
}
