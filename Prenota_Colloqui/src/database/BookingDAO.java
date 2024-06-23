package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Booking;
import model.ModelManagerSingleton;
import model.Professor;
import model.Student;
import model.User;


public class BookingDAO{

	private String schema;
	private Connection conn;


	public BookingDAO() {
		super();
		this.schema = "unibook";

	}

	public ArrayList<Booking> selectBooking (ModelManagerSingleton m)
	{
		ArrayList<Booking> result = new ArrayList<>();

		conn=DBConnection.startConnection(conn,schema);
		Statement st1;
		ResultSet rs1;

		try
		{
			st1 = conn.createStatement();
			String query="SELECT prof,DataOra,tempo,Matricola,accepted from prenotazione";
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				Student stemp = m.getStudentByUser(rs1.getString(4));
				Professor ptemp = m.getProfByUser(rs1.getString(1));
				
				boolean ac=false;
				
				if(rs1.getInt(5)==1) {
					ac=true;
				}
				
				Booking f=new Booking(ptemp, rs1.getString(2), rs1.getString(3), stemp,ac);

				result.add(f);
			}
		}catch (Exception e){e.printStackTrace();}
		
		
		DBConnection.closeConnection(conn);
		return result;
		
	}
	
	public void insertBooking(Booking f) {
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		
		boolean esito=true;

		try
		{

			String query="INSERT INTO prenotazione (Matricola,DataOra,tempo,prof,accepted) VALUES(?,?,?,?,?)";
			st1 = conn.prepareStatement(query);
			st1.setString(1, f.getStudent().getNome());
			st1.setString(2,f.getDate());
			st1.setString(3, f.getTime());
			st1.setString(4,f.getProfessor().getNome());
			st1.setInt(5, 0);
			
			st1.executeUpdate();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DBConnection.closeConnection(conn);
		
	}
	
	public void updateBooking(Booking f) {
		
		conn=DBConnection.startConnection(conn,schema);
		PreparedStatement st1;
		
		boolean esito=true;

		try
		{
			
			String query="UPDATE prenotazione SET accepted=1 WHERE Matricola=? AND prof=?";
			st1 = conn.prepareStatement(query);
			st1.setString(1, f.getStudent().getNome());
			st1.setString(2,f.getProfessor().getNome());
			
			st1.executeUpdate();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DBConnection.closeConnection(conn);
		
	}	
	
	
}
