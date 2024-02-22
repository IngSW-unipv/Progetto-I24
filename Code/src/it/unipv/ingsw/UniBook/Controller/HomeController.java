package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.DB.UserDAO;
import it.unipv.ingsw.UniBook.Exception.AuthorizationDeniedException;
import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.CondivisioneModel;
import it.unipv.ingsw.UniBook.Model.Renting;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.View.BookingView;
import it.unipv.ingsw.UniBook.View.ChatView;
import it.unipv.ingsw.UniBook.View.CondivisioneView;
import it.unipv.ingsw.UniBook.View.HomeView;
import it.unipv.ingsw.UniBook.View.ManagementView;
import it.unipv.ingsw.UniBook.View.RegistrationView;
import it.unipv.ingsw.UniBook.View.RentingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class HomeController {

	private HomeView hv;
	private ManagementView mv;
	private BookingView bv;
	private RentingView rv;
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

				openResourceManagementFrame(); // controller non frame

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
				
				openResourceRenting();
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

		// RegistrationView v = new RegistrationView();
		// RegistrationController c = new RegistrationController(v);
		view.setVisible(true);

	}

	private void openResourceRenting() {
		rv = new RentingView();
		Renting r = new Renting();
		RentingController c = new RentingController(r, rv);
		rv.setVisible(true);
	}

	private void openSharing() {
		System.out.println("ciaooo");
		sv = new CondivisioneView();
		CondivisioneModel r = new CondivisioneModel();
		CondivisioneController c = new CondivisioneController(sv, r);
		sv.setVisible(true);
	}
	
	private void openChat() {
        User userLoggato = SingletonManager.getInstance().getLoggedUser();
        if (userLoggato == null) {
            JOptionPane.showMessageDialog(null, "Effettua il login prima di accedere alla chat.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserDAO userDAO = new UserDAO();
        List<User> listaUtenti = userDAO.getUsersFromDatabase();

        if (listaUtenti.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nessun utente disponibile per la chat.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        listaUtenti.removeIf(u -> u.getId().equals(userLoggato.getId()));

        JComboBox<User> destinatarioComboBox = new JComboBox<>(listaUtenti.toArray(new User[0]));
        int result = JOptionPane.showConfirmDialog(null, destinatarioComboBox, "Seleziona il destinatario", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            User destinatario = (User) destinatarioComboBox.getSelectedItem();
            if (destinatario != null) {
                ChatView cv = new ChatView(userLoggato, destinatario);
                ChatController controller = new ChatController(cv, userLoggato, destinatario);
                cv.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Destinatario non valido.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
