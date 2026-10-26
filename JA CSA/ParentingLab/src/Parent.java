public class Parent {
	// initialize variables
	final static double maxposX = 50;
	final static double minposX = -50;
	final static double maxposY = 50;
	final static double minposY = -50;
	private Child child1, child2;
	private double posX, posY;
	static double totalTraveled;

	//make constructors
	
	public Parent(Child child1, Child child2, double x, double y) {//all parameter constructor
		super();
		this.child1 = child1;
		this.child2 = child2;
		if (x <= 50.00)
			this.posX = x;
		else
			this.posX = 0.0;
		if (y <= 50.00)
			this.posY = y;
		else
			this.posY = 0.0;
	}

	public Parent() {//reused all parameters constructor
		this(null, null, 0.0, 0.0);
	}
	
	//getters and setters

	public Child getChild1() {
		return child1;
	}

	public void setChild1(Child child1) {
		this.child1 = child1;
	}

	public Child getChild2() {
		return child2;
	}

	public void setChild2(Child child2) {
		this.child2 = child2;
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

	public static double getTotalTraveled() {
		return totalTraveled;
	}

	@Override
	public String toString() {
		return "Parent:\n Child 1: " + child1 + "\n Child 2: " + child2 + "\n Parent X-Coordinate: " + posX
				+ "\n Parent Y-Coordinate: " + posY + "\n";
	}

	/**
	 * checks if a parent's child1, child2, or both are null
	 * 
	 * @param c1 (child1)
	 * @param c2 (child2)
	 * @return0 if none are null, 1 if only child 1 is null, 2 if only child 2 is
	 *          null, and 3 if both are null
	 */
	private int childrenNull(Child c1, Child c2) {
		int output = 0;
		if (c1 == null) {
			output = 1;
		}
		if (c2 == null) {
			output = 2;
		}
		if (c1 == null && c2 == null) {
			output = 3;
		}

		return output;
	}

	/**
	 * moves the children to the parent
	 */
	public void gatherChildren() {

		if (!(childrenNull(child1, child2) == 1 || childrenNull(child1, child2) == 3)) {// if child 1 isnt null
			child1.move(posX, posY);
		}

		if (!(childrenNull(child1, child2) == 2 || childrenNull(child1, child2) == 3)) {// if child2 isnt null
			child2.move(posX, posY);
		}

	}

	/**
	 * give the distance between the child and parent
	 * 
	 * @param c (the child who we are checking the distance between them and the
	 *          parent)
	 * @return the distance separating the child and parent
	 */
	private double parentChildDistance(Child c) {
		// initialize variables
		double distance = 0.0;
		if (c != null) {// if the singular child exists
			double childX = c.getPosX();
			double childY = c.getPosY();
			// find the hypotenuse (the diagonal distance)
			distance = distance(childX, childY);
		}
		return distance;
	}

	/**
	 * returns the distance between
	 * 
	 * @param newX
	 * @param newY
	 * @return
	 */
	private double distance(double newX, double newY) {
		// a^2 + b^2 = c^2
		double a_sqrd = Math.pow(newX - posX, 2);// (distance between current X and new X)^2
		double b_sqrd = Math.pow(newY - posY, 2);// (distance between current y and new y)^2
		double final_distance = Math.sqrt(a_sqrd + b_sqrd);
		return final_distance;
	}

	private void move(double newX, double newY) {
		if (!(newX > maxposX || newX < minposX || newY > maxposY || newY < minposY)) {// if not out of bounds
			// move the parent
			totalTraveled += distance(newX, newY);
			posX = newX;
			posY = newY;
		}
	}

	/**
	 * moves the parent to the closest child
	 * 
	 * @return the name of the child they went to
	 */
	public String moveToClosest() {
		String child_name = "";
		if (childrenNull(child1, child2) == 0) {// if neither child is null
			if (parentChildDistance(child1) > parentChildDistance(child2)) {// if child 2 is closer
				totalTraveled += this.parentChildDistance(child2);
				// move the parent to child 2
				this.move(child2.getPosX(), child2.getPosY());
				// return child 2's name
				child_name = child2.getName();
			} else if (parentChildDistance(child1) < parentChildDistance(child2)) {// if child 1 is closer
				totalTraveled += this.parentChildDistance(child1);
				// move the parent to child1
				this.move(child1.getPosX(), child1.getPosY());
				// return child 1's name
				child_name = child1.getName();
			} else {// if they happen to be equal then move to child 1
				totalTraveled += this.parentChildDistance(child1);
				// move the parent to child 1
				this.move(child1.getPosX(), child1.getPosY());
				// return child 1's name
				child_name = child1.getName();
			}
			if (childrenNull(child1, child2) == 2) {// if child 2 is null but child1 is not, then move to child 1
				totalTraveled += this.parentChildDistance(child1);
				// move the parent to child 1
				this.move(child1.getPosX(), child1.getPosY());
				// return child 1's name
				child_name = child1.getName();
			}
			if (childrenNull(child1, child2) == 1) {// if child 1 is null but child2 is not, then move to child 2
				totalTraveled += this.parentChildDistance(child2);
				// move the parent to child 2
				this.move(child2.getPosX(), child2.getPosY());
				// return child 2's name
				child_name = child2.getName();
			}

		}

		return child_name;
	}

	/**
	 * given a specific point, move the parent as well as any children that are
	 * within 1 unit of the parent
	 * 
	 * @param x (the x-coord of the desired point)
	 * @param y (the y-coord of the desired point)
	 * @return how many people moved (1, 2, or 3)
	 */
	public int moveWith(double x, double y) {
		int score = 0;// the number which shows what the parent was able to do

		if (x > maxposX || x < minposX || y > maxposY || y < minposY)// if out of bounds
			return score;// no one moved
		else {
			score++;// at least the parent is moving, so +1 point
			if (!(childrenNull(child1, child2) == 1 || childrenNull(child1, child2) == 3)) {// if child1 is not null
				if (parentChildDistance(child1) < 1) {// if the 1st child is less than 1 unit away from parent
					score++;// now the score will be 2 because at least the parent and 1 child is move
					child1.move(x, y);// move the 1st child
				}
			}
			if (!(childrenNull(child1, child2) == 2 || childrenNull(child1, child2) == 3)) {// if child2 is not null
				if (parentChildDistance(child2) < 1) {// if the 2nd child is less than 1 unit away from the parent
					score++;// now the score will increase by 1 (either 2 or 3) because at least one child
							// and the parent are moving
					child2.move(x, y);// move the 2nd child
				}
			}
			this.move(x, y);// move the parent
		}
		return score;
	}

	/**
	 * compares one parent to another through seeing how close each parent is to
	 * their children and judges them based on the distances the parent closer to
	 * their children is the better parent (if the score returned is negative, then
	 * parent 1 is farther away from their children, meaning they are the worse
	 * parent)
	 * 
	 * @param p (parent 2)
	 * @return how much closer parent 1 is to their children (if negative, then
	 *         parent 1 is the worse parent)
	 */
	public double compareTo(Parent p) {
		double p1_score = 0.0;
		double p2_score = 0.0;
		double p1_rating = 0.0;

		if (childrenNull(child1, child2) != 1 || childrenNull(child1, child2) != 3) {// if this.child1 is not null
			p1_score += parentChildDistance(child1);// adds the distance between child1 and parent 1 to parent 1's score
		}
		if (childrenNull(child1, child2) != 2 || childrenNull(child1, child2) != 3) {// if this.child2 is not null
			p1_score += parentChildDistance(child2);// adds the distance between child2 and parent 1 to parent 1's score
		}

		if (childrenNull(p.getChild1(), p.getChild2()) != 1 || childrenNull(p.getChild1(), p.getChild2()) != 3) {// if
																													// the
																													// other
																													// parent's
																													// child
																													// 1
																													// is
																													// not
																													// null
			p2_score += parentChildDistance(p.getChild1());// adds the distance between parent 2's child 1 and parent 2
															// to parent 2's score
		}
		if (childrenNull(p.getChild1(), p.getChild2()) != 2 || childrenNull(p.getChild1(), p.getChild2()) != 3) {// if
																													// the
																													// other
																													// parent's
																													// child
																													// 2
																													// is
																													// not
																													// null
			p2_score += parentChildDistance(p.getChild2());// adds the distance between parent 2's child 2 and parent 2
															// to parent 2's score
		}

		p1_rating = p2_score - p1_score;// find out how much closer parent 1 is to their children (how much better is
										// parent 1 compared to parent 2)
		return p1_rating;
	}

}