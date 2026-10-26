public class Playing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test default child to see if it works
		Child a = new Child();
		//System.out.println(d);// print original child i made

		/*
		 * this all works System.out.println(d.move(60)); System.out.println(d +
		 * "\n");// print after it moved
		 * 
		 * d.turn(true); System.out.println(d.move(45.00, 59.00)); System.out.println(d
		 * + "\n"); // print it after it might've teleported
		 */

		// create second child to do meet(Child) function
		Child b = new Child("Jason", -.5, 0, "West");

		/*
		 * this works // meet d & e together and print to make sure they are in the
		 * middle System.out.println(d.meet(e)); System.out.println(e + "\n");
		 * System.out.println(d + "\n");
		 */

		/*
		 * this works too System.out.println(e.runBox(30) + "\n"); System.out.println(e
		 * + "\n");
		 */

		/*
		 * this works System.out.println(e.move(9,32)); System.out.println(e + "\n");//
		 * prints e's info System.out.println(Child.getTotalTraveled());
		 */
		//System.out.println(e.equals(e));

		
		//test parent
		
		Parent p1 = new Parent(a, b, -20, 52);
		// p1.gatherChildren(); -- works
		//jason is closer when his coords are (0, 16) and parents are (0, 50), and johnny is closer when jason's coords are (48,16)
		System.out.println(p1.moveToClosest()); //-- works
		System.out.println(p1.moveWith(50, 50)); //-- works
		System.out.println(p1);
		
		Child c = new Child("Kathy", 50, 19, "East");
		Child d = new Child("Tim", 12, 0, "South");
		
		Parent p2 = new Parent (c, d, 0, 12);
		System.out.println(p2);
		System.out.println(p1.compareTo(p2));
		
		Parent p3 = new Parent();
		System.out.println(p3);
		System.out.println(Parent.getTotalTraveled());
		System.out.println(Child.getTotalTraveled());
	}

}