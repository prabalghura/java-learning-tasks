package com.jlt.LRUCache;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.LRUCache.exception.LRUCacheException;

public class LRUProvider {
	private static final Logger log = Logger.getLogger(LRUProvider.class.getName());
	
	public static void execute() {
		LRUCache<Object> cache = null;
		try {
			cache = LRUFactory.getCache(LRUCacheType.MapDeque, 7);
		} catch (LRUCacheException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		Object[] values = new Object[]{1,2,3,1,5,"abc","def"};
		System.out.println(cache);
		for(Object i: values) {
			cache.add(i);
			System.out.println("Added "+ i + " " +cache);
		}
	}
}
