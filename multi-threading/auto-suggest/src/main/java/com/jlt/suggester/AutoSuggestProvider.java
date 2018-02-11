package com.jlt.suggester;

import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jlt.suggester.exception.AutoSuggestException;
import com.jlt.suggestor.utils.SuggestorConstants;

/**
 * Executor class for providing auto suggest function.
 * 
 * @author Prabal Ghura
 *
 */
public class AutoSuggestProvider {

	private static final Logger log = Logger.getLogger(AutoSuggestProvider.class.getName());
	
	public static void execute() throws AutoSuggestException {
		DecimalFormat df = new DecimalFormat("#000");
		String[] inputWords = {"they", "can"};
		long time = System.currentTimeMillis();
		AutoSuggestType[] implementationTypes = AutoSuggestType.values();
		for(AutoSuggestType implementationType: implementationTypes)
		{
			log.log(Level.INFO, () -> "By " + implementationType);
			AutoSuggest suggestor = AutoSuggestFactory.getSuggester(implementationType, Paths.get(SuggestorConstants.INPUT_FILE).toFile());
			for(String word: inputWords)
			{
				List<String> suggestions = suggestor.suggest(word);
				if(suggestions.isEmpty())
					log.log(Level.INFO, () -> "For " + word + " there are no suggestions available.");
				else
					log.log(Level.INFO, () -> "For " + word + " we suggest " + suggestions.toString());
			}
		}
		long timetaken = System.currentTimeMillis() - time;
		log.log(Level.INFO, () -> "Time Taken "+ (timetaken/1000) + "." + df.format(timetaken%1000) + " seconds");
	}

	private AutoSuggestProvider() {
		super();
	}
}
