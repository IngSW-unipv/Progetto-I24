package it.unipv.ingsw.UniBook;

import it.unipv.ingsw.UniBook.Controller.RegistrationController;
import it.unipv.ingsw.UniBook.View.RegistrationView;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.User;
import javax.swing.JFrame;

public class Start {

	public static void main(String[] args) {

		User model = SingletonManager.getInstance().getLoggedUser();
		RegistrationView view = new RegistrationView();
		RegistrationController c = new RegistrationController(view, model);

		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setVisible(true);

	}

}
