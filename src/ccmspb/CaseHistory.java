/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author HP
 */
public class CaseHistory {
    JPanel mainPanel;
    
    JPanel inputPanel;
    ButtonGroup radioButtons;
    JRadioButton courtButton, caseNumberButton, fileButton, lawyerButton, locationButton, nextHearingButton/*, multiButton*/;
    JComboBox selectCourt, selectLawyer,enterCaseNumber, enterFileNumber, selectLocation;     //aditya    20/10/16
//    JTextField enterCaseNumber, enterFileNumber;
    JButton submit;
    
    JPanel outputPanel;
    
    void prepareGUI(String username, String userGroup) throws ClassNotFoundException, SQLException{
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
        
        DatabaseHandling object = new DatabaseHandling();
        
        /**select location option*/
        locationButton = new JRadioButton("Select Location", false);
        radioButtons.add(locationButton);
        inputPanel.add(locationButton, inputConstraint);
        
        inputConstraint.gridx++;
        
        selectLocation = new JComboBox();
        object.populateLocationList(selectLocation);
        inputPanel.add(selectLocation, inputConstraint);
        
        /**select court option*/
        inputConstraint.gridy++;
        inputConstraint.gridx = 0;
        
        courtButton = new JRadioButton("Select Court", false);
        radioButtons.add(courtButton);
        inputPanel.add(courtButton, inputConstraint);
        
        inputConstraint.gridx++;
        
        selectCourt = new JComboBox();
        object.populateCourtsList(selectCourt);
        inputPanel.add(selectCourt, inputConstraint);
        
        /**case number option*/
        inputConstraint.gridy++;
        inputConstraint.gridx = 0;
        
        caseNumberButton = new JRadioButton("Enter Case Number", false);
        radioButtons.add(caseNumberButton);
        inputPanel.add(caseNumberButton, inputConstraint);
        
        inputConstraint.gridx++; 
        
        enterCaseNumber = new JComboBox();
        object.populateCaseNumberList(enterCaseNumber);
//        enterCaseNumber.setColumns(8);
        inputPanel.add(enterCaseNumber,inputConstraint);
        
        /**file number option*/
        inputConstraint.gridy++;
        inputConstraint.gridx = 0;
        
        fileButton = new JRadioButton("Enter File Number", false);
        radioButtons.add(fileButton);
        inputPanel.add(fileButton, inputConstraint);
        
        inputConstraint.gridx++;
                
        enterFileNumber = new JComboBox();     //combo-box used on ccms website
        object.populateFileNumberList(enterFileNumber);
//        enterFileNumber.setColumns(8);
        inputPanel.add(enterFileNumber, inputConstraint);
        
        /*String[] selectFileNumber = {"Select File Number", "1234"};  //temporary
        fileNumber = new JComboBox(selectFileNumber);         //combo-box used on ccms website
        inputPanel.add(fileNumber, inputConstraint);*/
        
        /**lawyer option*/
        inputConstraint.gridy++;
        inputConstraint.gridx = 0;
        
        lawyerButton = new JRadioButton("Select Lawyer");
        radioButtons.add(lawyerButton);
        inputPanel.add(lawyerButton, inputConstraint);
        
        inputConstraint.gridx++;
        
        selectLawyer = new JComboBox();
        object.populateLawyerList(selectLawyer);
        inputPanel.add(selectLawyer, inputConstraint);
        
        /**next hearing option*/
        inputConstraint.gridy++;
        inputConstraint.gridx = 0;
        
        nextHearingButton = new JRadioButton("Next Hearing between", false);
        radioButtons.add(nextHearingButton);
        inputPanel.add(nextHearingButton, inputConstraint);
        
        inputConstraint.gridx++;
        
        UtilDateModel afterModel = new UtilDateModel();
        UtilDateModel beforeModel = new UtilDateModel();
        
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        
        JDatePanelImpl datePanelAfter = new JDatePanelImpl(afterModel, p);;
        JDatePanelImpl datePanelBefore = new JDatePanelImpl(beforeModel, p);;
        
        JDatePickerImpl datePickerAfter = new JDatePickerImpl(datePanelAfter,new DateLabelFormatter());
        JDatePickerImpl datePickerBefore = new JDatePickerImpl(datePanelBefore, new DateLabelFormatter());
        
        inputPanel.add(datePickerAfter, inputConstraint);
        inputConstraint.gridx++;
        inputPanel.add(datePickerBefore, inputConstraint);
        
        inputConstraint.gridy++;
        inputConstraint.gridx = 0;
        
        submit = new JButton("Submit");
        inputPanel.add(submit, inputConstraint);
        
        
        mainPanel.add(inputPanel, BorderLayout.NORTH);
    /**inputPanel layout completed*/
    
    /**ouputPanel layout begins*/
        outputPanel = new JPanel();
//	outputPanel.setBackground(Color.lightGray);
        
        
        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportOutput obj = new ReportOutput();
                outputPanel.removeAll();
                if(courtButton.isSelected()){
                    try {
                        outputPanel.add(obj.prepareGUI(username, 0, selectCourt.getSelectedItem().toString(), userGroup));
                        mainPanel.add(outputPanel, BorderLayout.CENTER);
                        System.out.println("Court "+selectCourt.getSelectedItem());
                        outputPanel.validate();
                        mainPanel.validate();
                        return;
                    } catch (SQLException ex) {
                        Logger.getLogger(CaseHistory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(caseNumberButton.isSelected()){
                    try {
                        outputPanel.add(obj.prepareGUI(username, 1, enterCaseNumber.getSelectedItem().toString(), userGroup));
                        mainPanel.add(outputPanel, BorderLayout.CENTER);
                        System.out.println("case number "+enterCaseNumber.getSelectedItem().toString());
                        outputPanel.validate();
                        mainPanel.validate();
                        return;
                    } catch (SQLException ex) {
                        Logger.getLogger(CaseHistory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(fileButton.isSelected()){
                    try {
                        outputPanel.add(obj.prepareGUI(username, 2, enterFileNumber.getSelectedItem().toString(), userGroup));
                        mainPanel.add(outputPanel, BorderLayout.CENTER);
                        System.out.println("file number "+enterFileNumber.getSelectedItem().toString());
                        outputPanel.validate();
                        mainPanel.validate();
                        return;
                    } catch (SQLException ex) {
                        Logger.getLogger(CaseHistory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(lawyerButton.isSelected()){
                    try {
                        outputPanel.add(obj.prepareGUI(username, 3, selectLawyer.getSelectedItem().toString(), userGroup));
                        mainPanel.add(outputPanel, BorderLayout.CENTER);
                        System.out.println("lawyer "+selectLawyer.getSelectedItem());
                        outputPanel.validate();
                        mainPanel.validate();
                        return;
                    } catch (SQLException ex) {
                        Logger.getLogger(CaseHistory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(locationButton.isSelected()){
                    try {
                        outputPanel.add(obj.prepareGUI(username, 4, selectLocation.getSelectedItem().toString(), userGroup));
                        mainPanel.add(outputPanel, BorderLayout.CENTER);
                        System.out.println("Location "+selectLocation.getSelectedItem());
                        outputPanel.validate();
                        mainPanel.validate();
                        return;
                    } catch (SQLException ex) {
                        Logger.getLogger(CaseHistory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(nextHearingButton.isSelected()){
                    try {
                        String after = datePickerAfter.getJFormattedTextField().getText();
                        String before = datePickerBefore.getJFormattedTextField().getText();
                        
                        String dates = after + before;
                        
                        System.out.println(after.substring(8, 10));
                        System.out.println(after.substring(5, 7));
                        System.out.println(after.substring(0, 4));
                        
                        Date afterDate = new Date();
                        afterDate.setDate(Integer.parseInt(after.substring(8, 10)));
                        afterDate.setMonth(Integer.parseInt(after.substring(5, 7)));
                        afterDate.setYear(Integer.parseInt(after.substring(0, 4)));
                        
                        Date beforeDate = new Date();
                        beforeDate.setDate(Integer.parseInt(before.substring(8, 10)));
                        beforeDate.setMonth(Integer.parseInt(before.substring(5, 7)));
                        beforeDate.setYear(Integer.parseInt(before.substring(0, 4)));
                        
                        System.out.println("Date objects "+afterDate.toString()+" "+beforeDate.toString());
                        if(afterDate.after(beforeDate))
                        {
                            JOptionPane.showMessageDialog(new JPanel(), "Check ordering of dates");
                            return;
                        }
                        outputPanel.add(obj.prepareGUI(username, 5, dates, userGroup));
                        mainPanel.add(outputPanel, BorderLayout.CENTER);
                        System.out.println("After "+after+" Before "+before);
                        outputPanel.validate();
                        mainPanel.validate();
                        return;
                    } catch (SQLException ex) {
                        Logger.getLogger(CaseHistory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                JOptionPane.showMessageDialog(new JFrame(), "Select an option and fill the data if required");
            }
        });
    /**outputPanel layout completed*/
    }
}