package it.unipv.ingsw.UniBook.Strategy;

import it.unipv.ingsw.UniBook.Model.Renting;

public class ResearcherStrategy implements IDiscountStrategy {

	private double discount;
	
	public ResearcherStrategy(double d) {
		this.discount = d;
	}

	@Override
	public double getFinal(Renting r) {
		if(r.getU().getTipo()=="Ricercatore")
		{
			return r.getPrice()*discount;
		}
		else return r.getPrice();
	}
	
}
