import java.util.ArrayList;
import java.util.Collections;

public class Deck implements Comparable<Object> {// I was given a yellow squiggle, so I added the <Object> part (did I
													// need to?)

	// Class Variables
	private ArrayList<Integer> deck;

	// Constructors
	public Deck() {
		reset();
	}

	public Deck(ArrayList<Integer> list) {
		deck = list;
	}

	// Getters & Setters
	public ArrayList<Integer> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Integer> deck) {
		this.deck = deck;
	}

	// To String
	@Override
	public String toString() {
		String output = "Deck: ";
		if (this.deck != null) {
			int counter = 0;
			for (Integer card : deck) {
				output += card + " ";
				counter++;
				if (counter % 10 == 0)
					output += "\n";
			}
		}
		return output;
	}

	// Methods

	/**
	 * Reset the deck to be cards 1-52
	 */
	public void reset() {
		deck = new ArrayList<Integer>(52);
		for (int i = 1; i <= 52; i++) {
			deck.add(i);// sets the 0th card to 1, the 1st to 2, all the way up to 52
		}
	}

	/**
	 * Deal a certain amount of cards
	 * 
	 * @param num
	 * @param bottomdeal: If the cards will be dealt from the bottom or not
	 * @return the ArrayList of Integers that you "dealt" (the cards)
	 */
	public ArrayList<Integer> deal(int num, boolean bottomdeal) {
		ArrayList<Integer> output = new ArrayList<Integer>(num);

		if (this.deck != null) {
			int amount = Math.min(num, deck.size());// to check if the amount they asked for is too high
			if (bottomdeal) {
				for (int i = 0; i < amount; i++) {
					output.add(0, deck.get(deck.size() - 1));
					deck.remove(deck.size() - 1);
				}
			}
			if (!bottomdeal) {
				for (int i = 0; i < amount; i++) {
					output.add(deck.remove(0));// from the top, so it stays in order but they are just separated (and
												// the
												// top cards are added to the output up until the amount of cards is
												// reached)
				}
			}
		}
		return output;
	}

	/**
	 * Adds specific cards to the deck
	 * 
	 * @param moreCards
	 * @return How many cards were added
	 */
	public int addCards(ArrayList<Integer> moreCards) {
		int output = 0;
		if (this.deck == null) {
			deck = new ArrayList<Integer>();
		}

		for (int i = 0; i < moreCards.size(); i++) {// for each card possibly being added
			boolean inDeck = false;
			for (int c = 0; c < deck.size(); c++) {// check each card in original deck
				if (moreCards.get(i) == deck.get(c)) {// if the card's number is found in the deck
					inDeck = true;
				}
			}
			if (!inDeck) {// if the card isn't in the deck at all
				deck.add(moreCards.remove(i));
				output++; // to count how many cards were added
				i--;// to not skip the next card
				// this also checks when the deck is updated so there is never any dupes
			}
		}

		return output;
	}

	/**
	 * 
	 * @param cutSpot: desired place to cut the deck
	 * @return if cutting the deck was successful
	 */
	public boolean cut(int cutSpot) {

		boolean output = false;// if it is successful or not
		if (this.deck != null) {
			ArrayList<Integer> cut = new ArrayList<Integer>();
			if (cutSpot >= 0 && cutSpot < deck.size()) {// if the spot given isn't out of bounds
				cut = this.deal(cutSpot + 1, false);// code reusability
				this.addCards(cut);// code reusability
				output = true;
			}
		}
		return output;
	}

	/**
	 * Check whether one deck has the same cards as another deck (or not) and if it
	 * is shuffled around or in the same order (or not)
	 */
	@Override
	public int compareTo(Object deck2) {
		int output = -1;
		if (this.deck != null && deck2 instanceof Deck) {
			Deck d2 = (Deck) (deck2);
			if (d2.getDeck() != null) {
				if (d2.getDeck().size() == deck.size()) {// they need to be the same size in order to have the same
															// cards
					// for (int i = 0; i < deck.size(); i++) {
					if (deck.equals(d2.getDeck())) {
						// check each card and if they are exactly the same in the same order
						output = 0;// this is when it is perfect
					} else {
						// check if cards are the same but in different orders
						ArrayList<Integer> sorted_d1 = deck;
						Collections.sort(sorted_d1);
						ArrayList<Integer> sorted_d2 = d2.getDeck();
						Collections.sort(sorted_d2);
						if (sorted_d1.equals(sorted_d2))
							output = 1;
					}
				}
			}
		}
		// otherwise, they have to be different cards (therefore output = -1)
		return output;
	}

	public void shuffleRandom(int count, boolean show) {
		ArrayList<Integer> top = new ArrayList<Integer>();
		Deck t_deck = new Deck(top);
		ArrayList<Integer> bottom = new ArrayList<Integer>();
		Deck b_deck = new Deck(bottom);

		if (this.deck != null) {
			for (int i = 0; i < count; i++) {
				// cut random amount of cards (less than half of deck size)
				int cut_num = (int) (Math.random() * deck.size() / 2);// has to be less than deck.size/2 (so it starts
																		// at 0)

				// i couldn't figure out how to use code reusability with dealing the bottom in
				// a separate array in the correct order, so here's what I got:
				top = this.deal(deck.size() - cut_num, false);// puts the top half inside an array list (if cut_num is
																// 0,
																// then it will put the entire deck here)
				t_deck.setDeck(top);
				bottom = this.deal(deck.size(), false);// the rest of the cards will be the bottom ones, so it will put
														// them
														// inside this variable
				b_deck.setDeck(bottom);
				if (show)// to show the split at first
					System.out.println(top);
				while (bottom.size() > 7) {
					int rand = (int) (Math.random() * 5) + 3;// 3-7
					ArrayList<Integer> dealt = b_deck.deal(rand, false);
					top.addAll(0, dealt);// can't use addCards because it automatically adds it to the end

					if (show) // to show it after the extra cards were added
						System.out.println(top);

				}

				top.addAll(0, bottom);// after bottom is less than 7
				deck = top;
				if (show) {
					System.out.println(deck);
				}
			}
		}
	}

	public void shuffleInterlace(boolean whichFirst) {
		ArrayList<Integer> top = new ArrayList<Integer>();
		ArrayList<Integer> bot = new ArrayList<Integer>();
		if (this.deck != null) {
			if (deck.size() % 2 == 0) {// if it's even
				top = this.deal(deck.size() / 2, false);
				bot = new ArrayList<Integer>();
				bot.addAll(deck);
				deck.clear();
			} else {
				top = this.deal((deck.size() / 2) + 1, false);// top is the bigger ArrayList
				bot = new ArrayList<Integer>();
				bot.addAll(deck);
				deck.clear();
			}
			if (whichFirst) {// top will go first
				while (bot.size() != 0) {
					deck.add(top.remove(0));
					deck.add(bot.remove(0));
				}
				if (top.size() != 0) {// if top was larger than bot
					deck.add(top.remove(0));
				}
			} else {
				while (bot.size() != 0) {
					deck.add(bot.remove(0));
					deck.add(top.remove(0));
				}
				if (top.size() != 0) {
					deck.add(top.remove(0));
				}
			}
		}
	}

}