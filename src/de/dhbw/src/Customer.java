package de.dhbw.src;

import java.lang.*;
import java.util.*;

class Customer {
	
    private String name;
    private Vector rentals = new Vector();
    
    public Customer(String newname) {
        name = newname;
    }
    
    public void addRental(Rental arg) {
        rentals.addElement(arg);
    }
    
    public String getName() {
        return name;
    }
    
    public String statement() {
    	
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration enum_rentals = rentals.elements();
        String result = "Rental Record for " + this.getName() + "\n";
        result += "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";

        while (enum_rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) enum_rentals.nextElement();
            //determine amounts for each line
            thisAmount = amountFor(each);
            // add frequent renter points
            frequentRenterPoints ++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == PriceCode.NEW_RELEASE) && each.getDaysRented() > 1) {
                frequentRenterPoints ++;
            }
            //show figures for this rental
            result += "\t" + each.getMovie().getTitle()+ "\t" + "\t" + each.getDaysRented() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        //add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
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
    
}