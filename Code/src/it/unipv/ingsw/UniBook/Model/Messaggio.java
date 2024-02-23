package it.unipv.ingsw.UniBook.Model;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;

import it.unipv.ingsw.UniBook.Exception.PopupManager;

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

	//Metodo con cui si prova ad inviare un nuovo messaggio
	public boolean provaInvio() {
		
		if (!testo.isEmpty()) {			
			SingletonManager.getInstance().getMessaggioDAO().inserisciMessaggio(this);
			return true;
		} else {
			PopupManager.showPopup("Inserisci un messaggio valido");
			return false;
		}

	}
	
	//Metodo che mi restituisce i messaggi non letti
	public List<Messaggio> getMessaggiNonLetti() {
        return SingletonManager.getInstance().getMessaggioDAO().getMessaggiNonLetti(destinatario, mittente);
    }
	
	//Metodo con cui setto come letto un messaggio
	public void setMessaggioLetto(Messaggio messaggio) {
        SingletonManager.getInstance().getMessaggioDAO().setMessaggioLetto(messaggio);
    }
	
	

}
