package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.ModelManagerSingleton;
import model.User;
import view.MainFrame;

public class AutenticationController {


	public AutenticationController() {
	}
	
	public static void addListeners(ModelManagerSingleton m, MainFrame view) {
	
		
		// listener per il bottone di login
		ActionListener loginenter = new ActionListener() {
	    	private String username,password;
	    	
			@Override
			public void actionPerformed(ActionEvent e) {
				//ottengo credenziali
				username=view.getLoginview().getInput("username").getText();
				password=view.getLoginview().getInput("password").getText();

				manageAction();
				
			}
			
			private void manageAction() {
				
				
				User temp = m.checkCredentials(username, password);
				// controllo credenziali
				view.getLoginview().getInput("username").setText("");
				view.getLoginview().getInput("password").setText("");
				
				if(temp==null) { // credenziali errate
					System.out.println("Credenziali errate"); 
					view.getLoginview().setErrorText("Credenziali Errate");
				}else { //se giuste cambio vista e imposto utente logggato
					m.setLoggedUser(temp);
					view.changeView("2");
					
				}
				
				

				
			}
	    	  
	    };
	    
	    view.getLoginview().getInviabutton().addActionListener(loginenter); //listener bottone per entrare
	
		
	}
	
}
