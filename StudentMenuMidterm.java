// Programmer  : Alex Cruz
// Date        : 10/30/2023
// Description : Midterm: Student Menu Update - This program is a collective exercise in classes, methods, loops, text processing, files, and decision structures. 

// Imported Utility
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

// Student File Manager Class - Manages Reading and Writing to Student File
class StudentFileManager
{
    // Student File Manager Variables
    private Scanner inputFile;
    private String informationLine;

    // Creating Student Object ArrayList
    ArrayList<Student> studentArray = new ArrayList<Student>();
    
    // Constructor - Reads Through Student File
    public StudentFileManager(String studentFile) throws IOException
    {
        // Opens Student File and Creates New Scanner Object To Read Through File
        File openFile = new File(studentFile);
        inputFile = new Scanner(openFile);

        // While Loop To Check Through Student File
        while (inputFile.hasNext())
        {
            // Variables
            int inputIDNumber, convertedID;
            String idToConvert, inputFirstName, inputLastName, inputAddress, inputCity, inputState, inputZip;

            // Lines are Read Into "informationline" Variable
            informationLine = inputFile.nextLine();

            // Creates List of String and Splits Line Using "," Delimiter
            String[] inputTokens = informationLine.split(",");

            // Convert Student ID String To Integer
            idToConvert = inputTokens[0];
            convertedID = Integer.parseInt(idToConvert);

            // Collecting Student Information from inputTokens
            inputIDNumber = convertedID;
            inputFirstName = inputTokens[1];
            inputLastName = inputTokens[2];
            inputAddress = inputTokens[3];
            inputCity = inputTokens[4];
            inputState = inputTokens[5];
            inputZip = inputTokens[6];

            // Create New Student Object With input Information
            Student fileStudent = new Student(inputIDNumber, inputFirstName, inputLastName, inputAddress, inputCity, inputState, inputZip);

            // Add Student Object Into ArrayList
            studentArray.add(fileStudent);
        }
        
        // Closing inputFile
        inputFile.close();
    }

    // Add Student Method
    public boolean addStudent(Student newStudent) throws IOException
    {
        // addStudent Method Variables
        int newStudentID;

        // Initializes New Student ID with ID From newStudent Object
        newStudentID = newStudent.studentIDNumber;

        // Sends New Student ID To getStudent Method
        getStudent(newStudentID);

        // If getStudent Method Returns "null", Student ID Doesn't Exist - Add Student To ArrayList
        if (getStudent(newStudentID) == null)
        {
            // Add Student Object To ArrayList
            studentArray.add(newStudent);

            // Creates PrintWriter Object
            PrintWriter outputFile = new PrintWriter("student.txt");

            // OverWrites File Line By Line
            for (Student filedStudent : studentArray)
            {
                outputFile.println(filedStudent.studentIDNumber + "," + filedStudent.studentFirstName + "," + filedStudent.studentLastName + "," + filedStudent.studentAddress + "," + filedStudent.studentCity + "," + filedStudent.studentState + "," + filedStudent.studentZip);
            }

            // Close Output File
            outputFile.close();

            // Return Boolean
            return true;
        }

        // If getStudent Method Returns Student Object, Student ID Exists - Don't Add To ArrayList
        else
        {
            // Return Boolean
            return false;
        } 

    }

    
    // Update Student Method
    public boolean updateStudent(Student updatedStudent) throws IOException
    {
        // Update Student Variables
        int updatedIDNumber;

        // Initializes Updated Student ID with ID From Student Object
        updatedIDNumber = updatedStudent.studentIDNumber;

        // Sends Updated Student ID To getStudent Method
        getStudent(updatedIDNumber);

        // If getStudent Method Returns Student Object, Student ID Exists In File - Update Student Object
        if (getStudent(updatedIDNumber) != null)
        {
            // Gets Index For Position To Update Student Object
            for (int index = 0; index < studentArray.size(); index++)
            {
                // Gets The Student Object Using Index
                Student fileStudent = studentArray.get(index);

                // Checks If Student ID From File is Equal To Input ID
                if (fileStudent.studentIDNumber == updatedIDNumber)
                {
                    // Rewrites Updated Student Object Into StudentArray
                    studentArray.set(index, updatedStudent);

                    // Creates New Printwriter Object
                    PrintWriter outputFile = new PrintWriter("student.txt");

                    // OverWrites Output To File
                    for (Student filedStudent : studentArray)
                    {
                        outputFile.println(filedStudent.studentIDNumber + "," + filedStudent.studentFirstName + "," + filedStudent.studentLastName + "," + filedStudent.studentAddress + "," + filedStudent.studentCity + "," + filedStudent.studentState + "," + filedStudent.studentZip);
                    }

                    // Closes Output File
                    outputFile.close();
                }

            }

            // Return Boolean
            return true;

        }

        // If getStudent Method Returns "null", Student ID Doesn't Exist In File - Don't Update Object
        else
        {
            // Return Boolean
            return false;
        } 

    }
   
