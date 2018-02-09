package com.jlt.lrucache;

import com.jlt.lrucache.exception.LRUCacheException;

/**
 * Abstract class for Generic LRU Cache
 * 
 * @author Prabal Ghura
 *
 */
public abstract class LRUCache<T> {
	
	protected Integer capacity;

	public LRUCache(Integer capacity) throws LRUCacheException {
		super();
		if(capacity<1)
			throw new LRUCacheException("Capacity must not be less than 1");
		this.capacity = capacity;
	}

	public abstract String toString();
	
	public abstract void add(T t);
}
