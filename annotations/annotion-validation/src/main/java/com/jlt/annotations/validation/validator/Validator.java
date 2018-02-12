package com.jlt.annotations.validation.validator;

import java.util.List;

import com.jlt.annotations.validation.model.Document;

/**
 * Abstract class for all validators
 * 
 * @author Prabal Ghura
 *
 */
public abstract class Validator {

	private String name;
	
	protected Object content;
	
	private Class<? extends Document> clazz;
	
	private List<String> validationErrors;
	
	protected abstract void validate();
	
	protected void addError(String error) {
		validationErrors.add("For "+ clazz.getSimpleName() + " field " + name + " is having error: " + error);
	}
	
	/**
	 * @return the validationErrors
	 */
	public List<String> getValidationErrors() {
		validate();
		return validationErrors;
	}

	/**
	 * @param name the name to set
	 */
	public Validator setName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * @param content the content to set
	 */
	public Validator setContent(Object content) {
		this.content = content;
		return this;
	}
	
	/**
	 * @param clazz the class to set
	 */
	public Validator setClazz(Class<? extends Document> clazz) {
		this.clazz = clazz;
		return this;
	}
}
