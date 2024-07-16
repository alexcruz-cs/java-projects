// Programmer  : Alex Cruz
// Date        : 11/20/2023
// Description : Homework 9B - Preferred Customer Demo - This program is an exercise in Inheritance and overriding superclass methods.

// Person Class
import java.io.IOException;

class Person{
  
  // Person Variables
  String personName, personAddress, personTelephone;

  // Person Constructor (Paramters Gathered From Child Customer Class)
  Person(String name, String address, String telephone)
  {
    // Initializes Person Variables
    personName = name;
    personAddress = address;
    personTelephone = telephone;
  }

}

// Customer Class Extending Person Class
class Customer extends Person {

// Customer Variables
int customerID;
boolean customerOption;

// Customer Constructor (Parameters Gathered From Child PreferredCustomer Class)
Customer(String name, String address, String telephone, int id, boolean option)
{
  // Sends Person Parameters To Parent Person Class
  super(name, address, telephone);

  // Initializes Customer Variables
  customerID = id;
  customerOption = option;
}

}

// Preferred Custoemr Class Extending Customer Class
class PreferredCustomer extends Customer 
{
  // Preferred Customer Variables
  int customerPurchase;
  double customerDiscount;

  // Preferred Customer Constructor (Paramters Gathered From Object Creation In Main)
  PreferredCustomer(String name, String address, String telephone, int id, boolean option, int purchase)
  {
    // Sends Person and Customer Paramters To Parent Person and Customer Classes
    super(name, address, telephone, id, option);

    // Initializes Preferred Customer Variables
    customerPurchase = purchase;
    
  }

  // Calculate Discount Method - Calculates Discount Percentage Based On How Much Was Spent By Customer
  double calculateDiscount()
  {
    // If Customer Spends $500 or More, sets Discount to 5.0 
    if (customerPurchase >= 500)
    {
      customerDiscount = 5.0;
    }

    // If Customer Spends $1000 or More, sets Discount to 6.0
    if (customerPurchase >= 1000)
    {
      customerDiscount = 6.0;
    }
    
    // If Customer Spends $1500 or More, sets Discount to 7.0
    if (customerPurchase >= 1500)
    {
      customerDiscount = 7.0;
    }
    
    // If Customer Spends $2000 or More, sets Discount to 10.0
    if (customerPurchase >= 2000)
    {
      customerDiscount = 10.0;
    }

    // Returns Customer Discount
    return customerDiscount;
  }

  // To String Method - Overrides Object toString Method With Information Gathered
  public String toString()
  {
    // toString Method Variables
    String outputString;
    double convertedPurchase;

    // Converting Integer Gathered From Object Creation To Double
    convertedPurchase = customerPurchase;

    // Calls Method To Calculate Discount
    calculateDiscount();

    // Initializes outputString With Object Information, To Display
    outputString = ("Cust Name:" + super.personName + "\nCustID: " + super.customerID + "\nTelephone Number: " + super.personTelephone + "\nMail List Status: " + super.customerOption + "\nPurchase " + convertedPurchase + "\nDiscount Percent " + customerDiscount);

    // Returns outputString
    return outputString;
  }

}

// Main Program
public class PreferredCustomerDemo {
  public static void main(String[] args) throws IOException 
    {

      // Creates Preferred Customer Object
      PreferredCustomer preferredcustomer1 = new PreferredCustomer("John Adams",
      "Los Angeles, CA", "3235331234", 933, true, 400);
      // Displays Preferred Customer Object using toString Method
      System.out.println(preferredcustomer1.toString() + "\n");

      // Creates Preferred Customer Object
      PreferredCustomer preferredcustomer2 = new PreferredCustomer("Susan Adams",
      "Los Angeles, CA", "3235331234", 933, true, 600);
      // Displays Preferred Customer Object using toString Method
      System.out.println(preferredcustomer2.toString()+ "\n");

      // Creates Preferred Customer Object
      PreferredCustomer preferredcustomer3 = new PreferredCustomer("Mary Adams",
      "Los Angeles, CA", "3235331234", 933, true, 1100);
      // Displays Preferred Customer Object using toString Method
      System.out.println(preferredcustomer3.toString()+ "\n");

      // Creates Preferred Customer Object
      PreferredCustomer preferredcustomer4 = new PreferredCustomer("Larry Adams",
      "Los Angeles, CA", "3235331234", 933, true, 1600);
      // Displays Preferred Customer Object using toString Method
      System.out.println(preferredcustomer4.toString()+ "\n");

      // Creates Preferred Customer Object
      PreferredCustomer preferredcustomer5 = new PreferredCustomer("Mony Adams",
      "Los Angeles, CA", "3235331234", 933, true, 2600);
      // Displays Preferred Customer Object using toString Method
      System.out.println(preferredcustomer5.toString()+ "\n");
      
    }
}
