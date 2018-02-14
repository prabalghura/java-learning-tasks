package com.jlt.annotations.validation.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Abstract class for all validators
 * 
 * @author Prabal Ghura
 *
 */
public abstract class Validator {
	
	private Set<String> validationErrors;
	
	protected abstract void validate(String name, Object content, Map<String, String> properties);
	
	protected void addError(String error) {
		if(!validationErrors.contains(error))
			validationErrors.add(error);
	}
	
	private void clearErrors() {
		if(Objects.isNull(validationErrors))
			validationErrors = new HashSet<>();
		validationErrors.clear();
	}
	
	/**
	 * @return the validationErrors
	 */
	public List<String> getValidationErrors(String name, Object content, String... properties) {
		clearErrors();
		validate(name, content, getMapFromArray(properties));
		List<String> list = new ArrayList<>();
		for(String error: validationErrors)
			list.add(error);
		return list;
	}
	
	private Map<String, String> getMapFromArray(String... array) {
		Map<String, String> returnMap = new HashMap<>();
		for(String val: array) {
			String[] values = val.split("=");
			if(values.length==2)
			{
				String key = values[0];
				String value = values[1];
				returnMap.put(key, value);
			}
		}
		return returnMap;
	}
}
