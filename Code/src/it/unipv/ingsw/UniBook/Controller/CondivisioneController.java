package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.Model.CondivisioneModel;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.View.CondivisioneView;
import it.unipv.ingsw.UniBook.View.FileSelectionFrame;
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

        view.addUploadButtonListener(new UploadButtonListener());
        view.addDownloadButtonListener(new DownloadButtonListener());
    }

    private class UploadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                User user = SingletonManager.getInstance().getLoggedUser();
                if (user != null && (user.getTipo().equals("Professore") || user.getTipo().equals("Ricercatore"))) {
                    
                            new FileSelectionFrame();
                       
                }
                        
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        
    }
 }
    
    public class DownloadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleziona il file da scaricare");

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(null, "Download completato con successo per il file: " + selectedFile.getName(), "Download completato", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}