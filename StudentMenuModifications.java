// Programmer  : Alex Cruz
// Date        : 9/25/2023
// Description : Homework 4: Student Menu Modifications - This program is an exercise in updating a program to use methods.

// Imported Utility
import java.util.Scanner;
import java.io.*;

public class StudentMenuModifications {

    public static void main(String[] args) throws IOException {

        //Variable Declaration For Main Method
        Boolean menuIterator;
        // Variable For User
        int userChoice; 

        // Keyboard Scanner Setup
        Scanner keyboard = new Scanner(System.in);

        // Student File Check & Creation
		File studentFile = new File("student.txt");
		if (!studentFile.exists())
		{
			PrintWriter outputFile = new PrintWriter("student.txt");
			outputFile.close();
		}
		
		// Course File Check & Creation
		File courseFile = new File("course.txt");
		if (!courseFile.exists())
		{
			PrintWriter outputFile = new PrintWriter("course.txt");
			outputFile.close();
		}

        // Enrollment File Check & Creation
        File  enrollmentFile = new File("enrollment.txt");
        if (!enrollmentFile.exists())
        {
            PrintWriter outputFile = new PrintWriter("enrollment.txt");
			outputFile.close();
        }

        // Menu Iterator Initialization
        menuIterator = true;

        // Main Menu
        do
        {

            // Menu Options
            System.out.println("- - - - - - - - - - MAIN MENU - - - - - - - - - -");
			System.out.println("1. Add Student");
			System.out.println("2. Add Course");
			System.out.println("3. Display Student");
			System.out.println("4. Display Course");
			System.out.println("5. Add Enrollment");
            System.out.println("6. Exit");

            // Let User Input Choice
			System.out.println("Please Enter The Number For The Option:");
			userChoice = keyboard.nextInt();

            
            if (userChoice == 1)
            {
                System.out.println("You Have Chosen To Add A Student.");
                // Calling Method To Add Student
                addStudent(args);
            }

            else if (userChoice == 2)
            {
                System.out.println("You Have Chosen To Add A Course.");
                // Calling Method To Add Course
                addCourse(args);
            }

            else if (userChoice == 3)
            {
                System.out.println("You Have Chosen To Display A Student.");
                // Calling Method To Display Student
                displayStudent(args);
            }

            else if (userChoice == 4)
            {
                System.out.println("You Have Chosen To Display A Course.");
                // Calling Method To Display Course
                displayCourse(args);
            }

            else if (userChoice == 5)
            {
                System.out.println("You Have Chosen To Add An Enrollment.");
                // Calling Method To Add Enrollment
                addEnrollment(args);
            }

            else if (userChoice == 6)
            {
                // Exiting The Program
                System.out.println("You Have Chosen To Close The Program.");
                System.out.println("Thank you, Bye!");
                // Setting menuIterator To False
				menuIterator = false;

            }

            else if (userChoice < 1)
			{
                // If User Enters Invalid Option Less Than 1
				System.out.println("Error, Please Enter A Valid Option of 1-6!");
			}
			
			else if (userChoice > 6)
			{
                // If User Enters Invalid Option Greater than 6
				System.out.println("Error, Please Enter A Valid Option of 1-6!");
			}

        } while (menuIterator == true);


    }

    // Method For Adding A Student
    public static void addStudent(String[] args) throws IOException 
    {

        // Local Variable Declaration - For addStudent Method
        int studentIDNumber;
        String studentFirstName, studentLastName, studentAddress, studentCity, studentState, studentZip;

        // Keyboard Scanner Setup
        Scanner keyboard = new Scanner(System.in);

        // Ask For Student ID
        System.out.println("Enter The Student's ID: ");
        studentIDNumber = keyboard.nextInt();
        
        // Consumes Remaining Newline Character
        keyboard.nextLine();

        // Ask For Student Name
        System.out.println("Enter The Student's First Name: ");
        studentFirstName = keyboard.nextLine();
        
        // Ask For Student Last Name
        System.out.println("Enter The Student's Last Name: ");
        studentLastName = keyboard.nextLine();
        
        // Ask For Student Address
        System.out.println("Enter the Student's Address: ");
        studentAddress = keyboard.nextLine();
        
        // Ask For City
        System.out.println("Enter The City: ");
        studentCity = keyboard.nextLine();
        
        // Ask For State
        System.out.println("Enter The State: ");
        studentState = keyboard.nextLine();
        
        // Ask For Zip Code
        System.out.println("Enter the Zip Code: ");
        studentZip = keyboard.nextLine();
        
        // Opens "student.txt" File - To Read Through
        File openFile = new File("student.txt");
        Scanner inputFile = new Scanner(openFile);
        
        // Beggining of Loop To Read Through File
        studentloop:
        while (inputFile.hasNext())
            {
                // Gathers First Line
                String idOnFile = inputFile.nextLine();

                // Converts Line Into Integer
                int convertedID = Integer.parseInt(idOnFile);
                
                // Consumes Lines Of "Extra Information"
                inputFile.nextLine();
                inputFile.nextLine();
                inputFile.nextLine();
                inputFile.nextLine();
                inputFile.nextLine();
                inputFile.nextLine();

                // Checks To See If Student ID Is Already In File - Closes File & Breaks Into Main Menu
                if (convertedID == studentIDNumber)
                {
                    System.out.println("Error - Student ID Already in File!");
                    inputFile.close();
                    return;
                }

                // Continues Loop, Looking Through File
                if (!(convertedID == studentIDNumber))
                {
                    continue;
                }

                // Stops Looking Through & Breaks Out Of Loop
                else if (!(convertedID == studentIDNumber))
                {
                    // Breaks To studentloop:
                    break studentloop;
                }
            }

        // Writing New Student Record Into File

        System.out.println("Writing Student Record Into File...");
        
        // Opens File To Write To
        FileWriter fwriter = new FileWriter("student.txt",true);
        PrintWriter outputFile = new PrintWriter(fwriter);
        
        // Output - Written To File
        outputFile.println(studentIDNumber);
        outputFile.println(studentFirstName);
        outputFile.println(studentLastName);
        outputFile.println(studentAddress);
        outputFile.println(studentCity);
        outputFile.println(studentState);
        outputFile.println(studentZip);
        
        // Closes File
        System.out.println("File Written!");
        outputFile.close();	

    }

