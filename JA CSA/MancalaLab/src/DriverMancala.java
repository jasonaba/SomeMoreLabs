public class DriverMancala {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mancala m = new Mancala();
		
		/*all parameters constructor works int[] arr = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1, 1}; 
		Mancala m = new Mancala(arr, 0, 0, true);
		*/
		 /** 
		 * //these were all tests //System.out.println(m.toString());
		 * //System.out.println(m.piecesleft()); //m.makeMove(3);
		 * //System.out.println(m.makeMove(10));
		 */
		m.playGame();
		/*
		 * my moves: test1: P1: 2 P1: 0 P2: 8 P2: 12 (illegal so supposed to check it
		 * (works)) P2: 11
		 * 
		 * test2: P1: 2 P1: 3 (to check P2's move on 11 to make sure it doesn't make the
		 * 0th bucket also 0 (works)) P2: 8 P2: 11
		 * 
		 * test for customRule case (and ending case): P1: 2 P1: 0 P2: 8 P2: 11 P1: 1
		 * P2: 7(this should be one) P1: 2 P2: 6 (this should NOT be one--the opposite
		 * one has nothing in it, so the rule doesn't kick in) P1: 5 P2: 9 P1: 3 P2: 8
		 * P2: 10 (should be one--the opposite has 1) P1: 4 P2: 9 P2: 7 P2: 10 P1: 0 P2:
		 * 9 (should be one, bringing box2 to 17) P1: 2 P1: 4 P1: 5 (or else he will
		 * lose on the next move) P2: 8 P1: 3 (this is one) P2: 10 P2: 7 (just to stall)
		 * P1: 4 P2: 8 P1: 5 P2 IS THE WINNER--15 to 33!!!! (this works)
		 * 
		 * 
		 * another test for customRule case: P1:2, 0 P2: 8, 9 1: 1 2: 6 1: 3 2: 7 1: 2 2:
		 * 9, 8 1: 4 2: 11 1: 2 2: 9, 11, 10
		 * 
		 * test for tie ending (i used the array with all 1s):
		 * P1: 5,4 P2: 11, 10 P1: 1 P2: 7 P1: 2 P2: 8 P1: 3 P2: 9 P1: 5, 4, 5 
		 * ITS A TIE!
		 * 
		 */

	}

}