    // getStudent Method
    Student getStudent(int id)
    {        
        // Loop To Go Through Student Object ArrayList
        for (Student fileStudent : studentArray)
        {
            // Checks If Student ID From File Already Exists
            if (fileStudent.studentIDNumber == id)
            {
                //Variables To Return
                int returnID;
                String returnFirstName, returnLastName, returnAddress, returnCity, returnState, returnZip;

                // Variable Initialization - To Add To Student Object
                returnID = fileStudent.studentIDNumber;
                returnFirstName = fileStudent.studentFirstName;
                returnLastName = fileStudent.studentLastName;
                returnAddress = fileStudent.studentAddress;
                returnCity = fileStudent.studentCity;
                returnState = fileStudent.studentState;
                returnZip = fileStudent.studentZip;

                // Student Object Creation - To Return
                Student returnStudent = new Student(returnID, returnFirstName, returnLastName, returnAddress, returnCity, returnState, returnZip);

                // Returns Student Object
                return returnStudent;
            }

        }

        // If Student ID Doesn't Exist, Returns "null"
        return null;

    }

}

// Course File Manager Class
class CourseFileManager
{
    // Course File Manager Variables
    private Scanner inputFile;
    private String informationLine;

    // Creating Course Object Array List
    ArrayList<Course> courseArray = new ArrayList<Course>();

    // Constructor - Reads Through Course File
    public CourseFileManager (String courseFile) throws IOException
    {
        // Opens Course File and Creates new Scanner Object To Read Through File
        File openFile = new File(courseFile);
        inputFile = new Scanner(openFile);

        // While Loop To Check Through Course File
        while (inputFile.hasNext())
        {
            // Variables
            int inputIDNumber, convertedID;
            String idToConvert, inputCourseName, inputCourseDescription;

            // Lines Are Read Into "informationline" Variable
            informationLine = inputFile.nextLine();

            // Creates List of String and Splits Line Using "," Delimiter
            String[] inputTokens = informationLine.split(",");

            // Convert Course ID String To Integer
            idToConvert = inputTokens[0];
            convertedID = Integer.parseInt(idToConvert);

            // Collecting Course Information from inputTokens
            inputIDNumber = convertedID;
            inputCourseName = inputTokens[1];
            inputCourseDescription = inputTokens[2];

            // Create New Course Object With Input Information
            Course fileCourse = new Course(inputIDNumber, inputCourseName, inputCourseDescription);

            // Add Course Object Into ArrayList
            courseArray.add(fileCourse);

        }

        // Closing inputFile
        inputFile.close();

    }

    // Add Course Method
    public boolean addCourse(Course newCourse) throws IOException
    {
        // addCoruse Method Variables
        int newCourseID;

        // Initializes New Course ID with ID From newCourse Object
        newCourseID = newCourse.courseIDNumber;

        // Sends New Course ID to getCourse Method
        getCourse(newCourseID);

        // If getCourse Method Returns "null", Course ID Doesn't Exist - Add Course To Array List
        if (getCourse(newCourseID) == null)
        {
            // Add Course Object To Array List
            courseArray.add(newCourse);

            // Creates Printwriter Object
            PrintWriter outputFile = new PrintWriter("course.txt");

            // OverWrites File Line By Line
            for (Course filedCourse : courseArray)
            {
                outputFile.println(filedCourse.courseIDNumber + "," + filedCourse.courseName + "," + filedCourse.courseDescription);
            }

            // Close Output File
            outputFile.close();

            // Return Boolean
            return true;
        }

        // If getCourse Method Returns Course Object, Course ID Exists - Don't Add To ArrayList
        else
        {
            // Return Boolean
            return false;
        }
    }

    // Update Course Method
    public boolean updateCourse(Course updatedCourse) throws IOException
    {
        // Update Course Variables
        int updatedIDNumber;

        //Initializes Updated CourseID With ID From Course Object
        updatedIDNumber = updatedCourse.courseIDNumber;

        // Sends Updated Course ID to getCourse Method
        getCourse(updatedIDNumber);

        // If getCourse Method Returns Course Object, Course ID Exists In File - Update Course Object
        if (getCourse(updatedIDNumber) != null)
        {
            // Gets Index For Position To Update Course Object
            for (int index = 0; index < courseArray.size(); index++)
            {
                // Gets The Course Object Using Index
                Course fileCourse = courseArray.get(index);

                // Checks If Course ID From File Is Equal TO Input ID
                if (fileCourse.courseIDNumber == updatedIDNumber)
                {
                    // ReWrites Updated Student Object INto CourseArray
                    courseArray.set(index, updatedCourse);

                    // Creates New Printeriter Object
                    PrintWriter outputFile = new PrintWriter("course.txt");

                    // Writes Output To File
                    for (Course filedCourse : courseArray)
                    {
                        outputFile.println(filedCourse.courseIDNumber + "," + filedCourse.courseName + "," + filedCourse.courseDescription);
                    }

                    // Closes Output File
                    outputFile.close();
                }

            }

            // Return Boolean
            return true;
        }

        // If getCourse Method Returns "null", Course ID Doesn't Exist In File - Don't Updated Object
        else
        {
            // Return Boolean
            return false; 
        }


    }

