package com.jlt.suggester;

import java.io.File;

import com.jlt.suggester.exception.AutoSuggestException;

/**
 * Factory class for creating Auto Suggest objects of different type implementations
 * 
 * @author Prabal Ghura
 *
 */
public class AutoSuggestFactory {
	public static AutoSuggest getSuggester(AutoSuggestType type, File dataSource) throws AutoSuggestException {
		if (type == AutoSuggestType.STREAM)
			return new StreamAutoSuggest(dataSource);
		else
			throw new AutoSuggestException("Suggestor not found");
	}

	private AutoSuggestFactory() {
		super();
	}
}
