// Programmer  : Alex Cruz
// Date        : 11/13/2023
// Description : Homework 9A: Customer Demo - This program is an exercise in Inheritance by extending classes.

// Imported Utility
import java.io.IOException;
import java.util.Scanner;

// Person Class
class Person {

    // Person Class Variables
    String personName, personAddress, personTelephone;

    // Variables to Return
    String displayName, displayAddress, displayTelephone;

    // Default Constructor Used - Mutator and Accessor Methods Will Be Used To Add Fields To Objects


    // Person Name Mutator Method
    public void setPersonName(String personName)
    {
        // Sets displayName as Input Name
        displayName = personName;
    }

    // Person Name Accessor Method
    public String getPersonName()
    {
        // Returns displayName
        return displayName;
    }

    // Person Address Mutator Method
    public void setPersonAddress(String personAddress)
    {
        // Sets displayAddress as Input Address
        displayAddress = personAddress;
    }

    // Person Address Accessor Method
    public String getPersonAddress()
    {
        // Returns displayAddress
        return displayAddress;
    }

    // Person Telephone Number Mutator Method
    public void setPersonTelephone(String personTelephone)
    {
        // Sets displayTelephone as Input Telephone
        displayTelephone = personTelephone;
    }

    // Person Telephone Number Accessor Method
    String getPersonTelephone()
    {
        // Returns displayTelephone
        return displayTelephone;
    }
}

// Customer Class Inheriting Person Class 
class Customer extends Person {

    // Customer Class Variables
    int customerID;
    boolean customerMailingList;

    // Variables To Display
    int displayID;
    boolean displayOption;

    // Default Constructor Used - Mutator and Accessor Methods Will Be Used To Add Fields To Objects


    // Customer ID Mutator Method
    public void setCustomerID(int customerID)
    {
        // Sets displayID as Input ID
        displayID = customerID;
    }

    // Customer ID Accessor Method
    public int getCustomerID()
    {
        // Returns displayID
        return displayID;
    }

    // Mailing Option Mutator Method
    public void setMailingOption(boolean customerMailingList)
    {
        // Sets displayOption as Declared Default Option
        displayOption = customerMailingList;
    }

    // Mailing Option Accessor Method
    boolean getMailingOption()
    {
        // Returns displayOption
        return displayOption;
    }


}

// Main Program
public class CustomerDemo {

    public static void main(String[] args) throws IOException {
        
        // Variables
        String personName, personAddress, personTelephone;
        int inputID;
        boolean customerMailingList;

        // Creating Customer Object
        Customer newCustomer = new Customer();

        // Setting Up New Scanner Object
        Scanner keyboard = new Scanner(System.in);

        // Ask For Customer Name
        System.out.println("Enter customer Name:");
        personName = keyboard.nextLine();

        // Ask For Customer Address
        System.out.println("Enter customer Address:");
        personAddress = keyboard.nextLine();

        // Ask For Customer Telephone Number
        System.out.println("Enter customer Telephone Number:");
        personTelephone = keyboard.nextLine();

        // Ask For Customer ID
        System.out.println("Enter CustID");
        inputID = keyboard.nextInt();

        // Consumes Line After Input
        keyboard.nextLine();

        // Sets Customer Mailing List To True
        customerMailingList = true;

        // Adding Object Fields Using Mutator Methods
        newCustomer.setPersonName(personName);
        newCustomer.setPersonAddress(personAddress);
        newCustomer.setPersonTelephone(personTelephone);
        newCustomer.setCustomerID(inputID);
        newCustomer.setMailingOption(customerMailingList);

        // Displaying Information Using Accessor Methods
        System.out.println("Cust Name:" + newCustomer.getPersonName());
        System.out.println("CustID: " + newCustomer.getCustomerID());
        System.out.println("Telephone Number: " + newCustomer.getPersonTelephone());
        System.out.println("Mail List Status: " + newCustomer.getCustomerID());
    }
}
