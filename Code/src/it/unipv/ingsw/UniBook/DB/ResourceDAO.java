package it.unipv.ingsw.UniBook.DB;

import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.Resource;
import it.unipv.ingsw.UniBook.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ResourceDAO implements IResourceDAO {

	private String schema;
	private Connection conn;

	public ResourceDAO() {
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
			String query = "SELECT Nome FROM unibook.risorsa " + "WHERE Tipo='P'";

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

	public boolean insertRisorsa(Resource r) {
	    conn = DBConnection.startConnection(conn, schema);
	    PreparedStatement st1;
	    boolean esito = true;

	    try {
	        // Prendo ID max del DB
	        String getMaxIdQuery = "SELECT MAX(ID) FROM risorsa";
	        Statement getMaxIdStmt = conn.createStatement();
	        ResultSet maxIdRs = getMaxIdStmt.executeQuery(getMaxIdQuery);

	        int maxId = 0;
	        if (maxIdRs.next()) {
	            maxId = maxIdRs.getInt(1);
	        }

	        // Incrementa l'ID max per inserirlo nella nuova risorsa
	        int newId = maxId + 1;

	        String query = "INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`) VALUES (?, ?, ?, ?, ?);";
	        st1 = conn.prepareStatement(query);
	        st1.setInt(1, newId); // Nuovo ID 
	        st1.setString(2, r.getNome());
	        st1.setString(3, r.getDescrizione());
	        st1.setString(4, r.getIndirizzo());
	        st1.setString(5, r.getTipo());

	        st1.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	        esito = false;
	    }

	    DBConnection.closeConnection(conn);
	    return esito;
	}
	
	//Metodo con cui ottengo tutte le postazioni di un determinato laboratorio
	public ArrayList<Resource> getResourceByLab(Resource r) {
		ArrayList<Resource> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();

            String query = "SELECT * FROM risorsa WHERE ID_Lab = '" + r.getId() + "'";

			rs1 = st1.executeQuery(query);

			while (rs1.next()) {
				int idRisorsa = Integer.parseInt(rs1.getString(1));
				String nomeRisorsa = rs1.getString(2);
				String descrizione = rs1.getString(3);
				String indirizzo = rs1.getString(4);
				String tipo = rs1.getString(5);
				int idLab = Integer.parseInt(rs1.getString(6));			
				String matricola_inserimento = rs1.getString(7);
				
				result.add(new Resource(idRisorsa, nomeRisorsa, descrizione, indirizzo, tipo, idLab, matricola_inserimento));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
}