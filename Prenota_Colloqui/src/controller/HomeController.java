package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Booking;
import model.ModelManagerSingleton;
import model.Professor;
import model.User;
import view.BookingView;
import view.HomeView;
import view.MainFrame;
import view.ManageOfficeView;

public class HomeController {

    public HomeController(HomeView view) {

    }

    public static void addListeners(ModelManagerSingleton m, MainFrame view) {
    	
    	ActionListener booking = new ActionListener() { // prenotazione colloqui

			@Override
			public void actionPerformed(ActionEvent e) {

				manageAction();
				
			}
			
			private void manageAction() {
				
				if(m.getLoggedUser().isStudent()) {
					view.changeView("3");
				}else {
					view.getHomeView().setErrorText("Non hai i permessi necessari");
				}
				
			}
	    	  
	    };
	    
	    view.getHomeView().getButtonBookOfficeHours().addActionListener(booking); //gestione colloqui
    	
	    
	    ActionListener gestione = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				manageAction();
				
			}
			
			private void manageAction() {
				
				if(m.getLoggedUser().isProfessor()) {
					
					ArrayList<Booking> bookinglist = m.getBookingList();
					
					String s="";
					
					for(Booking b: bookinglist) {
						if(m.getLoggedUser()!=b.getProfessor()) {
							continue;
						}
						s=s+"- date: "+b.getDate()+" time: "+b.getTime()+" student: "+b.getStudent().getNome()+" accepted: "+b.getIsAccepted()+"<br>";
					}
					
					view.getManageOfficeView().getBookingsLabel().setText("<html>"+s+"</html>");
					
					view.changeView("4");
				}else {
					view.getHomeView().setErrorText("Non hai i permessi necessari");
				}
				
			}
	    	  
	    };
	    
	    view.getHomeView().getButtonManageOfficeHours().addActionListener(gestione);
	    
	    
	    ActionListener logout = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				manageAction();
				
			}
			
			private void manageAction() {
				
				m.setLoggedUser(null);
				view.changeView("1");
				
			}
	    	  
	    };
	    
	    view.getHomeView().getLogoutButton().addActionListener(logout);
	    
    }
    
}

