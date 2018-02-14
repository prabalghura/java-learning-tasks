package com.jlt.annotations.validation.validator;

import java.util.List;
import java.util.Map;

public class ValidationExecutor {
	private Validator validator;
	
	private Map<String, Object> properties;

	/**
	 * @param validator
	 * @param properties
	 */
	public ValidationExecutor(Validator validator, Map<String, Object> properties) {
		super();
		this.validator = validator;
		this.properties = properties;
	}
	
	public List<String> validate(String name, Object content) {
		return this.validator.validate(name, content, this.properties);
	}
}
