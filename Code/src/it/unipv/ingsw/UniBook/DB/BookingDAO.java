package it.unipv.ingsw.UniBook.DB;

import java.util.Date;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import it.unipv.ingsw.UniBook.Model.Booking;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.Model.Resource;

public class BookingDAO implements IBookingDAO {

	private String schema;
	private Connection conn;

	public BookingDAO() {
		super();
		this.schema = "unibook";
	}

	public ArrayList<Booking> selectBookingFromUser(User u) {
		ArrayList<Booking> result = new ArrayList<>();
		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try {
			
			st1 = conn.createStatement();
			String query = "select Nome, DataOra, tempo"
					+ " from (prenotazione join risorsa on prenotazione.ID_Risorsa = risorsa.ID)"
					+ " where Matricola = '"+u.getId()+"'";
			
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {
				
				String dateTimeString = rs1.getString(2);  
				String[] r = parseDateTimeToString(dateTimeString);
	            
				Resource r1= new Resource();
				r1.setNome(rs1.getString(1));
				
				result.add(new Booking(r1, u, r[0], r[1], Integer.parseInt(rs1.getString(3))));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	public boolean insertBooking(Booking b, User u) {

		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;

		boolean esito = true;

		try {
			//String dateTime = parseStringToDateTime(b.getDate(),b.getTime());

			String query = "INSERT INTO `unibook`.`prenotazione` (`ID_Risorsa`, `Matricola`, `DataOra`, `tempo`) "
					+ " VALUES(?,?,?,?)";
			st1 = conn.prepareStatement(query);

			st1.setInt(1, b.getR().getId());
			st1.setString(2, u.getId());

			// Per adattare i formati
			
			SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dataOraFormatted = b.getDate() + " " + b.getTime();
			java.util.Date parsedDate = inputDateFormat.parse(dataOraFormatted);
			st1.setString(3, outputDateFormat.format(parsedDate));
			

			st1.setInt(4, b.getDuration());

			
			
			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}

	public String getIDbyName(Resource r) {

		String nome = r.getName();

		String result = new String();

		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT ID FROM unibook.risorsa " + "WHERE Nome = '" + nome + "'";
			rs1 = st1.executeQuery(query);
			if (rs1.next()) {
				result = rs1.getString("ID");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	public Booking getBooking(User u, int index) {
		//Booking result = new Booking();
		Booking result = null;
		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try {
			
			st1 = conn.createStatement();
			String query = "SELECT ID_Risorsa, Matricola, DataOra, tempo FROM unibook.Prenotazione "
							+ "Where Prenotazione.matricola = '"+u.getId()+"'"
							+ "LIMIT 1 OFFSET " + (index);
			rs1 = st1.executeQuery(query);

			if (rs1.next()) {
				
				Resource r1= new Resource();
				r1.setId(Integer.parseInt(rs1.getString(1)));
				
				String dateTimeString = rs1.getString(3);  
				String[] r = parseDateTimeToString(dateTimeString);
				
				int tempo = Integer.parseInt(rs1.getString(4));
				
				result = new Booking(r1, u, r[0], r[1], tempo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	public boolean deleteSelectedBooking(Booking b) {

		boolean esito = true;

		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;

		try {
			
			String dateTime = parseStringToDateTime(b.getDate(),b.getTime());
			
			String query = "DELETE FROM unibook.prenotazione WHERE ID_Risorsa = ? AND Matricola = ? AND DataOra = ? AND tempo = ?";
	        st1 = conn.prepareStatement(query);

	        // Imposta i parametri nella query
	        st1.setInt(1, b.getR().getId());
	        st1.setString(2, b.getU().getId());
	        st1.setString(3, dateTime);
	        st1.setInt(4, b.getDuration());
	        
			
			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}
	
	
	
	 private String[] parseDateTimeToString(String dateTimeString) {
		 
	        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

	        try {
	            Date dateTime = inputFormat.parse(dateTimeString);
	            String date = dateFormat.format(dateTime);
	            String time = timeFormat.format(dateTime);
	            return new String[]{date, time};
	        } catch (ParseException e) {
	            e.printStackTrace();
	            return null; // Gestione dell'errore
	        }
	    }
	 
	 
	 private String parseStringToDateTime(String date, String time) {
		    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		    String dataOraFormatted = date + " " + time;
		    try {
		        Date parsedDate = inputDateFormat.parse(dataOraFormatted);
		        return inputDateFormat.format(parsedDate);
		    } catch (ParseException e) {
		        e.printStackTrace();
		        return null; // O gestisci l'eccezione in altro modo
		    }
		}

}
