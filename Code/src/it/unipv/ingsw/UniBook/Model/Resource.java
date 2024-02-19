package it.unipv.ingsw.UniBook.Model;

import javax.swing.JOptionPane;

import it.unipv.ingsw.UniBook.DB.BookingDAO;
import it.unipv.ingsw.UniBook.DB.ResourceDAO;
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
	
	
	    
	public void tryToUpload(String nome, String descrizione, boolean isPrenotabile, boolean isAffittabile, User user) {
		
	    try {
	    	
	        // Controllo se nessuna delle caselle è selezionata e se nome e descrizione sono vuoti
	        if ((!isPrenotabile && !isAffittabile) || nome.isEmpty() || descrizione.isEmpty()) {
	            throw new Exception("Riempi tutti i campi!");
	        }
	        
	        // Controllo se entrambe le caselle sono selezionate
	        if (isPrenotabile && isAffittabile) {
	            throw new Exception("Non è possibile selezionare entrambe le caselle.");
	        }

	        // Controllo se i campi nome e descrizione sono vuoti
	        if (nome.isEmpty() || descrizione.isEmpty()) {
	        	
	            throw new EmptyFieldException();
	            
	        }

	        String tipo;
	        String tipoDescrizione;

	        if (isPrenotabile) {
	        	
	            tipo = "P";
	            tipoDescrizione = "Prenotabile";
	            
	        } else {
	        	
	            tipo = "A";
	            tipoDescrizione = "Affittabile";
	            
	        }

	        // Creazione di una nuova risorsa con i valori forniti
	        Resource nuovaRisorsa = new Resource();
	        nuovaRisorsa.setNome(nome);
	        nuovaRisorsa.setDescrizione(descrizione);
	        nuovaRisorsa.setTipo(tipo);
	        nuovaRisorsa.setIndirizzo(user.getCorso());
	        nuovaRisorsa.setMatricola_inserimento(user.getId());
	        
	        // Inserimento della risorsa nel database utilizzando ResourceDAO
	        ResourceDAO resourceDAO = new ResourceDAO();
	        boolean inserimento = resourceDAO.insertRisorsa(nuovaRisorsa);

	        if (inserimento) {
	        	
	            PopupManager.mostraPopup("Risorsa inserita con successo! \nNome: " + nome + "\nDescrizione: " + descrizione + "\nLa risorsa è ora " + tipoDescrizione);
	        } else {
	        	
	            PopupManager.mostraPopup("Si è verificato un errore durante l'inserimento della risorsa.");
	            
	        }
	    		} catch (EmptyFieldException e) {
	    	
	    			e.mostraPopup();
	    			System.out.println(e.toString());
	        
	    		} catch (Exception e) {
	    	
	    			PopupManager.mostraPopup("Si è verificato un errore: " + e.getMessage());
	    			System.out.println(e.toString());
	        
	    		}
	}
}