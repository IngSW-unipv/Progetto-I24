package it.unipv.ingsw.UniBook.DB;

import java.util.List;

import it.unipv.ingsw.UniBook.Model.Messaggio;
import it.unipv.ingsw.UniBook.Model.User;

public interface IMessaggioDAO {

	public List<Messaggio> getMessaggi(User mittente, User destinatario);
	public void inserisciMessaggio(Messaggio messaggio);
	public List<Messaggio> getMessaggiNonLetti(User mittente, User destinatario);
	public void setMessaggioLetto(Messaggio messaggio);
	
}
