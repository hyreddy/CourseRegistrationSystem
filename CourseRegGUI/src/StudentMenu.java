import crs.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentMenu extends JFrame {

	private JPanel contentPane;
    private CRS crs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMenu frame = new StudentMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
     
     1.View all courses
S
2.View all courses that are not full
3.Register in a course
4.Withdraw from a course
5.View all registered courses

	 */
	public StudentMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCourseMgmt = new JLabel("Course Management");
		lblCourseMgmt.setFont(new Font("Futura", Font.PLAIN, 30));
		lblCourseMgmt.setBounds(60, 57, 351, 43);
		contentPane.add(lblCourseMgmt);
		
		JButton btnCreateANewCourse = new JButton("View all courses");
		btnCreateANewCourse.setFont(new Font("Futura", Font.PLAIN, 13));
		btnCreateANewCourse.setBounds(225, 130, 300, 29);
		contentPane.add(btnCreateANewCourse);
        btnCreateANewCourse.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewAllCourses vac = new ViewAllCourses(crs);
                vac.setVisible(true);
            }
        });
		
		JButton btnDeleteACourse = new JButton("View all courses that are not full");
		btnDeleteACourse.setFont(new Font("Futura", Font.PLAIN, 13));
		btnDeleteACourse.setBounds(225, 187, 300, 29);
        btnDeleteACourse.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewNotFullCourses vfc = new ViewNotFullCourses(crs);
                vfc.setVisible(true);
            }
        });
		contentPane.add(btnDeleteACourse);
		
		JButton btnEditACourse = new JButton("View all registered courses");
		btnEditACourse.setFont(new Font("Futura", Font.PLAIN, 13));
		btnEditACourse.setBounds(225, 240, 300, 29);
		contentPane.add(btnEditACourse);
        btnEditACourse.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewStudentsClasses vsc = new ViewStudentsClasses(crs);
                vsc.setVisible(true);
            }
        });
		
		JButton btnDisplayCourseInformation = new JButton("Withdraw from a course");
		btnDisplayCourseInformation.setFont(new Font("Futura", Font.PLAIN, 13));
		btnDisplayCourseInformation.setBounds(225, 295, 300, 29);
        btnDisplayCourseInformation.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WithdrawFromCourse wfc = new WithdrawFromCourse(crs);
                wfc.setVisible(true);
            }
        });
		contentPane.add(btnDisplayCourseInformation);
		
		JButton btnRegisterAStudent = new JButton("Register for a course");
		btnRegisterAStudent.setFont(new Font("Futura", Font.PLAIN, 13));
		btnRegisterAStudent.setBounds(225, 353, 300, 29);
        btnRegisterAStudent.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EnrollCourse ec = new EnrollCourse(crs);
                ec.setVisible(true);
            }
        });
		contentPane.add(btnRegisterAStudent);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Futura", Font.PLAIN, 13));
		btnExit.setBounds(279, 448, 179, 29);
        btnExit.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        
		contentPane.add(btnExit);
	}
    
    public StudentMenu(CRS crs) {
        this();
        this.crs = crs;
    }

}
