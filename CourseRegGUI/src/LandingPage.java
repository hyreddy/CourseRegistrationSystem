import crs.*;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class LandingPage {

	private JFrame frame;
    private CRS crs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LandingPage window = new LandingPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LandingPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
    private void login(String type) {
        LogInScreen login = new LogInScreen(this.crs, type);
        login.setVisible(true);
    }
	private void initialize() {
        this.crs = CRS.deserialize();
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAdmin = new JButton("Administrator");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                login("Admin");
			}
		});
		btnAdmin.setFont(new Font("Futura", Font.PLAIN, 30));
		btnAdmin.setBounds(123, 220, 232, 90);
		frame.getContentPane().add(btnAdmin);
		
		JButton btnStudent = new JButton("Student");
		btnStudent.setFont(new Font("Futura", Font.PLAIN, 30));
		btnStudent.setBounds(429, 220, 232, 90);
        btnStudent.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login("Student");
            }
        });
		frame.getContentPane().add(btnStudent);
		
		JLabel lblNewLabel = new JLabel("Or");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Futura", Font.BOLD, 15));
		lblNewLabel.setBounds(359, 253, 61, 34);
		frame.getContentPane().add(lblNewLabel);
        
    WindowListener exitListener = new WindowAdapter() {
    @Override
    public void windowClosing(WindowEvent e) {
            JOptionPane.showMessageDialog(null, "Saving Data", "Goodbye", JOptionPane.INFORMATION_MESSAGE);
            CRS.serialize(crs);
            System.exit(0);
    }
    };
    frame.addWindowListener(exitListener);  
	}
    
    

}
