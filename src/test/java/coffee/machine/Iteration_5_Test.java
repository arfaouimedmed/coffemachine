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
import coffee.machine.notifier.EmailNotifier;


@RunWith(MockitoJUnitRunner.class)
public class Iteration_5_Test
{
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
	    public void testShortageBeverage() throws Exception {
	    	
	    	when(mockBeverageQuantityChecker.isEmpty("Coffee")).thenReturn(true);
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("C:2:0"));
	    	assertEquals("there is a shortage of Coffee, an email sent to the company so that they can come and refill the machine",drinkMaker.infoMessage);

	    	when(mockBeverageQuantityChecker.isEmpty("Orange juice")).thenReturn(true);
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("O:2:0"));
	    	assertEquals("there is a shortage of Orange juice, an email sent to the company so that they can come and refill the machine",drinkMaker.infoMessage);

	    	when(mockBeverageQuantityChecker.isEmpty("Choclate")).thenReturn(true);
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("H:2:0"));
	    	assertEquals("there is a shortage of Choclate, an email sent to the company so that they can come and refill the machine",drinkMaker.infoMessage);

	    	when(mockBeverageQuantityChecker.isEmpty("Tea")).thenReturn(true);
	    	drinkMaker.makeDrink(CommandMaker.transformCommandDrink("T:2:0"));
	    	assertEquals("there is a shortage of Tea, an email sent to the company so that they can come and refill the machine",drinkMaker.infoMessage);
	       
	    }
	    
}
