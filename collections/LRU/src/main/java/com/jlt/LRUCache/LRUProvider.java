package com.jlt.LRUCache;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.LRUCache.exception.LRUCacheException;

/**
 * Provider class for sampling on LRU cache implementations
 * 
 * @author Prabal Ghura
 *
 */
public class LRUProvider {
	private static final Logger log = Logger.getLogger(LRUProvider.class.getName());
	
	public static void execute() {
		LRUCache<Object> cache = null;
		LRUCacheType[] implementationTypes = LRUCacheType.values();
		for(LRUCacheType implementationType: implementationTypes) {
			log.log(Level.INFO, "For "+ implementationType);
			try {
				cache = LRUFactory.getCache(implementationType, 3);
			} catch (LRUCacheException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
			//wrong way to do this just wanted to demonstrate that anything can be stored in the cache as it is a generic one.
			Object[] values = new Object[]{1,2,3,1,5,"abc","def"};
			log.log(Level.INFO, cache.toString());
			for(Object i: values) {
				cache.add(i);
				log.log(Level.INFO, "Added "+ i + " " +cache);
			}
		}
	}
}
