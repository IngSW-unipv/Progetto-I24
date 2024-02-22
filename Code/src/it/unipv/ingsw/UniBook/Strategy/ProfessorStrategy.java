package it.unipv.ingsw.UniBook.Strategy;

import it.unipv.ingsw.UniBook.Model.Renting;

public class ProfessorStrategy implements IDiscountStrategy {

	private double discount;

	public ProfessorStrategy(double d) {
		this.discount = d;
	}
	
	@Override
	public double getFinal(Renting r) {
		if(r.getU().getTipo().equals("Professore"))
		{
			return r.getPrice()*discount;
		}
		else return r.getPrice();
	}
	
	
	
}
