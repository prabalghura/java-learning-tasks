package com.jlt.refactor;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation for a statement type.
 * 
 * @author Prabal Ghura
 *
 */
public class PrintStatement implements Statement{

	private static final Logger log = Logger.getLogger(PrintStatement.class.getName());
	
	@Override
	public void generateStatement(Customer customer) {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentalsItr = customer.getRentals().listIterator();
		log.log(Level.INFO, "Rental Record for " + customer.getName());
		
		StringBuilder stb;
		String print;
		
		while (rentalsItr.hasNext()) {
			Rental rental = rentalsItr.next();
			// determine amounts for each book.
			double thisAmount = rental.fetchPrice();

			// show figures for this rental
			stb = new StringBuilder().append("\t").append(rental.getBook().getTitle()).append("\t").append(thisAmount);
			print = stb.toString();
			log.log(Level.INFO, print);
			
			frequentRenterPoints += rental.fetchPoints();
			totalAmount += thisAmount;
		}
		
		stb = new StringBuilder().append("Amount owed is ").append(totalAmount);
		print = stb.toString();
		log.log(Level.INFO, print);
		stb = new StringBuilder().append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
		print = stb.toString();
		log.log(Level.INFO, print);
	}
}
