package com.jlt.genericenum.exception;

/**
 * Custom exception for Generic Enum type
 * 
 * @author Prabal Ghura
 *
 */
public class EnumNotFoundException extends Exception{
	
	private static final long serialVersionUID = -5981860986330558863L;

	public EnumNotFoundException(String message) {
		super(message);
	}
}
