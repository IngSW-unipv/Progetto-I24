package it.unipv.ingsw.UniBook.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CondivisioneView extends JFrame {

    private JButton uploadButton;
    private JButton downloadButton;

    public CondivisioneView() {
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200));
        buttonPanel.setBackground(new Color(214, 255, 255));
        uploadButton = new JButton("Upload File");
        downloadButton = new JButton("Download File");

        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        pack();

    }

    public JButton getDownloadButton() {
    	return downloadButton;
    }
    
    public JButton getUploadButton() {
    	return uploadButton;
    }

}