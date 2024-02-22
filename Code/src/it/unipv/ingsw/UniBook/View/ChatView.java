package it.unipv.ingsw.UniBook.View;

import javax.swing.*;

import it.unipv.ingsw.UniBook.Controller.ChatController;
import it.unipv.ingsw.UniBook.Model.Messaggio;
import it.unipv.ingsw.UniBook.Model.User;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChatView {
    
    private JFrame frame;
    private JTextArea chatArea;
    private JTextField messaggioField;
    private JButton inviaButton;
    private User mittente;
    private User destinatario;
    private ChatController controller;


    public ChatView(User mittente, User destinatario) {
    	
        this.mittente = mittente;
        this.destinatario = destinatario;
        this.controller = new ChatController(this, mittente, destinatario);
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

        inviaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inviaMessaggio();
            }
        });

        JPanel inviaPanel = new JPanel(new BorderLayout());
        inviaPanel.add(messaggioField, BorderLayout.CENTER);
        inviaPanel.add(inviaButton, BorderLayout.EAST);
        frame.add(inviaPanel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Avvia la chat
        controller.avviaChat();
    }

    public void aggiungiMessaggio(Messaggio messaggio) {
        String messaggioStr = String.format("[%s] %s: %s%n", messaggio.getDataOra(), messaggio.getMittente(), messaggio.getTesto());
        chatArea.append(messaggioStr);
    }

    public void inviaMessaggio() {
        String testoMessaggio = messaggioField.getText();
        if (!testoMessaggio.isEmpty()) {
            controller.inviaMessaggio(testoMessaggio);
            messaggioField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Inserisci un messaggio valido.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}






