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

				try {
					
					 

					
					//model.setMatricola(view.getMatricola());
					//model.setPassword(String.valueOf(view.getPassword()));
					SingletonManager.getInstance().setLoggedUser(new User( view.getMatricola(), null,
							null, null, null, null, String.valueOf(view.getPassword())));
					
					Login logger = new Login(model);
					
					logger.fieldCheck();
					logger.passwordCheck();
					logger.login();
					
					view.dispose(); 
					
				} catch (EmptyFieldException e) {
					
					e.mostraPopup();
					System.out.println(e.toString());

				} catch (WrongFieldException e) {

					e.mostraPopup();
					System.out.println(e.toString());

				}

			}

		};

		view.getConfirmButton().addActionListener(confirm);

	}

}
