// Name        : Alex Cruz 
// Date        : 10/23/2023
// Description : Homework 7B: Parking Ticket Simulator - This program is an exercise in Classes and Objects and passing objects as arguments to methods.

// Imported Utility
import java.util.Scanner;
import java.io.IOException;

// Parked Car Class
class ParkedCar {

    String make, model, color, liscenseNumber;
    int minutes;

    ParkedCar(String mke, String mod, String col, String lis, int min) 
    {
        make = mke;
        model = mod;
        color = col;
        liscenseNumber = lis;
        minutes = min;
    }
}
// Parking Meter Class
class ParkingMeter {

    int minutes;

    ParkingMeter(int min) 
    {
        minutes = min;
    }
}

// Parking Ticket Class
class ParkingTicket {

    String carMake, carModel, carColor, carLiscenseNumber, officerName, officerBadge;
    int carMinutes, meterMinutes, minuteMultiplier;
    double ticketTotal = 25.00;

    ParkingTicket(String make, String model, String color, String liscenseNumber, String offname, String offbadge, int carmins, int metermins)
    {
        carMake = make;
        carModel = model;
        carColor = color;
        carLiscenseNumber = liscenseNumber;

        officerName = offname;
        officerBadge = offbadge;

        carMinutes = carmins;
        meterMinutes = metermins;

    }

    void ticketIssued() 
    {

        if (carMinutes < 60)
        {
            System.out.println("Ticket data: \n");
            System.out.println("Make: " + carMake + "\n");
            System.out.println("Model: " + carModel + "\n");
            System.out.println("Color: " + carColor + "\n");
            System.out.println("Liscense Number: " + carLiscenseNumber + "\n");
            System.out.println("Officer Name: " + officerName + "\n");
            System.out.println("Badge Number: " + officerBadge + "\n");
            System.out.println("Fine: " + ticketTotal + "\n");

        }

        if (carMinutes > 60)
        {
            minuteMultiplier = (10 * (carMinutes/60));

            ticketTotal = ticketTotal + minuteMultiplier;

            System.out.println("Ticket data: \n");
            System.out.println("Make: " + carMake + "\n");
            System.out.println("Model: " + carModel + "\n");
            System.out.println("Color: " + carColor + "\n");
            System.out.println("Liscense Number: " + carLiscenseNumber + "\n");
            System.out.println("Officer Name: " + officerName + "\n");
            System.out.println("Badge Number: " + officerBadge + "\n");
            System.out.println("Fine: " + ticketTotal + "\n");

        }
       
    }
}

// Police Officer Class
class PoliceOfficer {

    String name, badge, carMake, carModel, carColor, carLiscenseNumber;
    int carMinutes, meterMinutes;

    PoliceOfficer(String nme, String bdg) 
    {
        name = nme;
        badge = bdg;
    }

    void InspectCar(ParkedCar parkedCar, ParkingMeter parkMeter)
    {

        carMake = parkedCar.make;
        carModel = parkedCar.model;
        carColor = parkedCar.color;
        carLiscenseNumber = parkedCar.liscenseNumber;


        carMinutes = parkedCar.minutes;
        meterMinutes = parkMeter.minutes;

        if (meterMinutes < carMinutes)
        {
            System.out.println("Car parking time has expired. \n");
            IssueTicket();
        }

        if (meterMinutes > carMinutes)
        {
            System.out.println("The car parking minutes are valid");
        }

    }

    void IssueTicket()
    {

        ParkingTicket ticketIssued = new ParkingTicket(carMake, carModel, carColor, carLiscenseNumber, name, badge, carMinutes, meterMinutes);
        ticketIssued.ticketIssued();

    }
}

public class ParkingTicketSimulator {
    public static void main(String[] args) throws IOException 
    {
        // Variables
        String officerName, officerBadge, carMake, carModel, carColor, carLiscenseNumber;
        int carMinutes, purchasedMinutes;


        // Keyboard Scanner Setup
		Scanner keyboard = new Scanner(System.in);	

        // Police Officer Information
        System.out.println("Enter the officer's name");
        officerName = keyboard.nextLine();
        System.out.println("Enter the officer's badge number");
        officerBadge = keyboard.nextLine();

        // Police Officer Object Creation
        PoliceOfficer officerOnDuty = new PoliceOfficer(officerName, officerBadge);

        // Parked Car Information
        System.out.println("Enter the car's make");
        carMake = keyboard.nextLine();
        System.out.println("Enter the car's model");
        carModel = keyboard.nextLine();
        System.out.println("Enter the car's color");
        carColor = keyboard.nextLine();
        System.out.println("Enter the car's lisense number");
        carLiscenseNumber = keyboard.nextLine();
        System.out.println("Enter Minutes on car");
        carMinutes = keyboard.nextInt();

        while (carMinutes < 1)
        {
            System.out.print("Invalid Entry. Please try again.");
            carMinutes = keyboard.nextInt();
        }

        // Parked Car Object Creation
        ParkedCar parkedCar = new ParkedCar(carMake, carModel, carColor, carLiscenseNumber, carMinutes);

        // Parking Meter Information
        System.out.println("Enter the number of minutes purchased on the meter");
        purchasedMinutes = keyboard.nextInt();

        while (purchasedMinutes < 1)
        {
            System.out.print("Invalid Entry. Please try again.");
            purchasedMinutes = keyboard.nextInt();
        }

        // Parking Meter Object Creation
        ParkingMeter parkMeter = new ParkingMeter(purchasedMinutes);

        // Police Officer Inspecting Parked Car
        officerOnDuty.InspectCar(parkedCar, parkMeter);
    
        
    }
}
