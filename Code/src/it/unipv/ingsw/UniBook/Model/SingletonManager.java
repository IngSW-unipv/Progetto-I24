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

	// Costruttore privato
	private SingletonManager() {
		// Inizializzazione oggetti
		this.bookingDAO = new BookingDAO();
		this.rentingDAO = new RentingDAO();
		this.resourceDAO = new ResourceDAO();
		this.userDAO = new UserDAO();
		this.laboratoryDAO = new LaboratoryDAO();
		this.messaggioDAO = new MessaggioDAO();
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
		
		this.loggedUser=u;
		
	}

}
