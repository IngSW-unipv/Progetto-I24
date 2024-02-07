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
				String[] r = parseDateTimetoString(dateTimeString);
	            
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

	public boolean insertBooking(Booking p, User u) {

		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;

		boolean esito = true;

		try {
			String query = "INSERT INTO `unibook`.`prenotazione` (`ID_Risorsa`, `Matricola`, `DataOra`, `tempo`) "
					+ " VALUES(?,?,?,?)";
			st1 = conn.prepareStatement(query);

			st1.setInt(1, p.getR().getId());
			st1.setString(2, u.getId());

			// Per adattare i formati
			SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dataOraFormatted = p.getDate() + " " + p.getTime();
			java.util.Date parsedDate = inputDateFormat.parse(dataOraFormatted);
			st1.setString(3, outputDateFormat.format(parsedDate));

			st1.setInt(4, p.getDuration());

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
	
	/*public boolean deleteSelectedBooking(User u, int index) {

		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;

		boolean esito = true;

		try {
			String query = "INSERT INTO `unibook`.`prenotazione` (`ID_Risorsa`, `Matricola`, `DataOra`, `tempo`) "
					+ " VALUES(?,?,?,?)";
			st1 = conn.prepareStatement(query);

			st1.setInt(1, p.getR().getId());
			st1.setString(2, u.getId());

			// Per adattare i formati
			SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dataOraFormatted = p.getDate() + " " + p.getTime();
			java.util.Date parsedDate = inputDateFormat.parse(dataOraFormatted);
			st1.setString(3, outputDateFormat.format(parsedDate));

			st1.setInt(4, p.getDuration());

			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}
	*/
	
	 public static String[] parseDateTimetoString(String dateTimeString) {
		 
	        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

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


}
