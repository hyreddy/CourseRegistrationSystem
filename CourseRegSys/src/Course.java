/*
 * @Hrishikesh Yeluru
 * This class creates the variable and methods used by the instances of a course.
 */

import java.util.ArrayList;

public class Course implements java.io.Serializable{
	private String course_name;
	private String course_id;
	private int maxNumStud;
	private int regNumStud;
	private ArrayList<String> enrolledNames;
	private ArrayList<Student> enrolledStudents;
	private String instructor;
	private int secNum;
	private String location;

	// Constructor
	public Course() {
		enrolledStudents = new ArrayList<Student>();
		enrolledNames = new ArrayList<String>();
		
	}
	
	// Constructor
	public Course(Course c) {
	    this.course_name = c.course_name;
		this.course_id = c.course_id;
		this.maxNumStud = c.maxNumStud;
		this.regNumStud = c.regNumStud;
		this.enrolledNames = c.enrolledNames;
		this.enrolledStudents = c.enrolledStudents;
		this.instructor = c.instructor;
		this.secNum = c.secNum;
		this.location = c.location;
	}
	
	// Constructor
	public Course(String course_name, String course_id, String instructor, String location,  int secNum,int maxNumStud) {
		this.course_name = course_name;
		this.course_id = course_id;
		this.instructor = instructor;
		this.location = location;
		this.maxNumStud = maxNumStud;
		this.secNum = secNum;
		enrolledStudents = new ArrayList<Student>();
		enrolledNames = new ArrayList<String>();
	}
	
	// Constructor
	public Course(String course_name, String course_id, int maxNumStud, int regNumStud, ArrayList<String> enrolledNames,
			String instructor, int secNum, String location) {
		this.course_name = course_name;
		this.course_id = course_id;
		this.maxNumStud = maxNumStud;
		this.regNumStud = regNumStud;
		this.enrolledNames = enrolledNames;
		this.instructor = instructor;
		this.secNum = secNum;
		this.location = location;
		enrolledStudents = new ArrayList<Student>();
	}

	// getter
	public String getCourseName() {
		return this.course_name;
	}
	
	// getter
	public String getCourseId() {
		return this.course_id;
	}
	
	// getter
	public int getMaxNumStud() {
		return this.maxNumStud;
	}
	
	// getter
	public int getRegNumStud() {
		return this.regNumStud;
	}
	
	// getter
	public ArrayList<String> getEnrolledNames() {
		return this.enrolledNames;
	}
	
	// getter
	public ArrayList<Student> getEnrolledStudents() {
		return this.enrolledStudents;
	}
	
	// getter
	public String getInstructor() {
		return this.instructor;
	}
	
	// getter
	public int getSecNum() {
		return this.secNum;
	}
	
	// getter
	public String getLocation() {
		return this.location;
	}
	
	// getter
	public void setMaxNumStud(int num) {
		this.maxNumStud = num;
	}
	
	// getter
	public void setRegNumStud(int num) {
		this.regNumStud = num;
	}
	
	// Increases the number of registered students by 1
	public void incrementRegNumStud() {
		this.regNumStud++;
	}
	
	// Decreases the number of registered students by 1
	public void decrementRegNumStud() {
		this.regNumStud--;
	}
	
	// Setter
	public void setEnrolledNames(String name) {
		this.enrolledNames.add(name);
	}
	
	// Setter
	public void setEnrolledStudents(Student student) {
		this.enrolledStudents.add(student);
	}
	
	// Setter
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	// Setter
	public void setSecNum(int num) {
		this.secNum = num;
	}
	
	// Setter
	public void setLocation(String location) {
		this.location = location;
	}
	
	// Regular toString
    public String toString() {
        return "Course: " + course_name + " CourseID: " + course_id + " Max#: " + maxNumStud + " Reg#: " + regNumStud + " Names: " + enrolledNames + " Instructor: " + instructor + " Section: " + secNum + " Location: " + location;
    }
    
    // toString used by student objects
    public String toStudentString() {
        return "Course: " + course_name + " CourseID: " + course_id  + " Instructor: " + instructor + " Section: " + secNum + " Location: " + location;
    }
}