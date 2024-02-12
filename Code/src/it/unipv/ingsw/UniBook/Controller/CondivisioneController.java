package it.unipv.ingsw.UniBook.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unipv.ingsw.UniBook.Model.CondivisioneModel;
import it.unipv.ingsw.UniBook.View.CondivisioneView;

public class CondivisioneController {

    private CondivisioneView view;
    private CondivisioneModel model;

    public CondivisioneController(CondivisioneView view, CondivisioneModel model) {
        this.view = view;
        this.model = model;

        // Aggiungi listener per i pulsanti
        view.addAddButtonListener(new AddButtonListener());
        view.addDownloadButtonListener(new DownloadButtonListener());
    }

    // Listener per il pulsante di caricamento
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Logica di caricamento del file
            view.showMessage("Caricamento del file: " + view.getFileInput());
        }
    }

    // Listener per il pulsante di download
    private class DownloadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Logica di download del file
            String selectedFile = view.getFileInput();
            String selectedUser = view.getUserInput();

            // Chiama il metodo per la gestione del download del file
            handleFileDownload(selectedFile, selectedUser);
        }
    }

    private void handleFileDownload(String file, String user) {
        // Implementa la logica di download con i casi alternativi come nell'esempio precedente
        try {
            if (model.verificaPermessiUtente(user) && model.verificaConnessione() && model.verificaDimensioniFile(file)) {
                view.showMessage("Download del file: " + file + " - Permesso per: " + user);
                model.simulaDownload();
            } else {
                // Se il file non è trovato, mostra un messaggio di errore
                view.showMessage("Il sistema non trova il file indicato. Si prega di riprovare.");
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

