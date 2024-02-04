package it.unipv.ingsw.UniBook.Exception;

import javax.swing.JOptionPane;

public class WrongFieldException extends Exception{
	
	private static String errorMessage = "Errata compilazione dei campi!";

	public WrongFieldException() {
		super(errorMessage);
	}

	public void mostraPopup() {
		JOptionPane.showMessageDialog(null, errorMessage, "Sistema", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
