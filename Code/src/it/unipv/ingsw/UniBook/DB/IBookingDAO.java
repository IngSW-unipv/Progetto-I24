package it.unipv.ingsw.UniBook.DB;

import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.Model.Resource;

public interface IBookingDAO {

	public boolean insertPrenotazione(Booking p, User u);
	
	public String getIDbyName(Resource r);
	
}
