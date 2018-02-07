package com.jlt.counter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class for Word count function
 * 
 * @author Prabal Ghura
 *
 */
public class FileWordCounter extends RecursiveTask<Map<String, Integer>>{
	
	private static final long serialVersionUID = -7370870375063941571L;

	private static final Logger log = Logger.getLogger(FileWordCounter.class.getName());
	
	private List<File> files;
	
	private Map<String, Integer> countMap;
	
	public FileWordCounter(List<File> files) {
		super();
		this.files = files;
	}

	@Override
	protected Map<String, Integer> compute() {
		if(files.size()>1) {
			List<FileWordCounter> subtasks = createSubtasks();
			for(FileWordCounter subtask : subtasks) {
				subtask.fork();
			}
			Map<String, Integer> countMap = new HashMap<String, Integer>();
			for(FileWordCounter subtask : subtasks) {
                result += subtask.join();
            }
			ForkJoinTask.invokeAll();
		}
		else
		{
			this.countMap = getWordCount();
			return this.countMap;
		}
	}
	
	private List<FileWordCounter> createSubtasks() {
        List<FileWordCounter> subtasks = new ArrayList<FileWordCounter>();
        
        int size = files.size();
        
        List<File> partOne = files.subList(0, size/2);
        List<File> partTwo = files.subList(size/2, size);
 
        subtasks.add(new FileWordCounter(partOne));
        subtasks.add(new FileWordCounter(partTwo));
 
        return subtasks;
    }
	
	private Map<String, Integer> getWordCount() {
		File file = files.get(0);
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		try {
			List<String> lines = Files.readAllLines(file.toPath());
			for(String line: lines) {
				String[] words = line.split(" ");
				for(String word1: words) {
					String word = word1.toLowerCase();
					if(countMap.containsKey(word1))
						countMap.put(word, countMap.get(word)+1);
					else
						countMap.put(word, 1);
				}
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return countMap;
	}
}