package it.unipv.ingsw.UniBook.View;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileSelectionFrame extends JFrame {

    private JTextField fileNameField;
    private JTextField fileFormatField;
    private JButton selectFileButton;
    private JButton uploadButton;
    private FileSelectionUpdater fileSelectionUpdater;

    
    public FileSelectionFrame(FileSelectionUpdater fileSelectionUpdater) {
        this.fileSelectionUpdater = fileSelectionUpdater;
        
        setTitle("File Selection Frame");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        fileNameField = new JTextField(30);
        fileFormatField = new JTextField(30);
        selectFileButton = new JButton("Select File");
        uploadButton = new JButton("Upload");

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
        
    }
    
    private String removeFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(0, dotIndex);
        } else {
            return fileName;
        }
    }

    public interface FileSelectionListener {
        void onFileSelected(File selectedFile, String fileNameWithoutExtension);
    }
    
    public JButton getSelectButton() {
    	return selectFileButton;
    }
    
    public JButton getUploadButton() {
    	return uploadButton;
    }

    public File showFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            // Ottenere il nome del file e l'estensione
            String fileName = selectedFile.getName();
            String fileExtension = getFileExtension(fileName);
            // Aggiornare i campi di testo
            fileNameField.setText(removeFileExtension(fileName));
            fileFormatField.setText(fileExtension);
            return selectedFile;
        }
        return null;
    }

    public File getSelectedFile() {
    	
        String fileName = fileNameField.getText();
        String fileFormat = fileFormatField.getText();
        return new File(fileName + "." + fileFormat);
        
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        } else {
            return "";
        }
    }

    public interface FileSelectionUpdater {
        void updateFileFields(String fileName, String fileFormat);
    }   
    
}