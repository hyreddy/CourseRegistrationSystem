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

public class EnrollCourse extends JFrame {

	private JPanel contentPane;
	private JTextField textMaxNumStud;
	private JTextField textCourseName;
	private JTextField textCourseID;
	private JTextField textInstuctor;
	private JTextField textSectionNumber;
	private JTextField textLocation;
    private CRS crs;
    
    public EnrollCourse(CRS crs) {
        
        this.crs = crs;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        
        JLabel lblAddACourse = new JLabel("Register in a Course");
		lblAddACourse.setFont(new Font("Futura", Font.PLAIN, 30));
		lblAddACourse.setBounds(60, 57, 351, 43);
		contentPane.add(lblAddACourse);

		Student s = (Student)crs.getLoggedIn();
        ArrayList<Course> courses = crs.getCourses();
        String[] data = new String[courses.size()];
        for(int i = 0; i < courses.size(); i++) {
            data[i] = courses.get(i).getCourseName();
        }
        JList<String> myList = new JList<String>(data);
        myList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        myList.setBounds(60, 110, 500, 530);
        contentPane.add(myList);
        
        
        JButton btnEditACourse = new JButton("Enroll In Course");
		btnEditACourse.setFont(new Font("Futura", Font.PLAIN, 13));
		btnEditACourse.setBounds(200, 650, 179, 29);
        btnEditACourse.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enroll(myList.getSelectedIndex());
                repaint();
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
    
    public boolean enroll(int val) {
        Student s = (Student)crs.getLoggedIn();
        Course c = crs.getCourses().get(val);
        ArrayList<Course> courses = s.getCourses();
        if(courses.contains(c)) {
            JOptionPane.showMessageDialog(null, "You are already enrolled in that class", "Invalid Choice", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        boolean reg = c.addStudent(s);
        if(reg) {
            c.incrementRegNumStud();
            courses.add(c);
            JOptionPane.showMessageDialog(null, "Successfully Enrolled", "Successful", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null, "That Course Is Full", "Full Course", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        }

}
