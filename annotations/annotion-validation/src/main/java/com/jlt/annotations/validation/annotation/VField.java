package com.jlt.annotations.validation.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.jlt.annotations.validation.validator.Validator;

/**
 * Custom annotation class for field validations
 * 
 * @author Prabal Ghura
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface VField {
	
	Class<? extends Validator> type();
	
	String[] params() default {};
}