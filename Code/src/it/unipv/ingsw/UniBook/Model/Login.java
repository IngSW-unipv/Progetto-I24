package it.unipv.ingsw.UniBook.Model;

import javax.swing.JFrame;
import it.unipv.ingsw.UniBook.Controller.HomeController;
import it.unipv.ingsw.UniBook.DB.UserDAO;
import it.unipv.ingsw.UniBook.Exception.*;
import it.unipv.ingsw.UniBook.View.HomeView;

public class Login extends Authentication {

	private UserDAO uDAO;

	public Login(User u) {
		super(u);
		this.uDAO = SingletonManager.getInstance().getUserDAO();
	}

	public boolean login() {

		boolean success = false;

		try {
			
			fieldCheck();
						
			passwordCheck();

			setTypeOfUser();

			success = true;

			HomeView f = new HomeView();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);

			HomeController controller = new HomeController(f);

		} catch (EmptyFieldException e) {

			e.mostraPopup();
			System.out.println(e.toString());

		} catch (WrongFieldException e) {

			e.mostraPopup();
			System.out.println(e.toString());

		}

		return success;

	}

	public void fieldCheck() throws EmptyFieldException {
		if (this.u.getId().isEmpty() == true || this.u.getPassword().isEmpty() == true) {
			throw new EmptyFieldException();
		}
	}

	public void passwordCheck() throws WrongFieldException {

		if (!u.getPassword().equals(uDAO.selectPassword(u))) {
			throw new WrongFieldException();
		}

	}

}
