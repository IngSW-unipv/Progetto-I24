package it.unipv.ingsw.UniBook.Model;

import javax.swing.JOptionPane;

import it.unipv.ingsw.UniBook.DB.BookingDAO;
import it.unipv.ingsw.UniBook.Exception.DatabaseException;
import it.unipv.ingsw.UniBook.Exception.DurationException;
import it.unipv.ingsw.UniBook.Exception.EmptyFieldException;
import it.unipv.ingsw.UniBook.Exception.OverbookingException;
import it.unipv.ingsw.UniBook.Exception.PopupManager;
import it.unipv.ingsw.UniBook.View.ManagementView;

public class Resource {

	private int id;
	private String nome;
	private String descrizione;
	private String tipo;
	private ManagementView inter;
	private Resource r;
	
	public Resource() {

	}

	public Resource(int id, String nome, String descrizione, String tipo, ManagementView inter, Resource r) {
		
		this.id=id;
		this.nome=nome;
		this.descrizione=descrizione;
		this.tipo=tipo;
		this.inter=inter;
		this.r=r;
		
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

	public ManagementView getInter() {
		return inter;
	}

	public void setInter(ManagementView inter) {
		this.inter = inter;
	}

	public Resource getR() {
		return r;
	}

	public void setR(Resource r) {
		this.r = r;
	}

	
//CONTROLLI

	//metodo che controlla che l'utente compila nome e descrizione 
	public void checkText () {
	
		if (inter.getTextField1Text().isEmpty() || inter.getTextField2Text().isEmpty()) {
		
			String messaggio = "Riempi tutti i campi";
			JOptionPane.showMessageDialog(inter, messaggio, "Dati Mancanti", JOptionPane.WARNING_MESSAGE);
        
		}
	
	}

	//metodo controllo caselle check Prenotabile e Affittabile
	public void checkCaselle() {
		
		boolean isPrenotabile = inter.CheckBoxPrenotabileSelected();
		boolean isAffittabile = inter.CheckBoxAffittabileSelected();

			//controllo caselle
			if (!isPrenotabile && !isAffittabile) {
    	
				String messaggio = "Seleziona almeno una opzione tra Prenotabile e Affittabile";
				JOptionPane.showMessageDialog(inter, messaggio, "Selezione Errata", JOptionPane.WARNING_MESSAGE);
        	
			} else if (isPrenotabile && isAffittabile) {
    	
				String messaggio = "Non puoi selezionare contemporaneamente Prenotabile e Affittabile";
				JOptionPane.showMessageDialog(inter, messaggio, "Selezione Errata", JOptionPane.WARNING_MESSAGE);
        
			}else {
	        	
	            if (isPrenotabile) {
	            	
	            	r.setTipo("Prenotabile");
	                
	            } else if (isAffittabile) {
	            	
	            	r.setTipo("Affittabile");
	                
	            }
		
	            if(inter.CheckBoxPrenotabileSelected()) {
	            	
	            	r.setTipo("Prenotabile");
	            	
	            }
		
	            if(inter.CheckBoxAffittabileSelected()) {
	            	
	            	r.setTipo("Affittabile");
	            	
	            }
    
			}
	}
	
	
	public void tryToUpload() {

		Resource ttb = new Resource (id, nome, descrizione, tipo, inter, r);

		try {
			
			checkText();
			checkCaselle();

			nome = inter.getTextField1Text();
			descrizione = inter.getTextField2Text();

			r.setNome(nome);
			r.setDescrizione(descrizione);
			
			// Mostra le informazioni in una finestra
			String messaggio = "Nome : " + nome + "\n" + "Descrizione : " + descrizione + "\n"
					+ (inter.CheckBoxPrenotabileSelected() ? nome + " é ora prenotabile" : "")
					+ (inter.CheckBoxAffittabileSelected() ? nome + " é ora affitabile" : "");

			JOptionPane.showMessageDialog(inter, messaggio, "Dati Inseriti",
					JOptionPane.INFORMATION_MESSAGE);
			
			inter.dispose();

		 } catch (NullPointerException e) {
			
			String messaggio = "Riempi i campi";
			
			JOptionPane.showMessageDialog(inter, messaggio, "Dati Inseriti", JOptionPane.INFORMATION_MESSAGE);				
			}
		
	}
		

	}