import crs.*;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.event.WindowEvent;

public class LogInScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JTextField textPwd;
    private CRS crs;

    private String type;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInScreen frame = new LogInScreen();
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
	public LogInScreen() {
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setFont(new Font("Futura", Font.PLAIN, 30));
		lblLogIn.setBounds(291, 77, 138, 85);
		contentPane.add(lblLogIn);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Futura", Font.PLAIN, 20));
		lblUsername.setBounds(157, 212, 125, 27);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Futura", Font.PLAIN, 20));
		lblPassword.setBounds(157, 295, 125, 27);
		contentPane.add(lblPassword);
		
		textUsername = new JTextField();
		textUsername.setBounds(291, 208, 210, 41);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		textPwd = new JTextField();
		textPwd.setColumns(10);
		textPwd.setBounds(291, 291, 210, 41);
		contentPane.add(textPwd);
        
		JButton btnLogIn = new JButton("Log In");
        btnLogIn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textUsername.getText();
                String password = textPwd.getText();
                
                boolean loggedin = crs.login(username, password);
                if(loggedin) {
                    if(type.equals("Admin")) {
                        AdminMenu admin = new AdminMenu(crs);
                        admin.setVisible(true);
                    }
                    else {
                        StudentMenu student = new StudentMenu(crs);
                        student.setVisible(true);
                    }
                    setVisible(false);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid Password", "Invalid Username/Password Combination", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
		btnLogIn.setBounds(301, 401, 117, 29);
		contentPane.add(btnLogIn);
	}
    
    public LogInScreen(CRS crs, String type) {
        this();
        this.crs = crs;
        this.type = type;
    }

}
