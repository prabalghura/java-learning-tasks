package com.jlt.annotations.validation.driver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

import javax.xml.bind.ValidationException;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.jlt.annotations.validation.model.Aadhar;
import com.jlt.annotations.validation.utils.ReflectionsUtils;

/**
 * Driver class for demonstrating Validation functionality
 * 
 * @author Prabal Ghura
 *
 */
public class AnnotationDriver {
	
	private static final Logger log = Logger.getLogger(AnnotationDriver.class.getName());
	
	public static void main(String[] args) {
		
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = new JsonObject();
		try {
			jsonElement = parser.parse(new FileReader(AnnotationDriver.class.getClassLoader().getResource("aadhar.json").getFile()));
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		
		try {
			ReflectionsUtils.populateDocument(Aadhar.class, jsonObject);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}
}
