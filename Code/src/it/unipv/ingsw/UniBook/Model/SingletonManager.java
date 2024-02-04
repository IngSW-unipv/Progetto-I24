package it.unipv.ingsw.UniBook.Model;

import it.unipv.ingsw.UniBook.DB.*;
import it.unipv.ingsw.UniBook.Controller.*;
import it.unipv.ingsw.UniBook.View.*;

public class SingletonManager {

	private static SingletonManager instance = null;

	// Oggetti che voglio istanziare una sola volta
	private PrenotazioneDAO prenotazioneDAO;
	private RisorsaDAO risorsaDAO;
	private UserDAO userDAO;
	// private BookingController controller;
	// private BookingView view;

	// Costruttore privato
	private SingletonManager() {
		// Inizializzazione oggetti
		this.prenotazioneDAO = new PrenotazioneDAO();
		this.risorsaDAO = new RisorsaDAO();
		this.userDAO = new UserDAO();
		// this.controller = new BookingController();
		// this.view = new BookingView();
	}

	// Metodo pubblico per ottenere l'istanza Singleton
	public static SingletonManager getInstance() {
		if (instance == null) {
			instance = new SingletonManager();
		}
		return instance;
	}

	// Metodi per ottenere le istanze di oggetti
	public PrenotazioneDAO getPrenotazioneDAO() {
		return prenotazioneDAO;
	}

	public RisorsaDAO getRisorsaDAO() {
		return risorsaDAO;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/*
	 * public BookingController getController() { return controller; }
	 * 
	 * public BookingView getView() { return view; }
	 */

}
