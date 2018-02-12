package com.jlt.annotations.validation.utils;

import java.util.Objects;

import javax.xml.bind.ValidationException;

import com.jlt.annotations.validation.annotation.ValidateField;
import com.jlt.annotations.validation.model.Document;
import com.jlt.annotations.validation.validator.NullValidator;
import com.jlt.annotations.validation.validator.Validator;

/**
 * Factory class to create Custom Validators of different types
 * 
 * @author Prabal Ghura
 *
 */
public class ValidatorFactory {
	public static Validator getValidator(String name, Object content, Class<? extends Document> clazz1, ValidateField field) throws ValidationException {
		Class<? extends Validator> clazz = field.clazz();
		Validator validator = null;
		Integer propertyCount = null;
		//Exception can occur if less number of properties are provided or properties passed cannot be casted or any exception 
		//from within respective sub-validators constructors
		try {
			if(clazz.equals(NullValidator.class)) {
				validator = new NullValidator();
				propertyCount = 0;
			}
		} catch(ClassCastException | NullPointerException e) {
			throw new ValidationException("Incorrect Validator configuration");
		}
		if(Objects.isNull(validator))
			throw new ValidationException("Validator not found");
		if(field.values().length > propertyCount)
			throw new ValidationException("Incorrect Validator configuration");
		validator.setName(name).setContent(content).setClazz(clazz1);
		return validator;
	}

	private ValidatorFactory() {
		super();
	}
}
