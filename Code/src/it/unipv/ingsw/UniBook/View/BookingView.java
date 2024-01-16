package it.unipv.ingsw.UniBook.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;

import it.unipv.ingsw.UniBook.Controller.BookingController;

import java.util.Date;
import java.text.SimpleDateFormat;

public class BookingView extends JFrame {

	private JDateChooser dateChooser;

	private JComboBox<String> oraComboBox;

	private JComboBox<String> risorsaComboBox;

	private JComboBox<Integer> durataComboBox;

	private JButton confermaButton;

	public BookingView() {
		// Impostazioni del frame
		setTitle("Prenotazione Risorse");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setBackground(new Color(214, 255, 255));

		// Creazione dei componenti
		JLabel dataLabel = new JLabel("Data:");
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");

		JLabel oraLabel = new JLabel("Ora:");
		oraComboBox = new JComboBox<>(sceltaOrario());
		oraComboBox.setSelectedIndex(0); // Imposto l'ora predefinita a 8

		JLabel durataLabel = new JLabel("Durata (ore):");
		durataComboBox = new JComboBox<>(sceltaDurata());

		JLabel risorsaLabel = new JLabel("Risorsa:");
		risorsaComboBox = new JComboBox<>(sceltaOrario());

		confermaButton = new JButton("Conferma Prenotazione");

		Font font = new Font("Arial", Font.PLAIN, 22);

		dataLabel.setFont(font);
		oraLabel.setFont(font);
		durataLabel.setFont(font);
		risorsaLabel.setFont(font);
		confermaButton.setFont(font);
		
		oraComboBox.setFont(font);
		durataComboBox.setFont(font);
		risorsaComboBox.setFont(font);
		dateChooser.setFont(font);

		
			
		// Layout del frame
		setLayout(new GridLayout(5, 2));
		add(dataLabel);
		add(dateChooser);
		add(oraLabel);
		add(oraComboBox);
		add(durataLabel);
		add(durataComboBox);
		add(risorsaLabel);
		add(risorsaComboBox);
		add(new JLabel()); // spazio vuoto per avere il bottone a destra
		add(confermaButton);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		validate();
		
		setVisible(true);
	}

	public JButton getConfermaButton() {
		return confermaButton;
	}

	public String getData() {
		Date selectedDate = dateChooser.getDate();

		// Formatto la data come stringa usando quel formato
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// Restituisco la data formattata come stringa
		return dateFormat.format(selectedDate);
	}

	public String getOra() {
		return (String) oraComboBox.getSelectedItem();
	}

	public Integer getDurata() {
		return (Integer) durataComboBox.getSelectedItem();
	}

	public String getRisorsa() {
		return (String) risorsaComboBox.getSelectedItem();
	}

	private String[] sceltaOrario() {
		// Creo un array di orari disponibili da 8 a 18
		String[] orarioDisponibile = new String[11];
		for (int i = 0; i < 11; i++) {
			int ora = 8 + i;
			orarioDisponibile[i] = String.format("%02d:00", ora); // Formatto l'ora come "HH:00"
		}
		return orarioDisponibile;
	}

	private Integer[] sceltaDurata() {
		// Creo un array di durate disponibili da 1 a 4 ore
		return new Integer[] { 1, 2, 3, 4 };
	}
	
	

}
