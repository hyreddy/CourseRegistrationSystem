package crs;

import java.util.ArrayList;

public class Student extends User implements StudentMethods, java.io.Serializable{
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	private ArrayList<Course> courses = new ArrayList<>();
	
	public Student() {
		
	}
	
	public Student(String username, String password, String first_name, String last_name) {
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public Student(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String getUsername() {;
		return this.username;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public String getFirstName() {
		return this.first_name;
	}
	
	@Override
	public String getLastName() {
		return this.last_name;
	}
	
	@Override
	public ArrayList getCourses() {
		return this.courses;
	}
	
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public void setFirstName(String name) {
		this.first_name = name;
	}
	
	@Override
	public void setLastName(String name) {
		this.last_name = name;
	}
	
	@Override
	public void setCourses(Course course) {
		this.courses.add(course);
	}
	
	@Override
	public void viewCourses (ArrayList<Course> arr) {
		for(Course c: arr) {
			System.out.println(c.toStudentString());
		}
    	
	}
	
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
			System.out.println("YOU ARE REGISTERED ");
		}
		else {
			System.out.println("COURSE IS FULL");
		}
	}
	
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
	
	@Override
	public String toString() {
        return first_name + " " + last_name;
    }
}