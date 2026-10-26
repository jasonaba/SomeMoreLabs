public class Vehicle {
	private static double initial, multiplier;
	private String name;
	private int condition, miles;

	// Constructors
	public Vehicle() {
		name = "Honda Accord";
		condition = 5;
		miles = 0;
		initial = 20000;
		multiplier = 8000;
	}

	public Vehicle(String name, int condition, int miles) {
		this.name = name;
		this.condition = condition;
		this.miles = miles;
		initial = 20000;
		multiplier = 8000;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public int getMiles() {
		return miles;
	}

	public void setMiles(int miles) {
		this.miles = miles;
	}

	public static double getInitial() {
		return initial;
	}

	public static double getMultiplier() {
		return multiplier;
	}

	/**
	 *
	 * @return the value of the vehicle
	 */
	public double getValue() {
		return initial + (multiplier * condition);
	}

	/**
	 * 
	 * @return value of Vehicle when condition is 0
	 */
	public double getScrapValue() {
		return 0.0;// Vehicles do not have a scrap value--this is mainly for the subclasses
	}

	public String toString() {
		String output = "Type: " + this.getClass().toString().substring(6) + "\nName: " + this.name + "\nCondition: "
				+ condition + "\nMiles: " + this.miles;
		if (this.condition == 0 || this instanceof Car && this.miles >= 200){// if this vehicle's condition is zero OR if
																			// it is a car and it's mileage is 200K or
																			// more,
																			// then make it clear that the user is being
																			// shown the scrap value
			return output + "\nScrap Value: $" + this.getScrapValue() + "\n";
		}
		return output + "\nValue: $" + this.getValue() + "\n";//else, show the normal value
	}

}