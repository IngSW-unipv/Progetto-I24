package it.unipv.ingsw.UniBook.View;

import javax.swing.*;
import java.awt.*;

public class ManagementView extends JFrame {

	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JCheckBox checkBox1;
	private JCheckBox checkBox2;
	private JButton button;

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

		textField1 = new JTextField();
		textField2 = new JTextField();

		panel1.add(nome);
		panel1.add(textField1);
		panel1.add(descrizione);
		panel1.add(textField2);
		panel1.setBackground(new Color(214, 255, 255));

		// Pannello interno con un titolo e due spunte
		JPanel panel2 = new JPanel(new GridLayout(3, 1));
		textField3 = new JTextField();
		checkBox1 = new JCheckBox("PRENOTABILE");
		checkBox2 = new JCheckBox("AFFITTABILE");
		checkBox1.setBackground(new Color(214, 255, 255));
		checkBox2.setBackground(new Color(214, 255, 255));
		panel2.setBackground(new Color(214, 255, 255));
		panel2.add(possibilità);
		panel2.add(checkBox1);
		panel2.add(checkBox2);

		// Pannello interno con un bottone al centro in basso
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		button = new JButton("CONFERMA");
		panel3.setBackground(new Color(214, 255, 255));
		button.setPreferredSize(new Dimension(1250, 100));
		panel3.add(button);

		// Aggiunta dei pannelli interni al pannello principale
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);

		// Aggiunta del pannello principale al frame
		add(mainPanel);

		// FONT
		Font font = new Font("Arial", Font.PLAIN, 25);
		textField1.setFont(font);
		textField2.setFont(font);
		textField3.setFont(font);
		checkBox1.setFont(font);
		checkBox2.setFont(font);
		button.setFont(font);
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
		return button;
	}

	public String getTextField1Text() {
		return textField1.getText();
	}

	public String getTextField2Text() {
		return textField2.getText();
	}

	public String getTextField3Text() {
		return textField3.getText();
	}

	public boolean isCheckBox1Selected() {
		return checkBox1.isSelected();
	}

	public boolean isCheckBox2Selected() {
		return checkBox2.isSelected();
	}

}
