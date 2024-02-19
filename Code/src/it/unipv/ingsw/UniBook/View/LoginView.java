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

        loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        loginPanel.setBackground(new Color(214, 255, 255));
        add(loginPanel);

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        fieldsPanel.setBackground(new Color(214, 255, 255));
        loginPanel.add(fieldsPanel, BorderLayout.CENTER);

        fieldsPanel.add(new JLabel("Matricola:"));
        matricolaField = new JTextField();
        fieldsPanel.add(matricolaField);

        fieldsPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        fieldsPanel.add(passwordField);

        confirmButton = new JButton("Accedi");

        loginPanel.add(confirmButton, BorderLayout.SOUTH);

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
