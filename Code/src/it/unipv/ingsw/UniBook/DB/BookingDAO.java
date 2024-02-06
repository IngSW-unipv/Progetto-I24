package it.unipv.ingsw.UniBook.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import it.unipv.ingsw.UniBook.Model.Booking;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.Model.Resource;

public class BookingDAO implements IBookingDAO {

	private String schema;
	private Connection conn;

	public BookingDAO() {
		super();
		this.schema = "unibook";
	}

	public boolean insertPrenotazione(Booking p, User u) {

		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;

		boolean esito = true;

		try {
			String query = "INSERT INTO `unibook`.`prenotazione` (`ID_Risorsa`, `Matricola`, `DataOra`, `tempo`) "
					+ " VALUES(?,?,?,?)";
			st1 = conn.prepareStatement(query);

			st1.setInt(1, p.getR().getId());
			st1.setString(2, u.getMatricola());

			// Per adattare i formati
			SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dataOraFormatted = p.getData() + " " + p.getOra();
			java.util.Date parsedDate = inputDateFormat.parse(dataOraFormatted);
			st1.setString(3, outputDateFormat.format(parsedDate));

			st1.setInt(4, p.getDurata());

			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}

	public String getIDbyName(Resource r) {

		String nome = r.getNome();

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

}
