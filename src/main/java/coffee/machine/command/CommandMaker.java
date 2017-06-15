package coffee.machine.command;

import coffee.machine.drink.DrinkMaker;
import coffee.machine.dto.CommandDrink;
import coffee.machine.dto.Drink;

public class CommandMaker {
	
 public static CommandDrink transformCommandDrink(String command) throws IllegalArgumentException {
	 
	 CommandDrink commandDrink = new CommandDrink();
	 String[] commandDetails = null;

	 if (command != null) {
		 commandDetails = command.split(":");
	 	 if (commandDetails.length < 1 || commandDetails.length > 3 || commandDetails[0].length() > 2) {
	 		 throw new IllegalArgumentException("Invalid command");
	 	 } else {
	 		 
	 		switch(commandDetails[0].charAt(0)) {
		 		case 'T' : {
		 			commandDrink.setDrink(Drink.TEA);	
		 		break;
		 		}
				case 'H' : {
		 			commandDrink.setDrink(Drink.CHOCLATE);	
					break;
		 		}
				case 'C' : {
		 			commandDrink.setDrink(Drink.COFFEE);	
						
					break;
		 		}
				case 'O' : {
		 			commandDrink.setDrink(Drink.ORANGE_JUICE);
		 			if (commandDetails[0].length() > 1) {
		 		 		 throw new IllegalArgumentException("Invalid command");
		 			}
						
					break;
		 		}
				default : {
			 		 throw new IllegalArgumentException("Invalid command");
		 		}
	 		}
	 		
	 		if (commandDetails[0].length() == 2 && commandDetails[0].charAt(1) == 'h') {
	 			commandDrink.setExtraHot(true);
	 		}
	 		if (commandDetails.length > 1 && commandDetails[1].length() > 0) {
	 			try {
	 				commandDrink.setSugar(Integer.parseInt(commandDetails[1]));
	 			}catch(NumberFormatException nbrFmtException) {
			 		 throw new IllegalArgumentException("Invalid command");
	 			}
	 			if (commandDrink.getSugar() > 0) {
	 				commandDrink.setStick(true);
	 			}
	 		}
	 	 }
		 
	 }
	 
	 return commandDrink;
 }
 
 public static void makeCommandDrink(String command) {
	 CommandDrink commandDrink = new CommandDrink();
	 DrinkMaker drinkMaker = new DrinkMaker();
	 try{
	 commandDrink = transformCommandDrink(command);
	 drinkMaker.makeDrink(commandDrink);
	 }catch(IllegalArgumentException iae) {
		 drinkMaker.deliverInfoMessage(iae.getMessage());
	 }
 }
 
}
