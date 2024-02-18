package it.unipv.ingsw.UniBook.View;

import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import it.unipv.ingsw.UniBook.Model.Booking;
import java.util.List;



public class DeleteBookingView extends JFrame{

	private JTable table;
	private DefaultTableModel model;
	private JButton deleteButton;

	public DeleteBookingView() {

		setTitle("Cancella Prenotazioni");
		setSize(400, 300);
		setLocationRelativeTo(null);

		// Creazione del modello della tabella
		model = new DefaultTableModel();
		model.addColumn("Risorsa");
		model.addColumn("Data");
		model.addColumn("Ora");
		model.addColumn("Durata");
		table = new JTable(model);

		// Aggiunta della tabella al frame
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	
		deleteButton = new JButton("Elimina");
		add(deleteButton, BorderLayout.SOUTH);
		
		setVisible(true);

	}
	
	public JButton getDeleteButton() {
		return deleteButton;
	}

	public void updateTable(ArrayList<Booking> booking) {
		model.setRowCount(0); // Pulisce la tabella prima di aggiungere nuove righe
		for (Booking b : booking) {
			model.addRow(new Object[] { b.getR().getNome(), b.getDate(), b.getTime(),
					b.getDuration() });
		}
	}

	public int getSelectedRow() {
		return table.getSelectedRow();
	}

}
