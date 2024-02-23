package it.unipv.ingsw.UniBook.View;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.Renting;
import it.unipv.ingsw.UniBook.Model.Resource;
import it.unipv.ingsw.UniBook.Model.SingletonManager;

public class ManagementRentingView extends JFrame {
	private JTable table;
	private DefaultTableModel model;
	private JButton extendButton;
	private JDateChooser newEndDate;
	
	public ManagementRentingView () {
		setTitle("Visualizza e modifica affitti");
		setSize(400, 300);
		setLocationRelativeTo(null);
		
		model = new DefaultTableModel();
		model.addColumn("ID Risorsa");
		model.addColumn("Nome Risorsa");
		model.addColumn("DataInizio");
		model.addColumn("DataFine");
		model.addColumn("Costo");
		table = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		
		extendButton = new JButton("Posticipa");
		add(extendButton, BorderLayout.SOUTH);
		Calendar minDate = Calendar.getInstance();
		newEndDate = new JDateChooser();
		newEndDate.setDateFormatString("dd/MM/yyyy");
		newEndDate.setMinSelectableDate(minDate.getTime());
		setVisible(true);

	}
	
	public JButton getExtendButton() {
		return extendButton;
	}

	
	public JDateChooser getNewEndDateChooser() {
		return newEndDate;
	}
	
	public String getNewEndDate() {
		Date selectedDate = newEndDate.getDate();
		
		if(selectedDate == null)
			return "";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(selectedDate);
	}

	public void updateTable(ArrayList<Renting> rent) {
		model.setRowCount(0); // Pulisce la tabella prima di aggiungere nuove righe
		for (Renting r : rent) {
			model.addRow(new Object[] { r.getResource(),r.getResource().getNome(),r.getStartDate(), r.getEndDate(), r.getPrice()});
		}
		table.removeColumn(table.getColumnModel().getColumn(0));
	}

	public Renting getSelectedRow() {
		int sr = table.getSelectedRow();
		return new Renting((Resource)model.getValueAt(sr, 0),SingletonManager.getInstance().getLoggedUser(),
				model.getValueAt(sr, 2).toString(),model.getValueAt(sr, 3).toString(),(double)model.getValueAt(sr, 4));
	}
	
}
