package it.unipv.ingsw.UniBook.Exception;

import javax.swing.JOptionPane;

public class PopupManager {

	public static void mostraPopup(String messaggio) {
        JOptionPane.showMessageDialog(null, messaggio, "Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
	
}
