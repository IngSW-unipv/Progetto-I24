package it.unipv.ingsw.UniBook.Model;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import it.unipv.ingsw.UniBook.DB.BookingDAO;
import it.unipv.ingsw.UniBook.DB.ResourceDAO;
import it.unipv.ingsw.UniBook.Exception.*;
import it.unipv.ingsw.UniBook.View.ManagementView;

public class Resource {

	private int id;
	private String nome;
	private String descrizione;
	private double prezzo;
	private String indirizzo;
	private String tipo;
	private int idLab;
	private String matricola_inserimento;
	
	public Resource() {

	}
	
	//Costruttore per file
			public Resource(int id, String nome, String descrizione, String matricola_inserimento) {
				
		        this.id = id;
		        this.nome = nome;
		        this.descrizione = descrizione;
		        this.matricola_inserimento = matricola_inserimento;
		        
		    }

	//Costruttore per prenotazioni 
	public Resource(int id, String nome, String descrizione, String indirizzo, String tipo, int idLab, String matricola_inserimento) {
		
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.indirizzo = indirizzo;
		this.idLab = idLab;
		this.matricola_inserimento = matricola_inserimento;

	}
	
	//Costruttore per affitti
		public Resource(int id, String nome, String descrizione, double prezzo, String indirizzo, String tipo, int idLab, String matricola_inserimento) {
			
			this.id = id;
			this.nome = nome;
			this.descrizione = descrizione;
			this.prezzo = prezzo;
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

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	

//LOGICA

	public void tryToUpload(boolean isPrenotabile, boolean isAffittabile) {
	    try {
	    	
	    	System.out.println("Resource è "+isAffittabile + "Prezzo "+prezzo);
	    	
	        // Controllo se nessuna delle caselle è selezionata
	        if ((!isPrenotabile && !isAffittabile)) {
	            throw new EmptyFieldException();
	        }

	        // Controllo se entrambe le caselle sono selezionate
	        if (isPrenotabile && isAffittabile) {
	            throw new Exception("Non è possibile selezionare entrambe le caselle.");
	        }

	        // Controllo se i campi nome e descrizione sono vuoti
	        if (nome.isEmpty() || descrizione.isEmpty()) {
	            throw new EmptyFieldException();
	        }

	        // Se la risorsa è prenotabile e ha un prezzo, solleva un'eccezione
	        if (isPrenotabile && prezzo > 0) {
	            throw new Exception("Non è possibile inserire un prezzo per una risorsa prenotabile.");
	        }
	        
	        if (isAffittabile && prezzo <= 0.00) {
	            throw new Exception("Inserisici il prezzo per la risorsa affittabile.");
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

	        nuovaRisorsa.setIndirizzo(SingletonManager.getInstance().getLoggedUser().getCorso());
	        nuovaRisorsa.setMatricola_inserimento(SingletonManager.getInstance().getLoggedUser().getId());

	        // Impostazione del prezzo solo se la risorsa è affittabile
	        if (isAffittabile) {
	            nuovaRisorsa.setPrezzo(prezzo);
	        }

	        // Inserimento della risorsa nel database utilizzando ResourceDAO
	        ResourceDAO resourceDAO = SingletonManager.getInstance().getResourceDAO();
	        boolean inserimento;
	        
	        if (isAffittabile) {
	            inserimento = resourceDAO.insertRisorsaAffittabile(nuovaRisorsa);
	        } else {
	            inserimento = resourceDAO.insertRisorsaPrenotabile(nuovaRisorsa);
	        }

	        if (inserimento) {
	            PopupManager.showPopup("Risorsa inserita con successo! \nNome: " + nome + "\nDescrizione: " + descrizione + "\nLa risorsa è ora " + tipoDescrizione);
	        } else {
	            PopupManager.showPopup("Si è verificato un errore durante l'inserimento della risorsa.");
	        }
	        
	    } catch (EmptyFieldException e) {
	        e.showPopup();
	        System.out.println(e.toString());
	    } catch (Exception e) {
	        PopupManager.showPopup("Si è verificato un errore: " + e.getMessage());
	        System.out.println(e.toString());
	    }
	}
	
	// Metodo per rimuovere una risorsa
    public static void removeResource(ArrayList<Resource> resources, int selectedRow, DefaultTableModel model) {
        if (selectedRow != -1) {
            int option = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare questa risorsa?", "Conferma eliminazione", JOptionPane.YES_NO_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {

                int id = resources.get(selectedRow).getId();               // Ottieni l'ID dalla riga selezionata
                ResourceDAO resourceDAO = new ResourceDAO();
                Resource resourceToRemove = new Resource();
                resourceToRemove.setId(id);
                boolean removed = resourceDAO.removeRisorsa(resourceToRemove);
                
                if (removed) {
                    JOptionPane.showMessageDialog(null, "Risorsa rimossa con successo.");
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Si è verificato un errore durante la rimozione della risorsa.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleziona una risorsa da eliminare.");
        }
    }

    
	@Override
	public String toString() {
		return this.nome;
	}
}