package it.unipv.ingsw.UniBook.Model;
import it.unipv.ingsw.UniBook.DB.*;
import it.unipv.ingsw.UniBook.Controller.*;

public class SingletonManager {

	 private static SingletonManager instance = null;
	 
	 	//Oggetti che voglio istanziare una sola volta
	    private PrenotazioneDAO prenotazioneDAO;
	    private RisorsaDAO risorsaDAO;
	    private BookingController controller;

	    // Costruttore privato 
	    private SingletonManager() {
	    	//Inizializzazione oggetti
	        this.prenotazioneDAO = new PrenotazioneDAO();
	        this.risorsaDAO = new RisorsaDAO();
	        this.controller = new BookingController();
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

		public BookingController getBookingController() {
			return controller;
		}
	
	
}
