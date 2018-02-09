package com.jlt.LRUCache;

import java.util.Objects;

import com.jlt.LRUCache.exception.LRUCacheException;
import com.jlt.LRUCache.model.CacheNode;

/**
 * Implementation for LRU cache using custom deque condition generic type must honor and equals() methods contract
 * 
 * @author Prabal Ghura
 *
 */
public class DequeCache<T> extends LRUCache<T>{

	protected CacheNode<T> head;
	protected CacheNode<T> tail;
	protected Integer size;
	
	public DequeCache(Integer capacity) throws LRUCacheException {
		super(capacity);
		this.head = null;
		this.tail = null;
		this.size = 0;
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
	
	protected CacheNode<T> get(T t) {
		CacheNode<T> head = this.head;
		while(head != null) {
			if(head.equals(t))
				return head;
			head = head.getNext();
		}
		return null;
	}

	@Override
	public void add(T t) {
		CacheNode<T> node = this.get(t);
		if(!Objects.isNull(node)) bringToFront(node);
		else addNewNode(t);
	}
	
	protected CacheNode<T> addNewNode(T t) {
		CacheNode<T> node = new CacheNode<T>(t);
		if(this.size==this.capacity) removeLast();
		if(this.size==0) this.tail = node;
		setAsFront(node);
		this.size++;
		return node;
	}
	
	protected void bringToFront(CacheNode<T> node) {
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
	
	protected void setAsFront(CacheNode<T> node) {
		//updating current node pointers
		node.setPrevious(null);
		node.setNext(head);
		
		//pointing head previous to current node
		if(!Objects.isNull(this.head))
			this.head.setPrevious(node);
		
		//making current node head
		this.head = node;
	}
	
	protected void removeLast() {
		//removing n-1th node's connection to nth node
		if(this.tail.getPrevious() != null)
			this.tail.getPrevious().setNext(null);
		
		//moving tail one node back
		this.tail = this.tail.getPrevious();
		this.size--;
	}
}
