package it.unipv.ingsw.UniBook.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.Resource;
import it.unipv.ingsw.UniBook.DB.ResourceDAO;
import it.unipv.ingsw.UniBook.View.BookingView;
import it.unipv.ingsw.UniBook.DB.BookingDAO;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Exception.*;

public class BookingController {

	private Booking model;
	private BookingView view;

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
					model.setData(view.getData());
					model.setOra(view.getOra());
					model.setR(new Resource(0, view.getRisorsa(), null, null));
					model.setDurata(view.getDurata());
					
					model.checkEmptyDate();
					model.checkDurata();
					
					model.tryToBook();


				}catch (DatabaseException e) {
					e.mostraPopup();
					System.out.println(e.toString());
				} catch (EmptyFieldException e) {
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

				try {
					
					
					
				}catch (NullPointerException e) {
					//e.mostraPopup();
					System.out.println(e.toString());
				}

			}

		};

		view.getRemoveButton().addActionListener(remove);
		
	}

}
