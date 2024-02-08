package it.unipv.ingsw.UniBook.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.Resource;
import it.unipv.ingsw.UniBook.DB.ResourceDAO;
import it.unipv.ingsw.UniBook.View.BookingView;
import it.unipv.ingsw.UniBook.View.DeleteBookingView;
import it.unipv.ingsw.UniBook.DB.BookingDAO;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Exception.*;
import it.unipv.ingsw.UniBook.View.DeleteBookingView;

public class BookingController {

	private Booking model;
	private BookingView view;
	private DeleteBookingView dview;

	public BookingController() {

	}

	public BookingController(Booking model, BookingView view) {
		this.model = model;
		this.view = view;
		initComponents();
	}

	private void initComponents() {

		ActionListener confirm = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				try {
					model.setDate(view.getData());
					model.setTime(view.getOra());
					model.setR(new Resource(0, view.getRisorsa(), null, null));
					model.setDuration(view.getDurata());

					model.checkEmptyDate();
					model.checkDuration();

					model.tryToBook();

				} catch (DatabaseException e) {
					e.mostraPopup();
					System.out.println(e.toString());
				} catch (EmptyFieldException e) {
					e.mostraPopup();
					System.out.println(e.toString());
				} catch (DurationException e) {
					e.mostraPopup();
					System.out.println(e.toString());
				}

			}

		};

		// Aggiungo il listener al bottone
		view.getConfermaButton().addActionListener(confirm);

		ActionListener remove = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {
				
				dview = new DeleteBookingView();
				dview.updateTable(model.getUserBookings(SingletonManager.getInstance().getLoggedUser()));
				remove();
				//dview.updateTable(model.getUserBookings(SingletonManager.getInstance().getLoggedUser()));
			}

		};

		view.getRemoveButton().addActionListener(remove);
		
	}
	
	private void remove() {
		
		ActionListener deleteBooking=new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {
	            // Ottieni l'indice della riga selezionata dalla vista
	            int selectedRow = dview.getSelectedRow();
	            // Rimuovi la prenotazione dal modello	            
	            model.removeBooking(model.getUserBookings(SingletonManager.getInstance().getLoggedUser()), selectedRow);
	            
	            
	            // Aggiorna la vista

			}
			
		};
		
	dview.getDeleteButton().addActionListener(deleteBooking);
		
	}

}