    // Method For Adding A Course
    public static void addCourse(String[] args) throws IOException 
    {
        // Local Variable Declaration - For addCourse Method
        int courseIDNumber;
        String courseName, courseDescription;

        // Keyboard Scanner Setup
        Scanner keyboard = new Scanner(System.in);

        // Ask For Course ID
        System.out.println("Enter The Course ID: ");
        courseIDNumber = keyboard.nextInt();
        
        // Consumes Remaining Newline Character
        keyboard.nextLine();
        
        // Ask For Course Name
        System.out.println("Enter The Course Name: ");
        courseName = keyboard.nextLine();
        
        // Asl For Course Description
        System.out.println("Enter The Course Description: ");
        courseDescription = keyboard.nextLine();

        //Opens "course.txt" File - To Read Through
        File openFile = new File("course.txt");
        Scanner inputFile = new Scanner(openFile);
        
        //Beggining Of Loop To Read Through File
        courseloop:
        while (inputFile.hasNext())
            {
                // Gather's First Line
                String courseIDOnFile = inputFile.nextLine();
                
                // Converts Line Into Integer
                int convertedCourseID = Integer.parseInt(courseIDOnFile);
                
                // Consumes Lines Of "Extra Infomation"
                inputFile.nextLine();
                inputFile.nextLine();
                
                // Checks To see If Course ID is Already In File - Closes File & Breaks Into Main Menu
                if (convertedCourseID == courseIDNumber)
                {
                    System.out.println("Error - Course ID Already In File!");
                    inputFile.close();
                    return;
                }

                // Continues Loop, Looking Through File
                if (!(convertedCourseID == courseIDNumber))
                {
                    continue;
                }

                // Stops Looking Through & Breaks Out Of Loop
                else if (!(convertedCourseID == courseIDNumber))
                {
                    // Breaks To courseloop:
                    break courseloop;
                }
            }
        
        // Writing New Course Record Into File
        System.out.println("Writing Course Record Into File...");

        // Opens File To Write To
        FileWriter fwriter = new FileWriter("course.txt",true);
        PrintWriter outputFile = new PrintWriter(fwriter);
        
        // Output - Written To File
        outputFile.println(courseIDNumber);
        outputFile.println(courseName);
        outputFile.println(courseDescription);
        
        // Closing File
        System.out.println("Closing output File");
        outputFile.close();
    }

