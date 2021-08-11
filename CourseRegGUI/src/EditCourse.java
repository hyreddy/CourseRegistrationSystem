import crs.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.JButton;
import javax.swing.DefaultListSelectionModel;

public class EditCourse extends JFrame {

	private JPanel contentPane;
	private JTextField textMaxNumStud;
	private JTextField textCourseName;
	private JTextField textCourseID;
	private JTextField textInstuctor;
	private JTextField textSectionNumber;
	private JTextField textLocation;
    private CRS crs;
    
    public EditCourse(CRS crs) {
        
        this.crs = crs;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        
        JLabel lblAddACourse = new JLabel("Edit a Course");
		lblAddACourse.setFont(new Font("Futura", Font.PLAIN, 30));
		lblAddACourse.setBounds(60, 57, 351, 43);
		contentPane.add(lblAddACourse);

		
        
        ArrayList<Course> courses = crs.getCourses();
        String[] data = new String[courses.size()];
        for(int i = 0; i < courses.size(); i++) {
            data[i] = courses.get(i).getCourseName();
        }
        JList<String> myList = new JList<String>(data);
        myList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        myList.setBounds(60, 110, 500, 600);
        contentPane.add(myList);
        
        
        JButton btnEditACourse = new JButton("Edit a Course");
		btnEditACourse.setFont(new Font("Futura", Font.PLAIN, 13));
		btnEditACourse.setBounds(200, 730, 179, 29);
        btnEditACourse.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edit(myList.getSelectedIndex());
            }
        });
        
        contentPane.add(btnEditACourse);
        
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Futura", Font.PLAIN, 13));
		btnExit.setBounds(200, 780, 179, 29);
		contentPane.add(btnExit);
		btnExit.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
		});
        
        contentPane.add(btnExit);
    }
    
    public void edit(int courseId) {
        ArrayList<Course> courses = crs.getCourses();
        Course course = courses.get(courseId);
        JFrame frame = new JFrame("Edit " + course.getCourseName());
        
        JPanel contentPane = new JPanel();
        
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 800, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddACourse = new JLabel("Edit Course");
		lblAddACourse.setFont(new Font("Futura", Font.PLAIN, 30));
		lblAddACourse.setBounds(60, 57, 351, 43);
		contentPane.add(lblAddACourse);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setFont(new Font("Futura", Font.PLAIN, 20));
		lblCourseName.setBounds(157, 212, 125, 27);
		contentPane.add(lblCourseName);
		
		JLabel lblCourseId = new JLabel("Course ID");
		lblCourseId.setFont(new Font("Futura", Font.PLAIN, 20));
		lblCourseId.setBounds(157, 251, 125, 27);
		contentPane.add(lblCourseId);
		
		JLabel lblMaximumNumberOf = new JLabel("Maximum Number of Students");
		lblMaximumNumberOf.setFont(new Font("Futura", Font.PLAIN, 20));
		lblMaximumNumberOf.setBounds(157, 290, 332, 27);
        contentPane.add(lblMaximumNumberOf);
		
		JLabel lblInstructor = new JLabel("Instructor");
		lblInstructor.setFont(new Font("Futura", Font.PLAIN, 20));
		lblInstructor.setBounds(157, 329, 332, 27);
		contentPane.add(lblInstructor);
		
		JLabel lblSectionNumber = new JLabel("Section Number");
		lblSectionNumber.setFont(new Font("Futura", Font.PLAIN, 20));
		lblSectionNumber.setBounds(157, 368, 332, 27);
		contentPane.add(lblSectionNumber);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Futura", Font.PLAIN, 20));
		lblLocation.setBounds(157, 402, 125, 27);
		contentPane.add(lblLocation);
		
		JTextField textMaxNumStud = new JTextField();
		textMaxNumStud.setBounds(445, 290, 190, 29);
        textMaxNumStud.setText(course.getMaxNumStud() + "");
		contentPane.add(textMaxNumStud);
		textMaxNumStud.setColumns(10);
        
		
		JTextField textCourseName = new JTextField();
		textCourseName.setColumns(10);
		textCourseName.setBounds(445, 215, 190, 29);
        textCourseName.setText(course.getCourseName());
        textCourseName.setEditable(false);
		contentPane.add(textCourseName);
		
		JTextField textCourseID = new JTextField();
		textCourseID.setColumns(10);
		textCourseID.setBounds(445, 254, 190, 29);
        textCourseID.setText(course.getCourseId());
        textCourseID.setEditable(false);
		contentPane.add(textCourseID);
		
		JTextField textInstuctor = new JTextField();
		textInstuctor.setColumns(10);
		textInstuctor.setBounds(445, 332, 190, 29);
        textInstuctor.setText(course.getInstructor());
		contentPane.add(textInstuctor);
		
		JTextField textSectionNumber = new JTextField();
		textSectionNumber.setColumns(10);
		textSectionNumber.setBounds(445, 371, 190, 29);
        textSectionNumber.setText(course.getSecNum() + "");
		contentPane.add(textSectionNumber);
		
		JTextField textLocation = new JTextField();
		textLocation.setColumns(10);
		textLocation.setBounds(445, 405, 190, 29);
        textLocation.setText(course.getLocation());
		contentPane.add(textLocation);
		
		JButton editCourse = new JButton("Edit Course");
		editCourse.setBounds(330, 476, 117, 29);
		contentPane.add(editCourse);
        
        editCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String maxStudents = textMaxNumStud.getText();
                String courseName = textCourseName.getText();
                String courseID = textCourseID.getText();
                String instuctor = textInstuctor.getText();
                String sectionNumber = textSectionNumber.getText();
                String location = textLocation.getText();
                
                try {
                    course.setMaxNumStud(Integer.parseInt(maxStudents));
                }
                catch(NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Max Students a be an Integer", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    course.setSecNum(Integer.parseInt(sectionNumber));
                }
                catch(NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Max Students a be an Integer", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
                }
                course.setInstructor(instuctor);
                course.setLocation(location);
                JOptionPane.showMessageDialog(null, "Updated", "Fields Updated", JOptionPane.INFORMATION_MESSAGE);
                
            }
        });
        JButton exit = new JButton("Exit");
        exit.setBounds(330, 525, 117, 29);
        exit.setFont(new Font("Futura", Font.PLAIN, 13));
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        contentPane.add(exit);
        frame.setVisible(true);        
		
	}
    
	
	

}
