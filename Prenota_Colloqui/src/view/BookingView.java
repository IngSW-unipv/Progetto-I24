package view;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;



public class BookingView extends AbstractFormView {

	private JComboBox<Integer> durataComboBox;
	private JComboBox<String> oraComboBox;
	private JFormattedTextField dateChooser;


    public BookingView() {
    	
    	super();
    	
    	l1.setText("Prenotazione Colloqui");

		addDate(0,20,"date");
		addTime(1,20,"time");
		addInput(2,20,"professor");
    }
	public String getData() {
		
		return (String) dateChooser.getValue();
		
	}

	public String getOra() {
		return (String) oraComboBox.getSelectedItem();
	}

	public Integer getDurata() {
		return (Integer) durataComboBox.getSelectedItem();
	}
	
}

