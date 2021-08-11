/*
 * @Hrishikesh Yeluru
 * This class implements the StudentMethods and extends the user class. It implements all of these methods and variables
 */

import java.util.ArrayList;

public class Student extends User implements StudentMethods, java.io.Serializable{
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	private ArrayList<Course> courses = new ArrayList<>();
	
	// Constructor
	public Student() {
		
	}
	
	// Constructor
	public Student(String username, String password, String first_name, String last_name) {
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	// Constructor
	public Student(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	// Getter
	@Override
	public String getUsername() {;
		return this.username;
	}
	
	// Getter
	@Override
	public String getPassword() {
		return this.password;
	}
	
	// Getter
	@Override
	public String getFirstName() {
		return this.first_name;
	}
	
	// Getter
	@Override
	public String getLastName() {
		return this.last_name;
	}
	
	// Getter
	@Override
	public ArrayList getCourses() {
		return this.courses;
	}
	
	// Setter
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	
	// Setter
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Setter
	@Override
	public void setFirstName(String name) {
		this.first_name = name;
	}
	
	// Setter
	@Override
	public void setLastName(String name) {
		this.last_name = name;
	}
	
	// Setter
	@Override
	public void setCourses(Course course) {
		this.courses.add(course);
	}
	
	// Method prints all courses to the console using the student view
	@Override
	public void viewCourses (ArrayList<Course> arr) {
		for(Course c: arr) {
			System.out.println(c.toStudentString());
		}
    	
	}
	
	// Method prints courses that are not full
	@Override
	public ArrayList<Course> displayNotFullCourses (ArrayList<Course> arr) {
		ArrayList<Course> notfull = new ArrayList<>();
		for(Course c: arr) {
			if(c.getRegNumStud() < c.getMaxNumStud()) {
				notfull.add(c);
			}
		}
		return notfull;
	}
	
	// Method return index of a student in the ArrayList
	@Override
	public int findStudent(ArrayList<Student> arr,String firstname, String lastname) {
		int index;
		String efirstname = firstname.toLowerCase();
		String elastname = lastname.toLowerCase();
    	for(index = 0; index < arr.size(); index++) {
    		if ((arr.get(index).getFirstName()).toLowerCase().matches(efirstname)){
    			if((arr.get(index).getLastName()).toLowerCase().matches(elastname)) {
    				return index;
    			}
    		}
    	}
    	
    	return -1;
	}
	
	// Method return index of a course in the ArrayList
	@Override
	public int findCourse(ArrayList<Course> arr,String name, int secNum) {
    	int index;
    	for(index = 0; index < arr.size(); index++) {
    		if ((arr.get(index).getCourseName()).matches(name)){
    			if((arr.get(index).getSecNum()) == secNum) {
    				return index;
    			}
    		}
    	}
    	
    	return -1;
    }
	
	// This method registers a student in a course
	@Override
	public void registerInCourse(ArrayList<Student> students, String firstname, String lastname, ArrayList<Course> courses, String name, int secNum) {
		int indexOfCourse = findCourse(courses, name, secNum);
		int indexOfStudent = findStudent(students, firstname, lastname);
		String name1 = students.get(indexOfStudent).getFirstName() + " " + students.get(indexOfStudent).getLastName();
		if(courses.get(indexOfCourse).getRegNumStud() < courses.get(indexOfCourse).getMaxNumStud()) {
			courses.get(indexOfCourse).setEnrolledNames(name1);
			courses.get(indexOfCourse).setEnrolledStudents(students.get(indexOfStudent));
			courses.get(indexOfCourse).incrementRegNumStud();
			students.get(indexOfStudent).setCourses(courses.get(indexOfCourse));
			System.out.println();
			System.out.println("YOU ARE REGISTERED ");
		}
		else {
			System.out.println();
			System.out.println("COURSE IS FULL");
		}
	}
	
	// This method withdraws a student in a course
	@Override
	public void withdrawCourse(ArrayList<Student> students, String firstname, String lastname, ArrayList<Course> courses, String name, int secNum) {
		int indexOfCourse = findCourse(courses, name, secNum);
		int indexOfStudent = findStudent(students, firstname, lastname);
		String name1 = firstname + " " + lastname;
	
		courses.get(indexOfCourse).getEnrolledNames().remove(name1);
		courses.get(indexOfCourse).getEnrolledStudents().remove(students.get(indexOfStudent));
		courses.get(indexOfCourse).decrementRegNumStud();
		students.get(indexOfStudent).getCourses().remove(courses.get(indexOfCourse));
	}
	
	// This method shows the courses a student is enrolled in
	@Override
	public void displayEnrolledCourses (ArrayList<Student> students, String firstname, String lastname) {
		int indexOfStudent = findStudent(students, firstname, lastname);
		ArrayList<Course> enrolled = students.get(indexOfStudent).getCourses();
		
		for(Course c: enrolled) {
			System.out.println(c.toStudentString());
		}
		if(enrolled.size() == 0)
			System.out.println("Not Enrolled In Any Courses");
	}
	
	// toString method to print student's name
	@Override
	public String toString() {
        return first_name + " " + last_name;
    }
}