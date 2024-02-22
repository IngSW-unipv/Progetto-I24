package it.unipv.ingsw.UniBook.Exception;

public class OverbookingException extends Exception {

	private static String errorMessage = "La risorsa desiderata non è disponibile. Prova a selezionare un altro orario";

	public OverbookingException() {
		
		super(errorMessage);

	}

	public void showPopup() {
		PopupManager.showPopup(errorMessage);
	}
	
	public void showPopup(String msg) {
		PopupManager.showPopup(msg);
	}
}
