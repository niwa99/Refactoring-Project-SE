package de.dhbw.src;

public enum PriceCode {
	
	CHILDREN(2),
	REGULAR(0),
	NEW_RELEASE(1);
	
	private int priceCode;
	
	PriceCode(int priceCode) {
		this.priceCode = priceCode;
	}
	
	public int asNumber() {
		return priceCode;
	}
}
