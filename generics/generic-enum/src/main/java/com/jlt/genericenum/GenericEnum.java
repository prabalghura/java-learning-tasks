package com.jlt.genericenum;

import com.jlt.genericenum.exception.EnumNotFoundException;

/**
 * Interface to be implemented to achieve generic enum behaviour.
 * 
 * @author Prabal Ghura
 *
 */
public interface GenericEnum<T extends Comparable<T>> {

	public T getVal();
	
	public static <T extends Comparable<T>> GenericEnum<T> getEnumFromValue(Class<? extends GenericEnum<T>> class1, T t) 
			throws EnumNotFoundException {
		for(GenericEnum<T> en: class1.getEnumConstants()){
			if(en.getVal().compareTo(t) == 0) return en;
		}
		throw new EnumNotFoundException("Enum not found");
    }
}
