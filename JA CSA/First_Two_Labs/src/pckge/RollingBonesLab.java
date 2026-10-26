//Jason Abankwah | 1 B/D
package pckge;

import java.util.Scanner;

public class RollingBonesLab {

	// initialize the all same number payout, rolls in a row payout, the two
	// rolls add up to the third payout, and the amount of money started with, all of which will never change.

	final static int samewin = 30;
	final static int rowwin = 10;
	final static int addingwin = 5;
	final static int startmoney = 100;// for if they play the game and not simulate, this starting price enables them

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// initialize variables for dice, trials, money, input, if the person wants to
		// run a simulation or do their own game, and if they want to play again
		int trials = 0;
		int die1 = 0;
		int die2 = 0;
		int die3 = 0;
		double cost = 0;
		double money = 100;
								// to compare the money they gain/lose after they play matches
		boolean runSim = false;
		boolean playAgain = false;
		Scanner input = new Scanner(System.in);
//begin asking them questions pertaining to game
		want2play(input, runSim, playAgain, trials, cost, startmoney, money, die1, die2, die3);

	}

	public static void want2play(Scanner input, boolean runSim, boolean playAgain, int trials, double cost,
			double startmoney, double money, int d1, int d2, int d3) {
		// ask user if they want to run a simulation or play it as a game
		System.out.println("Do you want to run a simulation (IF YOU WANT TO RUN YOUR OWN GAME, INPUT 'false')?");
		runSim = input.nextBoolean();
		if (runSim) {
			// asks them how many times they want to simulate the game, the amount of the
			// cost, then runs it
			System.out.println("How many trials would you like to run?");
			trials = input.nextInt();
			System.out.println("How much money will it cost to play 1 game?");
			cost = input.nextInt();
			money = simGame(cost, money, trials, d1, d2, d3);// simulate the game and set money equal to the simulation
			System.out.println(money);
		} else {// if they don't want to run a simulation, then they play the game with the
				// normal value for cost
			cost = 3;
			money = playGame(input, playAgain, cost, startmoney, money, d1, d2, d3);
		}
		// if they run out of sufficient funds to play then they can't play again (for
		// simulation and normal game)
		if (money < cost) {
			System.out.println("You have run out of money. Thanks for playing!");
		} else {// if they want to play again and they have enough money to, then the game
				// repeats with the same amount of money as last time
			System.out.println("Would you like to try again or run a simulation (TRUE OR FALSE)?");
			playAgain = input.nextBoolean();
			if (playAgain) {// if true, then restarts the game--the final money in the normal game (not the
							// simulated
				// game) will be used if they want to play the normal game again
				want2play(input, runSim, playAgain, trials, cost, startmoney, money, d1, d2, d3);
			}
		}
	}

	// playing the game
	public static double playGame(Scanner input, boolean playAgain, double cost, double startmoney, double money,
			int d1, int d2, int d3) {
		// subtract cost from money to begin game
		money -= cost;
		// roll three 6-sided die (and make it look fancy)
		d1 = (int) (Math.random() * 6) + 1;
		d2 = (int) (Math.random() * 6) + 1;
		d3 = (int) (Math.random() * 6) + 1;
		System.out.println("--------------");
		System.out.println(" | " + d1 + " | " + d2 + " | " + d3 + " |");
		System.out.println("--------------");
		// if the roll gave all the same number, add the corresponding payout to the
		// money
		if (isAllSame(d1, d2, d3)) {
			money += samewin;
		}
		// if the roll gave numbers in a row, add the corresponding payout to the money
		if (inRow(d1, d2, d3)) {
			money += rowwin;
		}
		// if the roll gave two numbers that add up to the third, add the corresponding
		// payout to the money
		if (rollAddsUp(d1, d2, d3)) {
			money += addingwin;
		}
		// tell the user the value of their roll (whether the criteria is true or false)
		System.out.println("Are all the rolls the same? " + isAllSame(d1, d2, d3));
		System.out.println("Are the rolls in a row? " + inRow(d1, d2, d3));
		System.out.println("Do two of the rolls add up to the third? " + rollAddsUp(d1, d2, d3));
		//tell the user the money they ended with and if they gained/lost money or if they didn't change in the amount they had compared to when they started
		System.out.println("You ended with " + money + "!");
		if (money > startmoney)
			System.out.println("That means you gained a profit of " + (money - startmoney) + "!");
		else if (money < startmoney)
			System.out.println("That means you lost " + (startmoney - money) + ".");
		else
			System.out.println("That means you didn't gain any money (not bad though!).");
//return the money to be used later
		return money;
	}

	// if they choose to simulate the game, run this so nothing is printed during
	// each simulation
	public static double simGame(double cost, double money, int trials, int d1, int d2, int d3) {
		// set the money to zero
		money = 0;
		// iterate this for the number of trials they wanted
		for (int i = 1; i <= trials; i++) {
			// subtract money from cost
			money = money - cost;
			// roll the three die and set the value to the variable
			d1 = (int) (Math.random() * 6) + 1;
			d2 = (int) (Math.random() * 6) + 1;
			d3 = (int) (Math.random() * 6) + 1;
			// if the values rolled are all the same, add the corresponding payout to the
			// money
			if (isAllSame(d1, d2, d3)) {
				money += samewin;
			}
			// if the roll gave numbers in a row, add the corresponding payout to the money
			if (inRow(d1, d2, d3)) {
				money += rowwin;
			}
			// if the roll gave two numbers that add up to the third, add the corresponding
			// payout to the money
			if (rollAddsUp(d1, d2, d3)) {
				money += addingwin;
			}
		}
		// show the user the money they earned/lost and tell them if they did good or
		// bad

		System.out.println("You earned: " + money);
		if (money > 0) {
			System.out.println("You gained a profit! That means you won!");
		} else {
			System.out.println("You didn't gain a profit. Maybe you shouldn't play this game.");
		}
		return money;
	}

	public static boolean isAllSame(int d1, int d2, int d3) {
		// if all the numbers equal each other, return true
		if ((d1 == d2) && (d1 == d3)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean inRow(int d1, int d2, int d3) {
		// check if the rolls were in a row or not
		if ((d1 + 1 == d2) && (d2 + 1 == d3)) {// die1, die2, die3
			return true;
		} else if ((d1 + 1 == d3) && (d3 + 1 == d2)) {// die1, die3, die2
			return true;
		} else if ((d2 + 1 == d3) && (d3 + 1 == d1)) {// die2, die3, die1
			return true;
		} else if ((d2 + 1 == d1) && (d1 + 1 == d3)) {// die2, die1, die3
			return true;
		} else if ((d3 + 1 == d1) && (d1 + 1 == d2)) {// die3, die1, die2)
			return true;
		} else if ((d3 + 1 == d2) && (d2 + 1 == d1)) {// die3, die2, die1)
			return true;
		} else {
			return false;
		}
	}

	public static boolean rollAddsUp(int d1, int d2, int d3) {
		// check if two of the die values add up to the third
		if (d1 + d2 == d3) {// die1 + die2 = die3
			return true;
		} else if (d1 + d3 == d2) {// die 1 + die3 = die2
			return true;
		} else if (d2 + d3 == d1) {// die2 + die3 = die1
			return true;
		} else {
			return false;
		}
	}
}