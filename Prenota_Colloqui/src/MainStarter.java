import javax.swing.JFrame;

import controller.MVCController;
import model.ModelManagerSingleton;
import model.Professor;
import model.Student;
import model.User;
import view.MainFrame;

public class MainStarter { 

	public static void main(String[] args) {
		
		// creo oggetto che rappresenta MainFrame
		MainFrame v = new MainFrame();
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
		
		
		ModelManagerSingleton m = ModelManagerSingleton.getIstance(); //creo istanza
		
		MVCController c = new MVCController(v,m);
		
		
		v.changeView("1"); 

	}

}
