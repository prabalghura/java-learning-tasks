package com.jlt.annotations.validation.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.ValidationException;

import com.jlt.annotations.validation.annotation.Consistent;
import com.jlt.annotations.validation.utils.ReflectionsUtils;

/**
 * Collection class to hold various documents especially to perform consistency checks
 * 
 * @author Prabal Ghura
 *
 */
public class DocumentSet {
	private List<? extends Document> documents;
	
	private List<String> validationErrors;

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
	 * @throws ValidationException 
	 */
	public List<String> getValidationErrors() throws ValidationException {
		validate();
		return validationErrors;
	}
	
	/**
	 * Checks if document is valid
	 * 
	 * @return
	 * @throws ValidationException 
	 */
	public Boolean isValid() throws ValidationException {
		validate();
		return this.validationErrors.isEmpty();
	}
	
	/**
	 * For field validations & consistency validations
	 * @throws ValidationException 
	 * 
	 */
	private void validate() throws ValidationException {
		this.validationErrors = new ArrayList<>();
		
		// Perform individual field validations
		for(Document document: this.documents) {
			List<String> errors = document.getValidationErrors();
			for(String error: errors) {
				this.validationErrors.add("For " + document.getClass().getSimpleName() + " " + error);
			}
		}
		
		if(this.validationErrors.isEmpty()) {
			consistencyCheck();
		}
	}
	
	private void consistencyCheck() throws ValidationException {
		Map<String, List<Object>> consistentMap = new HashMap<>();
		for(Document document: this.documents) {
			List<Field> validateFields = ReflectionsUtils.getFieldsAnnotatedWithForDocument(Consistent.class, document.getClass());
			for(Field field: validateFields) {
				Consistent consistent = field.getAnnotation(Consistent.class);
				Object value = ReflectionsUtils.getFieldValue(document, field);
				List<Object> list;
				if(consistentMap.containsKey(consistent.value())) {
					list = consistentMap.get(consistent.value());
				} else {
					list = new ArrayList<>();
				}
				list.add(value);
				consistentMap.put(consistent.value(), list);
			}
		}
		for (Map.Entry<String, List<Object>> entry : consistentMap.entrySet())
		{
			List<Object> list = entry.getValue();
			Object value = list.get(0);
			for(Object inValue: list) {
				if(!value.equals(inValue)) {
					this.validationErrors.add("Field " + entry.getKey() + " is not consistent accross all documents.");
					break;
				}
			}
		}
	}
}
