package it.unipv.ingsw.UniBook.Model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import it.unipv.ingsw.UniBook.Exception.*;
import it.unipv.ingsw.UniBook.DB.BookingDAO;

public class Booking {

	private Resource r;
	private User u;
	private String date;
	private String time;
	private int duration;
	private BookingDAO bDAO;

	public Booking() {
		this.bDAO = SingletonManager.getInstance().getBookingDAO();
		this.u = SingletonManager.getInstance().getLoggedUser();
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

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	// Logica-------

	// Metodo che ritorna quando finisce la prenotazione per mostrarlo nel PopUp di
	// avvenuta prenotazione
	public String getBookingEnd() {
		String timeToInt = time.substring(0, 2);
		int timeInt = Integer.parseInt(timeToInt) + duration;
		return timeInt + ":00";
	}

	// Metodo che controlla che un utente non prenoti quando l'università è chiusa
	private void checkDuration() throws DurationException {

		boolean valid;

		int close = 19;

		int startBooking = Integer.parseInt(time.substring(0, 2));

		if (duration > (close - startBooking)) {
			valid = false;
		} else {
			valid = true;
		}

		if (!valid) {
			throw new DurationException();
		}
	}

	public ArrayList<Resource> updateJListResources() {

		ArrayList<Resource> availableResources = SingletonManager.getInstance().getResourceDAO().getAllBookableResources();
		availableResources.addAll(SingletonManager.getInstance().getLaboratoryDAO().selectAllLaboratory());

		return availableResources;
	}

	// Metodo che controlla la disponibilità della risorsa scelta nella data e ora
	// selezionate
	private void checkAvailability() throws OverbookingException {

		if (!bDAO.checkAvailability(this))
			throw new OverbookingException();

	}

	// Metodo che verifica l'inserimento della data
	private void checkEmptyDate() throws EmptyFieldException {
		if (date == "")
			throw new EmptyFieldException();
	}

	// Metodo che restituisce le prenotazioni di un determinato utente, utile nella
	// cancellazione delle risorse
	public ArrayList<Booking> getUserBookings(User u) {
		return bDAO.selectBookingFromUser(u);
	}

	// Metodo che consente l'eliminazione di risorse previa conferma
	private boolean removeBooking(int index) {

		int choice;

		if (index != -1) {
			choice = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare questa prenotazione?",
					"Conferma Eliminazione", JOptionPane.YES_NO_OPTION);

			// Verifica la scelta dell'utente
			if (choice == JOptionPane.YES_OPTION) {
				bDAO.deleteSelectedBooking(bDAO.getBooking(u, index));
				return true;
			}
		}

		return false;

	}

	public boolean delete(int index) {
		if (removeBooking(index)) {
			return true;
		}else {
			PopupManager.showPopup("Cancellazione fallita");
			return false;
		}
	}

	public boolean tryToBook() {

		Boolean result = false;

		try {

			if (r.getNome().toLowerCase().contains("laboratorio")) {
				result = laboratoryManagement();
			} else {
				result = resourceManagement();
			}

			if (result) {
				PopupManager.showPopup("Prenotazione effettuata con successo! " + "\n Data: " + date + "\n Dalle: "
						+ time + " alle: " + getBookingEnd() + "\n Risorsa: " + r.getNome());
			}

		} catch (EmptyFieldException e) {
			e.showPopup();
			System.out.println(e.toString());
		} catch (DurationException e) {
			e.showPopup();
			System.out.println(e.toString());
		} catch (OverbookingException e) {
			e.showPopup();
			System.out.println(e.toString());
		}

		return result;

	}

	private boolean resourceManagement() throws EmptyFieldException, OverbookingException, DurationException {
		checkEmptyDate();
		checkDuration();
		checkAvailability();

		boolean succesfulInsertion = bDAO.insertBooking(this);
		return succesfulInsertion;
	}

	private boolean laboratoryManagement()
			throws EmptyFieldException, DurationException, OverbookingException {

		try {

			// Se la conversiona va a buon fine permetto la prenotazione del laboratorio
			Professor professor = (Professor) u;

			checkEmptyDate();
			checkDuration();

			int capacity = SingletonManager.getInstance().getLaboratoryDAO().getCapacity(r);

			// Se l'aula è prenotata più della metà non consento la prenotazione al prof
			if (bDAO.getAlreadyPresentBooking(this).size() < (capacity / 2)) {

				// Ottengo le risorse prenotate in quella data e in quell'ora nel laboratorio
				for (Booking booking : bDAO.getAlreadyPresentBooking(this)) {
					bDAO.deleteSelectedBooking(booking); // Cancello le prenotazioni degli studenti
				}
				// Ciclo con cui vado a prenotare tutte le risorse di quel laboratorio
				for (Resource resource : SingletonManager.getInstance().getResourceDAO()
						.getResourceByLab((this.getR()))) {
					bDAO.insertBooking(new Booking(resource, u, date, time, duration));
				}

				return true;

			} else {
				throw new OverbookingException();
			}

		} catch (ClassCastException e) {
			AuthorizationDeniedException ecc = new AuthorizationDeniedException();
			ecc.showPopup();
			System.out.println(ecc.toString());
			return false;
		}

	}

}
