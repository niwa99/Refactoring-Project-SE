package de.dhbw.src;

public class Movie {
    
    private String title;
    private PriceCode priceCode;
    
    public Movie(String newtitle, PriceCode priceCode) {
        title = newtitle;
        this.priceCode = priceCode;
    }
    
    public PriceCode getPriceCode() {
        return priceCode;
    }
    
    public void setPriceCode(PriceCode priceCode) {
        this.priceCode = priceCode;
    }
    
    public String getTitle () {
        return title;
    }
}