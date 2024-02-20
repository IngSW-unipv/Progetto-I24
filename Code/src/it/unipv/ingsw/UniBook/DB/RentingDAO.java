package it.unipv.ingsw.UniBook.DB;

import java.util.Date;
import java.text.SimpleDateFormat;
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
		// TODO Auto-generated method stub
		return false;
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

}
