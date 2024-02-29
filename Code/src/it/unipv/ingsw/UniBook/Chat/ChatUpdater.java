package it.unipv.ingsw.UniBook.Chat;

import java.util.List;

import it.unipv.ingsw.UniBook.Controller.ChatController;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.Model.Messaggio;
import it.unipv.ingsw.UniBook.Model.SingletonManager;

public class ChatUpdater extends Thread {

	private ChatController controller;
	private User mittente;
	private User destinatario;
	private boolean running;

	public ChatUpdater(ChatController controller, User mittente, User destinatario) {
		this.controller = controller;
		this.mittente = mittente;
		this.destinatario = destinatario;
		this.running = true;
	}
	
	public ChatUpdater() {
		this.running = true;
	}

	@Override
	public void run() {
		
			while (running) {
				try {
	
					Thread.sleep(2000);
					// Prelevo i nuovi messaggi e li setto come letti una volta presi, cosi non continua a mostrarli
					List<Messaggio> messaggi = SingletonManager.getInstance().getMessaggioDAO().getMessaggiNonLetti(mittente, destinatario);
					
					for (Messaggio mes : messaggi) {
						SingletonManager.getInstance().getMessaggioDAO().setMessaggioLetto(mes);
					}
					
					controller.updateChat(messaggi);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	

	}

	public void stopUpdating() {
		running = false;
	}

}
