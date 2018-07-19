/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JScrollPane;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;


/**
 *
 * @author HP
 */
public class MainPage {

    JFrame frame;
    JPanel basePanel;
    JPanel rootPanel;// a lot of work has to be done on this....
    JPanel fileNumberPane, paneForTabbedPane;
    JPanel panelRootNorth, panelForButton, panelRootCenter;
    JPanel southPanelForButton;
    JPanel titlePanel, caseDetailsPanel, importantDates, caseSummary, keyOrderDetail, offFilePanel;
    //JPanel panelForFileNumber;//on the north of paneForTabbedPane.... 
    JButton newEntryButton, searchButton, addCourt, addLawyer, addCaseType, addLocation, saveButton, editButton, updateButton;
    JLabel courtSelectCD, caseTypeCD, caseNumberCD, yearCD, lawyerCD, locationCD;
    JComboBox courtList, caseTypeList, lawyerField, location;
    JTextField caseNumber, yearField;
    GridBagConstraints constraintForRoot, constraintForCDetail, constraintForDetailPane;
    GridBagConstraints constraintForImpDatePane, constraintsForSummary;
//    GridBagConstraints constraintForPetiPane,constraintForResPane;
    BorderLayout rootBorder, panelRootNorthBorder;
    JTabbedPane tabbedPane;
    JPanel detailPane, petitionerPane, documentPane, caseProceedingPane;
    JPanel paneOnDetail;
    JTextField fileNumberField;
    JComboBox appealNumber;
    JScrollPane  mainScrollPane;

    JButton addPR,editPR,cancelPR;
    JPanel PRSouth;
    
    JTextField courtName;
    JPanel documentSouth;
    JButton addDoc,editDoc,cancelDoc;

    
    DetailPane objDetailPane;
    PetitionerPane objPetiPane;
    CaseProceeding objCaseProc;
//    JLabel 
    // Components For Add petioner/respondent..

