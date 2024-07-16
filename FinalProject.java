// Programmer  : Alex Cruz
// Date        : 12/16/2023
// Description : Final Project: Student Menu - This program is an exercise in creating a JavaFX GUI Program which includes exceptions, inheritance classes, methods, loops, text processing, files, and decision structures. 


// Imported Utility
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Student File Manager Class - Manages Reading and Writing to Student File
class StudentFileManager
{
    // Student File Manager Variables
    private Scanner inputFile;
    private String informationLine;

    // Creating Student Object ArrayList
    ArrayList<Student> studentArray = new ArrayList<Student>();
    
    // Constructor - Reads Through Student File
    public StudentFileManager(String studentFile)
    {
        try
        {
            // Opens Student File and Creates New Scanner Object To Read Through File
            File openFile = new File(studentFile);
            inputFile = new Scanner(openFile);
        }

        // File Not Found Exception
        catch (FileNotFoundException fnfe)
        {
            Dialog<Exception> fileNotFoundDialog = new Dialog<>();
            fileNotFoundDialog.setTitle("Error");
            fileNotFoundDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            fileNotFoundDialog.setContentText("File Not Found.");
            fileNotFoundDialog.show();
        }

        // Generic Exception
        catch (Exception e)
        {
            Dialog<Exception> genericExceptionDialog = new Dialog<>();
            genericExceptionDialog.setTitle("Error!");
            genericExceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            genericExceptionDialog.setContentText("An Error Occured");
            genericExceptionDialog.show();
        }
        
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

            try
            {
                // Create New Student Object With input Information
                Student fileStudent = new Student(inputIDNumber, inputFirstName, inputLastName, inputAddress, inputCity, inputState, inputZip);

                // Add Student Object Into ArrayList
                studentArray.add(fileStudent);
            }

            // Invalid ID Exception
            catch (InvalidID iid)
            {
                Dialog<Exception> exceptionDialog = new Dialog<>();
                exceptionDialog.setTitle("Error");
                exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                exceptionDialog.setContentText(iid.getMessage());
                exceptionDialog.show();
            }

            // Empty Field Exception
            catch (EmptyField ef)
            {
                Dialog<Exception> exceptionDialog = new Dialog<>();
                exceptionDialog.setTitle("Error");
                exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                exceptionDialog.setContentText(ef.getMessage());
                exceptionDialog.show();
            }

            // Generic Exception
            catch (Exception e)
            {
                Dialog<Exception> exceptionDialog = new Dialog<>();
                exceptionDialog.setTitle("Error");
                exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                exceptionDialog.setContentText("An error has occured.");
                exceptionDialog.show();
            }
        }
        
        // Closing inputFile
        inputFile.close();
    }

    // Add Student Method
    public boolean addStudent(Student newStudent)
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
            try
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
                
            }

            catch (FileNotFoundException fnfe)
            {
                Dialog<Exception> fileNotFoundDialog = new Dialog<>();
                fileNotFoundDialog.setTitle("Error");
                fileNotFoundDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                fileNotFoundDialog.setContentText("File Not Found.");
                fileNotFoundDialog.show();
            }

            // Generic Exception
            catch (Exception e)
            {
                Dialog<Exception> genericExceptionDialog = new Dialog<>();
                genericExceptionDialog.setTitle("Error!");
                genericExceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                genericExceptionDialog.setContentText("An Error Occured");
                genericExceptionDialog.show();
            }

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
    public boolean updateStudent(Student updatedStudent) 
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
                    try
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

                    catch (FileNotFoundException fnfe)
                    {
                        Dialog<Exception> fileNotFoundDialog = new Dialog<>();
                        fileNotFoundDialog.setTitle("Error");
                        fileNotFoundDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        fileNotFoundDialog.setContentText("File Not Found.");
                        fileNotFoundDialog.show();
                    }

                    // Generic Exception
                    catch (Exception e)
                    {
                        Dialog<Exception> genericExceptionDialog = new Dialog<>();
                        genericExceptionDialog.setTitle("Error");
                        genericExceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        genericExceptionDialog.setContentText("An Error Occured");
                        genericExceptionDialog.show();
                    }
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

                try
                {
                    // Student Object Creation - To Return
                    Student returnStudent = new Student(returnID, returnFirstName, returnLastName, returnAddress, returnCity, returnState, returnZip);

                    // Returns Student Object
                    return returnStudent;
                }

                // Invalid ID Exception
                catch (InvalidID iid)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText(iid.getMessage());
                    exceptionDialog.show();
                }

                // Empty Field Exception
                catch (EmptyField ef)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText(ef.getMessage());
                    exceptionDialog.show();
                }
            }

        }

        // If Student ID Doesn't Exist, Returns "null"
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
    Student (int idnumber, String firstname, String lastname, String address, String city, String state, String zip) throws InvalidID, EmptyField
    {
        // Exception Handling

        if (idnumber < 0)
        {
            throw new InvalidID();
        }

        if (firstname.equals(""))
        {
            throw new EmptyField();
        }

        if (lastname.equals(""))
        {
            throw new EmptyField();
        }

        if (address.equals(""))
        {
            throw new EmptyField();
        }

        if (city.equals(""))
        {
            throw new EmptyField();
        }

        if (state.equals(""))
        {
            throw new EmptyField();
        }

        if (zip.equals(""))
        {
            throw new EmptyField();
        }

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

// Course File Manager Class
class CourseFileManager
{
    // Course File Manager Variables
    private Scanner inputFile;
    private String informationLine;

    // Creating Course Object Array List
    ArrayList<Course> courseArray = new ArrayList<Course>();

    // Constructor - Reads Through Course File
    public CourseFileManager (String courseFile)
    {
        try
        {
            // Opens Course File and Creates new Scanner Object To Read Through File
            File openFile = new File(courseFile);
            inputFile = new Scanner(openFile);
        }

        // File Not Found Exception
        catch (FileNotFoundException fnfe)
        {
            Dialog<Exception> fileNotFoundDialog = new Dialog<>();
            fileNotFoundDialog.setTitle("Error");
            fileNotFoundDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            fileNotFoundDialog.setContentText("File Not Found.");
            fileNotFoundDialog.show();
        }

        // Generic Exception
        catch (Exception e)
        {
            Dialog<Exception> genericExceptionDialog = new Dialog<>();
            genericExceptionDialog.setTitle("Error!");
            genericExceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            genericExceptionDialog.setContentText("An Error Occured");
            genericExceptionDialog.show();
        }

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

            try
            {
                // Create New Course Object With Input Information
                Course fileCourse = new Course(inputIDNumber, inputCourseName, inputCourseDescription);

                // Add Course Object Into ArrayList
                courseArray.add(fileCourse);
            }

            // Invalid ID Exception
            catch (InvalidID iid)
            {
                Dialog<Exception> exceptionDialog = new Dialog<>();
                exceptionDialog.setTitle("Error");
                exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                exceptionDialog.setContentText(iid.getMessage());
                exceptionDialog.show();
            }

            // Empty Field Exception
            catch (EmptyField ef)
            {
                Dialog<Exception> exceptionDialog = new Dialog<>();
                exceptionDialog.setTitle("Error");
                exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                exceptionDialog.setContentText(ef.getMessage());
                exceptionDialog.show();
            }

            // Generic Exception
            catch (Exception e)
            {
                Dialog<Exception> exceptionDialog = new Dialog<>();
                exceptionDialog.setTitle("Error");
                exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                exceptionDialog.setContentText("An error has occured.");
                exceptionDialog.show();
            }

        }

        // Closing inputFile
        inputFile.close();

    }

    // Add Course Method
    public boolean addCourse(Course newCourse)
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
            try
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
            }

            // File Not Found Exception
            catch (FileNotFoundException fnfe)
            {
                Dialog<Exception> fileNotFoundDialog = new Dialog<>();
                fileNotFoundDialog.setTitle("Error");
                fileNotFoundDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                fileNotFoundDialog.setContentText("File Not Found.");
                fileNotFoundDialog.show();
            }

            // Generic Exception
            catch (Exception e)
            {
                Dialog<Exception> genericExceptionDialog = new Dialog<>();
                genericExceptionDialog.setTitle("Error!");
                genericExceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                genericExceptionDialog.setContentText("An Error Occured");
                genericExceptionDialog.show();
            }


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
    public boolean updateCourse(Course updatedCourse)
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
                    try
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

                    // File Not Found Exception
                    catch (FileNotFoundException fnfe)
                    {
                        Dialog<Exception> fileNotFoundDialog = new Dialog<>();
                        fileNotFoundDialog.setTitle("Error");
                        fileNotFoundDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        fileNotFoundDialog.setContentText("File Not Found.");
                        fileNotFoundDialog.show();
                    }

                    // Generic Exception
                    catch (Exception e)
                    {
                        Dialog<Exception> genericExceptionDialog = new Dialog<>();
                        genericExceptionDialog.setTitle("Error");
                        genericExceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        genericExceptionDialog.setContentText("An Error Occured");
                        genericExceptionDialog.show();
                    }
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

                try
                {
                    // Course Object Creation - To Return
                    Course returnCourse = new Course(returnID, returnName, returnDescription);

                    // Return Course Object
                    return returnCourse;
                }

                // Invalid ID Exception
                catch (InvalidID iid)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText(iid.getMessage());
                    exceptionDialog.show();
                }

                // Empty Field Exception
                catch (EmptyField ef)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText(ef.getMessage());
                    exceptionDialog.show();
                }

                // Generic Exception
                catch (Exception e)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("An error has occured.");
                    exceptionDialog.show();
                }

            }

        }

        // If Course ID Doesn't Exist, Returns "null"
        return null;

    }

}

class Course
{
    // Course Class Variables
    int courseIDNumber;
    String courseName, courseDescription;

    // Course Contructor
    Course (int idnumber, String name, String description) throws InvalidID, EmptyField
    {
        if (idnumber < 0)
        {
            throw new InvalidID();
        }

        if (name.equals(""))
        {
            throw new EmptyField();
        }

        if (description.equals(""))
        {
            throw new EmptyField();
        }

        // Initializing Variables
        courseIDNumber = idnumber;
        courseName = name;
        courseDescription = description;
    }

}

