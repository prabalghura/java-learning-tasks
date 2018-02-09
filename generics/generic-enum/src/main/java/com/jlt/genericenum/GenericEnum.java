package com.jlt.genericenum;

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
			throws EnumNotFoundException {
		for(GenericEnum<T> en: class1.getEnumConstants()){
			if(en.getVal().equals(t)) return en;
		}
		throw new EnumNotFoundException("Enum not found");
    }
}
