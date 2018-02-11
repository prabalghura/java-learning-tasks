package com.jlt.suggester;

import java.io.File;
import java.util.List;

/**
 * Abstract class for Auto-suggest function
 * 
 * @author Prabal Ghura
 *
 */
public abstract class AutoSuggest {
	
	protected File dataSource;

	/**
	 * 
	 * @param dataSource
	 */
	public AutoSuggest(File dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	/**
	 * 
	 * @param word
	 * @return A list of suggestions for passed word
	 */
	public abstract List<String> suggest(String word);
}
