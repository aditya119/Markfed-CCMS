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
public class ChangePassword {
    JPanel rootPanel;
    JPanel basePanel;
   
    GridBagConstraints constraints;
    JTextField userName;
    JPasswordField oldPassword,newPassword,confirmPassword;
    JComboBox loginType;
    JButton submit,cancel;
    JPanel panelForButton;
    
    JPanel prepareGUI(String username, String userGroup){
    rootPanel = new JPanel(new BorderLayout());
    panelForButton = new JPanel();
    basePanel = new JPanel(new GridBagLayout());
    basePanel.setBorder(BorderFactory.createLineBorder(Color.orange,2,true));
    basePanel.setBackground(Color.white);
    
    submit = new JButton("Submit");
    cancel = new JButton("Cancel");
    
    userName = new JTextField(20);
    
    loginType = new JComboBox();
    loginType.addItem("Administrator");
    loginType.addItem("Operator");
    
    userName = new JTextField(20);
    oldPassword = new JPasswordField(20);
    confirmPassword = new JPasswordField(20);
    newPassword = new JPasswordField(20);
    
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
    
    if(userGroup.equals("Administrator")){
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
    }
    
    basePanel.add(new JLabel("Enter Old Password"),constraints);
    constraints.gridx ++;
    
    basePanel.add(oldPassword,constraints);
    constraints.gridx=0;
    constraints.gridy++;
    
    basePanel.add(new JLabel("Enter New Password"),constraints);
    constraints.gridx ++;
    
    basePanel.add(newPassword,constraints);
    constraints.gridx=0;
    constraints.gridy++;
    
    basePanel.add(new JLabel("Confirm Password"),constraints);
    constraints.gridx ++;
    
    basePanel.add(confirmPassword,constraints);
    
    panelForButton.add(submit);
    panelForButton.add(cancel);
    
    rootPanel.add(basePanel);
    rootPanel.add(panelForButton,BorderLayout.SOUTH);
    
    submit.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            DatabaseHandling object = new DatabaseHandling();
            if(!newPassword.getText().equals(confirmPassword.getText())){
                JOptionPane.showMessageDialog(new JFrame(), "New Password and Confirm Password fields do not match");
            }
            else if(userGroup.equals("Operator")){
                try {
                    if(oldPassword.getText().isEmpty() || newPassword.getText().isEmpty() || confirmPassword.getText().isEmpty()){
                        JOptionPane.showMessageDialog(new JFrame(), "Fill all Text Fields");
                        return;
                    }
                    int x = object.changePassword(username, userGroup, oldPassword.getText(), newPassword.getText());
                    if(x == 1){
                        JOptionPane.showMessageDialog(new JFrame(), "Password changed");
                        userName.setText("");
                        oldPassword.setText("");
                        confirmPassword.setText("");
                        newPassword.setText("");
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(), "Record not found, check details");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(userGroup.equals("Administrator")){
                try {
                    if(userName.getText().isEmpty() || oldPassword.getText().isEmpty() || newPassword.getText().isEmpty() || confirmPassword.getText().isEmpty()){
                        JOptionPane.showMessageDialog(new JFrame(), "Fill all Text Fields");
                        return;
                    }
                    int x = object.changePassword(userName.getText(), loginType.getSelectedItem().toString(), oldPassword.getText(), newPassword.getText());
                    if(x == 1){
                        JOptionPane.showMessageDialog(new JFrame(), "Password changed");
                        userName.setText("");
                        oldPassword.setText("");
                        confirmPassword.setText("");
                        newPassword.setText("");
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(), "Record not found, check details");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
    
    return rootPanel;
   }
}