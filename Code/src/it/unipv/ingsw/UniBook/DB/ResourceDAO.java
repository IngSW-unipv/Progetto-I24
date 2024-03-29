package it.unipv.ingsw.UniBook.DB;

import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.Resource;
import it.unipv.ingsw.UniBook.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public Resource getResourceById(int id) {
		Resource result = new Resource();
		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;
		ResultSet rs1;
		try {
			String query = "SELECT ID,Nome,Descrizione,Indirizzo,Tipo,ID_Lab,Matricola_inserimento,Prezzo FROM unibook.risorsa WHERE risorsa.ID = ? ";
			st1 = conn.prepareStatement(query);
			st1.setInt(1, id);
			rs1 = st1.executeQuery();
			
			rs1.next();
			result = new Resource(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(8),rs1.getString(4),rs1.getString(5),rs1.getInt(6),rs1.getString(7));
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
		return result;
		
	}
	
	@Override
	public ArrayList<Resource> getAllBookableResources() {
		ArrayList<Resource> result = new ArrayList<>();

		conn = DBConnection.startConnection(conn, schema);
		Statement st1;
		ResultSet rs1;

		try {
			st1 = conn.createStatement();
			String query = "SELECT * FROM unibook.risorsa " + "WHERE Tipo='P'";

			rs1 = st1.executeQuery(query);

			while (rs1.next()) {
				
				int idRisorsa = Integer.parseInt(rs1.getString(1));
				String nomeRisorsa = rs1.getString(2);
				String descrizione = rs1.getString(3);
				String indirizzo = rs1.getString(4);
				String tipo = rs1.getString(5);
				int idLab = -1;
				if(rs1.getString(6) != null)
					idLab = Integer.parseInt(rs1.getString(6));			
				String matricola_inserimento = rs1.getString(7);
				double price = rs1.getDouble(8);
				result.add(new Resource(idRisorsa, nomeRisorsa, descrizione,price ,indirizzo, tipo, idLab, matricola_inserimento));
	            
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	//Metodo per ottenere un nuovo ID
	public int getMaxID() {
	    int maxId = 0;
	    Connection conn = null;
	    
	    try {
	    	
	        conn = DBConnection.startConnection(conn, schema);
	        String getMaxIdQuery = "SELECT MAX(ID) FROM risorsa";
	        Statement getMaxIdStmt = conn.createStatement();
	        ResultSet maxIdRs = getMaxIdStmt.executeQuery(getMaxIdQuery);
	        if (maxIdRs.next()) {
	            maxId = maxIdRs.getInt(1);
	        }
	    } catch (SQLException e) {
	    	
	        e.printStackTrace();
	        
	    } finally {
	    	
	        try {
	        	
	            if (conn != null) {
	            	
	                conn.close();
	                
	            }
	        } catch (SQLException e) {
	        	
	            e.printStackTrace();
	            
	        }
	    }
	    
	    int newId = maxId + 1;
	    return newId;
	}
	
	//Metodo per inserire le risorse prenotabili nel database
	public boolean insertRisorsaPrenotabile(Resource r) {
	    conn = DBConnection.startConnection(conn, schema);
	    PreparedStatement st1;
	    boolean esito = true;

	    try {
	        // Ottieni il nuovo ID
	        int newId = getMaxID();
	        
	        String query = "INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `Matricola_Inserimento`) VALUES (?, ?, ?, ?, ?, ?);";
	        st1 = conn.prepareStatement(query);
	        st1.setInt(1, newId); // Nuovo ID creato
	        st1.setString(2, r.getNome());
	        st1.setString(3, r.getDescrizione());
	        st1.setString(4, r.getIndirizzo());
	        st1.setString(5, r.getTipo());
	        st1.setString(6, r.getMatricola_inserimento());

	        st1.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	        esito = false;
	    }

	    DBConnection.closeConnection(conn);
	    return esito;
	}
	
	/// Metodo per inserire le risorse affittabili nel database
	public boolean insertRisorsaAffittabile(Resource r) {
	    conn = DBConnection.startConnection(conn, schema);
	    PreparedStatement st1;
	    boolean esito = true;

	    try {
	        // Ottieni il nuovo ID
	        int newId = getMaxID();
	        
	        String query = "INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `Matricola_Inserimento`, `Prezzo`) VALUES (?, ?, ?, ?, ?, ?, ?);";
	        st1 = conn.prepareStatement(query);
	        st1.setInt(1, newId); // Nuovo ID creato
	        st1.setString(2, r.getNome());
	        st1.setString(3, r.getDescrizione());
	        st1.setString(4, r.getIndirizzo());
	        st1.setString(5, r.getTipo());
	        st1.setString(6, r.getMatricola_inserimento());
	        st1.setDouble(7, r.getPrezzo()); 

	        st1.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	        esito = false;
	    } finally {
	        DBConnection.closeConnection(conn); // Chiudi la connessione nel blocco finally
	    }

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

	public boolean removeRisorsa(Resource resource) {
		
	    conn = DBConnection.startConnection(conn, schema);
	    PreparedStatement st1;
	    boolean success = false;

	    try {
	    	
	        String query = "DELETE FROM risorsa WHERE ID = ?";
	        st1 = conn.prepareStatement(query);
	        st1.setInt(1, resource.getId()); // Convertiamo l'ID in intero
	        int rowsAffected = st1.executeUpdate();
	        
	        // Se almeno una riga è stata rimossa, considera l'operazione come riuscita
	        if (rowsAffected > 0) {
	        	
	            success = true;
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace(); // Stampa l'eccezione per identificare l'errore
	    }

	    DBConnection.closeConnection(conn);
	    return success;
	}
	
	public ArrayList<Resource> getAllRentableResources() {
		
	    ArrayList<Resource> resources = new ArrayList<>();

	    conn = DBConnection.startConnection(conn, schema);
	    Statement st1;
	    ResultSet rs1;

	    try {
	        st1 = conn.createStatement();
	        String query = "SELECT * FROM risorsa where Tipo = 'A'";

	        rs1 = st1.executeQuery(query);

	        while (rs1.next()) {
	        	int idRisorsa = Integer.parseInt(rs1.getString(1));
				String nomeRisorsa = rs1.getString(2);
				String descrizione = rs1.getString(3);
				String indirizzo = rs1.getString(4);
				String tipo = rs1.getString(5);
				int idLab = -1;
				if(rs1.getString(6) != null)
					idLab = Integer.parseInt(rs1.getString(6));			
				String matricola_inserimento = rs1.getString(7);
				double price = rs1.getDouble(8);
				resources.add(new Resource(idRisorsa, nomeRisorsa, descrizione,price ,indirizzo, tipo, idLab, matricola_inserimento));
	            
	        }
	    } catch (Exception e) {
	    	
	        e.printStackTrace();
	        
	    }

	    DBConnection.closeConnection(conn);
	    return resources;
	    
	}
	
	public ArrayList<Resource> getResourceByMatricola(String matricola) {
	    ArrayList<Resource> result = new ArrayList<>();

	    conn = DBConnection.startConnection(conn, schema);
	    PreparedStatement st1;
	    ResultSet rs1;

	    try {
	        String query = "SELECT * FROM risorsa WHERE matricola_inserimento = ?";
	        st1 = conn.prepareStatement(query);
	        st1.setString(1, matricola);

	        rs1 = st1.executeQuery();

	        while (rs1.next()) {
	            int idRisorsa = rs1.getInt("ID");
	            String nomeRisorsa = rs1.getString("Nome");
	            String descrizione = rs1.getString("Descrizione");
	            String indirizzo = rs1.getString("Indirizzo");
	            String tipo = rs1.getString("Tipo");
	            int idLab = rs1.getInt("ID_Lab");
	            String matricolaInserimento = rs1.getString("Matricola_Inserimento");

	            result.add(new Resource(idRisorsa, nomeRisorsa, descrizione, indirizzo, tipo, idLab, matricolaInserimento));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    DBConnection.closeConnection(conn);
	    return result;
	}
	
	public boolean insertFile(Resource r) {
	    conn = DBConnection.startConnection(conn, schema);
	    PreparedStatement st1;
	    boolean esito = true;

	    try {
	        // Ottieni il nuovo ID
	        int newId = getMaxID();
	        
	        String query = "INSERT INTO unibook.risorsa (ID, Nome, Descrizione, Indirizzo, Tipo, Matricola_Inserimento) VALUES (?, ?, ?, ?, ?, ?);";
	        st1 = conn.prepareStatement(query);
	        st1.setInt(1, newId); // Nuovo ID creato
	        st1.setString(2, r.getNome());
	        st1.setString(3, r.getDescrizione());
	        st1.setString(4, r.getIndirizzo());
	        st1.setString(5,  "F");
	        st1.setString(6, r.getMatricola_inserimento());

	        st1.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	        esito = false;
	    }

	    DBConnection.closeConnection(conn);
	    return esito;
	}
	
	public ArrayList<Resource> getResourceFile() {
		conn = DBConnection.startConnection(conn, schema);
        ArrayList<Resource> resources = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try { 
        	
            String query = "SELECT * FROM risorsa WHERE Tipo = 'F'";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("Nome");
                String descrizione = rs.getString("Descrizione");
                String matricolaInserimento = rs.getString("Matricola_Inserimento");

                Resource resource = new Resource(id, nome, descrizione, matricolaInserimento);
                resources.add(resource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resources;
    }
	
}