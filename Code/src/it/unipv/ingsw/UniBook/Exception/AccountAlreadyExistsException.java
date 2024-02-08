package it.unipv.ingsw.UniBook.Exception;

import javax.swing.JOptionPane;

public class AccountAlreadyExistsException extends Exception{

	private static String errorMessage = "Account già esistente";

	public AccountAlreadyExistsException() {
		
		super(errorMessage);

	}

	public void mostraPopup() {
		PopupManager.mostraPopup(errorMessage);
	}
	
}
