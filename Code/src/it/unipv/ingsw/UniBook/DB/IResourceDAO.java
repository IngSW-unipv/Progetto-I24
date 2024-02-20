package it.unipv.ingsw.UniBook.DB;
import java.util.ArrayList;
import it.unipv.ingsw.UniBook.Model.Resource;

public interface IResourceDAO {
		
	public ArrayList<String> selectAll();
	public boolean insertRisorsaPrenotabile(Resource f);
	public boolean insertRisorsaAffittabile(Resource f);
	public boolean removeRisorsa(Resource f);
	public int getMaxID(); 

}
