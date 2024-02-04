package it.unipv.ingsw.UniBook.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingsw.UniBook.Model.User;
import java.sql.Connection;

public class UserDAO implements IUserDAO {

	private String schema;
	private Connection conn;

	public UserDAO() {
		super();
		this.schema = "unibook";
	}

	public boolean insertUser(User u) {

		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;

		boolean esito = true;

		try {
			String query = "INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`)"
					+ " VALUES(?,?,?,?,?,?,?)";
			st1 = conn.prepareStatement(query);

			st1.setString(1, u.getMatricola());
			st1.setString(2, u.getNome());
			st1.setString(3, u.getCognome());
			st1.setString(4, u.getTipo());
			st1.setString(5, u.getEmail());
			st1.setString(6, u.getCorso());
			st1.setString(7, u.getPassword());

			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}
	
	public String selectPassword(User u) {
		
		String matricola = u.getMatricola();
		
	    String result = new String();

	    conn = DBConnection.startConnection(conn, schema);
	    Statement st1;
	    ResultSet rs1;

	    try {
	        st1 = conn.createStatement();
	        String query = "SELECT Password FROM unibook.utente " +
	                	   "WHERE Matricola= '"+matricola+"'";

	        rs1 = st1.executeQuery(query);


	        if (rs1.next()) {
	            result = rs1.getString("Password");
	        }
	            
	  
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    DBConnection.closeConnection(conn);
	    return result;
	}

}
