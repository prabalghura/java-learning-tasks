package com.jlt.lrucache;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.lrucache.exception.LRUCacheException;

/**
 * Driver class for executing LRU Cache implementation
 * 
 * @author Prabal Ghura
 *
 */
public class LRUDriver {
	private static final Logger log = Logger.getLogger(LRUDriver.class.getName());
	
	public static void main(String[] args) {
		try {
			LRUProvider.execute();
		} catch (LRUCacheException e) {
			log.log(Level.INFO, e.getMessage());
		}
	}
}
