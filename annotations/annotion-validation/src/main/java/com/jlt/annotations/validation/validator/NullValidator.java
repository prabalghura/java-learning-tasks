package com.jlt.annotations.validation.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * An implementation of Validator for performing null check.
 * 
 * @author Prabal ghura
 *
 */
public class NullValidator implements Validator{

	private static NullValidator singleInstance;
	
	private NullValidator() {
		super();
	}

	public static NullValidator getInstance() {
		if(Objects.isNull(singleInstance)) {
			singleInstance = new NullValidator();
		}
		return singleInstance;
	}

	@Override
	public List<String> validate(String name, Object content, Map<String, Object> properties) {
		List<String> errors = new ArrayList<>();
		if(Objects.isNull(content))
			errors.add(name + " should not be null.");
		return errors;
	}
}
