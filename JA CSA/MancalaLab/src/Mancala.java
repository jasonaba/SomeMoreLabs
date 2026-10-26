import java.util.Scanner;

public class Mancala {
	private int[] board;// the board is created with 12 'buckets'
	private int box1, box2, rockcount, pitnumber;// the scores
	private boolean turn1;// if it is player 1's turn

	// constructors
	public Mancala() {
		rockcount = 4;// so if someone wants to change the number they can do it here or through the
						// all parameters constructor
		pitnumber = 12;//just in case anyone wants to change the number
		board = new int[pitnumber];
		for (int i = 0; i < board.length; i++) {
			board[i] = rockcount;
		}
		box1 = 0;
		box2 = 0;
		turn1 = true;
	}

	public Mancala(int[] brd, int b1, int b2, boolean t1) {
		super();
		this.board = brd;
		this.box1 = b1;
		this.box2 = b2;
		this.turn1 = t1;
	}

	// To String Method
	public String toString() {
		String output = "Bottom left bucket is index 0 \n Top right bucket is index " + board.length / 2
				+ "\n\t--------------------\n\t|";

		for (int i = 11; i > board.length / 2 - 1; i--) {// shows player 2's pits
			output += " " + board[i] + " ";
		}
		output += "|\n Box2 (" + box2 + "\t\t    " + box1 + ") Box1 \n\t|";
		for (int i = 0; i < board.length - board.length / 2; i++) {// shows player 1's pits
			output += " " + board[i] + " ";
		}
		if (turn1) {
			output += "|\n \t--------------------\n \t    Player1 Turn \n";
		} else if (!turn1)
			output += "|\n \t--------------------\n \t    Player2 Turn \n";
		return output;
	}

	// Getters and Setters

	public int[] getBoard() {
		return board;
	}

	public void setBoard(int[] board) {
		this.board = board;
	}

	public int getBox1() {
		return box1;
	}

	public void setBox1(int box1) {
		this.box1 = box1;
	}

	public int getBox2() {
		return box2;
	}

	public void setBox2(int box2) {
		this.box2 = box2;
	}

	public boolean isTurn1() {
		return turn1;
	}

	public void setTurn1(boolean turn1) {
		this.turn1 = turn1;
	}

	public String piecesleft() {
		int count = 0;
//for each bucket it adds the amount of rocks in it (this doesn't include the box values because they aren't apart of this array)
		for (int bucket : board)
			count += board[bucket];
		return "There are " + count;
	}

	private boolean gameOver() {
		int p1_check = 0;
		int p2_check = 0;
		for (int i = 0; i < board.length / 2; i++) {// checks player 1's pits
			p1_check += board[i];// tallies how much player 1 has in their pits
		}
		for (int i = board.length / 2; i < board.length; i++) {// checks player 2's pits
			p2_check += board[i];// tallies how much player 2 has in their pits
		}
		if (p1_check == 0) {// if player 1 has no rocks in their pits
			box2 += p2_check;// give player 2 all the rocks in their own pits
			return true;// end the game
		}
		if (p2_check == 0) {// if player2 has no rocks in their pits
			box1 += p1_check;// give player 1 all the rocks in their own pits
			return true;// end the game
		}

		return false;// if both of them have at least one rock in one of their pits, keep the game
						// playing
	}

	/**
	 * resets the board to default settings when the game is finished/when this is
	 * called in the driver method (is this supposed to be public because it isn't
	 * specified to be private)
	 */
	public void resetBoard() {
		for (int i = 0; i < board.length; i++) {
			board[i] = 4;
		}
		box1 = 0;
		box2 = 0;
		turn1 = true;
	}

