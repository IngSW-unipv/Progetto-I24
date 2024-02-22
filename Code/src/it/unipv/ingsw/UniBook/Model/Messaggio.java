package it.unipv.ingsw.UniBook.Model;

import java.time.LocalDateTime;

public class Messaggio {
    
    private String mittente;
    private String destinatario;
    private String testo;
    private LocalDateTime dataOra;
    private boolean letto;
    
    public String getMittente() {
        return mittente;
    }
    
    public void setMittente(String mittente) {
        this.mittente = mittente;
    }
    
    public String getDestinatario() {
        return destinatario;
    }
    
    public void setDestinatario(String destinatario) {
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

}

