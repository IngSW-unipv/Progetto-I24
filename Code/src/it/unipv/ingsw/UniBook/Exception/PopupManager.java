package it.unipv.ingsw.UniBook.Exception;

import javax.swing.JOptionPane;

public class PopupManager {

	public static void showPopup(String message) {
        JOptionPane.showMessageDialog(null, message, "Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
	
	public static int showChoosing(Object [] options) {
		return JOptionPane.showOptionDialog(null, "Scegli un'opzione", "Affitti", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
	}
	
}
