package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Professor;
import model.Student;
import model.User;


public class UserDAO{

	private String schema;
	private Connection conn;


	public UserDAO() {
		super();
		this.schema = "unibook";

	}

	public ArrayList<User> selectUtenti ()
	{
		ArrayList<User> result = new ArrayList<>();

		conn=DBConnection.startConnection(conn,schema);
		Statement st1;
		ResultSet rs1;

		try
		{
			st1 = conn.createStatement();
			String query="SELECT Matricola,Password from utente WHERE Tipo='Studente'";
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				User f=new Student(rs1.getString(1), rs1.getString(2));

				result.add(f);
			}
		}catch (Exception e){e.printStackTrace();}
		
		
		try
		{
			st1 = conn.createStatement();
			String query="SELECT Matricola,Password from utente WHERE Tipo='Professore'";
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				User f=new Professor(rs1.getString(1), rs1.getString(2));

				result.add(f);
			}
		}catch (Exception e){e.printStackTrace();}
		
		
		DBConnection.closeConnection(conn);
		return result;
		
	}
	
	
}
