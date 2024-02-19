package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.Exception.AuthorizationDeniedException;
import it.unipv.ingsw.UniBook.Exception.PopupManager;
import it.unipv.ingsw.UniBook.Model.*;

import it.unipv.ingsw.UniBook.View.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeController {

	private HomeView hv;
	private ManagementView mv;
	private BookingView bv;
	private CondivisioneView sv;
	private User u;

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
	
	  //aggiungo listener bottone condivisione file
			
			ActionListener CF = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					manageAction();
				}

				private void manageAction() {

					openSharing(); 

				}
			};
			// Aggiungo il listener al bottone
			hv.getButtonF().addActionListener(CF);
}
	
	// Controllo se l'utente è un professore o un ricercatore
	private void openResourceManagementFrame() {
	    User user = SingletonManager.getInstance().getLoggedUser();
	    
	    if (user != null && (user.getTipo().equals("Professore") || user.getTipo().equals("Ricercatore"))) {
	    	
	        mv = new ManagementView();
	        ManagementController c = new ManagementController(mv, user);
	        mv.setVisible(true);
	        
	    } else {
	        try {
	        	
	            throw new AuthorizationDeniedException();
	            
	        } catch (AuthorizationDeniedException e) {
	        	
	            e.mostraPopup();
	            
	        }
	    }
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

		User model = SingletonManager.getInstance().getLoggedUser();
		RegistrationView view = new RegistrationView();
		RegistrationController c = new RegistrationController(view, model);
		
		//RegistrationView v = new RegistrationView();
		//RegistrationController c = new RegistrationController(v);
		view.setVisible(true);

	}
	private void openSharing() {
		System.out.println("ciaooo");
		sv = new CondivisioneView();
		CondivisioneModel r = new CondivisioneModel();
		CondivisioneController c = new CondivisioneController(sv, r);
		sv.setVisible(true);
	}

}

