package it.unipv.ingsw.UniBook.Strategy;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class DiscountFactory {

	private IDiscountStrategy type;
	private final String PROPERTYNAME = "discount.strategy.class.name";
	private final String DISCOUNTAMOUNT = "discount.amount";

	public IDiscountStrategy getDiscountStrategy() {

		if (type == null) {

			Properties p = new Properties(System.getProperties());
			String ClassName;
			double amount;
			try {
				p.load(new FileInputStream("properties/properties"));
				ClassName = p.getProperty(PROPERTYNAME);
				amount = Double.parseDouble(p.getProperty(DISCOUNTAMOUNT));

				Constructor c = Class.forName(ClassName).getConstructor(double.class);
				type = (IDiscountStrategy) c.newInstance(amount);

			} catch (Exception e) {
				e.printStackTrace();
				type = null;
			}

		}

		return type;
	}

}
