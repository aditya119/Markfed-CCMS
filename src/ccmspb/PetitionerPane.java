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
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author HP
 */
public class PetitionerPane {
    JScrollPane mainScrollPetitioner,mainScrollRespondent;
    JSplitPane paneOnpetitionerPane;
    JPanel paneOnPetitioner,paneOnRespondent;
    JPanel petitionerDetail,respondentDetail;
    JTextField petitionerName,respondentName,petitionerEmail,respondentEmail;
    JLabel petitionerFileName, respondentFileName;
    JTextArea petitionerAddress,respondentAddress;
    GridBagConstraints constraintForPetiPane,constraintForResPane;
    JScrollPane scrollPaneForPetitionerAddress, scrollPaneForRespondentAddress;
    JButton addPetitionerFile, addRespondentFile, downloadPetitionerFile, downloadRespondentFile;
    File petitionerFile = null, respondentFile = null;
   // JButton addPR,editPR,cancelPR;
   // JPanel PRSouth;
    
    JSplitPane petitionerTabbedPane(String fileNumber){
        paneOnPetitioner = new JPanel(new BorderLayout());
        paneOnPetitioner.setBorder(BorderFactory.createLineBorder(Color.black));
        paneOnRespondent = new JPanel(new BorderLayout());
        paneOnRespondent.setBorder(BorderFactory.createLineBorder(Color.black));
//         paneOnPetitioner.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
//         paneOnPetitioner.setLayout(new BoxLayout(paneOnPetitioner,BoxLayout.Y_AXIS));
        mainScrollPetitioner = new JScrollPane(paneOnPetitioner, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollRespondent = new JScrollPane(paneOnRespondent, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        petitionerDetail = new JPanel(new GridBagLayout());
        respondentDetail = new JPanel(new GridBagLayout());
        paneOnPetitioner.add(petitionerDetail);
        paneOnRespondent.add(respondentDetail);
        petitionerName = new JTextField(15);
        respondentName = new JTextField(15);
        petitionerEmail = new JTextField(15);
        respondentEmail = new JTextField(15);
        petitionerAddress = new JTextArea(3, 15);
        petitionerAddress.setLineWrap(true);
        respondentAddress = new JTextArea(3, 15);
        respondentAddress.setLineWrap(true);
        addPetitionerFile = new JButton("Add File");
        addRespondentFile = new JButton("Add File");
        petitionerFileName = new JLabel("No File Selected");
        respondentFileName = new JLabel("No File Selected");
        downloadPetitionerFile = new JButton("Download");
        downloadRespondentFile = new JButton("Download");
        
        scrollPaneForPetitionerAddress = new JScrollPane(petitionerAddress, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneForRespondentAddress = new JScrollPane(respondentAddress, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        paneOnpetitionerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,paneOnPetitioner,paneOnRespondent);
        
        constraintForPetiPane = new GridBagConstraints();
        constraintForPetiPane.gridheight = 1;
        constraintForPetiPane.gridwidth = 1;
        constraintForPetiPane.gridx = 0;
        constraintForPetiPane.gridy = 0;
        constraintForPetiPane.insets.top = 5;
        constraintForPetiPane.insets.bottom = 5;
        constraintForPetiPane.insets.left = 5;
        constraintForPetiPane.insets.right = 8;
        constraintForPetiPane.anchor = GridBagConstraints.WEST;
        
        constraintForResPane = new GridBagConstraints();
        constraintForResPane.gridheight = 1;
        constraintForResPane.gridwidth = 1;
        constraintForResPane.gridx = 0;
        constraintForResPane.gridy = 0;
        constraintForResPane.insets.top = 5;
        constraintForResPane.insets.bottom = 5;
        constraintForResPane.insets.left = 5;
        constraintForResPane.insets.right = 8;
        constraintForResPane.anchor = GridBagConstraints.WEST;
        
        petitionerDetail.add(new JLabel("Petitioner Name:*"), constraintForPetiPane);
        constraintForPetiPane.gridx++;
        petitionerDetail.add(petitionerName, constraintForPetiPane);
        constraintForPetiPane.gridx = 0;
        constraintForPetiPane.gridy++;
        petitionerDetail.add(new JLabel("Petitioner E-Mail:"), constraintForPetiPane);
        constraintForPetiPane.gridx++;
        petitionerDetail.add(petitionerEmail, constraintForPetiPane);
        constraintForPetiPane.gridx = 0;
        constraintForPetiPane.gridy++;
        petitionerDetail.add(new JLabel("Enter Address*"), constraintForPetiPane);
        constraintForPetiPane.gridx++;
        petitionerDetail.add(scrollPaneForPetitionerAddress, constraintForPetiPane);
        constraintForPetiPane.gridx = 0;
        constraintForPetiPane.gridy++;
        petitionerDetail.add(addPetitionerFile, constraintForPetiPane);
        constraintForPetiPane.gridx++;
        petitionerDetail.add(petitionerFileName, constraintForPetiPane);
//        constraintForPetiPane.gridx = 0;
//        constraintForPetiPane.gridy++;
//        petitionerDetail.add(downloadPetitionerFile, constraintForPetiPane);
        
        respondentDetail.add(new JLabel("Respondent Name:*"), constraintForResPane);
        constraintForResPane.gridx++;
        respondentDetail.add(respondentName, constraintForResPane);
        constraintForResPane.gridx = 0;
        constraintForResPane.gridy++;
        respondentDetail.add(new JLabel("Respondent E-Mail:"), constraintForResPane);
        constraintForResPane.gridx++;
        respondentDetail.add(respondentEmail, constraintForResPane);
        constraintForResPane.gridx = 0;
        constraintForResPane.gridy++;
        respondentDetail.add(new JLabel("Enter Address*"), constraintForResPane);
        constraintForResPane.gridx++;
        respondentDetail.add(scrollPaneForRespondentAddress, constraintForResPane);
        constraintForResPane.gridx = 0;
        constraintForResPane.gridy++;
        respondentDetail.add(addRespondentFile, constraintForResPane);
        constraintForResPane.gridx++;
        respondentDetail.add(respondentFileName, constraintForResPane);
//        constraintForResPane.gridx = 0;
//        constraintForResPane.gridy++;
//        respondentDetail.add(downloadRespondentFile, constraintForResPane);
        
        addPetitionerFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setAcceptAllFileFilterUsed(true);
//                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files only", "pdf");
//                fc.setFileFilter(filter);
                int returnVal = fc.showOpenDialog(paneOnPetitioner); //Where frame is the parent component
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    petitionerFile = fc.getSelectedFile();
                    petitionerFileName.setText(petitionerFile.getName());
                    //Now you have your file to do whatever you want to do
                } else {
                    //User did not choose a valid file
                }
//                try {
//                    Desktop.getDesktop().open(petitionerFile);
//                } catch (IOException ex) {
//                    Logger.getLogger(PetitionerPane.class.getName()).log(Level.SEVERE, null, ex);
//                }   
            }
        });
        
        addRespondentFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setAcceptAllFileFilterUsed(true);
//                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files only", "pdf");
//                fc.setFileFilter(filter);
                int returnVal = fc.showOpenDialog(paneOnPetitioner); //Where frame is the parent component
                
//                File file = null;
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    respondentFile = fc.getSelectedFile();
                    respondentFileName.setText(respondentFile.getName());
                    //Now you have your file to do whatever you want to do
                } else {
                    //User did not choose a valid file
                }
            }
        });
        
//        downloadPetitionerFile.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DatabaseHandling obj = new DatabaseHandling();
//                try {
//                    obj.downloadFile(1, fileNumber);
//                } catch (SQLException ex) {
//                    Logger.getLogger(PetitionerPane.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(PetitionerPane.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IOException ex) {
//                    Logger.getLogger(PetitionerPane.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//        
//        downloadRespondentFile.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DatabaseHandling obj = new DatabaseHandling();
//                try {
//                    obj.downloadFile(2, fileNumber);
//                } catch (SQLException ex) {
//                    Logger.getLogger(PetitionerPane.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(PetitionerPane.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IOException ex) {
//                    Logger.getLogger(PetitionerPane.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
        
        return paneOnpetitionerPane;
    }   
}