// Enrollment File Manager Class
class EnrollmentFileManager
{
    // Enrollment File Manager Variables
    private Scanner inputFile;
    private String informationLine;

    // Creating Enrollment Object ArrayList
    public ArrayList<Enrollment> enrollmentArray = new ArrayList<Enrollment>();

    // Constructor - Reads Through Enrollment File
    public EnrollmentFileManager (String enrollmentFile)
    {
        try
        {
            // Opens Enrollment File and Creates New Scanner Object To Read Through File
            File openFile = new File(enrollmentFile);
            inputFile = new Scanner(openFile);
        }

        // File Not Found Exception
        catch (FileNotFoundException fnfe)
        {
            Dialog<Exception> fileNotFoundDialog = new Dialog<>();
            fileNotFoundDialog.setTitle("Error");
            fileNotFoundDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            fileNotFoundDialog.setContentText("File Not Found.");
            fileNotFoundDialog.show();
        }

        // Generic Exception
        catch (Exception e)
        {
            Dialog<Exception> genericExceptionDialog = new Dialog<>();
            genericExceptionDialog.setTitle("Error");
            genericExceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            genericExceptionDialog.setContentText("An Error Occured");
            genericExceptionDialog.show();
        }

        while (inputFile.hasNext())
        {
            // Variables
            int inputStudentID, inputCourseID, convertedStudentID, convertedCourseID;
            String studentIDToConvert, inputStudentName, courseIDToConvert, inputCourseName, inputCourseDescription, inputSemester, inputYear, inputGrade;

            // Lines Are Read Into "informationline" Variable
            informationLine = inputFile.nextLine();

            // Creates List Of String and Splits Line using "," Delimiter
            String[] inputTokens = informationLine.split(",");

            // Convert Student ID String to Integer
            studentIDToConvert = inputTokens[0];
            convertedStudentID = Integer.parseInt(studentIDToConvert);

            // Convert Course ID String To Integer
            courseIDToConvert = inputTokens[2];
            convertedCourseID = Integer.parseInt(courseIDToConvert);


            // Collecting Of Enrollment Information From InputTokens
            inputStudentID = convertedStudentID;
            inputStudentName = inputTokens[1];
            inputCourseID = convertedCourseID;
            inputCourseName = inputTokens[3];
            inputCourseDescription = inputTokens[4];
            inputSemester = inputTokens[5];
            inputYear = inputTokens[6];
            inputGrade = inputTokens[7];

            try
            {
                // Creates New Enrollment Object With input Information
                Enrollment fileEnrollment = new Enrollment(inputStudentID, inputStudentName, inputCourseID, inputCourseName, inputCourseDescription, inputSemester, inputYear, inputGrade);

                // Add Enrollment Object Into ArrayList
                enrollmentArray.add(fileEnrollment);
            }

            // Invalid ID Exception
            catch (InvalidID iid)
            {
                Dialog<Exception> exceptionDialog = new Dialog<>();
                exceptionDialog.setTitle("Error");
                exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                exceptionDialog.setContentText(iid.getMessage());
                exceptionDialog.show();
            }

            // Empty Field Exception
            catch (EmptyField ef)
            {
                Dialog<Exception> exceptionDialog = new Dialog<>();
                exceptionDialog.setTitle("Error");
                exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                exceptionDialog.setContentText(ef.getMessage());
                exceptionDialog.show();
            }

            // Generic Exception
            catch (Exception e)
            {
                Dialog<Exception> exceptionDialog = new Dialog<>();
                exceptionDialog.setTitle("Error");
                exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                exceptionDialog.setContentText("An error has occured.");
                exceptionDialog.show();
            }

        }

        // Closing InputFile
        inputFile.close();

    }

