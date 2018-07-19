/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
import javax.swing.table.TableModel;

/**
 *
 * @author HP
 */

public class ReportOutput {
    JPanel outputPanel, tablePanel, buttonPanel;
    GridBagLayout reportLayout;
    JTable outputTable;
    JScrollPane scrollPane, outputScroll;
    Dimension preferredSize;
    JButton downloadTable, mailReport, delete, downloadOrderFiles;
    
    public void toExcel(JTable table, int x, String check){
        try{
            //here 'x' is used find out which radio button is selected; 'check' is the value through which data will be filtered
            TableModel model = table.getModel();
            System.out.println("rows "+model.getRowCount()+" columns "+model.getColumnCount());
            if(model.getRowCount() == 0)
            {
                JOptionPane.showMessageDialog(new JPanel(), "Empty Table");
                return;
            }
            
            String checker = "";
            switch(x)
            {
                case 0: { checker = "Court "+check; break; }
                case 1: { checker = "Case Number "+check; break; }
                case 2: { checker = "File Number "+check; break; }
                case 3: { checker = "Lawyer "+check; break; }
                case 4: { checker = "Location "+check; break; }
                case 5:
                {
                    String after = check.substring(0, 10);
                    String before = check.substring(10);
                    checker = "Next hearing between "+after+" "+before;
                    break;
                }
            }
            System.out.println("checker = "+checker);
            String home = System.getProperty("user.home");
            Path fileToDeletePath = Paths.get(home+"/Downloads/"+checker+".xls");
            if(new File(home+"/Downloads/"+checker+".xls").exists()){
                Files.delete(fileToDeletePath);
            }
            File file = new File(home+"/Downloads/"+checker+".xls");
            FileWriter excel = new FileWriter(file);
            
            
            for(int i = 0; i < model.getColumnCount(); i++){
                excel.write(model.getColumnName(i) + "\t");
            }

            excel.write("\n");

            for(int i=0; i< model.getRowCount(); i++) {
                for(int j=0; j < model.getColumnCount(); j++) {
                    excel.write(model.getValueAt(i,j).toString()+"\t");
                }
                excel.write("\n");
            }
            JOptionPane.showMessageDialog(new JPanel(), "File saved at "+home+"/Downloads/"+checker+".xls");
            excel.close();
        }catch(IOException e){ System.out.println(e); }
    }
    
    JPanel prepareGUI(String username, int x, String check, String userGroup) throws SQLException{
        outputPanel = new JPanel();
        reportLayout = new GridBagLayout();
        
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridheight = 1;
        constraint.gridwidth = 1;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets.top = 5;
        constraint.insets.bottom = 5;
        constraint.insets.left = 5;
        constraint.insets.right = 8;
        constraint.anchor = GridBagConstraints.CENTER;
        
        outputPanel.setLayout(reportLayout);
//        outputScroll = new JScrollPane(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        outputScroll.add(outputPanel);
        DatabaseHandling object = new DatabaseHandling();
        outputTable = object.makeOutputTable(x, check);
        
        scrollPane = new JScrollPane(outputTable);
        preferredSize= new Dimension(700, 200);
        scrollPane.setPreferredSize(preferredSize);
        tablePanel = new JPanel();
        tablePanel.add(scrollPane);
        outputPanel.add(tablePanel, constraint);
        
        constraint.gridy++;
        constraint.gridx = 0;
        
        downloadTable = new JButton("Download Table");
        outputPanel.add(downloadTable, constraint);
        
        constraint.gridy++;
        constraint.gridx = 0;
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());
                
        downloadOrderFiles =  new JButton("Download Order Files");
        buttonPanel.add(downloadOrderFiles);
        
//        constraint.gridy++;
//        constraint.gridx = 0;
        mailReport = new JButton("Generate Report");
        buttonPanel.add(mailReport);
        
