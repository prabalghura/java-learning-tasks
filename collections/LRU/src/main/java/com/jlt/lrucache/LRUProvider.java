package com.jlt.lrucache;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.lrucache.exception.LRUCacheException;

/**
 * Provider class for sampling on LRU cache implementations
 * 
 * @author Prabal Ghura
 *
 */
public class LRUProvider {
	private static final Logger log = Logger.getLogger(LRUProvider.class.getName());
	
	public static void execute() throws LRUCacheException {
		LRUCache<Object> cache = null;
		LRUCacheType[] implementationTypes = LRUCacheType.values();
		for(LRUCacheType implementationType: implementationTypes) {
			log.log(Level.INFO, () -> "For "+ implementationType);
			cache = LRUFactory.getCache(implementationType, 3);
			//wrong way to do this just wanted to demonstrate that anything can be stored in the cache as it is a generic one.
			Object[] values = new Object[]{1,2,3,1,5,"abc","def"};
			String message = cache.toString();
			log.log(Level.INFO, message);
			for(Object i: values) {
				cache.add(i);
				StringBuilder stb = new StringBuilder().append("Added ")
						.append(i)
						.append(" ")
						.append(cache);
				message = stb.toString();
				log.log(Level.INFO, message);
			}
		}
	}

	private LRUProvider() {
		super();
	}
}
