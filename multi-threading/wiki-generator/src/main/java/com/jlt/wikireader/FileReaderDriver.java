package com.jlt.wikireader;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.wikier.utils.FileReaderException;

/**
 * Driver class for reading various file formats for keywords
 * 
 * @author Prabal Ghura
 *
 */
public class FileReaderDriver {
	
	private static final Logger log = Logger.getLogger(FileReaderDriver.class.getName());
	
	public static void main(String[] args) {
		try {
			FileReaderProvider.execute();
		} catch (FileReaderException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
}
