package coffee.machine.reporting;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import coffee.machine.dto.Drink;

public class DashBoard {

	private Map<Drink, Long> mapNumberDrinks = new HashMap<Drink, Long>();
	private static DashBoard instance = null;

	private DashBoard() {
		this.mapNumberDrinks = new HashMap<Drink, Long>();
	}
	public static DashBoard getInstance() {
		if ( instance == null ) {
	        instance = new DashBoard();
	        instance.initMapNumberDrinks();
	    }
	    return instance;
	}

	public void initMapNumberDrinks() {
	       instance.mapNumberDrinks.put(Drink.CHOCLATE, 0l);
	        instance.mapNumberDrinks.put(Drink.COFFEE, 0l);
	        instance.mapNumberDrinks.put(Drink.ORANGE_JUICE, 0l);
	        instance.mapNumberDrinks.put(Drink.TEA, 0l);
	}
	public  void addDrink(Drink drink) {
		instance.mapNumberDrinks.put(drink, instance.mapNumberDrinks.get(drink) + 1);
	}
	
	public  Long getNumberDrink(Drink drink) {
		return instance.mapNumberDrinks.get(drink);
	}
	public String reportDrinks() {
	    String output = new String();
	    for (Map.Entry<Drink, Long> entry : instance.mapNumberDrinks.entrySet()) {
	    	output += "Number of " + entry.getKey().getDrinkName() + " = " + entry.getValue() + "\n";
	    }
		
		return output;
	}
	
	public double moneyEarned() {
	    BigDecimal sum = new BigDecimal(0);
			for (Map.Entry<Drink, Long> entry : instance.mapNumberDrinks.entrySet()) {
				sum = sum.add(BigDecimal.valueOf(entry.getKey().getDrinkPrice()).multiply(BigDecimal.valueOf(entry.getValue())));

		    }

		return sum.setScale(2).doubleValue();
	}

}
