package com.jlt.annotations.validation.exception;

/**
 * Custom Exception class for annotation based validation
 * 
 * @author Prabal Ghura
 *
 */
public class CustomValidationException extends Exception{

	private static final long serialVersionUID = -6769278426355517776L;

	/**
	 * @param message
	 */
	public CustomValidationException(String message) {
		super(message);
	}

}
