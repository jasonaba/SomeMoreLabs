//Jason Abankwah | Block 1 B/D
package  pckge;
import java.util.Scanner;

public class TaxCalc {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Scanner input = new Scanner(System.in);
		//answer question: will the user get a refund or owe money
		//make the variables
		int dependentnum; 
		double withheld, taxableincome, tranche1, tranche2, tranche3, deduction, taxowed, taxreturn, taxcredit;
		boolean married; 
		deduction = 0.0;
		tranche1 = 0.0;
		tranche2 = 0.0;
		tranche3 = 0.0;
		final double tranche1percent = .1;
		final double tranche2percent = .12;
		final double tranche3percent = .22;
		final double tranche4percent = .32;
		final double childDeduction = 2200.00;

		taxowed = 0.0;
		
		//ask user for their info
		System.out.println("Are you married (TRUE OR FALSE)?");
		married = input.nextBoolean();
		System.out.println("What is your income?");
		taxableincome = input.nextDouble();
		System.out.println("How much money have you witheld?");
		withheld = input.nextDouble();
		System.out.println("How many children/dependents do you have?");
		dependentnum = input.nextInt();
		
		if(married) { //set the married filers tranches and deduction
			tranche1 = 23850.00; 
			tranche2 = 96950.00;
			tranche3 = 206700.00;
			deduction = 30000.00;
			}
		
		else { //set the single filer tranches and deduction
			tranche1 = 11925.00;
			tranche2 = 48475.00;
			tranche3 = 103350.00;
			deduction = 15000.00;
		}
		taxableincome = taxableincome - deduction;//find taxableincome amount	
		
		//if the filer's taxableincome is less than the cutoff, they don't need to pay taxes 
		if(taxableincome <= 0 ) {
			System.out.println("You won't pay any taxes this year");
			taxreturn = withheld;
			System.out.println(taxowed);
		}
		else if(taxableincome<=tranche1){
			//if they fall between 0 and tranche1
			taxowed = taxableincome * tranche1percent;
			//if taxableincome is greater than or equal to taxowed, set taxowed to 10% of tranche1
			}
		else if(taxableincome <= tranche2) {//if they fall between tranche1 and tranche2
			taxowed = tranche1 * tranche1percent;//make taxowed the the tranche1percent * tranche1
			taxableincome = taxableincome-tranche1; //make taxable income subtracted by tranche1 since we already taxed that entire amount
			taxowed = taxowed + taxableincome * tranche2percent;//tax the rest of the taxable income by tranche2 since you are in tranche2
		}
		else if(taxableincome <= tranche3) {//if it is more than tranche2 but less than tranche3
			taxowed = tranche1 * tranche1percent;//get tranche1 value in taxowed
			taxowed = taxowed + (tranche2-tranche1) * tranche2percent;//is tranche2-tranche1 because the money taxed is the amount between the two tranches, not the total of tranche2
			taxableincome = taxableincome - tranche2;//subtract total taxed income from taxable income
			taxowed = taxowed + taxableincome * tranche3percent;//tax rest of taxableincome by tranche3percent
		}
		else if(taxableincome > tranche3) {//if taxable income is greater than tranche3
			taxowed = tranche1 * tranche1percent;//set taxowed first to the amount of money taxed in the 1st tranche
			taxowed = taxowed + (tranche2-tranche1) * tranche2percent;//add everything in taxowed by money taxed in the tranche1-tranche2 range
			taxowed = taxowed + (tranche3-tranche2) * tranche3percent;// set taxowed to total taxowed so far + the amount taxed in range of tranche2-tranche3
			taxableincome = taxableincome - tranche3;//subtract total money taxed (value of tranche3) from taxableincome
			taxowed = taxowed + taxableincome * tranche4percent;//set taxowed to the total so far + the rest of the taxableincome taxed by the 4th tranche percentage
		}
		
			//subtract 2,200 for every child they have if they are paying taxes (tax credit)
		if(!(taxableincome<=0)) {
			taxcredit = dependentnum * childDeduction;
			taxowed = taxowed - taxcredit;
		}
			//if taxowed is more than withheld, then they owe whatever the difference is (taxowed - withheld)
			if(taxowed > withheld) {
				taxowed = taxowed - withheld;
				System.out.println("You owe " + (taxowed) + " to the government.");
				
			}
			//if taxowed is less than withheld, they will get a refund for whatever the difference is (withheld - taxowed)
			else if(withheld >= taxowed) {
				taxreturn = withheld - taxowed;
				System.out.println("The government will give you " + (taxreturn) + " dollars");
			}
		input.close();
			}
		
		
		
}