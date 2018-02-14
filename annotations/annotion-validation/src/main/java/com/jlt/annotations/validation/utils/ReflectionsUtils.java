package com.jlt.annotations.validation.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.ValidationException;

import com.jlt.annotations.validation.annotation.GetterMethod;
import com.jlt.annotations.validation.model.Document;

/**
 * Utils class to provide various functionalities related to reflections
 * 
 * @author Prabal Ghura
 *
 */
public class ReflectionsUtils {
	
	private ReflectionsUtils() {
		super();
	}

	public static Object getFieldValue(Document obj, Field field) throws ValidationException {
		String name = field.getName();
		String getterMethodName;
		if(field.isAnnotationPresent(GetterMethod.class))
			getterMethodName = field.getAnnotation(GetterMethod.class).methodName();
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
	
	public static List<Field> getFieldsAnnotatedWithForDocument(Class<? extends Annotation> annotation, Class<? extends Document> document) {
		Field[] fields = document.getDeclaredFields();
		List<Field> result = new ArrayList<>();
		for(Field field: fields) {
			if(field.isAnnotationPresent(annotation))
				result.add(field);
		}
		return result;
	}
}
