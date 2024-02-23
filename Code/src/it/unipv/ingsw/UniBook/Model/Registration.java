package it.unipv.ingsw.UniBook.Model;

import javax.swing.JFrame;

import it.unipv.ingsw.UniBook.Controller.HomeController;
import it.unipv.ingsw.UniBook.Exception.*;
import it.unipv.ingsw.UniBook.View.HomeView;

public class Registration extends Authentication{

	public Registration(User u) {
		super(u);
	}

	public boolean register(String ConfirmPassword) {

		boolean success = false;
		
		try{
			
			idCheck();
			accountCheck();
			fieldCheck(ConfirmPassword);
			passwordCheck(ConfirmPassword);
			successfulOperationCheck();
			
			setTypeOfUser();
			
			success = true;
			
			HomeView f = new HomeView();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);

			HomeController controller = new HomeController(f);

			
		}	catch (EmptyFieldException e) {
			
			e.showPopup();
			System.out.println(e.toString());

		} catch (WrongFieldException e) {
			
			e.showPopup();
			System.out.println(e.toString());

		} catch (DatabaseException e) {
			e.showPopup();
			System.out.println(e.toString());
		}
		
		catch (AccountAlreadyExistsException e) {
			e.showPopup();
			System.out.println(e.toString());
		}
		
		return success;
	}

	private void fieldCheck(String password) throws EmptyFieldException {
		if (this.u.getId().isEmpty() || this.u.getNome().isEmpty() || this.u.getCognome().isEmpty()
				|| this.u.getEmail().isEmpty() || this.u.getCorso().isEmpty()
				|| String.valueOf(this.u.getPassword()).equals("") || password.equals("")) {
			throw new EmptyFieldException();
		}
	}

	// Controlla che se un utente inserisce la matricola con S, sia effettivaemente
	// uno studente..
	private void idCheck() throws WrongFieldException {

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

	private void passwordCheck(String password) throws WrongFieldException {

		if (!String.valueOf(this.u.getPassword()).equals(password))
			throw new WrongFieldException();
	}

	private void successfulOperationCheck() throws DatabaseException {

		boolean inserimentoRiuscito = SingletonManager.getInstance().getUserDAO().insertUser(u);

		if (!inserimentoRiuscito)
			throw new DatabaseException();

	}
	
	private void accountCheck() throws AccountAlreadyExistsException {
		if(this.u.getId().equals(SingletonManager.getInstance().getUserDAO().selectMatricola(u))) {	
			throw new AccountAlreadyExistsException();
		}
	}

}