    // getCourse Method
    Course getCourse(int id)
    {
        // Loop To Go Through Course Object ArrayList
        for (Course fileCourse : courseArray)
        {
            // Checks IF Course ID From File Is Equal To Input ID
            if (fileCourse.courseIDNumber == id)
            {
                // Variables To Return
                int returnID;
                String returnName, returnDescription;

                // Variable Initialization - To Add To Course Object
                returnID = fileCourse.courseIDNumber;
                returnName = fileCourse.courseName;
                returnDescription = fileCourse.courseDescription;

                // Course Object Creation - To Return
                Course returnCourse = new Course(returnID, returnName, returnDescription);

                // Return Course Object
                return returnCourse;

            }

        }

        // If Course ID Doesn't Exist, Returns "null"
        return null;

    }

}

// Enrollment File Manager Class
class EnrollmentFileManager
{
    // Enrollment File Manager Variables
    private Scanner inputFile;
    private String informationLine;

    // Creating Enrollment Object ArrayList
    ArrayList<Enrollment> enrollmentArray = new ArrayList<Enrollment>();

    // Constructor - Reads Through Enrollment File
    public EnrollmentFileManager (String enrollmentFile) throws IOException
    {
        // Opens Enrollment File and Creates New Scanner Object To Read Through File
        File openFile = new File(enrollmentFile);
        inputFile = new Scanner(openFile);

        while (inputFile.hasNext())
        {
            // Variables
            int inputIDNumber, inputStudentID, inputCourseID, inputYear, convertedEnrollmentID, convertedStudentID, convertedCourseID, convertedCourseYear;
            String enrollmentIDToConvert, studentIDToConvert, courseIDToConvert, courseYearToConvert, inputSemester, inputGrade;

            // Lines Are Read Into "informationline" Variable
            informationLine = inputFile.nextLine();

            // Creates List Of String and Splits Line using "," Delimiter
            String[] inputTokens = informationLine.split(",");

            // Covnert Enrollment ID String To Integer
            enrollmentIDToConvert = inputTokens[0];
            convertedEnrollmentID = Integer.parseInt(enrollmentIDToConvert);

            // Convert Student ID String to Integer
            studentIDToConvert = inputTokens[1];
            convertedStudentID = Integer.parseInt(studentIDToConvert);

            // Convert Course ID String To Integer
            courseIDToConvert = inputTokens[2];
            convertedCourseID = Integer.parseInt(courseIDToConvert);

            // Convert Course Year String To Integer
            courseYearToConvert = inputTokens[4];
            convertedCourseYear = Integer.parseInt(courseYearToConvert);


            // Collecting Of Enrollment Information From InputTokens
            inputIDNumber = convertedEnrollmentID;
            inputStudentID = convertedStudentID;
            inputCourseID = convertedCourseID;
            inputSemester = inputTokens[3];
            inputYear = convertedCourseYear;
            inputGrade = inputTokens[5];

            // Creates New Enrollment Object With input Information
            Enrollment fileEnrollment = new Enrollment(inputIDNumber, inputStudentID, inputCourseID, inputSemester, inputYear, inputGrade);

            // Add Enrollment Object Into ArrayList
            enrollmentArray.add(fileEnrollment);

        }

        // Closing InputFile
        inputFile.close();

    }

    // Add Enrollment Method
    public boolean addEnrollment (Enrollment newEnrollment) throws IOException
    {

        // addEnrollment Method Variables
        int newEnrollmentID, studentID, courseID, newEnrollmentYear;
        String newEnrollmentSemester;

        // Initializes New Enrollment ID with ID From Enrollment Object
        newEnrollmentID = newEnrollment.enrollmentIDNumber;
        studentID = newEnrollment.studentIDNumber;
        courseID = newEnrollment.courseIDNumber;
        newEnrollmentYear = newEnrollment.enrollmentYear;
        newEnrollmentSemester = newEnrollment.enrollmentSemester;

        // Sends Enrollment Arguments to getEnrollment Method
        getEnrollment(newEnrollmentID, studentID, courseID, newEnrollmentYear, newEnrollmentSemester);

        // If getEnrollment Method Returns "null", Enrollment Object Doesn't Exist - Add Enrollment To ArrayList
        if (getEnrollment(newEnrollmentID, studentID, courseID, newEnrollmentYear, newEnrollmentSemester) == null)
        {
            //Add To ArrayList
            enrollmentArray.add(newEnrollment);

            // Creates Printwriter Object
            PrintWriter outputFile = new PrintWriter("enrollment.txt");

            // OverWrites File Line By Line
            for (Enrollment filedEnrollment : enrollmentArray)
            {
                outputFile.println(filedEnrollment.enrollmentIDNumber + "," + filedEnrollment.studentIDNumber + "," + filedEnrollment.courseIDNumber + "," + filedEnrollment.enrollmentSemester + "," + filedEnrollment.enrollmentYear + "," + filedEnrollment.enrollmentGrade);

            }

            // Close Output File
            outputFile.close();

            // Return Boolean
            return true;
        }

        // If getEnrollment Method Returns Enrollment Object, Enrollment Exists - Don't Add To ArrayList
        else
        {
            // Return Boolean
            return false;
        }

    }

