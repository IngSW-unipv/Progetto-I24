package it.unipv.ingsw.UniBook.Controller;

import java.util.List;
import it.unipv.ingsw.UniBook.DB.MessaggioDAO;
import it.unipv.ingsw.UniBook.Model.Messaggio;
import it.unipv.ingsw.UniBook.Model.User;
import it.unipv.ingsw.UniBook.View.ChatView;

public class ChatController {
    private ChatView view;
    private MessaggioDAO messaggioDAO;
    private User mittente;
    private User destinatario;


    public ChatController(ChatView view, User mittente, User destinatario) {
        this.view = view;
        this.messaggioDAO = new MessaggioDAO();
        this.mittente = mittente;
        this.destinatario = destinatario;
    }

    public void avviaChat() {
        List<Messaggio> messaggi = messaggioDAO.getMessaggi(mittente.getId(), destinatario.getId());
        for (Messaggio messaggio : messaggi) {
            view.aggiungiMessaggio(messaggio);
        }
    }

    public void inviaMessaggio(String testoMessaggio) {
        Messaggio nuovoMessaggio = new Messaggio();
        nuovoMessaggio.setMittente(mittente.getId());
        nuovoMessaggio.setDestinatario(destinatario.getId());
        nuovoMessaggio.setTesto(testoMessaggio);
        messaggioDAO.inserisciMessaggio(nuovoMessaggio);
        view.aggiungiMessaggio(nuovoMessaggio);
    }

    public List<Messaggio> getMessaggiNonLetti() {
        return messaggioDAO.getMessaggiNonLetti(destinatario.getId(), mittente.getId());
    }

    public void setMessaggioLetto(Messaggio messaggio) {
        messaggioDAO.setMessaggioLetto(messaggio);
    }
}



