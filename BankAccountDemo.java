// Programmer  : Alex Cruz
// Date        : 11/13/2023
// Description : Homework 9D: Bank Account - This program is an exercise in Inheritance by using Abstract Classes and Abstract Methods.

// Imported Utility
import java.io.IOException;
import java.util.Scanner;

// Bank Account Class
abstract class BankAccount {

    // Bank Account Variables
    double bankBalance, interestRate;

    // Static Bank Account Variables
    static int depositTracker, withdrawTracker;

    // Bank Account Constructor
    BankAccount(double balance, double interest) {

        // Initializes Bank Account Variables
        bankBalance = balance;
        interestRate = interest;

    }

    // Deposit Method
    void deposit(double depositAmount) {

        // Adds Deposit Amount To Balance
        bankBalance = bankBalance + depositAmount;

        // Increments Deposit Tracker
        depositTracker ++;
    }

    // Withdraw Method
    void withdraw(double withdrawAmount) {

        // Removes Withdrawn Amount From Balance
        bankBalance = bankBalance - withdrawAmount;

        // Increments Withdraw Tracker
        withdrawTracker ++;
    }

    // Calculate Interest Method
    void calcInterest(double interestRate) {

        // Method Variables
        double annualInterestRate, monthlyInterestRate, monthlyInterest;

        // Calculates Annual Interest Rate
        annualInterestRate = (interestRate / 100 ) ;

        // Calculates Monthly Interest Rate
        monthlyInterestRate = (annualInterestRate / 12);

        // Calculates Monthly Interest
        monthlyInterest = (bankBalance * monthlyInterestRate);

        // Adds Monthly Interest To Balance
        bankBalance = bankBalance + monthlyInterest;
    }

    // Monthly Process Method
    void monthlyProcess(double interestRate) {

        // Calls calcInterest Method using Interest Rate
        calcInterest(interestRate);

        // Resets Tracker Counters
        depositTracker = 0;
        withdrawTracker = 0;

    }
}

// Savings Account Class Inheriting Bank Account Class
class SavingsAccount extends BankAccount {

    // Static Class Variables
    static boolean accountActive = true;

    // Savings Account Constructor
    SavingsAccount(double bankBalance, double interestRate)
    {
        // Calling Superclass Constructor (BankAccount Constructor)
        super(bankBalance, interestRate);

    }

    // Deposit Method
    void deposit(double depositAmount) {

        // Calls Deposit Method In Parent Class
        super.deposit(depositAmount);

        // If Account Was Marked Inactive
        if (accountActive == false)
        {
            // Checks To See If Balance Is Above $25.00
            if (bankBalance > 25.00)
            {
                // Displayes Active Account Message
                System.out.println("Your account is now ACTIVE\n");

                // Sets Account As Active
                accountActive = true;
            }

        }

    }

    // Withdraw Method
    void withdraw(double withdrawAmount) {

        // Withdraw Method Variables
        double withdrawFee = 1.00;

        // Checks If Account Is Active
        if (accountActive == true)
        {

            // Calls Withdraw Method In Parent Class
            super.withdraw(withdrawAmount);

            // If Balance Is Less Than $25.00
            if (bankBalance <= 25.00)
            {
                // Displays Account Status
                System.out.println("Your balance is less than minimum balance. Your account is now INACTIVE ");

                // Sets Account As Inactive.
                accountActive = false;
            }

            // If More Than 4 Withdraws Have Been Made
            if (withdrawTracker > 4)
            {
                // Displays Fee Message
                System.out.println("You have exceeded monthly limit of withdrawals. Fee of $1 charged\n");

                // Charges Withdraw Fee From Balance
                bankBalance = bankBalance - withdrawFee;
            }


        }

        // Checks if Account Is Inactive
        else if (accountActive == false)
        {
            // Displays 
            System.out.println("Your Account Is INACTIVE. Cannot Withdraw From Account.");
        }


    }

    // Monthly Process Method
    void monthlyProcess(double interestRate) {

        // Calls Parent Monthly Process Method
        super.monthlyProcess(interestRate);

    }


}

