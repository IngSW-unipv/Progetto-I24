package it.unipv.ingsw.UniBook.Model;

import javax.swing.JFrame;

import it.unipv.ingsw.UniBook.Controller.HomeController;
import it.unipv.ingsw.UniBook.DB.UserDAO;
import it.unipv.ingsw.UniBook.Exception.*;
import it.unipv.ingsw.UniBook.View.HomeView;

public class Registration {

	private User u;
	private UserDAO uDAO;

	public Registration(User u) {
		this.u = new User();
		
		this.u.setMatricola(u.getMatricola());
		this.u.setNome(u.getNome());
		this.u.setCognome(u.getCognome());
		this.u.setTipo(u.getTipo());
		this.u.setCorso(u.getCorso());
		this.u.setEmail(u.getEmail());
		this.u.setPassword(String.valueOf(u.getPassword()));
		
		this.uDAO = SingletonManager.getInstance().getUserDAO();

	}

	public void register() {

		
		
		HomeView f = new HomeView();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		HomeController controller = new HomeController(f);

	}

	public void fieldCheck(String password) throws EmptyFieldException {
		System.out.println(u.getMatricola());
		if (this.u.getMatricola().isEmpty() || this.u.getNome().isEmpty() || this.u.getCognome().isEmpty()
				|| this.u.getEmail().isEmpty() || this.u.getCorso().isEmpty()
				|| String.valueOf(this.u.getPassword()).equals("")
				|| password.equals("")) {
			throw new EmptyFieldException();
		}
	}

	public void passwordCheck(String password) throws WrongFieldException {

		if (!String.valueOf(this.u.getPassword()).equals(password))
			throw new WrongFieldException();
		}
	
	public void succesfulOperationCheck() throws DatabaseException{
		
		boolean inserimentoRiuscito = uDAO.insertUser(u);
		
		if (!inserimentoRiuscito)
			throw new DatabaseException();
		
	}
		
	
	
}
