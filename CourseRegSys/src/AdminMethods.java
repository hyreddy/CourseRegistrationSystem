/*
 * @Hrishikesh Yeluru
 * This interface has all the signatures of the methods implemented in the Admin class.
 */

import java.util.ArrayList;

public interface AdminMethods {

	void setUsername(String username);

	void setPassword(String password);

	void setFirstName(String name);

	void setLastName(String name);
	
	void createNewCourse(ArrayList arr, String name, String course_id, String instructor, String location, int secNum,
			int maxNumStud);

	void deleteCourse(ArrayList<Course> arr, String name, int secNum);

	int findCourse(ArrayList<Course> arr, String name, int secNum);

	int findCourse(ArrayList<Course> arr, String course_id);

	void editMaxNumStud(ArrayList<Course> arr, String name, int secNum, int maxNum);

	void editRegNumStud(ArrayList<Course> arr, String name, int secNum, int regNum);

	void editEnrolledNames(ArrayList<Course> arr, String name, int secNum, String firstName, String lastName);

	void editEnrolledStudents(Course arr, Student student);

	void editEnrolledStudents(ArrayList<Course> courses, String name, int secNum, ArrayList<Student> students,
			String firstname, String lastname);

	void editInstructor(ArrayList<Course> arr, String name, int secNum, String instructor);

	void editSecNum(ArrayList<Course> arr, String name, int secNum, int editSecNum);

	void editLocation(ArrayList<Course> arr, String name, int secNum, String location);

	void displayCourseInfo(ArrayList<Course> arr, String course_id);

	void registerStudent(ArrayList<Student> student, String username, String password, String first_name,
			String last_name);

	void displayAllCourses(ArrayList<Course> arr);

	ArrayList<Course> displayFullCourses(ArrayList<Course> arr);

	void writeFullToFile(ArrayList<Course> arr);

	void viewStuInCourse(ArrayList<Course> arr, String name, int section);

	int findStudent(ArrayList<Student> arr, String firstname, String lastname);

	void displayEnrolledCourses(ArrayList<Student> students, String firstname, String lastname);

	ArrayList<Course> sortNumReg(ArrayList<Course> arr);
	
	
}
