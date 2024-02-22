package it.unipv.ingsw.UniBook.Exception;

public class ResourceAlreadyRentedException extends Exception {

	private String errorMessage;

	public ResourceAlreadyRentedException(String fromData, String toData) {

		this.errorMessage = "La risorsa è prenotata da: "+fromData+" a: "+toData;

	}
	
	public void showPopup() {
		PopupManager.mostraPopup(errorMessage);
	}
	
}
