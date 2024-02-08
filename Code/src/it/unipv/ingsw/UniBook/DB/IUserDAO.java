package it.unipv.ingsw.UniBook.DB;

import it.unipv.ingsw.UniBook.Model.User;

public interface IUserDAO {

	public boolean insertUser(User u);
	public String selectPassword(User u);
	
}
