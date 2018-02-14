package com.jlt.annotations.validation.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * An implementation of Validator for performing maxLength check.
 * 
 * @author Prabal ghura
 *
 */
public class LengthValidator implements Validator{

	private static LengthValidator singleInstance;
	
	private LengthValidator() {
		super();
	}

	public static LengthValidator getInstance() {
		if(Objects.isNull(singleInstance)) {
			singleInstance = new LengthValidator();
		}
		return singleInstance;
	}

	@Override
	public List<String> validate(String name, Object content, Map<String, Object> properties) {
		Integer length = Integer.parseInt(properties.get("value").toString());
		List<String> list  = new ArrayList<>();
		
		String value = (String) content;
		if(value.length() != length)
			list.add(name + " should be equal to " + length + " characters");
		return list;
	}
}
