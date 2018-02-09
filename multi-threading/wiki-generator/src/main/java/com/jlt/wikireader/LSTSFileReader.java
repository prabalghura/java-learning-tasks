package com.jlt.wikireader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for reading line separated keywords in a file 
 * which contains further formatting with tabs & spaces.
 * 
 * @author Prabal Ghura
 *
 */
public class LSTSFileReader extends LSFileReader{

	public LSTSFileReader(File file) {
		super(file);
	}

	@Override
	public List<String> getKeywords() {
		List<String> linesRaw = super.getKeywords();
		List<String> result = new ArrayList<>();
		for(String s : linesRaw) {
			String[] tokens = s.split("\\t");
			String keyword = tokens[1].trim();
			result.add(keyword);
		}
		return result;
	}
}
