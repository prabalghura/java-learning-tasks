package com.jlt.refactor;

import java.util.Date;

/**
 * Implementation class for Book type non-fiction
 * 
 * @author Prabal Ghura
 *
 */
public class NonFictionBook extends Book {
	
	private static final long serialVersionUID = 203243477542363727L;

	public NonFictionBook(String title, Date releaseDate) {
		super(title, releaseDate);
	}

	@Override
	public double getPrice(int daysKept) {
		double result = 0;
		result += daysKept * 3;
		return result;
	}

	@Override
	public double getPoints(int daysKept) {
		return 1;
	}
}
