package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.Model.Resource;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.View.DeleteResourceView;
import it.unipv.ingsw.UniBook.View.ManagementView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ManagementController {

    private ManagementView interfaccia;
	private User user;

public ManagementController(ManagementView interfaccia, User user) {
    	
        this.interfaccia = interfaccia;
        this.user = SingletonManager.getInstance().getLoggedUser();

        // Aggiungo il listener al bottone "CONFERMA"
        this.interfaccia.getConfermaButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserisciRisorsa();
            }
        });

        // Aggiungo il listener al bottone "RIMUOVI RISORSA"
        this.interfaccia.getRimuoviButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeRisorsa();
            }
        });
    }
    
    private void removeRisorsa() {
    	DeleteResourceView DeleteFrame = new DeleteResourceView(user.getId());
    	DeleteFrame.setVisible(true);
    }

    private void inserisciRisorsa() {
        // Ottieni i valori dai campi della vista
        String nome = interfaccia.getTextNome();
        String descrizione = interfaccia.getTextDescrizione();
        boolean isPrenotabile = interfaccia.CheckBoxPrenotabileSelected();
        boolean isAffittabile = interfaccia.CheckBoxAffittabileSelected();
        Double prezzo = interfaccia.getTextPrezzo();

        Resource nuovaRisorsa;

            nuovaRisorsa = new Resource(0, nome, descrizione, prezzo, user.getCorso(), isPrenotabile ? "P" : "A", 0, user.getId());
        

        nuovaRisorsa.tryToUpload(isPrenotabile, isAffittabile);
    }

}


