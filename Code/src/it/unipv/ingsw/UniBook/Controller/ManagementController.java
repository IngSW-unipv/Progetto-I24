package it.unipv.ingsw.UniBook.Controller;

import javax.swing.*;

import it.unipv.ingsw.UniBook.Model.Resource;
import it.unipv.ingsw.UniBook.View.ManagementView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagementController {

	private ManagementView interfaccia;
	private Resource r;
	private JCheckBox checkBox1;
	private JCheckBox checkBox2;

	public ManagementController(ManagementView interfaccia, Resource r) {
		this.interfaccia = interfaccia;
		this.r=r;
		initComponents();

	}

	private void initComponents() {

		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {
				
				
				if (interfaccia.getTextField1Text().isEmpty() || interfaccia.getTextField2Text().isEmpty()) {
					
                    String messaggio = "Riempi tutti i campi";
                    JOptionPane.showMessageDialog(interfaccia, messaggio, "Dati Mancanti", JOptionPane.WARNING_MESSAGE);
                    
				} else

				try {
					
					String nome = interfaccia.getTextField1Text();
					String descrizione = interfaccia.getTextField2Text();

					r.setNome(nome);
					r.setDescrizione(descrizione);
					
					boolean isPrenotabile = interfaccia.isCheckBox1Selected();
				     boolean isAffittabile = interfaccia.isCheckBox2Selected();

				     
				     //controllo caselle
				        if (!isPrenotabile && !isAffittabile) {
				        	
				            String messaggio = "Seleziona almeno una opzione tra Prenotabile e Affittabile";
				            JOptionPane.showMessageDialog(interfaccia, messaggio, "Selezione Errata", JOptionPane.WARNING_MESSAGE);
				            
				        } else if (isPrenotabile && isAffittabile) {
				        	
				            String messaggio = "Non puoi selezionare contemporaneamente Prenotabile e Affittabile";
				            JOptionPane.showMessageDialog(interfaccia, messaggio, "Selezione Errata", JOptionPane.WARNING_MESSAGE);
				            
				        } else {
				        	
				            if (isPrenotabile) {
				            	
				                r.setTipo("Prenotabile");
				                
				            } else if (isAffittabile) {
				            	
				                r.setTipo("Affittabile");
				                
				            }
					
					if(interfaccia.isCheckBox1Selected()) {
						r.setTipo("Prenotabile");
					}
					
					if(interfaccia.isCheckBox2Selected()) {
						r.setTipo("Affittabile");
					}
					
					// Mostra le informazioni in una finestra
					String messaggio = "Nome : " + nome + "\n" + "Descrizione : " + descrizione + "\n"
							+ (interfaccia.isCheckBox1Selected() ? nome + " é ora prenotabile" : "")
							+ (interfaccia.isCheckBox2Selected() ? nome + " é ora affitabile" : "");

					JOptionPane.showMessageDialog(interfaccia, messaggio, "Dati Inseriti",
							JOptionPane.INFORMATION_MESSAGE);
					
					interfaccia.dispose();

				        }
				} catch (NullPointerException e) {
					
					String messaggio = "Riempi i campi";
					
					JOptionPane.showMessageDialog(interfaccia, messaggio, "Dati Inseriti", JOptionPane.INFORMATION_MESSAGE);				
					}
				
			}

		};

		// Aggiungo il listener al bottone
		interfaccia.getConfermaButton().addActionListener(al);

	}
		
}
