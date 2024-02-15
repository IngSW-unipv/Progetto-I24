package it.unipv.ingsw.UniBook.DB;

import java.util.ArrayList;

import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.Model.Resource;

public interface IBookingDAO {

	public ArrayList<Booking> selectBookingFromUser(User u);
	
	public boolean insertBooking(Booking b);
	
	public String getIDbyName(Resource r);
	
	public Booking getBooking(User u, int index);
	
	public boolean deleteSelectedBooking(Booking b);
	
	public boolean chechAvilability(Booking b);

	public ArrayList<Booking> getAlreadyPresentBooking(Booking b);
	
	
}
