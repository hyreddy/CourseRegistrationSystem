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

public class DeleteCourse extends JFrame {

	private JPanel contentPane;
	private JTextField textMaxNumStud;
	private JTextField textCourseName;
	private JTextField textCourseID;
	private JTextField textInstuctor;
	private JTextField textSectionNumber;
	private JTextField textLocation;
    private CRS crs;
    
    public DeleteCourse(CRS crs) {
        
        this.crs = crs;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        
        JLabel lblAddACourse = new JLabel("Delete a Course");
		lblAddACourse.setFont(new Font("Futura", Font.PLAIN, 30));
		lblAddACourse.setBounds(60, 57, 351, 43);
		contentPane.add(lblAddACourse);

		
        
        ArrayList<Course> courses = crs.getCourses();
        String[] data = new String[courses.size()];
        for(int i = 0; i < courses.size(); i++) {
            data[i] = courses.get(i).getCourseName();
        }
        JList<String> myList = new JList<String>(data);
        myList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        myList.setBounds(60, 110, 500, 600);
        contentPane.add(myList);
        
        
        JButton btnEditACourse = new JButton("Delete Course");
		btnEditACourse.setFont(new Font("Futura", Font.PLAIN, 13));
		btnEditACourse.setBounds(200, 730, 179, 29);
        btnEditACourse.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delete(myList.getSelectedIndex());
                
                ArrayList<Course> courses = crs.getCourses();
                String[] newData = new String[courses.size()];
                for(int i = 0; i < courses.size(); i++) {
                    newData[i] = courses.get(i).getCourseName();
                }
                myList.setListData(newData);
                repaint();
            }
        });
        
        contentPane.add(btnEditACourse);
        
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Futura", Font.PLAIN, 13));
		btnExit.setBounds(200, 780, 179, 29);
		contentPane.add(btnExit);
		btnExit.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
		});
        
        contentPane.add(btnExit);
    }	
    
    public void delete(int val) {
        crs.getCourses().remove(val);
    }
	

}
