// Programmer  : Alex Cruz
// Date        : 9/11/2023
// Description : Homework 2A: Internet Service Provider - This program is an exercise in descision structures using if/else statements.

// imported utility
import java.util.Scanner;

public class InternetServiceProvider {

	public static void main(String[] args) {
		
		// Variable Declaration
		String customerPackage;
		double hourlyCharges, totalCharges;
		int hoursUsed, additionalHours;
		
		// Keyboard Scanner Setup
		Scanner keyboard = new Scanner(System.in);	
	
		// Start Of User Facing Prompt
		System.out.println("Enter the letter of the package purchased:");
		customerPackage = keyboard.nextLine();
		
		// Formats User Input into Uppercase
		customerPackage = customerPackage.toUpperCase();
		
		System.out.println("Enter the number of hours that were used:");
		hoursUsed = keyboard.nextInt();
		
		
		// If User Inputs Package A
		if (customerPackage.equals("A")) {
			
			// 10 Provided Hours
			if (hoursUsed == 10) {
				 
				totalCharges = 9.95;
				System.out.println("Your Total Charges Are $" + totalCharges);
	
			}
			
			// Less Than 10 Provided Hours
			else if (hoursUsed < 10) {
				totalCharges = 9.95;
				System.out.println("Your Total Charges Are $" + totalCharges);
	
			}
			
			// Additional Hour Calculation
			else if (hoursUsed > 10) {
				
				additionalHours = hoursUsed - 10;
				
				hourlyCharges = additionalHours * 2.00;
				
				totalCharges = 9.95 + hourlyCharges;
				
				System.out.println("Your Total Charges Are $" + totalCharges);

			}

		}
		
		// If User Inputs Package B
		else if (customerPackage.equals("B")) {
			
			// 20 Provided Hours
			if (hoursUsed == 20) {
				 
				totalCharges = 13.95;
				System.out.println("Your Total Charges Are $" + totalCharges);
	
			}
			
			// Less Than 20 Provided Hours
			else if (hoursUsed < 20) {
				totalCharges = 13.95;
				System.out.println("Your Total Charges Are $" + totalCharges);
	
			}
			
			// Additional Hour Calculation
			else if (hoursUsed > 20) {
				
				additionalHours = hoursUsed - 20;
				hourlyCharges = additionalHours * 1.00;
				totalCharges = 13.95 + hourlyCharges;
				
				System.out.println("Your Total Charges Are $" + totalCharges);

			}
	
		}
		
		// If User Inputs Package C
		else if (customerPackage.equals("C")) {
								
			totalCharges = 19.95;
			
			System.out.println("Your Total Charges Are $" + totalCharges);

		}
	}

}
