package com.jlt.LRUCache;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.jlt.LRUCache.exception.LRUCacheException;
import com.jlt.LRUCache.model.CacheNode;

public class MapDequeCache<T> extends LRUCache<T>{
	
	/*needed just so that we need not traverse the deque to find current
	size or find whether an element is already present in cache or not.*/
	private Map<T, CacheNode<T>> map;
	private CacheNode<T> head;
	private CacheNode<T> tail;

	public MapDequeCache(Integer capacity) throws LRUCacheException {
		super(capacity);
		this.map = new HashMap<T, CacheNode<T>>();
		this.head = null;
		this.tail = null;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder("Cache is ");
		CacheNode<T> head = this.head;
		Boolean isEmpty = true;
		while(head != null) {
			isEmpty = false;
			output.append(head.toString() + ", ");
			head = head.getNext();
		}
		if(!isEmpty)
			output.setLength(output.length()-2);
		output.append(".");
		return output.toString();
	}

	@Override
	public void add(T t) {
		CacheNode<T> node;
		if(this.map.containsKey(t)) {
			node = this.map.get(t);
			bringToFront(node);
		}
		else {
			node = new CacheNode<T>(t);
			if(this.map.size()==this.capacity) removeLast();
			if(this.map.size()==0) this.tail = node;
			setAsFront(node);
			this.map.put(t, node);
		}
	}
	
	private void bringToFront(CacheNode<T> node) {
		//updating adjacent nodes to point each other
		if(!Objects.isNull(node.getPrevious()))
			node.getPrevious().setNext(node.getNext());
		else
			this.head = node.getNext();
		if(!Objects.isNull(node.getNext()))
			node.getNext().setPrevious(node.getPrevious());
		else
			this.tail = node.getPrevious();
		
		setAsFront(node);
	}
	
	private void setAsFront(CacheNode<T> node) {
		//updating current node pointers
		node.setPrevious(null);
		node.setNext(head);
		
		//pointing head previous to current node
		if(!Objects.isNull(this.head))
			this.head.setPrevious(node);
		
		//making current node head
		this.head = node;
	}
	
	private void removeLast() {
		//removing n-1th node's connection to nth node
		if(this.tail.getPrevious() != null)
			this.tail.getPrevious().setNext(null);
		
		//removing from map
		this.map.remove(this.tail.getValue());
		
		//moving tail one node back
		this.tail = this.tail.getPrevious();
	}
}
