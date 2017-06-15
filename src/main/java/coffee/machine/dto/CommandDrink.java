package coffee.machine.dto;

public class CommandDrink {
	private Drink drink;
	private int sugar;
	private boolean stick;
	private boolean extraHot;
	
	public Drink getDrink() {
		return drink;
	}
	public void setDrink(Drink drink) {
		this.drink = drink;
	}
	public int getSugar() {
		return sugar;
	}
	public void setSugar(int sugar) {
		this.sugar = sugar;
	}
	public boolean isStick() {
		return stick;
	}
	public void setStick(boolean stick) {
		this.stick = stick;
	}
	public boolean isExtraHot() {
		return extraHot;
	}
	public void setExtraHot(boolean extraHot) {
		this.extraHot = extraHot;
	}
	
}
