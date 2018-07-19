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
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

/**
 *
 * @author HP
 */
public class DocumntPanel {
    
    
    JScrollPane DmainScrollPetitioner,DmainScrollRespondent;
    JSplitPane DpaneOnpetitionerPane;
    JPanel DpaneOnPetitioner,DpaneOnRespondent;
    JPanel DpetitionerDetail,DrespondentDetail;
    JTextField DpetitionerName,DrespondentName,dateofUploadPet,dateofUploadRes;
    JTextArea DdocumentSummaryPet,DdocumentSummaryRes;
    GridBagConstraints DconstraintForPetiPane,DconstraintForResPane;
    JScrollPane DSPaneForPA, DSPaneForRA;
    JComboBox listDocumentRes,listDocumentPet;
   // JButton addPR,editPR,cancelPR;
   // JPanel PRSouth;
    JSplitPane documentTabbedPane(){
        
        DpaneOnPetitioner = new JPanel(new BorderLayout());
        DpaneOnPetitioner.setBorder(BorderFactory.createLineBorder(Color.black));
        DpaneOnRespondent = new JPanel(new BorderLayout());
        DpaneOnRespondent.setBorder(BorderFactory.createLineBorder(Color.black));
        DmainScrollPetitioner = new JScrollPane(DpaneOnPetitioner, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        DmainScrollRespondent = new JScrollPane(DpaneOnRespondent, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        DpetitionerDetail = new JPanel(new GridBagLayout());
        DrespondentDetail = new JPanel(new GridBagLayout());
        DpaneOnPetitioner.add(DpetitionerDetail);
        DpaneOnRespondent.add(DrespondentDetail);
        DpetitionerName = new JTextField(20);
        DrespondentName = new JTextField(20);
        DdocumentSummaryPet = new JTextArea(4, 20);
        DdocumentSummaryPet.setLineWrap(true);
        DdocumentSummaryRes = new JTextArea(4, 20);
        DdocumentSummaryRes.setLineWrap(true);
        DSPaneForPA = new JScrollPane(DdocumentSummaryPet, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        DSPaneForRA = new JScrollPane(DdocumentSummaryRes, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        DpaneOnpetitionerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,DpaneOnPetitioner,DpaneOnRespondent);
        listDocumentPet = new JComboBox();
        listDocumentPet.addItem("reports");
        listDocumentPet.addItem("awards");
        listDocumentPet.addItem("reports");
        listDocumentRes = new JComboBox();
        listDocumentRes.addItem("reports");
        listDocumentRes.addItem("awards");
        listDocumentRes.addItem("reports");
        dateofUploadPet = new JTextField(20);
        dateofUploadRes = new JTextField(20);
        
        
         DconstraintForPetiPane = new GridBagConstraints();
        DconstraintForPetiPane.gridheight = 1;
        DconstraintForPetiPane.gridwidth = 1;
        DconstraintForPetiPane.gridx = 0;
        DconstraintForPetiPane.gridy = 0;
        DconstraintForPetiPane.insets.top = 5;
        DconstraintForPetiPane.insets.bottom = 5;
        DconstraintForPetiPane.insets.left = 5;
        DconstraintForPetiPane.insets.right = 8;
        DconstraintForPetiPane.anchor = GridBagConstraints.WEST;
        
        DconstraintForResPane = new GridBagConstraints();
        DconstraintForResPane.gridheight = 1;
        DconstraintForResPane.gridwidth = 1;
        DconstraintForResPane.gridx = 0;
        DconstraintForResPane.gridy = 0;
        DconstraintForResPane.insets.top = 5;
        DconstraintForResPane.insets.bottom = 5;
        DconstraintForResPane.insets.left = 5;
        DconstraintForResPane.insets.right = 8;
        DconstraintForResPane.anchor = GridBagConstraints.WEST;
        
        DpetitionerDetail.add(new JLabel("Petitioner Name :"), DconstraintForPetiPane);
        DconstraintForPetiPane.gridx++;
        DpetitionerDetail.add(DpetitionerName, DconstraintForPetiPane);
        DconstraintForPetiPane.gridx = 0;
        DconstraintForPetiPane.gridy++;
        DpetitionerDetail.add(new JLabel("Select the Document"), DconstraintForPetiPane);
        DconstraintForPetiPane.gridx++;
        DpetitionerDetail.add(listDocumentPet, DconstraintForPetiPane);
        DconstraintForPetiPane.gridx = 0;
        DconstraintForPetiPane.gridy++;
        DpetitionerDetail.add(new JLabel("Summary Of the Document"), DconstraintForPetiPane);
        DconstraintForPetiPane.gridx++;
        DpetitionerDetail.add(DSPaneForPA, DconstraintForPetiPane);
        DconstraintForPetiPane.gridx = 0;
        DconstraintForPetiPane.gridy++;
        DpetitionerDetail.add(new JLabel("Date Of Upload"), DconstraintForPetiPane);
        DconstraintForPetiPane.gridx++;
        DpetitionerDetail.add(dateofUploadPet, DconstraintForPetiPane);
        
        DrespondentDetail.add(new JLabel("Respondent Name :"), DconstraintForResPane);
        DconstraintForResPane.gridx++;
        DrespondentDetail.add(DrespondentName, DconstraintForResPane);
        DconstraintForResPane.gridx = 0;
        DconstraintForResPane.gridy++;
        DrespondentDetail.add(new JLabel("Select Document"), DconstraintForResPane);
        DconstraintForResPane.gridx++;
        DrespondentDetail.add(listDocumentRes, DconstraintForResPane);
        DconstraintForResPane.gridx = 0;
        DconstraintForResPane.gridy++;
        DrespondentDetail.add(new JLabel("Summary of the Document"), DconstraintForResPane);
        DconstraintForResPane.gridx++;
        DrespondentDetail.add(DSPaneForRA, DconstraintForResPane);
        DconstraintForResPane.gridx = 0;
        DconstraintForResPane.gridy++;
        DrespondentDetail.add(new JLabel("Date of Upload"), DconstraintForResPane);
        DconstraintForResPane.gridx++;
        DrespondentDetail.add(dateofUploadRes, DconstraintForResPane);
        
        return DpaneOnpetitionerPane;
    }
    
}