    // Add Enrollment Method
    public boolean addEnrollment (Enrollment newEnrollment)
    {
        // addEnrollment Method Variables
        int newStudentID, newCourseID;
        String newEnrollmentSemester, newEnrollmentYear, newEnrollmentGrade;

        // Initializes New Enrollment ID with ID From Enrollment Object
        newStudentID = newEnrollment.studentIDNumber;
        newCourseID = newEnrollment.courseIDNumber;
        newEnrollmentSemester = newEnrollment.enrollmentSemester;
        newEnrollmentYear = newEnrollment.enrollmentYear;
        newEnrollmentGrade = newEnrollment.enrollmentGrade;

        // Sends Enrollment Arguments to getEnrollment Method
        getEnrollment(newStudentID, newCourseID, newEnrollmentSemester, newEnrollmentYear, newEnrollmentGrade);

        // If getEnrollment Method Returns "null", Enrollment Object Doesn't Exist - Add Enrollment To ArrayList
        if (getEnrollment(newStudentID, newCourseID, newEnrollmentSemester, newEnrollmentYear, newEnrollmentGrade) == null)
        {
            try
            {
                //Add To ArrayList
                enrollmentArray.add(newEnrollment);

                // Creates Printwriter Object
                PrintWriter outputFile = new PrintWriter("enrollment.txt");

                // OverWrites File Line By Line
                for (Enrollment filedEnrollment : enrollmentArray)
                {
                    outputFile.println(filedEnrollment.studentIDNumber + "," + filedEnrollment.studentName + "," +  filedEnrollment.courseIDNumber + "," + filedEnrollment.courseName + "," + filedEnrollment.courseDescription + "," + filedEnrollment.enrollmentSemester + "," + filedEnrollment.enrollmentYear + "," + filedEnrollment.enrollmentGrade);

                }

                // Close Output File
                outputFile.close();
            }

            // File Not Found Exception
            catch (FileNotFoundException fnfe)
            {
                Dialog<Exception> fileNotFoundDialog = new Dialog<>();
                fileNotFoundDialog.setTitle("Error");
                fileNotFoundDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                fileNotFoundDialog.setContentText("File Not Found.");
                fileNotFoundDialog.show();
            }

            // Generic Exception
            catch (Exception e)
            {
                Dialog<Exception> genericExceptionDialog = new Dialog<>();
                genericExceptionDialog.setTitle("Error");
                genericExceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                genericExceptionDialog.setContentText("An Error Occured");
                genericExceptionDialog.show();
            }

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
    public boolean updateEnrollment(Enrollment updatedEnrollment)
    {
        // Update Enrollment Variables
        int studentID, courseID;
        String updatedSemester, updatedYear, updatedGrade;


        // Initializes Updated Enrollment ID With ID From Enrollment Object
        studentID = updatedEnrollment.studentIDNumber;
        courseID = updatedEnrollment.courseIDNumber;
        updatedSemester =updatedEnrollment.enrollmentSemester;
        updatedYear = updatedEnrollment.enrollmentYear;
        updatedGrade = updatedEnrollment.enrollmentYear;

        // Sends Updated Enrollment ID To getEnrollment Method
        getEnrollment(studentID,courseID,updatedSemester,updatedYear,updatedGrade);

        //If getEnrollment Method Returns Enrollment Object, Enrolment Already Exists - Update Enrollment Object
        if (getEnrollment(studentID,courseID,updatedSemester,updatedYear,updatedGrade) != null)
        {
            // Gets Index For Position to Update Enrollment Object
            for (int index = 0; index < enrollmentArray.size(); index++)
            {
                // Gets The Enrollment Object Using Index
                Enrollment fileEnrollment = enrollmentArray.get(index);

                // Checks If Enrollment ID From File Is Equal To Input ID
                if (fileEnrollment.studentIDNumber == studentID)
                {
                    try
                    {
                        // Rewrites Updated Enrollment Object Into Enrollment Array
                        enrollmentArray.set(index, updatedEnrollment);

                        // Creates New Printwriter Object
                        PrintWriter outputFile = new PrintWriter("enrollment.txt");

                        // OverWrites Output To File
                        for (Enrollment filedEnrollment : enrollmentArray)
                        {
                            outputFile.println(filedEnrollment.studentIDNumber + "," + filedEnrollment.studentName + "," + filedEnrollment.courseIDNumber + "," + fileEnrollment.courseName + "," + filedEnrollment.courseDescription + "," + filedEnrollment.enrollmentSemester + "," + filedEnrollment.enrollmentYear + "," + filedEnrollment.enrollmentGrade);
                        }

                        // Closes Output File
                        outputFile.close();
                    }

                    // File Not Found Exception
                    catch (FileNotFoundException fnfe)
                    {
                        Dialog<Exception> fileNotFoundDialog = new Dialog<>();
                        fileNotFoundDialog.setTitle("Error");
                        fileNotFoundDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        fileNotFoundDialog.setContentText("File Not Found.");
                        fileNotFoundDialog.show();
                    }

                    // Generic Exception
                    catch (Exception e)
                    {
                        Dialog<Exception> genericExceptionDialog = new Dialog<>();
                        genericExceptionDialog.setTitle("Error");
                        genericExceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        genericExceptionDialog.setContentText("An Error Occured");
                        genericExceptionDialog.show();
                    }

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
    Enrollment getEnrollment(int sid , int cid, String semester, String year, String grade)
    {
        // Loop To Go Through Enrollment Object ArrayList
        for (Enrollment fileEnrollment : enrollmentArray)
        {
            // Checks If Enrollment ID From File Already Exists
            if ( (fileEnrollment.studentIDNumber == sid) && (fileEnrollment.courseIDNumber == cid) && (fileEnrollment.enrollmentSemester.equals(semester)) && (fileEnrollment.enrollmentYear.equals(year)))
            {
                // Variables To Return
                int returnSID, returnCID;
                String returnName, returnCourseName, returnCourseDesc, returnSemester, returnYear, returnGrade;

                // Variable Initialization - To Add To Enrollment Object
                returnSID = fileEnrollment.studentIDNumber;
                returnName = fileEnrollment.studentName;
                returnCID = fileEnrollment.courseIDNumber;
                returnCourseName = fileEnrollment.courseName;
                returnCourseDesc = fileEnrollment.courseDescription;
                returnSemester = fileEnrollment.enrollmentSemester;
                returnYear = fileEnrollment.enrollmentYear;
                returnGrade = fileEnrollment.enrollmentGrade;

                try
                {
                    // Enrollment Object Creation - To Return
                    Enrollment returnEnrollment = new Enrollment(returnSID, returnName, returnCID, returnCourseName, returnCourseDesc, returnSemester, returnYear, returnGrade);

                    // Returns Enrollment Object
                    return returnEnrollment;
                }

                // Invalid ID Exception
                catch (InvalidID iid)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText(iid.getMessage());
                    exceptionDialog.show();
                }

                // Empty Field Exception
                catch (EmptyField ef)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText(ef.getMessage());
                    exceptionDialog.show();
                }

                // Generic Exception
                catch (Exception e)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("An error has occured.");
                    exceptionDialog.show();
                }

            }

        }

        // If Enrollment ID Doesn't Exist, Returns "null"
        return null;
    }

    
    // OverLoaded getEnrollment Method - For Displaying 
    Enrollment getEnrollment (int sid)
    {
        // Loop To Go Through Enrollment Object ArrayList
        for (Enrollment fileEnrollment : enrollmentArray)
        {
            // Checks If Enrollment ID From File Already Exists
            if (fileEnrollment.studentIDNumber == sid)
            {
                // Variables To Return
                int /*returnSID,*/ returnCID;
                String returnName, /*returnCourseName,*/ returnCourseDesc, returnSemester, returnYear, returnGrade;
                
                // Variable Initialization
                //returnSID = fileEnrollment.studentIDNumber;
                returnName = fileEnrollment.studentName;
                returnCID = fileEnrollment.courseIDNumber;
                //returnCourseName = fileEnrollment.courseName;
                returnCourseDesc = fileEnrollment.courseDescription;
                returnSemester = fileEnrollment.enrollmentSemester;
                returnYear = fileEnrollment.enrollmentYear;
                returnGrade = fileEnrollment.enrollmentGrade;

                System.out.println("Student Name: " + returnName);
                System.out.println("Course ID: " + returnCID);
                System.out.println("Course Description: " + returnCourseDesc);
                System.out.println("Semester: " + returnSemester);
                System.out.println("Year: " + returnYear);
                System.out.println("Grade: " + returnGrade);

                /*
                try
                {
                    // Enrollment Object Creation - To Return
                    Enrollment returnEnrollment = new Enrollment(returnSID, returnName, returnCID, returnCourseName, returnCourseDesc, returnSemester, returnYear, returnGrade);

                    // Return Enrollment Object
                    return returnEnrollment;
                }

                // Invalid ID Exception
                catch (InvalidID iid)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText(iid.getMessage());
                    exceptionDialog.show();
                }

                // Empty Field Exception
                catch (EmptyField ef)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText(ef.getMessage());
                    exceptionDialog.show();
                }

                // Generic Exception
                catch (Exception e)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("An error has occured.");
                    exceptionDialog.show();
                }

                 */


            }

        }

        // If Enrollment ID Doesn't Exist, Returns "null"
        return null;
    }
    
}

// Enrollment Class - For Creating Enrollment Objects
class Enrollment
{
    // Enrollment Class Variables
    int studentIDNumber, courseIDNumber;
    String studentName, courseName, courseDescription, enrollmentSemester, enrollmentYear, enrollmentGrade;

    // Enrollment Contructor
    Enrollment (int studentid, String studentname, int courseid, String coursename, String coursedescription, String semester, String year, String grade) throws InvalidID, EmptyField, InvalidYear
    {
        // Initializing Variables
        if (studentid <0)
        {
            throw new InvalidID();
        }

        if (studentname.equals(""))
        {
            throw new EmptyField();
        }

        if (courseid <0)
        {
            throw new InvalidID();
        }

        if (coursename.equals(""))
        {
            throw new EmptyField();
        }

        if (coursedescription.equals(""))
        {
            throw new EmptyField();
        }

        if (semester.equals(""))
        {
            throw new EmptyField();
        }

        if (year.equals(""))
        {
            throw new InvalidYear();
        }

        if (grade.equals(""))
        {
            throw new EmptyField();
        }

        // Variable Initialization
        studentIDNumber = studentid;
        studentName = studentname;
        courseIDNumber = courseid;
        courseName = coursename;
        courseDescription = coursedescription;
        enrollmentSemester = semester;
        enrollmentYear = year;
        enrollmentGrade = grade;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public Integer getCourseID()
    {
        return courseIDNumber;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public String getSemester()
    {
        return enrollmentSemester;
    }

    public String getYear()
    {
        return enrollmentYear;
    }

    public String getGrade()
    {
        return enrollmentGrade;
    }

}

// Invalid ID Exception Class
class InvalidID extends Exception
{
    public InvalidID()
    {
        super("Error, Input ID Is Invalid.");
    }
}

// Empty Field Exception Class
class EmptyField extends Exception
{
    public EmptyField()
    {
        super("Error, An Input Field Was Empty.");
    }
}

// Invalid Year Exception Class
class InvalidYear extends Exception
{
    public InvalidYear()
    {
        super("Error, Year Input Is Invalid");
    }
}

// Main Program
public class FinalProject extends Application {

    // Stage Dimension Constants
    final double WIDTH = 500, HEIGHT = 400;

    // Menu Bar Components
    private MenuBar menuBar;
    private Menu fileMenu;
    private Menu studentMenu;
    private Menu courseMenu;
    private Menu enrollmentMenu;
    private Menu reportsMenu;
    private MenuItem exitItem;
    private MenuItem addStudent;
    private MenuItem editStudent;
    private MenuItem viewStudent;
    private MenuItem viewCourse;
    private MenuItem addCourse;
    private MenuItem editCourse;
    private MenuItem addEnrollment;
    private MenuItem vieweditEnrollment;
    private MenuItem generateReport;


    // Labels
    private Label welcomeLabel;

   public static void main(String[] args) 
   {
      
      launch(args);

   }

   @Override
   public void start(Stage primaryStage)
   {
    

        // File Check & Creation Method
        fileChecks();

        // Creates Student File Manager Object To Read Through
        StudentFileManager studentFileManager = new StudentFileManager("student.txt");

        // Creates Course File Manager Object To Read Through
        CourseFileManager courseFileManager = new CourseFileManager("course.txt");

        // Creates Enrollment File Manager Object To Read through File
        EnrollmentFileManager enrollmentFileManager = new EnrollmentFileManager("enrollment.txt");


        // Welcome Label
        welcomeLabel = new Label("Welcome To University Enrollment!");

        // Create The Menu Bar
        menuBar = new MenuBar();

        // Creating File Menu
        buildFileMenu(primaryStage);

        // Creating Student Menu
        buildStudentMenu(primaryStage,studentFileManager);

        // Creating Course Menu
        buildCourseMenu(primaryStage,courseFileManager);

        // Creating Enrollment Menu
        buildEnrollmentMenu(primaryStage,studentFileManager,courseFileManager,enrollmentFileManager);

        // Creating Reports Menu
        buildReportsMenu(primaryStage,studentFileManager,courseFileManager,enrollmentFileManager);

        // Adding Menus To Menu Bar
        menuBar.getMenus().add(fileMenu);
        menuBar.getMenus().add(studentMenu);
        menuBar.getMenus().add(courseMenu);
        menuBar.getMenus().add(enrollmentMenu);
        menuBar.getMenus().add(reportsMenu);

        // Controls For Border Pane
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(welcomeLabel);

        // Create Scene & Display
        Scene welcomeScene = new Scene(borderPane, WIDTH, HEIGHT);
        primaryStage.setTitle("University Enrollment");
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
   }
   
   private void fileChecks()
   {
        // Student File Check and Creation
        File studentFile = new File("student.txt");
        if (!studentFile.exists())
        {
            try
            {
                PrintWriter outputFile = new PrintWriter("student.txt");
                outputFile.close();
            }

            // File Not Found Exception
            catch (FileNotFoundException fnfe)
            {
                Dialog<Exception> fileNotFoundDialog = new Dialog<>();
                fileNotFoundDialog.setTitle("Error");
                fileNotFoundDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                fileNotFoundDialog.setContentText("File Not Found.");
                fileNotFoundDialog.show();
            }

            // Generic Exception
            catch (Exception e)
            {
                Dialog<Exception> genericExceptionDialog = new Dialog<>();
                genericExceptionDialog.setTitle("Error");
                genericExceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                genericExceptionDialog.setContentText("An Error Occured");
                genericExceptionDialog.show();
            }

        }

        // Course File Check & Creation
        File courseFile = new File("course.txt");
        if (!courseFile.exists())
        {
            try
            {
                PrintWriter outputFile = new PrintWriter("course.txt");
                outputFile.close();
            }

            // File Not Found Exception
            catch (FileNotFoundException fnfe)
            {
                Dialog<Exception> fileNotFoundDialog = new Dialog<>();
                fileNotFoundDialog.setTitle("Error");
                fileNotFoundDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                fileNotFoundDialog.setContentText("File Not Found.");
                fileNotFoundDialog.show();
            }

            // Generic Exception
            catch (Exception e)
            {
                Dialog<Exception> genericExceptionDialog = new Dialog<>();
                genericExceptionDialog.setTitle("Error");
                genericExceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                genericExceptionDialog.setContentText("An Error Occured");
                genericExceptionDialog.show();
            }

        }

        // Enrollment File Check & Creation
        File  enrollmentFile = new File("enrollment.txt");
        if (!enrollmentFile.exists())
        {
            try
            {
                PrintWriter outputFile = new PrintWriter("enrollment.txt");
                outputFile.close();
            }

            // File Not Found Exception
            catch (FileNotFoundException fnfe)
            {
                Dialog<Exception> fileNotFoundDialog = new Dialog<>();
                fileNotFoundDialog.setTitle("Error");
                fileNotFoundDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                fileNotFoundDialog.setContentText("File Not Found.");
                fileNotFoundDialog.show();
            }

            // Generic Exception
            catch (Exception e)
            {
                Dialog<Exception> genericExceptionDialog = new Dialog<>();
                genericExceptionDialog.setTitle("Error");
                genericExceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                genericExceptionDialog.setContentText("An Error Occured");
                genericExceptionDialog.show();
            }

        }
   }

   private void buildFileMenu(Stage primaryStage)
   {
        fileMenu = new Menu("File");

        exitItem = new MenuItem("Exit");

        exitItem.setOnAction(exitEvent ->
        {
            primaryStage.close();
        });

        fileMenu.getItems().add(exitItem);
   }

   private void buildStudentMenu(Stage primaryStage,StudentFileManager studentFileManager)
   {
        // Create Student Menu Object
        studentMenu = new Menu("Student");

        // Creating Menu Items For Student Menu
        addStudent = new MenuItem("Add Student");
        editStudent = new MenuItem("Edit Student");
        viewStudent = new MenuItem("View Student");

        
        // Register Event Handlers
        addStudent.setOnAction(addStudentEvent ->
        {
            // Option Label - Add Student
            Label studentOptionAdd = new Label("New Student Information");

            // Add Student Labels
            Label addPromptID = new Label("Student ID");
            Label addPromptFirst = new Label("First Name");
            Label addPromptLast = new Label("Last Name");
            Label addPromptAddress = new Label("Address");
            Label addPromptCity = new Label("City");
            Label addPromptState = new Label("State");
            Label addPromptZip = new Label("Zip Code");

            // Add Student Text Fields
            TextField addTextID = new TextField();
            TextField addTextFirst = new TextField();
            TextField addTextLast = new TextField();
            TextField addTextAddress = new TextField();
            TextField addTextCity = new TextField();
            TextField addTextZip = new TextField();

            // Add Student Combo Box
            ComboBox<String> addComboStates = new ComboBox<>();
            addComboStates.getItems().addAll("AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VA","VT","WA","WV","WI","WY");

            // Add Student Buttons
            Button createStudentButton = new Button("Create Student");

            // Add Student Grid
            GridPane addStudentGrid = new GridPane();
            addStudentGrid.add(addPromptID,0,0);
            addStudentGrid.add(addTextID,1,0);
            addStudentGrid.add(addPromptFirst,0,1);
            addStudentGrid.add(addTextFirst,1,1);
            addStudentGrid.add(addPromptLast,0,2);
            addStudentGrid.add(addTextLast,1,2);
            addStudentGrid.add(addPromptAddress,0,3);
            addStudentGrid.add(addTextAddress,1,3);
            addStudentGrid.add(addPromptCity,0,4);
            addStudentGrid.add(addTextCity,1,4);
            addStudentGrid.add(addPromptState,2,4);
            addStudentGrid.add(addComboStates,3,4);
            addStudentGrid.add(addPromptZip,0,5);
            addStudentGrid.add(addTextZip,1,5);
            addStudentGrid.add(createStudentButton,1,6);

            // Setting Grid Alignment
            addStudentGrid.setHgap(10);
            addStudentGrid.setVgap(10);
            addStudentGrid.setPadding(new Insets(10,10,10,10));
            addStudentGrid.setAlignment(Pos.CENTER);

            // Setting Grid Constraints - Column 1
            ColumnConstraints column1 = new ColumnConstraints();
            addStudentGrid.getColumnConstraints().add(column1);
            column1.setPrefWidth(70);

            // Creating Vertical Box
            VBox addStudentVBox = new VBox(5);
            addStudentVBox.getChildren().setAll(studentOptionAdd,addStudentGrid);
            addStudentVBox.setAlignment(Pos.CENTER);

            // Controls For Border Pane
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(menuBar);
            borderPane.setCenter(addStudentVBox);
            
            // Create Scene & Display
            Scene addStudentScene = new Scene(borderPane, WIDTH, HEIGHT);
            primaryStage.setTitle("University Enrollment - Add Student");
            primaryStage.setScene(addStudentScene);
            primaryStage.show();

            createStudentButton.setOnAction(createStudentEvent ->
            {
                try
                {

                    // Variable Declaration
                    int studentIDNumber;
                    String studentFirstName, studentLastName, studentAddress, studentCity, studentState, studentZip;
                
                    // Initializing Variables With Text Field Input
                    studentIDNumber = Integer.parseInt(addTextID.getText());
                    studentFirstName = addTextFirst.getText();
                    studentLastName = addTextLast.getText();
                    studentAddress = addTextAddress.getText();
                    studentCity = addTextCity.getText();
                    studentState = addComboStates.getValue().toString();
                    studentZip = addTextZip.getText();

                    try
                    {
                        // Creates New Student Object - To Send To addStudent Method
                        Student newStudent = new Student(studentIDNumber, studentFirstName, studentLastName, studentAddress, studentCity, studentState, studentZip);

                        // Sends newStudent Object to addStudent Method - If Object Is New, Will Add To ArrayList and Save To File
                        if (studentFileManager.addStudent(newStudent) == true)
                        {
                            Dialog<Exception> addSuccess = new Dialog<>();
                            addSuccess.setTitle("Success!");
                            addSuccess.getDialogPane().getButtonTypes().add(ButtonType.OK);
                            addSuccess.setContentText("Student Has Been Added!");
                            addSuccess.show();

                        }

                        // If newStudent Object Is Not New, Displays Error Message
                        else
                        {
                            Dialog<Exception> addFail = new Dialog<>();
                            addFail.setTitle("Failure!");
                            addFail.getDialogPane().getButtonTypes().add(ButtonType.OK);
                            addFail.setContentText("Error, Student ID Already Exists.\nStudent Was Not Added.");
                            addFail.show();

                        }
                        
                    }

                    // Invalid ID Exception
                    catch (InvalidID iid)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText(iid.getMessage());
                        exceptionDialog.show();

                    }

                    // Empty Field Exception
                    catch (EmptyField ef)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText(ef.getMessage());
                        exceptionDialog.show();
                    }

                    catch (InputMismatchException ime)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText("Error, Invalid Input.");
                        exceptionDialog.show();
                    }

                    // Generic Exception
                    catch (Exception e)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Erro!");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText("An error has occured.");
                        exceptionDialog.show();
                    }
                }

                catch (NumberFormatException imex)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("Invalid Input For Student ID Field");
                    exceptionDialog.show();
                }

                catch (Exception e)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("An Error Has Occured.");
                    exceptionDialog.show();

                }
            });

        });

