package it.unipv.ingsw.UniBook.Model;

import it.unipv.ingsw.UniBook.DB.*;
import it.unipv.ingsw.UniBook.Controller.*;
import it.unipv.ingsw.UniBook.View.*;
import it.unipv.ingsw.UniBook.Model.*;

public class SingletonManager {

	private static SingletonManager instance = null;

	// Oggetti che voglio istanziare una sola volta
	private BookingDAO bookingDAO;
	private ResourceDAO resourceDAO;
	private UserDAO userDAO;
	private User loggedUser;
	private Resource r;
	private Professor professor;
	private Researcher researcher;
	private User user;
	//private Student student;
	// private BookingController controller;
	// private BookingView view;

	// Costruttore privato
	private SingletonManager() {
		// Inizializzazione oggetti
		this.bookingDAO = new BookingDAO();
		this.resourceDAO = new ResourceDAO();
		this.userDAO = new UserDAO();
		this.loggedUser = null;
		//this.student = null;
		this.professor = null;
		this.researcher = null;
		this.user=null;
		
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

	public ResourceDAO getResourceDAO() {
		return resourceDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	// public void setLoggedUser(User u) {
	// this.loggedUser = u;
	// }

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User u) {
		/*try {
			this.student = (Student) u;
			this.loggedUser = u;
		} catch (ClassCastException e1) {
			try {
				this.professor = (Professor) u;
				this.loggedUser = u;
			} catch (ClassCastException e2) {
				try {
					this.researcher = (Researcher) u;
					this.loggedUser = u;
				} catch (ClassCastException e3) {
					System.err.println("Impossibile impostare l'utente loggato: tipo utente non riconosciuto.");
				}
			}
		}
	}
	*/
		
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

}
