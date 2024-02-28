package it.unipv.ingsw.UniBook.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import it.unipv.ingsw.UniBook.Exception.PopupManager;
import it.unipv.ingsw.UniBook.Model.Resource;
import it.unipv.ingsw.UniBook.Model.SingletonManager;

import java.awt.*;
import java.util.ArrayList;

public class FileDownloadFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JButton downloadButton;

    public FileDownloadFrame() {
        setTitle("Download file");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Descrizione");
        model.addColumn("Inserito da");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        updateTable();

        downloadButton = new JButton("Download");
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(downloadButton);
        add(buttonPanel, BorderLayout.SOUTH);
        

        setLocationRelativeTo(null);
    }
    
    public JButton getDownloadFrameButton() {
    	return downloadButton;
    }

    public void updateTable() {
        ArrayList<Resource> resources = SingletonManager.getInstance().getResourceDAO().getResourceFile();

        model.setRowCount(0);
        for (Resource r : resources) {
            model.addRow(new Object[]{r.getNome(), r.getDescrizione(), r.getMatricola_inserimento()});
        }
    }

    public void confermaDownload() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String nomeFile = (String) model.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Confermi il download di \"" + nomeFile + "\"?", "Conferma Download", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
            	PopupManager.showPopup("Download completato!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleziona un file dalla tabella.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}