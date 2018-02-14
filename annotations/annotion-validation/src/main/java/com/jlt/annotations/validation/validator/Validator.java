package com.jlt.annotations.validation.validator;

import java.util.List;
import java.util.Map;

/**
 * Abstract class for all validators
 * 
 * @author Prabal Ghura
 *
 */
public interface Validator {
	
	public List<String> validate(String name, Object content, Map<String, Object> properties);
}
