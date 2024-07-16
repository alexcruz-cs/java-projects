// Programmer  : Alex Cruz
// Date        : 9/25/2023
// Description : Homework 5: Car Demo - This program is an exercise in writing simple classes and using constructors and other methods.


// Car Class
class Car {

    // Private Car Class Variables
     private int yearModel, speed;
     private String make;

    
    // Class Car Constructor
    public Car(int yrm, String mke)
    {
        // Holds The Car's Year
        yearModel = yrm;

        // Holds The Car's Make
        make = mke;

        // Holds The Car's Speed
        speed = 0;
    }

    // Accelerate Method
    public int accelerate()
    {
        // Adds 5 to speed
        speed += 5;

        // Returns speed to main
        return speed;
    }

    // Brake Method
    public int brake()
    {
        // Subtracts 5 from speed
        speed -= 5;

        // Returns speed to main
        return speed;
    }

}


public class CarSimulator {

    public static void main(String[] args) {

        // Creating Object From Car Class
        Car theCar = new Car(2023, "Audi TT");

        //  
        System.out.println("We will now call the accelerate function 5 times");

        // Sets carAccelerate Loop To Run 5 times
        for (int carAccelerate = 0; carAccelerate < 5; carAccelerate++)
        {
            // Prints Out Current Speed
            System.out.println("The current speed is: " + theCar.accelerate());
        }

        // Begining of Braking Loop
        System.out.println("We will now call the brake function 5 times");

        // Sets carbrake Loop To Run 5 times
        for (int carBrake = 0; carBrake < 5; carBrake++)
        {
            // Prints Out Current Speed
            System.out.println("The current speed is: " + theCar.brake());
        }

        System.out.println("The program will end now.");

    }
}
