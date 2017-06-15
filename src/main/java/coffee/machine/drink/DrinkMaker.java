package coffee.machine.drink;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.inject.Inject;

import coffee.machine.beverage.BeverageQuantityChecker;
import coffee.machine.dto.CommandDrink;
import coffee.machine.money.MoneyService;
import coffee.machine.notifier.EmailNotifier;
import coffee.machine.reporting.DashBoard;

public class DrinkMaker {
	@Inject
	private BeverageQuantityChecker beverageQuantityChecker;
	@Inject
	private EmailNotifier emailNotifier;
	
	public String infoMessage;	
	
public void makeDrink(CommandDrink commandDrink) {
	MoneyService moneyService = MoneyService.getInstance();
	DecimalFormat df = new DecimalFormat("#.##");
	if (this.beverageQuantityChecker.isEmpty(commandDrink.getDrink().getDrinkName())) {
		deliverInfoMessage("there is a shortage of " + commandDrink.getDrink().getDrinkName() + ", an email sent to the company so that they can come and refill the machine");
		this.emailNotifier.notifyMissingDrink(commandDrink.getDrink().getDrinkName());
	} else if (moneyService.getMoney() >= commandDrink.getDrink().getDrinkPrice()) {
		deliverInfoMessage("make drink " + commandDrink.getDrink().getDrinkName());
		deliverInfoMessage("old credit = " + df.format(moneyService.getMoney()));
		moneyService.subMoney(commandDrink.getDrink().getDrinkPrice());
		deliverInfoMessage("new credit = " + df.format(moneyService.getMoney()));
		DashBoard.getInstance().addDrink(commandDrink.getDrink());
	} else {
		deliverInfoMessage("credit insufficient, missing " + BigDecimal.valueOf( commandDrink.getDrink().getDrinkPrice() - moneyService.getMoney()).setScale(2));
	}
}
public void deliverInfoMessage(String message){
	this.infoMessage = message;
	System.out.println(message);
}
public void resetInfoMessage(){
	this.infoMessage = "";
}

}
