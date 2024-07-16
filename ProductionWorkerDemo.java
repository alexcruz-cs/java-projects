// Programmer  : Alex Cruz
// Date        : 11/27/2023
// Description : Homework 10B: Production Worker Demo - This program is an exercise in Handling and Throwing Exceptions.

// Imported Utility
import java.util.Scanner;

// Employee Class
class Employee {

    // Eployee Class Variables
    String employeeName, employeeID, idNumbers, employeeHireDate;
    char idCharacter;


    // Employee Constructor - Throws Invalid Employee Number Exception
    Employee(String name, String id, String date) throws InvalidEmployeeNumber
    {

        // Checks To See If ID Field Is Empty
        if (id.equals(""))
        {
            throw new InvalidEmployeeNumber();
        }

        //Splits ID into Numbers and Character
        String[] inputID = id.split("-");

        // Checks To See If InputID Contains Less Than 2 Values 
        if (inputID.length < 2)
        {
            //If InputID Contains Less Than 2 Values - Throws Exception
            throw new InvalidEmployeeNumber();
        }

        // The Numbers Section Of The Employee ID
        idNumbers = inputID[0];

        // Parses Through Employee ID Number Using Its Length
        for (int index = 0; index < idNumbers.length(); index++)
        {
            // Reads Each Number As A Character Into The number Variable
            char number = idNumbers.charAt(index);

            // Checks If The Character is Between 0-9
            if (number < '0' || number > '9')
            {
                // If The Character IS NOT Between 0-9 - Throws Exception
                throw new InvalidEmployeeNumber();
            }
        }

        // The Letter Section Of The Employee ID
        idCharacter = inputID[1].charAt(0);

        // Checks If The Character Is Between A-M
        if (idCharacter < 'A' || idCharacter > 'M')
        {
            // If The Character IS NOT Between A-M - Throws Exception 
            throw new InvalidEmployeeNumber();
        }

        // Initializing Employee Class Variables
        employeeName = name;
        employeeID = id;
        employeeHireDate = date;
    }

}

// Production Worker Class
class ProductionWorker extends Employee {

    // Production Worker Class Variables
    int workerShift;
    double workerRate;

    // Production Worker Class Constructor
    ProductionWorker(String name, String id, String date, int shift, double rate) throws InvalidShift, InvalidPayRate, InvalidEmployeeNumber
    {
        // Sends Employee Data to Parent Employee Class
        super(name,id,date);

        // Checks If Input Worker Shift Is Valid - Only Allows 1 or 2
        if (shift < 1 || shift > 2)
        {
            // If Shift IS NOT 1 or 2 - Throws Exception
            throw new InvalidShift();
        }

        // Checks If Input Worker Rate Is Valid - No Negative Numbers
        if (rate < 0)
        {
            // Id Rate IS Negative - Throws Exception
            throw new InvalidPayRate();
        }

        // Initializing Production Worker Class Variables
        workerShift = shift;
        workerRate = rate;
    }

    // toString Method
    public String toString()
    {
        // toString Method Variables
        String outputString;

        // Initializes Output String
        outputString = ("Employee Details\nEmployee name: " + super.employeeName + "\nEmployee Number: " + super.employeeID + "\nHire Date: " + super.employeeHireDate + "\nShift: " + workerShift + "\nHourly Pay Rate: $" + workerRate);

        // Returns outputString
        return outputString;
    }
    
}

// Invalid Employee Number Exception Class
class InvalidEmployeeNumber extends Exception {

    // Constructor
    public InvalidEmployeeNumber()
    {
        //Exception Text
        super("ERROR: InvalidEmployeeNumber ");
    }
}

// Invalid Shift Exception Class
class InvalidShift extends Exception {

    // Constructor
    public InvalidShift()
    {
        // Exception Text
        super("ERROR: InvalidShift");
    }
}

// Invalid Pay Rate Exception Class
class InvalidPayRate extends Exception {

    // Constructor
    public InvalidPayRate()
    {
        // Exception Text
        super("ERROR: InvalidPayRate");
    }
}

// Main Program
public class ProductionWorkerDemo {
    public static void main(String[] args) {

        // Main Program Variables
        String inputName, inputID, inputDate;
        int inputShift;
        Double inputRate;

        // Creates Keyboard Scanner Object
        Scanner keyboard = new Scanner(System.in);

        // Prompts User For Employee Name
        System.out.println("Enter employee name:");
        inputName = keyboard.nextLine();

        // Prompts User For Employee Number
        System.out.println("Enter employee number, (ex. 999-M):");
        inputID = keyboard.nextLine();

        // Prompts User For Employee Hire Date
        System.out.println("Enter employee hire date:");
        inputDate = keyboard.nextLine();

        // Prompts User For Employee Shift
        System.out.println("Employee shift, (1 = day or 2 = night): ");
        inputShift = keyboard.nextInt();

        // Prompts User For Employe Hourly Rate
        System.out.println("Enter employee hourly pay rate:");
        inputRate = keyboard.nextDouble();

        // Tries To Create Employee Object
        try
        {
            // Referencing Employee Parent Class to Create Production Worker Object
            Employee newEmployee = new ProductionWorker(inputName, inputID, inputDate, inputShift, inputRate);

            // Outputs Employee Information
            System.out.println(newEmployee.toString());
        }

        // Catches Exception, If Exception Is A Invalid Employee Number Object
        catch (InvalidEmployeeNumber ien)
        {
            // Outputs Exception Object Message
            System.out.println(ien.getMessage());
        }

        // Catches Exception, If Exception Is A Invalid Shift Object
        catch (InvalidShift ins)
        {
            // Outputs Exception Object Message
            System.out.println(ins.getMessage());
        }

        // Catches Exception, If Exception Is A Invalid Pay Rate Object
        catch (InvalidPayRate ipr)
        {
            // Outputs Exception Object Message
            System.out.println(ipr.getMessage());
        }

    }
}
