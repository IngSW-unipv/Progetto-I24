package it.unipv.ingsw.UniBook.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.UniBook.View.RegistrationView;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.Exception.*;
import it.unipv.ingsw.UniBook.View.LoginView;
import it.unipv.ingsw.UniBook.Model.Registration;

public class RegistrationController {
	private RegistrationView view;
	private User model;

	public RegistrationController(RegistrationView view) {
		this.view = view;
	}

	public RegistrationController(RegistrationView view, User model) {
		this.view = view;
		this.model = model;
		initComponents();
	}

	private void initComponents() {

		ActionListener confirm = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				// model =new User();

				model.setId(view.getMatricola());
				model.setNome(view.getNome());
				model.setCognome(view.getCognome());
				model.setTipo(view.getTipo());
				model.setCorso(view.getCorso());
				model.setEmail(view.getEmail());
				model.setPassword(String.valueOf(view.getPassword()));

				Registration reg = new Registration(model);

				if(reg.register(String.valueOf(view.getConfermaPassword())))
					view.dispose();
					

			}

		};

		view.getRegisterButton().addActionListener(confirm);

		ActionListener switchToLogin = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				LoginView loginView = new LoginView();
				LoginController loginController = new LoginController(loginView, model);

				loginView.setVisible(true);

				// Chiudo la finestra corrente
				view.dispose();

			}

		};

		view.getLoginButton().addActionListener(switchToLogin);

	}

}
