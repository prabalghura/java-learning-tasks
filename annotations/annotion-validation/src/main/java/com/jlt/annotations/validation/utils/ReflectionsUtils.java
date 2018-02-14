package com.jlt.annotations.validation.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.ValidationException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jlt.annotations.validation.annotation.GetterMethod;
import com.jlt.annotations.validation.annotation.SetterMethod;
import com.jlt.annotations.validation.model.Document;
import com.jlt.annotations.validation.validator.ValidationExecutor;
import com.jlt.annotations.validation.validator.Validator;

/**
 * Utils class to provide various functionalities related to reflections
 * 
 * @author Prabal Ghura
 *
 */
public class ReflectionsUtils {
	
	private static final Logger log = Logger.getLogger(ReflectionsUtils.class.getName());
	
	private ReflectionsUtils() {
		super();
	}

	public static Object getFieldValue(Object obj, Field field) throws ValidationException {
		String name = field.getName();
		String getterMethodName;
		if(field.isAnnotationPresent(GetterMethod.class))
			getterMethodName = field.getAnnotation(GetterMethod.class).value();
		else
			getterMethodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
		Method getter;
		try {
			getter = obj.getClass().getDeclaredMethod(getterMethodName);
		} catch (NoSuchMethodException e) {
			throw new ValidationException("No getter defined for field " + name);
		}
		try {
			return getter.invoke(obj);
		} catch (IllegalAccessException e) {
			throw new ValidationException("No public getter defined for field " + name);
		} catch (IllegalArgumentException e) {
			throw new ValidationException("Illegal getter definition for field " + name);
		} catch (InvocationTargetException e) {
			throw new ValidationException("For field " + name + " getter is throwing an error");
		}
	}
	
	public static void setFieldValue(Object obj, Field field, Object value) throws ValidationException {
		String name = field.getName();
		String setterMethodName;
		if(field.isAnnotationPresent(GetterMethod.class))
			setterMethodName = field.getAnnotation(SetterMethod.class).value();
		else
			setterMethodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
		Method setter;
		try {
			setter = obj.getClass().getDeclaredMethod(setterMethodName);
		} catch (NoSuchMethodException e) {
			throw new ValidationException("No setter defined for field " + name);
		}
		try {
			setter.invoke(obj, value);
		} catch (IllegalAccessException e) {
			throw new ValidationException("No public setter defined for field " + name);
		} catch (IllegalArgumentException e) {
			throw new ValidationException("Illegal setter definition for field " + name);
		} catch (InvocationTargetException e) {
			throw new ValidationException("For field " + name + " setter is throwing an error");
		}
	}
	
	public static List<Field> getFieldsAnnotatedWithForDocument(Class<? extends Annotation> annotation, Class<? extends Document> document) {
		Field[] fields = document.getDeclaredFields();
		List<Field> result = new ArrayList<>();
		for(Field field: fields) {
			if(field.isAnnotationPresent(annotation))
				result.add(field);
		}
		return result;
	}
	
	public static Document populateDocument(Class<? extends Document> type, JsonObject jsonObject) throws ValidationException {
		Field[] documentFields = type.getDeclaredFields();
		Document document = null;
		try {
			document = type.newInstance();
		} catch (InstantiationException e) {
			throw new ValidationException(type.getSimpleName() + " instantiation has failed.");
		} catch (IllegalAccessException e) {
			throw new ValidationException(type.getSimpleName() + " does not have a public nullary constructor.");
		}
		for(Field documentField: documentFields) {
			JsonElement val = jsonObject.get(documentField.getName());
			List<ValidationExecutor> validators = getValidationAnnotationsForField(documentField);
			for(ValidationExecutor validator: validators) {
				List<String> errors = validator.validate(documentField.getName(), val.getAsString());
				if(!errors.isEmpty()) {
					document.addErrors(errors);
					break;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Gets all validators with properties defined for a field
	 * 
	 * @param field
	 * @return list of ValidationExecutor
	 */
	public static List<ValidationExecutor> getValidationAnnotationsForField(Field field) {
		Annotation[] annotations = field.getAnnotations();
		List<Class<? extends Annotation>> availableValidators = FieldValidationType.getValidateAnnotations();
		List<ValidationExecutor> list = new ArrayList<>();
		//Iterate field annotations
		for(Annotation annotation: annotations) {
			if(availableValidators.contains(annotation.annotationType())) {
				Validator validator = null;
				try {
					validator = FieldValidationType.getValidator(annotation.annotationType());
				} catch (ValidationException e1) {
					/* consuming this exception bcoz it is never likely to occur bcoz availableValidators
					 * are fetched FieldValidationType and it definitely have a mapping for all annotations
					 * returned
					*/
					log.log(Level.SEVERE, e1.getMessage());
				}
				Field[] annotationFields = annotation.getClass().getDeclaredFields();
				Map<String, Object> properties = new HashMap<>();
				for(Field annotationField: annotationFields) {
					try {
						properties.put(annotationField.getName(), annotationField.get(annotation));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// I can consume this exception by just logging it because annotation always public methods with no arguments.
						log.log(Level.SEVERE, e.getMessage());
					}
				}
				list.add(new ValidationExecutor(validator, properties));
			}
		}
		return list;
	}
}
