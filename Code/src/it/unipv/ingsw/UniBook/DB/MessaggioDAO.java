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

public class MessaggioDAO {

    private Connection conn;
    private String schema;

    public MessaggioDAO() {
        super();
        this.schema = "unibook";
//			conn=DBConnection.startConnection(conn,schema);
    }

    public void setConnection(Connection connection) {
        this.conn = connection;
    }

    public List<Messaggio> getMessaggi(String mittente, String destinatario) {
        conn = DBConnection.startConnection(conn, schema);
        List<Messaggio> messaggi = new ArrayList<>();
        String query = "SELECT * FROM messaggio WHERE (mittente = ? AND destinatario = ?) OR (mittente = ? AND destinatario = ?) ORDER BY dataOra ASC";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, mittente);
            statement.setString(2, destinatario);
            statement.setString(3, destinatario);
            statement.setString(4, mittente);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Messaggio messaggio = new Messaggio();
                messaggio.setMittente(resultSet.getString("mittente"));
                messaggio.setDestinatario(resultSet.getString("destinatario"));
                messaggio.setTesto(resultSet.getString("testo"));
                messaggio.setDataOra(resultSet.getTimestamp("dataOra").toLocalDateTime());
                messaggi.add(messaggio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messaggi;
    }


    public void inserisciMessaggio(Messaggio messaggio) {
        conn = DBConnection.startConnection(conn, schema);
        String query = "INSERT INTO messaggio (mittente, destinatario, testo, dataOra) VALUES (?, ?, ?, NOW())";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, messaggio.getMittente());
            statement.setString(2, messaggio.getDestinatario());
            statement.setString(3, messaggio.getTesto());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Messaggio> getMessaggiNonLetti(String mittente, String destinatario) {
        conn = DBConnection.startConnection(conn, schema);
        List<Messaggio> messaggiNonLetti = new ArrayList<>();
        String query = "SELECT * FROM messaggio WHERE mittente = ? AND destinatario = ? AND letto = 0";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, destinatario);
            statement.setString(2, mittente);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Messaggio messaggio = new Messaggio();
                messaggio.setMittente(resultSet.getString("mittente"));
                messaggio.setDestinatario(resultSet.getString("destinatario"));
                messaggio.setTesto(resultSet.getString("testo"));
                messaggio.setDataOra(resultSet.getTimestamp("dataOra").toLocalDateTime());
                messaggio.setLetto(false); // Imposta il messaggio come non letto
                messaggiNonLetti.add(messaggio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messaggiNonLetti;
    }
    
    

    public void setMessaggioLetto(Messaggio messaggio) {
        conn = DBConnection.startConnection(conn, schema);
        String query = "UPDATE messaggio SET letto = true WHERE mittente = ? AND destinatario = ? AND dataOra = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, messaggio.getMittente());
            statement.setString(2, messaggio.getDestinatario());
            statement.setTimestamp(3, Timestamp.valueOf(messaggio.getDataOra()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




