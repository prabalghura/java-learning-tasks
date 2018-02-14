package com.jlt.annotations.validation.driver;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.ValidationException;

import com.jlt.annotations.validation.model.Aadhar;

/**
 * Driver class for demonstrating Validation functionality
 * 
 * @author Prabal Ghura
 *
 */
public class AnnotationDriver {
	
	private static final Logger log = Logger.getLogger(AnnotationDriver.class.getName());
	
	public static void main(String[] args) {
		Aadhar aadhar = new Aadhar(null, null, null, null, null);
		
		try {
			List<String> errors = aadhar.getValidationErrors();
			for(String error: errors) {
				log.log(Level.INFO, error);
			}
		} catch (ValidationException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
}
