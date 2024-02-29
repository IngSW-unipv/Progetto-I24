package it.unipv.ingsw.UniBook.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.UniBook.Model.Messaggio;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.User;

public class MessaggioDAO {

	private Connection conn;
	private String schema;

	public MessaggioDAO() {
		this.schema = "unibook";
	}

	public void setConnection(Connection connection) {
		this.conn = connection;
	}

	public List<Messaggio> getMessaggi(User mittente, User destinatario) {
		conn = DBConnection.startConnection(conn, schema);
		
		List<Messaggio> messaggi = new ArrayList<>();
		String query = "SELECT * FROM messaggio WHERE (mittente = ? AND destinatario = ?) OR (mittente = ? AND destinatario = ?) ORDER BY dataOra ASC";
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, mittente.getId());
			statement.setString(2, destinatario.getId());
			statement.setString(3, destinatario.getId());
			statement.setString(4, mittente.getId());
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				String idm = resultSet.getString(2);
				String idd = resultSet.getString(3);
				User mit = new User(idm, null);
				User des = new User(idd, null);

				Messaggio messaggio = new Messaggio();
				messaggio.setMittente(mit);
				messaggio.setDestinatario(des);
				messaggio.setTesto(resultSet.getString("testo"));
				messaggio.setDataOra(resultSet.getTimestamp("dataOra").toLocalDateTime());
				messaggi.add(messaggio);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);

		return messaggi;
	}

	public void inserisciMessaggio(Messaggio messaggio) {
		conn = DBConnection.startConnection(conn, schema);
		
		String query = "INSERT INTO messaggio (mittente, destinatario, testo, dataOra) VALUES (?, ?, ?, NOW())";
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, messaggio.getMittente().getId());
			statement.setString(2, messaggio.getDestinatario().getId());
			statement.setString(3, messaggio.getTesto());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);

	}

	public List<Messaggio> getMessaggiNonLetti(User mittente, User destinatario) {
		conn = DBConnection.startConnection(conn, schema);
		List<Messaggio> messaggiNonLetti = new ArrayList<>();
		String query = "SELECT * FROM messaggio WHERE mittente = ? AND destinatario = ? AND letto = 0";
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, destinatario.getId());
			statement.setString(2, mittente.getId());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String idm = resultSet.getString(2);
				String idd = resultSet.getString(3);
				User mit = new User(idm, null);
				User des = new User(idd, null);
				Messaggio messaggio = new Messaggio();
				messaggio.setMittente(mit);
				messaggio.setDestinatario(des);
				messaggio.setTesto(resultSet.getString("testo"));
				messaggio.setDataOra(resultSet.getTimestamp("dataOra").toLocalDateTime());
				messaggio.setLetto(false); // Imposta il messaggio come non letto
				messaggiNonLetti.add(messaggio);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
		return messaggiNonLetti;
	}

	public void setMessaggioLetto(Messaggio messaggio) {
		conn = DBConnection.startConnection(conn, schema);
		
		String query = "UPDATE messaggio SET letto = true WHERE mittente = ? AND destinatario = ? AND dataOra = ?";
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, messaggio.getMittente().getId());
			statement.setString(2, messaggio.getDestinatario().getId());
			statement.setTimestamp(3, Timestamp.valueOf(messaggio.getDataOra()));
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);

	}
}
