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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * @author HP
 */
public class MasterDataList {

    JPanel mainPanel;
    
    JPanel inputPanel;
    
    ButtonGroup radioButtons;
    JRadioButton stateAndDistrict, departmentAndOffice, court, caseType, designation, lawyers, judges, act, subject, section, document;
    JComboBox selectState, selectDistrict, selectDepartment, selectOffice;
    
    JButton submit, cancel;
    
    JPanel outputPanel;
    
    public void prepareGUI(){
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
        
        radioButtons = new ButtonGroup();
        
        stateAndDistrict = new JRadioButton("State", false);
        radioButtons.add(stateAndDistrict);
        inputPanel.add(stateAndDistrict, inputConstraint);
        
        inputConstraint.gridx++;
        
        String[] states = {"Select one","Punjab", "Haryana"};
        selectState = new JComboBox(states);
        inputPanel.add(selectState, inputConstraint);
        
        inputConstraint.gridx++;
        
        String[] districts = {"Select District"};
        selectDistrict = new JComboBox(districts);
        inputPanel.add(selectDistrict, inputConstraint);
        
        inputConstraint.gridx = 0;
        inputConstraint.gridy++;
        
        departmentAndOffice = new JRadioButton("Department", false);
        radioButtons.add(departmentAndOffice);
        inputPanel.add(departmentAndOffice, inputConstraint);
        
        inputConstraint.gridx++;
        
        String[] departments = {"Select one"};
        selectDepartment = new JComboBox(departments);
        inputPanel.add(selectDepartment, inputConstraint);
        
        inputConstraint.gridx++;
        
        String[] offices = {"Select office"};
        selectOffice = new JComboBox(offices);
        inputPanel.add(selectOffice, inputConstraint);
        
        inputConstraint.gridx = 0;
        inputConstraint.gridy++;
        
        court = new JRadioButton("Court", false);
        radioButtons.add(court);
        inputPanel.add(court, inputConstraint);
        
        inputConstraint.gridx++;
        
        caseType = new JRadioButton("Case Type", false);
        radioButtons.add(caseType);
        inputPanel.add(caseType, inputConstraint);
        
        inputConstraint.gridx++;
        
        designation = new JRadioButton("Designation", false);
        radioButtons.add(designation);
        inputPanel.add(designation, inputConstraint);
        
        inputConstraint.gridx = 0;
        inputConstraint.gridy++;
        
        lawyers = new JRadioButton("Lawyers", false);
        radioButtons.add(lawyers);
        inputPanel.add(lawyers,inputConstraint);
        
        inputConstraint.gridx++;
        
        judges = new JRadioButton("Judges", false);
        radioButtons.add(judges);
        inputPanel.add(judges, inputConstraint);
        
        inputConstraint.gridx++;
        
        act = new JRadioButton("Act", false);
        radioButtons.add(act);
        inputPanel.add(act, inputConstraint);
        
        inputConstraint.gridx = 0;
        inputConstraint.gridy++;
        
        subject = new JRadioButton("Subject", false);
        radioButtons.add(subject);
        inputPanel.add(subject, inputConstraint);
        
        inputConstraint.gridx++;
        
        section = new JRadioButton("Section", false);
        radioButtons.add(section);
        inputPanel.add(section, inputConstraint);
        
        inputConstraint.gridx++;
        
        document = new JRadioButton("Document", false);
        radioButtons.add(document);
        inputPanel.add(document, inputConstraint);
        
        inputConstraint.gridx = 0;
        inputConstraint.gridy++;
        
        submit = new JButton("Submit");
        inputPanel.add(submit,inputConstraint);
        
        mainPanel.add(inputPanel, BorderLayout.NORTH);
    /**inputPanel layout completed*/
    
    /**ouputPanel layout begins*/
        outputPanel = new JPanel();
        outputPanel.setBackground(Color.lightGray);
        mainPanel.add(outputPanel, BorderLayout.CENTER);
    /**outputPanel layout completed*/
    }

    
}
