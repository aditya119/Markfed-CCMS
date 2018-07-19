/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JComboBox;
import javax.swing.JTable;

/*
 *
 * @author Aditya Sharma
 * In 'my.ini', change max_allowed_packet_size and innodb_log_file_size (should be more than 10% of max_allowed_packet_size)
 */

public class DatabaseHandling {
    Connection connect;
    Statement stateObj;
    ResultSet rs;
    ResultSet rs1,rs2,rs3,rs4;
    String ip = "127.0.0.1";
    String password = "";
    
    private void createDatabaseConnection() throws ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/markfed?jdbcCompliantTruncation=false","root",password);
            stateObj = connect.createStatement();
            System.out.println("Database Connected!");
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
    }
    
    public void testServer() throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT * FROM `user_info`");
            while(rs.next()){
                System.out.println(rs.getString("username"));
            }
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public void addCourt(String court) throws SQLException, ClassNotFoundException{
        try{
            createDatabaseConnection();
            String query = "INSERT INTO `court_type` (`court_name`) VALUES (?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, court);
            preparedStatement.execute();
        }
        finally{
        }
    }
    
    public void addCaseType(String caseType) throws SQLException, ClassNotFoundException{
        try{
            createDatabaseConnection();
            String query = "INSERT INTO `case_type` (`casetype_name`) VALUES (?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, caseType);
            preparedStatement.execute();
        }
        finally{
        }
    }
    
    public void addLocation(String location) throws SQLException, ClassNotFoundException{
        try{
            createDatabaseConnection();
            String query = "INSERT INTO `location` (`location_name`) VALUES (?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, location);
            preparedStatement.execute();
        }
        finally{
        }
    }
    
    public void addLawyer(String lawyer, String email) throws SQLException, ClassNotFoundException{
        try{
            createDatabaseConnection();
            String query = "INSERT INTO `lawyer`(`lawyer_name`, `email`) VALUES (?, ?)";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, lawyer);
            preparedStatement.setString(2, email);
            preparedStatement.execute();
        }
        finally{
        }
    }
    
    public void populateCaseNumberList(JComboBox caseNumber) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT `case_number` FROM `case_info`");
            while(rs.next()){
                caseNumber.addItem(rs.getString("case_number"));
            }
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public void populateFileNumberList(JComboBox fileNumber) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT `file_number` FROM `case_info`");
            while(rs.next()){
                fileNumber.addItem(rs.getString("file_number"));
            }
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public void populateCourtsList(JComboBox courtList) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT * FROM `court_type` ORDER BY `court_name`");
            while(rs.next()){
                courtList.addItem(rs.getString("court_name"));
            }
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public void populateCaseTypeList(JComboBox caseTypeList) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT * FROM `case_type` ORDER BY `casetype_name`");
            while(rs.next()){
                caseTypeList.addItem(rs.getString("casetype_name"));
            }
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public void populateLocationList(JComboBox locationList) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT * FROM `location` ORDER BY `location_name`");
            while(rs.next()){
                locationList.addItem(rs.getString("location_name"));
            }
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public void populateLawyerList(JComboBox lawyerList) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT * FROM `lawyer` ORDER BY `lawyer_name`");
            while(rs.next()){
                lawyerList.addItem(rs.getString("lawyer_name"));
            }
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public void populateDecisionList(JComboBox decision) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT * FROM `decision_type` ORDER BY `decision`");
            while(rs.next()){
                decision.addItem(rs.getString("decision"));
            }
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    /**
    public void populateYearList(JComboBox yearList) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT DISTINCT `year` FROM `case_info`");
            while(rs.next()){
                yearList.addItem(rs.getString("year"));
            }
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    */
    
    public ArrayList<String> getEmailList() throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("(SELECT `email` FROM `lawyer`) UNION (SELECT `email_add` FROM `user_info`)");
            ArrayList<String> lawyerList = new ArrayList<>();
            int i = 0;
            while(rs.next()){
                lawyerList.add(rs.getString("email"));
                i++;
            }
            return lawyerList;
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public ArrayList<String> getFileNumberList() throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT * FROM `case_info` ORDER BY `file_number`");
            ArrayList<String> fileNumberList = new ArrayList<>();
            int i = 0;
            while(rs.next()){
                fileNumberList.add(rs.getString("file_number"));
                i++;
            }
            return fileNumberList;
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public void changeStatus(String username, String status) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            String query = "UPDATE `user_info` SET `status_app`=? WHERE `username`=?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, username);
            preparedStatement.execute();
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public int verifyUser(String username, String password) throws ClassNotFoundException
    {
        try
        {
//            testServer();
            createDatabaseConnection();
            System.out.println("username: "+username+", password: "+password);
            rs = stateObj.executeQuery("SELECT `password`, `user_group` FROM `user_info` WHERE `username` = '" + username + "'");
            if (rs.next())
            {
                System.out.println(rs.getString("password"));
                System.out.println(rs.getString("user_group"));
                if (rs.getString("password").equals(password))
                {
                    //awesome
                    System.out.println(rs.getString("user_group"));
                    if(rs.getString("user_group").equals("Administrator")){
                        return 1;
                    }
                    else if(rs.getString("user_group").equals("Operator")){
                        return 2;
                    }
                } 
                else
                {
                    //false password
                    return 0;
                }
            } 
            else
            {
                //no records found
                return 3;
            }
        } catch (SQLException se) {

        } finally {
            try
            {
                connect.close();
                stateObj.close();
            }
            catch(SQLException se)
            {
                        
            }
        }
        return 0;
    }
    
    public int getTotalCases() throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT COUNT(`file_number`) FROM `case_info`");
            if(rs.next()){
                return rs.getInt("COUNT(`file_number`)");
            }
            else{
                return 0;
            }
        }finally{
            connect.close();
            stateObj.close();
        }
    }
    
    int getAdjournedCases() throws ClassNotFoundException, SQLException {
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT COUNT(`file_number`) FROM `latest_proceeding` WHERE `decision`='ADJOURNMENT' OR `decision`='PENDING'");
            if(rs.next()){
                return rs.getInt("COUNT(`file_number`)");
            }
            else{
                return 0;
            }
        }finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public int getOrderedCases() throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT COUNT(`file_number`) FROM `latest_proceeding` WHERE `decision`='FINAL JUDGEMENT'");
            if(rs.next()){
                return rs.getInt("COUNT(`file_number`)");
            }
            else{
                return 0;
            }
        }finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public boolean isFileNumberExisting(String fileNumber) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT `file_number` FROM `case_info` WHERE `file_number`='"+fileNumber+"'");
            if(rs.next()){
                if(rs.getString("file_number").equals(fileNumber)){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public boolean isFileNumberAppealable(String fileNumber) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT * FROM `latest_proceeding` NATURAL JOIN `case_info` WHERE `file_number`='"+fileNumber+"'");
            if(rs.next()){
                if(rs.getString("decision").equals("FINAL JUDGEMENT") && !rs.getString("court_name").equals("SUPREME COURT")){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public void updateLog(String username, String userGroup, String fileNumber, String action) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            stateObj.executeUpdate("INSERT INTO `case_update_log` (`username`, `user_group`, `file_number`, `action`) VALUES ('"+username+"', '"+userGroup+"', '"+fileNumber+"', '"+action.toUpperCase()+"')");
        }finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public void deleteEntry(String username, String userGroup, String fileNumber) throws ClassNotFoundException, SQLException{
        try{
            if(!isFileNumberExisting(fileNumber)){
                JOptionPane.showMessageDialog(new JPanel(),"File Number "+fileNumber+" already deleted");
                stateObj.executeUpdate("INSERT INTO `case_update_log` (`username`, `user_group`, `file_number`, `action`) VALUES ('"+username+"', '"+userGroup+"', '"+fileNumber+"', 'DELETE ATTEMPT')");
                return;
            }
            createDatabaseConnection();
            System.out.println(fileNumber);
            stateObj.executeUpdate("DELETE FROM `case_info` WHERE `file_number`='"+fileNumber+"'");
            System.out.println(fileNumber+" deleted from case_info");
            stateObj.executeUpdate("DELETE FROM `petitioner_respondent_info` WHERE `file_number`='"+fileNumber+"'");
            System.out.println(fileNumber+" deleted from pet_res_info");
            stateObj.executeUpdate("DELETE FROM `date_info` WHERE `file_number`='"+fileNumber+"'");
            System.out.println(fileNumber+" deleted from date_info");
            stateObj.executeUpdate("DELETE FROM `case_proceeding` WHERE `file_number`='"+fileNumber+"'");
            System.out.println(fileNumber+" deleted from case_proc");
            stateObj.executeUpdate("DELETE FROM `latest_proceeding` WHERE `file_number`='"+fileNumber+"'");
            System.out.println(fileNumber+" deleted from latest_proc");
            stateObj.executeUpdate("INSERT INTO `case_update_log` (`username`, `user_group`, `file_number`, `action`) VALUES ('"+username+"', '"+userGroup+"', '"+fileNumber+"', 'DELETE')");
        }catch(SQLException se){
//            JOptionPane.showMessageDialog(new JPanel(), "Error File Number "+fileNumber+" not added");
//            se.printStackTrace();
        }finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public void insertCaseInfo(RetrieveCaseInfoClass obj) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException{
        try{           
            // Here we are trying to convery the String object into Blob objects as we have used 
            // BLOB in our database... 
            
            if(isFileNumberExisting(obj.fileNumberField)){
                JOptionPane.showMessageDialog(new JPanel(), "File Number already exists");
                return;
            }
            System.out.println(obj.fileNumberField);
            createDatabaseConnection();
            //case_info
            stateObj.executeUpdate("INSERT INTO `case_info` (`file_number`,`case_number`,`year`,`location`,`case_type`,`court_name`,`lawyer`) VALUES ('"+obj.fileNumberField+"','"+obj.caseNumber+"','"+obj.yearList+"','"+obj.locationList+"','"+obj.caseTypeList+"','"+obj.courtList+"','"+obj.lawyer+"')");
            // stateObj.executeUpdate("INSERT INTO CASE_SUMMARY (FILE_NUMBER,SUMMARY_PRAYER,GENERAL_REMARKS,OTHER_LINKS) VALUES('"+obj.fileNumberField+"','"+blobSummary+"','"+blobGeneral+"','"+blobOtherLinks+"')");            
            
            if(obj.petitionerFile == null && obj.respondentFile == null){
                PreparedStatement ps = connect.prepareStatement("INSERT INTO `petitioner_respondent_info` (`file_number`,`petitioner_name`,`petitioner_email`,`petitioner_address`,`respondent_name`,`respondent_email`,`respondent_address`) VALUES (?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, obj.fileNumberField);
                ps.setString(2, obj.petitionerName);
                ps.setString(3, obj.petitionerEmail);
                ps.setString(4, obj.petitionerAddress);
                ps.setString(5, obj.respondentName);
                ps.setString(6, obj.respondentEmail);
                ps.setString(7, obj.respondentAddress);
                ps.executeUpdate();
            }
            else if(obj.petitionerFile != null && obj.respondentFile == null){
                byte[] petitionerData = new byte[(int) obj.petitionerFile.length()];
                DataInputStream petitionerStream = new DataInputStream(new FileInputStream(obj.petitionerFile));
                petitionerStream.readFully(petitionerData);  // read from file into byte[] array
                petitionerStream.close();
                
                String petitionerFileType = new FileType().getLastToken(obj.petitionerFileName, "\\.");
                
                PreparedStatement ps = connect.prepareStatement("INSERT INTO `petitioner_respondent_info` (`file_number`,`petitioner_name`,`petitioner_email`,`petitioner_address`,`petitioner_file_type`,`petitioner_file`,`respondent_name`,`respondent_email`,`respondent_address`,`respondent_file_type`,`respondent_file`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL)");
                ps.setString(1, obj.fileNumberField);
                ps.setString(2, obj.petitionerName);
                ps.setString(3, obj.petitionerEmail);
                ps.setString(4, obj.petitionerAddress);
                ps.setString(5, petitionerFileType);
                ps.setBytes(6, petitionerData);
                ps.setString(7, obj.respondentName);
                ps.setString(8, obj.respondentEmail);
                ps.setString(9, obj.respondentAddress);
                ps.executeUpdate();
            }
            else if(obj.petitionerFile == null && obj.respondentFile != null){
                byte[] respondentData = new byte[(int) obj.respondentFile.length()];
                DataInputStream respondentStream = new DataInputStream(new FileInputStream(obj.respondentFile));
                respondentStream.readFully(respondentData);  // read from file into byte[] array
                respondentStream.close();
                
                String respondentFileType = new FileType().getLastToken(obj.respondentFileName, "\\.");
                
                PreparedStatement ps = connect.prepareStatement("INSERT INTO `petitioner_respondent_info` (`file_number`,`petitioner_name`,`petitioner_email`,`petitioner_address`,`petitioner_file_type`,`petitioner_file`,`respondent_name`,`respondent_email`,`respondent_address`,`respondent_file_type`,`respondent_file`) VALUES (?, ?, ?, ?, NULL, NULL, ?, ?, ?, ?, ?)");
                ps.setString(1, obj.fileNumberField);
                ps.setString(2, obj.petitionerName);
                ps.setString(3, obj.petitionerEmail);
                ps.setString(4, obj.petitionerAddress);
                ps.setString(5, obj.respondentName);
                ps.setString(6, obj.respondentEmail);
                ps.setString(7, obj.respondentAddress);
                ps.setString(8, respondentFileType);
                ps.setBytes(9, respondentData);
                ps.executeUpdate();
            }
            else{
                byte[] petitionerData = new byte[(int) obj.petitionerFile.length()];
                DataInputStream petitionerStream = new DataInputStream(new FileInputStream(obj.petitionerFile));
                petitionerStream.readFully(petitionerData);  // read from file into byte[] array
                petitionerStream.close();

                byte[] respondentData = new byte[(int) obj.respondentFile.length()];
                DataInputStream respondentStream = new DataInputStream(new FileInputStream(obj.respondentFile));
                respondentStream.readFully(respondentData);  // read from file into byte[] array
                respondentStream.close();

                String petitionerFileType = new FileType().getLastToken(obj.petitionerFileName, "\\.");
                String respondentFileType = new FileType().getLastToken(obj.respondentFileName, "\\.");
                //petitioner_respondent_info
                PreparedStatement ps = connect.prepareStatement("INSERT INTO `petitioner_respondent_info` (`file_number`,`petitioner_name`,`petitioner_email`,`petitioner_address`,`petitioner_file_type`,`petitioner_file`,`respondent_name`,`respondent_email`,`respondent_address`,`respondent_file_type`,`respondent_file`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, obj.fileNumberField);
                ps.setString(2, obj.petitionerName);
                ps.setString(3, obj.petitionerEmail);
                ps.setString(4, obj.petitionerAddress);
                ps.setString(5, petitionerFileType);
                ps.setBytes(6, petitionerData);
                ps.setString(7, obj.respondentName);
                ps.setString(8, obj.respondentEmail);
                ps.setString(9, obj.respondentAddress);
                ps.setString(10, respondentFileType);
                ps.setBytes(11, respondentData);
                ps.executeUpdate();
            }
            
            //date_info
            stateObj.executeUpdate("INSERT INTO `date_info` (`file_number`,`case_filed_on`,`notice_received_on`,`first_hearing_on`) VALUES ('"+obj.fileNumberField+"','"+obj.datePickerCaseFiledOn+"','"+obj.datePickerNoticeReceivedOn+"','"+obj.datePickerFirstHearingField+"')");
            
            //case_proceeding
            if(obj.decision.equals("ADJOURNMENT")){
                stateObj.executeUpdate("INSERT INTO `case_proceeding` (`file_number`,`proceeding_number`,`proceeding_date`,`description`,`decision`,`next_hearing_on`) VALUES ('"+obj.fileNumberField+"','1','"+obj.datePickerProceeding+"','"+obj.description+"','"+obj.decision+"','"+obj.datePickerNextHearingField+"')");
                stateObj.executeUpdate("INSERT INTO `latest_proceeding` (`file_number`,`proceeding_number`,`proceeding_date`,`description`,`decision`,`next_hearing_on`) VALUES('"+obj.fileNumberField+"','1','"+obj.datePickerProceeding+"','"+obj.description+"','"+obj.decision+"','"+obj.datePickerNextHearingField+"')");
            }
            else if(obj.decision.equals("PENDING")){
                stateObj.executeUpdate("INSERT INTO `case_proceeding` (`file_number`,`proceeding_number`,`proceeding_date`,`description`,`decision`,`next_hearing_on`) VALUES ('"+obj.fileNumberField+"','1','"+obj.datePickerProceeding+"','"+obj.description+"','"+obj.decision+"','"+obj.datePickerProceeding+"')");
                stateObj.executeUpdate("INSERT INTO `latest_proceeding` (`file_number`,`proceeding_number`,`proceeding_date`,`description`,`decision`,`next_hearing_on`) VALUES('"+obj.fileNumberField+"','1','"+obj.datePickerProceeding+"','"+obj.description+"','"+obj.decision+"','"+obj.datePickerProceeding+"')");
            }
            else if(obj.decision.equals("FINAL JUDGEMENT")){
                byte[] orderData = new byte[(int) obj.orderFile.length()];
                DataInputStream orderStream = new DataInputStream(new FileInputStream(obj.orderFile));
                orderStream.readFully(orderData);  // read from file into byte[] array
                orderStream.close();
                
                String orderFileType = new FileType().getLastToken(obj.orderFileName, "\\.");
                
                PreparedStatement ps1 = connect.prepareStatement("INSERT INTO `case_proceeding` (`file_number`,`proceeding_number`,`proceeding_date`,`description`,`decision`,`order_file`,`order_file_type`) VALUES (?,1,?,?,?,?,?)");
                ps1.setString(1, obj.fileNumberField);
                ps1.setString(2, obj.datePickerProceeding);
                ps1.setString(3, obj.description);
                ps1.setString(4, obj.decision);
                ps1.setBytes(5, orderData);
                ps1.setString(6, orderFileType);
                ps1.executeUpdate();
                
                PreparedStatement ps2 = connect.prepareStatement("INSERT INTO `latest_proceeding` (`file_number`,`proceeding_number`,`proceeding_date`,`description`,`decision`,`order_file`,`order_file_type`) VALUES (?,1,?,?,?,?,?)");
                ps2.setString(1, obj.fileNumberField);
                ps2.setString(2, obj.datePickerProceeding);
                ps2.setString(3, obj.description);
                ps2.setString(4, obj.decision);
                ps2.setBytes(5, orderData);
                ps2.setString(6, orderFileType);
                ps2.executeUpdate();
            }
            else if(obj.decision.equals("INTERIM ORDER")){
                byte[] orderData = new byte[(int) obj.orderFile.length()];
                DataInputStream orderStream = new DataInputStream(new FileInputStream(obj.orderFile));
                orderStream.readFully(orderData);  // read from file into byte[] array
                orderStream.close();
                
                String orderFileType = new FileType().getLastToken(obj.orderFileName, "\\.");
                System.out.println("Next Hearing "+obj.datePickerNextHearingField);
                PreparedStatement ps1 = connect.prepareStatement("INSERT INTO `case_proceeding` (`file_number`,`proceeding_number`,`proceeding_date`,`description`,`decision`,`next_hearing_on`,`order_file`,`order_file_type`) VALUES (?,1,?,?,?,?,?,?)");
                ps1.setString(1, obj.fileNumberField);
                ps1.setString(2, obj.datePickerProceeding);
                ps1.setString(3, obj.description);
                ps1.setString(4, obj.decision);
                ps1.setString(5, obj.datePickerNextHearingField);
                ps1.setBytes(6, orderData);
                ps1.setString(7, orderFileType);
                ps1.executeUpdate();
                System.out.println("Next Hearing "+obj.datePickerNextHearingField);
                PreparedStatement ps2 = connect.prepareStatement("INSERT INTO `latest_proceeding` (`file_number`,`proceeding_number`,`proceeding_date`,`description`,`decision`,`next_hearing_on`,`order_file`,`order_file_type`) VALUES (?,1,?,?,?,?,?,?)");
                ps2.setString(1, obj.fileNumberField);
                ps2.setString(2, obj.datePickerProceeding);
                ps2.setString(3, obj.description);
                ps2.setString(4, obj.decision);
                ps2.setString(5, obj.datePickerNextHearingField);
                ps2.setBytes(6, orderData);
                ps2.setString(7, orderFileType);
                ps2.executeUpdate();
            }
            JOptionPane.showMessageDialog(new JPanel(), "File Number added");
        }catch(SQLException se){
            deleteEntry("SYSTEM", "SYSTEM", obj.fileNumberField);
            JOptionPane.showMessageDialog(new JPanel(), "Error File Number "+obj.fileNumberField+" not added");
            se.printStackTrace();
        }finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public RetrieveCaseInfoClass getPreviousProceedingData(String fileNumber) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            rs = stateObj.executeQuery("SELECT * FROM `latest_proceeding` WHERE `file_number`='"+fileNumber+"'");
            RetrieveCaseInfoClass obj = new RetrieveCaseInfoClass();
            if(rs.next()){
                obj.decision = rs.getString("decision");
                obj.proceedingNumber = rs.getInt("proceeding_number");
                obj.datePickerProceeding = rs.getString("proceeding_date");
            }
            System.out.println(obj.decision);
            return obj;
        }finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public void updateCaseInfo(RetrieveCaseInfoClass obj) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException{
        try{           
            // Here we are trying to convery the String object into Blob objects as we have used 
            // BLOB in our database... 
            
            if(!isFileNumberExisting(obj.fileNumberField)){
                JOptionPane.showMessageDialog(new JPanel(), "File Number does not exist; click Save to make new entry");
                return;
            }
            RetrieveCaseInfoClass previousObject = getPreviousProceedingData(obj.fileNumberField);
            createDatabaseConnection();
            //case_info
            stateObj.executeUpdate("UPDATE `case_info` SET `case_number`='"+obj.caseNumber+"',`lawyer`='"+obj.lawyer+"',`year`='"+obj.yearList+"',`location`='"+obj.locationList+"',`case_type`='"+obj.caseTypeList+"',`court_name`='"+obj.courtList+"' WHERE `file_number`='"+obj.fileNumberField+"'");
            // stateObj.executeUpdate("INSERT INTO CASE_SUMMARY (FILE_NUMBER,SUMMARY_PRAYER,GENERAL_REMARKS,OTHER_LINKS) VALUES('"+obj.fileNumberField+"','"+blobSummary+"','"+blobGeneral+"','"+blobOtherLinks+"')");          
            
            
            if(obj.petitionerFile == null && obj.respondentFile == null){
                PreparedStatement ps = connect.prepareStatement("UPDATE `petitioner_respondent_info` SET `petitioner_name`=?,`petitioner_email`=?,`petitioner_address`=?,`respondent_name`=?,`respondent_email`=?,`respondent_address`=? WHERE `file_number`=?");
                ps.setString(1, obj.petitionerName);
                ps.setString(2, obj.petitionerEmail);
                ps.setString(3, obj.petitionerAddress);
                ps.setString(4, obj.respondentName);
                ps.setString(5, obj.respondentEmail);
                ps.setString(6, obj.respondentAddress);
                ps.setString(7, obj.fileNumberField);
                ps.executeUpdate();
            }
            else if(obj.petitionerFile != null && obj.respondentFile == null){
                byte[] petitionerData = new byte[(int) obj.petitionerFile.length()];
                DataInputStream petitionerStream = new DataInputStream(new FileInputStream(obj.petitionerFile));
                petitionerStream.readFully(petitionerData);  // read from file into byte[] array
                petitionerStream.close();
                
                String petitionerFileType = new FileType().getLastToken(obj.petitionerFileName, "\\.");
                
                PreparedStatement ps = connect.prepareStatement("UPDATE `petitioner_respondent_info` SET `petitioner_name`=?,`petitioner_email`=?,`petitioner_address`,`petitioner_file_type`=?,`petitioner_file`=?,`respondent_name`=?,`respondent_email`=?,`respondent_address`=?,`respondent_file_type`=NULL,`respondent_file`=NULL WHERE `file_number`=?");
                ps.setString(1, obj.petitionerName);
                ps.setString(2, obj.petitionerEmail);
                ps.setString(3, obj.petitionerAddress);
                ps.setString(4, petitionerFileType);
                ps.setBytes(5, petitionerData);
                ps.setString(6, obj.respondentName);
                ps.setString(7, obj.respondentEmail);
                ps.setString(8, obj.respondentAddress);
                ps.setString(9, obj.fileNumberField);
                ps.executeUpdate();
            }
            else if(obj.petitionerFile == null && obj.respondentFile != null){
                byte[] respondentData = new byte[(int) obj.respondentFile.length()];
                DataInputStream respondentStream = new DataInputStream(new FileInputStream(obj.respondentFile));
                respondentStream.readFully(respondentData);  // read from file into byte[] array
                respondentStream.close();
                
                String respondentFileType = new FileType().getLastToken(obj.respondentFileName, "\\.");
                
                PreparedStatement ps = connect.prepareStatement("UPDATE `petitioner_respondent_info` SET `petitioner_name`=?,`petitioner_email`=?,`petitioner_address`=?,`petitioner_file_type`=NULL,`petitioner_file`=NULL,`respondent_name`=?,`respondent_email`=?,`respondent_address`=?,`respondent_file_type`=?,`respondent_file`=? WHERE `file_number`=?");
                ps.setString(1, obj.petitionerName);
                ps.setString(2, obj.petitionerEmail);
                ps.setString(3, obj.petitionerAddress);
                ps.setString(4, obj.respondentName);
                ps.setString(5, obj.respondentEmail);
                ps.setString(6, obj.respondentAddress);
                ps.setString(7, respondentFileType);
                ps.setBytes(8, respondentData);
                ps.setString(9, obj.fileNumberField);
                ps.executeUpdate();
            }
            else{
                byte[] petitionerData = new byte[(int) obj.petitionerFile.length()];
                DataInputStream petitionerStream = new DataInputStream(new FileInputStream(obj.petitionerFile));
                petitionerStream.readFully(petitionerData);  // read from file into byte[] array
                petitionerStream.close();

                byte[] respondentData = new byte[(int) obj.respondentFile.length()];
                DataInputStream respondentStream = new DataInputStream(new FileInputStream(obj.respondentFile));
                respondentStream.readFully(respondentData);  // read from file into byte[] array
                respondentStream.close();
                
                String petitionerFileType = new FileType().getLastToken(obj.petitionerFileName, "\\.");
                String respondentFileType = new FileType().getLastToken(obj.respondentFileName, "\\.");
                //petitioner_respondent_info/
                PreparedStatement ps = connect.prepareStatement("UPDATE `petitioner_respondent_info` SET `petitioner_name`=?,`petitioner_email`=?,`petitioner_address`=?,`petitioner_file_type`=?,`petitioner_file`=?,`respondent_name`=?,`respondent_email`=?,`respondent_address`=?,`respondent_file_type`=?,`respondent_file`=? WHERE `file_number`=?");
                ps.setString(1, obj.petitionerName);
                ps.setString(2, obj.petitionerEmail);
                ps.setString(3, obj.petitionerAddress);
                ps.setString(4, petitionerFileType);
                ps.setBytes(5, petitionerData);
                ps.setString(6, obj.respondentName);
                ps.setString(7, obj.respondentEmail);
                ps.setString(8, obj.respondentAddress);
                ps.setString(9, respondentFileType);
                ps.setBytes(10, respondentData);
                ps.setString(11, obj.fileNumberField);
                ps.executeUpdate();
            }
            
            //date_info
            stateObj.executeUpdate("UPDATE `date_info` SET `case_filed_on`='"+obj.datePickerCaseFiledOn+"',`notice_received_on`='"+obj.datePickerNoticeReceivedOn+"',`first_hearing_on`='"+obj.datePickerFirstHearingField+"' WHERE `file_number`='"+obj.fileNumberField+"'");
            
            //case_proceeding
            System.out.println(previousObject.decision+" check "+obj.decision);
            System.out.println(previousObject.datePickerProceeding+" check "+obj.datePickerProceeding);
            if(previousObject.decision.equals(obj.decision) && previousObject.datePickerProceeding.equals(obj.datePickerProceeding)){
                System.out.println(previousObject.decision+" "+obj.decision);
                System.out.println(previousObject.datePickerProceeding+" "+obj.datePickerProceeding);
                System.out.println("Same proceeding data, other data will be updated");
            }
            else{
                if(!previousObject.decision.equals("PENDING")){
                    if(obj.decision.equals("ADJOURNMENT")){
                        stateObj.executeUpdate("INSERT INTO `case_proceeding` (`file_number`,`proceeding_number`,`proceeding_date`,`description`,`decision`,`next_hearing_on`) VALUES ('"+obj.fileNumberField+"','"+obj.proceedingNumber+"','"+obj.datePickerProceeding+"','"+obj.description+"','"+obj.decision+"','"+obj.datePickerNextHearingField+"')");
                        stateObj.executeUpdate("UPDATE `latest_proceeding` SET `proceeding_number`='"+obj.proceedingNumber+"',`proceeding_date`='"+obj.datePickerProceeding+"',`description`='"+obj.description+"',`decision`='"+obj.decision+"',`next_hearing_on`='"+obj.datePickerNextHearingField+"',`order_file`=NULL,`order_file_type`=NULL WHERE `file_number`='"+obj.fileNumberField+"'");
                    }
    //                else if(obj.decision.equals("PENDING")){
    //                    stateObj.executeUpdate("INSERT INTO CASE_PROCEEDING (FILE_NUMBER,PROCEEDING_NUMBER,PROCEEDING_DATE,DESCRIPTION,DECISION) VALUES ('"+obj.fileNumberField+"','"+obj.proceedingNumber+"','"+obj.datePickerProceeding+"','"+obj.description+"','"+obj.decision+"')");
    //                    stateObj.executeUpdate("UPDATE LATEST_PROCEEDING SET PROCEEDING_NUMBER='"+obj.proceedingNumber+"',PROCEEDING_DATE='"+obj.datePickerProceeding+"',DESCRIPTION='"+obj.description+"',DECISION='"+obj.decision+"',NEXT_HEARING_ON=NULL,ORDER_FILE=NULL,ORDER_FILE_TYPE=NULL WHERE FILE_NUMBER='"+obj.fileNumberField+"'");
    //                }
                    else if(obj.decision.equals("FINAL JUDGEMENT")){
                        byte[] orderData = new byte[(int) obj.orderFile.length()];
                        DataInputStream orderStream = new DataInputStream(new FileInputStream(obj.orderFile));
                        orderStream.readFully(orderData);  // read from file into byte[] array
                        orderStream.close();

                        String orderFileType = new FileType().getLastToken(obj.orderFileName, "\\.");

                        PreparedStatement ps1 = connect.prepareStatement("INSERT INTO `case_proceeding` (`file_number`,`proceeding_number`,`proceeding_date`,`description`,`decision`,`order_file`,`order_file_type`) VALUES (?,?,?,?,?,?,?)");
                        ps1.setString(1, obj.fileNumberField);
                        ps1.setInt(2, obj.proceedingNumber);
                        ps1.setString(3, obj.datePickerProceeding);
                        ps1.setString(4, obj.description);
                        ps1.setString(5, obj.decision);
                        ps1.setBytes(6, orderData);
                        ps1.setString(7, orderFileType);
                        ps1.executeUpdate();

                        PreparedStatement ps2 = connect.prepareStatement("UPDATE LATEST_PROCEEDING SET PROCEEDING_NUMBER='"+obj.proceedingNumber+"',PROCEEDING_DATE=?,DECISION=?,DESCRIPTION=?,NEXT_HEARING_ON=NULL,ORDER_FILE=?,ORDER_FILE_TYPE=? WHERE FILE_NUMBER=?");               
                        ps2.setString(1, obj.datePickerProceeding);
                        ps2.setString(2, obj.decision);
                        ps2.setString(3, obj.description);
                        ps2.setBytes(4, orderData);
                        ps2.setString(5, orderFileType);
                        ps2.setString(6, obj.fileNumberField);
                        ps2.executeUpdate();
                    }
                    else if(obj.decision.equals("INTERIM ORDER")){
                        byte[] orderData = new byte[(int) obj.orderFile.length()];
                        DataInputStream orderStream = new DataInputStream(new FileInputStream(obj.orderFile));
                        orderStream.readFully(orderData);  // read from file into byte[] array
                        orderStream.close();

                        String orderFileType = new FileType().getLastToken(obj.orderFileName, "\\.");

                        PreparedStatement ps1 = connect.prepareStatement("INSERT INTO `case_proceeding` (`file_number`,`proceeding_number`,`proceeding_date`,`description`,`decision`,`next_hearing_on`,`order_file`,`order_file_type`) VALUES (?,?,?,?,?,?,?,?)");
                        ps1.setString(1, obj.fileNumberField);
                        ps1.setInt(2, obj.proceedingNumber);
                        ps1.setString(3, obj.datePickerProceeding);
                        ps1.setString(4, obj.description);
                        ps1.setString(5, obj.decision);
                        ps1.setString(6, obj.datePickerNextHearingField);
                        ps1.setBytes(7, orderData);
                        ps1.setString(8, orderFileType);
                        ps1.executeUpdate();

                        PreparedStatement ps2 = connect.prepareStatement("UPDATE `latest_proceeding` SET `proceeding_number`='"+obj.proceedingNumber+"',`proceeding_date`=?,`decision`=?,`description`=?,`next_hearing_on`=?,`order_file`=?,`order_file_type`=? WHERE `file_number`=?");
                        ps2.setString(1, obj.datePickerProceeding);
                        ps2.setString(2, obj.decision);
                        ps2.setString(3, obj.description);
                        ps2.setString(4, obj.datePickerNextHearingField);
                        ps2.setBytes(5, orderData);
                        ps2.setString(6, orderFileType);
                        ps2.setString(7, obj.fileNumberField);
                        ps2.executeUpdate();
                    }
                }
                else{
                    if(obj.decision.equals("ADJOURNMENT")){
                        stateObj.executeUpdate("UPDATE `case_proceeding` SET `proceeding_date`='"+obj.datePickerProceeding+"',`description`='"+obj.description+"',`decision`='"+obj.decision+"',NEXT_HEARING_ON='"+obj.datePickerNextHearingField+"',ORDER_FILE=NULL,ORDER_FILE_TYPE=NULL WHERE FILE_NUMBER='"+obj.fileNumberField+"'");
                        stateObj.executeUpdate("UPDATE `latest_proceeding` SET `proceeding_number`=1,`proceeding_date`='"+obj.datePickerProceeding+"',`description`='"+obj.description+"',`decision`='"+obj.decision+"',`next_hearing_on`='"+obj.datePickerNextHearingField+"',`order_file`=NULL,`order_file_type`=NULL WHERE `file_number`='"+obj.fileNumberField+"'");
                    }
    //                else if(obj.decision.equals("PENDING")){
    //                    JOptionPane.showMessageDialog(new JPanel(), "Decision can't be pending after first proceeding");
    //                    stateObj.executeUpdate("INSERT INTO CASE_PROCEEDING (FILE_NUMBER,PROCEEDING_NUMBER,PROCEEDING_DATE,DESCRIPTION,DECISION) VALUES ('"+obj.fileNumberField+"','"+obj.proceedingNumber+"','"+obj.datePickerProceeding+"','"+obj.description+"','"+obj.decision+"')");
    //                    stateObj.executeUpdate("UPDATE LATEST_PROCEEDING SET PROCEEDING_DATE='"+obj.datePickerProceeding+"',DESCRIPTION='"+obj.description+"',DECISION='"+obj.decision+"',NEXT_HEARING_ON=NULL,ORDER_FILE=NULL,ORDER_FILE_TYPE=NULL WHERE FILE_NUMBER='"+obj.fileNumberField+"'");
    //                }
                    else if(obj.decision.equals("FINAL JUDGEMENT")){
                        byte[] orderData = new byte[(int) obj.orderFile.length()];
                        DataInputStream orderStream = new DataInputStream(new FileInputStream(obj.orderFile));
                        orderStream.readFully(orderData);  // read from file into byte[] array
                        orderStream.close();

                        String orderFileType = new FileType().getLastToken(obj.orderFileName, "\\.");

                        PreparedStatement ps1 = connect.prepareStatement("UPDATE `case_proceeding` SET `proceeding_date`=?,`description`=?,`decision`=?,`next_hearing_on`=NULL,`order_file`=?,`order_file_type`=? WHERE `file_number`=?");
                        ps1.setString(1, obj.datePickerProceeding);
                        ps1.setString(2, obj.description);
                        ps1.setString(3, obj.decision);
                        ps1.setBytes(4, orderData);
                        ps1.setString(5, orderFileType);
                        ps1.setString(6, obj.fileNumberField);
                        ps1.executeUpdate();

                        PreparedStatement ps2 = connect.prepareStatement("UPDATE `latest_proceeding` SET `proceeding_number`=1,`proceeding_date`=?,`decision`=?,`description`=?,`next_hearing_on`=NULL,`order_file`=?,`order_file_type`=? WHERE `file_number`=?");               
                        ps2.setString(1, obj.datePickerProceeding);
                        ps2.setString(2, obj.decision);
                        ps2.setString(3, obj.description);
                        ps2.setBytes(4, orderData);
                        ps2.setString(5, orderFileType);
                        ps2.setString(6, obj.fileNumberField);
                        ps2.executeUpdate();
                    }
                    else if(obj.decision.equals("INTERIM ORDER")){
                        byte[] orderData = new byte[(int) obj.orderFile.length()];
                        DataInputStream orderStream = new DataInputStream(new FileInputStream(obj.orderFile));
                        orderStream.readFully(orderData);  // read from file into byte[] array
                        orderStream.close();

                        String orderFileType = new FileType().getLastToken(obj.orderFileName, "\\.");

                        PreparedStatement ps1 = connect.prepareStatement("UPDATE `case_proceeding` SET `proceeding_date`=?,`description`=?,`decision`=?,`next_hearing_on`=?,`order_file`=?,`order_file_type`=? WHERE `file_number`=?");
                        ps1.setString(1, obj.datePickerProceeding);
                        ps1.setString(2, obj.description);
                        ps1.setString(3, obj.decision);
                        ps1.setString(4, obj.datePickerNextHearingField);
                        ps1.setBytes(5, orderData);
                        ps1.setString(6, orderFileType);
                        ps1.setString(7, obj.fileNumberField);
                        ps1.executeUpdate();

                        PreparedStatement ps2 = connect.prepareStatement("UPDATE `latest_proceeding` SET `proceeding_number`=1,`proceeding_date`=?,`decision`=?,`description`=?,`next_hearing_on`=?,`order_file`=?,`order_file_type`=? WHERE `file_number`=?");               
                        ps2.setString(1, obj.datePickerProceeding);
                        ps2.setString(2, obj.decision);
                        ps2.setString(3, obj.description);
                        ps2.setString(3, obj.datePickerNextHearingField);
                        ps2.setBytes(4, orderData);
                        ps2.setString(5, orderFileType);
                        ps2.setString(6, obj.fileNumberField);
                        ps2.executeUpdate();
                    }
                }
            }
            JOptionPane.showMessageDialog(new JPanel(), "File Number updated");
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            connect.close();
            stateObj.close();
        }
    }

    RetrieveCaseInfoClass fetchCaseInfo(String fileNumberField) throws SQLException, ClassNotFoundException  {
        System.out.println(fileNumberField);

        try{
            createDatabaseConnection();

            rs1 = stateObj.executeQuery("SELECT * FROM `case_info` WHERE `file_number` = '"+fileNumberField+"'");
            if(rs1.next()){
                RetrieveCaseInfoClass obj = new RetrieveCaseInfoClass();
                obj.caseNumber = rs1.getString("case_number");
                obj.fileNumberField = rs1.getString("file_number");
                obj.courtList = rs1.getString("court_name");
                obj.caseTypeList = rs1.getString("case_type");
                obj.yearList = rs1.getString("year");
                obj.locationList = rs1.getString("location");
                obj.lawyer = rs1.getString("lawyer");
                return obj;
            }
        }catch(SQLException se){
            
        }finally{
            connect.close();
            stateObj.close();
        }
        return null;
    }

    RetrieveCaseInfoClass fetchPetipane(String fileNumber, int x) throws SQLException, ClassNotFoundException, IOException {
        try{
            createDatabaseConnection();
            //value of x is used to control download of files when used in development of report; when x != 1, in case of report generation, files will not be downloaded
            rs2 = stateObj.executeQuery("SELECT * FROM `petitioner_respondent_info` WHERE `file_number` = '"+fileNumber+"'");
            
            if(rs2.next()){
                RetrieveCaseInfoClass obj = new RetrieveCaseInfoClass();
                obj.petitionerName = rs2.getString("petitioner_name");
                obj.petitionerEmail = rs2.getString("petitioner_email");
                obj.petitionerAddress = rs2.getString("petitioner_address");
                obj.petitionerFileName = "FileNumber"+fileNumber+"_Petitioner";
                obj.petitionerFileType = rs2.getString("petitioner_file_type");
                if(obj.petitionerFileType != null){
                    obj.petitionerFileType = obj.petitionerFileType.substring(obj.petitionerFileType.lastIndexOf("/") + 1);
                    obj.petitionerFileName = obj.petitionerFileName +"."+ obj.petitionerFileType;
                }
                
                obj.respondentName = rs2.getString("respondent_name");
                obj.respondentEmail = rs2.getString("respondent_email");
                obj.respondentAddress = rs2.getString("respondent_address");
                obj.respondentFileName = "FileNumber"+fileNumber+"_Respondent";
                obj.respondentFileType = rs2.getString("respondent_file_type");
                if(obj.respondentFileType != null){
                    obj.respondentFileType = obj.respondentFileType.substring(obj.respondentFileType.lastIndexOf("/") + 1);
                    obj.respondentFileName = obj.respondentFileName +"."+ obj.respondentFileType;
                }
                if(x == 1){
                    Blob petitionerBlob = rs2.getBlob("petitioner_file");
    //                if(rs2.getBlob("PETITIONER_FILE") == null){
    //                    obj.petitionerFile = null;
    //                    obj.petitionerFileName = "No file selected";
    //                }
    //                else{
                        if(petitionerBlob == null){
                            obj.petitionerFile = null;
                            obj.petitionerFileName = "No file added";
                        }
                        else{
                            String home = System.getProperty("user.home");
                            Path fileToDeletePath = Paths.get(home+"/Downloads/"+fileNumber+"/"+obj.petitionerFileName);
                            if(new File(home+"/Downloads/"+fileNumber+"/"+obj.petitionerFileName).exists()){
                                Files.delete(fileToDeletePath);
                            }
                            Path pathToFile = Paths.get(home+"/Downloads/"+fileNumber+"/"+obj.petitionerFileName);
                            Files.createDirectories(pathToFile.getParent());
                            Files.createFile(pathToFile);
                            obj.petitionerFile = new File(home+"/Downloads/"+fileNumber+"/"+obj.petitionerFileName);
                            FileOutputStream petitionerOutput = new FileOutputStream(obj.petitionerFile);
                            System.out.println("Writing to file " + obj.petitionerFile.getAbsolutePath());
                            InputStream petitionerInput = petitionerBlob.getBinaryStream();
                            int b1 = 0;
                            while ((b1 = petitionerInput.read()) != -1)
                            {
                                petitionerOutput.write(b1);
                            }
                            JOptionPane.showMessageDialog(new JPanel(), "File "+obj.petitionerFileName+" downloaded at "+obj.petitionerFile.getAbsolutePath());
                        }
                        
    //                }


                    Blob respondentBlob = rs2.getBlob("respondent_file");
    //                if(rs2.getBlob("RESPONDENT_FILE") == null){
    //                    obj.respondentFile = null;
    //                    obj.respondentFileName = "No file selected";
    //                }
    //                else{
                        if(respondentBlob == null){
                            obj.respondentFile = null;
                            obj.respondentFileName = "No file added";
                        }
                        else{
                            String home = System.getProperty("user.home");
                            Path fileToDeletePath = Paths.get(home+"/Downloads/"+fileNumber+"/"+obj.respondentFileName);
                            if(new File(home+"/Downloads/"+fileNumber+"/"+obj.respondentFileName).exists()){
                                Files.delete(fileToDeletePath);
                            }
                            
                            Path pathToFile = Paths.get(home+"/Downloads/"+fileNumber+"/"+obj.respondentFileName);
                            Files.createDirectories(pathToFile.getParent());
                            Files.createFile(pathToFile);
                            obj.respondentFile = new File(home+"/Downloads/"+fileNumber+"/"+obj.respondentFileName);
                            FileOutputStream respondentOutput = new FileOutputStream(obj.respondentFile);
                            System.out.println("Writing to file " + obj.respondentFile.getAbsolutePath());
                            InputStream respondentInput = respondentBlob.getBinaryStream();
                            int b2 = 0;
                            while ((b2 = respondentInput.read()) != -1)
                            {
                                respondentOutput.write(b2); 
                            }
                            JOptionPane.showMessageDialog(new JPanel(), "File "+obj.respondentFileName+" downloaded at "+obj.respondentFile.getAbsolutePath());
                        }
                        
    //                }
                }
                return obj;
            }
        }catch(SQLException se){
        
        }finally{
            connect.close();
            stateObj.close();
        }
        return null;
    }
    
//    public void downloadFile(int x, String fileNumber) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException{
//        createDatabaseConnection();
//        switch(x){
//            case 1: {
//                rs = stateObj.executeQuery("SELECT * FROM PETITIONER_RESPONDENT_INFO WHERE FILE_NUMBER='"+fileNumber+"'");
//                Blob petitionerBlob = rs.getBlob("PETITIONER_FILE");
////                if(rs2.getBlob("PETITIONER_FILE") == null){
////                    obj.petitionerFile = null;
////                    obj.petitionerFileName = "No file selected";
////                }
////                else{
//                    File petitionerFile = new File(fileNumber+"_Petitioner.pdf");
//                    FileOutputStream petitionerOutput = new FileOutputStream(petitionerFile);
//                    System.out.println("Writing to file " + petitionerFile.getAbsolutePath());
//                    InputStream petitionerInput = petitionerBlob.getBinaryStream();
//                    int b1 = 0;
//                    while ((b1 = petitionerInput.read()) != -1)
//                    {
//                        petitionerOutput.write(b1);
//                    }
//                    JOptionPane.showMessageDialog(new JPanel(), "File "+fileNumber+"_Petitioner.pdf downloaded at "+petitionerFile.getAbsolutePath());
////                }
//                    break;
//            }
//            case 2: {
//                rs = stateObj.executeQuery("SELECT * FROM PETITIONER_RESPONDENT_INFO WHERE FILE_NUMBER='"+fileNumber+"'");
//                Blob respondentBlob = rs.getBlob("RESPONDENT_FILE");
////                if(rs2.getBlob("RESPONDENT_FILE") == null){
////                    obj.respondentFile = null;
////                    obj.respondentFileName = "No file selected";
////                }
////                else{
//                    File respondentFile = new File(fileNumber+"_Respondent.pdf");
//                    FileOutputStream respondentOutput = new FileOutputStream(respondentFile);
//                    System.out.println("Writing to file " + respondentFile.getAbsolutePath());
//                    InputStream respondentInput = respondentBlob.getBinaryStream();
//                    int b2 = 0;
//                    while ((b2 = respondentInput.read()) != -1)
//                    {
//                        respondentOutput.write(b2); 
//                    }
//                    JOptionPane.showMessageDialog(new JPanel(), "File "+fileNumber+"_Respondent.pdf downloaded at "+respondentFile.getAbsolutePath());
////                }
//                    break;
//            }
//        }
//        connect.close();
//        stateObj.close();
//    }

    RetrieveCaseInfoClass fetchCaseProceed(String fileNumber) throws SQLException, ClassNotFoundException, IOException {
        try{
            createDatabaseConnection();
//            rs2 = stateObj.executeQuery("SELECT * FROM CASE_PROCEEDING WHERE FILE_NUMBER='"+fileNumber+"' AND PROCEEDING_NUMBER=(SELECT MAX(PROCEEDING_NUMBER) FROM CASE_PROCEEDING WHERE FILE_NUMBER='"+fileNumber+"')");
//            if(rs2.next()){
//                RetrieveCaseInfoClass obj = new RetrieveCaseInfoClass();
//                obj.datePickerProceeding = rs2.getString("PROCEEDING_DATE");
////                obj.lawyer = rs2.getString("LAWYER");
//                obj.decision = rs2.getString("DECISION");
//                obj.proceedingNumber = rs2.getInt("PROCEEDING_NUMBER");
//                return obj;
//            }
//            else if(!rs.next()){
//                RetrieveCaseInfoClass obj = new RetrieveCaseInfoClass();
//                obj.proceedingNumber = 0;
//                obj.datePickerProceeding = "";
////                obj.lawyer = "";
//                obj.decision = "";       
//                return obj;
//            }
            rs2 = stateObj.executeQuery("SELECT * FROM `case_proceeding` WHERE `file_number`='"+fileNumber+"' AND `proceeding_number`=(SELECT MAX(`proceeding_number`) FROM `case_proceeding` WHERE `file_number`='"+fileNumber+"')");
            RetrieveCaseInfoClass obj = new RetrieveCaseInfoClass();
            if(rs2.next()){
                obj.proceedingNumber = rs2.getInt("proceeding_number");
                obj.datePickerProceeding = rs2.getString("proceeding_date");
                obj.decision = rs2.getString("decision");
                obj.description = rs2.getString("description");
                obj.datePickerNextHearingField = rs2.getString("next_hearing_on");
                System.out.println(fileNumber+" "+obj.proceedingNumber+" "+obj.datePickerNextHearingField+" "+obj.decision+" "+obj.datePickerProceeding);

                if(obj.decision.equals("ADJOURNMENT")){
                    obj.datePickerNextHearingField = rs2.getString("next_hearing_on");
                    obj.orderFile = null;
                }
                else if(obj.decision.equals("PENDING")){
                    obj.datePickerNextHearingField = null;
                    obj.orderFile = null;
                }
                else if(obj.decision.equals("FINAL JUDGEMENT")){
                    //uncomment to allow download of files at the case details tab
//                    Blob orderBlob = rs2.getBlob("ORDER_FILE");
                    String type = rs2.getString("order_file_type");
                    String filetype = type.substring(type.lastIndexOf("/") + 1);
//                    String home = System.getProperty("user.home");
//                    String fileName = home+"/Downloads/"+fileNumber+"/FileNumber"+fileNumber+"_Order_"+obj.proceedingNumber+"."+filetype;
//                    Path fileToDeletePath = Paths.get(fileName);
//                    if(new File(fileName).exists()){
//                        Files.delete(fileToDeletePath);
//                    }
//
//                    Path pathToFile = Paths.get(fileName);
//                    Files.createDirectories(pathToFile.getParent());
//                    Files.createFile(pathToFile);
//                    obj.orderFile = new File(fileName);
//                    FileOutputStream respondentOutput = new FileOutputStream(obj.orderFile);
//                    System.out.println("Writing to file " + obj.orderFile.getAbsolutePath());
//                    InputStream orderInput = orderBlob.getBinaryStream();
//                    int b2 = 0;
//                    while ((b2 = orderInput.read()) != -1)
//                    {
//                        respondentOutput.write(b2); 
//                    }
                    obj.orderFileName = "FileNumber"+fileNumber+"_Order_"+obj.proceedingNumber+"."+filetype;
//                    JOptionPane.showMessageDialog(new JPanel(), "File "+obj.orderFileName+" downloaded at "+obj.orderFile.getAbsolutePath());
                }
                else if(obj.decision.equals("INTERIM ORDER")){
                    obj.datePickerNextHearingField = rs2.getString("next_hearing_on");
                    String type = rs2.getString("order_file_type");
                    String filetype = type.substring(type.lastIndexOf("/") + 1);
                    obj.orderFileName = "FileNumber"+fileNumber+"_Order_"+obj.proceedingNumber+"."+filetype;
                }
            }            
            return obj;
        }catch(SQLException se){
        }
        finally{
            connect.close();
            stateObj.close();
        }
        return null;
    }

    RetrieveCaseInfoClass fetchCasedate(String fileNumber) throws SQLException, ClassNotFoundException {
        try{
            createDatabaseConnection();
            rs3 = stateObj.executeQuery("SELECT * FROM `date_info` WHERE `file_number` = '"+fileNumber+"'");
            if(rs3.next()){
                RetrieveCaseInfoClass obj = new RetrieveCaseInfoClass();
                if(rs3.getString("case_filed_on").equals("1947-08-15")){
                    obj.datePickerCaseFiledOn = null;
                }
                else{                    
                    obj.datePickerCaseFiledOn = rs3.getString("case_filed_on");
                }
                if(rs3.getString("first_hearing_on").equals("1947-08-15")){
                    obj.datePickerFirstHearingField = null;
                }
                else{
                    obj.datePickerFirstHearingField = rs3.getString("first_hearing_on");
                }
                if(rs3.getString("notice_received_on").equals("1947-08-15")){
                    obj.datePickerNoticeReceivedOn = null;
                }
                else{
                    obj.datePickerNoticeReceivedOn = rs3.getString("notice_received_on");
                }
//                obj.datePickerNextHearingField = rs3.getString("NEXT_HEARING_ON");
                return obj;
            }
        }catch(SQLException se){
             se.printStackTrace();
        }finally{
            connect.close();
            stateObj.close();
        }
        return null;
    }
    
//    RetrieveCaseInfoClass fetchSummary(String text) throws SQLException{
//        try{
//            rs4 = stateObj.executeQuery("SELECT * FROM CASE_SUMMARY WHERE FILE_NUMBER = '"+text+"'");
//            if(rs4.next()){
//                RetrieveCaseInfoClass obj = new RetrieveCaseInfoClass();
//                obj.summaryInPrayer = rs4.getString("SUMMARY_PRAYER");
//                obj.generalRemarks = rs4.getString("GENERAL_REMARKS)");
//                obj.otherLinks = rs4.getString("OTHER_LINKS");
//                
//                return obj;
//            }
//            
//        }catch(SQLException se){
//            se.printStackTrace();
//        }finally{
//            if(stateObj!=null){
//                stateObj.close();
//            }
//        }
//        return (new RetrieveCaseInfoClass());
//    }

    public void fetchOrderFiles(String fileNumber) throws ClassNotFoundException, SQLException, IOException{
        try{
            createDatabaseConnection();
            System.out.println(fileNumber);
            rs = stateObj.executeQuery("SELECT * FROM `case_proceeding` WHERE `file_number`='"+fileNumber+"'");
            int flag = 0;
            while(rs.next()){
                if(!rs.getString("decision").equals("ADJOURNMENT")){
                    flag = 1;
                    String proceedingNumber = rs.getString("proceeding_number");
                    
                    Blob orderBlob = rs.getBlob("order_file");
                    String type = rs.getString("order_file_type");
                    String filetype = type.substring(type.lastIndexOf("/") + 1);
                    String home = System.getProperty("user.home");
                    String fileName = home+"/Downloads/"+fileNumber+"/FileNumber"+fileNumber+"_Order_"+proceedingNumber+"."+filetype;
                    Path fileToDeletePath = Paths.get(fileName);
                    if(new File(fileName).exists()){
                        Files.delete(fileToDeletePath);
                    }

                    Path pathToFile = Paths.get(fileName);
                    Files.createDirectories(pathToFile.getParent());
                    Files.createFile(pathToFile);
                    File orderFile = new File(fileName);
                    FileOutputStream respondentOutput = new FileOutputStream(orderFile);
                    System.out.println("Writing to file " + orderFile.getAbsolutePath());
                    InputStream orderInput = orderBlob.getBinaryStream();
                    int b2 = 0;
                    while ((b2 = orderInput.read()) != -1)
                    {
                        respondentOutput.write(b2); 
                    }
                    String orderFileName = "FileNumber"+fileNumber+"_Order_"+proceedingNumber+"."+filetype;
                    JOptionPane.showMessageDialog(new JPanel(), "File "+orderFileName+" downloaded at "+orderFile.getAbsolutePath());
                    orderFile = null;
                    orderFileName = null;
                }
            }
            if(flag == 0){
                JOptionPane.showMessageDialog(new JPanel(), "No Order Files for this File Number");
            }
        }catch(SQLException se){
             se.printStackTrace();
        }finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public int numberOfProceedings(String fileNumber) throws SQLException, ClassNotFoundException{
        try{
            createDatabaseConnection();
            System.out.println(fileNumber);
            rs = stateObj.executeQuery("SELECT COUNT(proceeding_number) FROM `case_proceeding` WHERE `file_number`='"+fileNumber+"'");
            if(rs.next()){
                return rs.getInt("COUNT(proceeding_number)");
            }         
        }catch(SQLException se){
             se.printStackTrace();
        }finally{
            connect.close();
            stateObj.close();
        }
        return 0;
    }
    
    public String getReportData(String fileNumber, String username) throws ClassNotFoundException, SQLException, IOException, MessagingException{
        String beginning = "<html>\n<head>\n\t<title>File Number ";
        RetrieveCaseInfoClass r1 = fetchCaseInfo(fileNumber);
//        String caseInfoMessage = "File Number: "+r1.fileNumberField+"\nCase Number: "+r1.caseNumber+"\nYear: "+r1.yearList+"\nCase Type: "+r1.caseTypeList+"\nCourt Name: "+r1.courtList+"\nLawyer: "+r1.lawyer+"\n\n\n\n";
        
        String css = r1.fileNumberField+"</title>\n<style>\n\ttd{\n\tword-wrap: break-word;\n\tmax-width: 150px;\n\twidth: 150px;\n}\n</style>\n";
        
        String caseInfoMessage = "</head>\n<body>\nFile Number: "+r1.fileNumberField+"<br />\nCase Number: "+r1.caseNumber+"<br />\nYear: "+r1.yearList+"<br />\nNature of Case: "+r1.caseTypeList+"<br />\nCourt Name: "+r1.courtList+"<br />\nLawyer: "+r1.lawyer+"<br />\nLocation: "+r1.locationList+"<br />\n<hr />\n";
        
        RetrieveCaseInfoClass r2 = fetchCasedate(fileNumber);
//        String dateInfoMessage = "DATE INFO:\n\n\tCase Filed On: "+r2.datePickerCaseFiledOn+"\n\tNotice Received On: "+r2.datePickerNoticeReceivedOn+"\n\tFirst Hearing On: "+r2.datePickerFirstHearingField+"\n\n\n\n";
        String dateInfoMessage = "<h3>DATE INFO:</h3>\nCase Filed On: "+r2.datePickerCaseFiledOn+"<br />\nNotice Received On: "+r2.datePickerNoticeReceivedOn+"<br />\nFirst Hearing On: "+r2.datePickerFirstHearingField+"<br />\n<hr />\n";
        
        RetrieveCaseInfoClass r3 = fetchPetipane(fileNumber, 0);
//        String petitionerInfoMessage = "PETITIONER-RESPONDENT INFO:\n\n\tPetitioner Name: "+r3.petitionerName+"\n\tPetitioner Address: "+r3.petitionerAddress+"\n\n\tRespondent Name: "+r3.respondentName+"\n\tRespondent Address: "+r3.respondentAddress+"\n\n\n\n";
        String petitionerInfoMessage = "<h3>PETITIONER-RESPONDENT INFO:</h3>\n<table>\n\t<tr><th align=\"left\" valign=\"top\">Petitioner Name: </th><td>"+r3.petitionerName+"</td>\n<tr><th align=\"left\" valign=\"top\">Petitioner e-Mail: </th><td>"+r3.petitionerEmail+"</td>\n</tr><tr>\n\t<th align=\"left\" valign=\"top\">Petitioner Address: </th><td>"+r3.petitionerAddress+"</td>\n</tr>\n<tr>\n\t<td>&nbsp;</td><td>&nbsp;</td>\n</tr>\n<tr>\n\t<th align=\"left\" valign=\"top\">Respondent Name: </th><td>"+r3.respondentName+"</td></tr>\n<tr><th align=\"left\" valign=\"top\">Respondent e-Mail: </th><td>"+r3.respondentEmail+"</td>\n<tr>\n\t<th align=\"left\" valign=\"top\">Respondent Address: </th><td>"+r3.respondentAddress+"</td>\n</tr>\n</table>\n<hr />\n";
        int n = numberOfProceedings(fileNumber);
        System.out.println("number of proceedings: "+n);
//        String proceedingHeading = "PROCEEDING:\n\nProc. No.\t\tProc. Date\t\tDecision\t\tNext Hearing\n\n";
        String proceedingHeading = "<h3>PROCEEDING INFO:</h3>\n<table border=\"1\">\n<tr>\t<th>Proc. No.</th>\n\t<th>Proc. Date</th>\n\t<th>Decision</th>\n\t<th>Next Hearing</th>\n<th>Description</th>\n</tr>\n";
//        proceedingHeading = proceedingHeading.toUpperCase();
        String[] proceedingMessage = new String[n];
        createDatabaseConnection();
        rs = stateObj.executeQuery("SELECT * FROM `case_proceeding` WHERE `file_number`='"+fileNumber+"'");
        int i = 0;
        while(rs.next()){
            if(rs.getString("decision").equals("ADJOURNMENT") || rs.getString("decision").equals("INTERIM ORDER")){
//                proceedingMessage[i] = "\t"+rs.getString("PROCEEDING_NUMBER")+"\t\t"+rs.getString("PROCEEDING_DATE")+"\t\t"+rs.getString("DECISION")+"\t\t"+rs.getString("NEXT_HEARING_ON")+"\n\n";
                proceedingMessage[i] = "<tr>\t<td align=\"center\">"+rs.getString("proceeding_number")+"</td>\n\t<td align=\"center\">"+rs.getString("proceeding_date")+"</td>\n\t<td align=\"center\">"+rs.getString("decision")+"</td>\n\t<td align=\"center\">"+rs.getString("next_hearing_on")+"</td>\n\t<td align=\"center\">"+rs.getString("description")+"</td>\n</tr>\n";
            }
            else{
//                proceedingMessage[i] = "\t"+rs.getString("PROCEEDING_NUMBER")+"\t\t"+rs.getString("PROCEEDING_DATE")+"\t\t"+rs.getString("DECISION")+"\t\tNA\n\n";
                proceedingMessage[i] = "<tr>\t<td align=\"center\">"+rs.getString("proceeding_number")+"</td>\n\t<td align=\"center\">"+rs.getString("proceeding_date")+"</td>\n\t<td align=\"center\">"+rs.getString("decision")+"</td>\n\t<td align=\"center\">NA</td>\n\t<td align=\"center\">"+rs.getString("description")+"</td>\n</tr>\n";
            }
            i++;
        }
        
        StringBuilder strBuilder = new StringBuilder();
        for (String proceedingMessage1 : proceedingMessage) {
            strBuilder.append(proceedingMessage1);
        }
        String proceedingTable = strBuilder.toString();
        String proceedingInfoMessage = proceedingHeading + proceedingTable;
        String ending = "\t\t</table>\n\t</body>\n</html>";
//        String report = caseInfoMessage + dateInfoMessage + petitionerInfoMessage + proceedingInfoMessage;
        String report = beginning + css + caseInfoMessage + dateInfoMessage + petitionerInfoMessage + proceedingInfoMessage + ending;
        System.out.println(report);
        
//        EmailData obj = getEmailData(username);
//        sendEmail(obj.email, "Report for File Number "+fileNumber, report, username, "");
//        new SendMessage().messageFunction(report, obj.phoneNumber);
        connect.close();
        stateObj.close();
        return report;
    }
    
    EmailData getEmailData(String username) throws SQLException, ClassNotFoundException{
        try{
            createDatabaseConnection();
            System.out.println(username);
            rs = stateObj.executeQuery("SELECT * FROM `user_info` WHERE `username`='"+username+"'");
            if(rs.next()){
                EmailData obj = new EmailData();
                obj.email = rs.getString("email_add");
                obj.password = rs.getString("email_password");
                obj.phoneNumber = rs.getString("contact");
                System.out.println(obj.email+" "+obj.password+" "+obj.phoneNumber);
                return obj;
            }
         
        }catch(SQLException se){
             se.printStackTrace();
        }finally{
            connect.close();
            stateObj.close();
        }
        return null;
    }
    
    public void sendEmail(String to, String subject, String message, String username, String filename) throws ClassNotFoundException, SQLException, MessagingException{
        try{
            createDatabaseConnection();
            EmailData obj = getEmailData(username);
            System.out.println(username);
            
            EmailDemo emailObject = new EmailDemo();
            emailObject.sender(obj.email, obj.password, to, subject, message, filename);
        }catch(SQLException se){
             se.printStackTrace();
        }finally{
            connect.close();
            stateObj.close();
        }
    }
    
    JTable makeNotificationTable(String username, int numberOfDays) throws SQLException, MessagingException{
        try {
            EmailData obj = getEmailData(username);
            createDatabaseConnection();
            
            ArrayList<String[]>  tableData=new ArrayList<>();
            
            String[] columnNames =  {
                "File Number", "Case Type", "Next Hearing On"
            };
            
            
            rs = stateObj.executeQuery("SELECT * FROM `latest_proceeding` NATURAL JOIN `case_info` WHERE NOT (next_hearing_on <=> NULL)");
            Date today = new Date();
            Date dayWanted = new Date(today.getTime()+ numberOfDays*(1000 * 60 * 60 * 24) + (1000 * 60 * 60 * 24));
            
            while (rs.next()){
                System.out.println(today);
                System.out.println(rs.getString("next_hearing_on"));
                System.out.println(dayWanted+"\n");
                if(rs.getDate("next_hearing_on").after(today) && rs.getDate("next_hearing_on").before(dayWanted)){
                    System.out.println(rs.getString("file_number"));
                    tableData.add(new String[]{rs.getString("file_number"), rs.getString("case_type"), rs.getString("next_hearing_on")});
                    System.out.println("gotcha");
                    EmailDemo mail = new EmailDemo();
                    String subject = "Attention for File Number "+rs.getString("file_number");
                    String message = "Next Hearing Date approaching\nFile Number: "+rs.getString("file_number")+",\nCase Type "+rs.getString("case_type")+",\nNext Hearing On: "+rs.getString("next_hearing_on");
//                    mail.sender(obj.email, obj.password, obj.email, subject, message, "");
//                    new SendMessage().messageFunction(message, obj.phoneNumber);
                }
                else{
                    System.out.println("next");
                }
            }
                    
            String[][] tableRows = tableData.toArray(new String[tableData.size()][]); // convert the ArrayList to a regular Array
            JTable table = new JTable( tableRows, columnNames );   // thats how you put the data into the JTable
            return table;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHandling.class.getName()).log(Level.SEVERE, null, ex);
        }catch (NullPointerException ex) {
            Logger.getLogger(DatabaseHandling.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try
            {
                connect.close();
                stateObj.close();
            }
            catch(SQLException se)
            {
                        
            }
        }        
        return null;
    }
    
    JTable makeOutputTable(int x, String check) throws SQLException {
        try {
            createDatabaseConnection();
            //here 'x' is used find out which radio button is selected; 'check' is the value through which data will be filtered
            ArrayList<String[]>  tableData=new ArrayList<>();
            
            String[] columnNames =  {
                "File Number", "Case Number", "Court Name", "Case Type", "Proceeding Number", "Proceeding Date", "Lawyer", "Year", "Decision", "Next Proceeding",
            };
            
            switch(x)
            {
                case 0:{ 
                    rs = stateObj.executeQuery("SELECT * FROM `case_info` NATURAL JOIN `latest_proceeding` WHERE `court_name`='"+check+"'");
                    
                    while ( rs.next() ){
                        if(rs.getString("decision").equals("ADJOURNMENT") || rs.getString("decision").equals("INTERIM ORDER")){
                            tableData.add(new String[]{rs.getString("file_number"), rs.getString("case_number"), rs.getString("court_name"), rs.getString("case_type"), rs.getString("proceeding_number"), rs.getString("proceeding_date"), rs.getString("lawyer"), rs.getString("year"), rs.getString("decision"), rs.getString("next_hearing_on")});
                        }
                        else{
                            tableData.add(new String[]{rs.getString("file_number"), rs.getString("case_number"), rs.getString("court_name"), rs.getString("case_type"), rs.getString("proceeding_number"), rs.getString("proceeding_date"), rs.getString("lawyer"), rs.getString("year"), rs.getString("decision"), "NA"});
                        }
                    }
                    break;
                }
                case 1:{
                    rs = stateObj.executeQuery("SELECT * FROM `case_info` NATURAL JOIN `latest_proceeding` WHERE `case_number`='"+check+"'");

                    while ( rs.next() ){
                        if(rs.getString("decision").equals("ADJOURNMENT") || rs.getString("decision").equals("INTERIM ORDER")){
                            tableData.add(new String[]{rs.getString("file_number"), rs.getString("case_number"), rs.getString("court_name"), rs.getString("case_type"), rs.getString("proceeding_number"), rs.getString("proceeding_date"), rs.getString("lawyer"), rs.getString("year"), rs.getString("decision"), rs.getString("next_hearing_on")});
                        }
                        else{
                            tableData.add(new String[]{rs.getString("file_number"), rs.getString("case_number"), rs.getString("court_name"), rs.getString("case_type"), rs.getString("proceeding_number"), rs.getString("proceeding_date"), rs.getString("lawyer"), rs.getString("year"), rs.getString("decision"), "NA"});
                        }
                    }
                    break;
                }
                case 2:{
                    rs = stateObj.executeQuery("SELECT * FROM `case_info` NATURAL JOIN `latest_proceeding` WHERE `file_number`='"+check+"'");
                    while ( rs.next() ){
                        if(rs.getString("decision").equals("ADJOURNMENT") || rs.getString("decision").equals("INTERIM ORDER")){
                            tableData.add(new String[]{rs.getString("file_number"), rs.getString("case_number"), rs.getString("court_name"), rs.getString("case_type"), rs.getString("proceeding_number"), rs.getString("proceeding_date"), rs.getString("lawyer"), rs.getString("year"), rs.getString("decision"), rs.getString("next_hearing_on")});
                        }
                        else{
                            tableData.add(new String[]{rs.getString("file_number"), rs.getString("case_number"), rs.getString("court_name"), rs.getString("case_type"), rs.getString("proceeding_number"), rs.getString("proceeding_date"), rs.getString("lawyer"), rs.getString("year"), rs.getString("decision"), "NA"});
                        }
                    }
                    break;
                }
                case 3:{
                    rs = stateObj.executeQuery("SELECT * FROM `case_info` NATURAL JOIN `latest_proceeding` WHERE `lawyer`='"+check+"'");
                    while ( rs.next() ){
                        if(rs.getString("decision").equals("ADJOURNMENT") || rs.getString("decision").equals("INTERIM ORDER")){
                            tableData.add(new String[]{rs.getString("file_number"), rs.getString("case_number"), rs.getString("court_name"), rs.getString("case_type"), rs.getString("proceeding_number"), rs.getString("proceeding_date"), rs.getString("lawyer"), rs.getString("year"), rs.getString("decision"), rs.getString("next_hearing_on")});
                        }
                        else{
                            tableData.add(new String[]{rs.getString("file_number"), rs.getString("case_number"), rs.getString("court_name"), rs.getString("case_type"), rs.getString("proceeding_number"), rs.getString("proceeding_date"), rs.getString("lawyer"), rs.getString("year"), rs.getString("decision"), "NA"});
                        }
                    }
                    break;
                }
                case 4:{ 
                    rs = stateObj.executeQuery("SELECT * FROM `case_info` NATURAL JOIN `latest_proceeding` WHERE `location`='"+check+"'");
                    
                    while ( rs.next() ){
                        if(rs.getString("decision").equals("ADJOURNMENT") || rs.getString("decision").equals("INTERIM ORDER")){
                            tableData.add(new String[]{rs.getString("file_number"), rs.getString("case_number"), rs.getString("court_name"), rs.getString("case_type"), rs.getString("proceeding_number"), rs.getString("proceeding_date"), rs.getString("lawyer"), rs.getString("year"), rs.getString("decision"), rs.getString("next_hearing_on")});
                        }
                        else{
                            tableData.add(new String[]{rs.getString("file_number"), rs.getString("case_number"), rs.getString("court_name"), rs.getString("case_type"), rs.getString("proceeding_number"), rs.getString("proceeding_date"), rs.getString("lawyer"), rs.getString("year"), rs.getString("decision"), "NA"});
                        }
                    }
                    break;
                }
                case 5:{ 
                    String after = check.substring(0, 10);
                    String before = check.substring(10);
                    System.out.println("Final Check After "+after+" Before "+before);
                    rs = stateObj.executeQuery("SELECT * FROM `case_info` NATURAL JOIN `latest_proceeding` WHERE `next_hearing_on` BETWEEN '"+after+"' AND '"+before+"'");
                    
                    while ( rs.next() ){
                        if(rs.getString("decision").equals("ADJOURNMENT") || rs.getString("decision").equals("INTERIM ORDER")|| rs.getString("decision").equals("PENDING")){
                            tableData.add(new String[]{rs.getString("file_number"), rs.getString("case_number"), rs.getString("court_name"), rs.getString("case_type"), rs.getString("proceeding_number"), rs.getString("proceeding_date"), rs.getString("lawyer"), rs.getString("year"), rs.getString("decision"), rs.getString("next_hearing_on")});
                        }
                        else{
                            tableData.add(new String[]{rs.getString("file_number"), rs.getString("case_number"), rs.getString("court_name"), rs.getString("case_type"), rs.getString("proceeding_number"), rs.getString("proceeding_date"), rs.getString("lawyer"), rs.getString("year"), rs.getString("decision"), "NA"});
                        }
                    }
                    break;
                }
            }
            String[][] tableRows = tableData.toArray(new String[tableData.size()][]); // convert the ArrayList to a regular Array
            JTable table = new JTable( tableRows, columnNames );   // thats how you put the data into the JTable
            return table;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHandling.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try
            {
                connect.close();
                stateObj.close();
            }
            catch(SQLException se)
            {
                        
            }
        }        
        return null;
    }
    
    public boolean isUsernameExisting(String username) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            System.out.println(username);
            rs = stateObj.executeQuery("SELECT `username` FROM `user_info` WHERE `username` ='" + username + "'");
            if(rs.next())
            {
                if (rs.getString("username").equals(username))
                {
                    System.out.println("user exists");
                    return true;
                }
                else
                {
                    System.out.println("create new user");
                    return false;
                }
            }
            System.out.println("nothing done");
            return false;
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public boolean isExisting(String username, String password, String userGroup) throws ClassNotFoundException, SQLException{
        try{
            createDatabaseConnection();
            System.out.println(username);
            System.out.println(password);
            System.out.println(userGroup);
            rs = stateObj.executeQuery("SELECT `username`, `password`, `user_group` FROM `user_info` WHERE `username`='" + username + "' AND `password`='"+password+"' AND `user_group`='"+userGroup+"'");
            if(rs.next())
            {
                if (rs.getString("username").equals(username) && rs.getString("password").equals(password) && rs.getString("user_group").equals(userGroup))
                {
                    System.out.println("record verified");
                    return true;
                }
                else
                {
                    System.out.println("record not verified");
                    return false;
                }
            }
            System.out.println("nothing done");
            return false;
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    // for creating a new user
    public int createNewUser(String userGroup, String fullName, /*String contact,*/ String email, String emailPassword,/* String city,*/ String username, String password) throws ClassNotFoundException, SQLException{
        try{
            if(isUsernameExisting(username))
            {
                System.out.println("User Already exists");
                return 1;
            }
            createDatabaseConnection();
            stateObj.executeUpdate("INSERT INTO `user_info` (`username`, `password`, `full_name`, `email_add`, `email_password` ,`user_group`) VALUES ('"+username+"','"+password+"','"+fullName+"','"+email+"','"+emailPassword+"','"+userGroup+"')");
            if(isExisting(username, password, userGroup)){
                System.out.println("User Added");
                return 0;
            }
            else{
                System.out.println("Error");
                return 2;
            }
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public int changePassword(String username, String userGroup, String oldPassword, String newPassword) throws ClassNotFoundException, SQLException{
        try{
            if(isExisting(username, oldPassword, userGroup)){
                createDatabaseConnection();
                String query = "UPDATE `user_info` SET `password`=? WHERE `username`=?";
                PreparedStatement preparedStatement = connect.prepareStatement(query);
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, username);
                preparedStatement.executeUpdate();
                return 1;
            }
            else{
                System.out.println("not changed");
                return 0;
            }
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public void updatePermissions(String username, String usergroup) throws ClassNotFoundException, SQLException{
        createDatabaseConnection();
        try{
            createDatabaseConnection();
            String query = "UPDATE `user_info` SET `user_group`=? WHERE `username`=?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, usergroup);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
    
    public int deleteUser(String username) throws ClassNotFoundException, SQLException{
        createDatabaseConnection();
        try{
            createDatabaseConnection();
            String query = "DELETE FROM `user_info` WHERE `username`=?";
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            if(!isUsernameExisting(username)){
                return 0;
            }
            else{
                return 1;
            }
        }
        finally{
            connect.close();
            stateObj.close();
        }
    }
}