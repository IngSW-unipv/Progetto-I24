package it.unipv.ingsw.UniBook.Exception;

import javax.swing.JOptionPane;

public class DatabaseException extends Exception {

	private static String errorMessage = "C'è stato un problema nella comunicazione col database";

	public DatabaseException() {
		
		super(errorMessage);

	}

	public void mostraPopup() {
		JOptionPane.showMessageDialog(null, errorMessage, "Sistema", JOptionPane.INFORMATION_MESSAGE);
	}

}