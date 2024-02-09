package it.unipv.ingsw.UniBook.DB;

import java.util.ArrayList;

import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.Model.Resource;

public interface IBookingDAO {

	public boolean insertBooking(Booking p, User u);
	
	public String getIDbyName(Resource r);
	
	public boolean deleteSelectedBooking(Booking b);
	
	public Booking getBooking(User u, int index);
	
	public ArrayList<Booking> selectBookingFromUser(User u);
	
	public boolean chechAvilability(Booking b);

	
}
