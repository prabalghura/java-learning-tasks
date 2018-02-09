package com.jlt.wikier.utils;

/**
 * Custom Exception when user is trying to use undefined FileWikiReader
 * 
 * @author Prabal Ghura
 *
 */
public class FileReaderException extends Exception{

	private static final long serialVersionUID = 400893707013500340L;

	public FileReaderException(String message) {
		super(message);
	}

}
