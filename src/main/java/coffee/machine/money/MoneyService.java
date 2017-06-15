package coffee.machine.money;

import java.math.BigDecimal;

public class MoneyService {
	
private double money;

private static MoneyService instance = null;

private MoneyService() {
	this.money = 0;
}



public static MoneyService getInstance() {
	if ( instance == null ) {
        instance = new MoneyService();
    }
    return instance;
}
public  void addMoney(double value) {
	instance.money += value;
}
public void subMoney(double value) {
	instance.money = BigDecimal.valueOf(instance.money).subtract(BigDecimal.valueOf(value)).setScale(2).doubleValue();
}

public double getMoney() {
	return instance.money;
}

public  void setMoney(double money) {
	instance.money = money;
}

}
