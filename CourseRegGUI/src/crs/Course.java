package crs;

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

	public Course() {
		enrolledStudents = new ArrayList<Student>();
		enrolledNames = new ArrayList<String>();
		
	}
	
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
    
    public void withdrawStudent(Student s) {
        enrolledStudents.remove(s);
        enrolledStudents.remove(s.toString());
    }
    
    public boolean addStudent(Student s) {
        if(this.maxNumStud == this.regNumStud) {
            return false;
        }
        else {
            setEnrolledStudents(s);
            setEnrolledNames(s.toString());
            return true;
        }
    }

	public String getCourseName() {
		return this.course_name;
	}
	
	public String getCourseId() {
		return this.course_id;
	}
	
	public int getMaxNumStud() {
		return this.maxNumStud;
	}
	
	public int getRegNumStud() {
		return this.regNumStud;
	}
	
	public ArrayList<String> getEnrolledNames() {
		return this.enrolledNames;
	}
	
	public ArrayList<Student> getEnrolledStudents() {
		return this.enrolledStudents;
	}
	
	public String getInstructor() {
		return this.instructor;
	}
	
	public int getSecNum() {
		return this.secNum;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void setMaxNumStud(int num) {
		this.maxNumStud = num;
	}
	
	public void setRegNumStud(int num) {
		this.regNumStud = num;
	}
	
	public void incrementRegNumStud() {
		this.regNumStud++;
	}
	
	public void decrementRegNumStud() {
		this.regNumStud--;
	}
	
	public void setEnrolledNames(String name) {
		this.enrolledNames.add(name);
	}
	
	public void setEnrolledStudents(Student student) {
		this.enrolledStudents.add(student);
	}
	
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public void setSecNum(int num) {
		this.secNum = num;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
    public String toString() {
        return "Course: " + course_name + " CourseID: " + course_id + " Max#: " + maxNumStud + " Reg#: " + regNumStud + " Names: " + enrolledNames + " Instructor: " + instructor + " Section: " + secNum + " Location: " + location;
    }
    
    public String toStudentString() {
        return "Course: " + course_name + " CourseID: " + course_id  + " Instructor: " + instructor + " Section: " + secNum + " Location: " + location;
    }
}