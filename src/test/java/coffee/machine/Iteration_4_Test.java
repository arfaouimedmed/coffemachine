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
import coffee.machine.dto.Drink;
import coffee.machine.money.MoneyService;
import coffee.machine.notifier.EmailNotifier;
import coffee.machine.reporting.DashBoard;

@RunWith(MockitoJUnitRunner.class)
public class Iteration_4_Test
{
		MoneyService moneyService = MoneyService.getInstance();
		DashBoard dashBoard = DashBoard.getInstance();
	    @Mock 
	    EmailNotifier mockEmailNotifier;
	    
	    @Mock 
	    BeverageQuantityChecker mockBeverageQuantityChecker;

	    @InjectMocks
		DrinkMaker drinkMaker = new DrinkMaker ();
	    
	    @Before	    
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	        dashBoard.initMapNumberDrinks();
	    	moneyService.setMoney(10);
	    }
	 
	    @After
	    public void tearDown() {
	    }
	 
	    @Test
	    public void testEarningMoneyDrinkSold() throws Exception {
	    	
	    	when(mockBeverageQuantityChecker.isEmpty("Coffee")).thenReturn(false);
	    	when(mockBeverageQuantityChecker.isEmpty("Choclate")).thenReturn(false);
	    	when(mockBeverageQuantityChecker.isEmpty("Tea")).thenReturn(false);
	    	when(mockBeverageQuantityChecker.isEmpty("Orange juice")).thenReturn(false);
	    	
	    	
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("C:2:0"));
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("Ch:1:0"));
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("C::"));
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("Ch::"));
	    	
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("H:2:0"));
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("Hh:1:0"));
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("H::"));
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("Hh::"));
	    	
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("T:2:0"));
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("Th:1:0"));
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("T::"));
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("Th::"));
	    	
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("O:2:0"));
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("O:1:0"));
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("O::"));
	    	
	    	assertEquals(Double.valueOf(7.8), Double.valueOf(dashBoard.moneyEarned()));

	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("C::"));
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("Ch::"));
	    	
	        assertEquals(Long.valueOf(6), dashBoard.getNumberDrink(Drink.COFFEE));
	        assertEquals(Double.valueOf(9), Double.valueOf(dashBoard.moneyEarned()));

	        assertEquals(Long.valueOf(4), dashBoard.getNumberDrink(Drink.CHOCLATE));
	        
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("Th::"));
	        assertEquals(Long.valueOf(5), dashBoard.getNumberDrink(Drink.TEA));

	        assertEquals(Long.valueOf(3), dashBoard.getNumberDrink(Drink.ORANGE_JUICE));
	       
	    }
	    
}
