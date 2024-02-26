package it.unipv.ingsw.UniBook.DB;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {
	
	  private static String username; 
	  private static String password; 
	  private static String dbDriver; 
	  private static String dbURL; 
	  private static DBConnection conn;
	 
	
	private static void init() {
		Properties p = new Properties(System.getProperties());
		try {
			
			username="root";
			password= "0000";
			dbDriver = "com.mysql.cj.jdbc.Driver";
			dbURL = "jdbc:mysql://127.0.0.1:3306/";
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection startConnection(Connection conn, String schema)
	{
		init();
	
		
		
		if ( isOpen(conn) )
			closeConnection(conn);
	
		try 
		{
			
			Class.forName(dbDriver);
			
			conn = DriverManager.getConnection(dbURL+schema+"?user=root?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);// Apertura connessione

		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	public static boolean isOpen(Connection conn)
	{
		if (conn == null)
			return false;
		else
			return true;
	}

	public static Connection closeConnection(Connection conn)
	{
		if ( !isOpen(conn) )
			return null;
		try 
		{

			conn.close();
			conn = null;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
		return conn;
	}
}

