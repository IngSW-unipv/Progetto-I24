package it.unipv.ingsw.UniBook.View;

import javax.swing.*;

import it.unipv.ingsw.UniBook.Controller.ChatController;
import it.unipv.ingsw.UniBook.Model.Booking;
import it.unipv.ingsw.UniBook.Model.Messaggio;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.User;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChatView {

	private JFrame frame;
	private JTextArea chatArea;
	private JTextField messaggioField;
	private JButton inviaButton;
	private User mittente;
	private User destinatario;

	public ChatView(User mittente, User destinatario) {

		this.mittente = mittente;
		this.destinatario = destinatario;
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		frame = new JFrame("Chat con " + destinatario.getNome() + " " + destinatario.getCognome());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 300);

		chatArea = new JTextArea(10, 30);
		chatArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(chatArea);
		frame.add(scrollPane, BorderLayout.CENTER);

		messaggioField = new JTextField(20);
		inviaButton = new JButton("Invia");

		JPanel inviaPanel = new JPanel(new BorderLayout());
		inviaPanel.add(messaggioField, BorderLayout.CENTER);
		inviaPanel.add(inviaButton, BorderLayout.EAST);
		frame.add(inviaPanel, BorderLayout.SOUTH);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public JButton getInvia() {
		return inviaButton;
	}

	public String getMessaggio() {
		return messaggioField.getText();
	}

	public void setVisible(boolean b) {

	}
	
	public void avviaChat(List<Messaggio> messaggi) {

		for (Messaggio m : messaggi) {
			aggiungiMessaggio(m);
		}
		
	}
	
	public void aggiungiMessaggio(Messaggio messaggio) {
		String messaggioStr = String.format("[%s] %s: %s%n", messaggio.getDataOra(), messaggio.getMittente().getId(),
				messaggio.getTesto());
		chatArea.append(messaggioStr);
		messaggioField.setText("");
	}

	

}
