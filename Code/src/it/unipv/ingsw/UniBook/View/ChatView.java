package it.unipv.ingsw.UniBook.View;

import javax.swing.*;

import it.unipv.ingsw.UniBook.Model.Messaggio;
import it.unipv.ingsw.UniBook.Model.User;
import java.awt.*;


public class ChatView extends JFrame{

	private JFrame frame;
	private JTextArea chatArea;
	private JTextField messaggioField;
	private JButton inviaButton;
	private User destinatario;

	public ChatView(User destinatario) {

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
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void aggiungiMessaggio(Messaggio messaggio) {
		String messaggioStr = String.format("[%s] %s: %s%n", messaggio.getDataOra(), messaggio.getMittente().getId(),
				messaggio.getTesto());
		chatArea.append(messaggioStr);
		messaggioField.setText("");
	}

	

}
