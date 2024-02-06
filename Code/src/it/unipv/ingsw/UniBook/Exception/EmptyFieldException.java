package it.unipv.ingsw.UniBook.Exception;

import javax.swing.JOptionPane;

public class EmptyFieldException extends Exception {

	private static String errorMessage = "Riempi tutti i campi!";

	public EmptyFieldException() {
		super(errorMessage);
	}

	public void mostraPopup() {
		PopupManager.mostraPopup(errorMessage);
	}

}
