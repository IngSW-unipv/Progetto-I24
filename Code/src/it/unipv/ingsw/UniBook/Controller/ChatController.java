package it.unipv.ingsw.UniBook.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.text.View;

import it.unipv.ingsw.UniBook.DB.MessaggioDAO;
import it.unipv.ingsw.UniBook.DB.UserDAO;
import it.unipv.ingsw.UniBook.Exception.PopupManager;
import it.unipv.ingsw.UniBook.Model.Messaggio;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.View.ChatView;
import it.unipv.ingsw.UniBook.View.DeleteBookingView;

public class ChatController {
	private ChatView view;
	private Messaggio m;
	private User mittente;
	private User destinatario;

	public ChatController(ChatView view, User mittente, User destinatario) {
		this.view = view;
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.m = new Messaggio();
		initComponents();
	}

	private void initComponents() {
		
		caricaChat();

		ActionListener invia = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {

				m.setMittente(mittente);
				m.setDestinatario(destinatario);
				m.setDataOra(LocalDateTime.now().withSecond(2).withNano(0));
				m.setLetto(false);
				m.setTesto(view.getMessaggio());

				if (m.provaInvio()) {
					view.aggiungiMessaggio(m);
				}else {
					PopupManager.showPopup("Inserisci un messaggio valido");
				}

			}

		};

		view.getInvia().addActionListener(invia);

	}

	public void caricaChat() {
		List<Messaggio> messaggi = SingletonManager.getInstance().getMessaggioDAO().getMessaggi(mittente, destinatario);
		view.avviaChat(messaggi);
	}

	/*
	 * public List<Messaggio> getMessaggiNonLetti() { return
	 * SingletonManager.getInstance().getMessaggioDAO().getMessaggiNonLetti(
	 * destinatario, mittente); }
	 * 
	 * 
	 * 
	 * public void setMessaggioLetto(Messaggio messaggio) {
	 * messaggioDAO.setMessaggioLetto(messaggio); }
	 */

}
