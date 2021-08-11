import crs.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.util.ArrayList;

public class ViewFullCourses extends JFrame {

	private JPanel contentPane;
	private JTable tableEnrolledCourses;
    private CRS crs;
	/**
	 * Launch the application.
	 */
    
    public String getCourseList() {
        System.out.println(crs);
        ArrayList<Course> courses = crs.getCourses();
        System.out.println(courses);
        String rs = "";
        for(Course c : courses) {
            if(c.getMaxNumStud() == c.getRegNumStud()) {
                rs = rs + c.getCourseName() + "\n";
            }
        }
        return rs;
    }

	/**
	 * Create the frame.
	 */
	public ViewFullCourses(CRS crs) {
        this.crs = crs;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableEnrolledCourses = new JTable();
        JTextArea courses = new JTextArea(getCourseList());
        courses.setEditable(false);
		courses.setBounds(61, 55, 900, 500);
		contentPane.add(courses);
        
        JButton exit = new JButton("Exit");
        exit.setBounds(600, 600, 120, 69);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        contentPane.add(exit);
	}
}
