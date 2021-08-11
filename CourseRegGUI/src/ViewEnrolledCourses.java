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

public class ViewEnrolledCourses extends JFrame {

	private JPanel contentPane;
	private JTable tableEnrolledCourses;
    private CRS crs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEnrolledCourses frame = new ViewEnrolledCourses(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
    public String getCourseList() {
        System.out.println(crs);
        ArrayList<Course> courses = crs.getCourses();
        System.out.println(courses);
        String rs = "";
        for(Course c : courses) {
            rs = rs + c.toStudentString() + "\n";
        }
        return rs;
    }

	/**
	 * Create the frame.
	 */
	public ViewEnrolledCourses(CRS crs) {
        this.crs = crs;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableEnrolledCourses = new JTable();
        JTextArea courses = new JTextArea(getCourseList());
		courses.setBounds(61, 55, 1100, 500);
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
