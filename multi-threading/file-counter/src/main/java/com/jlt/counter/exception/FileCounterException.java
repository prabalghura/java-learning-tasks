package com.jlt.counter.exception;

/**
 * Custom Exception class for Word Count Opeartions
 * 
 * @author Prabal Ghura
 *
 */
public class FileCounterException extends Exception{

	private static final long serialVersionUID = -8769268805507469588L;

	public FileCounterException(String message) {
		super(message);
	}
}
