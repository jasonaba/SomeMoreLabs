public class Car extends Vehicle {
	private static int  maxCondition, minCondition, maxScrapPrice, totalCars, activeCars, mileageThreshold;
	private static double conditionInterest, mileageDepreciation, scrapFraction/* same as helicopter class */;

	// Constructor
	public Car(String name, int condition, int miles) {
		super(name, condition, miles);
		maxCondition = 5;
		minCondition = 0;
		totalCars++;
		conditionInterest = 1000;
		if (!(this.getCondition() == minCondition && this.getMiles() >= mileageThreshold)) {// if the car isn't totaled and it's miles is less
																	// than 200K
			activeCars++;
		}
		scrapFraction = .1;
		maxScrapPrice = 2000;// the maximum price a car w/ condition 0 can be
		mileageDepreciation = 500;// how much the car decreases by based on it's mileage
		mileageThreshold = 200;
	}

	// Getters and Setters
	public static int getActiveCars() {
		return activeCars;
	}


	public static int getTotalCars() {
		return totalCars;
	}

	/**
	 * overwrite setMiles so activeCars decreases if it exceeds the threshold
	 */
	public void setMiles(int miles) {
		super.setMiles(miles);
		if(!(this.getMiles()<mileageThreshold))
			activeCars--;
	}

	/**
	 * overwrite setCondition so activeCars decreases if the car's condition reaches the threshold for cars to be unusable
	 */
	public void setCondition(int condition) {
		super.setCondition(condition);
		if(this.getCondition() == minCondition)
			activeCars--;
	}
	
	public double getValue() {
		return super.getValue() + (conditionInterest * this.getCondition());
	}

	public double getScrapValue() {
		double output = (super.getInitial() + (super.getMultiplier() * maxCondition)) + (conditionInterest * maxCondition);// this is the max amount it could
																					// be
		output = (output * scrapFraction) - (mileageDepreciation * this.getMiles());
		if (output < 0) {
			return 0.0;
		}
		if (output > maxScrapPrice) {
			return maxScrapPrice;
		}
		return output;
	}

	/**
	 * 
	 * @param c (the car being salvaged)
	 * @return changes the car given into a normal vehicle
	 */
	public static Vehicle salvage(Car c, String name) {
		return new Vehicle(name, c.getCondition(), c.getMiles());
	}
}