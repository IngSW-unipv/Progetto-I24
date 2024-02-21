package it.unipv.ingsw.UniBook.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import it.unipv.ingsw.UniBook.DB.RentingDAO;
import it.unipv.ingsw.UniBook.DB.ResourceDAO;
import it.unipv.ingsw.UniBook.Exception.EmptyFieldException;
import it.unipv.ingsw.UniBook.Exception.OverbookingException;
import it.unipv.ingsw.UniBook.Exception.PopupManager;

public class Renting {
	private Resource r;
	private User u;
	private String startDate;
	private String endDate;
	
	public Renting() {
		this.u = SingletonManager.getInstance().getLoggedUser();
	}
	
	public Renting(Resource r, User u, String startDate, String finishDate) {
		this.r = r;
		this.u = u;
		this.startDate = startDate;
		this.endDate = finishDate;
	}

	public ArrayList<Resource> updateJlistResource(){;
		ArrayList<Resource> RentableResources = SingletonManager.getInstance().getResourceDAO().getAllRentableResources();
		
		return RentableResources;
	}
	
	
	public boolean tryToRent() {
		Renting rent;
		Boolean result = false;
		try {
			if(check()) {
				rent = new Renting(r,u,startDate,endDate);
				result = SingletonManager.getInstance().getRentingDAO().InsertRenting(rent);
			}
				
		}catch(EmptyFieldException e) {
			e.mostraPopup();
		} catch (OverbookingException e) {
			e.mostraPopup();
		}
		
		if(result) {
			PopupManager.mostraPopup("Affitto effettuato con successo");
		}
		return result;
		
	}
	
	public boolean check() throws EmptyFieldException,OverbookingException {
		return true;
	}
	
	public ArrayList<Renting> getUserRenting(User u){
		return SingletonManager.getInstance().getRentingDAO().selectRentingFromUser(u);
	}
	
	public Resource getResource() {
		return r;
	}

	public void setResource(Resource r) {
		this.r = r;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String finishDate) {
		this.endDate = finishDate;
	}

}