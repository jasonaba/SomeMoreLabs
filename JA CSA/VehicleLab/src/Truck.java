
public class Truck extends Car {
	private static double depreciatingFraction;

	public Truck(String name, int condition, int miles) {
		super(name, condition, miles);
		depreciatingFraction = .95;// the percent at which it depreciates based on mileage (NOT CONNECTED TO SCRAP
									// VALUE)
	}

	public double getValue() {
		double output = super.getValue();
		for(int i = 0; i<= this.getMiles()/10; i++) {//for every 10k miles it compoundedly decreases
			output = output*depreciatingFraction;
		}
		return output;
	}
	
	/**
	 * Tows the vehicle given, setting it's condition to zero
	 * @param other (the vehicle being towed)
	 */
	public void tow(Vehicle other) {
		if(!(other instanceof Truck)) {//If the vehicle given is not a truck--you cannot tow trucks
			other.setCondition(0);//if it is a car, it will decrease the activeCars
		}
	}
}
