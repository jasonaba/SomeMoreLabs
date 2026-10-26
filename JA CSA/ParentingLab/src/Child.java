public class Child {

	// initiating variables
	final static double maxposX = 50;
	final static double minposX = -50;
	final static double maxposY = 50;
	final static double minposY = -50;

	// child's variables
	private String name;
	private double posX;
	private double posY;
	private String direction;
	static double totalTraveled = 0;

	// constructors
	// default
	public Child() {//reused all parameters constructor
		this("Johnny", 0.0, 0.0, "North");
	}

	// all parameters
	public Child(String name, double posX, double posY, String direction) {
		super();
		this.name = name;
		if(posX <= 50.00)
		this.posX = posX;
		else
			this.posX = 0.0;
		if(posY <= 50.00)
		this.posY = posY;
		else
			this.posY = 0.0;
		this.direction = direction;
	}

	// toString
	@Override
	public String toString() {
		return "Child name: " + name + "\n X-position: " + posX + "\n Y-Position: " + posY + "\n Direction: "
				+ direction + "\n";
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public static double getTotalTraveled() {
		return totalTraveled;
	}

	// methods
	// math max and math.min will be helpful for moving
	/**
	 * moving in the direction facing
	 * 
	 * @param dist (given a distance)
	 * @return (return the length moved--it can possibly not move as far)
	 */
	public double move(double dist) {
		double length_moved = 0;
		if (direction.equals("North")) {
			totalTraveled += Math.min(dist, maxposY - posY);// if the distance between the border and the person
															// vs that between the person and if they were to
															// move the specific distance, then choose the
															// smaller one
			length_moved += Math.min(dist, maxposY - posY);
			posY = Math.min(posY + dist, maxposY);// y is getting more positive
		}
		if (direction.equals("South")) {
			totalTraveled += Math.min(dist, posY - minposY);
			length_moved += Math.min(dist, posY - minposY);
			posY = Math.max(posY - dist, minposY);// y is getting more negative
		}
		if (direction.equals("East")) {
			totalTraveled = Math.min(dist, maxposX - posX);// whichever is smaller
			length_moved += Math.min(dist, maxposX - posX);
			posX = Math.min(posX + dist, maxposX);// x is getting more positive
		}
		if (direction.equals("West")) {
			totalTraveled += Math.min(dist, posX - minposX);// whichever is smaller on the coordinate plane
			length_moved += Math.min(dist, posX - minposX);
			posX = Math.max(posX - dist, minposX);// x is going to be getting more negative
		}
		return length_moved;
	}

	/**
	 * will turn the child right or left
	 * 
	 * @param right (if the person wants to turn right, they say true, else, false)
	 * @return the direction tehy are now facing
	 */
	public String turn(boolean right) {
		String new_dir = "";
		if (right) {// if it needs to turn right, set its direction to whatever direction is right
					// of its current direction
			if (direction.equals("North"))
				new_dir = "East";
			if (direction.equals("East"))
				new_dir = "South";
			if (direction.equals("South"))
				new_dir = "West";
			if (direction.equals("West"))
				new_dir = "North";
		} else {// if it needs to turn left, set the direction to whatever is left of its
				// current direction
			if (direction.equals("North"))
				new_dir = "West";
			if (direction.equals("East"))
				new_dir = "North";
			if (direction.equals("South"))
				new_dir = "East";
			if (direction.equals("West"))
				new_dir = "South";
		}
		direction = new_dir;// sets the new direction to whatever it should be
		return new_dir;// returns the new direction
	}

	/**
	 * finds the hypotenuse of the right triangle which is made when moving the
	 * child and makes that the distance
	 * 
	 * @param newX (the x-coord of the place they want to go)
	 * @param newY (the y-coord of the place they want to go)
	 * @return if they could move or not
	 */
	private double distance(double newX, double newY) {
		// a^2 + b^2 = c^2
		double a_sqrd = Math.pow(newX - posX, 2);// (distance between current X and new X)^2
		double b_sqrd = Math.pow(newY - posY, 2);// (distance between current y and new y)^2
		double final_distance = Math.sqrt(a_sqrd + b_sqrd);
		return final_distance;
	}

	/**
	 * moves the child to a certain spot
	 * 
	 * @param x (the x-coord of where the child should go)
	 * @param y (the y-coord of where the child should go)
	 * @return if the child can move there or not (don't go if the area is out of
	 *         bounds)
	 */
	public boolean move(double x, double y) {
		boolean canMove = false;

		if (x > maxposX || x < minposX || y > maxposY || y < minposY)
			return canMove;// if the point is out of bounds, they will not be able to move there and it
							// returns false
		else {
			// move and say the child could move
			totalTraveled += distance(x, y);
			posX = x;
			posY = y;
			canMove = true;
		}

		return canMove;
	}

	/**
	 * find the midpoint of the line between the two children (think of the children
	 * as points on a coordinate plane) and bring them to that specific area
	 * 
	 * @param c (the other child)
	 * @return the coords where they meet up
	 */
	public String meet(Child c) {
		String coords = "";
		double avg_x = 0.0;// x-coord where they will meet up in the middle
		double avg_y = 0.0;// y-coord where they will meet up in the middle

		// do midpoint formula
		avg_x = (this.posX + c.posX) / 2;// finds avg of the two childrens' x-coords
		avg_y = (this.posY + c.posY) / 2; // finds avg of the two childrens' y-coords

		// make two children teleport there
		// this child
		this.posX = avg_x;
		this.posY = avg_y;
		// other child
		c.posX = avg_x;
		c.posY = avg_y;

		// set coords to a string
		coords = "(" + avg_x + " " + avg_y + ")";

		return coords;
	}

	/**
	 * Makes child run in a box
	 * 
	 * @param dist (the max distance they should go w/o running out of bounds)
	 * @return how far they went in total
	 */
	public double runBox(double dist) {
		double distance_traveled = 0.0;
		double move_1 = 0.0;// the first move (which has to be equal to the third move
		double move_2 = 0.0;// the second move (which has to be equal to the fourth move

		// make the box
		// 1st side
		move_1 = move(dist);// it will return how far it went
		// add this to distance traveled
		distance_traveled += move_1;
		// turn right
		turn(true);
		// 2nd side
		move_2 = move(dist);
		distance_traveled += move_2;
		turn(true);
		// 3rd side
		distance_traveled += move(move_1);
		;
		turn(true);
		// 4th side
		distance_traveled += move(move_2);
		;
		turn(true);// so it's facing the original way

		return distance_traveled;
	}

	/**
	 * checks if two different children are equal to each other | if the child is
	 * checked against something pointing to the same block of data (i.e. itself),
	 * then it will return false (THEY MUST BE DIFFERENT CHILDREN)
	 * 
	 * @param c (the other child, not this one)
	 * @return true if they have the same name and are within 1 unit of each other,
	 *         false if otherwise
	 */
	public boolean equals(Child c) {
		// am i supposed to check if the children are actually existing or if they are
		// pointing to the same data?

		// to check if they are genuinely pointing towards the same thing
		if (this == c)
			return false;

		if (this.name.equals(c.name) && this.distance(c.posX, c.posY) < 1)
			return true;
		else
			return false;

	}

}