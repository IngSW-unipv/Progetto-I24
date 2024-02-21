package it.unipv.ingsw.UniBook.DB;

import java.util.ArrayList;

import it.unipv.ingsw.UniBook.Model.*;

public interface IRentingDAO {

	public ArrayList<Renting> selectRentingFromUser(User u);
	public boolean InsertRenting(Renting r);
	public Renting getRenting(User u, int index);
	public boolean deleteSelectedRenting(Renting r);
	public ArrayList<Renting> getAlreadyPresentRenting(Renting r);
	
	
}