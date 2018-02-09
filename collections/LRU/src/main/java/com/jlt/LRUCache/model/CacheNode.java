package com.jlt.LRUCache.model;

public class CacheNode<T>{
	
	private T value;
	private CacheNode<T> previous;
	private CacheNode<T> next;

	public CacheNode(T value) {
		super();
		this.value = value;
	}
	
	public String toString() {
    	return String.valueOf(value);
    }

	public CacheNode<T> getPrevious() {
		return previous;
	}

	public void setPrevious(CacheNode<T> previous) {
		this.previous = previous;
	}

	public CacheNode<T> getNext() {
		return next;
	}

	public void setNext(CacheNode<T> next) {
		this.next = next;
	}

	public T getValue() {
		return value;
	}

	@Override
	public boolean equals(Object arg0) {
		return this.getValue().equals(arg0);
	}
}