    // Updated Enrollment Method
    public boolean updateEnrollment(Enrollment updatedEnrollment) throws IOException
    {
        // Update Enrollment Variables
        int updatedEnrollmentID, studentID, courseID, updatedYear;
        String updatedSemester;

        // Initializes Updated Enrollment ID With ID From Enrollment Object
        updatedEnrollmentID = updatedEnrollment.enrollmentIDNumber;
        studentID = updatedEnrollment.studentIDNumber;
        courseID = updatedEnrollment.courseIDNumber;
        updatedYear = updatedEnrollment.enrollmentYear;
        updatedSemester = updatedEnrollment.enrollmentSemester;

        // Sends Updated Enrollment ID To getEnrollment Method
        getEnrollment(updatedEnrollmentID, studentID, courseID, updatedYear, updatedSemester);

        //If getEnrollment Method Returns Enrollment Object, Enrolment Already Exists - Update Enrollment Object
        if (getEnrollment(updatedEnrollmentID, studentID, courseID, updatedYear, updatedSemester) != null)
        {
            // Gets Index For Position to Update Enrollment Object
            for (int index = 0; index < enrollmentArray.size(); index++)
            {
                // Gets The Enrollment Object Using Index
                Enrollment fileEnrollment = enrollmentArray.get(index);

                // Checks If Enrollment ID From File Is Equal To Input ID
                if (fileEnrollment.enrollmentIDNumber == updatedEnrollmentID)
                {
                    // Rewrites Updated Enrollment Object Into Enrollment Array
                    enrollmentArray.set(index, updatedEnrollment);

                    // Creates New Printwriter Object
                    PrintWriter outputFile = new PrintWriter("enrollment.txt");

                    // OverWrites Output To File
                    for (Enrollment filedEnrollment : enrollmentArray)
                    {
                        outputFile.println(filedEnrollment.enrollmentIDNumber + "," + filedEnrollment.studentIDNumber + "," + filedEnrollment.courseIDNumber + "," + filedEnrollment.enrollmentSemester + "," + filedEnrollment.enrollmentYear + "," + filedEnrollment.enrollmentGrade);
                    }

                    // Closes Output File
                    outputFile.close();

                }

            }

            // Return Boolean
            return true;

        }

        // If getEnrollment Method Returns Enrollment Object - Don't Update Object
        else
        {
            // Return Boolean
            return false; 
        }
    }

    // Get Enrollment Method - For Adding or Updating
    Enrollment getEnrollment (int eid, int sid, int cid, int year, String semester)
    {
        // Loop To Go Through Enrollment Object ArrayList
        for (Enrollment fileEnrollment : enrollmentArray)
        {
            // Checks If Enrollment ID From File Already Exists
            if (fileEnrollment.enrollmentIDNumber == eid)
            {
                // Variables To Return
                int returnID, returnSID, returnCID, returnYear;
                String returnSemester, returnGrade;

                // Variable Initialization - To Add To Enrollment Object
                returnID = fileEnrollment.enrollmentIDNumber;
                returnSID = fileEnrollment.studentIDNumber;
                returnCID = fileEnrollment.courseIDNumber;
                returnSemester = fileEnrollment.enrollmentSemester;
                returnYear = fileEnrollment.enrollmentYear;
                returnGrade = fileEnrollment.enrollmentGrade;

                // Enrollment Object Creation - To Return
                Enrollment returnEnrollment = new Enrollment(returnID, returnSID, returnCID, returnSemester, returnYear, returnGrade);

                // Returns Enrollment Object
                return returnEnrollment;
            }

        }

        // If Enrollment ID Doesn't Exist, Returns "null"
        return null;
    }

    
    // OverLoaded getEnrollment Method - For Displaying 
    Enrollment getEnrollment (int eid)
    {
        // Loop To Go Through Enrollment Object ArrayList
        for (Enrollment fileEnrollment : enrollmentArray)
        {
            // Checks If Enrollment ID From File Already Exists
            if (fileEnrollment.enrollmentIDNumber == eid)
            {
                // Variables To Return
                int returnID, returnSID, returnCID, returnYear;
                    String returnSemester, returnGrade;

                // Variable Initialization
                returnID = fileEnrollment.enrollmentIDNumber;
                returnSID = fileEnrollment.studentIDNumber;
                returnCID = fileEnrollment.courseIDNumber;
                returnSemester = fileEnrollment.enrollmentSemester;
                returnYear = fileEnrollment.enrollmentYear;
                returnGrade = fileEnrollment.enrollmentGrade;

                // Enrollment Object Creation - To Return
                Enrollment returnEnrollment = new Enrollment(returnID, returnSID, returnCID, returnSemester, returnYear, returnGrade);

                // Return Enrollment Object
                return returnEnrollment;
            }

        }

        // If Enrollment ID Doesn't Exist, Returns "null"
        return null;
    }
    
}

// Student Class - For Creating Student Objects
class Student
{
    // Student Class Variables
    int studentIDNumber;
    String studentFirstName, studentLastName, studentAddress, studentCity, studentState, studentZip;

    // Student Constructor
    Student (int idnumber, String firstname, String lastname, String address, String city, String state, String zip)
    {
        // Initializing Variables
        studentIDNumber = idnumber;
        studentFirstName = firstname;
        studentLastName = lastname;
        studentAddress = address;
        studentCity = city;
        studentState = state;
        studentZip = zip;
    }

}

// Course Class - For Creating Course Objects
class Course
{
    // Course Class Variables
    int courseIDNumber;
    String courseName, courseDescription;

