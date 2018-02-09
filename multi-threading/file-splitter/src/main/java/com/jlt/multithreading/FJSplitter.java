package com.jlt.multithreading;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation class for Splitting up using Fork & Join Technique
 * 
 * @author Prabal Ghura
 *
 */
public class FJSplitter extends Splitter{
	
	private static final Logger log = Logger.getLogger(FJSplitter.class.getName());
	
	public FJSplitter(File file, int linesPerFile, String outputFolder) {
		super(file, linesPerFile, outputFolder);
		Forker.outputFolder = this.outputFolder;
		Forker.linesPerFile = linesPerFile;
	}

	@Override
	public void split() {
		long time = System.currentTimeMillis();
		List<String> lines = new ArrayList<>();
		
		try {
			lines = Files.readAllLines(file.toPath());
			
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		ForkJoinPool commonPool = ForkJoinPool.commonPool();
		commonPool.invoke(new Forker(lines, 0));
		
		long timetaken = System.currentTimeMillis() - time;
		log.log(Level.INFO, () -> "For " + linesPerFile + " Time Taken "+ (timetaken/1000) + "." + (timetaken%1000) + " seconds");
	}
	
	public static class Forker extends RecursiveAction {
		
		private static final long serialVersionUID = 5843610262309778047L;

		public static String outputFolder = null;
		public static int linesPerFile;
		 
	    private List<String> lines;
	    
	    private long startingIndex;

		public Forker(List<String> lines, long startingIndex) {
			super();
			this.lines = lines;
			this.startingIndex = startingIndex;
		}

		@Override
	    protected void compute() {
	        if (lines.size() > linesPerFile) {
	            ForkJoinTask.invokeAll(createSubtasks());
	        } else {
	        	try {
	        		Path path = Paths.get(outputFolder, "data_"+ (startingIndex/linesPerFile) +".csv");
					Files.write(path, lines, Charset.defaultCharset());
				} catch (IOException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
	        }
	    }
	 
	    private List<Forker> createSubtasks() {
	        List<Forker> subtasks = new ArrayList<>();
	        
	        int size = lines.size();
	        int limit = ((size/linesPerFile)/2)*linesPerFile;
	        
	        if(limit==0)
	        	limit=linesPerFile;
	        
	        List<String> partOne = lines.subList(0, limit);
	        List<String> partTwo = lines.subList(limit, size);
	 
	        subtasks.add(new Forker(partOne, this.startingIndex));
	        subtasks.add(new Forker(partTwo, this.startingIndex+limit));
	 
	        return subtasks;
	    }
	}
}
