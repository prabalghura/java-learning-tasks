package com.jlt.wikier;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.wikier.utils.FileReaderException;
import com.jlt.wikier.utils.WikierTypeNotFound;

/**
 * Driver class for making a wiki calls based on keywords in 
 * different formatted files and creating files for the descriptions.
 * 
 * @author Prabal Ghura
 *
 */
public class WikiDriver {
	
	private static final Logger log = Logger.getLogger(WikiDriver.class.getName());
	
	public static void main(String[] args) {
		try {
			WikiProvider.execute();
		} catch (WikierTypeNotFound | FileReaderException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
}
