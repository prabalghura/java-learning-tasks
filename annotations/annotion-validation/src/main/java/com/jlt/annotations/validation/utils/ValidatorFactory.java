package com.jlt.annotations.validation.utils;

import javax.xml.bind.ValidationException;

import com.jlt.annotations.validation.validator.VMaxLength;
import com.jlt.annotations.validation.validator.VNull;
import com.jlt.annotations.validation.validator.Validator;

/**
 * Factory class to create Custom Validators of different types
 * 
 * @author Prabal Ghura
 *
 */
public class ValidatorFactory {
	public static Validator getValidator(Class<? extends Validator> clazz) throws ValidationException {
		switch(clazz.getSimpleName()) {
		case "VNull":
			return VNull.getInstance();
		case "VMaxLength":
			return VMaxLength.getInstance();
		default:
			throw new ValidationException("Validator not found");
		}
	}

	private ValidatorFactory() {
		super();
	}
}
