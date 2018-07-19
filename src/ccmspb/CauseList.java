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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class CauseList {	
	JPanel centerPanel;	//contains main data input and required output
	BorderLayout centerPanelLayout;
	
	JPanel inputPanel;	//inputs required details
	GridBagLayout inputPanelLayout;
	
	JLabel listingDateWise;
	JTextField listingDateWiseField;
	
	JButton submit;
	
	JPanel outputPanel;	//produces output based on the provided input
		
	void prepareGUI(){
	/**center panel layout begins*/
            centerPanel = new JPanel();
            centerPanelLayout = new BorderLayout();
            centerPanel.setLayout(centerPanelLayout);

            /**input panel layout begins*/
            inputPanel = new JPanel();
            inputPanelLayout = new GridBagLayout();
            inputPanel.setLayout(inputPanelLayout);

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

            listingDateWise = new JLabel("Listing Date Wise");
            inputPanel.add(listingDateWise, inputConstraint);

            inputConstraint.gridx++;
            listingDateWiseField = new JTextField();
            listingDateWiseField.setColumns(7);
            inputPanel.add(listingDateWiseField);

            inputConstraint.gridx = 0;
            inputConstraint.gridy++;
            submit = new JButton("Submit");
            inputPanel.add(submit, inputConstraint);

            centerPanel.add(inputPanel, BorderLayout.NORTH);
            /**input panel layout completed*/

            /**output panel layout begins*/
            outputPanel = new JPanel();
            outputPanel.setBackground(Color.lightGray);
            centerPanel.add(outputPanel, BorderLayout.CENTER);
            /**output panel layout completed*/
            
	/**center panel layout completed*/
	}
    
}
