package com.jlt.wikier.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesUtils {
	
	private static final Logger log = Logger.getLogger(PropertiesUtils.class.getName());
	
	private static Properties prop = new Properties();
	
	public static String getProperty(String key) {
		if(prop.isEmpty()) loadProperties();
		return prop.getProperty(key);
	}
	
	private static void loadProperties() {
		final String filename = "application.properties";
		InputStream input = PropertiesUtils.class.getClassLoader().getResourceAsStream(filename);
		try {
			prop.load(input);
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	private PropertiesUtils() {
		super();
	}
}
