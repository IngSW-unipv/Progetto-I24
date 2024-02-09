package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.Resource;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.View.BookingView;
import it.unipv.ingsw.UniBook.View.ManagementView;
import it.unipv.ingsw.UniBook.View.RegistrationView;
import it.unipv.ingsw.UniBook.View.HomeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeController {

	private HomeView hv;
	private ManagementView mv;
	private BookingView bv;

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

				openResourceManagementFrame();

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

		RegistrationView v = new RegistrationView();
		RegistrationController c = new RegistrationController(v);
		v.setVisible(true);

	}

}