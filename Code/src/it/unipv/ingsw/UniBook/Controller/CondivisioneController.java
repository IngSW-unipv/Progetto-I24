package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.Model.CondivisioneModel;
import it.unipv.ingsw.UniBook.View.CondivisioneView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CondivisioneController {

    private CondivisioneView view;
    private CondivisioneModel model;

    public CondivisioneController(CondivisioneView view, CondivisioneModel model) {
        this.view = view;
        this.model = model;

        // Aggiungi listener per il pulsante di upload
        view.addUploadButtonListener(new UploadButtonListener());
        // Aggiungi listener per il pulsante di download
        view.addDownloadButtonListener(new DownloadButtonListener());
    }

    // Listener per il pulsante di upload
    private class UploadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Crea un file chooser
            JFileChooser fileChooser = new JFileChooser();
            // Imposta la modalità di selezione su file
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            // Mostra il file chooser e ottieni il risultato
            int result = fileChooser.showOpenDialog(view);

            // Se l'utente ha selezionato un file
            if (result == JFileChooser.APPROVE_OPTION) {
                // Ottieni il file selezionato
                File selectedFile = fileChooser.getSelectedFile();
                // Ottieni il nome del file
                String fileName = selectedFile.getName();

                // Verifica l'estensione del file
                if (model.verificaEstensione(fileName)) {
                    // Verifica la dimensione del file
                    long fileSize = selectedFile.length();
                    if (model.verificaDimensione(fileSize)) {
                        // Simula l'upload del file
                        view.showMessage("Upload completato con successo!", "Upload completato");
                    } else {
                        view.showMessage("Dimensione del file troppo grande.", "Errore");
                    }
                } else {
                    view.showMessage("Tipo di file non supportato.", "Errore");
                }
            }
        }
    }

    // Listener per il pulsante di download
    private class DownloadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Logica di gestione del download
            try {
                model.simulaDownload();
                view.showMessage("File scaricato con successo!", "Download completato");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
} 