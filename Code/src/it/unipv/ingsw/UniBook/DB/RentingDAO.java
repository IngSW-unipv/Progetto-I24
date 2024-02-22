package it.unipv.ingsw.UniBook.DB;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import it.unipv.ingsw.UniBook.Exception.*;
import it.unipv.ingsw.UniBook.Model.Renting;
import java.sql.Connection;
import java.util.ArrayList;

import it.unipv.ingsw.UniBook.Model.User;

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
		    st1.setString(4, convertDateToMysqlDate(r.getEndDate()));
		    System.out.println(r.getPrice()+"/t"+r.getTotalPrice());
		    st1.setDouble(5, r.getTotalPrice());
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
	
	public Boolean checkAvailability(Renting r) throws ResourceAlreadyRentedException,DatabaseException {
		boolean available = false;
		conn = DBConnection.startConnection(conn, schema);
		ResultSet rs1;
		PreparedStatement st1;
		try {
			String query = "select * from unibook.affitto where ID_Risorsa = ? and ((DataInizio <= ? AND DataFine >= ?)OR(DataInizio <= ? AND DataFine >= ?))";
			
			st1 = conn.prepareStatement(query);
			st1.setInt(1,r.getResource().getId());
			String date =convertDateToMysqlDate(r.getStartDate());
			st1.setString(2, date);
			st1.setString(3,date);
			date = convertDateToMysqlDate(r.getEndDate());
			st1.setString(4,date);
			st1.setString(5,date);
			rs1 = st1.executeQuery();
			
			if(!rs1.next()) {
				available = true;
			}
			else {
				throw new ResourceAlreadyRentedException(convertMysqlDatetoDate(rs1.getString(3)),convertMysqlDatetoDate(rs1.getString(4)));
			}
			
		}catch(ResourceAlreadyRentedException e) {
			throw e;
		}catch(SQLException e) {
			throw new DatabaseException();
		}
		DBConnection.closeConnection(conn);
		
		return available;
	}
	
	
}
