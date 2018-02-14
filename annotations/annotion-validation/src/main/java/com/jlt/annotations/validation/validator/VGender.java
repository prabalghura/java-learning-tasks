package com.jlt.annotations.validation.validator;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class VGender extends Validator{

	private static VGender singleInstance;
	
	private VGender() {
		super();
	}

	public static VGender getInstance() {
		if(Objects.isNull(singleInstance)) {
			singleInstance = new VGender();
		}
		return singleInstance;
	}
	
	@Override
	protected void validate(String name, Object content, Map<String, String> properties) {
		if(!content.getClass().equals(String.class)) {
			addError(name + " should be a String");
			return;
		}
		
		if(Arrays.asList("M","F").contains(content))
			addError(name + " should have values as M or F.");
	}

}
