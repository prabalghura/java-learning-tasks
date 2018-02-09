package com.jlt.genericenum;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.genericenum.exception.EnumNotFoundException;

/**
 * Driver class for demonstration of generic enum
 * 
 * @author Prabal Ghura
 *
 */
public class EnumDriver {
	private static final Logger log = Logger.getLogger(EnumDriver.class.getName());
	
	public static void main(String[] args) {
		try {
			String day = GenericEnum.getEnumFromValue(WeekDay.class, "Monday").toString();
			log.log(Level.INFO, day);
			day = GenericEnum.getEnumFromValue(WeekDay.class, "dummy").toString();
			log.log(Level.INFO, day);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | EnumNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
}
