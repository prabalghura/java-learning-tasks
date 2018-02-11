package com.jlt.suggester;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Implementation for Auto suggest function using streams
 * 
 * @author Prabal Ghura
 *
 */
public class StreamAutoSuggest extends AutoSuggest{
	
	private static final Logger log = Logger.getLogger(StreamAutoSuggest.class.getName());
	
	public StreamAutoSuggest(File dataSource) {
		super(dataSource);
	}

	@Override
	public List<String> suggest(String word) {
		List<String> suggestions = new ArrayList<>();
		try (Stream<String> lines = Files.lines(dataSource.toPath())) {
			Stream<String> words = lines.map(line -> line.split(" : ")[0]);
			Stream<String> matchingWords = words.filter(word1 -> word1.indexOf(word) != -1);
			matchingWords.forEach(suggestions::add);
			
		} catch (IOException e) {
			log.log(Level.SEVERE, "Unable to get suggestions for " + word + " " + e.getMessage());
		}
		return suggestions;
	}

}