    JPanel prepareGUI(String username, String userGroup) throws ClassNotFoundException, SQLException {

        basePanel = new JPanel(new BorderLayout());

        rootBorder = new BorderLayout();
        panelRootNorthBorder = new BorderLayout();
        objDetailPane = new DetailPane();
        objPetiPane = new PetitionerPane();
        objCaseProc = new CaseProceeding();
//        frame = new JFrame();
//        
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Toolkit toolkit= frame.getToolkit();
//        Dimension size = toolkit.getScreenSize();
//        frame.setSize(size.width,size.height);
//        frame.setLocation(size.width/2-frame.getWidth()/2,size.height/2-frame.getHeight()/2);
//        // frame.setResizable(false);
        southPanelForButton = new JPanel();
        rootPanel = new JPanel(rootBorder); //    ROOT PANEL MANIPULATION ... :) 
        panelRootNorth = new JPanel(panelRootNorthBorder);
        panelRootCenter = new JPanel(new BorderLayout());
        fileNumberPane = new JPanel();
        fileNumberPane.setBorder(BorderFactory.createLineBorder(Color.black));
        fileNumberPane.setBackground(Color.ORANGE);
        panelForButton = new JPanel(new FlowLayout());
        panelForButton.setBackground(Color.ORANGE);
        panelForButton.setBorder(BorderFactory.createLineBorder(Color.black));
        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        detailPane = new JPanel(new BorderLayout());
        caseDetailsPanel = new JPanel(new GridBagLayout());
        
        documentPane = new JPanel(new BorderLayout());
        caseProceedingPane = new JPanel(new BorderLayout());
        paneForTabbedPane = new JPanel(new BorderLayout());

        saveButton = new JButton("Save");
//        editButton = new JButton("Edit");
        updateButton = new JButton("Update");
        fileNumberField = new JTextField(20);

        appealNumber = new JComboBox();
        appealNumber.addItem("0");
        appealNumber.addItem("1");
        appealNumber.addItem("2");
        appealNumber.addItem("3");
        appealNumber.addItem("4");
        appealNumber.addItem("5");
        appealNumber.addItem("6");
        appealNumber.addItem("7");
        appealNumber.addItem("8");
        appealNumber.addItem("9");
        appealNumber.addItem("10");

        petitionerPane = new JPanel(new BorderLayout());
 //
        mainScrollPane = new JScrollPane(objDetailPane.addDetailPane(), VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_ALWAYS);
        mainScrollPane.setWheelScrollingEnabled(true);         //detailPane.add(mainScrollPane);

//        addPR = new JButton("Add");
//        editPR = new JButton("Edit");
//        cancelPR = new JButton("Cancel");
//        PRSouth = new JPanel();
//        
//        addDoc = new JButton("Upload");
//        editDoc = new JButton("Edit");
//        cancelDoc = new JButton("Cancel");
//        documentSouth = new JPanel();
        /*
         *
         * This space is for GRIDBAGCONSTRAINTS ....
         */
        constraintForCDetail = new GridBagConstraints();
        constraintForCDetail.gridheight = 1;
        constraintForCDetail.gridwidth = 1;
        constraintForCDetail.gridx = 0;
        constraintForCDetail.gridy = 0;
        constraintForCDetail.insets.top = 5;
        constraintForCDetail.insets.bottom = 5;
        constraintForCDetail.insets.left = 5;
        constraintForCDetail.insets.right = 8;
        constraintForCDetail.anchor = GridBagConstraints.WEST;        

        courtSelectCD = new JLabel("Select Court*");
        caseTypeCD = new JLabel("Select Case type*");
        caseNumberCD = new JLabel("Case Number*");
        yearCD = new JLabel("Year*");
        locationCD = new JLabel("Location*");
        lawyerCD = new JLabel("Lawyer*");
        
        courtList = new JComboBox();
        caseTypeList = new JComboBox();
        yearField = new JTextField(10);
        location = new JComboBox();
        lawyerField = new JComboBox();
        
        DatabaseHandling object = new DatabaseHandling();
        object.populateCourtsList(courtList);

        object.populateCaseTypeList(caseTypeList);
        
        object.populateLocationList(location);
        
        object.populateLawyerList(lawyerField);

//        object.populateYearList(yearList);

        caseNumber = new JTextField(10);

       // newEntryButton = new JButton("New");
        searchButton = new JButton("Search");
        
        addCourt = new JButton("Add Court");
        addLocation = new JButton("Add Location");
        addLawyer = new JButton("Add Lawyer");
        addCaseType = new JButton("Add Case Type");
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        caseDetailsPanel.add(courtSelectCD, constraintForCDetail);
        constraintForCDetail.gridx++;

        caseDetailsPanel.add(courtList, constraintForCDetail);
        
        if(userGroup.equals("Administrator")){
            constraintForCDetail.gridx += 2;
            caseDetailsPanel.add(addCourt, constraintForCDetail);
        }
        
        constraintForCDetail.gridx = 0;
        constraintForCDetail.gridy++;

        caseDetailsPanel.add(caseTypeCD, constraintForCDetail);
        constraintForCDetail.gridx++;
        caseDetailsPanel.add(caseTypeList, constraintForCDetail);
        if(userGroup.equals("Administrator")){
            constraintForCDetail.gridx += 2;
            caseDetailsPanel.add(addCaseType, constraintForCDetail);
        }
        
        constraintForCDetail.gridx = 0;
        constraintForCDetail.gridy++;
        
        caseDetailsPanel.add(locationCD, constraintForCDetail);
        constraintForCDetail.gridx++;
        caseDetailsPanel.add(location, constraintForCDetail);        
        if(userGroup.equals("Administrator")){
            constraintForCDetail.gridx += 2;
            caseDetailsPanel.add(addLocation, constraintForCDetail);
        }
        
        constraintForCDetail.gridx = 0;
        constraintForCDetail.gridy++;

        caseDetailsPanel.add(caseNumberCD, constraintForCDetail);
        constraintForCDetail.gridx++;
        caseDetailsPanel.add(caseNumber, constraintForCDetail);
        constraintForCDetail.gridx = 0;
        constraintForCDetail.gridy++;

        caseDetailsPanel.add(yearCD, constraintForCDetail);
        constraintForCDetail.gridx++;
        caseDetailsPanel.add(yearField, constraintForCDetail);
        constraintForCDetail.gridx = 0;
        constraintForCDetail.gridy++;

        caseDetailsPanel.add(lawyerCD, constraintForCDetail);
        constraintForCDetail.gridx++;
        caseDetailsPanel.add(lawyerField, constraintForCDetail);
        if(userGroup.equals("Administrator")){
            constraintForCDetail.gridx += 2;
            caseDetailsPanel.add(addLawyer, constraintForCDetail);
        }
        constraintForCDetail.gridx = 0;
        constraintForCDetail.gridy++;
        
        // addition of items ... 
        //frame.add(basePanel);
        basePanel.add(rootPanel);
        rootPanel.add(panelRootNorth, BorderLayout.NORTH);
        rootPanel.add(panelRootCenter, BorderLayout.CENTER);

        panelRootCenter.add(paneForTabbedPane, BorderLayout.CENTER);

        paneForTabbedPane.add(tabbedPane);
        basePanel.add(fileNumberPane, BorderLayout.NORTH);
        fileNumberPane.add(new JLabel("File Number*"));
        fileNumberPane.add(fileNumberField);
//        ArrayList<String> fileNumberList = new DatabaseHandling().getFileNumberList();
//        AutoSuggestor suggestor = new AutoSuggestor(fileNumberField, new LoginHome().mainFrame, fileNumberList, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f);
        fileNumberPane.add(new JLabel("Appeal Number"));
        fileNumberPane.add(appealNumber);
        //paneForTabbedPane.add(southPanelForButton)

        panelRootNorth.add(caseDetailsPanel, BorderLayout.WEST);
        panelRootNorth.add(panelForButton, BorderLayout.SOUTH);
        panelForButton.add(searchButton);
       // panelForButton.add(newEntryButton);

      //  detailPane.add(paneOnDetail);
        /*
         * ----- Now its time to add the tabbed panes on the SOUTH SIDE OF THE
         * ROOT PANEL
         */
        
//        tabbedPane.addTab("Detail Pane", detailPane);
//        tabbedPane.addTab("Add Petitioner/Respondent Data", petitionerPane);
//        tabbedPane.addTab("Add Document Data", documentPane);
//        tabbedPane.addTab("Add Case Proceeding Data", caseProceedingPane);

        
        
        
        detailPane.add(mainScrollPane, BorderLayout.CENTER);
        paneForTabbedPane.add(southPanelForButton, BorderLayout.SOUTH);
//        southPanelForButton.add(saveButton);
////        southPanelForButton.add(editButton);
//        southPanelForButton.add(updateButton);

        //PRSouth.add(addPR);
        //PRSouth.add(editPR);
        //PRSouth.add(cancelPR);
//        petitionerPane.add(objPetiPane.petitionerTabbedPane(),BorderLayout.CENTER);
       // petitionerPane.add(PRSouth,BorderLayout.SOUTH);
        
        
       // documentSouth.add(addDoc);
        //documentSouth.add(editDoc);
        //documentSouth.add(cancelDoc);
//        documentPane.add(new DocumntPanel().documentTabbedPane(),BorderLayout.CENTER);
        //documentPane.add(documentSouth,BorderLayout.SOUTH);
        
        caseProceedingPane.add(objCaseProc.prepareGUI(),BorderLayout.CENTER);
        //caseProceedingPane.add(documentSouth,BorderLayout.SOUTH);
        
        
        /*
         * The Below space is alloted for the actionListeners
         *
         *
         */
        if(userGroup.equals("Administrator")){
            addCourt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String court = (String) JOptionPane.showInputDialog(basePanel,"Enter Name","Add Court",INFORMATION_MESSAGE).toUpperCase();
                    courtList.setSelectedIndex(-1);
                    courtList.setSelectedItem(court);
                    if(courtList.getSelectedIndex()>-1){
                       JOptionPane.showMessageDialog(new JFrame(), "Court name already exists");
                    }
                    else{
                        try {
                            if(!court.isEmpty()){
                                courtList.addItem(court);
                                object.addCourt(court);
                            }
                            else{
                            }
                            courtList.setSelectedItem("NONE");
                        } catch (SQLException ex) {
                            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }   
                }
            });
            
