package it.unipv.ingsw.UniBook.View;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PaymentView extends JFrame {
	
	JLabel cardNumberLabel;
	JTextField cardNumberField;
	JLabel cvvLabel;
	JTextField cvvField;
	JLabel amountLabel;
	JButton payButton;
	
	public PaymentView(Double price) {
		cardNumberLabel = new JLabel("Numero carta:");
        cardNumberField = new JTextField();

        cvvLabel = new JLabel("CVV:");
        cvvField = new JTextField();

        amountLabel = new JLabel("Totale: "+price+"€");
        
        payButton = new JButton("Procedi");
        
        Font font = new Font("Arial", Font.PLAIN, 22);
        cardNumberLabel.setFont(font);
        cardNumberField.setFont(font);
        cvvLabel.setFont(font);
        cvvField.setFont(font);
        payButton.setFont(font);
        amountLabel.setFont(font);
 
        GridLayout g = new GridLayout(4, 2);
        g.setHgap(10);
        g.setVgap(10);
        setLayout(g);
        
        add(cardNumberLabel);
        add(cardNumberField);
        add(cvvLabel);
        add(cvvField);
        add(amountLabel);
        add(payButton);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width/4, screenSize.height/4);
		validate();

		setVisible(true);

	}

	public String getCardNumberField() {
		return cardNumberField.getText();
	}


	public String getCvvField() {
		return cvvField.getText();
	}


	public JButton getPayButton() {
		return payButton;
	}
	
	
	
}
