package it.unipv.ingsw.UniBook.Model;

import javax.swing.JOptionPane;

import it.unipv.ingsw.UniBook.DB.BookingDAO;
import it.unipv.ingsw.UniBook.Exception.*;
import it.unipv.ingsw.UniBook.View.ManagementView;

public class Resource {

	private int id;
	private String nome;
	private String descrizione;
	private String indirizzo;
	private String tipo;
	private int idLab;
	private String matricola_inserimento;
	
	public Resource() {

	}

	//Costruttore giusto che mi serviva per la prenotazione 
	public Resource(int id, String nome, String descrizione, String indirizzo, String tipo, int idLab, String matricola_inserimento) {
		
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.indirizzo = indirizzo;
		this.idLab = idLab;
		this.matricola_inserimento = matricola_inserimento;

	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String Tipo) {
		this.tipo = Tipo;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public int getIdLab() {
		return idLab;
	}

	public void setIdLab(int idLab) {
		this.idLab = idLab;
	}

	public String getMatricola_inserimento() {
		return matricola_inserimento;
	}

	public void setMatricola_inserimento(String matricola_inserimento) {
		this.matricola_inserimento = matricola_inserimento;
	}

	
	
//CONTROLLI
		
		 // Metodo per controllare lo stato delle caselle di controllo
	    public void checkCheckBoxes(boolean isPrenotabile, boolean isAffittabile) throws EmptyFieldException{
	    	
	        if (!isPrenotabile && !isAffittabile) {
	        	
	        	PopupManager.mostraPopup("Selezionare almeno una casella.");
	            
	        } else if (isPrenotabile && isAffittabile) {
	        	
	        	PopupManager.mostraPopup("Non è possibile selezionare entrambe le caselle.");
	            
	        }
	    }

	public void tryToUpload (String nome, String descrizione, boolean isPrenotabile, boolean isAffittabile){

			try {
				
	            checkCheckBoxes(isPrenotabile, isAffittabile);
	            
	            if (nome.isEmpty() || descrizione.isEmpty()) {
	                throw new EmptyFieldException();
	            }
	            
	            if ((isPrenotabile && !isAffittabile) || (!isPrenotabile && isAffittabile)) {
	            	
	            	if(isPrenotabile == true) {
	            		
	            		tipo = "prenotabile";
	            		
	            	} else tipo = "affittabile";
	            	
	            	PopupManager.mostraPopup("Risorsa inserita con successo! " + "\n Nome: " + nome + "\n Descrizione: " + descrizione + "\n La risorsa é ora "+ tipo);
	            	
	            }} catch (EmptyFieldException e) {
					
				e.mostraPopup();
				System.out.println(e.toString());
				
				}
	}
}