        if(userGroup == "Administrator"){
//            constraint.gridy++;
//            constraint.gridx = 0;
            delete = new JButton("Delete");
            buttonPanel.add(delete);
        }
        outputPanel.add(buttonPanel, constraint);
        
        downloadTable.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                toExcel(outputTable, x, check);
            }
        });
        
        downloadOrderFiles.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileNumber = (String) outputTable.getValueAt(outputTable.getSelectedRow(), outputTable.getSelectedColumn());
                DatabaseHandling obj = new DatabaseHandling();
                try {
                    obj.fetchOrderFiles(fileNumber);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ReportOutput.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ReportOutput.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ReportOutput.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        if(userGroup == "Administrator"){        
            delete.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(JOptionPane.showConfirmDialog(new JPanel(), "Are you sure?") == 0){
                        String fileNumber = (String) outputTable.getValueAt(outputTable.getSelectedRow(), outputTable.getSelectedColumn());
                        DatabaseHandling obj = new DatabaseHandling();
                        try {
                            obj.deleteEntry(username, userGroup, fileNumber);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(ReportOutput.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(ReportOutput.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(new JPanel(), "Entry for File Number "+fileNumber+" deleted");
                    }
                    else {
                        System.out.println("No selected");
                    }
                }
            });
        }
        mailReport.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fileNumber = (String) outputTable.getValueAt(outputTable.getSelectedRow(), outputTable.getSelectedColumn());
                    DatabaseHandling obj = new DatabaseHandling();
                    String home = System.getProperty("user.home");
//                    Path fileToDeletePath = Paths.get(home+"/Downloads/"+fileNumber+"/FileNumber"+fileNumber+".rtf");
//                    if(new File(home+"/Downloads/"+fileNumber+"/FileNumber"+fileNumber+".rtf").exists()){
//                        Files.delete(fileToDeletePath);
//                    }
                    
                    Path fileToDeletePath = Paths.get(home+"/Downloads/"+fileNumber+"/FileNumber"+fileNumber+".html");
                    if(new File(home+"/Downloads/"+fileNumber+"/FileNumber"+fileNumber+".html").exists()){
                        Files.delete(fileToDeletePath);
                    }
                    
//                    Path pathToFile = Paths.get(home+"/Downloads/"+fileNumber+"/FileNumber"+fileNumber+".rtf");
                    Path pathToFile = Paths.get(home+"/Downloads/"+fileNumber+"/FileNumber"+fileNumber+".html");
                    
                    Files.createDirectories(pathToFile.getParent());
                    Files.createFile(pathToFile);
                    try {
                        String message = obj.getReportData(fileNumber, username);
//                        try (PrintStream out = new PrintStream(new FileOutputStream(home+"/Downloads/"+fileNumber+"/FileNumber"+fileNumber+".rtf"))) {
//                            out.print(message);
//                        }
//                        JOptionPane.showMessageDialog(new JPanel(), "File FileNumber"+fileNumber+".rtf downloaded at "+home+"/Downloads/"+fileNumber+"/FileNumber"+fileNumber+".rtf");
//                        Desktop.getDesktop().open(new File(home+"/Downloads/"+fileNumber+"/FileNumber"+fileNumber+".rtf"));
                        
                        try (PrintStream out = new PrintStream(new FileOutputStream(home+"/Downloads/"+fileNumber+"/FileNumber"+fileNumber+".html"))) {
                            out.print(message);
                        }
                        JOptionPane.showMessageDialog(new JPanel(), "File FileNumber"+fileNumber+".html downloaded at "+home+"/Downloads/"+fileNumber+"/FileNumber"+fileNumber+".html");
                        Desktop.getDesktop().open(new File(home+"/Downloads/"+fileNumber+"/FileNumber"+fileNumber+".html"));
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ReportOutput.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ReportOutput.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ReportOutput.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MessagingException ex) {
                        Logger.getLogger(ReportOutput.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(ReportOutput.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        return outputPanel;
    }
}
