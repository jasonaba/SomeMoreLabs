//Jason Abankwah | 1 B/D
//make sure to do documentation
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

 
//QUESTION: DO THE WORDS NEED TO BE IN THE SAME ORDER AS THE USER ENTERED THEM INTO THEIR OWN FILE??
public class SpellingLab {
	
/**
 * Runs all the code/methods
 * @param args
 * @throws FileNotFoundException 
 */
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		//ask user what the file name is that they want
		
		Scanner systeminput = new Scanner(System.in);//input to ask the user what their file name is
		System.out.println("What is the name of the file you want to be checked (INCLUDE .txt IN THE NAME)?");
		String userfilename = systeminput.next(); //systeminput.next();
		
		//create Scanner to load in their 
		Scanner filescanner = new Scanner(new File(userfilename));//makes a scanner to load it all in
		PrintWriter newfilewriter = new PrintWriter(new File("Corrected_Words.txt"));//make a file writer to write in my new file
		runAllPrograms(filescanner, newfilewriter);
		
		filescanner.close(); newfilewriter.close();
		
		System.out.println("I made the file named Corrected_Words.txt with your corrected words");

	}

	
	public static void runAllPrograms(Scanner userfilescanner, PrintWriter myfilewriter) {
		while(userfilescanner.hasNext()) {
			String word = userfilescanner.next();//sets the word to the next word written (after white spaces)
			int loc_q = word.indexOf("q");//finds location of q
			if(loc_q == -1)
				loc_q = word.indexOf("Q");//if no lowercase q, finds location of uppercase q
			
			int loc_k = word.indexOf("k");
			if(loc_k == -1)
				loc_k = word.indexOf("K");
			
			boolean needsCbeforeK = fixK(word, loc_k);
			boolean needsUafterQ = QthenU(word, loc_q);
			boolean includesVowel = vowelChecker(word);//takes in the value for 
			
			if(!includesVowel) {
				word = word + " (aeiouy)";//sets word to the word with aeiouy at end of non-including vowels
				//if this runs, the word is flagged then moved on from
			}
			else {
				if(needsUafterQ) {
				word = word.substring(0,loc_q + 1 ) 
						+ "u" + word.substring(loc_q + 1);//set word to the word with u after q, then new line
				
			}
				if(needsCbeforeK) {
					System.out.println(needsCbeforeK);
					String before = word.substring(0,loc_k);//sets a variable to a string of every letter up to the K (non-inclusive)
					String after = word.substring(loc_k); //sets a variable to everything after and including the K
					word = before + "c" + after;
				}
			}
		myfilewriter.write(word + " ");
				
			
		}
	}
	
	
	/**
	 * check if the word has at least one vowel (including Y) in it
	 * should i call it in QthenU method (this method gives the parameter to vowelChecker each time it iterates through the words)
	 * @param word (the word given by the runAllPrograms method)
	 * @return true if the word has at least one vowel, false if none
	 * (and it will add "aeiouy" at the end if it doesn't have any
	 */
	public static boolean vowelChecker(String word) {//or should parameters be char c and boolean includeY?
			
			String vowels = "aeiouyAEIOUY";
			
			for(int i = 0; i<word.length(); i++) {
				
				char c = word.charAt(i);
				if(vowels.indexOf(c)>=0) {
					return true;
				}
			}
			
		
		return false;
	}
	
	
	
	
	/**
	 * checks if necessary to add a U after a Q (if it is 
	 * @param userfilescanner the scanner used to load in the user's file
	 * @param myfilewriter the filewriter for the corrected file
	 */
	public static boolean QthenU(String word, int locationOfQ) {//sets the letter after 'q' to a 'u' unless 'q' is the last letter
			
			if (!(locationOfQ == -1 || locationOfQ == word.length()-1)) {
				if(!(word.substring(locationOfQ + 1, locationOfQ+2).equals("u"))) {//checks if the letter after q is not e
				return true;
				}
				
			}
			return false;
			
		}
		
	public static boolean fixK(String word, int locationofK) {
		
		String vowels = "aeiouyAEIOUY";
		
		
		if(locationofK>-1) {//if there is a K in the word
			if(vowels.indexOf(locationofK-1)>-1 && vowels.indexOf(locationofK-2)==-1){//if the letter before k is a vowel, 
				//and the letter before that isn't, then a c needs to be added
				return true;
			}
		}
		
		
		return false;
	}
		
		
	}

	