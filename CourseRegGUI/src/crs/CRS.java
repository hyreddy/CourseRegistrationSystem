package crs;

import java.util.*;
import java.io.*;

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
import java.util.Scanner;
import java.io.Serializable;


public class CRS implements Serializable{
    
    public static final String data = "crsdata.ser";

    
    ArrayList<User> users;
    ArrayList<Course> courses;
    User loggedIn;
    
    CRS() {
        this.users = new ArrayList<User>();
        loggedIn = null;
        readCoursesFromCSV("MyUniversityCourses.csv");
    }
    
    public boolean isLoggedIn() {
        return this.loggedIn != null;
    }
    
    public User getLoggedIn() {
        return this.loggedIn;
    }
    
    public void addUser(User u) {
        this.users.add(u);
    }
    
    public ArrayList<Course> getCourses() {
        return this.courses;
    }
    
    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        for(User u : users) {
            try {
                students.add((Student)u);
            }
            catch(ClassCastException cce) {
                continue;
            }
        }
        return students;
    }
    
    public User getUser(String username) {
        for(User u : this.users) {
            if(u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }
    
    public boolean validate(String username, String password) {
        User u = getUser(username);
        if(u == null) {
            return false;
        }
        return u.getPassword().equals(password);
    }
    
    public boolean login(String username, String password) {
        if(!validate(username, password)) {
            return false;
        }
        login(getUser(username));
        return true;
    }
    
    public void login(User u) {
        this.loggedIn = u;
    }
    
    public void updateRecords() {
        CRS.serialize(this);
    }

    public static void serialize(CRS crs) {
        try {
            File f = new File(data);
            FileOutputStream file = new FileOutputStream(f); 
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(crs);
            out.close(); 
            file.close(); 
        } 
        catch(Exception e) { 
            System.out.println("Some errror"); 
        } 
        }

    public static CRS deserialize() {
        CRS crs = new CRS();

        try{
            File f = new File(data);
            FileInputStream file = new FileInputStream(f); 
            ObjectInputStream in = new ObjectInputStream(file); 
            crs = (CRS)in.readObject(); 
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
        crs.addUser(new Admin("Admin", "Admin001", "John", "Doe"));
        //crs.readCoursesFromCSV("MyUniversityCourses.csv");
        return crs;
    }

    public void readCoursesFromCSV(String fileName) {
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
        this.courses = courses;
    }

    public Course createCourse(String[] metadata) {
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