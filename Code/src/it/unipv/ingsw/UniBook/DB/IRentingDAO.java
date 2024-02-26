package it.unipv.ingsw.UniBook.DB;

import java.util.ArrayList;

import it.unipv.ingsw.UniBook.Exception.DatabaseException;
import it.unipv.ingsw.UniBook.Exception.ResourceAlreadyRentedException;
import it.unipv.ingsw.UniBook.Model.*;

public interface IRentingDAO {

	public ArrayList<Renting> selectRentingFromUser(User u);
	public boolean InsertRenting(Renting r);
	public boolean ModifyRenting(Renting r);
	public Boolean checkAvailability(Renting r,int rows)throws ResourceAlreadyRentedException,DatabaseException;
	
	
}