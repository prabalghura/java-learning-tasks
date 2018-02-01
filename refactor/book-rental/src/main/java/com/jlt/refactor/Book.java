/**
 * 
 */
package com.jlt.refactor;

import java.io.Serializable;
import java.util.Date;

/**
 * Abstract class for Book data
 * 
 * @author Prabal Ghura
 *
 */
public abstract class Book implements Serializable {

	private static final long serialVersionUID = -7348792584072115788L;

	public static final int FICTION = 1;
	public static final int NON_FICTION = 2;
	public static final int CHILDRENS = 3;
	
	private long id;
	private String title;
	private Date releaseDate;
	
	//common constructor for all book types
	public Book(final String title, final Date releaseDate) {
		super();
		this.title = title;
		this.releaseDate = releaseDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	//Subclasses will provide different implementations after taking a parameter.
	public abstract double getPrice(int daysKept);
	
	public abstract double getPoints(int daysKept);
}
