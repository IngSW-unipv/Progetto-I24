package it.unipv.ingsw.UniBook.View;

import javax.swing.*;
import java.awt.*;
import it.unipv.ingsw.UniBook.Controller.BookingController;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.Renting;
import it.unipv.ingsw.UniBook.Model.Resource;

import java.util.Date;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;
import java.util.Calendar;

public class RentingView extends JFrame {
	
	//private RentingController controller;
	private Renting r;
	private JDateChooser startDate;
	private JDateChooser finishDate;
	private JComboBox <Resource> resources;
	private JButton confirmButton;
	private JButton cancelButton;
	
	public RentingView() {
		r = new Renting();

		// Impostazioni del frame
		setTitle("Affitto Risorse");
		setSize(400, 200);

		getContentPane().setBackground(new Color(214, 255, 255));
		JLabel dataStartLabel = new JLabel("Data inizio affitto:");
		
		startDate = new JDateChooser();
		startDate.setDateFormatString("dd/MM/yyyy");
		Calendar minDate = Calendar.getInstance();
		startDate.setMinSelectableDate(minDate.getTime());
		
		JLabel dataFinishLabel = new JLabel("Data fine affitto:");
		finishDate = new JDateChooser();
		finishDate.setDateFormatString("dd/MM/yyyy");
		finishDate.setMinSelectableDate(minDate.getTime());
		
		JLabel resourceLabel = new JLabel("Risorsa");
		resources = new JComboBox<Resource>(r.updateJlistResource().toArray(new Resource[0]));
		confirmButton = new JButton("Conferma");
		cancelButton = new JButton("Annulla");

		Font font = new Font("Arial", Font.PLAIN, 22);

		dataStartLabel.setFont(font);
		dataFinishLabel.setFont(font);
		resourceLabel.setFont(font);
		resources.setFont(font);
		startDate.setFont(font);
		finishDate.setFont(font);
		confirmButton.setFont(font);
		cancelButton.setFont(font);

		setLayout(new GridLayout(4, 2));
		
		add(dataStartLabel);
		add(startDate);
		add(dataFinishLabel);
		add(finishDate);
		add(resourceLabel);
		add(resources);
		add(cancelButton);
		add(confirmButton);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		validate();

		setVisible(true);
	}
}

