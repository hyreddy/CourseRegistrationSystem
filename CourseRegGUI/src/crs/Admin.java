package crs;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Admin extends User implements AdminMethods, java.io.Serializable {
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	
	public Admin(String username, String password, String first_name, String last_name) {
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String getUsername() {
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
    public void createNewCourse(ArrayList arr, String name, String course_id, String instructor, String location,  int secNum,int maxNumStud) {
    	Course course = new Course(name, course_id, instructor, location, secNum,maxNumStud);
    	arr.add(course);
    }
    
	@Override
    public void deleteCourse(ArrayList<Course> arr, String name, int secNum) {
    	ArrayList<Integer> delete = new ArrayList<>();
    	int count = -1;
    	for(Course c: arr) {
    		count++;
    		if ((c.getCourseName()).matches(name)){
    			if((c.getSecNum()) == secNum) {
    				delete.add(count);
    			}
    		}
    	}
    	for(int i: delete) {
    		arr.remove(i);
    	}
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
    public int findCourse(ArrayList<Course> arr,String course_id) {
    	int index;
    	for(index = 0; index < arr.size(); index++) {
    		if ((arr.get(index).getCourseId()).matches(course_id)){
    				return index;
    		}
    	}
    	
    	return -1;
    }
    
    @Override
    public void editMaxNumStud(ArrayList<Course> arr, String name, int secNum, int maxNum) {
		int index = findCourse(arr, name, secNum);
		arr.get(index).setMaxNumStud(maxNum);
	}
	
    @Override
	public void editRegNumStud(ArrayList<Course> arr, String name, int secNum, int regNum) {
		int index = findCourse(arr, name, secNum);
		arr.get(index).setRegNumStud(regNum);
	}
	
	@Override
	public void editEnrolledNames(ArrayList<Course> arr, String name, int secNum, String firstName, String lastName) {
		int index = findCourse(arr, name, secNum);
		String fullName = firstName + " " + lastName;
		arr.get(index).setEnrolledNames(fullName);
		arr.get(index).incrementRegNumStud();
	}
	
	@Override
	public void editEnrolledStudents(Course arr, Student student) {
		arr.setEnrolledStudents(student);
	}
	
	@Override
	public void editEnrolledStudents(ArrayList<Course> courses, String name, int secNum, ArrayList<Student> students, String firstname, String lastname) {
		int indexOfCourse = findCourse(courses, name, secNum);
		int indexOfStudent = findStudent(students, firstname, lastname);
		String name1 = students.get(indexOfStudent).getFirstName() + " " + students.get(indexOfStudent).getLastName();
		if(courses.get(indexOfCourse).getRegNumStud() < courses.get(indexOfCourse).getMaxNumStud()) {
			courses.get(indexOfCourse).setEnrolledNames(name1);
			courses.get(indexOfCourse).setEnrolledStudents(students.get(indexOfStudent));
			courses.get(indexOfCourse).incrementRegNumStud();
			students.get(indexOfStudent).setCourses(courses.get(indexOfCourse));
			System.out.println("STUDENT ADDED ");
		}
		else {
			System.out.println("COURSE IS FULL");
		}
	}
	
	@Override
	public void editInstructor(ArrayList<Course> arr, String name, int secNum, String instructor) {
		int index = findCourse(arr, name, secNum);
		arr.get(index).setInstructor(instructor);
	}
	
	@Override
	public void editSecNum(ArrayList<Course> arr, String name, int secNum, int editSecNum) {
		int index = findCourse(arr, name, secNum);
		arr.get(index).setSecNum(editSecNum);
	}
	
	@Override
	public void editLocation(ArrayList<Course> arr, String name, int secNum, String location) {
		int index = findCourse(arr, name, secNum);
		arr.get(index).setLocation(location);
	}
	
	@Override
	public void displayCourseInfo (ArrayList<Course> arr, String course_id) {
    	for(Course c: arr) {
    		if ((c.getCourseId()).matches(course_id)){
    			System.out.println(c.toString());
    		}
    	}
	}
	
	@Override
	public void registerStudent(ArrayList<Student> student, String username, String password, String first_name, String last_name) {
		Student stud = new Student(username, password, first_name,last_name);
		student.add(stud);
	}
	
	@Override
	public void displayAllCourses (ArrayList<Course> arr) {
		for(Course c: arr) {
			System.out.println(c);
		}
	}
	
	@Override
	public ArrayList<Course> displayFullCourses (ArrayList<Course> arr) {
		ArrayList full = new ArrayList<>();
		for(Course c: arr) {
			if(c.getRegNumStud() >= c.getMaxNumStud()) {
				full.add(c);
			}
		}
		return full;
	}
	
	@Override
	public void writeFullToFile (ArrayList<Course> arr) {
		ArrayList<Course> full = displayFullCourses(arr);
		try {
			FileWriter myWriter = new FileWriter("coursesFull.txt");
			if(full.size() >= 1) {
			for(Course c: full) {
				myWriter.write(c.toString());
			}
			}
			else {
				myWriter.write("There are no full courses. ");
			}
			myWriter.close();
			System.out.println("Successfully wrote to coursesFull.txt");
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
				}
	}
	
	@Override
	public void viewStuInCourse(ArrayList<Course> arr, String name, int section) {
		ArrayList<Student> stu = new ArrayList<>();
		for(Course c: arr) {
			if((c.getCourseName().equals(name)) && (c.getSecNum() == section)) {
				stu = c.getEnrolledStudents();
			}
		}
		for(Student s: stu) {
			System.out.println(s);
		}
		if(stu.size() == 0)
			System.out.println("There are no students in this course ");
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
	public void displayEnrolledCourses(ArrayList<Student> students, String firstname, String lastname) {
		int indexOfStudent = findStudent(students, firstname, lastname);
		ArrayList<Course> enrolled = students.get(indexOfStudent).getCourses();
		
		for(Course c: enrolled) {
			System.out.println(c.toStudentString());
		}
		if(enrolled.size() == 0)
			System.out.println("Not Enrolled In Any Courses");
	}
	
	@Override
	public ArrayList<Course> sortNumReg(ArrayList<Course> arr) {
	    int n = arr.size();

	    for(int i = 0; i< n-1; i++) {
	        int min_idx=i;
	        
	        for(int j = i + 1; j< n; j++) {
	            if (((arr.get(j)).getRegNumStud()) < ((arr.get(min_idx)).getRegNumStud())){
	                min_idx=j;

	            }
	        }

	        if (min_idx != i) {
 
    	        Course temp = arr.get(min_idx);

    	        arr.remove(min_idx);
    	        arr.add(min_idx, arr.get(i));
    	        
    	        arr.add(i, temp);
    	        arr.remove(i+1);
	        }	       
	    }
	    return arr;
    }

}
