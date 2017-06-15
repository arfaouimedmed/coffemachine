package coffee.machine;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coffee.machine.command.CommandMaker;
import coffee.machine.dto.CommandDrink;
import coffee.machine.dto.Drink;


public class Iteration_3_Test
{
		CommandMaker commandMaker;
		CommandDrink commandDrink;
	    @Before
	    public void setUp() {
	    }
	 
	    @After
	    public void tearDown() {
	    }
	 
	    @Test
	    public void testCommandOrangeJuice() throws Exception {
	    	
	    	commandDrink = CommandMaker.transformCommandDrink("O::");
	        assertEquals(Drink.ORANGE_JUICE, commandDrink.getDrink());
	        assertEquals(0, commandDrink.getSugar());
	        assertEquals(false, commandDrink.isStick());
	        assertEquals(false, commandDrink.isExtraHot());
	    }
	    @Test
	    public void testCommandCoffe() throws Exception {

	    	commandDrink = CommandMaker.transformCommandDrink("Ch::");
	        assertEquals(Drink.COFFEE, commandDrink.getDrink());
	        assertEquals(0, commandDrink.getSugar());
	        assertEquals(false, commandDrink.isStick());
	        assertEquals(true, commandDrink.isExtraHot());
	    }
	    @Test
	    public void testCommandTea() throws Exception {
	    	
	    	commandDrink = CommandMaker.transformCommandDrink("Th:2:0");
	        assertEquals(Drink.TEA, commandDrink.getDrink());
	        assertEquals(2, commandDrink.getSugar());
	        assertEquals(true, commandDrink.isStick());
	        assertEquals(true, commandDrink.isExtraHot());
	    }
	    @Test
	    public void testCommandChoclate() throws Exception {
	    	
	    	commandDrink = CommandMaker.transformCommandDrink("Hh:1:0");
	        assertEquals(Drink.CHOCLATE, commandDrink.getDrink());
	        assertEquals(1, commandDrink.getSugar());
	        assertEquals(true, commandDrink.isStick());
	        assertEquals(true, commandDrink.isExtraHot());
	    }
   }
