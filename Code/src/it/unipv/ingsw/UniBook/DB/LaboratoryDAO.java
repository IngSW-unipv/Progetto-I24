package it.unipv.ingsw.UniBook.DB;


	import it.unipv.ingsw.UniBook.Model.Laboratory;
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
//				conn=DBConnection.startConnection(conn,schema);
		}
	
	
	public ArrayList<String> selectAllLaboratory() {
		ArrayList<String> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT nome FROM unibook.laboratorio";

			rs1 = st1.executeQuery(query);

			while (rs1.next()) {
				String nomeLab = rs1.getString("nome");
				result.add(nomeLab);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	public String getIDbyName(Resource r) {

		String nome = r.getNome();

		String result = new String();

		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT ID FROM unibook.laboratorio " + "WHERE Nome = '" + nome + "'";
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
