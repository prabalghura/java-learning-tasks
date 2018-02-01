package com.jlt.refactor;

import java.util.Iterator;

/**
 * Implementation for a statement type.
 * 
 * @author Prabal Ghura
 *
 */
public class PrintStatement implements Statement{

	@Override
	public void generateStatement(Customer customer) {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentalsItr = customer.getRentals().listIterator();
		System.out.println("Rental Record for " + customer.getName());;
		
		while (rentalsItr.hasNext()) {
			Rental rental = (Rental) rentalsItr.next();
			// determine amounts for each book.
			double thisAmount = rental.fetchPrice();

			// show figures for this rental
			System.out.println("\t" + rental.getBook().getTitle() + "\t" + thisAmount);
			
			frequentRenterPoints += rental.fetchPoints();
			totalAmount += thisAmount;
		}
		
		System.out.println("Amount owed is " + totalAmount);
		System.out.println("You earned " + frequentRenterPoints + " frequent renter points");
	}
}
