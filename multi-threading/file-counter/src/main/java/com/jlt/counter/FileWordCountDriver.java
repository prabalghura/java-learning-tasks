package com.jlt.counter;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.counter.exception.FileCounterException;

/**
 * Driver class for Word count function
 * 
 * @author Prabal Ghura
 *
 */
public class FileWordCountDriver {
	private static final Logger log = Logger.getLogger(FileWordCountDriver.class.getName());
	
	public static void main(String[] args) {
		try {
			CountProvider.execute();
		} catch (FileCounterException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
}