    public static void displayStudent(String[] args) throws IOException 
    {
        // Local Variable Declaration - For displayStudent Method
        int displayID, studentIDNumber;
        Boolean studentTest;

        // Keyboard Scanner Setup
        Scanner keyboard = new Scanner(System.in);

        // Ask The User To Enter Student ID They Want To Find
        System.out.println("Enter Student ID You Want To Display: ");
        displayID = keyboard.nextInt();
            
        // Opens The Student File
        File openFile = new File("student.txt");
        Scanner inputFile = new Scanner(openFile);

        // Setting studentTest Boolean to True
        studentTest = true;
                        
        // Loop Searching Through The "student.txt" File
        while (inputFile.hasNextLine()) 
        {
            // Gathers First Line
            String inputID = inputFile.nextLine();
            
            // Converts Line Into Integer
            studentIDNumber = Integer.parseInt(inputID);
            
            // Gathering Lines Of Information & Initializing Variables
            String inputName = inputFile.nextLine();			
            String inputLastName = inputFile.nextLine();
            String inputAddress = inputFile.nextLine();
            String inputCity = inputFile.nextLine();
            String inputState = inputFile.nextLine();
            String inputZip = inputFile.nextLine();
            
            
            // If Record Is Found, Following Is Displayed				
            if (studentIDNumber == displayID)
            {
                // Displays Student Information
                System.out.println("Student ID Number: " + studentIDNumber);
                System.out.println("Student First Name: " + inputName);
                System.out.println("Student Last Name: " + inputLastName);
                System.out.println("Adress: " + inputAddress);
                System.out.println("City: " + inputCity);
                System.out.println("State: " + inputState);
                System.out.println("Zip Code: " + inputZip);

                // Sets studentTest Boolean to False
                studentTest = false;

                break;
            }

            // Continues Looping Through File
            if (!(studentIDNumber == displayID))
            {
                continue;
            }

        }

        // If Record Is Not Found, Following is Displayed
            if (studentTest == true)
            {
                System.out.println("Error - Student ID Not Found In File!");
                System.out.println("Please Try Creating Student Record First,");

                // Closing File
                inputFile.close();

                return;
            }
        
        // Closing File
        inputFile.close();
    }

    public static void displayCourse(String[] args) throws IOException 
    {
        // Local Variable Declaration - For displayCourse Method
        int displayID, courseIDNumber;
        Boolean courseTest;

        // Keyboard Scanner Setup
        Scanner keyboard = new Scanner(System.in);

        // Ask The User To Enter Course ID They Want To Find
        System.out.println("Enter Course ID You Want To Display: ");
        displayID = keyboard.nextInt();
        
        // Opens The Course File
        File openFile = new File("course.txt");
        Scanner inputFile = new Scanner(openFile);

        // Setting courseTest Boolean to True
        courseTest = true;
                        
        // Loop Searching Through The Course File
        while (inputFile.hasNextLine()) 
        {
            // Gathers First Line
            String inputID = inputFile.nextLine();
            
            // Converts Line Into Integer
            courseIDNumber = Integer.parseInt(inputID);
            
            // Gathering Lines Of Information & Initializing Variables
            String inputName = inputFile.nextLine();			
            String inputDescription = inputFile.nextLine();

            
            // If Record Is Found, Following is Displayed				
            if (courseIDNumber == displayID)
            {
                System.out.println("Course ID: " + courseIDNumber);
                System.out.println("Course Name: " + inputName);
                System.out.println("Course Description: " + inputDescription);

                // Setting courseTest Boolean To False
                courseTest = false;

                // Closing File
                inputFile.close();

                break;
            }

            // Continues Looping Through File
            if (!(courseIDNumber == displayID))
            {
                continue;
            }

        }
        

        // If Record Is Not Found, Following Is Displayed
            if (courseTest == true)
            {
                System.out.println("Error - Course ID Not Found In File!");
                System.out.println("Please Try Creating Course Record First,");
                return;
            }

        // Closing File
        inputFile.close();
    }

