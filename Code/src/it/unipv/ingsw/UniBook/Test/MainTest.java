package it.unipv.ingsw.UniBook.Test;
import it.unipv.ingsw.UniBook.Test.AuthenticationTest;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    
	    	AuthenticationTest t = new AuthenticationTest();
	    	
	        // Test Autenticazione
	        t.testLoginOk();
	        t.testLoginNotOk();
	        t.testRegistrationOk();
	        t.testRegistrationNotOk();

	    
		
	}

}
