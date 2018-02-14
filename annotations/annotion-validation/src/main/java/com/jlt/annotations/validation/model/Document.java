package com.jlt.annotations.validation.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Abstract class for all documents to be validated
 * 
 * @author Prabal Ghura
 *
 */
public abstract class Document {
	private Set<String> validationErrors;

	public Document() {
		super();
		this.validationErrors = new HashSet<>();
	}
	
	public void addErrors(List<String> errors) {
		validationErrors.addAll(errors);
	}

	/**
	 * @return the validationErrors
	 */
	public List<String> getValidationErrors(){
		List<String> errors = new ArrayList<>();
		for(String error: this.validationErrors) {
			errors.add(error);
		}
		return errors;
	}
	
	/**
	 * Checks if document is valid
	 * 
	 * @return true if valid otherwise false
	 */
	public Boolean isValid() {
		return this.validationErrors.isEmpty();
	}
}
