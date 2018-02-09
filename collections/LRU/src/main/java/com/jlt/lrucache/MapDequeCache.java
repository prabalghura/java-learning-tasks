package com.jlt.lrucache;

import java.util.HashMap;
import java.util.Map;

import com.jlt.lrucache.exception.LRUCacheException;
import com.jlt.lrucache.model.CacheNode;

/**
 * Implementation for LRU cache using custom deque and hashmap data-structures
 * one condition generic type must honor the hashCode() and equals() methods contract
 * 
 * @author Prabal Ghura
 *
 */
public class MapDequeCache<T> extends DequeCache<T>{
	
	/*needed just so that we need not traverse the deque to find current
	size or find whether an element is already present in cache or not.*/
	private Map<T, CacheNode<T>> map;

	public MapDequeCache(Integer capacity) throws LRUCacheException {
		super(capacity);
		this.map = new HashMap<>();
	}
	
	@Override
	protected CacheNode<T> get(T t) {
		return this.map.get(t);
	}
	
	@Override
	protected CacheNode<T> addNewNode(T t) {
		CacheNode<T> node = super.addNewNode(t);
		this.map.put(t, node);
		return node;
	}
	
	@Override
	protected void removeLast() {
		super.removeLast();
		
		//removing from map
		this.map.remove(this.tail.getValue());
	}
}
