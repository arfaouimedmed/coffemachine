package coffee.machine;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import coffee.machine.beverage.BeverageQuantityChecker;
import coffee.machine.command.CommandMaker;
import coffee.machine.drink.DrinkMaker;
import coffee.machine.dto.CommandDrink;
import coffee.machine.money.MoneyService;
import coffee.machine.notifier.EmailNotifier;


@RunWith(MockitoJUnitRunner.class)
public class Iteration_2_Test
{
		
		MoneyService moneyService = MoneyService.getInstance();
		
		CommandDrink commandDrink;

		@Mock
 	    EmailNotifier mockEmailNotifier;

		@Mock
 	    BeverageQuantityChecker mockBeverageQuantityChecker;

	    @InjectMocks
		DrinkMaker drinkMaker = new DrinkMaker ();
	    
	    @Before
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }
	 
	    @After
	    public void tearDown() {
	    }
	 
	    @Test
	    public void testMakeDrinkIfEnoughMoney() throws Exception {
	    	
	    	when(mockBeverageQuantityChecker.isEmpty("Coffee")).thenReturn(false);
	    	when(mockBeverageQuantityChecker.isEmpty("Choclate")).thenReturn(false);
	    	when(mockBeverageQuantityChecker.isEmpty("Tea")).thenReturn(false);
	    	
	    	moneyService.setMoney(0.8);
	    	commandDrink = CommandMaker.transformCommandDrink("C:2:0");
	    	drinkMaker.makeDrink(commandDrink);
	        assertEquals(Double.valueOf(0.2),Double.valueOf(moneyService.getMoney()));

	        
	        commandDrink = CommandMaker.transformCommandDrink("H::");
	    	drinkMaker.makeDrink(commandDrink);
	    	assertEquals("credit insufficient, missing 0.30",drinkMaker.infoMessage);
	    	
	    	moneyService.addMoney(0.3);
	    	commandDrink = CommandMaker.transformCommandDrink("H:2:0");
	    	drinkMaker.makeDrink(commandDrink);
	        assertEquals(Double.valueOf(0.0),Double.valueOf(moneyService.getMoney()));
	        
	        moneyService.addMoney(0.5);
	    	commandDrink = CommandMaker.transformCommandDrink("T:2:0");
	    	drinkMaker.makeDrink(commandDrink);
	        assertEquals(Double.valueOf(0.1),Double.valueOf(moneyService.getMoney()));

	    	
	    }
	    
}
