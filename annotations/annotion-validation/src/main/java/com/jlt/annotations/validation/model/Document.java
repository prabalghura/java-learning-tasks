package com.jlt.annotations.validation.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for all documents to be validated
 * 
 * @author Prabal Ghura
 *
 */
public abstract class Document {
	private List<String> validationErrors;

	public Document() {
		super();
		validationErrors = new ArrayList<>();
	}

	/**
	 * @return the validationErrors
	 */
	public List<String> getValidationErrors() {
		validate();
		return validationErrors;
	}
	
	/**
	 * Checks if document is valid
	 * 
	 * @return
	 */
	public Boolean isValid() {
		validate();
		return this.validationErrors.isEmpty();
	}
	
	/**
	 * For field validations
	 * 
	 */
	public void validate() {
		//TODO: something
	}
}
