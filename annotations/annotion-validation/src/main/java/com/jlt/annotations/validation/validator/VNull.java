package com.jlt.annotations.validation.validator;

import java.util.Map;
import java.util.Objects;

/**
 * An implementation of Validator for performing null check.
 * 
 * @author Prabal ghura
 *
 */
public class VNull extends Validator{

	private static VNull singleInstance;
	
	private VNull() {
		super();
	}

	public static VNull getInstance() {
		if(Objects.isNull(singleInstance)) {
			singleInstance = new VNull();
		}
		return singleInstance;
	}
	
	@Override
	protected void validate(String name, Object content, Map<String, String> properties) {
		if(Objects.isNull(content))
			addError(name + " should not be null.");
	}
}
