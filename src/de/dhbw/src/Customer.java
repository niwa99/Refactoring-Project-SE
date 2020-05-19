package de.dhbw.src;

import java.util.*;

class Customer {
	
	private static final String C_TAB = "\t";
	private static final String C_LINEBREAK = "\n";
	
    private String name;
    private ArrayList<Rental> rentals = new ArrayList<>();
    
    public Customer(String name) {
        this.name = name;
    }
    
    public void addRental(Rental rental) {
        rentals.add(rental);
    }
    
    public String getName() {
        return name;
    }
    
    public String getFullInformation() {
    	
    	StringBuilder builder = new StringBuilder();
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        
        builder.append(getNewHeaderOutputLine());
        builder.append(getNewInformationOutputLine("Title", "Days", "Amount"));
        
        for (Rental rental : rentals) {
        	double rentalAmount = getAmountForRental(rental);
        	frequentRenterPoints++;
        	
        	if (isTwoDayNewReleaseBonus(rental)) {
        		frequentRenterPoints++;
        	}
        	
        	builder.append(getNewInformationOutputLine(rental.getMovie().getTitle(), rental.getDaysRented(), rentalAmount));
        	totalAmount += rentalAmount;
        };
        
        builder.append(getNewFooterOutputLines(totalAmount, frequentRenterPoints));
        return builder.toString();
    }

    private double getAmountForRental(Rental rental) {
        
        switch (rental.getMovie().getPriceCode()) {
            case REGULAR:
                return getIncreaseRegular(rental);
            case NEW_RELEASE:
                return getIncreaseNewRelease(rental);
            case CHILDREN:
                return getIncreaseChildren(rental);
        }
        return 0;
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
    
    private String getNewHeaderOutputLine() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("Rental Record for ");
        builder.append(this.getName());
        builder.append(C_LINEBREAK);
        return builder.toString();
    }
    
    private String getNewInformationOutputLine(String title, int days, double amount) {
    	return getNewInformationOutputLine(title, String.valueOf(days), String.valueOf(amount));
    }
    
    private String getNewInformationOutputLine(String title, String days, String amount) {
    	StringBuilder builder = new StringBuilder();
    	builder.append(C_TAB);
        builder.append(title);
        builder.append(C_TAB);
        builder.append(C_TAB);
        builder.append(days);
        builder.append(C_TAB);
        builder.append(amount);
        builder.append(C_LINEBREAK);
        return builder.toString();
    }
    
    private String getNewFooterOutputLines(double totalAmount, int frequentRenterPoints) {
    	StringBuilder builder = new StringBuilder();
    	builder.append("Amount owed is ");
    	builder.append(totalAmount);
    	builder.append(C_LINEBREAK);
    	
    	builder.append("You earned ");
    	builder.append(frequentRenterPoints);
    	builder.append(" frequent renter points");
    	return builder.toString();
    }
    
}