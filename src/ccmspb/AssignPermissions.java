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
 * @author Aditya Sharma
 */
public class AssignPermissions {
    JPanel rootPanel;
    JPanel basePanel;
   
    GridBagConstraints constraints;
    JTextField userName;
    JComboBox loginType;
    JButton submit,delete;
    JPanel panelForButton;
    
    JPanel prepareGUI(String username, String userGroup){
        rootPanel = new JPanel(new BorderLayout());
        panelForButton = new JPanel();
        basePanel = new JPanel(new GridBagLayout());
        basePanel.setBorder(BorderFactory.createLineBorder(Color.orange,2,true));
        basePanel.setBackground(Color.white);

        submit = new JButton("Submit");
        delete = new JButton("Delete");

        userName = new JTextField(20);

        loginType = new JComboBox();
        loginType.addItem("Administrator");
        loginType.addItem("Operator");

        userName = new JTextField(20);

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

        basePanel.add(new JLabel("Select User Type"),constraints);
        constraints.gridx ++;

        basePanel.add(loginType,constraints);
        constraints.gridx=0;
        constraints.gridy++;

        basePanel.add(new JLabel("Enter Username"),constraints);
        constraints.gridx ++;

        basePanel.add(userName,constraints);
        constraints.gridx=0;
        constraints.gridy++;
        
        panelForButton.add(submit);
        panelForButton.add(delete);

        rootPanel.add(basePanel);
        rootPanel.add(panelForButton,BorderLayout.SOUTH);
        
        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!new DatabaseHandling().isUsernameExisting(userName.getText())){
                        JOptionPane.showMessageDialog(new JFrame(), "Username does not exist");
                    }
                    else{
                        DatabaseHandling object = new DatabaseHandling();
                        object.updatePermissions(userName.getText(), loginType.getSelectedItem().toString());
                        JOptionPane.showMessageDialog(new JFrame(), "Usergroup changed");
                        
                        userName.setText("");
                        
                    }                
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!new DatabaseHandling().isUsernameExisting(userName.getText())){
                        JOptionPane.showMessageDialog(new JFrame(), "Username does not exist");
                    }
                    else{
                        DatabaseHandling object = new DatabaseHandling();
                        int x = object.deleteUser(userName.getText());
                        if(x == 0){
                            JOptionPane.showMessageDialog(new JFrame(), "User deleted");
                        }
                        else{
                            JOptionPane.showMessageDialog(new JFrame(), "Error");
                        }
                        
                        userName.setText("");
                        
                    }                
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(UserManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        return rootPanel;
    }
}