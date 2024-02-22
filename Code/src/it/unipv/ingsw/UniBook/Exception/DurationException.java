package it.unipv.ingsw.UniBook.Exception;

public class DurationException extends Exception{

	private static String errorMessage = "La durata selezionata non è valida per l'orario di prenotazione. "
			+ "Ti ricordiamo che l'università chiude alle 19:00.";
	

	public DurationException() {
		
		super(errorMessage);

	}

	public void showPopup() {
		PopupManager.showPopup(errorMessage);
	}

	
}
