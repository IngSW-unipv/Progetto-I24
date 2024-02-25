package it.unipv.ingsw.UniBook.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

import it.unipv.ingsw.UniBook.Model.Messaggio;
import it.unipv.ingsw.UniBook.Model.SingletonManager;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.View.ChatView;

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
				}

			}

		};

		view.getInvia().addActionListener(invia);

		
	}

	//Metodo con cui inizializzo la chat caricando i messaggi precedenti
	public void caricaChat() {
		List<Messaggio> messaggi = SingletonManager.getInstance().getMessaggioDAO().getMessaggi(mittente, destinatario);
		
		//Setto come letti gli eventuali nuovi messaggi
		for (Messaggio mes : messaggi) {
			SingletonManager.getInstance().getMessaggioDAO().setMessaggioLetto(mes);
		}
		
		//Mostro i messaggi nella view
		updateChat(messaggi);
	}
	
	//Passo i messaggi alla view e li aggiungo
	public void updateChat(List<Messaggio> messaggi) {

		for (Messaggio m : messaggi) {
			view.aggiungiMessaggio(m);
		}
			
	}
	
	 
}
