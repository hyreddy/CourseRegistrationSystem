/*
 * @Hrishikesh Yeluru
 * This class is the driver for the Course Registration System and uses instances of the Admin, Student, and
 * Course classes.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunCRS {
	public static void main(String[] args) {
		// The next two lines of code initialize the default users of the system
		Admin admin1 = new Admin("Admin", "Admin001");
		Student defaultStud = new Student();
		
		// The next two lines of code create the ArrayLists that will store the courses and students
		ArrayList<Course> courses = null;
		ArrayList<Student> students = null;
		
		// The next two lines of code create the variable that stores the first and last name of the student who uses the system
		String logFName = "";
		String logLName = "";
		
		// File that stores the serialized courses 
		String c_filename = "csr_courses.ser";
		
		// Checks to see if c_filename exists
		File c_file = new File(c_filename);
		boolean c_exists = c_file.exists();
		
		// File that stores the serialized students 
		String s_filename = "csr_students.ser";
		
		// Checks to see if s_filename exists
		File s_file = new File(s_filename);
		boolean s_exists = s_file.exists();
		
		// This if statement either deSerializes the files or initializes the course and student ArrayLists
		if (c_exists) {
			courses = deSerialize(c_filename);
		}
		else {
			courses = readCoursesFromCSV("MyUniversityCourses.csv");
		}
		
		if (s_exists) {
			students = deSerialize(s_filename);
		}
		else {
			students = new ArrayList<Student>();
			Student student = new Student("sysdefault", "pwd", "system", "default");
			students.add(student);
		}
		
		// Creates the scanner object
		Scanner input = new Scanner(System.in);
		
		// Stores what the user types
		String typeIn = null;
		
		// This loop prompts the user to pick a login method until satisfied
		do {
		System.out.print("Are you a Administrator or a Student (Type 'a' for administrator and 's' for student): ");
		typeIn = input.next();
		
		}while(((!typeIn.equals("s")) && (!typeIn.equals("a"))));
		
		// Stores the username and pwd
		String username = null;
		String pwd = null;
		
		// Creates the boolean to be satisfied if credentials are met
		boolean cred = false;
		do {
			System.out.print("Enter your Username: ");
			username = input.next();
			System.out.print("Enter your Password: ");
			pwd = input.next();
			
			// Checks against the only Admin in the system
			if(typeIn.equals("a")) {
				if(username.equals("Admin"))
					if(pwd.equals("Admin001"))
						cred = true;
			}
			
			// Checks the students credentials against all the Students in the ArrayList
			if(typeIn.equals("s")) {
				for(int i = 0; i< students.size(); i++) {
					if (students.get(i).getUsername().equals(username))
						if (students.get(i).getPassword().equals(pwd)) {
							cred = true;
							
							// Stores the names for future operations
							logFName = students.get(i).getFirstName();
							logLName = students.get(i).getLastName();
						}
							
				}
			}
			
			if(cred == false) {
				System.out.println("Invalid Credentials. Try Again. ");
			}
			
		}while(cred == false);
		
		// Creates variables that are used in the loop to control flow
		String toDoA = "s";
		String toDoS = "c";
		
		// Boolean variable that keeps the loop going until satisfied
		boolean menu = false;
		do {
		// Loop is asking user to pick between course management and reports
		if(typeIn.equals("a")) {
			System.out.print("Course Management or Reports (Type 'c' for Course Management and 'r' for Reports): ");
			toDoA = input.next();
			if(toDoA.equals("c")) {
				menu = true;
			}
			else if(toDoA.equals("r")) {
				menu = true;
			}
			else {
				System.out.println("Invalid Action. Try Again");
			}
		}
		else if(typeIn.equals("s")) {
			menu = true;
		}
		}while(menu == false);
		
		// courMan is the int that picks menu options
		int courMan = 0;
		do {
			// If an r was typed in the previous loop it will skip this loop
			if(toDoA.equals("r")) {
				courMan = 6;
			}
			// This lets the user pick what they want to do
			else if(toDoA.equals("c")) {
				System.out.println();
				System.out.println("Course Management (Type the number that is next to your desired action): ");
				System.out.println("1.Create a new course ");
				System.out.println("2.Delete a course ");
				System.out.println("3.Edit a course ");
				System.out.println("4.Display information for a given course ");
				System.out.println("5.Register a student ");
				System.out.println("6.Exit ");
				
				courMan = input.nextInt();
				
				switch (courMan) {
				case 1:
					// Variables to create a course
					String name1;
					String course_id1;
					String instructor1;
					String location1;
					int secNum1;
					int maxNum1;
					
					input.nextLine();
					System.out.print("What is the name of this course: ");
					name1 = input.nextLine();
					
					System.out.print("What is the course ID: ");
					course_id1 = input.nextLine();
					
					System.out.print("What is the Instructor Name: ");
					instructor1 = input.nextLine();
					
					System.out.print("What is the location: ");
					location1 = input.nextLine();
					
					System.out.print("What is the Section Number: ");
					secNum1 = input.nextInt();
					
					System.out.print("What is the Maximum Number of Students: ");
					maxNum1 = input.nextInt();
					
					if(admin1.findCourse(courses, name1, secNum1) != -1) {
						System.out.println();
						System.out.println("THIS SECTION OF " + name1 + " ALREADY EXISTS ");
						break;
					}
					
					admin1.createNewCourse(courses, name1, course_id1, instructor1, location1, secNum1, maxNum1);
					System.out.println();
					System.out.println("COURSE ADDED ");
					serialize(courses, c_filename);
					break;
				case 2:
					// Variables to locate and delete the course
					String name2;
					int secNum2;
					
					input.nextLine();
					System.out.print("What is the name of the course: ");
					name2 = input.nextLine();
					
					System.out.print("What is the section number: ");
					secNum2 = input.nextInt();
					
					if(admin1.findCourse(courses, name2, secNum2) == -1) {
						System.out.println();
						System.out.println("COURSE DOES NOT EXIST ");
						break;
					}
					admin1.deleteCourse(courses, name2, secNum2);
					System.out.println("Course Deleted ");
					serialize(courses, c_filename);
					break;
				case 3:
					// Variables used to edit a course
					int edit3;
					String name3;
					int secNum3;
					int maxNum3;
					int regNum3;
					String instructor3;
					int secNum3a;
					String location3;
					String firstName3;
					String lastName3;
					
					input.nextLine();
					System.out.print("What is the name of the course you want to edit: ");
					name3 = input.nextLine();
					
					System.out.print("What is the section number: ");
					secNum3 = input.nextInt();
					
					if(admin1.findCourse(courses, name3, secNum3) == -1) {
						System.out.println();
						System.out.println("COURSE DOES NOT EXIST ");
						break;
					}
					
					System.out.println();
					System.out.println("What information do you want to edit: ");
					System.out.println("1.Maximum Number of Students ");
					System.out.println("2.Instructor ");
					System.out.println("3.Section Number ");
					System.out.println("4.Location ");
					
					edit3 = input.nextInt();
					
					// Loop that lets the user pick what entry to edit
					if(edit3 == 1) {
						System.out.print("What is the Maximum Number of Students: ");
						maxNum3 = input.nextInt();
						admin1.editMaxNumStud(courses,name3, secNum3, maxNum3);
					}

					else if(edit3 == 2) {
						input.nextLine();
						System.out.print("Who is the Instructor: ");
						instructor3 = input.nextLine();
						admin1.editInstructor(courses,name3, secNum3, instructor3);
					}
					
					else if(edit3 == 3) {
						System.out.print("What is the section number: ");
						secNum3a = input.nextInt();
						admin1.editSecNum(courses,name3, secNum3, secNum3a);
					}
					
					else if(edit3 == 4) {
						input.nextLine();
						System.out.print("Where is it located: ");
						location3 = input.nextLine();
						admin1.editLocation(courses,name3, secNum3, location3);
					}
					
					serialize(courses, c_filename);
					break;
				case 4:
					// Uses id that user prompts to display course info
					String course_id4;
					
					input.nextLine();
					System.out.print("What is the Course ID: ");
					course_id4 = input.nextLine();
					
					if(admin1.findCourse(courses, course_id4) == -1) {
						System.out.println();
						System.out.println("COURSE DOES NOT EXIST ");
					}
					
					admin1.displayCourseInfo(courses, course_id4);
					break;
				case 5:
					// Variables needed to create a student
					String firstname5;
					String lastname5;
					String username5;
					String pwd5;
					
					input.nextLine();
					System.out.print("What is the student's first name: ");
					firstname5 = input.nextLine();
					
					System.out.print("What is the student's last name: ");
					lastname5 = input.nextLine();
					
					System.out.print("What is the username: ");
					username5 = input.next();
					
					System.out.print("What is the password: ");
					pwd5 = input.next();
					
					if(admin1.findStudent(students, firstname5, lastname5) != -1) {
						System.out.println();
						System.out.println("STUDENT ALREADY EXSISTS");
						break;
					}
					
					admin1.registerStudent(students, username5, pwd5, firstname5, lastname5);
					System.out.println("Student Registered ");
					serialize(students, s_filename);
					break;
				case 6:
					// Saves data and exits the system
					serialize(courses, c_filename);
					serialize(students, s_filename);
					System.exit(0);
				}
			}
			// This loop lets the user pick what they want to do
			else if(toDoS.equals("c")) {
				System.out.println();
				System.out.println("Course Management (Type the number that is next to your desired action): ");
				System.out.println("1.View all courses ");
				System.out.println("2.View all courses that are not full ");
				System.out.println("3.Register in a course ");
				System.out.println("4.Withdraw from a course ");
				System.out.println("5.View all registered courses ");
				System.out.println("6.Exit ");
				
				courMan = input.nextInt();
				
				// Based on courMan that is inputed the switch executes each menu option
				switch (courMan) {
				case 1:
					// Views Courses
					defaultStud.viewCourses(courses);
					break;
				case 2:
					// Views full courses
					for(Course c: defaultStud.displayNotFullCourses(courses)) {
						System.out.println(c.toStudentString());
					}
					break;
				case 3:
					// Variables used to register in a course
					String firstname3;
					String lastname3;
					String name3;
					int secNum3;

					
					input.nextLine();
					System.out.print("What is your first name: ");
					firstname3 = input.nextLine();
					
					System.out.print("What is your last name: ");
					lastname3 = input.nextLine();
					
					// Checks if the student is in the system
					if(defaultStud.findStudent(students, firstname3, lastname3) == -1) {
						System.out.println();
						System.out.println("STUDENT CANNOT BE FOUND ");
						break;
					}
					
					// Checks if the student is logged in with the name they are registering
					if(((firstname3.toLowerCase()).matches(logFName.toLowerCase()) && (lastname3.toLowerCase()).matches(logLName.toLowerCase())) == false) {
						System.out.println();
						System.out.println("CANNOT REGISTER A STUDENT YOU ARE NOT SIGNED IN WITH");
						break;
					}
					
					System.out.print("What is the name of the course you want to be registered in: ");
					name3 = input.nextLine();
					
					System.out.print("What is the section number: ");
					secNum3 = input.nextInt();
					
					// Checks if the course exists
					if(defaultStud.findCourse(courses, name3, secNum3) == -1) {
						System.out.println();
						System.out.println("COURSE CANNOT BE FOUND ");
						break;
					}
					
					// Prints error if you are registering for the same course
					if(students.get(defaultStud.findStudent(students, firstname3, lastname3)).getCourses().contains(courses.get(defaultStud.findCourse(courses, name3, secNum3)))) {
						System.out.println("YOU ARE ALREADY REGISTERED IN THIS COURSE");
						break;
					}
					
					// Registers student
					defaultStud.registerInCourse(students, firstname3, lastname3, courses, name3, secNum3);
					serialize(courses, c_filename);
					serialize(students, s_filename);
					break;
				case 4:
					// Variables needed to withdraw student
					String firstname4;
					String lastname4;
					String name4;
					int secNum4;

					
					input.nextLine();
					System.out.print("What is your first name: ");
					firstname4 = input.nextLine();
					
					System.out.print("What is your last name: ");
					lastname4 = input.nextLine();
					
					// Checks if the student is in the system
					if(defaultStud.findStudent(students, firstname4, lastname4) == -1) {
						System.out.println();
						System.out.println("STUDENT CANNOT BE FOUND ");
						break;
					}
					
					// Checks if the student is logged in with the name they are withdrawing
					if(((firstname4.toLowerCase()).matches(logFName.toLowerCase()) && (lastname4.toLowerCase()).matches(logLName.toLowerCase())) == false) {
						System.out.println();
						System.out.println("CANNOT WITHDRAW A STUDENT YOU ARE NOT SIGNED IN WITH");
						break;
					}
					
					System.out.print("What is the name of the course you want to be withdrawn from: ");
					name4 = input.nextLine();
					
					System.out.print("What is the section number: ");
					secNum4 = input.nextInt();
					
					// Checks if the course exists
					if(defaultStud.findCourse(courses, name4, secNum4) == -1) {
						System.out.println();
						System.out.println("COURSE CANNOT BE FOUND ");
						break;
					}
					
					int indexOfStudent = defaultStud.findStudent(students, logFName, logLName);
					ArrayList<Course> enrolled = students.get(indexOfStudent).getCourses();
					
					// Checks if student is registered in the course
					if(defaultStud.findCourse(enrolled, name4, secNum4) == -1) {
						System.out.println();
						System.out.println(logFName + " " + logLName + " IS NOT ENROLLED IN THIS CLASS ");
						break;
					}
					
					// Withdraws student from course
					defaultStud.withdrawCourse(students, firstname4, lastname4, courses, name4, secNum4);
					System.out.println();
					System.out.println("You have withdrawn from " + name4);
					serialize(courses, c_filename);
					serialize(students, s_filename);
					break;
				case 5:
					// Displays enrolled classes
					defaultStud.displayEnrolledCourses(students, logFName, logLName);
					break;
				case 6:
					// Saves and exits
					serialize(courses, c_filename);
					serialize(students, s_filename);
					System.exit(0);
				}
			}

		}while(courMan != 6);
		
		int repoMan = 0;
		do {
				// Prompts user to pick action
				System.out.println();
				System.out.println("Reports (Type the number that is next to your desired action): ");
				System.out.println("1.View all courses ");
				System.out.println("2.View all courses that are FULL ");
				System.out.println("3.Write to a file the list of courses that are full ");
				System.out.println("4.View the names of the students that are registered in a specific course ");
				System.out.println("5.View the list of courses that a given student is registered in "); 
				System.out.println("6.Sort courses by number of registered students  ");
				System.out.println("7.Exit ");
				
				courMan = input.nextInt();
				
				// User entered value for courMan toggles through switch statement
				switch (courMan) {
				case 1:
					// Displays all courses
					admin1.displayAllCourses(courses);
					break;
				case 2:
					// Prints full courses if there are any
					for(Course c: admin1.displayFullCourses(courses)) {
						System.out.println(c);
					}
					if(admin1.displayFullCourses(courses).size() == 0) {
						System.out.println("THERE ARE NO FULL COURSES ");
					}
					break;
				case 3:
					// Writes full courses to file
					admin1.writeFullToFile(courses);
					break;
				case 4:
					// Variables used for viewing students in a specific course
					String name4;
					int secNum4;
					
					input.nextLine();
					System.out.println("What is the Course name: ");
					name4 = input.nextLine();
					
					System.out.println("What is the Section Number: ");
					secNum4 = input.nextInt();
					admin1.viewStuInCourse(courses, name4, secNum4);
					break;
				case 5:
					// Variables used for displaying courses
					String firstname5;
					String lastname5;
					
					input.nextLine();
					System.out.print("What is the student's first name: ");
					firstname5 = input.nextLine();
					
					System.out.print("What is the student's last name: ");
					lastname5 = input.nextLine();
					
					// Checks if the student exists
					if(defaultStud.findStudent(students, firstname5, lastname5) == -1) {
						System.out.println();
						System.out.println("STUDENT CANNOT BE FOUND ");
						break;
					}
					
					// Displays courses
					admin1.displayEnrolledCourses(students, firstname5, lastname5);
					break;
				case 6:
					// Prints courses after sortin
					for(Course c: admin1.sortNumReg(courses)) {
						System.out.println(c);
					}
					break;
				case 7:
					// Saves and exits
					serialize(courses, c_filename);
					serialize(students, s_filename);
					System.exit(0);
				}
			
		}while(repoMan != 7);
	}

// This method saves the contents of an object in a file
public static void serialize(ArrayList obj, String filename) {
	try {    
        FileOutputStream file = new FileOutputStream(filename); 
        ObjectOutputStream out = new ObjectOutputStream(file);
        
        out.writeObject(obj);
          
        out.close(); 
        file.close(); 

    } 
      
    catch(IOException ex) { 
        System.out.println("IOException is caught"); 
    } 
	}

// This method retrieves the contents of a file and returns it in the form of an ArrayList
public static ArrayList deSerialize(String filename) {
	ArrayList arr = null;

	try{    
        FileInputStream file = new FileInputStream(filename); 
        ObjectInputStream in = new ObjectInputStream(file); 
      
        arr = (ArrayList) in.readObject(); 
        
        in.close(); 
        file.close(); 
    } 
      
    catch(IOException ex) 
    { 
        System.out.println("IOException is caught"); 
    } 
      
    catch(ClassNotFoundException ex) 
    { 
        System.out.println("ClassNotFoundException is caught"); 
    }
	
	return arr;
}

// This method reads the contents of a csv and returns a ArrayList with its contents
public static ArrayList readCoursesFromCSV(String fileName) {
	ArrayList<Course> courses = new ArrayList<>();
    Path pathToFile = Paths.get(fileName);
	
    try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
    	String headerLine = br.readLine();
    	String line = br.readLine();
        while (line != null) {
        	String[] attributes = line.split(",");
            Course course = createCourse(attributes);
            courses.add(course);
            line = br.readLine();
        }
    } catch (IOException ioe) {
        ioe.printStackTrace();
    }
    return courses;
}

// This method takes string data and creates a course object
public static Course createCourse(String[] metadata) {
    String course_name = metadata[0];
    String course_id = metadata[1];
    int maxNumStud = Integer.parseInt(metadata[2]);
    int regNumStud = Integer.parseInt(metadata[3]);
    ArrayList<String> enrolledNames = new ArrayList<String>();
    if(metadata[4].equals("NULL")) {
    	metadata[4] = "";
    }
    else {
    	enrolledNames.add(metadata[4]);
    }
    String instructor = metadata[5];
    int secNum = Integer.parseInt(metadata[6]);
    String location = metadata[7];
    return new Course(course_name, course_id, maxNumStud,regNumStud, enrolledNames, instructor, secNum, location);
}
}
