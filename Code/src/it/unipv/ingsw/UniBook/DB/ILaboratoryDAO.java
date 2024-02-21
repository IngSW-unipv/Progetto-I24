package it.unipv.ingsw.UniBook.DB;

import java.util.ArrayList;

import it.unipv.ingsw.UniBook.Model.Resource;

public interface ILaboratoryDAO {

	public ArrayList<Resource> selectAllLaboratory();
	public int getCapacity(Resource r);

}