        editStudent.setOnAction(editStudentEvent ->
        {
            // Option Label - Edit Student
            Label studentOptionEdit = new Label("Edit Student Information");

            // Edit Labels
            Label editPromptID = new Label("Student ID");
            Label editPromptFirst = new Label("First Name");
            Label editPromptLast = new Label("Last Name");
            Label editPromptAddress = new Label("Address");
            Label editPromptCity = new Label("City");
            Label editPromptState = new Label("State");
            Label editPromptZip = new Label("Zip Code");

            // Edit Text Fields
            TextField editTextID = new TextField();
            TextField editTextFirst = new TextField();
            TextField editTextLast = new TextField();
            TextField editTextAddress = new TextField();
            TextField editTextCity = new TextField();
            TextField editTextZip = new TextField();

            // Edit Combo Box
            ComboBox<String> editComboStates = new ComboBox<>();
            editComboStates.getItems().addAll("AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VA","VT","WA","WV","WI","WY");

            // Edit Student Buttons
            Button editStudentSearch = new Button("Search");
            Button editStudentUpdate = new Button("Update Student");
            Button editStudentReset = new Button("Reset");

            // Edit Student Grid
            GridPane editStudentGrid = new GridPane();
            editStudentGrid.add(editPromptID,0,0);
            editStudentGrid.add(editTextID,1,0);
            editStudentGrid.add(editStudentSearch,2,0);
            editStudentGrid.add(editPromptFirst,0,1);
            editStudentGrid.add(editTextFirst,1,1);
            editStudentGrid.add(editPromptLast,0,2);
            editStudentGrid.add(editTextLast,1,2);
            editStudentGrid.add(editPromptAddress,0,3);
            editStudentGrid.add(editTextAddress,1,3);
            editStudentGrid.add(editPromptCity,0,4);
            editStudentGrid.add(editTextCity,1,4);
            editStudentGrid.add(editPromptState,0,5);
            editStudentGrid.add(editComboStates,1,5);
            editStudentGrid.add(editPromptZip,0,6);
            editStudentGrid.add(editTextZip,1,6);
            editStudentGrid.add(editStudentUpdate,1,7);
            editStudentGrid.add(editStudentReset,2,7);

            // Setting Grid Alignment
            editStudentGrid.setHgap(10);
            editStudentGrid.setVgap(10);
            editStudentGrid.setPadding(new Insets(10,10,10,10));
            editStudentGrid.setAlignment(Pos.CENTER);

            // Creating Vertical Box
            VBox editStudentVBox = new VBox(5);
            editStudentVBox.getChildren().setAll(studentOptionEdit,editStudentGrid);
            editStudentVBox.setAlignment(Pos.CENTER);

            // Setting Grid Constraints - Column 1
            ColumnConstraints column1 = new ColumnConstraints();
            editStudentGrid.getColumnConstraints().add(column1);
            column1.setPrefWidth(70);

            // Controls For Border Pane
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(menuBar);
            borderPane.setCenter(editStudentVBox);
            
            // Create Scene & Display
            Scene addStudentScene = new Scene(borderPane, WIDTH, HEIGHT);
            primaryStage.setTitle("University Enrollment - Edit Student");
            primaryStage.setScene(addStudentScene);
            primaryStage.show();

            editStudentSearch.setOnAction(editSearchEvent ->
            {
                try
                {

                    // Local Variable Declaration - For Displaying Student Object
                    int studentIDNumber;

                    // Initializing Variables With Text Field Input
                    studentIDNumber = Integer.parseInt(editTextID.getText());

                    // Variables
                    String  retrievedFirstName, retrievedLastName, retrievedAddress, retrievedCity, retrievedState, retrievedZip;
                    

                    // If Student Object Is Found, Display Student
                    if (studentFileManager.getStudent(studentIDNumber) != null)
                    {
                        
                        // Initializing Retreived Variables
                        retrievedFirstName = studentFileManager.getStudent(studentIDNumber).studentFirstName;
                        retrievedLastName = studentFileManager.getStudent(studentIDNumber).studentLastName;
                        retrievedAddress = studentFileManager.getStudent(studentIDNumber).studentAddress;
                        retrievedCity = studentFileManager.getStudent(studentIDNumber).studentCity;
                        retrievedState = studentFileManager.getStudent(studentIDNumber).studentState;
                        retrievedZip = studentFileManager.getStudent(studentIDNumber).studentZip;

                        // Output Retrieved Student Information
                        editTextID.setDisable(true);
                        editTextFirst.setText(retrievedFirstName);
                        editTextLast.setText(retrievedLastName);
                        editTextAddress.setText(retrievedAddress);
                        editTextCity.setText(retrievedCity);
                        editComboStates.setValue(retrievedState);
                        editTextZip.setText(retrievedZip);

                    }

                    // If Student Object Isn't Found - Display Error Message
                    else
                    {
                        Dialog<Exception> addSuccess = new Dialog<>();
                        addSuccess.setTitle("Error");
                        addSuccess.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        addSuccess.setContentText("Student ID doesn't exist in file");
                        addSuccess.show();
                    }
                }

                catch (NumberFormatException imex)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("Invalid Input For Student ID Field");
                    exceptionDialog.show();
                }

                catch (Exception e)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("An Error Has Occured.");
                    exceptionDialog.show();

                }
            });

