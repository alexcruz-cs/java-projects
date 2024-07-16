// Programmer  : Alex Cruz
// Date        : 11/20/2023
// Description : Homework 9C: Employee Demo - This program is an exercise in Inheritance using polymorphism.

// Imported Utility
import java.util.Scanner;
import java.io.IOException;

// Employable Interface
interface employable
{
    // Employable Method Header - Will Define In Child Classes
    public double calculateWage();
}

// Full Time Employee Class Implements Employable Class
class fulltimeEmployee implements employable
{
    // Employee Class Variables
    int hoursWorked;
    double hourlyRate, totalWage;

    // Full Time Employee Constructor - Parameters Gathered From User Input In Main
    fulltimeEmployee(int hours, double rate)
    {
        // Initializes Employee Class Variables With Gathred Information
        hoursWorked = hours;
        hourlyRate = rate;

    }

    // Calculates Wage For Full Time Employee
    public double calculateWage()
    {
        // Calculate Wage at Hours Worked * Hourly Rate
        totalWage = (hoursWorked * hourlyRate);

        // Returns Wage
        return totalWage;

    }
}

// Part Time Employee Class Implements Employable Class
class parttimeEmployee implements employable
{
    // Part Time Employee Variables
    int hoursWorked, overTimeHours;
    double hourlyRate, overTimeRate, overTimePay, totalWage;

    // Part Time Employee Constructor - Parameters Gathered From User Input In Main
    parttimeEmployee(int hours, double rate)
    {
        // Initializes Part Time Employee Variables With Gathered Information
        hoursWorked = hours;
        hourlyRate = rate;
    }

    // Calculates Wage For Part Time Employees
    public double calculateWage()
    {
        // Calculate Wage Variables - Initializing Regular Hours at 40
        int regularHours = 40;
        double overtimeMultiplier = 1.5;

        // If Part Time Employee Has Worked 40 Hours Or Less - Normal Wage Calculation
        if (hoursWorked <= regularHours)
        {
            // Calculating Total Wage - Hours Worked * Hourly Rate
            totalWage = (hoursWorked * hourlyRate);
        }

        // If Part Time Employee Has Worked More Than 40 Hours - OverTime Wage Calculation (Calculated at 1.5 * The Hourly Rate)
        if (hoursWorked > regularHours)
        {
            // Calculates OverTime Hours By Subtracting Regular Hours (40) from Hours Worked To Get Over Time Hours
            overTimeHours = (hoursWorked - regularHours);

            // Calculates Over Time Rate By Multiplying Hourly Rate By The Over Time Multiplier (1.5)
            overTimeRate = (hourlyRate * overtimeMultiplier);

            // Calculates Over Time Pay
            overTimePay = (overTimeHours * overTimeRate);

            // Calculates Regular Wage By Multiplying Regular Hours (40) By The Hourly Rate
            totalWage = (regularHours * hourlyRate);

            // Calculates Total Wage By Adding Regular Wage to Over Time Pay 
            totalWage = (totalWage + overTimePay);

        }

        // Returns Wage
        return totalWage;
    }
}

public class EmployeeDemo {
    public static void main(String[] args) throws IOException {

        // Main Variables
    int hoursWorked; 
    double hourlyRate;

    // Creates Keyboard Scanner
    Scanner keyboard = new Scanner(System.in);

    // Prompt User For Full Time Employee Information
    System.out.println("Enter Information For Full Time Employee,");
    System.out.println("Please Enter Hours Worked: ");
    hoursWorked = keyboard.nextInt();

    System.out.println("Please Enter Hourly Rate:");
    hourlyRate = keyboard.nextDouble();

    // Referencing employable Parent Class to Create fullTimeEMployee Object
    employable Employee = new fulltimeEmployee(hoursWorked, hourlyRate);

    // Calls The DisplayEmployeeInfo Method In fulltimeEmployee Class
    DisplayEmployeeInfo(Employee);

    // Prompt User For Part Time Employee Information
    System.out.println("\nEnter Information For Part Time Employee,");
    System.out.println("Please Enter Hours Worked: ");
    hoursWorked = keyboard.nextInt();

    System.out.println("Please Enter Hourly Rate: ");
    hourlyRate = keyboard.nextDouble();

    // Polymorphing Employee Variable to Create parttimeEmployee Object
    Employee = new parttimeEmployee(hoursWorked, hourlyRate);

    // Calls The DisplayEmployeeInfo() Method in parttimeEmployee Class
    DisplayEmployeeInfo(Employee);

  }

  // Method Displays the Employee Information.
  static void DisplayEmployeeInfo(employable emp)
  {

      // Display Employee Info Variables
      double totalWage;

      // Calls The calculateWage() Method From Employee Object - Gets Back The Wage and Displays It.
      totalWage = emp.calculateWage();

      // Displays The Total Wage
      System.out.printf("The Employee's Wage Is: $%.2f \n", totalWage);

  }
}
