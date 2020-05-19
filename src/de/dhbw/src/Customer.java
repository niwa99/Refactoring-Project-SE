package de.dhbw.src;

import java.lang.*;
import java.util.*;

class Customer {
	
	private static final String C_TAB = "\t";
	private static final String C_LINEBREAK = "\n";
	
    private String name;
    private ArrayList<Rental> rentals = new ArrayList<>();
    
    public Customer(String newname) {
        name = newname;
    }
    
    public void addRental(Rental arg) {
        rentals.add(arg);
    }
    
    public String getName() {
        return name;
    }
    
    public String getFullInformation() {
    	
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + this.getName() + C_LINEBREAK;
        result += C_TAB + "Title" + C_TAB + C_TAB + "Days" + C_TAB + "Amount" + C_LINEBREAK;
        
        for (Rental rental : rentals) {
        	double rentalAmount = 0;
        	rentalAmount = amountFor(rental);
        	frequentRenterPoints ++;
        	
        	if (isTwoDayNewReleaseBonus(rental)) {
        		frequentRenterPoints ++;
        	}
        	
        	//show figures for this rental
        	result += C_TAB + rental.getMovie().getTitle()+ C_TAB + C_TAB + rental.getDaysRented() + C_TAB + rentalAmount + C_LINEBREAK;
        	totalAmount += rentalAmount;
        };
        //add footer lines
        result += "Amount owed is " + totalAmount + C_LINEBREAK;
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    private double amountFor(Rental rental) {
        
        switch (rental.getMovie().getPriceCode()) {
            case REGULAR:
                return getIncreaseRegular(rental);
            case NEW_RELEASE:
                return getIncreaseNewRelease(rental);
            case CHILDREN:
                return getIncreaseChildren(rental);
        }
        return -1;
    }
    
    private double getIncreaseRegular(Rental rental) {
    	
    	double increase = 2;
    	if (rental.getDaysRented() > 2) {
    		increase += (rental.getDaysRented() - 2) * 1.5;
    	}
    	return increase;
    }
    
    private double getIncreaseNewRelease(Rental rental) {
    	
    	return rental.getDaysRented() * 3;
    }
    
    private double getIncreaseChildren(Rental rental) {
    	
    	double increase = 1.5;
    	if (rental.getDaysRented() > 3) {
    		increase += (rental.getDaysRented() - 3) * 1.5;
    	}
    	return increase;
    }
    
    private boolean isTwoDayNewReleaseBonus(Rental rental) {
    	return (rental.getMovie().getPriceCode() == PriceCode.NEW_RELEASE && rental.getDaysRented() > 1) ? true : false;
    }
    
}