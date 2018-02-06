package com.jlt.wikiReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation for reading line separated keywords in a file
 * 
 * @author Prabal Ghura
 *
 */
public class LSFileReader extends FileWikiReader{
	
	private static final Logger log = Logger.getLogger(LSFileReader.class.getName());

	public LSFileReader(File file) {
		super(file);
	}
	
	@Override
	public List<String> getKeywords() {
		List<String> lines = new ArrayList<String>();
		try {
			lines = Files.readAllLines(file.toPath());
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return lines;
	}
	
	
}
