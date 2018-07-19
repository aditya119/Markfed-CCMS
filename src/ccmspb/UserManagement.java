/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class UserManagement {
    
    JPanel basePanel;
    GridBagConstraints constraints;
    JComboBox userType,loginType,departmentType;
    JTextField userName,fullName,contact,emailAddress,/*city,*/ emailPassword;
    
    JButton submit,cancel;
    JPanel panelForButton;
    JPanel rootPanel;
    JPasswordField password,confirmPassword;
        
    JPanel prepareGUI(){
        rootPanel = new JPanel(new BorderLayout());
        panelForButton = new JPanel();
        basePanel = new JPanel(new GridBagLayout());
        basePanel.setBackground(Color.white);
        basePanel.setBorder(BorderFactory.createLineBorder(Color.orange,2, true));
        userType = new JComboBox();
        userType.addItem("State");
        userType.addItem("District");
        userType.addItem("Tehsil");
        userType.addItem("Block");

        submit = new JButton("Submit");
        cancel = new JButton("Cancel");

        loginType = new JComboBox();
        loginType.addItem("Administrator");
        loginType.addItem("Operator");

        userName = new JTextField(20);
        password = new JPasswordField(20);
        confirmPassword = new JPasswordField(20);
        fullName = new JTextField(20);
        contact = new JTextField(20);
        emailAddress = new JTextField(20);
        emailPassword = new JTextField(20);
//        city = new JTextField(20);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.insets.bottom =5;
        constraints.insets.top =5;
        constraints.insets.right =5;
        constraints.insets.left =5;
        constraints.anchor = GridBagConstraints.WEST;

    //    basePanel.add(new JLabel("Select User Type"),constraints);
    //    constraints.gridx ++;
    //    
    //    basePanel.add(userType,constraints);
    //    constraints.gridx=0;
    //    constraints.gridy++;

        basePanel.add(new JLabel("Select Login Type"),constraints);
        constraints.gridx ++;

        basePanel.add(loginType,constraints);
        constraints.gridx=0;
        constraints.gridy++;

        basePanel.add(new JLabel("User Full Name"),constraints);
        constraints.gridx ++;

        basePanel.add(fullName,constraints);
        constraints.gridx=0;
        constraints.gridy++;

//        basePanel.add(new JLabel("Contact"),constraints);
//        constraints.gridx ++;
//
//        basePanel.add(contact,constraints);
//        constraints.gridx=0;
//        constraints.gridy++;

        basePanel.add(new JLabel("Email Address"),constraints);
        constraints.gridx ++;

        basePanel.add(emailAddress,constraints);
        constraints.gridx=0;
        constraints.gridy++;
        
        basePanel.add(new JLabel("Email Password for App"),constraints);
        constraints.gridx ++;

        basePanel.add(emailPassword,constraints);
        constraints.gridx=0;
        constraints.gridy++;

//        basePanel.add(new JLabel("Enter Location"),constraints);
//        constraints.gridx ++;
//
//        basePanel.add(city, constraints);
//        constraints.gridx=0;
//        constraints.gridy++;

        basePanel.add(new JLabel("Enter Username"),constraints);
        constraints.gridx ++;

        basePanel.add(userName,constraints);
        constraints.gridx=0;
        constraints.gridy++;

        basePanel.add(new JLabel("Enter Password"),constraints);
        constraints.gridx ++;

        basePanel.add(password,constraints);
        constraints.gridx=0;
        constraints.gridy++;

        basePanel.add(new JLabel("Confirm Password"),constraints);
        constraints.gridx ++;

        basePanel.add(confirmPassword,constraints);
        constraints.gridx=0;
        constraints.gridy++;


        panelForButton.add(submit);
        panelForButton.add(cancel);

        rootPanel.add(basePanel);
        rootPanel.add(panelForButton,BorderLayout.SOUTH);

        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
//                    if(contact.getText().length() != 13){
//                        JOptionPane.showMessageDialog(new JFrame(), "Add contact in the format +91XXXXXXXXXX");
//                        return;
//                    }
                    if(fullName.getText().isEmpty() /*|| contact.getText().isEmpty()*/ || emailAddress.getText().isEmpty() || emailPassword.getText().isEmpty() || /*city.getText().isEmpty() ||*/ userName.getText().isEmpty() || password.getText().isEmpty() || confirmPassword.getText().isEmpty()){
                        JOptionPane.showMessageDialog(new JFrame(), "Fill all the Text Fields");
                        return;
                    }
                    if(!password.getText().equals(confirmPassword.getText())){
                        JOptionPane.showMessageDialog(new JFrame(), "Password and Confirm Password fields do not match");
                    }
                    else{
                        DatabaseHandling object = new DatabaseHandling();
                        int x = object.createNewUser(loginType.getSelectedItem().toString(), fullName.getText()/*, contact.getText()*/, emailAddress.getText(), emailPassword.getText(), /*city.getText(),*/ userName.getText(), password.getText());
                        switch(x){
                            case 0: {
                                JOptionPane.showMessageDialog(new JFrame(), "New User Added");
                                break;
                            }
                            case 1: {
                                JOptionPane.showMessageDialog(new JFrame(), "User Already exists");
                                break;
                            }
                            case 2: {
                                JOptionPane.showMessageDialog(new JFrame(), "Error");
                                break;
                            }
                        }
                        userName.setText("");
                        fullName.setText("");
                        contact.setText("");
                        emailAddress.setText("");
                        emailPassword.setText("");
//                        city.setText("");
                        password.setText("");
                        confirmPassword.setText("");
                    }                
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                userName.setText("");
                fullName.setText("");
                contact.setText("");
                emailAddress.setText("");
//                city.setText("");
                password.setText("");
                confirmPassword.setText("");
            }
        });

        return rootPanel;
    }
}