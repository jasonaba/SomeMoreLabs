import java.util.ArrayList;
import java.util.Arrays;


public class Table {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Deck dealer = new Deck();
		System.out.println(dealer);
		
		//test deal()--looking good (check just to be safe)
		System.out.println();
		System.out.println("deal()");
		ArrayList<Integer> Player1 = dealer.deal(10, false);
		System.out.println(Player1);
		System.out.println(dealer);//takes first 10 cards away
		*/
		
		/*
		//test addCards()--looks good BUT can I use this to implement code reusability more?
		System.out.println();
		System.out.println("addCards()");
		ArrayList<Integer> addedcards = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 57, 142, 12, 190));
		System.out.println(addedcards);
		dealer.addCards(addedcards);
		System.out.println(dealer);
		System.out.println(addedcards);//should show only [12]
		//System.out.println(Player1);
		*/
		
		/*
		//test cut()--maybe check if necessary (although I believe I followed the instructions to a T)
		System.out.println();
		System.out.println("cut()");
		System.out.println(dealer);
		//System.out.println(dealer.cut(dealer.getDeck().size()/2));
		System.out.println(dealer.cut(2));
		System.out.println(dealer);
		System.out.println(dealer.cut(dealer.getDeck().size()));//should be false--WORKS
		*/
		
		/*
		//test compareTo--looking gooood
		System.out.println();
		System.out.println("compareTo()");
		Deck new_deck1 = new Deck();
		Deck new_deck2 = new Deck();
		
		//check for a perfect scenario
		System.out.println(new_deck1.compareTo(new_deck2));//this should be perfect
		System.out.println(new_deck1);
		System.out.println(new_deck2);
		System.out.println();
		
		//check for a wrong scenario
		System.out.println(dealer.compareTo(new_deck1));//this should be wrong (-1) because size is different
		System.out.println(dealer);
		System.out.println(new_deck1);
		System.out.println();
	
		//check for a mixed around scenario
		System.out.println(new_deck2.cut(new_deck2.getDeck().size()/2));//to change order of new_deck2 but keep same size
		System.out.println(new_deck2);
		System.out.println(new_deck2.compareTo(new_deck1));//should be 1
		System.out.println();
		
		//to check dealing from bottom as well as making EXTRA sure the perfect condition works
		System.out.println(new_deck2.deal(new_deck2.getDeck().size()/2, true));
		System.out.println(new_deck2);
		System.out.println(new_deck2.compareTo(new_deck2));
		System.out.println();
		*/
		
		/*
		//test reset()--loooooking gooooooood
		new_deck2.reset();
		System.out.println(new_deck2);
		System.out.println();
		*/
		
		/*
		//test setDeck() & Deck(list) constructor--both looking good
		ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));
		//System.out.println("setDeck()");
		//new_deck2.setDeck(nums);
		//System.out.println(new_deck2);
		System.out.println("Parameter Constructor");
		Deck new_deck3 = new Deck(nums);
		System.out.println();
		*/
		
		/*
		//test shuffleRandom()--check possibly
		System.out.println("shuffleRandom()");
		System.out.println(new_deck3);
		new_deck3.shuffleRandom(1, true);
		System.out.println();
		new_deck2.shuffleRandom(1, true);
		System.out.println();
		*/
		
		/*
		//test shuffleInterlace()--looking good
		System.out.println("shuffleInterlace()");
		//nums = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
		nums = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8)); //to make it odd
		Deck new_deck4 = new Deck(nums);
		new_deck4.shuffleInterlace(true);
		//new_deck4.shuffleInterlace(false);
		System.out.println(new_deck4);
		*/
		
		ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4));
		Deck checker1 = new Deck(null);
		Deck checker2 = new Deck(numbers);
		
		//check each method
		System.out.println(checker1);
		checker1.deal(3, false);//fixed!!!
		checker1.cut(2);//fixed!!!
		/*
		checker1.addCards(numbers);//we are told we can assume moreCards will not be null
		System.out.println(checker1);//fixed!!!
		*/
		checker1.compareTo(checker1);
		System.out.println((Object)(checker1)==null);//to check if it was considered null as an object
		checker2.compareTo(checker1);//fixed!!!
		checker1.compareTo(checker2);//works!!
		checker1.shuffleInterlace(true);//fixed!!!
		checker1.shuffleRandom(3, true);//fixed!!!
		System.out.println(checker1.getDeck());//works!!!
		checker1.reset();//works!
		System.out.println(checker1);
		
		//check edge cases
		Deck new_deck5 = new Deck();
		System.out.println(new_deck5.cut(0));//check if it cuts at 0
		System.out.println(new_deck5);
		ArrayList<Integer> Player2 = new_deck5.deal(53, false);//to check if it will go oob for dealing 
		System.out.println(Player2);
		ArrayList<Integer> one_card = new ArrayList<Integer>(Arrays.asList(1));
		new_deck5.setDeck(one_card);
		System.out.println(new_deck5.cut(1));//checking oob
		System.out.println(new_deck5);
		System.out.println(new_deck5.cut(0));//works
		System.out.println(new_deck5);//check if it cuts only 1 card
		Deck new_deck6 = new Deck();
		new_deck5.reset();
		System.out.println(new_deck5.compareTo(new_deck6));//equal--works!!!
		System.out.println(new_deck5.cut(0));
		System.out.println(new_deck5);
		System.out.println(new_deck5.compareTo(new_deck6));//1 (same nums but diff order)
	}

}