package com.jlt.multithreading;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.multithreading.utils.SplitterNotFound;

/**
 * Driver class for running various Techniques
 * 
 * @author Prabal Ghura
 *
 */
public class SplitDriver {
	
	private static final Logger log = Logger.getLogger(SplitDriver.class.getName());
	
	public static void main(String[] args) {
		try {
			SplitProvider.execute();
		} catch (SplitterNotFound e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
}
