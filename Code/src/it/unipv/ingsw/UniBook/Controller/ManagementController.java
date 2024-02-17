package it.unipv.ingsw.UniBook.Controller;

import javax.swing.*;

import it.unipv.ingsw.UniBook.Model.Resource;
import it.unipv.ingsw.UniBook.View.ManagementView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagementController {

	private ManagementView interfaccia;
	private Resource r;

	public ManagementController(ManagementView interfaccia, Resource r) {
		
		this.interfaccia = interfaccia;
		this.r=r;
		initComponents();

	}

	private void initComponents() {

		ActionListener confirm = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				manageAction();
				
			}

			private void manageAction() {
				
				 String nome = interfaccia.getTextNome();
				 String descrizione = interfaccia.getTextDescrizione();
				 boolean prenotabile = interfaccia.CheckBoxPrenotabileSelected();
				 boolean affittabile = interfaccia.CheckBoxAffittabileSelected();
				    
				// Chiama il metodo tryToUpload()
				r.tryToUpload(nome, descrizione, prenotabile, affittabile);

			}
		};

	// Aggiungo il listener al bottone
	interfaccia.getConfermaButton().addActionListener(confirm);

	}
		
}

