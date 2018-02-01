package com.jlt.multiThreading;

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
		this.file = file;
		this.linesPerFile = linesPerFile;
		this.outputFolder = outputFolder;
	}

	public abstract void split();

	public void setLinesPerFile(int linesPerFile) {
		this.linesPerFile = linesPerFile;
	}
}
