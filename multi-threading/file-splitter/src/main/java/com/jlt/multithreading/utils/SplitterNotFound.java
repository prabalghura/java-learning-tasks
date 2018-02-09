package com.jlt.multithreading.utils;

/**
 * Custom Exception when user is trying to use undefined Splitter
 * 
 * @author Prabal Ghura
 *
 */
public class SplitterNotFound extends Exception{

	private static final long serialVersionUID = 278963708581675873L;
	
	public SplitterNotFound(String message) {
		super(message);
	}
}
