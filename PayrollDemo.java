// Programmer  : Alex Cruz
// Date        : 11/13/2023
// Description : Homework 10A: Payroll Demo - This program is an exercise in Handling and Throwing Exceptions.

// Imported Utility
import java.util.Scanner;

// Payroll Class
class Payroll{

    // Payroll Variables
    String emplName, emplID, idNumbers;
    int emplHours, checkIDNum;
    double emplRate, grossPay;
    char idCharacter;

    // Payroll Constructor
    Payroll(String name, String id, double rate, int hours) throws EmptyName, EmptyID, InvalidID, InvalidRate, InvalidHour
    {
        // Checks To See If Name Field Is Empty
        if (name.equals(""))
        {
            // If Name Field Is Empty - Throws Exception
            throw new EmptyName();
        }

        // Checks To See If ID Field Is Empty
        if (id.equals(""))
        {
            // If ID Field Is Empty - Throws Exception
            throw new EmptyID();
        }

        //Splits ID into Numbers and Character
        String[] inputID = id.split("-");

        // Checks To See If InputID Contains Less Than 2 Values
        if (inputID.length < 2)
        {
            //If InputID Contains Less Than 2 Values - Throws Exception
            throw new InvalidID();
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
                throw new InvalidID();
            }
        }

        // Letter Section of the Employee ID
        idCharacter = inputID[1].charAt(0);

        // Checks If ID Character Is Between A - M
        if (idCharacter < 'A' || idCharacter > 'M' )
        {
            // If IDCharter Isn't Between A-M, Throws Error
            throw new InvalidID();
        }

        // Checks To See If Rate Is Negative
        if (rate < 0)
        {
            throw new InvalidRate();
        }

        // Checks To See If Rate Is Greater Than 25
        if (rate > 25)
        {
            throw new InvalidRate();
        }

        // Checks To See If Hours Are Negative
        if (hours < 0)
        {
            throw new InvalidHour();
        }

        // Checks To See If Hours Are Greater Than 84
        if (hours > 84)
        {
            throw new InvalidHour();
        }

        // If No Exception Is Thrown, Continues to Complete Constructor
        emplName = name;
        emplID = id;
        emplHours = hours;
        emplRate = rate;
    }

    // Gross Pay Method
    void grossPay()
    {
        // Gross Pay = Employee Hours * Employee Rate
        grossPay = (emplHours * emplRate);
    }

    // toString Method
    public String toString()
    {
        // toString Method Variables
        String outputString;

        // Calls Gross Pay Method
        grossPay();

        // Calls grossPay() Method
        outputString = ("Employees name: " + emplName + "\nID: " + emplID + "\nHourly Rate: $" + emplRate + "\nHours: " + emplHours + " hrs" + "\nGross Pay: $" + grossPay);

        // Returns outputString
        return outputString;
    }

}

// Empty Name Exception Class
class EmptyName extends Exception {
  // Constructor
  public EmptyName()
  {
    super("Error: Name Cannot Be Empty");
  }
}

// Empty ID Exception Class
class EmptyID extends Exception {
  // Constructor
  public EmptyID()
  {
    super("Error.: ID Number Cannot Be Empty");
  }
}
// Invalid ID Exception Class
class InvalidID extends Exception {
  // Constructor
  public InvalidID()
  {
    super("Error: Numericals in ID must be between 0-9 and letters must be between A-M");
  }
}

// Ivalid Rate Exception Class
class InvalidRate extends Exception {
  // Constructor
  public InvalidRate()
  {
    super("Error: Hourly Rate Cannot be negative or greater than 25");
  }
}
// Invalid Hour Exception Class
class InvalidHour extends Exception {
  //construcor
  public InvalidHour()
  {
    super("Error: Hours Cannot be negative or greater than 84");
  }
}

// Main Program
public class PayrollDemo {
  public static void main(String[] args) {
    
    // Main Program Variables
    String inputName, inputID;
    int inputHours;
    double inputRate;

    // Creates Keyboard Scanner Object
    Scanner keyboard = new Scanner(System.in);

    // Prompts User For Employee's Name
    System.out.println("Enter the employee's name:");
    inputName = keyboard.nextLine();

    // Prompts User For Employee's ID Number
    System.out.println("Enter employee number, (ex. 999-M):");
    inputID = keyboard.nextLine();

    // Prompts User For Employee's Hourly Rate
    System.out.println("Enter the employee's hourly rate:");
    inputRate = keyboard.nextDouble();

    // Prompts User For Employee's Work Hours 
    System.out.println("Enter the number of hours the employee has worked:");
    inputHours = keyboard.nextInt();

    // Tries To Create Employee Object
    try
    {
      // Creates Payroll Employee With Input Information
      Payroll empPayroll = new Payroll(inputName, inputID, inputRate, inputHours);

      // Outputs Employee Information
      System.out.println(empPayroll.toString());
    }

    // Catches Exceptions - Empty Name
    catch (EmptyName en)
    {
      // Outputs Exception 
      System.out.println(en.getMessage());
    }

    // Catches Exception - Empty ID
    catch(EmptyID eid)
      {
        System.out.println(eid.getMessage());
      }

    // Catches Exception - Invalid ID
    catch(InvalidID iid)
      {
        System.out.println(iid.getMessage());
      }
    // Catches Exception - Invalid Rate
    catch(InvalidRate ir)
      {
        System.out.println(ir.getMessage());
      }

    // Catches Exception - Invalid Hours
    catch(InvalidHour ih)
      {
        System.out.println(ih.getMessage());
      }
  }
}
