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

public class AddACourse extends JFrame {

	private JPanel contentPane;
	private JTextField textMaxNumStud;
	private JTextField textCourseName;
	private JTextField textCourseID;
	private JTextField textInstuctor;
	private JTextField textSectionNumber;
	private JTextField textLocation;
    private CRS crs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddACourse frame = new AddACourse(null);
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
	public AddACourse(CRS crs) {
        this.crs = crs;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddACourse = new JLabel("Add a Course");
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
		
		textMaxNumStud = new JTextField();
		textMaxNumStud.setBounds(445, 290, 190, 29);
		contentPane.add(textMaxNumStud);
		textMaxNumStud.setColumns(10);
		
		textCourseName = new JTextField();
		textCourseName.setColumns(10);
		textCourseName.setBounds(445, 215, 190, 29);
		contentPane.add(textCourseName);
		
		textCourseID = new JTextField();
		textCourseID.setColumns(10);
		textCourseID.setBounds(445, 254, 190, 29);
		contentPane.add(textCourseID);
		
		textInstuctor = new JTextField();
		textInstuctor.setColumns(10);
		textInstuctor.setBounds(445, 332, 190, 29);
		contentPane.add(textInstuctor);
		
		textSectionNumber = new JTextField();
		textSectionNumber.setColumns(10);
		textSectionNumber.setBounds(445, 371, 190, 29);
		contentPane.add(textSectionNumber);
		
		textLocation = new JTextField();
		textLocation.setColumns(10);
		textLocation.setBounds(445, 405, 190, 29);
		contentPane.add(textLocation);
		
		JButton btnAddACourse = new JButton("Add Course");
		btnAddACourse.setBounds(330, 476, 117, 29);
		btnAddACourse.setFont(new Font("Futura", Font.PLAIN, 13));
		contentPane.add(btnAddACourse);
        
        btnAddACourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String maxStudents = textMaxNumStud.getText();
                String courseName = textCourseName.getText();
                String courseID = textCourseID.getText();
                String instuctor = textInstuctor.getText();
                String sectionNumber = textSectionNumber.getText();
                String location = textLocation.getText();
                
                try {
                    Course c = new Course(courseName, courseID, instuctor, location, Integer.parseInt(sectionNumber), Integer.parseInt(maxStudents));
                    crs.getCourses().add(c);
                    JOptionPane.showMessageDialog(null, "Course Succesfully Added", "Success", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    dispose();

                }
                catch(NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Max Students Must be an Integer", "Invalid Entry", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        JButton exit = new JButton("Exit");
        exit.setBounds(330, 525, 117, 29);
        exit.setFont(new Font("Futura", Font.PLAIN, 13));
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        contentPane.add(exit);
                    
		
	}
	
	

}