public class BankAccountDemo {
  public static void main(String[] args) throws IOException {

    // Main Program Variables
    boolean menuIterator;
    double beginningBalance, interestRate;
    String userChoice;

    // Setting Up Scanner
    Scanner keyboard = new Scanner(System.in);

    // Entering Beginning Balance
    System.out.println("Enter beginning balance :$");
    beginningBalance = keyboard.nextDouble();

    // Makes Sure Beggining Balance Isn't Negative
    while (beginningBalance < 0)
    {
        // Displays Error Message
        System.out.println("Error: Must enter positive value:");
        beginningBalance = keyboard.nextDouble();
    }

    // Entering Interest Rate
    System.out.println("Enter interest rate(whole number) :%");
    interestRate = keyboard.nextDouble();

    // Makes Sure Interest Rate Isn't Negative
    while (interestRate < 0)
    {
        System.out.println("Error: Must enter positive value:");
        interestRate = keyboard.nextDouble();
    }

    // Consumes Remaining Newline Character
    keyboard.nextLine();

    // Creates SavingsAccount Object
    SavingsAccount savingsAccount = new SavingsAccount(beginningBalance, interestRate);

    // Beginning of Menu Iteration
    menuIterator = true;

    // Menu Loop
    do
    {
        // Display Program Menu
        System.out.println("Enter D to deposit");
        System.out.println("Enter W to Withdraw");
        System.out.println("Enter B for Balance");
        System.out.println("Enter M for Monthly Process");
        System.out.println("Enter E to Exit");

        // Allow User To Input Choice
        userChoice = keyboard.nextLine();

        // If D For Deposit Is Chosen
        if (userChoice.equals("D"))
        {
            // Deposit Variables
            double depositAmount;

            // Ask For Amount To Deposit
            System.out.println("Enter the amount you want to Deposit :$");
            depositAmount = keyboard.nextDouble();

            // Consumes Remaining Newline Character
            keyboard.nextLine();

            // If Amount Input Is Negative
            if (depositAmount < 0.0)
            {
                // Display Error Message
                System.out.println("Error: Must enter positive value\n");
            }

            // Else If Amount Input Is Positive
            else
            {
                // Calls Savings Account Deposit Method
                savingsAccount.deposit(depositAmount);
            }

        }

        // If W For Withdraw Is Chosen
        else if (userChoice.equals("W"))
        {
            // Withdraw Variables
            Double withdrawAmount;

            // Ask For Amount To Withdraw
            System.out.println("Enter the amount you want to withdraw :$");
            withdrawAmount = keyboard.nextDouble();

            // Consumes Remaining Newline Character
            keyboard.nextLine();

            // If Amount Input Is Negative
            if (withdrawAmount < 0.0)
            {
                // Display Error Message
                System.out.println("Error: Must enter positive value\n");
            }

            // Elsi If Amount Input Is Positive
            else
            {
                // Calls Savings Account Withdraw Method
               savingsAccount.withdraw(withdrawAmount);
            }
        }

        // If B for Balance Is Chosen
        else if (userChoice.equals("B"))
        {
            // Balance Variable
            double totalBalance;

            // Initializes Total Balance
            totalBalance = savingsAccount.bankBalance;

            // Displays Balance
            System.out.printf("Your Balance is: %.2f \n", totalBalance);
        }

        // If M for Monthly Process Is Chosen
        else if (userChoice.equals("M"))
        {
            // Total Balance Variable
            double totalBalance;

            // Calls Savings Accoutn Monthly Process Method
            savingsAccount.monthlyProcess(interestRate);

            // Initializes Total Balance
            totalBalance = savingsAccount.bankBalance;

            // Displays Balance After Monthly Process Method Has Run
            System.out.printf("Your Balance after Monthly process is: %.2f \n", totalBalance);

        }

        // If E for Exit Is Chosen
        else if (userChoice.equals("E"))
        {
            // Total Balance Variables
            double totalBalance;

            // Initializes Total Balance
            totalBalance = savingsAccount.bankBalance;

            // Displays Ending Balance
            System.out.printf("Balance : $%.2f \n", totalBalance);
            System.out.println("Thank you. Bye");

            // Sets Menu Iterator To False, Will End Program
            menuIterator = false;
        }

        // Checks To See If Input Choice is Invalid
        else if (!(userChoice.equals("D")) && !(userChoice.equals("W")) && !(userChoice.equals("B")) && !(userChoice.equals("M")) && !(userChoice.equals("E")))
        {
            // Displays Error Message
            System.out.println("\nInvalid choice. Try again\n");
        }


    } while (menuIterator == true);
     
     
  }
}
