package it.unipv.ingsw.UniBook.View;

import javax.swing.*;
import java.awt.*;

public class RegistrationView extends JFrame {

	private JPanel registrationPanel;
	private JTextField nomeField;
	private JTextField cognomeField;
	private JTextField matricolaField;
	private JTextField emailField;
	private JComboBox <String> tipoField;
	private JComboBox <String> corsoField;
	private JPasswordField passwordField;
	private JPasswordField confermaPasswordField;
	private JButton registerButton;
	private JButton switchToLoginButton;

	public RegistrationView() {

		setTitle("Registrazione");
		setSize(400, 400);
		

		registrationPanel = new JPanel(new GridLayout(9, 2, 10, 10));
		registrationPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		registrationPanel.setBackground(new Color(214, 255, 255));
		add(registrationPanel);
		
		registrationPanel.add(new JLabel("Nome:"));
		nomeField = new JTextField();
		registrationPanel.add(nomeField);
	
		registrationPanel.add(new JLabel("Cognome:"));
		cognomeField = new JTextField();
		registrationPanel.add(cognomeField);
		
		registrationPanel.add(new JLabel("Matricola:"));
		matricolaField = new JTextField();
		registrationPanel.add(matricolaField);

		registrationPanel.add(new JLabel("Email:"));
		emailField = new JTextField();
		registrationPanel.add(emailField);

		registrationPanel.add(new JLabel("Tipo:"));
        String[] tipo = {"Professore", "Ricercatore", "Studente"};
		tipoField = new JComboBox<>(tipo);
		registrationPanel.add(tipoField);
		
		
		registrationPanel.add(new JLabel("Corso:"));
		String[] corso = {"INF", "ELE", "BIO", "IND", "MEC", "GES"};
		corsoField = new JComboBox<>(corso);
		registrationPanel.add(corsoField);

		registrationPanel.add(new JLabel("Password:"));
		passwordField = new JPasswordField();
		registrationPanel.add(passwordField);

		registrationPanel.add(new JLabel("Conferma Password:"));
		confermaPasswordField = new JPasswordField();
		registrationPanel.add(confermaPasswordField);

		registerButton = new JButton("Registrati");
		switchToLoginButton = new JButton("Vai al Login");

		registrationPanel.add(registerButton);
		registrationPanel.add(switchToLoginButton);

		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
		
	}

	public JPanel getRegistrationPanel() {
		return registrationPanel;
	}
	
	public String getNome() {
		return nomeField.getText();
	}
	
	public String getCognome() {
		return cognomeField.getText();
	}

	public String getMatricola() {
		return matricolaField.getText();
	}

	public String getEmail() {
		return emailField.getText();
	}

	public String getCorso() {
		return (String) corsoField.getSelectedItem();
	}

	public String getTipo() {
		return (String) tipoField.getSelectedItem();
	}
	
	public char[] getPassword() {
		return passwordField.getPassword();
	}

	public char[] getConfermaPassword() {
		return confermaPasswordField.getPassword();
	}
	
	public JButton getRegisterButton() {
		return registerButton;
	}
	
	public JButton getLoginButton() {
		return switchToLoginButton;
	}

}
