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

				model.setDate(view.getData());
				model.setTime(view.getOra());
				model.setR(new Resource(0, view.getRisorsa(), null, null, null, 0 , null));
				model.setDuration(view.getDurata());

				model.tryToBook();

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
			}

		};

		view.getRemoveButton().addActionListener(remove);

	}

	private void remove() {

		ActionListener deleteBooking = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {
				model.delete(dview.getSelectedRow());
				// Aggiorno la vista
				dview.updateTable(model.getUserBookings(SingletonManager.getInstance().getLoggedUser()));
			}

		};

		dview.getDeleteButton().addActionListener(deleteBooking);
	}

}
