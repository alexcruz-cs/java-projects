// Programmer  : Alex Cruz
// Date        : 9/11/2023
// Description : Homework 2B: Internet Service Provider Part 2 - This program is an exercise in descision structures using if/else statements and formatting output.

import java.text.DecimalFormat;
import java.util.Scanner;

public class InternetServiceProvider2 {

	public static void main(String[] args) {
		// Main Program
	
		// Variable Declaration
		String packagePurchased;
		double packageA, packageB, packageC, hourlyCharges, totalCharges, possibleSavings;
		int hoursUsed, additionalHours;
		
		// Variable Assignment
		packageA = 9.95;
		packageB = 13.95;
		packageC = 19.95;
		
		// Keyboard Scanner Setup
		Scanner keyboard = new Scanner(System.in);
		
		DecimalFormat Decformatter = new DecimalFormat("#0.00");
		
		// Start Of User Facing Prompt
		System.out.println("Enter the letter of the package purchased:");
		packagePurchased = keyboard.nextLine();
		
		packagePurchased = packagePurchased.toUpperCase();
		
		System.out.println("Enter the number of hours that were used:");
		hoursUsed = keyboard.nextInt();
	
		// If User Inputs Package A
		if (packagePurchased.equals("A")) {
					
			// 10 Provided Hours
			if (hoursUsed == 10) {
				 
				totalCharges = packageA;
								
				System.out.println("Your Total Charges Are $" + Decformatter.format(totalCharges));
	
			}
			
			// Less Than 10 Hours Provided 
			else if (hoursUsed < 10) {
				totalCharges = packageA;
				System.out.println("Your Total Charges Are $" + Decformatter.format(totalCharges));
	
			}
			
			// Additional Hour Calculation
			else if (hoursUsed > 10) {
				
				additionalHours = hoursUsed - 10;
				hourlyCharges = additionalHours * 2.00;
				totalCharges = packageA + hourlyCharges;
				
				System.out.println("Your Total Charges Are $" + Decformatter.format(totalCharges));
				
				
				// Possible Savings With Package B
				possibleSavings = totalCharges - packageB;
				
				// Possible Savings Are Equal To Or Less Than 0
				if (possibleSavings <= 0.00) {
					
				}
				
				// Possible Savings are Greater Than 0
				else if (possibleSavings > 0.00) {
					
					System.out.println("You would've saved $" + Decformatter.format(possibleSavings) + " if you would've gotten Package B");

				}
				
				// Possible Savings With Package C
				possibleSavings = totalCharges - packageC;
				
				// Possible Savings Are Equal To Or Less Than 0
				if (possibleSavings <= 0.00) {
					
				}
				
				// Possible Savings Are Greater Than 0
				else if (possibleSavings > 0.00) {
					
					System.out.println("You would've saved &" + Decformatter.format(possibleSavings) + " if you would've gotten Package C");

				}
				
			}
			

		}
		
		// If User Inputs Package B
		else if (packagePurchased.equals("B")) {
				
			// 20 Hours Provided
			if (hoursUsed == 20) {
				 
				totalCharges = packageB;
				System.out.println("Your Total Charges Are $" + Decformatter.format(totalCharges));
	
			}
			
			// Less Than 20 Hours Provided
			else if (hoursUsed < 20) {
				totalCharges = packageB;
				System.out.println("Your Total Charges Are $" + Decformatter.format(totalCharges));
	
			}
			
			// Additional Hour Calculation
			else if (hoursUsed > 20) {
				
				additionalHours = hoursUsed - 20;
				hourlyCharges = additionalHours * 1.00;
				totalCharges = packageB + hourlyCharges;
				
				System.out.println("Your Total Charges Are $" + Decformatter.format(totalCharges));
				
				// Possible Savings Calculation
				possibleSavings = totalCharges - packageC;
				
				// Possible Savings For Package C
				if (possibleSavings <= 0.00) {
					
				}
				
				else if (possibleSavings > 0.00) {
					
					System.out.println("You would've saved &" + Decformatter.format(possibleSavings) + " if you would've gotten Package C");

				}
			}
	
		}
		
		// If User Inputs Package C
		else if (packagePurchased.equals("C")) {
								
			totalCharges = packageC;
			
			System.out.println("Your Total Charges Are $" + Decformatter.format(totalCharges));

		}
		
		
		// Options Where User Inputs Neither A,B, or C
		
		else if (!(packagePurchased.equals("A"))) {
			
			System.out.println("That package input was not an option.");
		}
		
		else if (!(packagePurchased.equals("B"))) {
			
			System.out.println("That package input was not an option.");
		}
		
		else if (!(packagePurchased.equals("c"))) {
			
			System.out.println("That package input was not an option.");
		}
	}

}
