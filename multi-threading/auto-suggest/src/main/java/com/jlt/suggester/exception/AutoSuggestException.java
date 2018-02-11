package com.jlt.suggester.exception;

/**
 * Custom Exception class for Auto suggest function
 * 
 * @author Prabal Ghura
 *
 */
public class AutoSuggestException extends Exception {

	private static final long serialVersionUID = -1640490381817311345L;

	/**
	 * @param message
	 */
	public AutoSuggestException(String message) {
		super(message);
	}
}
