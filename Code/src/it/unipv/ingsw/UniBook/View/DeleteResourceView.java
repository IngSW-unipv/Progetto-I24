package it.unipv.ingsw.UniBook.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import it.unipv.ingsw.UniBook.DB.ResourceDAO;
import it.unipv.ingsw.UniBook.Model.Resource;

public class DeleteResourceView extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JButton removeButton;
    private String userMatricola;

    public DeleteResourceView(String userMatricola) {
    	
        this.userMatricola = userMatricola;

        setTitle("Rimozione risorse");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Creazione del modello della tabella
        model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Descrizione");
        model.addColumn("Tipo");
        table = new JTable(model);

        // Aggiunta della tabella al frame
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Carica i dati delle risorse dal database e aggiorna la tabella
        ResourceDAO resourceDAO = new ResourceDAO();
        ArrayList<Resource> resources = resourceDAO.getResourceByMatricola(userMatricola); // Ottiene solo le risorse inserite dall'utente loggato
        updateTable(resources);

        removeButton = new JButton("Elimina");
        add(removeButton, BorderLayout.SOUTH);

        // Centra il frame nello schermo
        setLocationRelativeTo(null);

        setVisible(true);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                int selectedRow = getSelectedRow();
                if (selectedRow != -1) {
                	
                    int option = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare questa risorsa?", "Conferma eliminazione", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                    	
                        // Ottieni l'ID dalla riga selezionata
                        int id = resources.get(selectedRow).getId();
                        ResourceDAO resourceDAO = new ResourceDAO();
                        Resource resourceToRemove = new Resource();
                        resourceToRemove.setId(id);
                        boolean removed = resourceDAO.removeRisorsa(resourceToRemove);
                        if (removed) {
                        	
                            JOptionPane.showMessageDialog(null, "Risorsa rimossa con successo.");
                            model.removeRow(selectedRow);
                            
                        } else {
                        	
                            JOptionPane.showMessageDialog(null, "Si è verificato un errore durante la rimozione della risorsa.");
                            
                        }
                    }
                } else {
                	
                    JOptionPane.showMessageDialog(null, "Seleziona una risorsa da eliminare.");
                    
                }
            }
        });

    }

    public int getSelectedRow() {

        return table.getSelectedRow();

    }

    public void updateTable(ArrayList<Resource> resources) {
    	
        model.setRowCount(0); // Pulisce la tabella prima di aggiungere nuove righe
        
        for (Resource r : resources) {
        	
            model.addRow(new Object[]{r.getNome(), r.getDescrizione(), r.getTipo()});
            
        }
    }
}
