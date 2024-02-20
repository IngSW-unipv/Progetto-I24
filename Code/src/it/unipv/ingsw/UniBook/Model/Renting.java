package it.unipv.ingsw.UniBook.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import it.unipv.ingsw.UniBook.DB.RentingDAO;
import it.unipv.ingsw.UniBook.DB.ResourceDAO;

public class Renting {
	private Resource r;
	private User u;
	private String startDate;
	private String finishDate;
	private RentingDAO rDAO;
	
	public Renting() {
		this.rDAO = SingletonManager.getInstance().getRentingDAO();
		this.u = SingletonManager.getInstance().getLoggedUser();
	}
	
	public Renting(Resource r, User u, String startDate, String finishDate) {
		this.rDAO = SingletonManager.getInstance().getRentingDAO();
		this.r = r;
		this.u = u;
		this.startDate = startDate;
		this.finishDate = finishDate;
	}

	public ArrayList<Resource> updateJlistResource(){
		ResourceDAO resDAO = SingletonManager.getInstance().getResourceDAO();
		ArrayList<Resource> RentableResources = resDAO.getAllRentableResources();
		
		return RentableResources;
	}
	
	
	public ArrayList<Renting> getUserRenting(User u){
		return rDAO.selectRentingFromUser(u);
	}
	
	public Resource getR() {
		return r;
	}

	public void setR(Resource r) {
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

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

}