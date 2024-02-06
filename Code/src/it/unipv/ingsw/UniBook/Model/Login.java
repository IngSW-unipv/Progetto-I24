package it.unipv.ingsw.UniBook.Model;

import javax.swing.JFrame;
import it.unipv.ingsw.UniBook.Controller.HomeController;
import it.unipv.ingsw.UniBook.DB.UserDAO;
import it.unipv.ingsw.UniBook.Exception.*;
import it.unipv.ingsw.UniBook.View.HomeView;

public class Login{

	private User u;
	private UserDAO uDAO;

	public Login(User u) {
		//this.u = new User();
		this.u = SingletonManager.getInstance().getLoggedUser();
		//this.u.setMatricola(u.getMatricola());
		//this.u.setPassword(u.getPassword());
		this.uDAO = SingletonManager.getInstance().getUserDAO();

	}

	public void login() {

		HomeView f = new HomeView();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		HomeController controller = new HomeController(f);

	}

	public void fieldCheck() throws EmptyFieldException {
		if (this.u.getMatricola().isEmpty() == true || this.u.getPassword().isEmpty() == true) {
			throw new EmptyFieldException();
		}
	}

	public void passwordCheck() throws WrongFieldException {

		if (!u.getPassword().equals(uDAO.selectPassword(u))) {
			throw new WrongFieldException();
		}

	}


}
