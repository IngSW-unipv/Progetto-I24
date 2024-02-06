package it.unipv.ingsw.UniBook.View;

import javax.swing.*;
import java.awt.*;
import it.unipv.ingsw.UniBook.Controller.BookingController;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.Booking;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;

public class BookingView extends JFrame {

	private BookingController controller;
	private Booking b;
	
	private JDateChooser dateChooser;

	private JComboBox<String> oraComboBox;

	private JComboBox<String> risorsaComboBox;

	private JComboBox<Integer> durataComboBox;

	private JButton confermaButton;

	private JButton storicoButton; // Nuovo pulsante
	private JButton cancellaButton;

	public BookingView() {
		//controller = new BookingController();
		b = new Booking();
		//this.controller = SingletonManager.getInstance().getBookingController();

		
		// Impostazioni del frame
		setTitle("Prenotazione Risorse");
		setSize(400, 200);

		getContentPane().setBackground(new Color(214, 255, 255));

		// Creazione dei componenti
		JLabel dataLabel = new JLabel("Data:");
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");

		JLabel oraLabel = new JLabel("Ora:");
		oraComboBox = new JComboBox<>(b.sceltaOrario());
		oraComboBox.setSelectedIndex(0); // Imposto l'ora predefinita a 8

		JLabel durataLabel = new JLabel("Durata (ore):");
		durataComboBox = new JComboBox<>(b.sceltaDurata());

		JLabel risorsaLabel = new JLabel("Risorsa:");
		risorsaComboBox = new JComboBox<String>(b.aggiornaJListRisorse().toArray(new String[0]));
		/*
		 toArray(new String[0]) è utilizzata per convertire la lista restituita dal metodo 
		 in un array di Stringhe, che è il tipo di dati accettato dal costruttore di JComboBox.
		  */
		
		
		
		
		confermaButton = new JButton("Conferma Prenotazione");

		storicoButton = new JButton("Storico Prenotazioni");
		cancellaButton = new JButton("Cancella Prenotazione");

		Font font = new Font("Arial", Font.PLAIN, 22);

		dataLabel.setFont(font);
		oraLabel.setFont(font);
		durataLabel.setFont(font);
		risorsaLabel.setFont(font);
		confermaButton.setFont(font);
		storicoButton.setFont(font);
		cancellaButton.setFont(font);

		oraComboBox.setFont(font);
		durataComboBox.setFont(font);
		risorsaComboBox.setFont(font);
		dateChooser.setFont(font);
		
		// Layout del frame
		setLayout(new GridLayout(6, 2));
		add(storicoButton);

		add(cancellaButton);
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

	public JButton getStoricoButton() {
		return storicoButton;
	}

	public JButton getCancellaButton() {
		return cancellaButton;
	}

	public String getData() {
		Date selectedDate = dateChooser.getDate();

		if (selectedDate == null) {
	        return "";
	    }
		
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

	public JComboBox<String> getRisorsaComboBox() {
		return risorsaComboBox;
	}



}
