package model;

import java.util.ArrayList;

import database.BookingDAO;
import database.UserDAO;

public class ModelManagerSingleton {

	private static ModelManagerSingleton jModelManager;
	
	private User loggeduser;
	
	private ArrayList<User> utenti;
	private ArrayList<Booking> bookinglist;
	
	private UserDAO userdao;
	private BookingDAO bookingdao;
	
	private ModelManagerSingleton() {
		
		this.loggeduser = null;
		
		utenti = new ArrayList<User>();
		bookinglist = new ArrayList<Booking>();
		
		userdao = new UserDAO();
		bookingdao = new BookingDAO();
		
		popolaUser(); //a inizio programma prendo utenti da DB e carico nell'array
		popolaBooking();
		
	}
	
	//singleton significa che puoi creare solo un'istanza di quella classe
	public static ModelManagerSingleton getIstance() {
		
		if(jModelManager==null) {
			jModelManager = new ModelManagerSingleton();
		}
		
		return jModelManager;
		
	}
	
	public void setLoggedUser(User u) {
		this.loggeduser = u;
	}
	
	public User getLoggedUser() {
		return loggeduser;
	}
	
	public void addUser(User u) {
		
		utenti.add(u);
		
	}
	
	public void popolaUser() {
		
		ArrayList<User> temp = userdao.selectUtenti();
		
		for(User u: temp) {
			utenti.add(u);
		}
		
	}
	
	public void popolaBooking() {
		
		ArrayList<Booking> temp = bookingdao.selectBooking(this);
		
		for(Booking b: temp) {
			bookinglist.add(b);
		}
		
	}
	
	public void addBooking(Booking b) {
		bookinglist.add(b);
		bookingdao.insertBooking(b);
	}
	
	public ArrayList<Booking> getBookingList(){
		return bookinglist;
	}
	
	public User checkCredentials(String nome, String password) {
		
		User temp=null;
		
		for(User u: utenti) {
			
			if((nome.equals(u.getNome())) && (password.equals(u.getPassword()))){
				temp = u;
				break;
			}
			
		}
		
		return temp;
		
	}
	
	public Professor getProfByUser(String s) { // ottengo prof sapendo il nome 
		
		Professor temp=null;
		
		for(User u: utenti) {
			
			if(u.isProfessor() && s.equals(u.getNome())) {
				temp = (Professor) u;
				break;
			}
			
		}
		
		return temp;
		
	}
	
	public Student getStudentByUser(String s) { // ottengo prof sapendo il nome 
		
		Student temp=null;
		
		for(User u: utenti) {
			
			if(u.isStudent() && s.equals(u.getNome())) {
				temp = (Student) u;
				break;
			}
			
		}
		
		return temp;
		
	}
	
	public void acceptBooking(Booking b) {
		b.setAccepted(true);
		bookingdao.updateBooking(b);
	}
	
}
