import crs.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;*/

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class AdminMenu extends JFrame {

	private JPanel contentPane;
    private CRS crs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminMenu() {
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCourseMgmt = new JLabel("Course Management ");
		lblCourseMgmt.setFont(new Font("Futura", Font.PLAIN, 22));
		lblCourseMgmt.setBounds(67, 40, 217, 44);
		contentPane.add(lblCourseMgmt);
		
		JLabel lblReports = new JLabel("Reports");
		lblReports.setFont(new Font("Futura", Font.PLAIN, 22));
		lblReports.setBounds(432, 40, 217, 44);
		contentPane.add(lblReports);
		
		JButton btnCreateANewCourse = new JButton("Create a New Course");
		btnCreateANewCourse.setFont(new Font("Futura", Font.PLAIN, 13));
		btnCreateANewCourse.setBounds(67, 130, 300, 29);
        
        btnCreateANewCourse.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddACourse addframe = new AddACourse(crs);
                addframe.setVisible(true);
            }
		});
        
		contentPane.add(btnCreateANewCourse);
		
		JButton btnDeleteACourse = new JButton("Delete a Course");
		btnDeleteACourse.setFont(new Font("Futura", Font.PLAIN, 13));
		btnDeleteACourse.setBounds(67, 187, 300, 29);
        btnDeleteACourse.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteCourse delete = new DeleteCourse(crs);
                delete.setVisible(true);
            }
        });
		contentPane.add(btnDeleteACourse);
		
		JButton btnEditACourse = new JButton("Edit a Course");
		btnEditACourse.setFont(new Font("Futura", Font.PLAIN, 13));
		btnEditACourse.setBounds(67, 240, 300, 29);
        btnEditACourse.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditCourse edit = new EditCourse(crs);
                edit.setVisible(true);
            }
        });
        
        
		contentPane.add(btnEditACourse);
		
		JButton btnDisplayCourseInformation = new JButton("Display Course Information");
		btnDisplayCourseInformation.setFont(new Font("Futura", Font.PLAIN, 13));
		btnDisplayCourseInformation.setBounds(67, 295, 300, 29);
		contentPane.add(btnDisplayCourseInformation);
		btnDisplayCourseInformation.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DisplayInfo di = new DisplayInfo(crs);
                
                
                
                
                di.setVisible(true);
            }
		});
		
		
		
		JButton btnRegisterAStudent = new JButton("Register a Student");
		btnRegisterAStudent.setFont(new Font("Futura", Font.PLAIN, 13));
		btnRegisterAStudent.setBounds(67, 353, 300, 29);
		contentPane.add(btnRegisterAStudent);
		
		btnRegisterAStudent.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterAStudent stu = new RegisterAStudent(crs);
                stu.setVisible(true);
            }
		});
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Futura", Font.PLAIN, 13));
		btnExit.setBounds(279, 480, 179, 29);
		contentPane.add(btnExit);
		btnExit.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
		});
		
		JButton btnViewAllCourses = new JButton("View All Courses");
		btnViewAllCourses.setFont(new Font("Futura", Font.PLAIN, 13));
		btnViewAllCourses.setBounds(432, 130, 300, 29);
        btnViewAllCourses.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewAllCoursesAdmin vaca = new ViewAllCoursesAdmin(crs);
                vaca.setVisible(true);
            }
		});
		contentPane.add(btnViewAllCourses);
		
		JButton btnViewFullCourses = new JButton("View Full Courses");
		btnViewFullCourses.setFont(new Font("Futura", Font.PLAIN, 13));
		btnViewFullCourses.setBounds(432, 187, 300, 29);
        btnViewFullCourses.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewFullCourses vac = new ViewFullCourses(crs);
                vac.setVisible(true);
            }
		});
		contentPane.add(btnViewFullCourses);
		
		JButton btnWriteToA = new JButton("Write to a File");
		btnWriteToA.setFont(new Font("Futura", Font.PLAIN, 13));
		btnWriteToA.setBounds(432, 240, 300, 29);
		btnWriteToA.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               User user = crs.getLoggedIn();
               Admin admin = (Admin)user;
               admin.writeFullToFile(crs.getCourses());
                JOptionPane.showMessageDialog(null, "Successfully wrote to coursesFull.txt", "Succesful", JOptionPane.INFORMATION_MESSAGE);

            }
		});
		
		
		contentPane.add(btnWriteToA);
		
		
		JButton btnCourseRegisteredStudents = new JButton("Course Registered Students");
		btnCourseRegisteredStudents.setFont(new Font("Futura", Font.PLAIN, 13));
		btnCourseRegisteredStudents.setBounds(432, 295, 300, 29);
        btnCourseRegisteredStudents.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CourseRegisteredStudents courseStudents = new CourseRegisteredStudents(crs);
                courseStudents.setVisible(true);
            }
        });
        
        
        
		contentPane.add(btnCourseRegisteredStudents);
		
		JButton btnStudentsEnrolledClasses = new JButton("Student's Enrolled Classes");
		btnStudentsEnrolledClasses.setFont(new Font("Futura", Font.PLAIN, 13));
		btnStudentsEnrolledClasses.setBounds(432, 353, 300, 29);
        btnStudentsEnrolledClasses.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentEnrolledClasses sec = new StudentEnrolledClasses(crs);
                sec.setVisible(true);
            }
        });
        
		contentPane.add(btnStudentsEnrolledClasses);
		
		JButton btnSortCourses = new JButton("Sort Courses");
		btnSortCourses.setFont(new Font("Futura", Font.PLAIN, 13));
		btnSortCourses.setBounds(432, 407, 300, 29);
        btnSortCourses.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < crs.getCourses().size(); i++) {
                    int j = i;
                    while((j > 0) && (crs.getCourses().get(j).getRegNumStud() > crs.getCourses().get(j - 1).getRegNumStud())) {
                        Course c1 = crs.getCourses().get(j);
                        Course c2 = crs.getCourses().get(j - 1);
                        crs.getCourses().set(j, c2);
                        crs.getCourses().set(j-1, c1);
                        j--;
                    }
                }
                ViewAllCoursesAdmin vaca = new ViewAllCoursesAdmin(crs);
                vaca.setVisible(true);
                //JOptionPane.showMessageDialog(null, "Successfully Sorted", "Sorted", JOptionPane.INFORMATION_MESSAGE);
            }
        });
		contentPane.add(btnSortCourses);
	}
    
    public AdminMenu(CRS crs) {
        this();
        this.crs = crs;
    }

}