package com.jlt.suggester;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.suggester.exception.AutoSuggestException;

/**
 * Driver class for Auto suggest function
 * 
 * @author Prabal Ghura
 *
 */
public class AutoSuggestDriver {
	private static final Logger log = Logger.getLogger(AutoSuggestDriver.class.getName());
	
	public static void main(String[] args) {
		try {
			AutoSuggestProvider.execute();
		} catch (AutoSuggestException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
}