	/**
	 * allows the game to play continuously in the driver by only calling this
	 * 
	 */
	public void playGame() {
		Scanner input = new Scanner(System.in);
		while (!gameOver()) {// while the game is still running, give the players the ability to play
								// continuously
			System.out.println(this.toString());
			int choice = input.nextInt();
			while (!makeMove(choice)) {// checks if the move isn't possible to be made/illegal (this is checked over
										// and over, so the game runs no matter what)
				System.out.println("That was an illegal move--try another move");
				choice = input.nextInt();// allows
			}
		}
		// when the game is over
		String results = "Winner: ";
		if (box1 > box2)
			results += "Player 1! \n";
		if (box2 > box1)
			results += "Player 2! \n";
		if (box2 == box1)
			results += "It's a tie! \n";
		results += "Player 1 score: " + box1 + "\nPlayer 2 score: " + box2;
		System.out.println(results);
		resetBoard();
		input.close();
	}

	private boolean customRule(int lastBox) {
		if (turn1 && lastBox >= 0 && lastBox <= board.length / 2 - 1 && board[lastBox] == 1
				&& board[board.length - 1 - lastBox] > 0) {
			// if the last box player 1 ended on has 1 in it currently (because you added
			// that one) and the opposite pit has at least one rock in it
			return true;
		}
		if (!turn1 && lastBox >= board.length / 2 && lastBox <= board.length - 1 && board[lastBox] == 1
				&& board[board.length - 1 - lastBox] > 0) {
			// if the last box player 2 ended on has 1 in it currently (because you added
			// that one) and the opposite pit has at least one rock in it
			return true;
		}
		return false;

	}

	/**
	 * 
	 * checks if your move is valid and makes you move
	 * 
	 * @param bucket
	 * @return if they are able to make that move or not
	 */
	public boolean makeMove(int bucket) {
		if (turn1 && bucket >= 0 && bucket < board.length / 2 && board[bucket] > 0) {
			move(bucket);
			return true;
		}
		if ((!turn1) && bucket > board.length / 2 - 1 && bucket < board.length && board[bucket] > 0) {// if plr2's turn,
																										// its one of
																										// their boxes,
																										// and
			move(bucket); // that bucket has at least one stone
			return true;
		}
		return false;// if its not in the player's own buckets
	}

	/**
	 * the method that actually makes the pits move
	 * 
	 * @param choice (the bucket they want to take rocks from).
	 */
	private void move(int choice) {
		int temp = 0;
		boolean addedinpit;
		temp = board[choice];// holds all the stones in the "choice" bucket
		board[choice] = 0;
		for (int i = choice + 1; temp > 0; i++) {// as long as you have rocks in your hand, then do this for each bucket
			addedinpit = false;
			if (i == board.length)// if it goes oob
				i = 0;// so it doesn't go out of bounds
			if (turn1 && i == board.length / 2 && temp > 0) {// if it reaches the player's storage pit with at least one
																// rock
				box1++;
				temp--;
			}
			if (!turn1 && i == 0 && temp > 0) {// this is the previous thing but for player 2
				box2++;
				temp--;
			}
			if (temp > 0) {// if the player does not place temp's last rock in the box
				board[i] += 1;// add one to the next bucket
				temp--;// subtract it from temp
				addedinpit = true;
			}
			if (turn1 && temp == 0 && customRule(i)) {// if this is the last pit and customRule is true and its player
														// 1's turn
				box1 += board[i] + board[board.length - 1 - i];
				// zero the pits out
				board[i] = 0;
				board[board.length - 1 - i] = 0;
			}
			if (!turn1 && temp == 0 && customRule(i)) {// if this is the last pit and customRule is true and its player
														// 2's turn
				box2 += board[i] + board[board.length - 1 - i];
				// zero the pits out
				board[i] = 0;
				board[board.length - 1 - i] = 0;
			}
			if ((temp == 0 && turn1 && i == board.length / 2 && !addedinpit)
					|| (!turn1 && i == 0 && temp == 0 && !addedinpit))
				// if the player ended by placing the last rock in their bucket
				break;// don't change their turn--they go again
			else if (turn1 && temp == 0) {// if they run out in any other way, their turn changes
				turn1 = false;
			} else if (!turn1 && temp == 0)
				turn1 = true;

		}
	}
}
