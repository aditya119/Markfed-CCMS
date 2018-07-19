/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

import org.jdatepicker.impl.*;

/**
 *
 * @author HP
 */
public class DetailPane {
    
    JPanel paneOnDetail;
    JPanel importantDates, caseSummary, keyOrderDetail,caseDetailsPanel;
    JLabel caseFiledOnL, noticeRecievedL, FirstHearingLabel, nextHearingLabel, admittedOn;
   // JTextField caseFiledTextField, noticeReceivedField, FirstHearingField, paraWiseField, admittedOnField;

    JTextArea summaryInPrayer, detailOfHFS, generalRemarks, otherLinks;
    JScrollPane summaryInPrayerSPane, detailOfHFSSPane, generalRemarksSpane, otherLinksSpane, mainScrollPane;
    JPanel sp1, sp2, sp3, sp4;

    UtilDateModel modelCFO, modelNR, modelFHO, modelNH;
    JDatePanelImpl datePanelCaseFiledOn ,datePanelNoticeReceived ,datePanelFirstHearingOn, datePanelNextHearing;
    JDatePickerImpl datePickerCaseFiledOn, datePickerNoticeReceivedOn, datePickerFirstHearingField ,datePickerNextHearingField;
    
    JPanel addDetailPane(){
        importantDates = new JPanel(new GridBagLayout());
        importantDates.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        //importantDates.setBackground(Color.CYAN);
        caseSummary = new JPanel(new GridBagLayout());
        caseSummary.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        caseSummary.setBackground(Color.ORANGE);
        paneOnDetail = new JPanel();
        paneOnDetail.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        paneOnDetail.setLayout(new BoxLayout(paneOnDetail, BoxLayout.Y_AXIS));
        keyOrderDetail = new JPanel(new GridBagLayout());
        caseDetailsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraintForImpDatePane, constraintsForSummary;
       

        // IMPORTANT Date WORK>>>
        caseFiledOnL = new JLabel("Case Filed On");
        noticeRecievedL = new JLabel("Notice Recieved On");
        FirstHearingLabel = new JLabel("First Hearing Date");
//        nextHearingLabel = new JLabel("Next Hearing On");
//        admittedOn = new JLabel("Admitted On");
//        caseFiledTextField = new JTextField(20);
//        noticeReceivedField = new JTextField(20);
//        FirstHearingField = new JTextField(20);
//        paraWiseField = new JTextField(20);
//        admittedOnField = new JTextField(20);

//        // CASE SUMMARY WORK >> 
//        sp1 = new JPanel();
//        sp2 = new JPanel();
//        sp3 = new JPanel();
//        sp4 = new JPanel();
//
//        summaryInPrayerSPane = new JScrollPane(sp1, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
//        summaryInPrayerSPane.setWheelScrollingEnabled(true);
//        summaryInPrayerSPane.getPreferredSize();
//        detailOfHFSSPane = new JScrollPane(sp2, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
//        detailOfHFSSPane.setWheelScrollingEnabled(true);
//        generalRemarksSpane = new JScrollPane(sp3, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
//        generalRemarksSpane.setWheelScrollingEnabled(true);
//        otherLinksSpane = new JScrollPane(sp4, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
//        otherLinksSpane.setWheelScrollingEnabled(true);
       //detailPane.add(mainScrollPane);

        
        constraintForImpDatePane = new GridBagConstraints();
        constraintForImpDatePane.gridheight = 1;
        constraintForImpDatePane.gridwidth = 1;
        constraintForImpDatePane.gridx = 0;
        constraintForImpDatePane.gridy = 0;
        constraintForImpDatePane.insets.top = 5;
        constraintForImpDatePane.insets.bottom = 5;
        constraintForImpDatePane.insets.left = 5;
        constraintForImpDatePane.insets.right = 8;
        constraintForImpDatePane.anchor = GridBagConstraints.WEST;
//
//        constraintForDetailPane = new GridBagConstraints();
//        constraintForDetailPane.gridheight = 1;
//        constraintForDetailPane.gridwidth = 1;
//        constraintForDetailPane.gridx = 0;
//        constraintForDetailPane.gridy = 0;
//        constraintForDetailPane.insets.top = 5;
//        constraintForDetailPane.insets.bottom = 5;
//        constraintForDetailPane.insets.left = 5;
//        constraintForDetailPane.insets.right = 8;
//        constraintForDetailPane.anchor = GridBagConstraints.WEST;

        constraintsForSummary = new GridBagConstraints();
        constraintsForSummary.gridheight = 1;
        constraintsForSummary.gridwidth = 1;
        constraintsForSummary.gridx = 0;
        constraintsForSummary.gridy = 0;
        constraintsForSummary.insets.top = 5;
        constraintsForSummary.insets.bottom = 5;
        constraintsForSummary.insets.left = 5;
        constraintsForSummary.insets.right = 8;
        constraintsForSummary.anchor = GridBagConstraints.WEST;

        modelCFO = new UtilDateModel();
        modelNR = new UtilDateModel();
        modelFHO= new UtilDateModel();
//        modelNH = new UtilDateModel();
        //model.setDate(20,04,2014);
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        
        datePanelCaseFiledOn = new JDatePanelImpl(modelCFO, p);
        datePanelNoticeReceived = new JDatePanelImpl(modelNR, p);
        datePanelFirstHearingOn = new JDatePanelImpl(modelFHO, p);
//        datePanelNextHearing = new JDatePanelImpl(modelNH, p);
        
        // Don't know about the formatter, but there it is...
        datePickerCaseFiledOn = new JDatePickerImpl(datePanelCaseFiledOn, new DateLabelFormatter());
        datePickerNoticeReceivedOn = new JDatePickerImpl(datePanelNoticeReceived, new DateLabelFormatter());
        datePickerFirstHearingField = new JDatePickerImpl(datePanelFirstHearingOn, new DateLabelFormatter());
//        datePickerNextHearingField = new JDatePickerImpl(datePanelNextHearing, new DateLabelFormatter());
        
    //  String n = datePickerCaseFiledOn.getJFormattedTextField().getText();
        
        summaryInPrayer = new JTextArea(4, 20);
        summaryInPrayer.setLineWrap(true);
        detailOfHFS = new JTextArea(4, 20);
        detailOfHFS.setLineWrap(true);
        generalRemarks = new JTextArea(4, 20);
        generalRemarks.setLineWrap(true);
        otherLinks = new JTextArea(2, 20);
        otherLinks.setLineWrap(true);
               
//        sp1.add(summaryInPrayer);
//        sp2.add(detailOfHFS);
//        sp3.add(generalRemarks);
//        sp4.add(otherLinks);
        
       // caseFiledTextField.add(datePicker);
        
        //IMPORTANT DATEE
        importantDates.add(caseFiledOnL, constraintForImpDatePane);
        constraintForImpDatePane.gridx++;
        importantDates.add(datePickerCaseFiledOn, constraintForImpDatePane);
        constraintForImpDatePane.gridx = 0;
        constraintForImpDatePane.gridy++;
        importantDates.add(noticeRecievedL, constraintForImpDatePane);
        constraintForImpDatePane.gridx++;
        importantDates.add(datePickerNoticeReceivedOn, constraintForImpDatePane);
        constraintForImpDatePane.gridx = 0;
        constraintForImpDatePane.gridy++;
        importantDates.add(FirstHearingLabel, constraintForImpDatePane);
        constraintForImpDatePane.gridx++;
        importantDates.add(datePickerFirstHearingField, constraintForImpDatePane);
//        constraintForImpDatePane.gridx = 0;
//        constraintForImpDatePane.gridy++;
//        importantDates.add(nextHearingLabel, constraintForImpDatePane);
//        constraintForImpDatePane.gridx++;
//        importantDates.add(datePickerNextHearingField, constraintForImpDatePane);
        constraintForImpDatePane.gridx = 0;

        /*
         * CASE SUMMARY >>.. >>> .> >>> > > >
         *
         */
//        caseSummary.add(new JLabel("Summary of the Prayer in the Petition"), constraintsForSummary);
//        constraintsForSummary.gridx++;
//        caseSummary.add(summaryInPrayerSPane, constraintsForSummary);
//        constraintsForSummary.gridx = 0;
//        constraintsForSummary.gridy++;
//        caseSummary.add(new JLabel("General Remarks"), constraintsForSummary);
//        constraintsForSummary.gridx++;
//        constraintsForSummary.gridwidth = 3;
//        constraintsForSummary.fill = GridBagConstraints.HORIZONTAL;
//        caseSummary.add(generalRemarksSpane, constraintsForSummary);
//        constraintsForSummary.gridx = 0;
//        constraintsForSummary.gridy++;
//        caseSummary.add(new JLabel("Other Link in the Current Case"), constraintsForSummary);
//        constraintsForSummary.gridx++;
//        constraintsForSummary.gridwidth = 3;
//        constraintsForSummary.fill = GridBagConstraints.HORIZONTAL;
//        caseSummary.add(otherLinksSpane, constraintsForSummary);
//        constraintsForSummary.gridx = 0;
//        constraintForImpDatePane.gridy++;
        
        paneOnDetail.add(new JLabel("IMPORTANT DATES "));
        paneOnDetail.add(importantDates);
//        paneOnDetail.add(new JLabel("CASE SUMMARY"));
//        paneOnDetail.add(caseSummary);
//        paneOnDetail.add(new JLabel("KEY ORDER DETAIL"));
//        paneOnDetail.add(keyOrderDetail);

        
        return paneOnDetail;
    }
}
