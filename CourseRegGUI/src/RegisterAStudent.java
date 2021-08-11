import crs.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class RegisterAStudent extends JFrame {

	private JPanel contentPane;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JTextField textUsername;
	private JTextField textPassword;
	private CRS crs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterAStudent frame = new RegisterAStudent();
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
	public RegisterAStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegisterAStudent = new JLabel("Register a Student");
		lblRegisterAStudent.setFont(new Font("Futura", Font.PLAIN, 30));
		lblRegisterAStudent.setBounds(60, 57, 351, 43);
		contentPane.add(lblRegisterAStudent);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Futura", Font.PLAIN, 20));
		lblFirstName.setBounds(270, 199, 125, 27);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Futura", Font.PLAIN, 20));
		lblLastName.setBounds(270, 238, 125, 27);
		contentPane.add(lblLastName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Futura", Font.PLAIN, 20));
		lblUsername.setBounds(270, 277, 125, 27);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Futura", Font.PLAIN, 20));
		lblPassword.setBounds(270, 316, 125, 27);
		contentPane.add(lblPassword);
		
		textFirstName = new JTextField();
		textFirstName.setBounds(394, 202, 130, 26);
		contentPane.add(textFirstName);
		textFirstName.setColumns(10);
		
		textLastName = new JTextField();
		textLastName.setColumns(10);
		textLastName.setBounds(394, 241, 130, 26);
		contentPane.add(textLastName);
		
		textUsername = new JTextField();
		textUsername.setColumns(10);
		textUsername.setBounds(394, 280, 130, 26);
		contentPane.add(textUsername);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(394, 319, 130, 26);
		contentPane.add(textPassword);
		
		JButton btnRegisterStudent = new JButton("Register Student");
		btnRegisterStudent.setFont(new Font("Futura", Font.PLAIN, 13));
		btnRegisterStudent.setBounds(336, 401, 140, 29);
		contentPane.add(btnRegisterStudent);
		
		JButton btnExit = new JButton(" Exit");
		btnExit.setFont(new Font("Futura", Font.PLAIN, 13));
		btnExit.setBounds(336, 451, 140, 29);
		contentPane.add(btnExit);
		btnExit.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
		});
		
		btnRegisterStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textUsername.getText();

                if (crs.getUser(username) == null) {
                    String pwd = textPassword.getText();
                    String fname = textFirstName.getText();
                    String lname = textLastName.getText();
                    
                    Student stud = new Student(username, pwd, fname, lname);
                    crs.addUser(stud);
                    crs.updateRecords();
                    JOptionPane.showMessageDialog(null, "Success", "User Registered", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    dispose();

                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid Entry", "User Already Exists", JOptionPane.ERROR_MESSAGE);
                    //the student already exists
                }
            }
		});
		
	}
	
	public RegisterAStudent(CRS crs) {
	    this();
	    this.crs = crs;
	}

}