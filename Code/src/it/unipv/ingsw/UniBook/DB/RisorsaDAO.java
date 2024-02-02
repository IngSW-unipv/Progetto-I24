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
	public ArrayList<String> selectAll() {
	    ArrayList<String> result = new ArrayList<>();

	    conn = DBConnection.startConnection(conn, schema);
	    Statement st1;
	    ResultSet rs1;

	    try {
	        st1 = conn.createStatement();
	        String query = "SELECT Nome FROM unibook.risorsa " +
	                	   "WHERE Tipo='P'";

	        rs1 = st1.executeQuery(query);

	        while (rs1.next()) {
	            String nomeRisorsa = rs1.getString("NOME");
	            result.add(nomeRisorsa);
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
			String query = "INSERT INTO RISORSA (NOME,DESCRIZIONE,TIPO) VALUES(?,?,?)";
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
