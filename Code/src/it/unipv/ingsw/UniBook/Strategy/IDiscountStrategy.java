package it.unipv.ingsw.UniBook.Strategy;

import it.unipv.ingsw.UniBook.Model.Renting;

public interface IDiscountStrategy {

	double getFinal(Renting r);
}