            addCaseType.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String caseType = (String) JOptionPane.showInputDialog(basePanel,"Enter Name","Add Case Type",INFORMATION_MESSAGE).toUpperCase();
                    caseTypeList.setSelectedIndex(-1);
                    caseTypeList.setSelectedItem(caseType);
                    if(caseTypeList.getSelectedIndex()>-1){
                       JOptionPane.showMessageDialog(new JFrame(), "Case Type already exists");
                    }
                    else{
                        try {
                            if(!caseType.isEmpty()){
                                caseTypeList.addItem(caseType);
                                object.addCaseType(caseType);
                            }
                            else{
                            }
                            caseTypeList.setSelectedItem("NONE");
                        } catch (SQLException ex) {
                            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }   
                }
            });
            
            addLocation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String locationName = (String) JOptionPane.showInputDialog(basePanel,"Enter Location","Add Location",INFORMATION_MESSAGE).toUpperCase();
                    location.setSelectedIndex(-1);
                    location.setSelectedItem(location);
                    if(location.getSelectedIndex()>-1){
                       JOptionPane.showMessageDialog(new JFrame(), "Location name already exists");
                    }
                    else{
                        try {
                            if(!locationName.isEmpty()){
                                location.addItem(locationName);
                                object.addLocation(locationName);
                            }
                            else{
                            }
                            location.setSelectedItem("NONE");
                        } catch (SQLException ex) {
                            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }   
                }
            });
            
            addLawyer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel comboPanel = new JPanel();
                    comboPanel.setLayout(new GridLayout(2, 2));
                    
                    JTextField email = new JTextField();
                    
                    comboPanel.add(new JLabel("Enter e-Mail"));
                    comboPanel.add(email);
                    
                    comboPanel.add(new JLabel("Enter Name"));
                    
                    String lawyer = (String)JOptionPane.showInputDialog(basePanel,comboPanel,"Add Lawyer",JOptionPane.INFORMATION_MESSAGE).toUpperCase();
                    
                    lawyerField.setSelectedIndex(-1);
                    lawyerField.setSelectedItem(lawyer);
                    if(lawyerField.getSelectedIndex()>-1){
                       JOptionPane.showMessageDialog(new JFrame(), "Lawyer name already exists");
                    }
                    else if(email.getText().isEmpty()){
                        JOptionPane.showMessageDialog(new JFrame(), "Enter E-Mail");
                    }
                    else{
                        try {
                            if(!lawyer.isEmpty()){
                                lawyerField.addItem(lawyer);
                                System.out.println(lawyer+" "+email.getText());
                                object.addLawyer(lawyer, email.getText());
                            }
                            else{
                            }
                            lawyerField.setSelectedItem("NONE");
                        } catch (SQLException ex) {
                            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }   
                }
            });
        }
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileNumberField.getText().isEmpty() || caseNumber.getText().isEmpty() || yearField.getText().isEmpty() || lawyerField.getSelectedItem().equals(null) || objPetiPane.petitionerName.getText().isEmpty()
                    || objPetiPane.petitionerAddress.getText().isEmpty() || objPetiPane.respondentName.getText().isEmpty() || objPetiPane.respondentAddress.getText().isEmpty()
                    || objCaseProc.datePickerProceeding.getJFormattedTextField().getText().isEmpty() /*|| objDetailPane.datePickerCaseFiledOn.getJFormattedTextField().getText().isEmpty()
                    || objDetailPane.datePickerFirstHearingField.getJFormattedTextField().getText().isEmpty() || objDetailPane.datePickerNoticeReceivedOn.getJFormattedTextField().getText().isEmpty()*/){
                    JOptionPane.showMessageDialog(new JFrame(), "Fill all the Text Fields");
                    return;
                }
                
                if(objCaseProc.decision.getSelectedItem().equals("ADJOURNMENT") && objCaseProc.decision.getSelectedItem().equals("INTERIM ORDER") && objCaseProc.datePickerNextHearing.getJFormattedTextField().getText().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(), "Next Hearing Date has to be filled when decision is ADJOURNMENT and INTERIM ORDER");
                    return;
                }
                if(!objCaseProc.decision.getSelectedItem().equals("ADJOURNMENT") && !objCaseProc.decision.getSelectedItem().equals("PENDING") && objCaseProc.orderFile == null){
                    JOptionPane.showMessageDialog(new JFrame(), "Order File has to be selected for INTERIM ORDER and FINAL JUDGEMENT");
                    return;
                }
                
                /**checking whether dates are genuine*/
                if(!(objDetailPane.datePickerCaseFiledOn.getJFormattedTextField().getText().isEmpty() || objDetailPane.datePickerFirstHearingField.getJFormattedTextField().getText().isEmpty()
                        || objDetailPane.datePickerNoticeReceivedOn.getJFormattedTextField().getText().isEmpty())){
                    Date caseFiled = new Date();
                    caseFiled.setDate(objDetailPane.datePickerCaseFiledOn.getModel().getDay());
                    caseFiled.setMonth(objDetailPane.datePickerCaseFiledOn.getModel().getMonth());
                    caseFiled.setYear(objDetailPane.datePickerCaseFiledOn.getModel().getYear());
                    System.out.println(caseFiled.toString());

                    Date noticeRecieved = new Date();
                    noticeRecieved.setDate(objDetailPane.datePickerNoticeReceivedOn.getModel().getDay());
                    noticeRecieved.setMonth(objDetailPane.datePickerNoticeReceivedOn.getModel().getMonth());
                    noticeRecieved.setYear(objDetailPane.datePickerNoticeReceivedOn.getModel().getYear());
                    System.out.println(noticeRecieved.toString());

                    Date firstHearing = new Date();
                    firstHearing.setDate(objDetailPane.datePickerFirstHearingField.getModel().getDay());
                    firstHearing.setMonth(objDetailPane.datePickerFirstHearingField.getModel().getMonth());
                    firstHearing.setYear(objDetailPane.datePickerFirstHearingField.getModel().getYear());
                    System.out.println(firstHearing.toString());

                    Date proceeding = new Date();
                    proceeding.setDate(objCaseProc.datePickerProceeding.getModel().getDay());
                    proceeding.setMonth(objCaseProc.datePickerProceeding.getModel().getMonth());
                    proceeding.setYear(objCaseProc.datePickerProceeding.getModel().getYear());
                    System.out.println(proceeding.toString());

                    if(caseFiled.after(noticeRecieved) || noticeRecieved.after(firstHearing) || caseFiled.after(firstHearing) || proceeding.before(firstHearing)){
                        JOptionPane.showMessageDialog(new JFrame(), "Case can't be filed after notice recieved and/or first hearing\nNotice can't be recieved after first hearing\nProceeding can't be before First hearing");
                        return;
                    }
                }
                /**date checking complete*/
                
                if(yearField.getText().length() != 4){
                    JOptionPane.showMessageDialog(new JFrame(), "YEAR entered is incorrect");
                    return;
                }
                
                String fileNumber = fileNumberField.getText();
                
                if(appealNumber.getSelectedItem().equals("1")){
                    fileNumber = fileNumber + "-appeal-1";
                }
                else if(appealNumber.getSelectedItem().equals("2")){
                    fileNumber = fileNumber + "-appeal-2";
                }
                else if(appealNumber.getSelectedItem().equals("3")){
                    fileNumber = fileNumber + "-appeal-3";
                }
                else if(appealNumber.getSelectedItem().equals("4")){
                    fileNumber = fileNumber + "-appeal-4";
                }
                else if(appealNumber.getSelectedItem().equals("5")){
                    fileNumber = fileNumber + "-appeal-5";
                }
                else if(appealNumber.getSelectedItem().equals("6")){
                    fileNumber = fileNumber + "-appeal-6";
                }
                else if(appealNumber.getSelectedItem().equals("7")){
                    fileNumber = fileNumber + "-appeal-7";
                }
                else if(appealNumber.getSelectedItem().equals("8")){
                    fileNumber = fileNumber + "-appeal-8";
                }
                else if(appealNumber.getSelectedItem().equals("9")){
                    fileNumber = fileNumber + "-appeal-9";
                }
                else if(appealNumber.getSelectedItem().equals("10")){
                    fileNumber = fileNumber + "-appeal-10";
                }
                System.out.println(fileNumber);
                DatabaseHandling obj = new DatabaseHandling();
                try {
                    obj.updateLog(username, userGroup, fileNumber, "insert");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                RetrieveCaseInfoClass caseInfo = new RetrieveCaseInfoClass();
                //case requisites.....
                caseInfo.caseNumber = caseNumber.getText().toUpperCase();
                caseInfo.caseTypeList = caseTypeList.getSelectedItem().toString();
                caseInfo.courtList = courtList.getSelectedItem().toString();
                caseInfo.fileNumberField = fileNumber;
                caseInfo.locationList = location.getSelectedItem().toString();
                caseInfo.yearList = yearField.getText();
                caseInfo.lawyer = lawyerField.getSelectedItem().toString().toUpperCase();

                //Dates.....
                if(!objDetailPane.datePickerCaseFiledOn.getJFormattedTextField().getText().isEmpty()){
                    caseInfo.datePickerCaseFiledOn = objDetailPane.datePickerCaseFiledOn.getJFormattedTextField().getText();
                }
                else{
                    caseInfo.datePickerCaseFiledOn = "1947-08-15";
                }
                if(!objDetailPane.datePickerFirstHearingField.getJFormattedTextField().getText().isEmpty()){
                    caseInfo.datePickerFirstHearingField = objDetailPane.datePickerFirstHearingField.getJFormattedTextField().getText();
                }
                else{
                    caseInfo.datePickerFirstHearingField = "1947-08-15";
                }
                if(!objDetailPane.datePickerNoticeReceivedOn.getJFormattedTextField().getText().isEmpty()){
                    caseInfo.datePickerNoticeReceivedOn = objDetailPane.datePickerNoticeReceivedOn.getJFormattedTextField().getText();
                }
                else{
                    caseInfo.datePickerNoticeReceivedOn = "1947-08-15";
                }
//                caseInfo.datePickerNextHearingField = objDetailPane.datePickerNextHearingField.getJFormattedTextField().getText();
//                caseInfo.summaryInPrayer = objDetailPane.summaryInPrayer.getText();
//                caseInfo.generalRemarks = objDetailPane.generalRemarks.getText();
//                caseInfo.otherLinks = objDetailPane.otherLinks.getText();


                // Petitioner Info....               
                caseInfo.petitionerName = objPetiPane.petitionerName.getText().toUpperCase();
                caseInfo.petitionerEmail = objPetiPane.petitionerEmail.getText();
                caseInfo.petitionerAddress = objPetiPane.petitionerAddress.getText().toUpperCase();
                
                if(objPetiPane.petitionerFile == null){
                    caseInfo.petitionerFile = null;
                    caseInfo.petitionerFileName = null;
                }else{
                    caseInfo.petitionerFile = objPetiPane.petitionerFile;
                    caseInfo.petitionerFileName = objPetiPane.petitionerFileName.getText().toUpperCase();
                }
                caseInfo.respondentName = objPetiPane.respondentName.getText().toUpperCase();
                caseInfo.respondentEmail = objPetiPane.respondentEmail.getText();
                caseInfo.respondentAddress = objPetiPane.respondentAddress.getText().toUpperCase();
                
                if(objPetiPane.respondentFile == null){
                    caseInfo.respondentFile = null;
                    caseInfo.respondentFileName = null;
                }else{
                    caseInfo.respondentFile = objPetiPane.respondentFile;
                    caseInfo.respondentFileName = objPetiPane.respondentFileName.getText().toUpperCase();
                }
                
                // Case Proceeding ...
                caseInfo.decision = objCaseProc.decision.getSelectedItem().toString();
                caseInfo.description = objCaseProc.description.getText().toUpperCase();
                caseInfo.datePickerProceeding = objCaseProc.datePickerProceeding.getJFormattedTextField().getText();
                switch (caseInfo.decision) {
                    case "ADJOURNMENT":
                        caseInfo.orderFile = null;
                        caseInfo.orderFileName = null;
                        caseInfo.datePickerNextHearingField = objCaseProc.datePickerNextHearing.getJFormattedTextField().getText();
                        break;
                    case "INTERIM ORDER":
                        caseInfo.orderFile = objCaseProc.orderFile;
                        caseInfo.orderFileName = objCaseProc.fileName.getText();
                        caseInfo.datePickerNextHearingField = objCaseProc.datePickerNextHearing.getJFormattedTextField().getText();
                        break;
                    case "FINAL JUDGEMENT":
                        caseInfo.orderFile = objCaseProc.orderFile;
                        caseInfo.orderFileName = objCaseProc.fileName.getText();
                        caseInfo.datePickerNextHearingField = null;
                        break;
                    case "PENDING":
                        caseInfo.orderFile = null;
                        caseInfo.orderFileName = null;
                        caseInfo.datePickerNextHearingField = null;
                        break;
                    default:
                        break;
                }
                if(JOptionPane.showConfirmDialog(new JPanel(), "Case Proceeding data cannot be corrected\nAre you sure about the data?") != 0){
                    return;
                }
                try {
                    obj.insertCaseInfo(caseInfo);
                } catch (SQLException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } finally {

                }               
            }
        });
        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileNumberField.getText().isEmpty() || caseNumber.getText().isEmpty() || yearField.getText().isEmpty() || lawyerField.getSelectedItem().equals(null) || objPetiPane.petitionerName.getText().isEmpty()
                    || objPetiPane.petitionerAddress.getText().isEmpty() || objPetiPane.respondentName.getText().isEmpty() || objPetiPane.respondentAddress.getText().isEmpty()
                    || objCaseProc.datePickerProceeding.getJFormattedTextField().getText().isEmpty() /*|| objDetailPane.datePickerCaseFiledOn.getJFormattedTextField().getText().isEmpty()
                    || objDetailPane.datePickerFirstHearingField.getJFormattedTextField().getText().isEmpty() || objDetailPane.datePickerNoticeReceivedOn.getJFormattedTextField().getText().isEmpty()*/){
                    JOptionPane.showMessageDialog(new JFrame(), "Fill all the Text Fields");
                    return;
                }
                
                RetrieveCaseInfoClass previous = new RetrieveCaseInfoClass();
                
                String fileNumber = fileNumberField.getText();
                if(appealNumber.getSelectedItem().equals("1")){
                    fileNumber = fileNumber + "-appeal-1";
                }
                else if(appealNumber.getSelectedItem().equals("2")){
                    fileNumber = fileNumber + "-appeal-2";
                }
                else if(appealNumber.getSelectedItem().equals("3")){
                    fileNumber = fileNumber + "-appeal-3";
                }
                else if(appealNumber.getSelectedItem().equals("4")){
                    fileNumber = fileNumber + "-appeal-4";
                }
                else if(appealNumber.getSelectedItem().equals("5")){
                    fileNumber = fileNumber + "-appeal-5";
                }
                else if(appealNumber.getSelectedItem().equals("6")){
                    fileNumber = fileNumber + "-appeal-6";
                }
                else if(appealNumber.getSelectedItem().equals("7")){
                    fileNumber = fileNumber + "-appeal-7";
                }
                else if(appealNumber.getSelectedItem().equals("8")){
                    fileNumber = fileNumber + "-appeal-8";
                }
                else if(appealNumber.getSelectedItem().equals("9")){
                    fileNumber = fileNumber + "-appeal-9";
                }
                else if(appealNumber.getSelectedItem().equals("10")){
                    fileNumber = fileNumber + "-appeal-10";
                }
                System.out.println(fileNumber);
                
                try {
                    DatabaseHandling object = new DatabaseHandling();
                    previous = object.getPreviousProceedingData(fileNumber);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(previous.decision.equals(objCaseProc.decision.getSelectedItem().toString()) && previous.datePickerProceeding.equals(objCaseProc.datePickerProceeding.getJFormattedTextField().getText())){
                    System.out.println(previous.decision+" check1 "+objCaseProc.decision.getSelectedItem().toString());
                    System.out.println(previous.datePickerProceeding+" check1 "+objCaseProc.datePickerProceeding.getJFormattedTextField().getText());
                    System.out.println("Same proceeding data, other data will be updated");
                }
                else{
                    if(objCaseProc.decision.getSelectedItem().equals("ADJOURNMENT") && objCaseProc.decision.getSelectedItem().equals("INTERIM ORDER") && objCaseProc.datePickerNextHearing.getJFormattedTextField().getText().isEmpty()){
                        JOptionPane.showMessageDialog(new JFrame(), "Next Hearing Date has to be filled when decision is ADJOURNMENT and INTERIM ORDER");
                        return;
                    }
                    if(!objCaseProc.decision.getSelectedItem().equals("ADJOURNMENT") && !objCaseProc.decision.getSelectedItem().equals("PENDING") && objCaseProc.orderFile == null){
                        JOptionPane.showMessageDialog(new JFrame(), "Order File has to be selected for INTERIM ORDER and FINAL JUDGEMENT");
                        return;
                    }
                    System.out.println("check pending\n...");
                    if(objCaseProc.decision.getSelectedItem().equals("PENDING")){
                        JOptionPane.showMessageDialog(new JPanel(), "Decision can't be PENDING except for first proceeding");
                        return;
                    }
                    System.out.println("not pending");
                }
                
                /**checking whether dates are genuine*/
//                Date caseFiled = new Date();
//                caseFiled.setDate(objDetailPane.datePickerCaseFiledOn.getModel().getDay());
//                caseFiled.setMonth(objDetailPane.datePickerCaseFiledOn.getModel().getMonth());
//                caseFiled.setYear(objDetailPane.datePickerCaseFiledOn.getModel().getYear());
//                System.out.println("case filed on "+caseFiled.toString()+" "+objDetailPane.datePickerCaseFiledOn.getJFormattedTextField().getText());
//                
//                Date noticeRecieved = new Date();
//                noticeRecieved.setDate(objDetailPane.datePickerNoticeReceivedOn.getModel().getDay());
//                noticeRecieved.setMonth(objDetailPane.datePickerNoticeReceivedOn.getModel().getMonth());
//                noticeRecieved.setYear(objDetailPane.datePickerNoticeReceivedOn.getModel().getYear());
//                System.out.println("notice recieved on "+noticeRecieved.toString());
//                
//                Date firstHearing = new Date();
//                firstHearing.setDate(objDetailPane.datePickerFirstHearingField.getModel().getDay());
//                firstHearing.setMonth(objDetailPane.datePickerFirstHearingField.getModel().getMonth());
//                firstHearing.setYear(objDetailPane.datePickerFirstHearingField.getModel().getYear());
//                System.out.println("first hearing on "+firstHearing.toString());
//                
//                Date proceeding = new Date();
//                proceeding.setDate(objCaseProc.datePickerProceeding.getModel().getDay());
//                proceeding.setMonth(objCaseProc.datePickerProceeding.getModel().getMonth());
//                proceeding.setYear(objCaseProc.datePickerProceeding.getModel().getYear());
//                System.out.println("proceeding on "+proceeding.toString());
//                
//                if(caseFiled.after(noticeRecieved) || noticeRecieved.after(firstHearing) || caseFiled.after(firstHearing) || proceeding.before(firstHearing)){
//                    JOptionPane.showMessageDialog(new JFrame(), "Case can't be filed after notice recieved and/or first hearing\nNotice can't be recieved after first hearing\nProceeding can't be before First hearing");
//                    return;
//                }
                /**date checking complete*/
                if(yearField.getText().length() != 4){
                    JOptionPane.showMessageDialog(new JFrame(), "YEAR entered is incorrect");
                    return;
                }
                DatabaseHandling obj = new DatabaseHandling();
                
                try {
                    obj.updateLog(username, userGroup, fileNumber, "update");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }

                RetrieveCaseInfoClass caseInfo = new RetrieveCaseInfoClass();
                //case requisites.....
                caseInfo.caseNumber = caseNumber.getText().toUpperCase();
                caseInfo.caseTypeList = caseTypeList.getSelectedItem().toString();
                caseInfo.courtList = courtList.getSelectedItem().toString();
                caseInfo.fileNumberField = fileNumber;
                caseInfo.yearList = yearField.getText();
                caseInfo.locationList = location.getSelectedItem().toString();
                caseInfo.lawyer = lawyerField.getSelectedItem().toString().toUpperCase();

                //Dates.....
                if(!objDetailPane.datePickerCaseFiledOn.getJFormattedTextField().getText().isEmpty()){
                    caseInfo.datePickerCaseFiledOn = objDetailPane.datePickerCaseFiledOn.getJFormattedTextField().getText();
                }
                else{
                    caseInfo.datePickerCaseFiledOn = "1947-08-15";
                }
                if(!objDetailPane.datePickerFirstHearingField.getJFormattedTextField().getText().isEmpty()){
                    caseInfo.datePickerFirstHearingField = objDetailPane.datePickerFirstHearingField.getJFormattedTextField().getText();
                }
                else{
                    caseInfo.datePickerFirstHearingField = "1947-08-15";
                }
                if(!objDetailPane.datePickerNoticeReceivedOn.getJFormattedTextField().getText().isEmpty()){
                    caseInfo.datePickerNoticeReceivedOn = objDetailPane.datePickerNoticeReceivedOn.getJFormattedTextField().getText();
                }
                else{
                    caseInfo.datePickerNoticeReceivedOn = "1947-08-15";
                }
//                caseInfo.datePickerCaseFiledOn = objDetailPane.datePickerCaseFiledOn.getJFormattedTextField().getText();
//                caseInfo.datePickerFirstHearingField = objDetailPane.datePickerFirstHearingField.getJFormattedTextField().getText();
//                caseInfo.datePickerNoticeReceivedOn = objDetailPane.datePickerNoticeReceivedOn.getJFormattedTextField().getText();
//                caseInfo.datePickerNextHearingField = objDetailPane.datePickerNextHearingField.getJFormattedTextField().getText();
//                caseInfo.summaryInPrayer = objDetailPane.summaryInPrayer.getText();
//                caseInfo.generalRemarks = objDetailPane.generalRemarks.getText();
//                caseInfo.otherLinks = objDetailPane.otherLinks.getText();


                // Petitioner Info....               
                caseInfo.petitionerName = objPetiPane.petitionerName.getText().toUpperCase();
                caseInfo.petitionerEmail = objPetiPane.petitionerEmail.getText();
                caseInfo.petitionerAddress = objPetiPane.petitionerAddress.getText().toUpperCase();
                caseInfo.petitionerFileName = objPetiPane.petitionerFileName.getText().toUpperCase();
                caseInfo.petitionerFile = objPetiPane.petitionerFile;
                caseInfo.respondentName = objPetiPane.respondentName.getText().toUpperCase();
                caseInfo.respondentEmail = objPetiPane.respondentEmail.getText();
                caseInfo.respondentAddress = objPetiPane.respondentAddress.getText().toUpperCase();
                caseInfo.respondentFileName = objPetiPane.respondentFileName.getText().toUpperCase();
                caseInfo.respondentFile = objPetiPane.respondentFile;
                
                // Case Proceeding ...
                caseInfo.decision = objCaseProc.decision.getSelectedItem().toString();
                caseInfo.description = objCaseProc.description.getText().toUpperCase();
                caseInfo.datePickerProceeding = objCaseProc.datePickerProceeding.getJFormattedTextField().getText();
                caseInfo.proceedingNumber = objCaseProc.proceedingNumber + 1;
                switch (caseInfo.decision) {
                    case "ADJOURNMENT":
                        caseInfo.orderFile = null;
                        caseInfo.orderFileName = null;
                        caseInfo.datePickerNextHearingField = objCaseProc.datePickerNextHearing.getJFormattedTextField().getText();
                        break;
                    case "INTERIM ORDER":
                        caseInfo.orderFile = objCaseProc.orderFile;
                        caseInfo.orderFileName = objCaseProc.fileName.getText();
                        caseInfo.datePickerNextHearingField = objCaseProc.datePickerNextHearing.getJFormattedTextField().getText();
                        break;
                    case "FINAL JUDGEMENT":
                        caseInfo.orderFile = objCaseProc.orderFile;
                        caseInfo.orderFileName = objCaseProc.fileName.getText();
                        caseInfo.datePickerNextHearingField = null;
                        break;
                    default:
                        break;
                }
                if(JOptionPane.showConfirmDialog(new JPanel(), "Case Proceeding data cannot be corrected\nAre you sure about the data?") != 0){
                    return;
                }
                try {
                    obj.updateCaseInfo(caseInfo);
                } catch (SQLException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } finally {

                }               
            }
        });
        
        searchButton.addActionListener(new ActionListener() {
            // fetching data from the database
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DatabaseHandling obj = new DatabaseHandling();
                    String fileNumber = fileNumberField.getText();
                    int appeal = 0;
                    if(appealNumber.getSelectedItem().equals("1")){
                    fileNumber = fileNumber + "-appeal-1";
                    }
                    else if(appealNumber.getSelectedItem().equals("2")){
                        fileNumber = fileNumber + "-appeal-2";
                    }
                    else if(appealNumber.getSelectedItem().equals("3")){
                        fileNumber = fileNumber + "-appeal-3";
                    }
                    else if(appealNumber.getSelectedItem().equals("4")){
                        fileNumber = fileNumber + "-appeal-4";
                    }
                    else if(appealNumber.getSelectedItem().equals("5")){
                        fileNumber = fileNumber + "-appeal-5";
                    }
                    else if(appealNumber.getSelectedItem().equals("6")){
                        fileNumber = fileNumber + "-appeal-6";
                    }
                    else if(appealNumber.getSelectedItem().equals("7")){
                        fileNumber = fileNumber + "-appeal-7";
                    }
                    else if(appealNumber.getSelectedItem().equals("8")){
                        fileNumber = fileNumber + "-appeal-8";
                    }
                    else if(appealNumber.getSelectedItem().equals("9")){
                        fileNumber = fileNumber + "-appeal-9";
                    }
                    else if(appealNumber.getSelectedItem().equals("10")){
                        fileNumber = fileNumber + "-appeal-10";
                    }
                    
                    if(obj.isFileNumberExisting(fileNumber)){
                        JOptionPane.showMessageDialog(new JPanel(), "File Number found!");
                        tabbedPane.removeAll();
                        tabbedPane.addTab("Detail Pane", detailPane);
                        petitionerPane.add(objPetiPane.petitionerTabbedPane(fileNumber),BorderLayout.CENTER);
                        tabbedPane.addTab("Add Petitioner/Respondent Data", petitionerPane);
//                        tabbedPane.addTab("Add Document Data", documentPane);
                        tabbedPane.addTab("Add Case Proceeding Data", caseProceedingPane);
                        
                        RetrieveCaseInfoClass bagObj = new RetrieveCaseInfoClass();

                        bagObj = obj.fetchCaseInfo(fileNumber);
                        System.out.println("Bag 1 created, case number "+bagObj.caseNumber);
                        caseNumber.setText(bagObj.caseNumber);
                        courtList.setSelectedItem(bagObj.courtList);
                        location.setSelectedItem(bagObj.locationList);
                        caseTypeList.setSelectedItem(bagObj.caseTypeList);
                        yearField.setText(bagObj.yearList);
                        lawyerField.setSelectedItem(bagObj.lawyer);

                        RetrieveCaseInfoClass bagObj2 = new RetrieveCaseInfoClass();
                        bagObj2 = obj.fetchPetipane(fileNumber, 1);
                        objPetiPane.petitionerName.setText(bagObj2.petitionerName);
                        objPetiPane.petitionerEmail.setText(bagObj2.petitionerEmail);
                        objPetiPane.petitionerAddress.setText(bagObj2.petitionerAddress);
                        objPetiPane.petitionerFileName.setText(bagObj2.petitionerFileName);
                        objPetiPane.petitionerFile = bagObj2.petitionerFile;
                        objPetiPane.respondentName.setText(bagObj2.respondentName);
                        objPetiPane.respondentEmail.setText(bagObj2.respondentEmail);
                        objPetiPane.respondentAddress.setText(bagObj2.respondentAddress);
                        objPetiPane.respondentFileName.setText(bagObj2.respondentFileName);
                        objPetiPane.respondentFile = bagObj2.respondentFile;
                        
                        RetrieveCaseInfoClass bagObj3 = new RetrieveCaseInfoClass();
                        bagObj3 = obj.fetchCasedate(fileNumber);
                        objDetailPane.datePickerCaseFiledOn.getJFormattedTextField().setText(bagObj3.datePickerCaseFiledOn);
                        objDetailPane.datePickerFirstHearingField.getJFormattedTextField().setText(bagObj3.datePickerFirstHearingField);
                        objDetailPane.datePickerNoticeReceivedOn.getJFormattedTextField().setText(bagObj3.datePickerNoticeReceivedOn);
//                        objDetailPane.datePickerNextHearingField.getJFormattedTextField().setText(bagObj3.datePickerNextHearingField);


                        RetrieveCaseInfoClass bagObj4 = new RetrieveCaseInfoClass();
                        System.out.println("Fetching Case Proceeding data");
                        bagObj4 = obj.fetchCaseProceed(fileNumber);
                        System.out.println("Data fetched");
                        objCaseProc.proceedingNumber = bagObj4.proceedingNumber;
                        objCaseProc.decision.setSelectedItem(bagObj4.decision);
                        objCaseProc.description.setText(bagObj4.description);
                        objCaseProc.datePickerProceeding.getJFormattedTextField().setText(bagObj4.datePickerProceeding);
                        if(bagObj4.decision.equals("ADJOURNMENT")){
                            System.out.println("Adjourned "+bagObj4.datePickerNextHearingField);
                            objCaseProc.datePickerNextHearing.getJFormattedTextField().setText(bagObj4.datePickerNextHearingField);
                            objCaseProc.orderFile = null;
                            objCaseProc.fileName.setText(null);
                        }
                        else if(bagObj4.decision.equals("FINAL JUDGEMENT")){
                            System.out.println("Final Judgement");
                            objCaseProc.fileName.setText(bagObj4.orderFileName);
                            objCaseProc.orderFile = bagObj4.orderFile;
                            objCaseProc.datePickerNextHearing.getJFormattedTextField().setText(null);
                        }
                        else if(bagObj4.decision.equals("INTERIM ORDER")){
                            System.out.println("Order Passed");
                            objCaseProc.fileName.setText(bagObj4.orderFileName);
                            objCaseProc.orderFile = bagObj4.orderFile;
                            objCaseProc.datePickerNextHearing.getJFormattedTextField().setText(bagObj4.datePickerNextHearingField);
                        }
                        else if(bagObj4.decision.equals("PENDING")){
                            System.out.println("Pending");
                            objCaseProc.datePickerNextHearing.getJFormattedTextField().setText(null);
                            objCaseProc.orderFile = null;
                            objCaseProc.fileName.setText(null);
                        }
                        if(!bagObj4.decision.equals("PENDING")){
                            objCaseProc.decision.removeItem("PENDING");
                        }
                        /**uncomment when summary of prayer etc needed*/

//                        RetrieveCaseInfoClass bagObj5 = new RetrieveCaseInfoClass();
//                        bagObj5 = obj.fetchSummary(fileNumber));
//                        objDetailPane.summaryInPrayer.setText(bagObj3.summaryInPrayer);
//                        objDetailPane.generalRemarks.setText(bagObj3.summaryInPrayer);                  
//                        objDetailPane.otherLinks.setText(bagObj3.summaryInPrayer);

                        /**---------------------*/
                        southPanelForButton.removeAll();
                        southPanelForButton.add(updateButton);
                        southPanelForButton.validate();
                        paneForTabbedPane.validate();
                        tabbedPane.validate();
                    }
                    else{
                        if(appeal != 0){
                            String previousAppeal = null;
                            if(appeal == 1){
                                previousAppeal = fileNumber.substring(0, fileNumber.length() - 9);
                            }
                            else if(appeal == 2){
                                previousAppeal = fileNumber.substring(0, fileNumber.length() - 1);
                                previousAppeal = previousAppeal + "1";
                            }
                            else if(appeal == 3){
                                previousAppeal = fileNumber.substring(0, fileNumber.length() - 1);
                                previousAppeal = previousAppeal + "2";
                            }
                            else if(appeal == 4){
                                previousAppeal = fileNumber.substring(0, fileNumber.length() - 1);
                                previousAppeal = previousAppeal + "3";
                            }
                            else if(appeal == 5){
                                previousAppeal = fileNumber.substring(0, fileNumber.length() - 1);
                                previousAppeal = previousAppeal + "4";
                            }
                            else if(appeal == 6){
                                previousAppeal = fileNumber.substring(0, fileNumber.length() - 1);
                                previousAppeal = previousAppeal + "5";
                            }
                            else if(appeal == 7){
                                previousAppeal = fileNumber.substring(0, fileNumber.length() - 1);
                                previousAppeal = previousAppeal + "6";
                            }
                            else if(appeal == 8){
                                previousAppeal = fileNumber.substring(0, fileNumber.length() - 1);
                                previousAppeal = previousAppeal + "7";
                            }
                            else if(appeal == 9){
                                previousAppeal = fileNumber.substring(0, fileNumber.length() - 1);
                                previousAppeal = previousAppeal + "8";
                            }
                            else if(appeal == 10){
                                previousAppeal = fileNumber.substring(0, fileNumber.length() - 1);
                                previousAppeal = previousAppeal + "9";
                            }
                            System.out.println(previousAppeal);
                            if(obj.isFileNumberExisting(previousAppeal)){
                                if(!obj.isFileNumberAppealable(previousAppeal)){
                                    JOptionPane.showMessageDialog(new JPanel(), "Appeal for this File Number can't be "
                                            + "generated as its previous appeal is not FINAL JUDGEMENT or it is already in"
                                            + " SUPREME COURT");
                                    return;
                                }
                            }
                            else if(!obj.isFileNumberExisting(previousAppeal)){
                                JOptionPane.showMessageDialog(new JPanel(), "Cannot create an appeal for a File Number"
                                        + " which does not exist");
                                    return;
                            }
                        }
                        JOptionPane.showMessageDialog(new JPanel(), "Conditions for this File Number and Appeal Number"
                                + " have been checked, New Entry will be created!");
//                        JOptionPane.showMessageDialog(new JPanel(), "File Number not found, New Entry will be created!");
                        tabbedPane.removeAll();
                        tabbedPane.addTab("Detail Pane", detailPane);
                        petitionerPane.add(objPetiPane.petitionerTabbedPane(fileNumber),BorderLayout.CENTER);
                        tabbedPane.addTab("Add Petitioner/Respondent Data", petitionerPane);
//                        tabbedPane.addTab("Add Document Data", documentPane);
                        tabbedPane.addTab("Add Case Proceeding Data", caseProceedingPane);
                        
                        caseNumber.setText("");
                        courtList.setSelectedItem("NONE");
                        location.setSelectedItem("NONE");
                        caseTypeList.setSelectedItem("NONE");
                        yearField.setText("");
                        lawyerField.setSelectedItem("NONE");
                        
                        objPetiPane.petitionerName.setText("");
                        objPetiPane.petitionerEmail.setText("");
                        objPetiPane.petitionerAddress.setText("");
                        objPetiPane.respondentName.setText("");
                        objPetiPane.respondentEmail.setText("");
                        objPetiPane.respondentAddress.setText("");
                        objPetiPane.petitionerFile = null;
                        objPetiPane.respondentFile = null;
                        objPetiPane.petitionerFileName.setText("No file added");
                        objPetiPane.respondentFileName.setText("No file added");
                        
                        objDetailPane.datePickerCaseFiledOn.getJFormattedTextField().setText("");
                        objDetailPane.datePickerFirstHearingField.getJFormattedTextField().setText("");
                        objDetailPane.datePickerNoticeReceivedOn.getJFormattedTextField().setText("");
//                        objDetailPane.datePickerNextHearingField.getJFormattedTextField().setText("");
                        
//                        objCaseProc.decision.setSelectedItem();
                        objCaseProc.description.setText("");
                        objCaseProc.datePickerProceeding.getJFormattedTextField().setText("");
                        objCaseProc.orderFile = null;
                        objCaseProc.fileName.setText("No File Selected");
                        objCaseProc.datePickerNextHearing.getJFormattedTextField().setText(null);
                        southPanelForButton.removeAll();
                        southPanelForButton.add(saveButton);
                        southPanelForButton.validate();
                        paneForTabbedPane.validate();
                        tabbedPane.validate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        return basePanel;

    }
};
