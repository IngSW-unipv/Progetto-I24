package it.unipv.ingsw.UniBook.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CondivisioneView extends JFrame {
    private JButton uploadButton;
    private JButton downloadButton;

    public CondivisioneView() {
        setTitle("Condivisione File");
        setSize(800, 600); // Imposta le dimensioni della finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea un pannello per contenere i bottoni
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200));
        
        // Crea i bottoni
        uploadButton = new JButton("Upload File");
        downloadButton = new JButton("Download File");

        // Aggiungi i bottoni al pannello
        buttonPanel.add(uploadButton);
        buttonPanel.add(downloadButton);

        // Aggiungi il pannello dei bottoni al contenitore principale
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        
        // Centra la finestra
        setLocationRelativeTo(null);

        pack();
        setVisible(true);
    }

    public void addUploadButtonListener(ActionListener listener) {
        uploadButton.addActionListener(listener);
    }

    public void addDownloadButtonListener(ActionListener listener) {
        downloadButton.addActionListener(listener);
    }

    // Metodo per mostrare un popup con un messaggio personalizzato
    public void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
} 