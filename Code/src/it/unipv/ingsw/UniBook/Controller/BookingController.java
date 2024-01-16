package it.unipv.ingsw.UniBook.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;

import it.unipv.ingsw.UniBook.Model.Prenotazione;
import it.unipv.ingsw.UniBook.View.BookingView;
import it.unipv.ingsw.UniBook.View.HomeView;

public class BookingController {

	private Prenotazione model;
	private BookingView view;

	public BookingController(Prenotazione model, BookingView view) {
		this.model = model;
		this.view = view;
		initComponents();
	}

	private void initComponents() {

		ActionListener al = new ActionListener() {
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
						mostraPopup("Prenotazione effettuata con successo! " + "\n Data: " + model.getData()
								+ "\n Dalle: " + model.getOra() + " alle: " + model.getFinePrenotazione()
								+ "\n Risorsa: " + model.getNome());

						view.dispose();
					} else {
						mostraPopup("La durata selezionata non è valida per l'orario di prenotazione."
								+ "Ti ricordiamo che l'università chiude alle 19:00.");
					}

				} catch (NullPointerException e) {
					mostraPopup("Seleziona una data");
				}

			}

		};

		// Aggiungo il listener al bottone
		view.getConfermaButton().addActionListener(al);

	}

	private void mostraPopup(String messaggio) {
		JOptionPane.showMessageDialog(null, messaggio, "Sistema", JOptionPane.INFORMATION_MESSAGE);
	}

}
