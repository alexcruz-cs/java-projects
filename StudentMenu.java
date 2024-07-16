// Programmer  : Alex Cruz
// Date        : 9/19/2023
// Description : Homework 3: Student Menu - This program is an exercise in loops, nested loops, and reading and writing to files.

// Imported Utility
import java.util.Scanner;
import java.io.*;

public class StudentMenu {

	public static void main(String[] args) throws IOException {
		
		// Variable Declaration
		Boolean menuIterator;

		// Variable For User
		int userChoice; 

		// Variables For Student 
		int studentIDNumber, displayStudent;
		String studentFirstName, studentLastName, studentAddress, studentCity, studentState, studentZip;
		
		// Variables For Course
		int courseIDNumber, displayCourse;
		String courseName, courseDescription;

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
			System.out.println("5. Exit Program");
			
			// Let User Input Choice
			System.out.println("Please Enter The Number For The Option:");
			userChoice = keyboard.nextInt();
			
			// If User Inputs 1 - Create Student Record (Checks If Record Exists Via ID Number)
			userchoice:
			if (userChoice == 1)
			{
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
				
				//Opens "student.txt" File - To Read Through
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
							break userchoice;
						}

						// Continues Loop, Looking Through File
						if (!(convertedID == studentIDNumber))
						{
							continue;
						}

						// Stops Looking Through & Breaks Out Of Loop
						else if (!(convertedID == studentIDNumber))
						{
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
			
			// If User Inputs 2 - Create Course Record (Checks If Record Exists Via ID Number)
			else if (userChoice == 2)
			{
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
							break userchoice;
						}

						// Continues Loop, Looking Through File
						if (!(convertedCourseID == courseIDNumber))
						{
							continue;
						}

						// Stops Looking Through & Breaks Out Of Loop
						else if (!(convertedCourseID == courseIDNumber))
						{
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
			
			// If User Inputs 3 - Display Student
			else if (userChoice == 3)
			{
				// Ask The User To Enter Student ID They Want To Find
				System.out.println("Enter Student ID You Want To Display: ");
				displayStudent = keyboard.nextInt();
					
				// Opens The Student File
				File openFile = new File("student.txt");
				Scanner inputFile = new Scanner(openFile);
								
				// Loop Searching Through The Student File
				studentfindloop:
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
					if (studentIDNumber == displayStudent)
					{
						System.out.println("Student ID Number: " + studentIDNumber);
						System.out.println("Student First Name: " + inputName);
						System.out.println("Student Last Name: " + inputLastName);
						System.out.println("Adress: " + inputAddress);
						System.out.println("City: " + inputCity);
						System.out.println("State: " + inputState);
						System.out.println("Zip Code: " + inputZip);
						break;
					}

					// Continues Looping Through File
					if (!(studentIDNumber == displayStudent))
					{
					  continue;
					}

					// If Record Is Not Found, Follwoing is Displayed
					else if (!(studentIDNumber == displayStudent))
					{
						System.out.println("Error - Student ID Not Found In File!");
						System.out.println("Try Creating New Student Record,");
						break studentfindloop;
					}
		
				}
				
				// Closing File
				inputFile.close();
				
			}
			
			// If User Inputs 4 - Display Course
			else if (userChoice == 4)
			{
				// Ask The User To Enter Course ID They Want To Find
				System.out.println("Enter Course ID You Want To Display: ");
				displayCourse = keyboard.nextInt();
				
				// Opens The Course File
				File openFile = new File("course.txt");
				Scanner inputFile = new Scanner(openFile);
								
				// Loop Searching Through The Course File
				while (inputFile.hasNextLine()) 
				{
					// Gathers First Line
					String inputID = inputFile.nextLine();
					
					// Converts Line Into Integer
					courseIDNumber = Integer.parseInt(inputID);
					
					// Gathering Liens Of Information & Initializing Variables
					String inputName = inputFile.nextLine();			
					String inputDescription = inputFile.nextLine();

					
					// If Record Is Found, Following is Displayed				
					if (courseIDNumber == displayCourse)
					{
						System.out.println("Course ID: " + courseIDNumber);
						System.out.println("Course Name: " + inputName);
						System.out.println("Course Description: " + inputDescription);
						break;
					}

					// If Record Is Not Found, Following Is Displayed
					else if (!(courseIDNumber == displayCourse))
					{
						System.out.println("Error - Course ID Not Found In File!");
						System.out.println("Try Creating New Course Record,");
						break;
					}

				}
				
				// Closing File
				inputFile.close();
			}
			
			// 
			else if (userChoice == 5)
			{
				System.out.println("Thank you, Bye!");
				menuIterator = false;
			}
			
			else if (userChoice < 1)
			{
				System.out.println("Error, Please Enter A Valid Option of 1-5");
			}
			
			else if (userChoice >5)
			{
				System.out.println("Error, Please Enter A Valid Option of 1-5");
			}
			
		} while (menuIterator == true);

	}

}
