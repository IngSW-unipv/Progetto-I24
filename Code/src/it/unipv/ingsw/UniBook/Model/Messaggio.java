package it.unipv.ingsw.UniBook.Model;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;

public class Messaggio {

	private User mittente;
	private User destinatario;
	private String testo;
	private LocalDateTime dataOra;
	private boolean letto;

	public Messaggio() {

	}

	public Messaggio(User mittente, User destinatario, String testo, LocalDateTime dataOra, boolean letto) {
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.testo = testo;
		this.dataOra = dataOra;
		this.letto = letto;
	}

	public User getMittente() {
		return mittente;
	}

	public void setMittente(User mittente) {
		this.mittente = mittente;
	}

	public User getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(User destinatario) {
		this.destinatario = destinatario;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public LocalDateTime getDataOra() {
		return dataOra;
	}

	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}

	public boolean isLetto() {
		return letto;
	}

	public void setLetto(boolean letto) {
		this.letto = letto;
	}

	

	public boolean provaInvio() {

		if (!testo.isEmpty()) {
			
			SingletonManager.getInstance().getMessaggioDAO().inserisciMessaggio(this);
			
			return true;
		} else {
			return false;
			
		}

	}
	
	public List<Messaggio> getMessaggiNonLetti() {
        return SingletonManager.getInstance().getMessaggioDAO().getMessaggiNonLetti(destinatario, mittente);
    }
	
	public void setMessaggioLetto(Messaggio messaggio) {
        SingletonManager.getInstance().getMessaggioDAO().setMessaggioLetto(messaggio);
    }
	
	

}
