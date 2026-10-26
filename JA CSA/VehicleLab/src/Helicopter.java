public class Helicopter extends Vehicle {
	private int maxPassengerWeight;
	private static double scrapFraction;// what fraction of the original price the helicopter becomes once it is
										// scrapped/towed

	// Constructor

	public Helicopter(String name, int condition, int miles, int maxPassengerWeight) {
		super(name, condition, miles);
		this.maxPassengerWeight = maxPassengerWeight;
		scrapFraction = .3;
	}

	// Getters and Setters

	public int getMaxPassengerWeight() {
		return maxPassengerWeight;
	}

	public void setMaxPassengerWeight(int maxPassengerWeight) {
		this.maxPassengerWeight = maxPassengerWeight;
	}

	/**
	 * 
	 * @param v (the vehicle being rebuilt)
	 * @return a helicopter (so when set equal to this, the vehicle being rebuilt
	 *         will turn into a helicopter)
	 */
	public static Helicopter rebuild(Vehicle v) {
		return new Helicopter(v.getName() + "-Heli", v.getCondition(), v.getMiles(), 100);
	}

	/**
	 * @return The value of the helicopter
	 */
	public double getValue() {
		double output = super.getValue() + (5000 * maxPassengerWeight);
		return output;
	}

	/**
	 * @return The scrap value of the helicopter (when the condition is zero)
	 */
	public double getScrapValue() {// provides the scrap value of the helicopter
		return this.getValue() * scrapFraction;
	}

	/**
	 * Shows the maximum passenger weight when a vehicle is a helicopter
	 */
	public String toString() {
		return super.toString() + "Max Passenger Weight: " + this.getMaxPassengerWeight() +"K\n";
	}

}