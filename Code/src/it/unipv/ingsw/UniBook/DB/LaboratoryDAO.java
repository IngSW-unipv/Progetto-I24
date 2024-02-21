package it.unipv.ingsw.UniBook.DB;

import it.unipv.ingsw.UniBook.Model.Resource;
import it.unipv.ingsw.UniBook.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LaboratoryDAO {

	private String schema;
	private Connection conn;

	public LaboratoryDAO() {
		super();
		this.schema = "unibook";
	}

	public ArrayList<Resource> selectAllLaboratory() {
		ArrayList<Resource> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT * FROM unibook.laboratorio";

			rs1 = st1.executeQuery(query);

			while (rs1.next()) {

				int idLab = Integer.parseInt(rs1.getString(1));
				String nomeLab = rs1.getString(3);
				result.add(new Resource(idLab, nomeLab, null, 0, null, null, 0, null));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	public int getCapacity(Resource r) {

		int result = 0;

		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT N_posti FROM unibook.laboratorio " + "WHERE ID = '" + r.getId() + "'";
			rs1 = st1.executeQuery(query);
			if (rs1.next()) {
				result = Integer.parseInt(rs1.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;

	}

}
