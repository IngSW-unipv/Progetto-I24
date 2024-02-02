package it.unipv.ingsw.UniBook.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import it.unipv.ingsw.UniBook.Model.Prenotazione;
import it.unipv.ingsw.UniBook.Model.Risorsa;
import it.unipv.ingsw.UniBook.DB.RisorsaDAO;
import it.unipv.ingsw.UniBook.View.BookingView;
import it.unipv.ingsw.UniBook.DB.PrenotazioneDAO;
import it.unipv.ingsw.UniBook.Model.SingletonManager;

public class BookingController {

	private Prenotazione model;
	private BookingView view;
	private PrenotazioneDAO pDAO;

	public BookingController() {

	}

	public BookingController(Prenotazione model, BookingView view) {
		this.pDAO = SingletonManager.getInstance().getPrenotazioneDAO();
		this.model = model;
		this.view = view;
		initComponents();
	}

	private void initComponents() {

		ActionListener conferma = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				try {
					model.setData(view.getData());
					model.setOra(view.getOra());
					model.setNome(view.getRisorsa());
					model.setDurata(view.getDurata());

					if (model.isDurataValida()) {					

						boolean inserimentoRiuscito = pDAO.insertPrenotazione(model);

						if (inserimentoRiuscito) {
							mostraPopup("Prenotazione effettuata con successo! " + "\n Data: " + model.getData()
									+ "\n Dalle: " + model.getOra() + " alle: " + model.getFinePrenotazione()
									+ "\n Risorsa: " + model.getNome());

							view.dispose();
						} else {
							mostraPopup("Errore durante l'inserimento nel database.");
						}

					} else {
						mostraPopup("La durata selezionata non è valida per l'orario di prenotazione."
								+ "Ti ricordiamo che l'università chiude alle 19:00.");
					}

				} catch (NullPointerException e) {
					mostraPopup("Seleziona una data");
					e.notify();
				}

			}

		};

		// Aggiungo il listener al bottone
		view.getConfermaButton().addActionListener(conferma);

	}

	public ArrayList<String> aggiornaJListRisorse() {

		RisorsaDAO risorsaDAO = new RisorsaDAO();
		ArrayList<String> risorseDisponibili = risorsaDAO.selectAll();

		return risorseDisponibili;
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

	private void mostraPopup(String messaggio) {
		JOptionPane.showMessageDialog(null, messaggio, "Sistema", JOptionPane.INFORMATION_MESSAGE);
	}

}
