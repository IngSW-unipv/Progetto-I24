package it.unipv.ingsw.UniBook;

import javax.swing.JFrame;

import it.unipv.ingsw.UniBook.Controller.HomeController;
import it.unipv.ingsw.UniBook.View.HomeView;

public class Start {

	public static void main(String[] args) {

		HomeView f = new HomeView();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		HomeController controller = new HomeController(f);
		 
	}

}
