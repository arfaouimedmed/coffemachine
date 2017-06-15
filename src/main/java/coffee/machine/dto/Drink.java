package coffee.machine.dto;

public enum Drink {
	  
    ORANGE_JUICE("Orange juice", 0.6), COFFEE("Coffee", 0.6), CHOCLATE("Choclate", 0.5), TEA("Tea", 0.4) ;  
      
     private String drinkName ;
     private double price;
      
     private Drink(String drinkName, double price) {  
         this.drinkName = drinkName ; 
         this.price = price;
    }  
      
     public String getDrinkName() {  
         return  this.drinkName ;  
    }
     public double getDrinkPrice() {  
         return  this.price ;  
    } 
}
