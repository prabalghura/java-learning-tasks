package com.jlt.LRUCache;

import com.jlt.LRUCache.exception.LRUCacheException;

/**
 * Factory class for creating various LRU Cache.
 * 
 * @author Prabal Ghura
 *
 */
public class LRUFactory {
	public static <T> LRUCache<T> getCache(LRUCacheType type, Integer capacity) throws LRUCacheException {
		switch (type) {
		case Deque:
			return new DequeCache<T>(capacity);
		case MapDeque:
			return new MapDequeCache<T>(capacity);
		default:
			throw new LRUCacheException("Cache Type not found");
		}
	}
}
