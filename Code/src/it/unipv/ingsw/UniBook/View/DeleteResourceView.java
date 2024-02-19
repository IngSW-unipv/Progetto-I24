package it.unipv.ingsw.UniBook.View;

import javax.swing.*;
import java.awt.*;

public class DeleteResourceView extends JFrame {

    public DeleteResourceView() {

        setTitle("Tabella Risorsa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Creazione della tabella con Nome, Descrizione e Tipo
        String[] columnNames = {"Nome", "Descrizione", "Tipo"};
        Object[][] data = {
        		
            {"Risorsa1", "Descrizione1", "Tipo1"},
            {"Risorsa2", "Descrizione2", "Tipo2"},
            
        };
        
        JTable table = new JTable(data, columnNames);
        
        // Aggiungi la tabella al frame all'interno di uno JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
    }
}