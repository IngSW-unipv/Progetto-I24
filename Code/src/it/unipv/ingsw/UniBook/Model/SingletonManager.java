package it.unipv.ingsw.UniBook.Model;

import it.unipv.ingsw.UniBook.DB.*;
import it.unipv.ingsw.UniBook.Controller.*;
import it.unipv.ingsw.UniBook.View.*;
import it.unipv.ingsw.UniBook.Model.*;

public class SingletonManager {

	private static SingletonManager instance = null;

	// Oggetti che voglio istanziare una sola volta
	private BookingDAO bookingDAO;
	private RentingDAO rentingDAO;
	private ResourceDAO resourceDAO;
	private UserDAO userDAO;
	private LaboratoryDAO laboratoryDAO;
	private MessaggioDAO messaggioDAO;
	private User loggedUser;
	private Resource r;
	private Professor professor;
	private Researcher researcher;
	private User user;

	// Costruttore privato
	private SingletonManager() {
		// Inizializzazione oggetti
		this.bookingDAO = new BookingDAO();
		this.rentingDAO = new RentingDAO();
		this.resourceDAO = new ResourceDAO();
		this.userDAO = new UserDAO();
		this.laboratoryDAO = new LaboratoryDAO();
		this.messaggioDAO = new MessaggioDAO();
		this.professor = null;
		this.researcher = null;
		loggedUser = new User();
	}

	// Metodo pubblico per ottenere l'istanza Singleton
	public static SingletonManager getInstance() {
		if (instance == null) {
			instance = new SingletonManager();
		}
		return instance;
	}

	// Metodi per ottenere le istanze di oggetti
	public BookingDAO getBookingDAO() {
		return bookingDAO;
	}

	public RentingDAO getRentingDAO() {
		return rentingDAO;
	}

	public ResourceDAO getResourceDAO() {
		return resourceDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public LaboratoryDAO getLaboratoryDAO() {
		return laboratoryDAO;
	}

	public MessaggioDAO getMessaggioDAO() {
		return messaggioDAO;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User u) {

		try {
			this.professor = (Professor) u;
			this.loggedUser = u;
		} catch (ClassCastException e1) {
			try {
				this.researcher = (Researcher) u;
				this.loggedUser = u;
			} catch (ClassCastException e2) {
				try {
					this.user = (User) u;
					this.loggedUser = u;
				} catch (ClassCastException e3) {
					System.err.println("Impossibile impostare l'utente loggato: tipo utente non riconosciuto.");
				}
			}
		}
	}

	public boolean isCurrentUserProfessor() {
		// TODO Auto-generated method stub
		return false;
	}

}
