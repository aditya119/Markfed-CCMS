/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

/**
 *
 * @author HP
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class Login {
    
	JFrame mainFrame;
	BorderLayout frameLayout;
	JPanel northPanel;
	JLabel heading;	
	JPanel mainPanel;
	GridBagLayout mainPanelLayout;	
	JLabel usernameLabel;
	JTextField usernameField;
	JLabel passwordLabel;
	JPasswordField passwordField;	
	JButton submit;
	JButton reset;
	JButton close;	
	JPanel southPanel;
	GridBagLayout southPanelLayout;	
//	JButton downloadUserManual;
	JLabel southLabel;
        String user, logintype;
        
        static String databaseLocation;
        Connection connect;
        ResultSet rs,mo;
        Statement stateObj;
	
	public static void main(String[] args){
		Login object = new Login();
		object.prepareGUI();
	}
	
	void prepareGUI(){
		mainFrame = new JFrame("Login Page");
		frameLayout = new BorderLayout();
		
		mainFrame.setResizable(false);
		mainFrame.setLayout(frameLayout);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(500, 300);
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension size = toolkit.getScreenSize();
		mainFrame.setLocation(size.width/2-mainFrame.getWidth()/2,size.height/2-mainFrame.getHeight()/2);
                mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
	
		northPanel = new JPanel();	
		northPanel.setBackground(Color.white);
		
		heading = new JLabel("Markfed Court Case Monitoring System");
		northPanel.add(heading);
		
		mainFrame.add(northPanel, BorderLayout.NORTH);
		
	/**main body in main panel*/
		mainPanel = new JPanel();
                
		mainPanelLayout = new GridBagLayout();
		mainPanel.setLayout(mainPanelLayout);
		
                GridBagConstraints constraint = new GridBagConstraints();
                constraint.gridheight = 1;
                constraint.gridwidth = 1;
                constraint.gridx = 0;
                constraint.gridy = 0;
                constraint.insets.top = 5;
                constraint.insets.bottom = 5;
                constraint.insets.left = 5;
                constraint.insets.right = 8;
                constraint.anchor = GridBagConstraints.CENTER;

		usernameLabel = new JLabel("Username");
		mainPanel.add(usernameLabel, constraint);
		constraint.gridx++;
		usernameField = new JTextField();
		usernameField.setColumns(10);
		mainPanel.add(usernameField, constraint);
		
		constraint.gridx = 0;
		constraint.gridy++;
		passwordLabel = new JLabel("Password");
		mainPanel.add(passwordLabel, constraint);
		constraint.gridx++;
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		mainPanel.add(passwordField, constraint);
		
		constraint.gridx = 0;
		constraint.gridy++;
		submit = new JButton("Submit");
		mainPanel.add(submit, constraint);
		constraint.gridx++;
		reset = new JButton("Reset");
		mainPanel.add(reset, constraint);
		constraint.gridx++;
		close = new JButton("Close");
		//mainPanel.add(close, constraint);
		
		mainFrame.add(mainPanel, BorderLayout.CENTER);
		
	/**residual stuff in south panel*/
		southPanel = new JPanel();
                
		southPanelLayout = new GridBagLayout();
		southPanel.setLayout(southPanelLayout);
		southPanel.setBackground(Color.white);
		
		GridBagConstraints southConstraint = new GridBagConstraints();
                southConstraint.gridheight = 1;
                southConstraint.gridwidth = 1;
                southConstraint.gridx = 0;
                southConstraint.gridy = 0;
                southConstraint.insets.top = 5;
                southConstraint.insets.bottom = 5;
                southConstraint.insets.left = 5;
                southConstraint.insets.right = 8;
                southConstraint.anchor = GridBagConstraints.CENTER;
		
//		downloadUserManual = new JButton("User Manual");
		southPanel.add(new JLabel("Designed and Developed by CSE Department, CCET, Chandigarh"), southConstraint);
		
		southConstraint.gridy++;

		mainFrame.add(southPanel, BorderLayout.SOUTH);
                
                southPanel.setVisible(true);
                mainPanel.setVisible(true);
                mainFrame.setVisible(true);
                
                submit.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            DatabaseHandling object = new DatabaseHandling();
                            int x = object.verifyUser(usernameField.getText(), passwordField.getText());
                            switch (x) {
                                case 1:{
                                    object.changeStatus(usernameField.getText(), "ONLINE");
                                    LoginHome obj = new LoginHome();
                                    obj.prepareGUI(usernameField.getText(), "Administrator");
                                    mainFrame.dispose();
                                    break;
                                }
                                case 2:{
                                    object.changeStatus(usernameField.getText(), "ONLINE");
                                    LoginHome obj = new LoginHome();
                                    obj.prepareGUI(usernameField.getText(), "Operator");
                                    mainFrame.dispose();
                                    break;
                                }
                                case 0:{
                                    JOptionPane.showMessageDialog(mainFrame, "Password Incorrect");
                                    break;
                                }
                                default:
                                    JOptionPane.showMessageDialog(mainFrame, "Username Incorrect");
                                    break;
                            }
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                reset.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                });
           }
}