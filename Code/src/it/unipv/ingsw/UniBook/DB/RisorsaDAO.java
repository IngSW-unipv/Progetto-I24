package it.unipv.ingsw.UniBook.DB;

import it.unipv.ingsw.UniBook.Model.Risorsa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RisorsaDAO implements IRisorsaDAO {

	private String schema;
	private Connection conn;

	public RisorsaDAO() {
		super();
		this.schema = "unibook";
//			conn=DBConnection.startConnection(conn,schema);
	}

	@Override
	public ArrayList<Risorsa> selectAll() {

		ArrayList<Risorsa> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT * from RISORSA";
			rs1 = st1.executeQuery(query);

			while (rs1.next()) {
				Risorsa r = new Risorsa(rs1.getString(1), rs1.getString(2), rs1.getString(3));

				result.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}

	public boolean insertRisorsa(Risorsa r) {

		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;

		boolean esito = true;

		try {
			String query = "INSERT INTO FORNITORI (NOME,DESCRIZIONE,TIPO) VALUES(?,?,?)";
			st1 = conn.prepareStatement(query);
			st1.setString(1, r.getNome());
			st1.setString(2, r.getDescrizione());
			st1.setString(3, r.getTipo());

			st1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			esito = false;
		}

		DBConnection.closeConnection(conn);
		return esito;

	}

}
