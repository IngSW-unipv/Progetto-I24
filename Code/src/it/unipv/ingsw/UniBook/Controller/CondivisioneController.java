package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.Exception.AuthorizationDeniedException;
import it.unipv.ingsw.UniBook.Model.CondivisioneModel;
import it.unipv.ingsw.UniBook.Model.Professor;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.View.CondivisioneView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CondivisioneController {

    private CondivisioneView view;
    private CondivisioneModel model;

    public CondivisioneController(CondivisioneView view, CondivisioneModel model) {
        this.view = view;
        this.model = model;

        view.addUploadButtonListener(new UploadButtonListener());
        view.addDownloadButtonListener(new DownloadButtonListener());
    }

    private class UploadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                SingletonManager manager = SingletonManager.getInstance();

                if (manager.isCurrentUserProfessor()) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    int result = fileChooser.showOpenDialog(view);
                    // Se l'utente ha selezionato un file
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        String fileName = selectedFile.getName();

                        if (model.verificaEstensione(fileName)) {
                            long fileSize = selectedFile.length();
                            if (model.verificaDimensione(fileSize)) {
                                view.showMessage("Upload completato con successo!", "Upload completato");
                            } else {
                                view.showMessage("Dimensione del file troppo grande.", "Errore");
                            }
                        } else {
                            view.showMessage("Tipo di file non supportato.", "Errore");
                        }
                    }
                } else {
                    view.showMessage("Non hai i permessi per eseguire l'upload.", "Sistema");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public class DownloadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String folderPath = "/Users/houssembenturkia/Progetto-I24/src/main/resources/download condivisione file/Risorse UniBook";
            System.out.println("Percorso cartella: " + folderPath);

            JFileChooser fileChooser = new JFileChooser(folderPath);
            fileChooser.setDialogTitle("Seleziona il file da scaricare");

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
            	File selectedFile = fileChooser.getSelectedFile();

             
                JOptionPane.showMessageDialog(null, "Download completato con successo per il file: " + selectedFile.getName(), "Download completato", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}