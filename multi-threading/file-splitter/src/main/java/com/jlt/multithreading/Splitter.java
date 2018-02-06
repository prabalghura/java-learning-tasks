package com.jlt.multithreading;

import java.io.File;

/**
 * Abstract class for Spitting function
 * 
 * @author Prabal Ghura
 *
 */
public abstract class Splitter {
	protected File file;
	
	protected int linesPerFile;
	
	protected String outputFolder;
	
	public Splitter(File file, int linesPerFile, String outputFolder) {
		super();
		String type = this.getClass().getSimpleName();
		String outPath = outputFolder + "/" + type + "/" + linesPerFile;
		this.file = file;
		this.linesPerFile = linesPerFile;
		this.outputFolder = outPath;
	}

	public abstract void split();
}
