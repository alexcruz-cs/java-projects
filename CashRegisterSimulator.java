// Programmer  : Alex Cruz
// Date        : 10/16/2023
// Description : Homework 7A: Cash Register Demo - This program is an exercise in Classes and Objects and passing objects as arguments to methods.

// Imported Utility
import java.util.Scanner;
import java.io.IOException;

// Cash Register Class
class CashRegister {

    // Private Variables
    private int quantity;
    private double cost, tax = 0.06;

    // Public Variables;
    public double subTotal, getTax, grandTotal;


    // Constructor
    CashRegister(Integer qty, RetailItem item1)
    {
        // Quanitity Gathered From User Input
        quantity = qty;

        // Cost Gathered From RetailItem Object 'item1'
        cost = item1.price;
    }

    // Get Subtotal Method
    void getSubtotal()
    {
        subTotal = quantity * cost;
    }

    // Get Tax Method
    void getTax()
    {
        getTax = subTotal * tax;
    }

    // Get Total Method
    void getTotal()
    {
        grandTotal = subTotal + getTax;
    }
}

// Retail Item Class
class RetailItem {

    // Variables
    private String description;
    private int unitsOnHand;
    double price;

    // Constructor
    RetailItem(String desc, int units, double prc)
    {
        description = desc;
        unitsOnHand = units;
        price = prc;

    }
    
    // REMOVE BEFORE SUBMITTING - Displays Input Information (For Debugging) REMOVE BEFORE SUBMITTING
    void displayItem()
    {
        System.out.println("Item Name Is   : " + description);
        System.out.println("Item Units Are : " + unitsOnHand);
        System.out.println("Item Price Is  : " + price);
    }

}

public class CashRegisterSimulator {
    public static void main(String[] args) throws IOException {

        // Retail Item Variables
        String itemDescription;
        int itemUnits;
        double itemPrice;

        // Cash Register Variables
        int itemsPurchased;
        
        // Keyboard Scanner Setup
		Scanner keyboard = new Scanner(System.in);	

        // -- Gathering Information on Retail Item --
        System.out.println("We need information about the retail item.");

        // Item Description
        System.out.println("What is the name of the item?");
        itemDescription = keyboard.nextLine();

        // Units Available
        System.out.println("How many units are available?");
        itemUnits = keyboard.nextInt();

        // While Loop - User Input Authentication
        while (itemUnits < 1)
        {
            System.out.println("Invalid Entry. Please try again.");
            itemUnits = keyboard.nextInt();
        }

        // Item Cost
        System.out.println("How much does the item cost per unit?");
		itemPrice = keyboard.nextDouble();

        // While Loop - User Input Authentication
        while (itemPrice < 1)
        {
            System.out.println("Invalid Entry. Please try again.");
            itemPrice = keyboard.nextInt();
        }

        // Creating Retail Item Object - Sending Gathered Information To RetailItem Constructor
        RetailItem item1 = new RetailItem(itemDescription,itemUnits,itemPrice);

        
        // item1.displayItem(); // Display For Debugging  = = Please Remove Before Turning In = =
        
        // - - Gathering information for CashRegister - - 
        System.out.println("How many items are you going to purchase?");
        itemsPurchased = keyboard.nextInt();

        // Creating Cash Register Object - Sending Gathered Information AND Retail Item Object to CashRegister Constructor
        CashRegister purchase1 = new CashRegister(itemsPurchased,item1);

        // Calling CashRegister Methods
        purchase1.getSubtotal();
        purchase1.getTax();
        purchase1.getTotal();

        // Outputting Data Retreived From CashRegister Methods
        System.out.println("Subtotal: " + purchase1.subTotal);
        System.out.println("Tax: " + purchase1.getTax);
        System.out.println("Total: " + purchase1.grandTotal);


    }
}
