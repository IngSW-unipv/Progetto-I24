package it.unipv.ingsw.UniBook.View;

import javax.swing.*;

import it.unipv.ingsw.UniBook.DB.ResourceDAO;
import it.unipv.ingsw.UniBook.Model.Resource;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileSelectionFrame extends JFrame {
    private JTextField fileNameField;
    private JTextField fileFormatField;
    private JButton selectFileButton;
    private JButton uploadButton;

    public FileSelectionFrame() {
        setTitle("File Selection Frame");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        fileNameField = new JTextField(30);
        fileFormatField = new JTextField(30);
        selectFileButton = new JButton("Select File");
        uploadButton = new JButton("Upload");

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(FileSelectionFrame.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName();
                    // Rimuovi l'estensione dal nome del file
                    String fileNameWithoutExtension = removeFileExtension(fileName);
                    // Imposta il nome del file senza estensione nel campo di testo
                    fileNameField.setText(fileNameWithoutExtension);
                    // Ottieni l'estensione del file
                    String fileFormat = getFileExtension(selectedFile);
                    fileFormatField.setText(fileFormat);
                }
            }
        });


        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(FileSelectionFrame.this, "Sei sicuro?", "Conferma Upload", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // Creazione dell'oggetto Resource con i dati presenti nei campi di testo
                    Resource resource = new Resource();
                    resource.setNome(fileNameField.getText());
                    resource.setDescrizione(fileFormatField.getText()); // Descrizione assunta come formato nel tuo codice
                    resource.setIndirizzo(""); // L'indirizzo non è fornito nel tuo codice
                    resource.setTipo("F"); // Tipo "F" come specificato nel tuo codice
                    
                    // Ottieni l'utente loggato
                    User loggedUser = SingletonManager.getInstance().getLoggedUser();
                    if (loggedUser != null) {
                        // Imposta l'indirizzo e la matricola_inserimento dell'utente loggato
                        resource.setIndirizzo(loggedUser.getCorso());
                        resource.setMatricola_inserimento(loggedUser.getId());
                    } else {
                        // Se l'utente non è loggato, mostra un messaggio di errore
                        JOptionPane.showMessageDialog(FileSelectionFrame.this, "Utente non loggato.", "Errore", JOptionPane.ERROR_MESSAGE);
                        return; // Esce dal metodo
                    }
                    
                    // Creazione dell'oggetto ResourceDAO per l'inserimento nel database
                    ResourceDAO resourceDAO = new ResourceDAO();
                    
                    // Inserimento nel database
                    boolean insertSuccess = resourceDAO.insertFile(resource);

                    if (insertSuccess) {
                        JOptionPane.showMessageDialog(FileSelectionFrame.this, "File inserito con successo!");
                    } else {
                        JOptionPane.showMessageDialog(FileSelectionFrame.this, "Errore durante l'inserimento del file.");
                    }
                }
            }
        });


        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        leftPanel.add(new JLabel("File Name:"), gbc);
        gbc.gridy++;
        leftPanel.add(fileNameField, gbc);
        gbc.gridy++;
        leftPanel.add(new JLabel("File Format:"), gbc);
        gbc.gridy++;
        leftPanel.add(fileFormatField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectFileButton);
        buttonPanel.add(uploadButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return "." + fileName.substring(dotIndex + 1).toLowerCase();
        } else {
            return "";
        }
    }

    
 // Metodo per rimuovere l'estensione dal nome del file
    private String removeFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(0, dotIndex);
        } else {
            return fileName;
        }
    }
}