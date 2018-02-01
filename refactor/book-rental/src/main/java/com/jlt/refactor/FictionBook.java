package com.jlt.refactor;

import java.util.Date;

/**
 * Implementation class for Book type fiction
 * 
 * @author Prabal Ghura
 *
 */
public class FictionBook extends Book{

	private static final long serialVersionUID = -5777899190809722911L;

	public FictionBook(String title, Date releaseDate) {
		super(title, releaseDate);
	}

	@Override
	public double getPrice(int daysKept) {
		double result = 2;
		if(daysKept > 2)
			result += (daysKept - 2) * 1.5;
		return result;
	}

	@Override
	public double getPoints(int daysKept) {
		double result = 1;
		if(daysKept > 1)
			result += 1;
		return result;
	}
}
