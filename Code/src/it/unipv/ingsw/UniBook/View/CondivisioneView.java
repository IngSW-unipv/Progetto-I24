package it.unipv.ingsw.UniBook.View;

import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CondivisioneView extends JFrame {
 
    private JTextField userField;
    private JTextField fileField;
    private JButton addButton;
    private JButton downloadButton;

    public CondivisioneView() {
        setTitle("UniBook - Condivisione File");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userField = new JTextField();
        fileField = new JTextField();
        addButton = new JButton("Aggiungi File");
        downloadButton = new JButton("Download File");

        // Aggiungi i pulsanti alla vista
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(addButton);
        panel.add(downloadButton);

        // Aggiungi i campi di testo alla vista
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(new JLabel("File da scaricare: "));
        inputPanel.add(fileField);
        inputPanel.add(new JLabel("Specificare chi può scaricare il file:"));
        inputPanel.add(userField);

        // Aggiungi i pannelli alla vista
        setLayout(new BorderLayout());
        add(panel, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public String getUserInput() {
        return userField.getText();
    }

    public String getFileInput() {
        return fileField.getText();
    }

    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addDownloadButtonListener(ActionListener listener) {
        downloadButton.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
