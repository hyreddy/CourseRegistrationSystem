/*
 * @Hrishikesh Yeluru
 * This interface has all the signatures of the methods implemented in the Student class.
 */

import java.util.ArrayList;

public interface StudentMethods {

	ArrayList getCourses();

	void setUsername(String username);

	void setPassword(String password);

	void setFirstName(String name);

	void setLastName(String name);

	void setCourses(Course course);

	void viewCourses(ArrayList<Course> arr);

	ArrayList<Course> displayNotFullCourses(ArrayList<Course> arr);

	int findStudent(ArrayList<Student> arr, String firstname, String lastname);

	int findCourse(ArrayList<Course> arr, String name, int secNum);

	void registerInCourse(ArrayList<Student> students, String firstname, String lastname, ArrayList<Course> courses,
			String name, int secNum);

	void withdrawCourse(ArrayList<Student> students, String firstname, String lastname, ArrayList<Course> courses,
			String name, int secNum);

	void displayEnrolledCourses(ArrayList<Student> students, String firstname, String lastname);

}
