package it.unipv.ingsw.UniBook.DB;
import java.util.ArrayList;
import it.unipv.ingsw.UniBook.Model.Risorsa;

public interface IRisorsaDAO {
		
		public ArrayList<String> selectAll();
		public boolean insertRisorsa(Risorsa f);

	
}
