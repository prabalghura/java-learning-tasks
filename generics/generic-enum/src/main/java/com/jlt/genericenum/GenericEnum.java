package com.jlt.genericenum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.jlt.genericenum.exception.EnumNotFoundException;

/**
 * Interface to be implemented to achieve generic enum behaviour.
 * 
 * @author Prabal Ghura
 *
 */
public interface GenericEnum<T> {

	public T getVal();
	
	public static <T> GenericEnum<T> getEnumFromValue(Class<? extends GenericEnum<T>> class1, T t) 
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, 
			EnumNotFoundException {

		for(GenericEnum<T> en: class1.getEnumConstants()){
		    
			Method main = class1.getDeclaredMethod("getVal");
			main.invoke(en);
			if(main.invoke(en).equals(t)) {
				return en;
			}
		}
		throw new EnumNotFoundException("Enum not found");
    }
}
