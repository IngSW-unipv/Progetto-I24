package it.unipv.ingsw.UniBook.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.UniBook.Model.Booking;

import it.unipv.ingsw.UniBook.View.BookingView;
import it.unipv.ingsw.UniBook.View.DeleteBookingView;
import it.unipv.ingsw.UniBook.Model.SingletonManager;

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
				model.setR(view.getRisorsa());
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
