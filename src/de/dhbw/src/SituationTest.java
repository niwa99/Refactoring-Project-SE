package de.dhbw.src;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SituationTest {

	@Test
	void createMoviesAndTestItsFunctions() {
		//arrange
		Movie movie1 = new Movie("Hangover", PriceCode.REGULAR);
		Movie movie2 = new Movie("Hangover 3", PriceCode.NEW_RELEASE);
		
		//act
		
		//assert
		assertTrue(movie1.getPriceCode().asNumber() == 0);
		assertTrue(movie2.getTitle().equals("Hangover 3"));
	}
	
	@Test
	void changePricecodeOfAMovieAndCheckItsCorrectness() {
		//arrange
		Movie movie1 = new Movie("Testmovie", PriceCode.REGULAR);
		
		//act
		movie1.setPriceCode(PriceCode.CHILDREN);
		
		//assert
		assertTrue(movie1.getPriceCode().asNumber() == 2);
	}
	
	@Test
	void checkRentalForCorrectness() {
		//arrange
		Movie movie1 = new Movie("Testmovie", PriceCode.REGULAR);
		Rental rental1 = new Rental(movie1, 5);
		
		//act
		
		//assert
		assertTrue(rental1.getMovie() == movie1);
		assertTrue(rental1.getDaysRented() == 5);
	}
	
	@Test
	void fullSituationTest() {
		//arrange
		String result;
        Movie m1 = new Movie("movie1", PriceCode.NEW_RELEASE);
        Movie m2 = new Movie("movie2", PriceCode.CHILDREN);
        Rental r1 = new Rental(m1, 10);
        Rental r2 = new Rental(m2, 5);
        Customer c1 = new Customer("joe");
        c1.addRental(r1);
        c1.addRental(r2);
        
        //act
        result = c1.statement();
        
        //assert
        assertTrue(result.contains("Rental Record for joe"));
        assertTrue(result.contains("movie1		10	30.0"));
        assertTrue(result.contains("movie2		5	4.5"));
        assertTrue(result.contains("Amount owed is 34.5"));
        assertTrue(result.contains("You earned 3 frequent renter points"));
	}

}
