package com.jlt.annotations.validation.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GenderValidator implements Validator{

	private static GenderValidator singleInstance;
	
	private GenderValidator() {
		super();
	}

	public static GenderValidator getInstance() {
		if(Objects.isNull(singleInstance)) {
			singleInstance = new GenderValidator();
		}
		return singleInstance;
	}

	@Override
	public List<String> validate(String name, Object content, Map<String, Object> properties) {
		List<String> list  = new ArrayList<>();
		
		if(!Arrays.asList("M","F").contains(content))
			list.add(name + " should have values as M or F.");
		
		return list;
	}

}
