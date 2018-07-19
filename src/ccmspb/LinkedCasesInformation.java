/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

/**
 *
 * @author HP
 
 * 
 */
import javax.swing.*;
import java.awt.*;
public class LinkedCasesInformation {
    
    JPanel mainPanel;
    
    JPanel inputPanel;    
    JLabel courtWise;
    JComboBox selectCourt;
    JButton submit;
    
    JPanel outputPanel;
    
    void prepareGUI(){
        mainPanel = new JPanel(new BorderLayout());
        
    /**inputPanel layout begins*/
        inputPanel = new JPanel(new GridBagLayout());
        
        GridBagConstraints inputConstraint = new GridBagConstraints();
        inputConstraint.gridheight = 1;
        inputConstraint.gridwidth = 1;
        inputConstraint.gridx = 0;
        inputConstraint.gridy = 0;
        inputConstraint.insets.top = 5;
        inputConstraint.insets.bottom = 5;
        inputConstraint.insets.left = 5;
        inputConstraint.insets.right = 8;
        inputConstraint.anchor = GridBagConstraints.WEST;
        
        courtWise = new JLabel("Court Wise");
        inputPanel.add(courtWise, inputConstraint);
        
        inputConstraint.gridx++;
        
        String[] courts = {"Select one", "Supreme Court", "High Court", "District Court"};
        selectCourt = new JComboBox(courts);
        inputPanel.add(selectCourt, inputConstraint);
        
        inputConstraint.gridx = 0;
        inputConstraint.gridy++;
        
        submit = new JButton("Submit");
        inputPanel.add(submit, inputConstraint);
                
        mainPanel.add(inputPanel, BorderLayout.NORTH);
    /**inputPanel layout completed*/
        
    /**outputPanel layout begins*/
        outputPanel = new JPanel();
        outputPanel.setBackground(Color.lightGray);
        mainPanel.add(outputPanel, BorderLayout.CENTER);
    /**outputPanel layout completed*/
    
    }
}
