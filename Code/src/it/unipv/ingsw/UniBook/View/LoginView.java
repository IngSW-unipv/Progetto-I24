package it.unipv.ingsw.UniBook.View;

import javax.swing.*;
import java.awt.*;


public class LoginView extends JFrame {

	private JPanel loginPanel;
	private JTextField matricolaField;
	private JPasswordField passwordField;
	private JButton confirmButton;


	public LoginView() {

		setTitle("Login");
		setSize(400, 150);
		

		loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		loginPanel.setBackground(new Color(214, 255, 255));
		add(loginPanel);
		
		loginPanel.add(new JLabel("Matricola:"));
		matricolaField = new JTextField();
		loginPanel.add(matricolaField);

		loginPanel.add(new JLabel("Password:"));
		passwordField = new JPasswordField();
		loginPanel.add(passwordField);

		confirmButton = new JButton("Accedi");

		loginPanel.add(confirmButton);

		// Centra la finestra
		setLocationRelativeTo(null);

		// Imposta la chiusura dell'applicazione quando si chiude la finestra
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
		
	}

	public JPanel getRegistrationPanel() {
		return loginPanel;
	}

	public String getMatricola() {
		return matricolaField.getText();
	}
	
	public char[] getPassword() {
		return passwordField.getPassword();
	}
	
	public JButton getConfirmButton() {
		return confirmButton;
	}

}
