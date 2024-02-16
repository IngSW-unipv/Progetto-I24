package it.unipv.ingsw.UniBook.Test;

import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.Model.Login;
import it.unipv.ingsw.UniBook.Model.Registration;
import it.unipv.ingsw.UniBook.Model.SingletonManager;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsw.UniBook.Exception.EmptyFieldException;
import it.unipv.ingsw.UniBook.Exception.WrongFieldException;

public class AuthenticationTest {

	private User u[];

	@Before
	public void initTest() {
		u = new User[3];
	}
	
	@Test
	public void testLoginOk() {

		u[0] = new User("S500816", "000");
		u[1] = new User("P501934", "234");
		u[2] = new User("R509822", "345");

		Login l;

		for (User user : u) {
			l = new Login(SingletonManager.getInstance().getUserDAO().selectUserByMatricola(user));
			assertTrue(l.login());
		}

	}

	@Test
	public void testLoginNotOk() {

		u[0] = new User("S000000", "000");
		u[1] = new User("P5019343213QA", "999");
		u[2] = new User("R509822", "222");

		Login l;

		for (User user : u) {
			l = new Login(SingletonManager.getInstance().getUserDAO().selectUserByMatricola(user));
			assertFalse(l.login());
		}

	}

	@Test
	public void testRegistrationOk() {

		//Metto le x perché ho il controllo sui campi vuoti, altrimenti se metto null da errore
		u[0] = new User("S000000", "test", "test", "Studente", "test", "test", "000");
		u[1] = new User("P111111", "test", "test", "Professore", "test", "test", "ciao");
		u[2] = new User("R222222", "test", "test", "Ricercatore", "test", "test", "a1b2c3");

		Registration r;

		for (User user : u) {
			r = new Registration(user);
			assertTrue(r.register(user.getPassword()));
			}
		clean();
	}

	@Test
	public void testRegistrationNotOk() {

		u[0] = new User("P000000", "test", "test", "Studente", "test", "test", "000");
		u[1] = new User("", "test", "test", "Professore", "test", "test", "ciao");
		u[2] = new User("R2232142222", "test", "test", "Ricercatore", "test", "test", "a1b2c3");

		Registration r;

		for (User user : u) {
			r = new Registration(user);
			assertFalse(r.register(user.getPassword()));
			}
		
		

	}
	
	//Rimuovo gli utenti inseriti nel testOk
	public void clean() {
		for (User user : u) {
			//Per rimuovere dal DB gli user appena inseriti
			System.out.println(user.getId());
			SingletonManager.getInstance().getUserDAO().deleteUser(user); 
		}
	}
	
	

}