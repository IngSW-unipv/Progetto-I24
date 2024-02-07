package it.unipv.ingsw.UniBook.Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import it.unipv.ingsw.UniBook.DB.ResourceDAO;
import it.unipv.ingsw.UniBook.Exception.*;
import it.unipv.ingsw.UniBook.DB.BookingDAO;
import it.unipv.ingsw.UniBook.Model.SingletonManager;

public class Booking {

	private Resource r;
	private User u;
	private String date;
	private String time;
	private int duration;
	private BookingDAO bDAO;

	public Booking() {
		this.bDAO = SingletonManager.getInstance().getBookingDAO();
	}

	public Booking(Resource r, User u, String date, String time, int duration) {
		this.r = r;
		this.u = u;
		this.bDAO = SingletonManager.getInstance().getBookingDAO();
		this.date = date;
		this.time = time;
		this.duration = duration;
	}

	public Resource getR() {
		return r;
	}

	public void setR(Resource r) {
		this.r = r;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getResourceName() {
		return r.getName();
	}

	public void setResourceName(String name) {
		r.setNome(name);
		;
	}

	public String getUserId() {
		return u.getId();
	}

	public void setUserId(String Id) {
		u.setId(Id);
		;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	// Logica-------

	public String getBookingEnd() {
		String timeToInt = time.substring(0, 2);
		int timeInt = Integer.parseInt(timeToInt) + duration;
		return timeInt + ":00";
	}

	public boolean validDuration() {

		int close = 19;

		int startBooking = Integer.parseInt(time.substring(0, 2));

		if (duration > (close - startBooking))
			return false;

		return true;

	}

	public String[] timeChoice() {
		// Creo un array di orari disponibili da 8 a 18
		String[] availableTime = new String[11];
		for (int i = 0; i < 11; i++) {
			int ora = 8 + i;
			availableTime[i] = String.format("%02d:00", ora); // Formatto l'ora come "HH:00"
		}
		return availableTime;
	}

	public Integer[] durationChoice() {
		// Creo un array di durate disponibili da 1 a 4 ore
		return new Integer[] { 1, 2, 3, 4 };
	}

	public ArrayList<String> updateJListResources() {

		ResourceDAO resourceDAO = new ResourceDAO();
		ArrayList<String> availableResources = resourceDAO.selectAll();

		return availableResources;
	}

	public void tryToBook() throws DatabaseException {

		// Setto l'ID alla risorsa sulla base del nome
		r.setId(Integer.parseInt(bDAO.getIDbyName(r)));

		Booking ttb = new Booking(r, u, date, time, duration);

		boolean succesfulInsertion = bDAO.insertBooking(ttb, SingletonManager.getInstance().getLoggedUser());

		if (succesfulInsertion) {
			PopupManager.mostraPopup("Prenotazione effettuata con successo! " + "\n Data: " + date + "\n Dalle: " + time
					+ " alle: " + getBookingEnd() + "\n Risorsa: " + r.getName());
		} else {
			throw new DatabaseException();
		}
	}

	public void checkDuration() throws DurationException{
		if (!validDuration()) {
			throw new DurationException();
			//PopupManager.mostraPopup("La durata selezionata non è valida per l'orario di prenotazione."
				//	+ "Ti ricordiamo che l'università chiude alle 19:00.");
		}
	}

	public void checkEmptyDate() throws EmptyFieldException {
		if (date == "")
			throw new EmptyFieldException();
	}

	
	public ArrayList<Booking> getUserBookings(User u) {
		return bDAO.selectBookingFromUser(u);
	}
	
	public void removeBooking(ArrayList<Booking> bookings,int index) {
		System.out.println("ECCOLO "+ index);
		//DA QUI DEVO RIMUOVERLO
		
        bookings.remove(index);
       // bDAO.deleteSelectedBooking(u, index);
    }
	

	//public void rimuoviPrenotazione(int index) {
		//bookings.remove(index);
	//}

}
