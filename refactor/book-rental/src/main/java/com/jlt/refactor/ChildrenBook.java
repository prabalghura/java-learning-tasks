package com.jlt.refactor;

import java.util.Date;

/**
 * Implementation class for Book type children
 * 
 * @author Prabal Ghura
 *
 */
public class ChildrenBook extends Book{
	
	private static final long serialVersionUID = 8477543368425614874L;

	public ChildrenBook(String title, Date releaseDate) {
		super(title, releaseDate);
	}

	@Override
	public double getPrice(int daysKept) {
		double result = 1.5;
		if(daysKept>3)
			result += (daysKept - 3) * 2;
		return result;
	}

	@Override
	public double getPoints(int daysKept) {
		return 1;
	}
}
