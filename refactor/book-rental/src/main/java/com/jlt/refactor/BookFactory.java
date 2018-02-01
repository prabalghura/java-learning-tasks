package com.jlt.refactor;

import java.util.Date;

/**
 * Factory class for creating Books of different types
 * 
 * @author Prabal Ghura
 *
 */
public class BookFactory {
	
	public static Book getBook(final String title, final int bookCategory, final Date releaseDate) {
		switch(bookCategory) {
		case(Book.CHILDRENS):
			return new ChildrenBook(title, releaseDate);
		case(Book.FICTION):
			return new FictionBook(title, releaseDate);
		case(Book.NON_FICTION):
			return new NonFictionBook(title, releaseDate);
		}
		return null;
	}
}
