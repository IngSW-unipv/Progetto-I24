package it.unipv.ingsw.UniBook.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.UniBook.View.LoginView;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.Model.Login;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Exception.*;

public class LoginController {
	private LoginView view;
	private User model;

	public LoginController(LoginView view, User model) {
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
				model.setId(view.getMatricola());

				// SingletonManager.getInstance().setLoggedUser(new User(view.getMatricola(),
				// null, null, null, null, null,
				// String.valueOf(view.getPassword())));

				Login logger = new Login(SingletonManager.getInstance().getUserDAO().selectUserByMatricola(model));

				if (logger.login())
					view.dispose();

			}

		};

		view.getConfirmButton().addActionListener(confirm);

	}

}
