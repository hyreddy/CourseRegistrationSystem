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

public class StudentEnrolledClasses extends JFrame {

	private JPanel contentPane;
	private JTextField textMaxNumStud;
	private JTextField textCourseName;
	private JTextField textCourseID;
	private JTextField textInstuctor;
	private JTextField textSectionNumber;
	private JTextField textLocation;
    private CRS crs;
    
    
    public StudentEnrolledClasses(CRS crs) {
        
        this.crs = crs;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        
        JLabel lblAddACourse = new JLabel("View Student's Classes");
		lblAddACourse.setFont(new Font("Futura", Font.PLAIN, 30));
		lblAddACourse.setBounds(60, 57, 351, 43);
		contentPane.add(lblAddACourse);

		
        
        ArrayList<Student> students = crs.getStudents();
        String[] data = new String[students.size()];
        for(int i = 0; i < students.size(); i++) {
            data[i] = students.get(i).toString();
        }
        JList<String> myList = new JList<String>(data);
        myList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        myList.setBounds(60, 200, 400, 400);
        contentPane.add(myList);
        
        
        JButton btnEditACourse = new JButton("View Registered Classes");
		btnEditACourse.setFont(new Font("Futura", Font.PLAIN, 13));
		btnEditACourse.setBounds(200, 650, 179, 29);
        btnEditACourse.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display(myList.getSelectedIndex());
            }
        });
        
        contentPane.add(btnEditACourse);
        
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Futura", Font.PLAIN, 13));
		btnExit.setBounds(200, 700, 179, 29);
		contentPane.add(btnExit);
		btnExit.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
		});
        
        contentPane.add(btnExit);
    }
    
    public void display(int courseId) {
        ArrayList<Student> students = crs.getStudents();
        Student student = students.get(courseId);
        JFrame frame = new JFrame(student.toString());
        ArrayList<Course> courses = student.getCourses();
        JPanel contentPane = new JPanel();
        
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 800, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddACourse = new JLabel("Student's Courses'");
		lblAddACourse.setFont(new Font("Futura", Font.PLAIN, 30));
		lblAddACourse.setBounds(60, 57, 351, 43);
		contentPane.add(lblAddACourse);
        
        String[] data = new String[courses.size()];
        for(int i = 0; i < courses.size(); i++) {
            data[i] = courses.get(i).getCourseName();
        }
        
        JList<String> myList = new JList<String>(data);
        myList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        myList.setBounds(60, 135, 400, 400);
        contentPane.add(myList);
		
        JButton exit = new JButton("Exit");
        exit.setBounds(330, 550, 117, 29);
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
