package controller;

import model.Booking;
import model.ModelManagerSingleton;
import model.Professor;
import model.Student;
import view.BookingView;
import view.MainFrame;
import view.ManageOfficeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookingController {
    private Booking model;
    private BookingView view;

    public BookingController() {

    }

    public static void addListeners(ModelManagerSingleton m, MainFrame view) {
    	
    	// listener bottone indietro
    	ActionListener indietro = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				manageAction();
				
			}
			
			private void manageAction() {
				
				view.changeView("2");
				
			}
	    	  
	    };
	    
	    view.getBookingView().getIndietrobutton().addActionListener(indietro);
	    
	    view.getManageOfficeView().getIndietro().addActionListener(indietro);
	    
	    
	    ActionListener invia = new ActionListener() {
// listener invia bottone prenota
	    	String date, time, prof;
	    	
			@Override
			public void actionPerformed(ActionEvent e) {

				date=(String) view.getBookingView().getInput("date").getText();
				time=view.getBookingView().getInput("time").getText();
				
				prof=view.getBookingView().getInput("professor").getText();
				
				manageAction();
				
			}
			
			private void manageAction() {
				// cerca tra tutti gli utenti un professore=prof
				Professor temp=m.getProfByUser(prof);
				
				if(temp==null) {

					view.getBookingView().setErrorText("Professore non esistente");
				}else {
					m.addBooking(new Booking(temp,date,time,(Student)m.getLoggedUser()));
					view.getBookingView().setErrorText("Prenotazione aggiunta");
					
					view.getBookingView().getInput("date").setText("");
					view.getBookingView().getInput("time").setText("");
					view.getBookingView().getInput("professor").setText("");
			
				}
				
				
			}
	    	  
	    };
	    
	    view.getBookingView().getInviabutton().addActionListener(invia);
	    
	    
	    ActionListener conferma = new ActionListener() {

	    	String nome;
	    	
			@Override
			public void actionPerformed(ActionEvent e) {

				nome=view.getManageOfficeView().getNomeStudent().getText();
				manageAction();
				
			}
			
			private void manageAction() {
				// recupero lista di tutte le prenotazioni
				ArrayList<Booking> bookinglist = m.getBookingList();
				
				for(Booking b: bookinglist) {
					//controllo se professore loggato stesso della prenot. e se nome inserito da prof=nome studente
					if(b.getProfessor()==m.getLoggedUser() && nome.equals(b.getStudent().getNome())) {
						m.acceptBooking(b); //
					}
					
				}
				
				String s="";
				
				for(Booking b: bookinglist) {
					if(m.getLoggedUser()!=b.getProfessor()) { //se utente loggato non è professore salta
						continue;
					}
					s=s+"- date: "+b.getDate()+" time: "+b.getTime()+" student: "+b.getStudent().getNome()+" accepted: "+b.getIsAccepted()+"<br>";
				}
				
				view.getManageOfficeView().getBookingsLabel().setText("<html>"+s+"</html>");
				
				
			}
	    	  
	    };
	    
	    view.getManageOfficeView().getConfirmButton().addActionListener(conferma);
	    
    	
    }
}
