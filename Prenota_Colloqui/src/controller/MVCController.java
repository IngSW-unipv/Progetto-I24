package controller;
import model.ModelManagerSingleton;
import view.MainFrame;

public class MVCController {

	private static MainFrame view;
	private static ModelManagerSingleton m;
	
	public MVCController(MainFrame view, ModelManagerSingleton m) {
		
		this.view = view;
		this.m = m;
		
		this.addListeners();
	}
	
	private void addListeners() {
		//aggiunge listener per ogni controller
		AutenticationController.addListeners(m, view);
		BookingController.addListeners(m, view);
		HomeController.addListeners(m,view);
		
		
	}
	
}
