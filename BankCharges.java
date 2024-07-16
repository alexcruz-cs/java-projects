// Programmer  : Alex Cruz
// Date        : 9/11/2023
// Description : Homework 2C: Bank Charges - This program is an exercise in decision structures and using logical operators.

//imported utility
import java.util.Scanner;

public class BankCharges {

	public static void main(String[] args) {
		// Main Program
		
		//Variable Declaration
		double baseFee, checkFees, totalCharges;
		int checksUsed;
		
		// Variable Assignment
		baseFee = 10.00;
		
		// Keyboard Scanner Setup
		Scanner keyboard = new Scanner(System.in);

		// Start Of User Facing Prompt
		System.out.println("Please enter the number of checks written this month");
		
		checksUsed = keyboard.nextInt();
		
		// Check Range For Less Than 20
		if (checksUsed < 20) {
			
			// .10 Calculation
			checkFees = checksUsed * 0.10;
			totalCharges = baseFee + checkFees;
					
			System.out.println("This month's service fees are " + totalCharges);
		}
		
		// Check Range For 20 - 39
		else if ((checksUsed == 20) && (checksUsed < 40)) {

			// .08 Calculation
			checkFees = .08 * checksUsed;
			totalCharges = baseFee + checkFees;
					
			System.out.println("This month's service fees are " + totalCharges);
		}
		
		// Check Range For 40 - 59
		else if ((checksUsed >39) && (checksUsed < 60)) {
			
			// .06 Calculation
			checkFees = .06 * checksUsed;
			totalCharges = baseFee + checkFees;
					
			System.out.println("This month's service fees are " + totalCharges);
		}
		
		// Check Range for Greater than 60
		else if (checksUsed > 59) {

			// .04 Calculation
			checkFees = .04 * checksUsed;
			totalCharges = baseFee + checkFees;
					
			System.out.println("This month's service fees are " + totalCharges);
		}
	}

}
