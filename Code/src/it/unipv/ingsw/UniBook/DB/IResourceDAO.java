package it.unipv.ingsw.UniBook.DB;
import java.util.ArrayList;
import it.unipv.ingsw.UniBook.Model.Resource;

public interface IResourceDAO {
		
	public ArrayList<Resource> getAllBookableResources();
	public boolean insertRisorsaPrenotabile(Resource f);
	public boolean insertRisorsaAffittabile(Resource f);
	public boolean removeRisorsa(Resource f);
	public int getMaxID(); 
	public ArrayList<Resource> getResourceFile();
	public Resource getResourceById(int id);
	public ArrayList<Resource> getResourceByLab(Resource r);
	public ArrayList<Resource> getAllRentableResources();
	public ArrayList<Resource> getResourceByMatricola(String matricola);
	public boolean insertFile(Resource r);
	
}
