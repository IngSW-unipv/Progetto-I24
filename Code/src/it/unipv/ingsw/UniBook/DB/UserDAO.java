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

			st1.setString(1, u.getId());
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

		String matricola = u.getId();

		String result = new String();

		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT Password FROM unibook.utente " + "WHERE Matricola= '" + matricola + "'";

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

	public String selectMatricola(User u) {

		String matricola = u.getId();

		String result = new String();

		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT Matricola FROM unibook.utente " + "WHERE Matricola= '" + matricola + "'";

			rs1 = st1.executeQuery(query);

			if (rs1.next()) {
				result = rs1.getString("Matricola");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	public User selectUserByMatricola(User u) {

		String matricola = u.getId();
		String pw = u.getPassword();
		User result = null;

		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT * FROM unibook.utente WHERE matricola= '" + matricola + "'"
					+ "and utente.Password='"+pw+"'";

			rs1 = st1.executeQuery(query);

			if (rs1.next()) {

				String nome = rs1.getString("nome");
				String cognome = rs1.getString("cognome");
				String tipo = rs1.getString("tipo");
				String email = rs1.getString("email");
				String corso = rs1.getString("corso");
				String password = rs1.getString("password");
				result = new User(matricola, nome, cognome, tipo, email, corso, password);
			}else {
				return u;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	//Metodo utile nel test della registrazione
	public boolean deleteUser(User u) {

		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;

		boolean esito = true;

		try {
			
			
			String query = "DELETE FROM `unibook`.`utente` WHERE (`Matricola` = '"+u.getId()+"')";
					
			st1 = conn.prepareStatement(query);

			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}

}