    public static void addEnrollment(String[] args) throws IOException 
    {
        // Local Variable Declaration - For addEnrollment Method
        int enrollmentIDNumber, studentIDNumber, courseIDNumber;
        String enrollmentSemester, enrollmentYear, enrollmentGrade;
        Boolean studentTest, courseTest;

        // Keyboard Scanner Setup
        Scanner keyboard = new Scanner(System.in);

        // Ask For Enrollment ID
        System.out.println("Enter Enrollment ID: ");
		enrollmentIDNumber = keyboard.nextInt();

        // Ask For Student ID
        System.out.println("Enter The Student's ID: ");
		studentIDNumber = keyboard.nextInt();

        // Ask for Course ID
        System.out.println("Enter The Course ID: ");
		courseIDNumber = keyboard.nextInt();

        // Consumes Remaining Newline Character
        keyboard.nextLine();

        // Ask For The Semester
        System.out.println("Enter The Semester: ");
		enrollmentSemester = keyboard.nextLine();

        // Ask For The Year
        System.out.println("Enter The Year: ");
		enrollmentYear = keyboard.nextLine();

        // Ask For The Grade
        System.out.println("Enter The Grade: ");
		enrollmentGrade = keyboard.nextLine();

        // Checkpoint 1 - Checking Student File To See If Student ID Exists

        //Opens Student File - To Read Through
        File openStudentFile = new File("student.txt");
        Scanner inputStudentFile = new Scanner(openStudentFile);

        // Setting studentTest Boolean To True
        studentTest = true;

        // Beggining of Loop To Read Through File
        while (inputStudentFile.hasNext())
            {
                // Gathers First Line
                String studentIDOnFile = inputStudentFile.nextLine();

                // Converts Line Into Integer
                int convertedID = Integer.parseInt(studentIDOnFile);
                
                // Consumes Lines Of "Extra Information"
                inputStudentFile.nextLine();
                inputStudentFile.nextLine();
                inputStudentFile.nextLine();
                inputStudentFile.nextLine();
                inputStudentFile.nextLine();
                inputStudentFile.nextLine();

                // Checks To See If Student ID Exists In File
                if (convertedID == studentIDNumber)
                {
                    System.out.println("Success! Student ID Exists in File!");

                    // Sets studentTest Boolean To False
                    studentTest = false;
                    
                    break;
                }

                // Continues Loop, Looking Through File
                if (!(convertedID == studentIDNumber))
                {
                    continue;
                }

            }

        // Closing Student File
        inputStudentFile.close();

        // If Boolean Remains True, Error Is Displayed; Student ID Not Found In File.
        if (studentTest == true)
        {
            System.out.println("Error - Student ID Not Found In File!");
            System.out.println(" - - Record Not Written To File! - - ");
            System.out.println("Please Try Creating Student Record First,");
            return;
        }

        // Checkpoint 2 - Checking Course File To See If Course ID Exists
    
        //Opens Course File - To Read Through
        File openCourseFile = new File("course.txt");
        Scanner inputCourseFile = new Scanner(openCourseFile);

        // Setting courseTest Boolean To True
        courseTest = true;
        
        //Beggining Of Loop To Read Through File
        while (inputCourseFile.hasNext())
            {
                // Gather's First Line
                String courseIDOnFile = inputCourseFile.nextLine();
                
                // Converts Line Into Integer
                int convertedCourseID = Integer.parseInt(courseIDOnFile);
                
                // Consumes Lines Of "Extra Infomation"
                inputCourseFile.nextLine();
                inputCourseFile.nextLine();
                
                // Checks To see If Course ID Exists In File
                if (convertedCourseID == courseIDNumber)
                {
                    System.out.println("Success! Course ID Exists In File!");
                    // Sets courseTest Boolean to False
                    courseTest = false;
                    continue;
                }

                // Continues Loop, Looking Through File
                if (!(convertedCourseID == courseIDNumber))
                {
                    continue;
                }

            }

        // Closing Course File
        inputCourseFile.close();

        // If Boolean Remains True, Error Is Displayed; Course ID Not Found In File.
        if (courseTest == true)
        {
             System.out.println("Error - Course ID Does Not Exist In File!");
             System.out.println(" - - Record Not Written To File! - - ");
             System.out.println("Please Try Creating Course First,");
             return;
        }

        // Checkpoint 3 - Checking To See If Enrollment ID Doesn't Already Exist

        // Opens The Enrollment File
        File openEnrollmentFile = new File("enrollment.txt");
        Scanner inputEnrollmentFile = new Scanner(openEnrollmentFile);

        // Loop Searching Through Enrollment File
        enrollmentloop:
        while (inputEnrollmentFile.hasNext())
            {
                // Gathers First Line
                String enrollmentIDOnFile = inputEnrollmentFile.nextLine();

                // Converts Line Into Integer
                int convertedEnrollmentID = Integer.parseInt(enrollmentIDOnFile);
                
                // Consumes Lines Of "Extra Information"
                inputEnrollmentFile.nextLine();
                inputEnrollmentFile.nextLine();
                inputEnrollmentFile.nextLine();
                inputEnrollmentFile.nextLine();
                inputEnrollmentFile.nextLine();

                // Checks To See If Enrollment ID Is Already In File - Closes File & Breaks Into Main Menu
                if (convertedEnrollmentID == enrollmentIDNumber)
                {
                    System.out.println("Error - Enrollment ID Already In File!");
                    System.out.println(" - - Record Not Written To File! - - ");
                    System.out.println("Please Try A Different Enrollment ID,");
                    // Closing File
                    inputEnrollmentFile.close();
                    return;
                }

                // Continues Loop, Looking Through File
                if (!(convertedEnrollmentID == enrollmentIDNumber))
                {
                    continue;
                }

                // Stops Looking Through & Breaks Out Of Loop
                else if (!(convertedEnrollmentID == enrollmentIDNumber))
                {
                    break enrollmentloop;
                }
            }

            System.out.println("Writing Enrollmment Record Into File...");

            // Opens File To Write To
            FileWriter fwriter = new FileWriter("enrollment.txt",true);
            PrintWriter outputFile = new PrintWriter(fwriter);
            
            // Output - Written To File
            outputFile.println(enrollmentIDNumber);
            outputFile.println(studentIDNumber);
            outputFile.println(courseIDNumber);
            outputFile.println(enrollmentSemester);
            outputFile.println(enrollmentYear);
            outputFile.println(enrollmentGrade);
            
            // Closing File
            System.out.println("Closing output File");
            outputFile.close();



    }

}
