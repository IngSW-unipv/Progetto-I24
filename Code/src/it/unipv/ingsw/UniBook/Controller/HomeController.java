package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.Inizio;
import it.unipv.ingsw.UniBook.Model.*;

import it.unipv.ingsw.UniBook.View.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeController {

	private HomeView hv;
	private ManagementView mv;
	private BookingView bv;
	//private CondivisioneView sv;

	public HomeController(HomeView view) {
		hv = view;
		initComponents();
	}

	private void initComponents() {

		ActionListener GR = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				openResourceManagementFrame(); //controller non frame 

			}
		};

		// Aggiungo il listener al bottone
		hv.getButtonGR().addActionListener(GR);

		ActionListener PR = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				openResourceBooking();

			}
		};

		// Aggiungo il listener al bottone
		hv.getButtonPR().addActionListener(PR);

		ActionListener EX = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				goBack();

			}

		};

		hv.getExitButton().addActionListener(EX);

	//}
	
	  //aggiungo listener bottone condivisione file
	//		hv.getButtonF().addActionListener(F);

			ActionListener F = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					manageAction();
				}

				private void manageAction() {

					//openSharing();

				}
			};	
			hv.getButtonF().addActionListener(F); //richiamo action listener

}
	

	private void openResourceManagementFrame() {

		mv = new ManagementView();
		Resource r = new Resource();
		ManagementController c = new ManagementController(mv, r);
		mv.setVisible(true);
	}

	private void openResourceBooking() {

		bv = new BookingView();
		Booking p = new Booking();
		BookingController c = new BookingController(p, bv);
		bv.setVisible(true);
	}

	private void goBack() {

		if (hv != null)
			hv.dispose();

		if (bv != null)
			bv.dispose();

		if (mv != null)
			mv.dispose();

		Inizio.main(null);
		
	}
	/*
	 private void openSharing() {
	 

		sv = new CondivisioneView();
		CondivisioneModel r = new CondivisioneModel();
		CondivisioneController c = new CondivisioneController(sv, r);
		sv.setVisible(true);
	}
	
	*/

}