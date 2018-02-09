package com.jlt.wikier.utils;

import java.io.File;
import com.jlt.wikireader.FileReaderType;

/**
 * Utils class for providing filePath based on ReaderType
 * 
 * @author Prabal Ghura
 *
 */
public class WikiConstants {
	
	private static final String BASEPATH = "basePath";
	
	public static File getFile(FileReaderType type) {
		switch (type) {
		case LINESEPARATED:
			return new File(PropertiesUtils.getProperty(BASEPATH), PropertiesUtils.getProperty("lineSeparatedFile"));
		case COMMASEPARATED:
			return new File(PropertiesUtils.getProperty(BASEPATH), PropertiesUtils.getProperty("commaSeparatedFile"));
		case LINETABSEPARATED:
			return new File(PropertiesUtils.getProperty(BASEPATH), PropertiesUtils.getProperty("lineTabSeparatedFile"));
		default:
			return null;
		}
	}

	private WikiConstants() {
		super();
	}
}
