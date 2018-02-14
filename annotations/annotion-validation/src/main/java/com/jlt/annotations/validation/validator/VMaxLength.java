package com.jlt.annotations.validation.validator;

import java.util.Map;
import java.util.Objects;

/**
 * An implementation of Validator for performing maxLength check.
 * 
 * @author Prabal ghura
 *
 */
public class VMaxLength extends Validator{

	private static VMaxLength singleInstance;
	
	private VMaxLength() {
		super();
	}

	public static VMaxLength getInstance() {
		if(Objects.isNull(singleInstance)) {
			singleInstance = new VMaxLength();
		}
		return singleInstance;
	}

	@Override
	protected void validate(String name, Object content, Map<String, String> properties) {
		Integer length = Integer.parseInt(properties.getOrDefault("length", "50"));
		
		if(!content.getClass().equals(String.class)) {
			addError(name + " should be a String");
			return;
		}
		
		String value = String.valueOf(content);
		if(value.length()>length)
			addError(name + " should be less than or equal to " + length + " characters");
	}
}
