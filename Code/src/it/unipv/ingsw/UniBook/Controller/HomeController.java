package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.Model.Prenotazione;
import it.unipv.ingsw.UniBook.Model.Risorsa;
import it.unipv.ingsw.UniBook.View.BookingView;
import it.unipv.ingsw.UniBook.View.ManagementView;
import it.unipv.ingsw.UniBook.View.HomeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeController {

	private HomeView view;

	public HomeController(HomeView view) {
		this.view = view;
		initComponents();
	}

	private void initComponents() {

		ActionListener GR = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {
				
					//view.getbuttonGR();
					apriGestioneRisorseFrame();
			
				}
			};
			
		// Aggiungo il listener al bottone
		view.getbuttonGR().addActionListener(GR);
		
		ActionListener PR = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				manageAction();
			}

			private void manageAction() {
				
					//view.getbuttonPR();
					apriPrenotazioneRisorse();
			
				}
			};
			
		// Aggiungo il listener al bottone
		view.getbuttonPR().addActionListener(PR);

	}
	
	private void apriGestioneRisorseFrame() {
        
        ManagementView g = new ManagementView();
		Risorsa r=new Risorsa();
		ManagementController c = new ManagementController(g,r);
        g.setVisible(true);
    }
	
private void apriPrenotazioneRisorse() {
        
	BookingView v = new BookingView();
	Prenotazione p = new Prenotazione();
	BookingController c = new BookingController(p,v);
    v.setVisible(true);
    }
	
}