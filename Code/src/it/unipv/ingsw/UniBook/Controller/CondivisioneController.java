package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.Model.CondivisioneModel;
import it.unipv.ingsw.UniBook.View.CondivisioneView;
import it.unipv.ingsw.UniBook.View.FileDownloadFrame;
import it.unipv.ingsw.UniBook.View.FileSelectionFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CondivisioneController {
	
	private CondivisioneView view;
    private CondivisioneModel model;
    private FileSelectionFrame fs;
    private FileDownloadFrame df;
        
     // Nella classe CondivisioneController
        public CondivisioneController(CondivisioneView view, CondivisioneModel model, FileSelectionFrame fs, FileDownloadFrame df) {
            this.view = view;
            this.model = model;
            this.fs = fs;
            this.df = df;

            // Aggiungi il listener per il pulsante "Select File" nel FileSelectionFrame
            fs.addSelectFileListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Quando viene premuto il pulsante "Select File", apri la finestra di dialogo di selezione file
                    File selectedFile = fs.showFileChooser();
                    
                    // Popola i campi del file selezionato nei campi di testo nella CondivisioneView
                    if (selectedFile != null) {
                        String fileName = selectedFile.getName();
                        String fileExtension = model.getFileExtension(fileName);
                    }
                }
            });

            // Aggiungi il listener per il pulsante "Upload File" nella vista CondivisioneView
            view.UploadButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFileSelectionFrame();
            }
            });

            // Aggiungi il listener per il pulsante "Select File" nel FileSelectionFrame
            fs.addSelectFileListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quando viene premuto il pulsante "Select File", apri la finestra di dialogo di selezione file
                File selectedFile = fs.showFileChooser();
                
                // Popola i campi del file selezionato nei campi di testo nella CondivisioneView
                if (selectedFile != null) {
                    String fileName = selectedFile.getName();
                    String fileExtension = model.getFileExtension(fileName);
                }
            }
            });
        
            // Aggiungi il listener per il pulsante "Upload" nel FileSelectionFrame solo quando premuto
            fs.addUploadListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Ottieni il file selezionato dal FileSelectionFrame
                    File selectedFile = fs.getSelectedFile();
                    inserisciFile(selectedFile);
                }
            });
            
            view.DownloadButtonListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Chiama il metodo tryToDownloadFile() del modello quando viene premuto il pulsante "Download file"
                    model.tryToDownloadFile();
                }
            });

    }

    private void showFileSelectionFrame() {
        fs.setVisible(true);
    }
    

    private void showFileDownloadFrame() {
        df.setVisible(true);
    }
    
    private void prendiFile() {}

    // Metodo per provare a caricare il file nel modello
    private void inserisciFile(File selectedFile) {
        String fileName = selectedFile.getName();
        String fileNameWithoutExtension = model.removeFileExtension(fileName);
        String fileExtension = model.getFileExtension(fileName);
        
        // Passa il nome del file e l'estensione al modello
        model.tryToUploadFile(selectedFile, fileNameWithoutExtension, fileExtension);
    }    
    
    
    
}