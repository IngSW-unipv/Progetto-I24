package it.unipv.ingsw.UniBook.DB;

import java.sql.PreparedStatement;

import it.unipv.ingsw.UniBook.Model.Prenotazione;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.SimpleDateFormat;


public class PrenotazioneDAO implements IPrenotazioneDAO {

	private String schema;
	private Connection conn;

	public PrenotazioneDAO() {
		super();
		this.schema = "unibook";
	}
	
	public boolean insertPrenotazione(Prenotazione p) {

		conn = DBConnection.startConnection(conn, schema);
		PreparedStatement st1;

		boolean esito = true;

		try {
			String query = "INSERT INTO `unibook`.`prenotazione` (`ID_Risorsa`, `Matricola`, `DataOra`, `tempo`) "
					+ " VALUES(?,?,?,?)";
			st1 = conn.prepareStatement(query);
			
			//DA IMPLEMENTARE CORRETTAMENTE UNA VOLTA FATTO IL LOGIN
			/*st1.setInt(1, p.getDurata());
			st1.setString(2, p.getNome().substring(4,9));*/
			
			st1.setInt(1, 6);
			st1.setString(2, "S500816");
			
			//Per adattare i formati
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
	
}
