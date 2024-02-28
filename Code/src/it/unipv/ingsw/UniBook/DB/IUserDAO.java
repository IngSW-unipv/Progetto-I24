package it.unipv.ingsw.UniBook.DB;

import java.util.List;

import it.unipv.ingsw.UniBook.Model.User;

public interface IUserDAO {

	public boolean insertUser(User u);
	public String selectPassword(User u);
	public List<User> getUsersFromDatabase();
	public boolean deleteUser(User u);
	public User selectUserByMatricola(User u);
	public String selectMatricola(User u);
	
	
}
