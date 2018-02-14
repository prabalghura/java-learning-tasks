package com.jlt.annotations.validation.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ValidationException;

import com.jlt.annotations.validation.annotation.MVField;
import com.jlt.annotations.validation.annotation.VField;
import com.jlt.annotations.validation.utils.ReflectionsUtils;
import com.jlt.annotations.validation.utils.ValidatorFactory;
import com.jlt.annotations.validation.validator.Validator;

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
	 * For field validations
	 * 
	 * @throws ValidationException 
	 * 
	 */
	private void validate() throws ValidationException {
		this.validationErrors = new ArrayList<>();
		
		//Single validations
		List<Field> validateFields = ReflectionsUtils.getFieldsAnnotatedWithForDocument(VField.class, 
				this.getClass());
		Validator validator;
		for(Field validateField: validateFields) {
			validator = ValidatorFactory.getValidator(validateField.getAnnotation(VField.class).type());
			validationErrors.addAll(validator.getValidationErrors(validateField.getName(), ReflectionsUtils.getFieldValue(this, validateField), 
					validateField.getAnnotation(VField.class).params()));
		}
		
		//Multi validations
		validateFields = ReflectionsUtils.getFieldsAnnotatedWithForDocument(MVField.class,
				this.getClass());
		for(Field validateField: validateFields) {
			MVField multi = validateField.getAnnotation(MVField.class);
			for(VField validationType: multi.validators()) {
				validator = ValidatorFactory.getValidator(validationType.type());
				List<String> errors = validator.getValidationErrors(validateField.getName(), 
						ReflectionsUtils.getFieldValue(this, validateField), validationType.params());
				if(!errors.isEmpty()) {
					validationErrors.addAll(errors);
					break;
				}
			}
		}
	}
}