    // Course Contructor
    Course (int idnumber, String name, String description)
    {
        // Initializing Variables
        courseIDNumber = idnumber;
        courseName = name;
        courseDescription = description;
    }

}

// Enrollment Class - For Creating Enrollment Objects
class Enrollment
{
    // Enrollment Class Variables
    int enrollmentIDNumber, studentIDNumber, courseIDNumber, enrollmentYear;
    String enrollmentSemester, enrollmentGrade;

    // Enrollment Contructor
    Enrollment (int idnumber, int studentid, int courseid, String semester, int year, String grade)
    {
        // Initializing Variables
        enrollmentIDNumber = idnumber;
        studentIDNumber = studentid;
        courseIDNumber = courseid;
        enrollmentSemester = semester;
        enrollmentYear = year;
        enrollmentGrade = grade;
    }


}

// Main Program
public class StudentMenuMidterm 
{
    public static void main(String[] args) throws IOException 
    {

        // Variable Declaration
        Boolean menuIterator;
        int userChoice;

        // Setting Up Scanner
        Scanner keyboard = new Scanner(System.in);

        // Student File Check and Creation
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

        // Creates Student File Manager Object To Read Through
        StudentFileManager studentFileManager = new StudentFileManager("student.txt");

        // Creates Course File Manager Object To Read Through
        CourseFileManager courseFileManager = new CourseFileManager("course.txt");

        // Creates Enrollment File Manager Object To Read through File
        EnrollmentFileManager enrollmentFileManager = new EnrollmentFileManager("enrollment.txt");



        menuIterator = true;

        // Menu Loop
        do
        {
            // Displays Program's Main Menu
            System.out.println(" - - - - - MAIN MENU - - - - - ");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Add Course");
            System.out.println("4. Edit Course");
            System.out.println("5. Add Enrollment");
            System.out.println("6. Edit Enrollment");
            System.out.println("7. Display Student");
            System.out.println("8. Display Course");
            System.out.println("9. Display Enrollment");
            System.out.println("10. Exit"); 

            // User Choice Input
            System.out.println("Please Enter The Number For Your Option: ");
            userChoice = keyboard.nextInt();

            // 1. Add Student
            if (userChoice == 1)
            {
                System.out.println(" - You Have Chosen Option To Add A Student - ");

                // Local Variable Declaration - For Creating New Student Object
                int studentIDNumber;
                String studentFirstName, studentLastName, studentAddress, studentCity, studentState, studentZip;

                // Ask For Student ID
                System.out.println("Please Enter the Student's ID: ");
                studentIDNumber = keyboard.nextInt();
                
                // Consumes Remaining Newline Character
                keyboard.nextLine();

                // Gather Student Information from User
                // Ask For Student Name
                System.out.println("Please Enter the Student's First Name: ");
                studentFirstName = keyboard.nextLine();
                
                // Ask For Student Last Name
                System.out.println("Please Enter the Student's Last Name: ");
                studentLastName = keyboard.nextLine();
                
                // Ask For Student Address
                System.out.println("Please Enter the Student's Address: ");
                studentAddress = keyboard.nextLine();
                
                // Ask For City
                System.out.println("Please Enter the City: ");
                studentCity = keyboard.nextLine();
                
                // Ask For State
                System.out.println("Please Enter the State: ");
                studentState = keyboard.nextLine();
                
                // Ask For Zip Code
                System.out.println("Please Enter the Zip Code: ");
                studentZip = keyboard.nextLine();

                // Creates New Student Object - To Send To addStudent Method
                Student newStudent = new Student(studentIDNumber, studentFirstName, studentLastName, studentAddress, studentCity, studentState, studentZip);

                // Sends newStudent Object to addStudent Method - If Object Is New, Will Add To ArrayList and Save To File
                if (studentFileManager.addStudent(newStudent) == true)
                {
                    System.out.println("Success! Student Was Written To File!");
                }

                // If newStudent Object Is Not New, Displays Error Message
                else
                {
                    System.out.println("Error, Student ID Already Exists.");
                }
            }

            // 2. Edit Student
            else if (userChoice == 2)
            {
                System.out.println(" - You Have Chosen Option To Edit A Student - ");

                // Local Variable Declaration - For Updating Student Object
                int studentIDNumber, updatedIDNumber;
                String updatedFirstName, updatedLastName, updatedAddress, updatedCity, updatedState, updatedZip;


                // Get ID Of Student To Update
                System.out.println("Please Enter the ID of the Student You Want To Edit: ");
                studentIDNumber = keyboard.nextInt();

                // Consumes Remaining Newline Character
                keyboard.nextLine();

                updatedIDNumber = studentIDNumber; 

                // Gather Student Information from User
                // Ask For Student Name
                System.out.println("Please Enter the Student's First Name:");
                updatedFirstName = keyboard.nextLine();

                // Ask For Student Last Name
                System.out.println("Please Enter the Student's Last Name:");
                updatedLastName = keyboard.nextLine();

                // Ask For Student Address
                System.out.println("Please Enter the Address:");
                updatedAddress = keyboard.nextLine();

                // Ask For City
                System.out.println("Please Enter the City:");
                updatedCity = keyboard.nextLine();

                // Ask For State
                System.out.println("Please Enter the State:");
                updatedState = keyboard.nextLine();

                // Ask For Zip Code
                System.out.println("Please Enter the Zip Code:");
                updatedZip = keyboard.nextLine();

                // Creates updated Student Object - To Send To updateStudent Method
                Student updatedStudent = new Student(updatedIDNumber, updatedFirstName, updatedLastName, updatedAddress, updatedCity, updatedState, updatedZip);

                // Sends updatedStudent Object to updateStudent Method = If Object Exists, Student Object Will Be Updated.
                if (studentFileManager.updateStudent(updatedStudent) == true)
                {
                    System.out.println("Success! Student Was Updated!");
                }

                // If Student Object Doesn't Exist, Displays Error Message
                else
                {
                    System.out.println("Error, Student ID Doesn't Exist In File.");
                }


            }

            // 3. Add Course
            else if (userChoice == 3)
            {
                System.out.println(" - You Have Chosen To Add A Course - ");

                //Local Variable Declaration - For Creating New Course Object
                int courseIDNumber;
                String courseName, courseDescription;

                // Ask For Course ID
                System.out.println("Please Enter The Course ID: ");
                courseIDNumber = keyboard.nextInt();

                // Consumes Remaining Newline Character
                keyboard.nextLine();

                // Ask For Course Name
                System.out.println("Please Enter The Course Name: ");
                courseName = keyboard.nextLine();

                // Ask For Course Description
                System.out.println("Please Enter The Course Description");
                courseDescription = keyboard.nextLine();

                // Creates New Course Object - To Send To addCourse Method
                Course newCourse = new Course(courseIDNumber, courseName, courseDescription);

                // Sends newCourse Object to addCourse Method - If Object Is New, Will Add To ArrayList and Save To File
                if (courseFileManager.addCourse(newCourse) == true)
                {
                    System.out.println("Success! Course Was Written To Array and File");
                }

                // If newCourse Object Is Not New, Displays Error Message
                else 
                {
                    System.out.println("Error, Course ID Already Exists.");
                }
            }

            // 4. Edit Course
            else if (userChoice == 4)
            {
                System.out.println(" - You Have Chosen To Edit A Course - ");

                // Local Variable Declaration - For Updating Course Object
                int courseIDNumber, updatedCourseID;
                String updatedCourseName, updatedCourseDescription;

                // Ask For Course ID To Update
                System.out.println("Please Enter the ID of the Course You Want To Edit: ");
                courseIDNumber = keyboard.nextInt();
                updatedCourseID = courseIDNumber;

                // Consumes Remaining NewLine Character
                keyboard.nextLine();

                // Gather Course Information From User
                // Ask For Course Name
                System.out.println("Please Enter The Course Name: ");
                updatedCourseName = keyboard.nextLine();

                // Ask For Course Description
                System.out.println("Please Enter The Course Description: ");
                updatedCourseDescription = keyboard.nextLine();

                // Creates New Course Object
                Course updatedCourse = new Course(updatedCourseID, updatedCourseName, updatedCourseDescription);

                // Sends updatedCourse Object to updateCourse Method - If Object Is New, Will Update In ArrayList and Save To File
                if (courseFileManager.updateCourse(updatedCourse) == true)
                {
                    System.out.println("Success! Course Was Updated");
                }

                // If Course Object Doesn't Exist, Displays Error Message
                else
                {
                    System.out.println("Error, Course ID Doesn't Exist In File");
                }
            }

            // 5. Add Enrollment
            else if (userChoice == 5)
            {
                System.out.println(" - You Have Chosen To Add An Enrollment - ");

                // Local Variable Declaration - For Creating Enrollment Object
                int enrollmentIDNumber, studentIDNumber, courseIDNumber, enrollmentYear;
                String enrollmentSemester, enrollmentGrade;

                // Ask For Enrollment ID
                System.out.println("Please Enter the Enrollment ID: ");
                enrollmentIDNumber = keyboard.nextInt();

                // Ask For Student ID
                System.out.println("Please Enter the Student ID: ");
                studentIDNumber = keyboard.nextInt();

                // Ask For Course ID
                System.out.println("Please Enter the Course ID: ");
                courseIDNumber = keyboard.nextInt();

                // Consumes Remaining Newline Character
                keyboard.nextLine();

                // Ask For Ennrollment Semester
                System.out.println("Please Enter the Enrollment Semester: ");
                enrollmentSemester = keyboard.nextLine();
                
                // Ask For Ennrollment Year
                System.out.println("Please Enter the Enrollment Year: ");
                enrollmentYear = keyboard.nextInt();

                // Consumes Remaining Newline Character
                keyboard.nextLine();
                
                // Ask For Grade
                System.out.println("Please Enter The Enrollment Grade: ");
                enrollmentGrade = keyboard.nextLine();

                // If Enrollment ID Doesn't Exist In File - Continue To Check Student ID
                if (enrollmentFileManager.getEnrollment(enrollmentIDNumber, studentIDNumber, courseIDNumber, enrollmentYear, enrollmentSemester) == null)
                {
                    // If Student ID Exists In File - Continue To Check Course ID
                    if (studentFileManager.getStudent(studentIDNumber) != null)
                    {
                        // If Course ID Exists In File - Continue To Create Enrollment Object
                        if (courseFileManager.getCourse(courseIDNumber) != null)
                        {
                            // Creates new Enrollment Object
                            Enrollment newEnrollment = new Enrollment(enrollmentIDNumber, studentIDNumber, courseIDNumber, enrollmentSemester, enrollmentYear, enrollmentGrade);
                            
                            // Sends newEnrollment To addEnrollment Method - If Object Is New, Will Add To ArrayList and Save To File
                            if (enrollmentFileManager.addEnrollment(newEnrollment) == true)
                            {
                                System.out.println("Success! Enrollment Was Written To File!");

                            }

                        }

                        // If Course ID Doesn't Exist In File, Displays Error Message
                        else
                        {
                            System.out.println("Error, Course ID Doesn't Exist In File.");
                        }
                    }

                    // If Student ID Doesn't Exist In File, Displays Error Message
                    else
                    {
                        System.out.println("Error, Student ID Doesn't Exist In File.");
                    }
                }

                // If Enrollment ID Already Exists In File, Displays Error Message
                else
                {
                    System.out.println("Error, Enrollment Already Exists In File.");
                }
                
            }

            // 6. Edit Enrollment
            else if (userChoice == 6)
            {
                System.out.println(" - You Have Chosen To Edit An Enrollment. - ");

                // Local Variable Declaration - For Updating Enrollment Object 
                int enrollmentIDNumber, updatedStudentID, updatedCourseID, updatedYear;
                String updatedSemester, updatedGrade;

                // Ask For Enrollment ID
                System.out.println("Enter The ID of the Enrollment You Want To Edit: ");
                enrollmentIDNumber = keyboard.nextInt();

                // Ask For Student ID
                System.out.println("Please Enter the Student ID: ");
                updatedStudentID = keyboard.nextInt();

                // Consumes Remaining Newline Character
                keyboard.nextLine();

                // Ask For Course ID
                System.out.println("Please Enter the Course ID: ");
                updatedCourseID = keyboard.nextInt();

                // Consumes Remaining Newline Character
                keyboard.nextLine();

                // Ask For Semester
                System.out.println("Please Enter the Semester: ");
                updatedSemester = keyboard.nextLine();

                // Ask For Year
                System.out.println("Please Enter the Year: ");
                updatedYear = keyboard.nextInt();

                // Consumes Remaining Newline Character
                keyboard.nextLine();

                // Ask For Grade 
                System.out.println("Please Enter the Grade: ");
                updatedGrade = keyboard.nextLine();

                // If Enrollment ID Exists In File - Continue To Check Student ID
                if (enrollmentFileManager.getEnrollment(enrollmentIDNumber, updatedStudentID, updatedCourseID, updatedYear, updatedSemester) != null)
                {
                    // IF Student Id Exists In File - Continue To Check Course ID
                    if(studentFileManager.getStudent(updatedStudentID) != null)
                    {
                        // IF Course ID Exists In File - Continue To Create Enrollment Object
                        if (courseFileManager.getCourse(updatedCourseID) != null)
                        {
                            // Create New Enrollment Object
                            Enrollment updatedEnrollment = new Enrollment(enrollmentIDNumber, updatedStudentID, updatedCourseID, updatedSemester, updatedYear, updatedGrade);

                            // Write Enrollment Object To ArrayList And File
                            if (enrollmentFileManager.updateEnrollment(updatedEnrollment) == true)
                            {
                                System.out.println("Success! Enrollment Was Updated!");
                            }

                        }

                        // If Course ID Doesn't Exist In File, Displays Error Message
                        else
                        {
                            System.out.println("Error, Course ID Doesn't Exist In File");
                        }

                    }

                    // If Student ID Doesn't Exist In File, Displays Error Message
                    else
                    {
                        System.out.println("Error, Student ID Doesn't Exist In File");
                    }

                }

                // If Enrollment ID Already Exists In File, Displays Error Message
                else
                {
                    System.out.println("Error, Enrollment ID Doesn't Exist In File");
                }

            }

            // 7. Display Student
            else if (userChoice == 7)
            {
                System.out.println(" - You Have Chosen To Display A Student - ");

                // Local Variable Declaration - For Displaying Student Object
                int studentIDNumber;

                // Ask For ID Of Student To Display
                System.out.println("Please Enter the ID of the Student You Want To Display: ");
                studentIDNumber = keyboard.nextInt();

                // If Student Object Is Found, Display Student
                if (studentFileManager.getStudent(studentIDNumber) != null)
                {
                    // Variables
                    int retrievedIDNumber;
                    String retrievedFirstName, retrievedLastName, retrievedAddress, retrievedCity, retrievedState, retrievedZip;
                    
                    // Initializing Retreived Variables
                    retrievedIDNumber = studentFileManager.getStudent(studentIDNumber).studentIDNumber;
                    retrievedFirstName = studentFileManager.getStudent(studentIDNumber).studentFirstName;
                    retrievedLastName = studentFileManager.getStudent(studentIDNumber).studentLastName;
                    retrievedAddress = studentFileManager.getStudent(studentIDNumber).studentAddress;
                    retrievedCity = studentFileManager.getStudent(studentIDNumber).studentCity;
                    retrievedState = studentFileManager.getStudent(studentIDNumber).studentState;
                    retrievedZip = studentFileManager.getStudent(studentIDNumber).studentZip;

                    // Display Student Information
                    System.out.println("Success, Student ID Exists In File! Now Displaying Student Information,");
                    System.out.println("Student ID Number: " + retrievedIDNumber);
                    System.out.println("Student First Name: " + retrievedFirstName);
                    System.out.println("Student Last Name: " + retrievedLastName);
                    System.out.println("Address: " + retrievedAddress);
                    System.out.println("City: " + retrievedCity);
                    System.out.println("State: " + retrievedState);
                    System.out.println("Zip Code: " + retrievedZip);

                }

                // If Student Object Isn't Found - Display Error Message
                else
                {
                    System.out.println("Error, Student ID Doesn't Exist In File");
                }
    

            }

            // 8. Display Course
            else if (userChoice == 8)
            {
                System.out.println(" - You Have Chosen To Display A Course - ");

                // Local Variable Declaration - For Displaying Course Object
                int courseIDNumber;

                // Ask For ID of Course To Display
                System.out.println("Please Enter the ID of the Course You Want To Display: ");
                courseIDNumber = keyboard.nextInt();

                // If Course Object Is Found, Display Course
                if (courseFileManager.getCourse(courseIDNumber) != null)
                {
                    // Variables
                    int retrievedIDNumber;
                    String retreivedCourseName, retreivedCourseDescription;

                    // Initializing Retreived Variables
                    retrievedIDNumber = courseFileManager.getCourse(courseIDNumber).courseIDNumber;
                    retreivedCourseName = courseFileManager.getCourse(retrievedIDNumber).courseName;
                    retreivedCourseDescription = courseFileManager.getCourse(retrievedIDNumber).courseDescription;

                    // Display Course Information
                    System.out.println("Success! Course ID Exists In File! Now Displaying Course Information,");
                    System.out.println("Course ID: " + retrievedIDNumber);
                    System.out.println("Course Name: " + retreivedCourseName);
                    System.out.println("Course Description: " + retreivedCourseDescription);

                }

                // If Course Object Isn't Found - Display Error Message
                else
                {
                    System.out.println("Error, Course ID Doesn't Exist In File");
                }

            }

            // 9. Display Enrollment
            else if (userChoice == 9)
            {
                System.out.println(" - You Have Chosen To Display An Enrollment - ");

                // Local Variable Declaration - For Displaying Enrollment Object
                int enrollmentIDNumber;

                // Ask For ID of Enrollment To Display
                System.out.println("Please Enter the ID of the Enrollment You Want To Display: ");
                enrollmentIDNumber = keyboard.nextInt();

                // If Enrollment Object Is Found, Display Course
                if (enrollmentFileManager.getEnrollment(enrollmentIDNumber) != null)
                {
                    // Variables
                    int retrievedEIDNumber, retrievedSIDNumber, retrievedCIDNumber, retrievedYear;
                    String retreivedSemester, retrievedGrade;

                    // Initializing Retreived Variables
                    retrievedEIDNumber = enrollmentFileManager.getEnrollment(enrollmentIDNumber).enrollmentIDNumber;
                    retrievedSIDNumber = enrollmentFileManager.getEnrollment(enrollmentIDNumber).studentIDNumber;
                    retrievedCIDNumber = enrollmentFileManager.getEnrollment(enrollmentIDNumber).courseIDNumber;
                    retreivedSemester = enrollmentFileManager.getEnrollment(enrollmentIDNumber).enrollmentSemester;
                    retrievedYear = enrollmentFileManager.getEnrollment(enrollmentIDNumber).enrollmentYear;
                    retrievedGrade = enrollmentFileManager.getEnrollment(enrollmentIDNumber).enrollmentGrade;

                    // Display Enrollment Information
                    System.out.println("Success, Enrollment ID Exists In File! Now Disaplying Enrollment Information,");
                    System.out.println("Enrollment ID Number: " + retrievedEIDNumber);
                    System.out.println("Student ID Number: " + retrievedSIDNumber);
                    System.out.println("Course ID Number: " + retrievedCIDNumber);
                    System.out.println("Semester: " + retreivedSemester);
                    System.out.println("Year: " + retrievedYear);
                    System.out.println("Grade: " + retrievedGrade);

                }

                // If Enrollment Object Isn't Found - Display Error Message
                else
                {
                    System.out.println("Error, Enrollment ID Doesn't Exist In File");
                }
            }

            // 10. Exit
            else if (userChoice == 10)
            {
                // Exiting The Program
                System.out.println("You Have Chosen To Close The Program.");
                System.out.println("Thank you, Bye!");
                
                // Setting menuIterator To False
				menuIterator = false;

            }

            // If User Input Is Less than 1
            else if (userChoice < 1)
            {
                // If User Enters Invalid Option Less Than 1
				System.out.println("Error, Please Enter A Valid Option of 1-10!");
            }

            // If User Input Is Greater than 10
            else if (userChoice > 10)
			{
                // If User Enters Invalid Option Greater than 6
				System.out.println("Error, Please Enter A Valid Option of 1-10!");
			}

        } while (menuIterator == true);


    }

}
