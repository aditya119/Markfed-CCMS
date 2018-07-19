/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author HP
 */
public class CaseProceeding {
    
    JPanel basePanel, decisionResult;
    UtilDateModel modelProcD, modelNextHD;
    JDatePanelImpl datePanelProceeding, datePanelNextHearing;
    JDatePickerImpl datePickerProceeding, datePickerNextHearing;
    GridBagConstraints constraints;
    JTextArea description;
    JScrollPane scroll;
    JComboBox decision;
    File orderFile = null;
    Label fileName;
    JButton selectFile;
    int proceedingNumber = 0;
    
    JScrollPane prepareGUI() throws ClassNotFoundException, SQLException{
        basePanel = new JPanel(new GridBagLayout());
        decisionResult = new JPanel(new GridBagLayout());
        modelProcD = new UtilDateModel();
        modelNextHD = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        
        datePanelProceeding = new JDatePanelImpl(modelProcD,p);
        datePanelNextHearing = new JDatePanelImpl(modelNextHD,p);
        datePickerProceeding = new JDatePickerImpl(datePanelProceeding,new DateLabelFormatter());
        datePickerNextHearing = new JDatePickerImpl(datePanelNextHearing, new DateLabelFormatter());
        description = new JTextArea(4, 15);
        scroll = new JScrollPane(description, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	//setting scroll pane to show vertical scroll bar as needed	
	description.setLineWrap(true);	//lines will be wrapped if they are too long to fit in allocated space
	description.setWrapStyleWord(true);	//lines will be wrapped at whitespaces if they are too long
        decision = new JComboBox();
        fileName = new Label("No File Selected*");
        selectFile = new JButton("Select File");
//        decision.addItem("ADJOURNMENT");
//        decision.addItem("INTERIM ORDER");
//        decision.addItem("STAY ORDER");
//        decision.addItem("FINAL JUDGEMENT");
//        decision.addItem("PENDING");
//        decision.addItem("DISMISSED");
        
        
        constraints = new GridBagConstraints();
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets.top = 5;
        constraints.insets.bottom = 5;
        constraints.insets.left = 5;
        constraints.insets.right = 8;
        constraints.anchor = GridBagConstraints.WEST;
        
        constraints.gridx=0;
        constraints.gridy++;
        basePanel.add(new JLabel("Proceeding Date*"), constraints);
        constraints.gridx++;
        basePanel.add(datePickerProceeding, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        basePanel.add(new JLabel("Description"), constraints);
        constraints.gridx++;
        basePanel.add(scroll, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        basePanel.add(new JLabel("Decision*"), constraints);
        constraints.gridx++;
        basePanel.add(decision, constraints);
        
//        constraints.gridx=0;
//        constraints.gridy++;
//        basePanel.add(new JLabel("Next Hearing On"), constraints);
//        constraints.gridx++;
//        basePanel.add(datePickerNextHearing, constraints);
        
        DatabaseHandling obj = new DatabaseHandling();
        obj.populateDecisionList(decision);
        decision.setSelectedItem(null);
        
        constraints.gridx=0;
        constraints.gridy++;
        
        basePanel.add(decisionResult, constraints);
        
        decision.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(decision.getSelectedItem().equals("ADJOURNMENT")){
                    decisionResult.removeAll();
                    constraints.gridx=0;
                    constraints.gridy=0;
                    
                    decisionResult.add(new JLabel("Next Hearing On*"), constraints);
                    constraints.gridx++;
                    decisionResult.add(datePickerNextHearing, constraints);
                }
                else if(decision.getSelectedItem().equals("PENDING")){
                    decisionResult.removeAll();
                }
                else if(decision.getSelectedItem().equals("INTERIM ORDER")){
                    decisionResult.removeAll();
                    constraints.gridx=0;
                    constraints.gridy=0;
                    
                    decisionResult.add(new JLabel("Next Hearing On*"), constraints);
                    constraints.gridx++;
                    decisionResult.add(datePickerNextHearing, constraints);
                    
                    constraints.gridx=0;
                    constraints.gridy++;
                    
                    decisionResult.add(fileName, constraints);
                    
                    constraints.gridx++;
//                    constraints.gridy=0;
                    decisionResult.add(selectFile, constraints);
                }
                else{
                    decisionResult.removeAll();
//                    if(orderFile == null){
//                        JFileChooser fc = new JFileChooser();
////                        fc.setAcceptAllFileFilterUsed(false);
////                        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files only", "pdf");
////                        fc.setFileFilter(filter);
//                        int returnVal = fc.showOpenDialog(decisionResult); //Where frame is the parent component
//
////                        File file = null;
//                        if (returnVal == JFileChooser.APPROVE_OPTION) {
//                            orderFile = fc.getSelectedFile();
//                            fileName.setText(orderFile.getName());
//
//
////                            decisionResult.add(new JLabel("File Selected"), constraints);
////                            
////                            constraints.gridx++;
////                            decisionResult.add(fileName, constraints);
//                            //Now you have your file to do whatever you want to do
//                        } else {
//                            JOptionPane.showMessageDialog(new JFrame(), "Select a valid file");
//                            orderFile = null;
//                            fileName.setText("No File Selected");
//
////                            constraints.gridx=0;
////                            constraints.gridy=0;
//                            decisionResult.add(new JLabel(), constraints);
//                            //User did not choose a valid file
//                        }
//                    }
                    constraints.gridx=0;
                    constraints.gridy=0;
                    decisionResult.add(fileName, constraints);
                    
                    constraints.gridx++;
                    constraints.gridy=0;
                    decisionResult.add(selectFile, constraints);
                }
                decisionResult.validate();
                basePanel.validate();
            }
        });
        
        selectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
//                fc.setAcceptAllFileFilterUsed(false);
//                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files only", "pdf");
//                fc.setFileFilter(filter);
                int returnVal = fc.showOpenDialog(decisionResult); //Where frame is the parent component

//                File file = null;
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    orderFile = fc.getSelectedFile();
                    fileName.setText(orderFile.getName());


//                    decisionResult.add(new JLabel("File Selected"), constraints);
//                    constraints.gridx++;
                    //Now you have your file to do whatever you want to do
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Select a valid file");
                    orderFile = null;
                    fileName.setText("No File Selected");
                    //User did not choose a valid file
                }
                decisionResult.validate();
                basePanel.validate();
            }    
        });
        JScrollPane scroll = new JScrollPane(basePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return scroll;
    }
}
