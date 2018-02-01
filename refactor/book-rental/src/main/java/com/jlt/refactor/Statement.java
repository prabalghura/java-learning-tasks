package com.jlt.refactor;

/**
 * Interface for a statement as their is no common functionality for various Statement types 
 * but we provide a structure for future child classes to implement.
 * 
 * @author Prabal Ghura
 *
 */
public interface Statement {
	
	public void generateStatement(Customer customer);
}