            editStudentUpdate.setOnAction(updateStudentEvent ->
            {
                try
                {
                    // Local Variable Declaration - For Updating Student Object
                    int updatedIDNumber;
                    String updatedFirstName, updatedLastName, updatedAddress, updatedCity, updatedState, updatedZip;

                    updatedIDNumber = Integer.parseInt(editTextID.getText());
                    updatedFirstName = editTextFirst.getText();
                    updatedLastName = editTextLast.getText();
                    updatedAddress = editTextAddress.getText();
                    updatedCity = editTextCity.getText();
                    updatedState = editComboStates.getValue().toString();
                    updatedZip = editTextZip.getText();

                    try
                    {
                        // Creates updated Student Object - To Send To updateStudent Method
                        Student updatedStudent = new Student(updatedIDNumber, updatedFirstName, updatedLastName, updatedAddress, updatedCity, updatedState, updatedZip);

                        // Sends updatedStudent Object to updateStudent Method = If Object Exists, Student Object Will Be Updated.
                        if (studentFileManager.updateStudent(updatedStudent) == true)
                        {
                            Dialog<Exception> addSuccess = new Dialog<>();
                            addSuccess.setTitle("Success!");
                            addSuccess.getDialogPane().getButtonTypes().add(ButtonType.OK);
                            addSuccess.setContentText("Student Has Been Updated!");
                            addSuccess.show();
                        }

                        // If Student Object Doesn't Exist, Displays Error Message
                        else
                        {
                            Dialog<Exception> addFail = new Dialog<>();
                            addFail.setTitle("Error!");
                            addFail.getDialogPane().getButtonTypes().add(ButtonType.OK);
                            addFail.setContentText("Student Was Not Updated.");
                            addFail.show();
                        }
                    }

                    // Invalid ID Exception
                    catch (InvalidID iid)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText(iid.getMessage());
                        exceptionDialog.show();
                    }

                    // Empty Field Exception
                    catch (EmptyField ef)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText(ef.getMessage());
                        exceptionDialog.show();
                    }

                    catch (InputMismatchException ime)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText("Error, Invalid Input.");
                        exceptionDialog.show();
                    }

