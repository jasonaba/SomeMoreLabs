
//Jason Abankwah | 1 B/D

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//QUESTION: DO THE WORDS NEED TO BE IN THE SAME ORDER AS THE USER ENTERED THEM INTO THEIR OWN FILE??
public class SpellingLab {

	/**
	 * Runs all the code/methods
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */

	static int num_consonantsfixed = 0;
	static int num_ckfixed = 0;
	static int num_cremoved = 0;
	static int num_qufixed = 0;
	static int num_novowels = 0;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		// ask user what the file name is that they want

		Scanner systeminput = new Scanner(System.in);// input to ask the user what their file name is
		System.out.println("What is the name of the file you want to be checked (INCLUDE .txt IN THE NAME)?");
		String userfilename = systeminput.next(); // systeminput.next();

		// create Scanner to load in their
		Scanner filescanner = new Scanner(new File(userfilename));// makes a scanner to load it all in
		PrintWriter newfilewriter = new PrintWriter(new File("Corrected_Words.txt"));// make a file writer to write in
																						// my new file
		runAllPrograms(filescanner, newfilewriter);// runs the program which has all the conditions in it

		filescanner.close();
		newfilewriter.close();
		systeminput.close();
		System.out.println("I made the file named 'Corrected_Words.txt' with your corrected words");
		System.out.println("Here were all the corrections:");
		System.out.println("Number of words with no vowels: " + num_novowels);
		System.out.println("Number of words corrected by adding 'U' after a 'Q': " + num_qufixed);
		System.out.println(
				"Number of words correctd by making the ending a double consonant ending: " + num_consonantsfixed);
		System.out.println("Number of words corrected by adding 'C' before a 'K': " + num_ckfixed);
		System.out.println("Number of words corrected by removing a 'C' before a 'K': " + num_cremoved);

	}

	/**
	 * takes in all the values provided by the other methods below and runs the
	 * program in the intended order and based on the boolean values given by the
	 * check methods
	 * 
	 * @param userfilescanner: what is used to load in the values from the user's own file
	 * @param myfilewriter: used to write in the new file
	 */
	public static void runAllPrograms(Scanner userfilescanner, PrintWriter myfilewriter) {
//since this is the method where I bring everything together I assumed I didn't have to make it less than 20 lines (I wrote 28 for this)

		while (userfilescanner.hasNext()) {
			String word = userfilescanner.next();// sets the word to the next word written (after white spaces)
			int loc_q = word.indexOf("q");// finds location of q
			if (loc_q == -1)
				loc_q = word.indexOf("Q");// if no lowercase q, finds location of uppercase q

			int loc_k = word.indexOf("k");// finds the location of k
			if (loc_k == -1)// if no lowercase then find the uppercase location (just in case they write an
							// uppercase at the back by accident)
				loc_k = word.indexOf("K");

			// store the return value for each checking/helper method
			boolean needsfixConsonants = checkConsonants(word);
			boolean needsCK = checkCthenK(word, loc_k);
			boolean needsUafterQ = checkQthenU(word, loc_q);
			boolean includesVowel = vowelChecker(word);

			if (!includesVowel) {
				word = word + " (aeiouy)";// sets word to the word with aeiouy at end of non-including vowels
				// if this runs, the word is flagged then moved on from
				num_novowels++;
			} else {
				if (needsUafterQ) {// if this is true, then fix it
					word = fixQthenU(word, loc_q);
					num_qufixed++;
				}
				if (needsfixConsonants) {
					word = fixConsonants(word);
					num_consonantsfixed++;
				}
				if (needsCK) {// if this is true then fix it
					word = fixCthenK(word, loc_k);
					num_ckfixed++;
				} else {
					word = RemoveC(word, loc_k);// check if it needs to be fixed and fix it
				}
			}
			myfilewriter.write(word + " ");// writes the edited word for each

		}
	}

	/**
	 * check if the word has at least one vowel (including Y) in it
	 * 
	 * @param word (the word given by the runAllPrograms method)
	 * @return true if the word has at least one vowel, false if none (and it will
	 *         add "aeiouy" at the end if it doesn't have any)
	 */
	public static boolean vowelChecker(String word) {

		String vowels = "aeiouyAEIOUY";// sets all the vowels we will be working with

		for (int i = 0; i < word.length(); i++) {

			char c = word.charAt(i);
			if (vowels.indexOf(c) >= 0) {// if the character is a vowel
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks where q is and sees if a 'U' needs to be added to the end
	 * 
	 * @param word
	 * @param locationOfQ
	 * @return a true or false value to determine whether a U needs to be added or
	 *         not
	 */

	public static boolean checkQthenU(String word, int locationOfQ) {// sets the letter after 'q' to a 'u' unless 'q' is
																		// the last letter

		if (!(locationOfQ == -1 || locationOfQ == word.length() - 1)) {
			if (!(word.substring(locationOfQ + 1, locationOfQ + 2).equals("u"))) {// checks if the letter after q is not
																					// e
				return true;
			}

		}
		return false;

	}

	/**
	 * called when we need to fix QthenU
	 * 
	 * @param word:        the string given
	 * @param locationofQ: the location of Q in the word
	 * @return the final word so it can be written
	 */
	public static String fixQthenU(String word, int locationofQ) {
		word = word.substring(0, locationofQ + 1) + "u" + word.substring(locationofQ + 1);// set word to the word with u
																							// after q, then new line
		return word;
	}

	/**
	 * checks if we need to add another consonant at the end of a word to make it a
	 * double consonant ending
	 * 
	 * @param word
	 * @return true or false based on if we need to add another consonant at the end
	 */
	public static boolean checkConsonants(String word) {
		String vowels = "aeiouAEIOU";
		String consonants = "flsFLS";
		int vowel_count = 0;// we will count how many vowels there are
		int vowel_index = -1;// we have to see what index a vowel is in this word
		for (int i = 0; i <= vowels.length() - 1; i++) {// repeating for every vowel in the vowels string
			if (word.indexOf(vowels.charAt(i)) >= 0) {// if the word has this vowel
				vowel_index = word.indexOf(vowels.charAt(i));// sets the vowel index to whatever index this vowel is in
																// the word
				vowel_count++;// adds 1 vowel to vowel counter
			}
		}
		if (vowel_count == 1) {// if there is only one vowel
			if (word.length() >= 4) {// if it is a 4-letter word or longer (***MR MOURADOV, THE EXAMPLE YOU GAVE OF
										// "pass" DOESN'T FALL UNDER THE RULES YOU SAID TO FOLLOW. WILL I GET POINTS OFF
										// FOR MAKING PASS NOT WORK?***)
				if (vowel_index == word.length() - 2 && consonants.indexOf(word.charAt(vowel_index + 1)) >= 0) {
					// 1st part checks if vowel is not last letter but second to last so 2nd part of
					// condition doesn't
					// go out of bounds
					// 2nd part checks if the letter after it is one of the 3 consonants
					return true;// if it is a 4 letter word with one of the 3 consonants ending the word and
								// only one vowel, this is true
				}
			}
		}
		return false;
	}

	/**
	 * Adds the same consonant to the end of the word to make it a double consonant
	 * ending
	 * 
	 * @param word
	 * @return
	 */
	public static String fixConsonants(String word) {
		char consonant_ending = word.charAt(word.length() - 1);
		word = word + consonant_ending;

		return word;
	}

	/**
	 * checks if we need to add CK after a vowel or not
	 * 
	 * @param word
	 * @param location_k
	 * @return true if we need to add CK or false if we do not have to
	 */
	public static boolean checkCthenK(String word, int location_k) {
		String vowels = "aeiouAEIOU";

		if (location_k == word.length() - 1) {// has to be last letter
			if (vowels.indexOf(word.charAt(location_k - 1)) >= 0 && location_k - 1 > 0) {// if the letter before k is a
																							// vowel (found in vowels
				// string) and can't be first letter (or will go out
				// of bounds
				int vowel_index = location_k - 1;// creates a variable for the vowel's index
				if (vowels.indexOf(word.charAt(vowel_index - 1)) == -1) {// if the letter before the vowel is not a
																			// vowel
					return true;// this means k comes before a single vowel and you need to add c
				}
			}
		}
		return false;
	}

	/**
	 * if after a single vowel a k follows, we add a c before it
	 * 
	 * @param word
	 * @param locationofK
	 * @return the fixed word
	 */
	public static String fixCthenK(String word, int location_k) {

		String before = word.substring(0, location_k); // sets a variable to the word up to (not including) the letter K
		word = before + "c" + word.substring(location_k);// adds ck to the word before the K (since k has to be the last
															// letter)

		return word;
	}

	/**
	 * runs if checkCthenK is false (this means there possibly was a k with a c
	 * before it) and removes the c if it is unnecessary
	 * 
	 * @param word
	 * @param location_k
	 * @return the word with c removed
	 */
	public static String RemoveC(String word, int location_k) {
		String vowels = "aeiouAEIOU";
		if (location_k == word.length() - 1) {// the k has to be the last letter still
			if (word.charAt(location_k - 1) == 'c' || word.charAt(location_k - 1) == 'C') {// if the letter before k is
																							// a c, then make a variable
																							// for it
				int c_index = location_k - 1;
				if (vowels.indexOf(word.charAt(c_index - 1)) == -1) {// if the letter before c is not a vowel, then we
																		// need to remove C
					word = word.substring(0, c_index) + word.substring(location_k); // just adds k to the end of the
																					// word (since K will be the
					// last letter)
					num_cremoved++;
				}
			}
		}
		return word;// if this fixes or if it doesn't do anything, word is returned

	}
}
