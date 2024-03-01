package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.Chat.ChatUpdater;
import it.unipv.ingsw.UniBook.Exception.AuthorizationDeniedException;
import it.unipv.ingsw.UniBook.Exception.PopupManager;
import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.CondivisioneModel;
import it.unipv.ingsw.UniBook.Model.Professor;
import it.unipv.ingsw.UniBook.Model.Renting;
import it.unipv.ingsw.UniBook.Model.Researcher;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.View.BookingView;
import it.unipv.ingsw.UniBook.View.ChatView;
import it.unipv.ingsw.UniBook.View.CondivisioneView;
import it.unipv.ingsw.UniBook.View.FileDownloadFrame;
import it.unipv.ingsw.UniBook.View.FileSelectionFrame;
import it.unipv.ingsw.UniBook.View.HomeView;
import it.unipv.ingsw.UniBook.View.ManagementRentingView;
import it.unipv.ingsw.UniBook.View.ManagementView;
import it.unipv.ingsw.UniBook.View.RegistrationView;
import it.unipv.ingsw.UniBook.View.RentingView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class HomeController {

	private HomeView hv;
	private ManagementView mv;
	private BookingView bv;
	private RentingView rv;
	private ManagementRentingView rmv;
	private CondivisioneView sv;
	private FileSelectionFrame fs;
    private FileDownloadFrame df;

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

		// Creazione listener per l'affitto di risorse
		ActionListener RT = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {
				openRentChoose();
			}
		};
		// Associazione listener-button
		hv.getButtonRT().addActionListener(RT);

		ActionListener EX = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				goBack();

			}

		};

		ActionListener CH = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {
				openChat();
			}
		};

		hv.getButtonC().addActionListener(CH);

		hv.getExitButton().addActionListener(EX);

		// aggiungo listener bottone condivisione file

		ActionListener CF = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				openSharingChoose();

			}
		};
		// Aggiungo il listener al bottone
		hv.getButtonF().addActionListener(CF);

	}

	// Controllo se l'utente è autorizzato ad aprire la finestra di gestione delle risorse
		private void openResourceManagementFrame() {
		    User user = SingletonManager.getInstance().getLoggedUser();

		        try {
		        	
		        	 checkUserAuthorization(user);

		        	 mv = new ManagementView();
		        	 ManagementController c = new ManagementController(mv, user);
		        	 mv.setVisible(true);
		                
		            } catch (ClassCastException e) {
		            	
		                AuthorizationDeniedException ex = new AuthorizationDeniedException();
		                ex.showPopup();
		                System.out.println(ex.toString());
		                
		            }
		        }
		
		// Metodo per verificare se l'utente è un professore o un ricercatore
		private void checkUserAuthorization(User user) throws ClassCastException {
			
		    try {
		    	
		        Professor professor = (Professor) user;
		        
		    } catch (ClassCastException e1) {
		    	
		        try {

		            Researcher researcher = (Researcher) user;

		        } catch (ClassCastException e2) {

		            throw new ClassCastException("L'utente non è né un professore né un ricercatore.");
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

		view.setVisible(true);

	}

	private void openRentChoose() {
		Object[] options = { "Affitta una risorsa", "Gestisci gli affitti" };
		int choose = PopupManager.showChoosing(options);
		switch (choose) {
		case 0:
			openResourceRenting();
			break;
		case 1:
			openResourceRentingManagement();
			break;
		}
	}

	private void openResourceRentingManagement() {

		rmv = new ManagementRentingView();
		Renting r = new Renting();
		RentingController c = new RentingController(r, rmv);

	}

	private void openResourceRenting() {

		rv = new RentingView();
		Renting r = new Renting();
		RentingController c = new RentingController(r, rv);
		rv.setVisible(true);
	}

	private void openSharingChoose() {

		Object[] options = { "Upload file", "Download file" };
		int choose = PopupManager.showChoosing(options);
		switch (choose) {
		case 0:
			openSelectionFrame();
			break;
		case 1:
			openDownloadFrame();
			break;
		}
		
	}
	
	private void openSelectionFrame() {
	    User user = SingletonManager.getInstance().getLoggedUser();

	    try {
	        checkUserAuthorization(user);
	        
	        // Solo se l'utente è un professore o un ricercatore consento l'apertura del frame di selezione file
	        sv = new CondivisioneView();
	        fs = new FileSelectionFrame(null);
	        df = new FileDownloadFrame();
	        CondivisioneModel r = new CondivisioneModel();
	        
	        CondivisioneController c = new CondivisioneController(sv, r, fs,df);
	        fs.setVisible(true);
	        
	    } catch (ClassCastException e) {
	        AuthorizationDeniedException ex = new AuthorizationDeniedException();
	        ex.showPopup();
	        System.out.println(ex.toString());
	    }
	}
	
	private void openDownloadFrame() {

	        sv = new CondivisioneView();
	        fs = new FileSelectionFrame(null);
	        df = new FileDownloadFrame();
	        CondivisioneModel r = new CondivisioneModel();
	        CondivisioneController c = new CondivisioneController(sv, r, fs, df);
	        df.setVisible(true);

	}

	private void openChat() {
		
		// Apertura Chat e scelta destinatario
		User userLoggato = SingletonManager.getInstance().getLoggedUser();

		List<User> listaUtenti = SingletonManager.getInstance().getUserDAO().getUsersFromDatabase();
		listaUtenti.removeIf(u -> u.getId().equals(userLoggato.getId()));

		JComboBox<User> destinatarioComboBox = new JComboBox<>(listaUtenti.toArray(new User[0]));
		int result = JOptionPane.showConfirmDialog(null, destinatarioComboBox, "Seleziona il destinatario",
				JOptionPane.OK_CANCEL_OPTION);

		ChatView cv = null;
		
		final ChatUpdater[] updater = new ChatUpdater[1]; 
		
		if (result == JOptionPane.OK_OPTION) {
			User destinatario = (User) destinatarioComboBox.getSelectedItem();

			cv = new ChatView(destinatario);
			ChatController controller = new ChatController(cv, userLoggato, destinatario);

			// Istanza e run del thread
			updater[0] = new ChatUpdater(controller, userLoggato, destinatario);
			updater[0].start();
			
			WindowAdapter windowListener = new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					manageAction();
				}

				private void manageAction() {
					updater[0].stopUpdating();
				}
			};

			cv.getFrame().addWindowListener(windowListener);
			
		}
	

	}

}
