package com.jlt.LRUCache;

import com.jlt.LRUCache.exception.LRUCacheException;

public class LRUFactory {
	public static <T> LRUCache<T> getCache(LRUCacheType type, Integer capacity) throws LRUCacheException {
		switch (type) {
		case MapDeque:
			return new MapDequeCache<T>(capacity);
		default:
			throw new LRUCacheException("Cache Type not found");
		}
	}
}
