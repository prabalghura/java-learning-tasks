package com.jlt.annotations.validation.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.ValidationException;

import com.jlt.annotations.validation.validator.*;
import com.jlt.annotations.validation.annotation.*;

public enum FieldValidationType {
	NULL(NotNull.class, NullValidator.class),
	MAXLENGTH(MaxLength.class, MaxLengthValidator.class),
	MINLENGTH(MinLength.class, MinLengthValidator.class),
	LENGTH(Length.class, LengthValidator.class),
	GENDER(Gender.class, GenderValidator.class);
	
	private Class<? extends Annotation> annotation;
	private Class<? extends Validator> validator;
	
	private FieldValidationType(Class<? extends Annotation> annotation, Class<? extends Validator> validator) {
		this.annotation = annotation;
		this.validator = validator;
	}
	
	/**
	 * Gets validator Mapped to a validation Annotation
	 * 
	 * @param annotation Class
	 * @return validator
	 * @throws ValidationException if unable to get validator instance
	 */
	public static Validator getValidator(Class<? extends Annotation> annotation) throws ValidationException{
		FieldValidationType[] types = FieldValidationType.values();
		Class<? extends Validator> validator = null;
		for(FieldValidationType type: types) {
			if(type.annotation.equals(annotation))
				validator = type.validator;
		}
		if(Objects.isNull(validator))
			throw new ValidationException("Validator not Found");
		Method method;
		try {
			method = validator.getMethod("getInstance");
			return (Validator) method.invoke(null);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new ValidationException(validator.getSimpleName() + " doesn't have getInstance Method");
		} catch (IllegalAccessException e) {
			throw new ValidationException(validator.getSimpleName() + " doesn't have public getInstance Method");
		} catch (IllegalArgumentException e) {
			throw new ValidationException(validator.getSimpleName() + " doesn't have valid getInstance Method");
		} catch (InvocationTargetException e) {
			throw new ValidationException(validator.getSimpleName() + " getInstance Method has thrown an exception");
		}
	}
	
	public static List<Class<? extends Annotation>> getValidateAnnotations() {
		List<Class<? extends Annotation>> validateAnnotations = new ArrayList<>();
		FieldValidationType[] types = FieldValidationType.values();
		for(FieldValidationType type: types) {
			validateAnnotations.add(type.annotation);
		}
		return validateAnnotations;
	}
}
