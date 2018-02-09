package com.jlt.lrucache;

import com.jlt.lrucache.exception.LRUCacheException;

/**
 * Factory class for creating various LRU Cache.
 * 
 * @author Prabal Ghura
 *
 */
public class LRUFactory {
	public static <T> LRUCache<T> getCache(LRUCacheType type, Integer capacity) throws LRUCacheException {
		switch (type) {
		case DEQUE:
			return new DequeCache<>(capacity);
		case MAPDEQUE:
			return new MapDequeCache<>(capacity);
		default:
			throw new LRUCacheException("Cache Type not found");
		}
	}

	private LRUFactory() {
		super();
	}
}