                    // Generic Exception
                    catch (Exception e)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Erro!");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText("An error has occured.");
                        exceptionDialog.show();

                    }

                }

                catch (NumberFormatException imex)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText("Invalid Input For Student ID Field");
                        exceptionDialog.show();
                    }

                    catch (Exception e)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText("An Error Has Occured.");
                        exceptionDialog.show();

                    }

            });

            editStudentReset.setOnAction((resetFieldsEvent ->
            {
                editTextID.setText("");
                editTextID.setDisable(false);
                editTextFirst.setText("");
                editTextLast.setText("");
                editTextAddress.setText("");
                editTextCity.setText("");
                editComboStates.setValue("");
                editTextZip.setText("");
            }));

        });

        viewStudent.setOnAction(viewStudentEvent ->
        {
            // Option Label - View Student
            Label studentOptionView = new Label("View Student Information");

            // View Labels
            Label viewPromptID = new Label("Student ID");
            Label viewPromptFirst = new Label("First Name");
            Label viewPromptLast = new Label("Last Name");
            Label viewPromptAddress = new Label("Address");
            Label viewPromptCity = new Label("City");
            Label viewPromptState = new Label("State");
            Label viewPromptZip = new Label("Zip Code");

            // View Text Fields
            TextField viewTextID = new TextField();
            TextField viewTextFirst = new TextField();
            TextField viewTextLast = new TextField();
            TextField viewTextAddress = new TextField();
            TextField viewTextCity = new TextField();
            TextField viewTextState = new TextField();
            viewTextState.setMaxWidth(60);
            TextField viewTextZip = new TextField();

            Button viewStudentSearch = new Button("Search");

            // View Student Grid
            GridPane viewStudentGrid = new GridPane();
            viewStudentGrid.add(viewPromptID, 0, 0);
            viewStudentGrid.add(viewTextID,1,0);
            viewStudentGrid.add(viewStudentSearch,2,0);
            viewStudentGrid.add(viewPromptFirst,0,1);
            viewStudentGrid.add(viewTextFirst,1,1);
            viewStudentGrid.add(viewPromptLast,0,2);
            viewStudentGrid.add(viewTextLast,1,2);
            viewStudentGrid.add(viewPromptAddress,0,3);
            viewStudentGrid.add(viewTextAddress,1,3);
            viewStudentGrid.add(viewPromptCity,0,4);
            viewStudentGrid.add(viewTextCity,1,4);
            viewStudentGrid.add(viewPromptState,0,5);
            viewStudentGrid.add(viewTextState,1,5);
            viewStudentGrid.add(viewPromptZip,0,6);
            viewStudentGrid.add(viewTextZip, 1, 6);

            // Setting Grid Alignment
            viewStudentGrid.setHgap(10);
            viewStudentGrid.setVgap(10);
            viewStudentGrid.setPadding(new Insets(10,10,10,10));
            viewStudentGrid.setAlignment(Pos.CENTER);

            // Creating Vertical Box
            VBox viewStudentVBox = new VBox(5);
            viewStudentVBox.getChildren().setAll(studentOptionView,viewStudentGrid);
            viewStudentVBox.setAlignment(Pos.CENTER);

            // Setting Grid Constraints - Column 1
            ColumnConstraints column1 = new ColumnConstraints();
            viewStudentGrid.getColumnConstraints().add(column1);
            column1.setPrefWidth(70);

            // Controls For Border Pane
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(menuBar);
            borderPane.setCenter(viewStudentVBox);
            
            // Create Scene & Display
            Scene addStudentScene = new Scene(borderPane, WIDTH, HEIGHT);
            primaryStage.setTitle("University Enrollment - View Student");
            primaryStage.setScene(addStudentScene);
            primaryStage.show();

            viewStudentSearch.setOnAction(viewSearchEvent ->
            {
                try
                {

                    // Local Variable Declaration - For Displaying Student Object
                    int studentIDNumber;

                    // Initializing Variables With Text Field Input
                    studentIDNumber = Integer.parseInt(viewTextID.getText());

                    // Variables
                    String  retrievedFirstName, retrievedLastName, retrievedAddress, retrievedCity, retrievedState, retrievedZip;
                    

                    // If Student Object Is Found, Display Student
                    if (studentFileManager.getStudent(studentIDNumber) != null)
                    {
                        
                        // Initializing Retreived Variables
                        retrievedFirstName = studentFileManager.getStudent(studentIDNumber).studentFirstName;
                        retrievedLastName = studentFileManager.getStudent(studentIDNumber).studentLastName;
                        retrievedAddress = studentFileManager.getStudent(studentIDNumber).studentAddress;
                        retrievedCity = studentFileManager.getStudent(studentIDNumber).studentCity;
                        retrievedState = studentFileManager.getStudent(studentIDNumber).studentState;
                        retrievedZip = studentFileManager.getStudent(studentIDNumber).studentZip;

                        // Output Retrieved Student Information

                        viewTextFirst.setText(retrievedFirstName);
                        viewTextFirst.setEditable(false);

                        viewTextLast.setText(retrievedLastName);
                        viewTextLast.setEditable(false);

                        viewTextAddress.setText(retrievedAddress);
                        viewTextAddress.setEditable(false);

                        viewTextCity.setText(retrievedCity);
                        viewTextCity.setEditable(false);

                        viewTextState.setText(retrievedState);
                        viewTextState.setEditable(false);

                        viewTextZip.setText(retrievedZip);
                        viewTextZip.setEditable(false);

                    }

                    // If Student Object Isn't Found - Display Error Message
                    else
                    {
                        Dialog<Exception> addSuccess = new Dialog<>();
                        addSuccess.setTitle("Error");
                        addSuccess.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        addSuccess.setContentText("Student ID doesn't exist in file");
                        addSuccess.show();
                    }
                }

                catch (NumberFormatException imex)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("Invalid Input For Student ID Field");
                    exceptionDialog.show();
                }

                catch (Exception e)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("An Error Has Occured.");
                    exceptionDialog.show();

                }

            });


        });

        // Add Menu Items To Student Menu
        studentMenu.getItems().add(addStudent);
        studentMenu.getItems().add(editStudent);
        studentMenu.getItems().add(viewStudent);
    }

   private void buildCourseMenu(Stage primaryStage,CourseFileManager courseFileManager)
   {
        // Create Course Menu Object
        courseMenu = new Menu("Course");

        // Creating Menu Items For Course Menu
        addCourse = new MenuItem("Add Course");
        editCourse = new MenuItem("Edit Course");
        viewCourse = new MenuItem("View Course");

        // Register Event Handlers

        addCourse.setOnAction(addCourseEvent ->
        {
            // Option Label - Add Course
            Label courseOptionAdd = new Label("New Course Information");

            // Add Course Labels
            Label addPromptCourseID = new Label("Course ID");
            Label addPromptCourseName = new Label("Course Name");
            Label addPromptCourseDescription = new Label("Course Description");

            // Add Course Text Fields
            TextField addTextCourseID = new TextField();
            TextField addTextCourseName = new TextField();
            TextField addTextCourseDescription = new TextField();

            // Add Course Buttons
            Button addCourseCreate = new Button("Create Course");

            // Add Course Grid
            GridPane addCourseGrid = new GridPane();
            addCourseGrid.add(addPromptCourseID, 0, 0);
            addCourseGrid.add(addTextCourseID, 1, 0);
            addCourseGrid.add(addPromptCourseName, 0, 1);
            addCourseGrid.add(addTextCourseName, 1, 1);
            addCourseGrid.add(addPromptCourseDescription, 0, 2);
            addCourseGrid.add(addTextCourseDescription, 1, 2);
            addCourseGrid.add(addCourseCreate, 1, 3);

            // Setting Grid Alignment
            addCourseGrid.setHgap(10);
            addCourseGrid.setVgap(10);
            addCourseGrid.setPadding(new Insets(10,10,10,10));
            addCourseGrid.setAlignment(Pos.CENTER);

            // Setting Grid Constraints - Column 1
            ColumnConstraints column1 = new ColumnConstraints();
            addCourseGrid.getColumnConstraints().add(column1);
            column1.setPrefWidth(110);

            // Creating Vertical Box
            VBox addCourseVBox = new VBox(5);
            addCourseVBox.getChildren().setAll(courseOptionAdd,addCourseGrid);
            addCourseVBox.setAlignment(Pos.CENTER);

            // Controls For Border Pane
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(menuBar);
            borderPane.setCenter(addCourseVBox);
            
            // Create Scene & Display
            Scene addCourseSCene = new Scene(borderPane, WIDTH, HEIGHT);
            primaryStage.setTitle("University Enrollment - Add Course");
            primaryStage.setScene(addCourseSCene);
            primaryStage.show();

            addCourseCreate.setOnAction(createStudentEvent ->
            {
                try
                {
                    //Local Variable Declaration - For Creating New Course Object
                    int courseIDNumber;
                    String courseName, courseDescription;

                    // Initiating Variables With User Input
                    courseIDNumber = Integer.parseInt(addTextCourseID.getText());
                    courseName = addTextCourseName.getText();
                    courseDescription = addTextCourseDescription.getText();

                    try
                    {
                        // Creates New Course Object - To Send To addCourse Method
                        Course newCourse = new Course(courseIDNumber, courseName, courseDescription);

                        // Sends newCourse Object to addCourse Method - If Object Is New, Will Add To ArrayList and Save To File
                        if (courseFileManager.addCourse(newCourse) == true)
                        {
                            Dialog<Exception> addSuccess = new Dialog<>();
                            addSuccess.setTitle("Success!");
                            addSuccess.getDialogPane().getButtonTypes().add(ButtonType.OK);
                            addSuccess.setContentText("Course Has Been Added!");
                            addSuccess.show();
                        }

                        // If newCourse Object Is Not New, Displays Error Message
                        else 
                        {
                            Dialog<Exception> addFail = new Dialog<>();
                            addFail.setTitle("Failure!");
                            addFail.getDialogPane().getButtonTypes().add(ButtonType.OK);
                            addFail.setContentText("Error, Course Already Exists.\nCourse Was Not Added.");
                            addFail.show();
                        }
                    }

                    // Invalid ID Exception
                    catch (InvalidID iid)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText(iid.getMessage());
                        exceptionDialog.show();
                    }

                    // Empty Field Exception
                    catch (EmptyField ef)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText(ef.getMessage());
                        exceptionDialog.show();
                    }

                    catch (InputMismatchException ime)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText("Error, Invalid Input.");
                        exceptionDialog.show();

                    }

                    // Generic Exception
                    catch (Exception e)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Erro!");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText("An error has occured.");
                        exceptionDialog.show();

                    }
                }

                catch (NumberFormatException imex)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("Invalid Input For Course ID Field");
                    exceptionDialog.show();
                }

                catch (Exception e)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("An Error Has Occured.");
                    exceptionDialog.show();

                }
                
            });

        });

        editCourse.setOnAction(editCourseEvent ->
        {
            // Option Label - Edit Course
            Label courseOptionEdit = new Label("Edit Course Information");

            // Edit Course Labels
            Label editPromptCourseID = new Label("Course ID");
            Label editPromptCourseName = new Label("Course Name");
            Label editPromptCourseDescription = new Label("Course Description");

            // Edit Course Text Fields
            TextField editTextCourseID = new TextField();
            TextField editTextCourseName = new TextField();
            TextField editTextCourseDescription = new TextField();

            // Edit Course Buttons
            Button editCourseSearch = new Button("Search");
            Button editCourseUpdate = new Button("Update");
            Button editCourseReset = new Button("Reset");

            // Edit Course Grid
            GridPane editCourseGrid = new GridPane();
            editCourseGrid.add(editPromptCourseID, 0, 0);
            editCourseGrid.add(editTextCourseID, 1, 0);
            editCourseGrid.add(editCourseSearch,2,0);
            editCourseGrid.add(editPromptCourseName, 0, 1);
            editCourseGrid.add(editTextCourseName, 1, 1);
            editCourseGrid.add(editPromptCourseDescription, 0, 2);
            editCourseGrid.add(editTextCourseDescription, 1, 2);
            editCourseGrid.add(editCourseUpdate, 1, 3);
            editCourseGrid.add(editCourseReset,2,3);

            // Setting Grid Alignment
            editCourseGrid.setHgap(10);
            editCourseGrid.setVgap(10);
            editCourseGrid.setPadding(new Insets(10,10,10,10));
            editCourseGrid.setAlignment(Pos.CENTER);

            // Setting Grid Constraints - Column 1
            ColumnConstraints column1 = new ColumnConstraints();
            editCourseGrid.getColumnConstraints().add(column1);
            column1.setPrefWidth(110);

            // Creating Vertical Box
            VBox editCourseVBox = new VBox(5);
            editCourseVBox.getChildren().setAll(courseOptionEdit,editCourseGrid);
            editCourseVBox.setAlignment(Pos.CENTER);

            // Controls For Border Pane
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(menuBar);
            borderPane.setCenter(editCourseVBox);
            
            // Create Scene & Display
            Scene editCourseScene = new Scene(borderPane, WIDTH, HEIGHT);
            primaryStage.setTitle("University Enrollment - Edit Course");
            primaryStage.setScene(editCourseScene);
            primaryStage.show();

            editCourseSearch.setOnAction(editCourseSearchEvent ->
            {
                try
                {
                    // Local Variable Declaration - For Displaying Course Object
                    int courseIDNumber;

                    // Ask For ID of Course To Display
                    courseIDNumber = Integer.parseInt(editTextCourseID.getText());

                    // Variables
                    String retreivedCourseName, retreivedCourseDescription;

                    // If Course Object Is Found, Display Course
                    if (courseFileManager.getCourse(courseIDNumber) != null)
                    {
                        // Initializing Retreived Variables
                        retreivedCourseName = courseFileManager.getCourse(courseIDNumber).courseName;
                        retreivedCourseDescription = courseFileManager.getCourse(courseIDNumber).courseDescription;

                        editTextCourseID.setDisable(true);
                        editTextCourseName.setText(retreivedCourseName);
                        editTextCourseDescription.setText(retreivedCourseDescription);

                    }

                    // If Course Object Isn't Found - Display Error Message
                    else
                    {
                        Dialog<Exception> addSuccess = new Dialog<>();
                        addSuccess.setTitle("Error");
                        addSuccess.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        addSuccess.setContentText("Course ID doesn't exist in file");
                        addSuccess.show();
                    }
                }

                catch (NumberFormatException imex)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("Invalid Input For Student ID Field");
                    exceptionDialog.show();
                }

                catch (Exception e)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("An Error Has Occured.");
                    exceptionDialog.show();

                }
            });

            editCourseUpdate.setOnAction(updateCourseEvent ->
            {
                try
                {
                    // Local Variable Declaration - For Updating Course Object
                    int updatedCourseID;
                    String updatedCourseName, updatedCourseDescription;

                    updatedCourseID = Integer.parseInt(editTextCourseID.getText());
                    updatedCourseName = editTextCourseName.getText();
                    updatedCourseDescription = editTextCourseDescription.getText();

                    try
                    {
                        // Creates New Course Object - To Send To addCourse Method
                        Course updatedCourse = new Course(updatedCourseID, updatedCourseName, updatedCourseDescription);

                        // Sends newCourse Object to addCourse Method - If Object Is New, Will Add To ArrayList and Save To File
                        if (courseFileManager.updateCourse(updatedCourse) == true)
                        {
                            Dialog<Exception> addSuccess = new Dialog<>();
                            addSuccess.setTitle("Success!");
                            addSuccess.getDialogPane().getButtonTypes().add(ButtonType.OK);
                            addSuccess.setContentText("Course Has Been Updated!");
                            addSuccess.show();
                        }

                        // If newCourse Object Is Not New, Displays Error Message
                        else 
                        {
                            Dialog<Exception> addSuccess = new Dialog<>();
                            addSuccess.setTitle("Error");
                            addSuccess.getDialogPane().getButtonTypes().add(ButtonType.OK);
                            addSuccess.setContentText("Course ID doesn't exist in file");
                            addSuccess.show();
                        }
                    }

                    // Invalid ID Exception
                    catch (InvalidID iid)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText(iid.getMessage());
                        exceptionDialog.show();
                    }

                    // Empty Field Exception
                    catch (EmptyField ef)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText(ef.getMessage());
                        exceptionDialog.show();
                    }

                    catch (InputMismatchException ime)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText("Error, Invalid Input.");
                        exceptionDialog.show();

                    }

                    // Generic Exception
                    catch (Exception e)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Erro!");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText("An error has occured.");
                        exceptionDialog.show();

                    }
                }

                catch (NumberFormatException imex)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("Invalid Input For Student ID Field");
                    exceptionDialog.show();
                }

                catch (Exception e)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("An Error Has Occured.");
                    exceptionDialog.show();

                }

            });

            editCourseReset.setOnAction(resetFieldsEvent ->
            {
                editTextCourseID.setText("");
                editTextCourseID.setDisable(false);
                editTextCourseName.setText("");
                editTextCourseDescription.setText("");
            });

        });

        viewCourse.setOnAction(viewCourseEvent ->
        {
            // Option Label - View Course
            Label courseOptionView = new Label("View Course Information");

            // View Course Labels
            Label viewPromptCourseID = new Label("Course ID");
            Label viewPromptCourseName = new Label("Course Name");
            Label viewPromptCourseDescription = new Label("Course Description");

            // View Course Text Fields
            TextField viewTextCourseID = new TextField();
            TextField viewTextCourseName = new TextField();
            TextField viewTextCourseDescription = new TextField();

            // View Course Buttons
            Button viewCourseSearch = new Button("Search");

            // Edit Course Grid
            GridPane viewCourseGrid = new GridPane();
            viewCourseGrid.add(viewPromptCourseID, 0, 0);
            viewCourseGrid.add(viewTextCourseID, 1, 0);
            viewCourseGrid.add(viewCourseSearch,2,0);
            viewCourseGrid.add(viewPromptCourseName,0,1);
            viewCourseGrid.add(viewTextCourseName, 1, 1);
            viewCourseGrid.add(viewPromptCourseDescription, 0, 2);
            viewCourseGrid.add(viewTextCourseDescription, 1, 2);
            
            // Setting Grid Alignment
            viewCourseGrid.setHgap(10);
            viewCourseGrid.setVgap(10);
            viewCourseGrid.setPadding(new Insets(10,10,10,10));
            viewCourseGrid.setAlignment(Pos.CENTER);

            // Setting Grid Constraints - Column 1
            ColumnConstraints column1 = new ColumnConstraints();
            viewCourseGrid.getColumnConstraints().add(column1);
            column1.setPrefWidth(110);

            // Creating Vertical Box
            VBox viewCourseBox = new VBox(5);
            viewCourseBox.getChildren().setAll(courseOptionView,viewCourseGrid);
            viewCourseBox.setAlignment(Pos.CENTER);

            // Controls For Border Pane
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(menuBar);
            borderPane.setCenter(viewCourseBox);
            
            // Create Scene & Display
            Scene addCourseSCene = new Scene(borderPane, WIDTH, HEIGHT);
            primaryStage.setTitle("University Enrollment - View Course");
            primaryStage.setScene(addCourseSCene);
            primaryStage.show();

            viewCourseSearch.setOnAction(viewCourseSearchEvent ->
            {
                try
                {
                    // Local Variable Declaration - For Displaying Course Object
                    int courseIDNumber;

                    // Ask For ID of Course To Display
                    courseIDNumber = Integer.parseInt(viewTextCourseID.getText());

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

                        viewTextCourseName.setText(retreivedCourseName);
                        viewTextCourseName.setEditable(false);

                        viewTextCourseDescription.setText(retreivedCourseDescription);
                        viewTextCourseDescription.setEditable(false);

                    }

                    // If Course Object Isn't Found - Display Error Message
                    else
                    {
                        Dialog<Exception> addSuccess = new Dialog<>();
                        addSuccess.setTitle("Error");
                        addSuccess.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        addSuccess.setContentText("Course ID doesn't exist in file");
                        addSuccess.show();
                    }
                }

                catch (NumberFormatException imex)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("Invalid Input For Student ID Field");
                    exceptionDialog.show();
                }

                catch (Exception e)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("An Error Has Occured.");
                    exceptionDialog.show();

                }
            });

        });

        // Add Menu Items To Course Menu
        courseMenu.getItems().add(addCourse);
        courseMenu.getItems().add(editCourse);
        courseMenu.getItems().add(viewCourse);
   }

   private void buildEnrollmentMenu(Stage primaryStage, StudentFileManager studentFileManager, CourseFileManager courseFileManager, EnrollmentFileManager enrollmentFileManager)
   {
        // Create Course Menu Object
        enrollmentMenu = new Menu("Enrollment");

        // Creating Menu Items For Course Menu
        addEnrollment = new MenuItem("Add Enrollment");
        vieweditEnrollment = new MenuItem("View/Edit Enrollment");
        

        // Register Event Handlers
        addEnrollment.setOnAction(addEnrollmentEvent ->
        {
            // Option Label - Add Enrollment
            Label enrollmentOptionAdd = new Label("New Enrollment Information");

            // Add Enrollment Labels
            Label addEnrollmentSID = new Label("Student ID");
            Label addEnrollmentStudent = new Label("Student Name");
            Label addEnrollmentPromptCID = new Label("Course ID");
            Label addEnrollmentCName = new Label("Course Name");
            Label addEnrollmentCDesc = new Label("Course Description");
            Label addEnrollmentSemester = new Label("Semester");
            Label addEnrollmentYear = new Label("Year");
            Label addEnrollmentGrade = new Label("Grade");


            // Add Enrollment Text fields
            TextField enrollmentStudentID = new TextField();
            TextField enrollmentStudentName = new TextField();
            enrollmentStudentName.setEditable(false);
            TextField enrollmentCourseID = new TextField();
            TextField enrollmentCourseName = new TextField();
            enrollmentCourseName.setEditable(false);
            TextField enrollmentCourseDescription = new TextField();
            enrollmentCourseDescription.setEditable(false);

            // Add Enrollment Combo Boxes
            ComboBox<String> enrollmentSemester = new ComboBox<>();
            enrollmentSemester.getItems().addAll("Winter","Spring","Summer","Fall");

            ComboBox<String> enrollmentYear = new ComboBox<>();
            enrollmentYear.getItems().addAll("2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035","2036","2037","2038","2039","2040");

            ComboBox<String> enrollmentGrade = new ComboBox<>();
            enrollmentGrade.getItems().addAll("A","B","C","D","F");

            // Add Enrollment Buttons
            Button searchEnrollmentStudent = new Button("Find Student");
            Button searchEnrollmentCourse = new Button("Find Course");
            Button createEnrollment = new Button("Create Enrollment");

            // Add Enrollment Grid
            GridPane addEnrollmentGrid = new GridPane();
            addEnrollmentGrid.add(addEnrollmentSID, 0, 0);
            addEnrollmentGrid.add(enrollmentStudentID, 1, 0);
            addEnrollmentGrid.add(searchEnrollmentStudent, 2, 0);
            addEnrollmentGrid.add(addEnrollmentStudent, 0, 1);
            addEnrollmentGrid.add(enrollmentStudentName, 1, 1);
            addEnrollmentGrid.add(addEnrollmentPromptCID, 0, 2);
            addEnrollmentGrid.add(enrollmentCourseID, 1, 2);
            addEnrollmentGrid.add(searchEnrollmentCourse, 2, 2);
            addEnrollmentGrid.add(addEnrollmentCName, 0, 3);
            addEnrollmentGrid.add(enrollmentCourseName, 1, 3);

            addEnrollmentGrid.add(addEnrollmentCDesc,0,4);
            addEnrollmentGrid.add(enrollmentCourseDescription,1,4);

            addEnrollmentGrid.add(addEnrollmentSemester, 0, 5);
            addEnrollmentGrid.add(enrollmentSemester, 0, 6);
            addEnrollmentGrid.add(addEnrollmentYear, 1, 5);
            addEnrollmentGrid.add(enrollmentYear, 1, 6);
            addEnrollmentGrid.add(addEnrollmentGrade, 2, 5);
            addEnrollmentGrid.add(enrollmentGrade, 2, 6);
            addEnrollmentGrid.add(createEnrollment, 1, 7);

            // Setting Grid Alignment
            addEnrollmentGrid.setHgap(10);
            addEnrollmentGrid.setVgap(10);
            addEnrollmentGrid.setPadding(new Insets(10,10,10,10));
            addEnrollmentGrid.setAlignment(Pos.CENTER);

            // Setting Grid Constraints - Column 1
            ColumnConstraints column1 = new ColumnConstraints();
            addEnrollmentGrid.getColumnConstraints().add(column1);
            column1.setPrefWidth(110);

            // Creating Vertical Box
            VBox addEnrollmentVBox = new VBox(5);
            addEnrollmentVBox.getChildren().setAll(enrollmentOptionAdd,addEnrollmentGrid);
            addEnrollmentVBox.setAlignment(Pos.CENTER);

            // Controls For Border Pane
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(menuBar);
            borderPane.setCenter(addEnrollmentVBox);
            
            // Create Scene & Display
            Scene addCourseSCene = new Scene(borderPane, WIDTH, HEIGHT);
            primaryStage.setTitle("University Enrollment - Add Enrollment");
            primaryStage.setScene(addCourseSCene);
            primaryStage.show();

            searchEnrollmentStudent.setOnAction(searchStudentEvent ->
            {
                try
                {

                    // Local Variable Declaration - For Displaying Student Object
                    int studentIDNumber;

                    // Initializing Variables With Text Field Input
                    studentIDNumber = Integer.parseInt(enrollmentStudentID.getText());

                    // Variables
                    String  retrievedFirstName, retrievedLastName;
                    
                    // If Student Object Is Found, Display Student
                    if (studentFileManager.getStudent(studentIDNumber) != null)
                    {
                        
                        // Initializing Retreived Variables
                        retrievedFirstName = studentFileManager.getStudent(studentIDNumber).studentFirstName;
                        retrievedLastName = studentFileManager.getStudent(studentIDNumber).studentLastName;
                        

                        // Output Retrieved Student Information

                        enrollmentStudentName.setText(retrievedFirstName + " " + retrievedLastName);
                        enrollmentStudentName.setEditable(false);

                    }

                    // If Student Object Isn't Found - Display Error Message
                    else
                    {
                        Dialog<Exception> addSuccess = new Dialog<>();
                        addSuccess.setTitle("Error");
                        addSuccess.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        addSuccess.setContentText("Student ID doesn't exist in file");
                        addSuccess.show();
                    }
                }

                catch (NumberFormatException imex)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("Invalid Input For Student ID Field");
                    exceptionDialog.show();
                }

                catch (Exception e)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("An Error Has Occured.");
                    exceptionDialog.show();

                }

                
            });

            searchEnrollmentCourse.setOnAction(searchEnrollmentEvent ->
            {
                try
                    {

                        // Local Variable Declaration - For Displaying Course Object
                        int courseIDNumber;

                        // Ask For ID of Course To Display
                        courseIDNumber = Integer.parseInt(enrollmentCourseID.getText());

                        // If Course Object Is Found, Display Course
                        if (courseFileManager.getCourse(courseIDNumber) != null)
                        {
                            // Variables
                            String retreivedCourseName, retreivedCourseDescription;

                            // Initializing Retreived Variables
                            retreivedCourseName = courseFileManager.getCourse(courseIDNumber).courseName;
                            retreivedCourseDescription = courseFileManager.getCourse(courseIDNumber).courseDescription;

                            // Setting Retreived Data In Text Fields
                            enrollmentCourseName.setText(retreivedCourseName);
                            enrollmentCourseName.setEditable(false);

                            enrollmentCourseDescription.setText(retreivedCourseDescription);
                            enrollmentCourseDescription.setEditable(false);

                        }

                        // If Course Object Isn't Found - Display Error Message
                        else
                        {
                            Dialog<Exception> addSuccess = new Dialog<>();
                            addSuccess.setTitle("Error");
                            addSuccess.getDialogPane().getButtonTypes().add(ButtonType.OK);
                            addSuccess.setContentText("Course ID doesn't exist in file");
                            addSuccess.show();
                        }
                    }

                    catch (NumberFormatException imex)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText("Invalid Input For Student ID Field");
                        exceptionDialog.show();
                    }

                    catch (Exception e)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText("An Error Has Occured.");
                        exceptionDialog.show();

                    }
            });

            createEnrollment.setOnAction(createEnrollmentEvent -> 
            {

                try
                {
                    // Local Variable Declaration - For Creating New Enrollment Object
                    int studentID, courseID;
                    String studentName, courseName, courseDescription, enrollSemester, enrollYear, enrollGrade;

                    // Initiating Variables With User Input
                    studentID = Integer.parseInt(enrollmentStudentID.getText());
                    studentName = enrollmentStudentName.getText();
                    courseID = Integer.parseInt(enrollmentCourseID.getText());
                    courseName = enrollmentCourseName.getText();
                    courseDescription = enrollmentCourseDescription.getText();
                    enrollSemester = enrollmentSemester.getValue().toString();
                    enrollYear = enrollmentYear.getValue().toString();
                    enrollGrade = enrollmentGrade.getValue().toString();

                    try
                    {
                        Enrollment newEnrollment = new Enrollment(studentID, studentName, courseID, courseName, courseDescription, enrollSemester, enrollYear, enrollGrade);
                    
                        if (enrollmentFileManager.addEnrollment(newEnrollment) == true)
                        {
                            Dialog<Exception> addSuccess = new Dialog<>();
                            addSuccess.setTitle("Success!");
                            addSuccess.getDialogPane().getButtonTypes().add(ButtonType.OK);
                            addSuccess.setContentText("Enrollment Has Been Added!");
                            addSuccess.show();
                        }

                        else
                        {
                            Dialog<Exception> addFail = new Dialog<>();
                            addFail.setTitle("Failure!");
                            addFail.getDialogPane().getButtonTypes().add(ButtonType.OK);
                            addFail.setContentText("Error, Enrollment Already Exists.\nEnrollment Was Not Added.");
                            addFail.show();
                        }
                    }

                    // Invalid ID Exception
                    catch (InvalidID iid)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText(iid.getMessage());
                        exceptionDialog.show();

                    }

                    // Empty Field Exception
                    catch (EmptyField ef)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText(ef.getMessage());
                        exceptionDialog.show();
                    }

                    catch (InputMismatchException ime)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Error");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText("Error, Invalid Input.");
                        exceptionDialog.show();
                    }

                    // Generic Exception
                    catch (Exception e)
                    {
                        Dialog<Exception> exceptionDialog = new Dialog<>();
                        exceptionDialog.setTitle("Erro!");
                        exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        exceptionDialog.setContentText("An error has occured.");
                        exceptionDialog.show();
                    }

                }

                catch (NumberFormatException imex)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("Invalid Input For Student ID Field");
                    exceptionDialog.show();
                }

                catch (Exception e)
                {
                    Dialog<Exception> exceptionDialog = new Dialog<>();
                    exceptionDialog.setTitle("Error");
                    exceptionDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    exceptionDialog.setContentText("An Error Has Occured.");
                    exceptionDialog.show();

                }

            });


        });

        vieweditEnrollment.setOnAction(viewEnrollmentEvent ->
        {
            // Label Creation
            Label promptStudentID = new Label("Student ID");
            Label promptCourseID = new Label("Course ID");

            // Text Field Creation
            TextField textStudentID = new TextField();
            textStudentID.setPrefWidth(70);
            TextField textCourseID = new TextField();
            textCourseID.setPrefWidth(70);

            // Button Creation
            Button searchStudentButton = new Button("Search");
            Button dropCourseButton = new Button("Drop Class");

            // Grid Pane Creation
            GridPane veEnrollmentGrid = new GridPane();
            veEnrollmentGrid.add(promptStudentID, 0, 0);
            veEnrollmentGrid.add(textStudentID, 1, 0);
            veEnrollmentGrid.add(searchStudentButton, 2, 0);
            veEnrollmentGrid.add(promptCourseID, 3, 0);
            veEnrollmentGrid.add(textCourseID, 4, 0);
            veEnrollmentGrid.add(dropCourseButton, 5, 0);

            // Setting Grid Alignment
            veEnrollmentGrid.setHgap(10);
            veEnrollmentGrid.setVgap(10);
            veEnrollmentGrid.setPadding(new Insets(10,10,10,10));
            veEnrollmentGrid.setAlignment(Pos.CENTER);

            // Text Area Creation
            TextArea enrollmentText = new TextArea();
            enrollmentText.setEditable(false);

            // Table View Creation - Saved For Later Use -
            /*

            TableView<Enrollment> enrollmentTable;
            
            //Name Column
            TableColumn<Enrollment, String> studentnameColumn = new TableColumn<>("Name");
            studentnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
           
            // Course Name Column
            TableColumn<Enrollment, String> coursenameColumn = new TableColumn<>("Course Name");
            coursenameColumn.setCellValueFactory(new PropertyValueFactory<>("course name"));
            
            // Course ID Column
            TableColumn<Enrollment, Integer> courseidColumn = new TableColumn<>("Course ID");
            courseidColumn.setCellValueFactory(new PropertyValueFactory<>("course id"));

            // Semester Column
            TableColumn<Enrollment, String> semesterColumn = new TableColumn<>("Semester");
            semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));

            // Year Column
            TableColumn<Enrollment, String> yearColumn = new TableColumn<>("Year");
            yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

            // Grade Column
            TableColumn<Enrollment, String> gradeColumn = new TableColumn<>("Grade");
            gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

            enrollmentTable = new TableView<>();
            enrollmentTable.getColumns().add(studentnameColumn);
            enrollmentTable.getColumns().add(coursenameColumn);
            enrollmentTable.getColumns().add(courseidColumn);
            enrollmentTable.getColumns().add(semesterColumn);
            enrollmentTable.getColumns().add(yearColumn);
            enrollmentTable.getColumns().add(gradeColumn);

            */


            // Creating Vertical Box
            VBox veEnrollmentVbox = new VBox(5);
            veEnrollmentVbox.getChildren().setAll(veEnrollmentGrid,enrollmentText);
            veEnrollmentVbox.setAlignment(Pos.CENTER);

            // Controls For Border Pane
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(menuBar);
            borderPane.setCenter(veEnrollmentVbox);
            
            // Create Scene & Display
            Scene addCourseSCene = new Scene(borderPane, WIDTH, HEIGHT);
            primaryStage.setTitle("University Enrollment - View/Edit Enrollment");
            primaryStage.setScene(addCourseSCene);
            primaryStage.show();

            searchStudentButton.setOnAction(searchStudentEvent -> {

                int studentIDNumber;

                studentIDNumber = Integer.parseInt(textStudentID.getText());

                // If Enrollment Object Is Found, Display Course
                if (enrollmentFileManager.getEnrollment(studentIDNumber) != null)
                {
                    // Text Area Creation
                    // enrollmentText = new TextArea();
                    
                }
                
                
                
            });

        });


        // Add Menu Items To Course Menu
        enrollmentMenu.getItems().add(addEnrollment);
        enrollmentMenu.getItems().add(vieweditEnrollment);
   }

   private void buildReportsMenu(Stage primaryStage, StudentFileManager studentFileManager, CourseFileManager courseFileManager, EnrollmentFileManager enrollmentFileManager)
   {
        // Create Course Menu Object
        reportsMenu = new Menu("Reports");

        // Creating Reports Menu Items
        generateReport = new MenuItem("Generate Report");

        generateReport.setOnAction(generateReportEvent ->
        {
            // Label Creation
            Label promptCourseID = new Label("Course ID:");
            Label promptSemester = new Label("Semester:");
            Label promptYear = new Label("Year:");

            // Text Field Creation
            TextField textCourseID = new TextField();
            textCourseID.setPrefWidth(60);

            // Combo Box Creation
            ComboBox<String> semesterSelection = new ComboBox<>();
            semesterSelection.getItems().addAll("Winter","Spring","Summer","Fall");

            ComboBox<String> yearSelection = new ComboBox<>();
            yearSelection.getItems().addAll("2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035","2036","2037","2038","2039","2040");

            // Button Creation
            Button generateReportButton = new Button("Generate Report");

            // Text Area Creation
            TextArea reportText = new TextArea();
            reportText.setEditable(false);

            // Grid Pane Creation
            GridPane generateReportGrid = new GridPane();
            generateReportGrid.add(promptCourseID,0,0);
            generateReportGrid.add(promptSemester,1,0);
            generateReportGrid.add(promptYear,2,0);
            generateReportGrid.add(textCourseID,0,1);
            generateReportGrid.add(semesterSelection,1,1);
            generateReportGrid.add(yearSelection,2,1);
            generateReportGrid.add(generateReportButton,3,1);

            // Setting Grid Alignment
            generateReportGrid.setHgap(20);
            generateReportGrid.setVgap(10);
            generateReportGrid.setPadding(new Insets(10,10,10,10));
            generateReportGrid.setAlignment(Pos.CENTER);

            // Creating Vertical Box
            VBox reportsVBox = new VBox(5);
            reportsVBox.getChildren().setAll(generateReportGrid,reportText);
            reportsVBox.setAlignment(Pos.CENTER);

            // Controls For Border Pane
            BorderPane borderPane = new BorderPane();
            borderPane.setTop(menuBar);
            borderPane.setCenter(reportsVBox);
            
            // Create Scene & Display
            Scene addCourseSCene = new Scene(borderPane, WIDTH, HEIGHT);
            primaryStage.setTitle("University Enrollment - Generate Report");
            primaryStage.setScene(addCourseSCene);
            primaryStage.show();


        });

        // Add Menu Item To Reports Menu
        reportsMenu.getItems().add(generateReport);
   }
   
}
