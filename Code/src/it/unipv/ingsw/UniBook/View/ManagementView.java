package it.unipv.ingsw.UniBook.View;

import javax.swing.*;
import java.awt.*;

public class ManagementView extends JFrame {

	private JTextField nameR;
	private JTextField descriptionR;
	private JTextField text;
	private JCheckBox reservationCheck;
	private JCheckBox rentableCheck;
	private JButton confermButton;
	private JButton removeRButton;


	public ManagementView() {

		// Impostazioni del frame
		setTitle("Gestione risorse");
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Creazione del pannello principale
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// Pannello interno con due caselle di testo
		JPanel panel1 = new JPanel(new GridLayout(2, 1));
		JLabel nome = new JLabel("NOME");
		JLabel descrizione = new JLabel("DESCRIZIONE");
		JLabel possibilità = new JLabel("QUESTA RISORSA PUÓ ESSERE :");

		nameR = new JTextField();
		descriptionR = new JTextField();

		panel1.add(nome);
		panel1.add(nameR);
		panel1.add(descrizione);
		panel1.add(descriptionR);
		panel1.setBackground(new Color(214, 255, 255));

		// Pannello interno con un titolo e due spunte
		JPanel panel2 = new JPanel(new GridLayout(3, 1));
		text = new JTextField();
		reservationCheck = new JCheckBox("PRENOTABILE");
		rentableCheck = new JCheckBox("AFFITTABILE");
		reservationCheck.setBackground(new Color(214, 255, 255));
		rentableCheck.setBackground(new Color(214, 255, 255));
		panel2.setBackground(new Color(214, 255, 255));
		panel2.add(possibilità);
		panel2.add(reservationCheck);
		panel2.add(rentableCheck);

		// Pannello interno con bottone "CONFERMA" al centro in basso e pulsante "RIMUOVI RISORSA"
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		confermButton = new JButton("CONFERMA");
		panel3.setBackground(new Color(214, 255, 255));
		confermButton.setPreferredSize(new Dimension(1250, 100));
		panel3.add(confermButton);
		
		removeRButton = new JButton("RIMUOVI RISORSA");
		removeRButton.setPreferredSize(new Dimension(1000, 100));
		panel3.add(removeRButton);
		
		// Aggiunta del pannello principale al frame
		add(mainPanel);

		// Aggiunta dei pannelli interni al pannello principale
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);

		// FONT
		Font font = new Font("Arial", Font.PLAIN, 25);
		nameR.setFont(font);
		descriptionR.setFont(font);
		text.setFont(font);
		reservationCheck.setFont(font);
		rentableCheck.setFont(font);
		confermButton.setFont(font);
		removeRButton.setFont(font);
		nome.setFont(font);
		descrizione.setFont(font);
		possibilità.setFont(font);

		// Impostazioni finali del frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		validate();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Chiudi solo il frame corrente quando viene chiuso
		setLocationRelativeTo(null); // Posiziona il frame al centro dello schermo

	}

	public JButton getConfermaButton() {
		return confermButton;
	}

	public String getTextField1Text() {
		return nameR.getText();
	}
 
	public String getTextField2Text() {
		return descriptionR.getText();
	}

	public String getTextField3Text() {
		return text.getText();
	}

	public boolean CheckBoxPrenotabileSelected() {
		return reservationCheck.isSelected();
	}

	public boolean CheckBoxAffittabileSelected() {
		return rentableCheck.isSelected();
	}

}