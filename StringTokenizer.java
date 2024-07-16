// Programmer  : Alex Cruz
// Date        : 10/16/2023
// Description : Homework 7: String Tokenizer - This program is an exercise in reading from files and tokenizing string.

// Imported Utility
import java.io.*;
import java.util.Scanner;

// Sales Data Reader Class
class salesDataReader
{
    // Private Class Variables
    private Scanner inputFile;
    private String line;

    // Constructor - Reads File
    public salesDataReader(String filename) throws IOException
    {
        File file = new File(filename);
        inputFile = new Scanner(file);
    }

    // Reads Through Each Line In File
    public boolean readNextLine() throws IOException
    {
        // Read Next Line Variables
        boolean lineRead;

        // Checks To See If File Has More Lines To Read
        lineRead = inputFile.hasNext();

        // If More Lines Exist, Reads Next Line From File
        if (lineRead)
        {
            line = inputFile.nextLine();
        }

        // Returns Boolean
        return lineRead;
    }

    // Weekly Sales Calculator Method
    public double weeklySales()
    {
        // Weekly Sales Variables
        double total = 0.0;
        double weekSales;

        // Gathers Tokens From String, Using the "," Delimiter
        String[] tokens = line.split(",");

        // Reads Tokens, Adds Them Up Into Total
        for (String str: tokens)
        {
            total += Double.parseDouble(str);
        }

        // Initializes weekSales With Data From total
        weekSales = total;

        // Returns weekSales
        return weekSales;
    }

    // Weekly Average Calculator Method
    public double weeklyAverage()
    {
        // Weekly Average Variables
        double total = 0.0;
        double average;

        // Gathers Tokens From String, Using the "," Delimiter
        String[] tokens = line.split(",");

        // Reads Tokens, Adds Them Up Into Total
        for (String str : tokens)
        {
            total += Double.parseDouble(str);
        }

        // Calculates The Average From Sales
        average = (double) total / tokens.length;

        // Returns Average
        return average;
    }

    //  Method To Close inputFile
    public void close() throws IOException
    {
        inputFile.close();
    }
}

// Main Program
class StringTokenizer {
  public static void main(String[] args) throws IOException {
    // Variables
    double weekSales;
    double average;
    int weekNumber = 1;

    // Variables to check Highest and Lowest Sales
    int highestWeek = 0;
    int lowestWeek = 0;
    double highestWeekSales = Double.MIN_VALUE;
    double lowestWeekSales = Double.MAX_VALUE;

    // Variables for Total Sum and Total Average
    double totalSum = 0.0;
    double totalAverage = 0.0;

    // Creates SalesDataReader Object
    salesDataReader dataReader = new salesDataReader("SalesData.txt");

    // Reads Through File and Calculates, Weekly Sales, Weekly Average, Total Sum, Total Average, Highest Sales Week, and Lowest Sales Week
    while (dataReader.readNextLine())
    {
        // Calls Method To Calulate Weekly Sales
        weekSales = dataReader.weeklySales();

        // Displays The Weekly Sales
        System.out.printf("Weekly sales from week " + weekNumber + " is $%.2f \n", weekSales);

        // Calculates Highest Sales Week
        if (weekSales > highestWeekSales)
        {
            // Sets Week Number as Highest Week
            highestWeek = weekNumber;
            // Sets Week Sales as Highest Week Sales - To Compare at Next Iteration
            highestWeekSales = weekSales;
        }

        // Calculates Lowest Sales Week
        if (weekSales < lowestWeekSales)
        {
            // Sets Week Number as Lowest Week
            lowestWeek = weekNumber;
            // Sets Week Sales as Lowest Week Sales - To Compare at Next Iteration
            lowestWeekSales = weekSales;
        } 

        // Calls Method To Calculate Weekly Average
        average = dataReader.weeklyAverage();

        // Display The Weekly Average
        System.out.printf("Average for week " + weekNumber + " is $%.2f \n", average);

        // Calculates Total Sum
        totalSum += weekSales;

        // Calculates Total Average
        totalAverage = (totalSum / weekNumber);

        //Increments weekNumber per Iteration
        weekNumber++;

    }

    //Displays Total Sum And Total Average
    System.out.printf("Total sale of all weeks = %.2f \n", totalSum);
    System.out.printf("Average weekly sales = %.2f \n", totalAverage);

    // Displays Highest Week Sales and Lowest Week Sales
    System.out.println("The week number with the highest amount of sales is: " + highestWeek);
    System.out.println("The week number with the lowest amount of sales is: " + lowestWeek);

    // Calls Method To Close InputFile
    dataReader.close();
     
     
  }
}
