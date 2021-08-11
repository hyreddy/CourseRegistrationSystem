import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminCourseMgnt extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminCourseMgnt frame = new AdminCourseMgnt();
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
	public AdminCourseMgnt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnViewCourses = new JButton("View Courses");
		btnViewCourses.setBounds(39, 61, 117, 29);
		contentPane.add(btnViewCourses);
		
		JLabel lblNewLabel = new JLabel("Course Management");
		lblNewLabel.setBounds(181, 30, 134, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnBackMenu = new JButton("Back");
		btnBackMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnBackMenu.setBounds(285, 227, 117, 29);
		contentPane.add(btnBackMenu);
	}

}
