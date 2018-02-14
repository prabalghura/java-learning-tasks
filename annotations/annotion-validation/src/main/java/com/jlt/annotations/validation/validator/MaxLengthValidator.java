package com.jlt.annotations.validation.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MaxLengthValidator implements Validator{
	private static MaxLengthValidator singleInstance;
	
	private MaxLengthValidator() {
		super();
	}

	public static MaxLengthValidator getInstance() {
		if(Objects.isNull(singleInstance)) {
			singleInstance = new MaxLengthValidator();
		}
		return singleInstance;
	}
	
	@Override
	public List<String> validate(String name, Object content, Map<String, Object> properties) {
		Integer length = Integer.parseInt(properties.get("value").toString());
		List<String> list  = new ArrayList<>();
		
		String value = (String) content;
		if(value.length() > length)
			list.add(name + " should be less than or equal to " + length + " characters");
		return list;
	}
}
