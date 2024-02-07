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

		this.u.setId(u.getId());
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
		if (this.u.getId().isEmpty() || this.u.getNome().isEmpty() || this.u.getCognome().isEmpty()
				|| this.u.getEmail().isEmpty() || this.u.getCorso().isEmpty()
				|| String.valueOf(this.u.getPassword()).equals("") || password.equals("")) {
			throw new EmptyFieldException();
		}
	}

	// Controlla che se un utente inserisce la matricola con S, sia effettivaemente
	// uno studente..
	public void matricolaCompatibileCheck() throws WrongFieldException {

		// Verifica formato matricola
		if (this.u.getId().matches("[SPR]\\d{6}")) {
		
			char tipoMatricola = this.u.getId().charAt(0);
			
			switch (tipoMatricola) {
			case 'S':
				if (!this.u.getTipo().toLowerCase().contains("studente")) {
					throw new WrongFieldException();
				}
				break;
			case 'P':
				if (!this.u.getTipo().toLowerCase().contains("professore")) {
					throw new WrongFieldException();
				}
				break;
			case 'R':
				if (!this.u.getTipo().toLowerCase().contains("ricercatore")) {
					throw new WrongFieldException();
				}
				break;
			default:
				// Tipo non riconosciuto
				throw new WrongFieldException();
			}
		} else {
			// Il formato della matricola non è valido
			throw new WrongFieldException();
		}

	}

	public void passwordCheck(String password) throws WrongFieldException {

		if (!String.valueOf(this.u.getPassword()).equals(password))
			throw new WrongFieldException();
	}

	public void succesfulOperationCheck() throws DatabaseException {

		boolean inserimentoRiuscito = uDAO.insertUser(u);

		if (!inserimentoRiuscito)
			throw new DatabaseException();

	}
	
	public void accountCheck() throws AccountAlreadyExistsException {
		if(this.u.getId().equals(uDAO.selectMatricola(u))) {	
			throw new AccountAlreadyExistsException();
		}
	}

}
