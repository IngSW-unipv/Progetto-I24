package it.unipv.ingsw.UniBook.Model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import it.unipv.ingsw.UniBook.DB.ResourceDAO;
import it.unipv.ingsw.UniBook.Exception.*;
import it.unipv.ingsw.UniBook.DB.BookingDAO;
import it.unipv.ingsw.UniBook.Model.SingletonManager;

public class Booking {

	private Resource r;
	private User u;
	private String data;
	private String ora;
	private int durata;
	private BookingDAO bDAO;

	public Booking() {
		this.bDAO = SingletonManager.getInstance().getBookingDAO();
	}

	public Booking(Resource r, User u, String data, String ora, int durata) {
		this.r = r;
		this.u = u;
		this.bDAO = SingletonManager.getInstance().getBookingDAO();
		this.data = data;
		this.ora = ora;
		this.durata = durata;
	}

	public Resource getR() {
		return r;
	}

	public void setR(Resource r) {
		this.r = r;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getOra() {
		return ora;
	}

	public void setOra(String ora) {
		this.ora = ora;
	}

	public String getNomeResource() {
		return r.getNome();
	}

	public void setNomeResource(String nome) {
		r.setNome(nome);
		;
	}

	public String getMatricolaUser() {
		return u.getMatricola();
	}

	public void setMatricolaUser(String Id) {
		u.setMatricola(Id);
		;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	// Logica

	public String getFinePrenotazione() {
		String oraToInt = ora.substring(0, 2);
		int oraInt = Integer.parseInt(oraToInt) + durata;
		return oraInt + ":00";
	}

	public boolean isDurataValida() {

		int orarioChiusura = 19;

		int oraInizioPrenotazione = Integer.parseInt(ora.substring(0, 2));

		if (durata > (orarioChiusura - oraInizioPrenotazione))
			return false;

		return true;

	}

	public String[] sceltaOrario() {
		// Creo un array di orari disponibili da 8 a 18
		String[] orarioDisponibile = new String[11];
		for (int i = 0; i < 11; i++) {
			int ora = 8 + i;
			orarioDisponibile[i] = String.format("%02d:00", ora); // Formatto l'ora come "HH:00"
		}
		return orarioDisponibile;
	}

	public Integer[] sceltaDurata() {
		// Creo un array di durate disponibili da 1 a 4 ore
		return new Integer[] { 1, 2, 3, 4 };
	}

	public ArrayList<String> aggiornaJListRisorse() {

		ResourceDAO risorsaDAO = new ResourceDAO();
		ArrayList<String> risorseDisponibili = risorsaDAO.selectAll();

		return risorseDisponibili;
	}

	public void tryToBook() throws DatabaseException {

		// Setto l'ID alla risorsa sulla base del nome
		r.setId(Integer.parseInt(bDAO.getIDbyName(r)));

		Booking ttb = new Booking(r, u, data, ora, durata);

		boolean inserimentoRiuscito = bDAO.insertPrenotazione(ttb, SingletonManager.getInstance().getLoggedUser());

		if (inserimentoRiuscito) {
			PopupManager.mostraPopup("Prenotazione effettuata con successo! " + "\n Data: " + data + "\n Dalle: " + ora + " alle: "
					+ getFinePrenotazione() + "\n Risorsa: " + r.getNome());
		} else {
			throw new DatabaseException();
		}
	}

	public void checkDurata() {
		if (!isDurataValida()) {
			PopupManager.mostraPopup("La durata selezionata non è valida per l'orario di prenotazione."
					+ "Ti ricordiamo che l'università chiude alle 19:00.");
		}
	}

	public void checkEmptyDate() throws EmptyFieldException {
		if (data == "")
			throw new EmptyFieldException();
	}

	/*private void mostraPopup(String messaggio) {
		JOptionPane.showMessageDialog(null, messaggio, "Sistema", JOptionPane.INFORMATION_MESSAGE);
	}*/
	

}
