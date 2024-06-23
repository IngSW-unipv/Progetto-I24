package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginView extends AbstractFormView{
	
	private final String cardposition = "3";

	public LoginView() {
		
		super();
		
		l1.setText("Login");
		
		addInput(0,20,"username");
		addInput(1,20,"password");
		
	}
	
}
