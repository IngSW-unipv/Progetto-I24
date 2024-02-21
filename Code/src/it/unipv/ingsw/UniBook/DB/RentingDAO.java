package it.unipv.ingsw.UniBook.DB;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.toedter.calendar.JDateChooser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;

import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.Renting;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.Model.Resource;
import it.unipv.ingsw.UniBook.Model.SingletonManager;

public class RentingDAO implements IRentingDAO {

	private String schema;
	private Connection conn;
	
	public RentingDAO() {
		super();
		this.schema = "unibook";
	}
	
	@Override
	public ArrayList<Renting> selectRentingFromUser(User u) {
		ArrayList<Renting> result = new ArrayList<>();
		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;
		try {
			st1 = conn.createStatement();
			String query = 	"select Nome, DataInizio, Durata" + 
							"from (affitto join risorsa on affitto.ID_Risorsa = risorsa.ID)"+
							"where Matricola = '"+u.getId()+"'";

			rs1 = st1.executeQuery(query);

			while (rs1.next()) {
				result.add(new Renting());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	@Override
	public boolean InsertRenting(Renting r) {
		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;

		boolean esito = true;

		try {

			String query = "INSERT INTO `unibook`.`affitto` (`ID_Risorsa`, `Matricola`, `DataInizio`, `DataFine`, `costo`) "
					+ " VALUES(?,?,?,?,?)";
			st1 = conn.prepareStatement(query);
			st1.setInt(1, r.getResource().getId());
			st1.setString(2,r.getU().getId());
			st1.setString(3,convertDateToMysqlDate(r.getStartDate()));
			LocalDate date1 = LocalDate.parse(r.getStartDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		    LocalDate date2 = LocalDate.parse(r.getEndDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		    System.out.println("Ciao");
		    int daysBetween = (int)ChronoUnit.DAYS.between(date1, date2);
		    st1.setString(4, convertDateToMysqlDate(r.getEndDate()));
		    st1.setDouble(5, daysBetween*r.getResource().getPrezzo());
			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public Renting getRenting(User u, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteSelectedRenting(Renting r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Renting> getAlreadyPresentRenting(Renting r) {
		// TODO Auto-generated method stub
		return null;
	}

	public String convertDateToMysqlDate(String date) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date parsedDate = inputFormat.parse(date);
			return outputFormat.format(parsedDate);
		}catch(ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String convertMysqlDatetoDate(String date) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date parsedDate = inputFormat.parse(date);
			return outputFormat.format(parsedDate);
		}catch(ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
