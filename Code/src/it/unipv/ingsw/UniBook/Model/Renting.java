package it.unipv.ingsw.UniBook.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import it.unipv.ingsw.UniBook.Exception.*;
import it.unipv.ingsw.UniBook.Strategy.IDiscountStrategy;
import it.unipv.ingsw.UniBook.Strategy.DiscountFactory;

public class Renting {
	private Resource r;
	private User u;
	private String startDate;
	private String endDate;
	private double price;
	private IDiscountStrategy s;
	
	
	public Renting() {
		this.u = SingletonManager.getInstance().getLoggedUser();
		DiscountFactory f=new DiscountFactory();
		s=f.getDiscountStrategy();
	}
	
	public Renting(Resource r, User u, String startDate, String finishDate) {
		this.r = r;
		this.u = SingletonManager.getInstance().getLoggedUser();
		this.startDate = startDate;
		this.endDate = finishDate;
		
		DiscountFactory f=new DiscountFactory();
		s=f.getDiscountStrategy();
		setTotalPrice();
	}

	public ArrayList<Resource> updateJlistResource(){;
		ArrayList<Resource> RentableResources = SingletonManager.getInstance().getResourceDAO().getAllRentableResources();
		
		return RentableResources;
	}
	
	
	public boolean tryToRent() {
		Renting rent;
		Boolean result = false;
		rent = new Renting(r,u,startDate,endDate);
		try {
			result = check(rent);
			if(result) {
				PopupManager.showPopup("L'affitto per il periodo selezionato ha un costo di: "+rent.getPrice());
			}
			return result;
				
		}catch(EmptyFieldException e) {
			e.showPopup();
		}
		catch(ResourceAlreadyRentedException e) {
			e.showPopup();
		}
		catch(DatabaseException e) {
			e.showPopup();
		}
		return result;

		
	}
	
	
	public boolean tryToInsert() throws DatabaseException {
		Renting rent = new Renting(r,u,startDate,endDate);
		if(SingletonManager.getInstance().getRentingDAO().InsertRenting(rent)) {
			PopupManager.showPopup("Affitto effettuato con successo!");
			return true;
		}
		return false;
	}
	
	
	public boolean check(Renting r) throws EmptyFieldException,ResourceAlreadyRentedException,DatabaseException {
		Boolean c = true;
		try {
		if((r.getStartDate() == "")||(r.getEndDate() == ""))
		{
			c = false;
			throw new EmptyFieldException();
		}
		c = SingletonManager.getInstance().getRentingDAO().checkAvailability(r);
		}
		catch(ResourceAlreadyRentedException e){
			throw e;
		}
		catch(DatabaseException e) {
			throw e;
		}
		return c;
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
	
	public double getPrice() {
		return this.price;
	}
	
	public void setTotalPrice() {
		LocalDate date1 = LocalDate.parse(this.startDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    LocalDate date2 = LocalDate.parse(this.endDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    int daysBetween = (int)ChronoUnit.DAYS.between(date1, date2);
	    if(daysBetween == 0)
	    	this.price = r.getPrezzo();
	    else
	    	this.price = r.getPrezzo()*daysBetween;
		this.price = s.getFinal(this);
	}
	

}