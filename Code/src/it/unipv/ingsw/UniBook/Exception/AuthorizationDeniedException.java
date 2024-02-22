package it.unipv.ingsw.UniBook.Exception;

import javax.swing.JOptionPane;

public class AuthorizationDeniedException extends Exception {

	private static String errorMessage = "Non hai i permessi necessari";

	public AuthorizationDeniedException() {
		
		super(errorMessage);

	}

	public void showPopup() {
		PopupManager.showPopup(errorMessage);
	}

}
