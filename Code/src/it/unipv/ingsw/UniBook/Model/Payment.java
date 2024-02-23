package it.unipv.ingsw.UniBook.Model;

import java.util.regex.Pattern;

import it.unipv.ingsw.UniBook.Exception.WrongFieldException;

public class Payment {
	private String cardNumber;
	private String CVV;
	
	private final String cardNumberValidator = "\\d{16}";
	private final String cvvValidator = "\\d{3}";
	
	public Payment(String cardNumber, String CVV) {
		this.cardNumber = cardNumber;
		this.CVV = CVV;
	}
	
	public boolean check() throws WrongFieldException {
		boolean c = false;
		if((Pattern.matches(cardNumberValidator, cardNumber))&&(Pattern.matches(cvvValidator, CVV)))
			c = true;
		else
			throw new WrongFieldException();
		return c;
	}
}
