package com.jlt.annotations.validation.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Collection class to hold various documents especially to perform consistency checks
 * 
 * @author Prabal Ghura
 *
 */
public class DocumentSet {
	private List<? extends Document> documents;

	/**
	 * @param documents
	 */
	public DocumentSet(List<? extends Document> documents) {
		super();
		this.documents = documents;
	}
	
	/**
	 * @param documents
	 */
	public DocumentSet(Document... documents) {
		super();
		this.documents = Arrays.asList(documents);
	}
	
	/**
	 * @return the validationErrors
	 */
	public List<String> getValidationErrors() {
		validate();
		List<String> validationErrors = new ArrayList<>();
		for(Document document: this.documents) {
			validationErrors.addAll(document.getValidationErrors());
		}
		return validationErrors;
	}
	
	/**
	 * Checks if entire documentSet is valid
	 * 
	 * @return
	 */
	public Boolean isValid() {
		validate();
		return getValidationErrors().isEmpty();
	}
	
	/**
	 * For field validations & consistency validations
	 * 
	 */
	public void validate() {
		//TODO: something
	}
}
