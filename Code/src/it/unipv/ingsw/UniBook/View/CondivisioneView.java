package it.unipv.ingsw.UniBook.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CondivisioneView extends JFrame {
    private JButton uploadButton;
    private JButton downloadButton;

    public CondivisioneView() {
        setTitle("Condivisione File");
        setSize(400, 300); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200));
        buttonPanel.setBackground(new Color(214, 255, 255));
        uploadButton = new JButton("Upload File");
        downloadButton = new JButton("Download File");

        buttonPanel.add(uploadButton);
        buttonPanel.add(downloadButton);

        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        
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
}