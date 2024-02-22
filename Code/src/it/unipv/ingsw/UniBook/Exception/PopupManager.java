package it.unipv.ingsw.UniBook.Exception;

import javax.swing.JOptionPane;

public class PopupManager {

	public static void showPopup(String message) {
        JOptionPane.showMessageDialog(null, message, "Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
	
}
