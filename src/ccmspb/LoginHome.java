/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

/**
 *
 * @author HP
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class LoginHome {
    
	JFrame mainFrame;
	BorderLayout mainFrameLayout;
	/**menu bar objects*/
	MenuBar menuBar;
	
	Menu masters, administration,operations,report,help;
        MenuItem caseItem,reportItem,aboutDevelopers, manual;
        MenuItem assignPermission,changePassword,userManagement;
	/*menu bar objects complete*/
	
	/**north panel and objects*/
	JPanel northPanel;
	
	GridBagLayout northPanelLayout;
	
	JLabel heading, welcome;
	
	JButton logout;
	/*north panel objects complete*/
	
	/**west panel and objects*/
	JPanel westPanel;
	
	GridLayout westPanelLayout;
	//summary panel and objects
	JPanel summaryPanel;
	BorderLayout summaryPanelLayout;
	JLabel northSummaryLabel;
	JPanel detailPanel;
	BoxLayout detailPanelLayout;
	Label casesEnteredLabel;
	Label adjournedCasesLabel;
	Label orderedCasesLabel;
        JButton refresh;
        int casesEntered = 0, adjournedCases = 0, orderedCases = 0;
	
	//lawyer panel and objects
	JPanel lawyerPanel, lawyerButtonPanel;		//in actual site: Latest updates
	BorderLayout lawyerPanelLayout;
	JLabel northLawyerLabel;
	JPanel lawyerCenterPanel;
	JScrollPane scroll;
	GridBagLayout lawyerCenterPanelLayout;
	Label lawyerEnterEmail, subjectLabel;
	JTextField toField, subjectField;
	Label lawyerEnterMessage;
	JTextArea enterMessageArea;
	JButton attachment, sendMessage;
        String attachmentFileName="";
	/*west panel objects complete*/
	
	/**south panel and objects*/
	JPanel southPanel;
	GridBagLayout southPanelLayout;
	JButton link1;
	JButton link2;
	JButton link3;
	/*south panel objects complete*/
	
	/**east panel and objects*/
	JPanel eastPanel;
	GridBagLayout eastPanelLayout;
	
	JLabel notifications;
	JButton notificationRefreshForOneDay, notificationRefreshForSevenDays, notificationRefreshForFifteenDays;
        
        JTable notificationTable;
        JScrollPane notificationScrollPane;
        Dimension preferredSize;
	/*east panel objects complete*/
	
	/**center panel and objects*/
	JPanel centerPanel;
	JTabbedPane tabbedPane;
//        JPanel operationsPanel;
        ParameterizedReportView object;
	/*center panel objects complete*/
        
        ImageIcon markfed;
        
	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException{
//	        LoginHome object = new LoginHome();	//object of the main class
//		object.prepareGUI("", "");
//	}
	
	void prepareGUI(String username, String user_group) throws ClassNotFoundException, SQLException{
		mainFrame = new JFrame("Markfed CCMS");
		mainFrameLayout = new BorderLayout();
		mainFrame.setLayout(mainFrameLayout);
		
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	//to close on clicking cross button
                mainFrame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent evt) {
                        try {
                            new DatabaseHandling().changeStatus(username, "OFFLINE");
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.exit(0);
                    }
                });
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension size = toolkit.getScreenSize();
		mainFrame.setSize(size.width,size.height-40);
                mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
//                markfed = new ImageIcon("logo.png");
//                mainFrame.setIconImage(markfed.getImage());
		
	/**temporary*/
		//username = new String();
		//String usertype = "administrator";
	/**---------*/
	
	/**menu bar layout begins*/
		menuBar = new MenuBar();
		
                operations = new Menu("Operation");
                caseItem = new MenuItem("Case Details");
                menuBar.add(operations); 
                operations.add(caseItem);
                report = new Menu("Report");
                reportItem = new MenuItem("Parameterized Report");
                menuBar.add(report); 
                report.add(reportItem);
                
//                if(user_group.equals("Administrator")){
//                    masters = new Menu("Masters");	//does not have any menu item
//                    menuBar.add(masters);
//                }
                
		administration = new Menu("Administration");	//does not have any menu item
		menuBar.add(administration);
                
                changePassword = new MenuItem("Change Password");
                
                administration.add(changePassword);
                
                if(user_group.equals("Administrator")){
                    assignPermission = new MenuItem("Assign Permission");
                    userManagement = new MenuItem("User Management");
                    administration.add(assignPermission);
                    administration.add(userManagement);
                }
                
                help = new Menu("Help");
                aboutDevelopers = new MenuItem("About Developers");
                manual = new MenuItem("User Manual");
                
                help.add(manual);
//                help.add(aboutDevelopers);
                menuBar.add(help);
                
		
		mainFrame.setMenuBar(menuBar);	//sets the menu bar on the mainFrame
	/**menu-bar layout completed*/
	
	/**north panel layout begins*/
                
                reportItem.setActionCommand(username);
                
		northPanel = new JPanel();
		northPanelLayout = new GridBagLayout();
		northPanel.setLayout(northPanelLayout);
		northPanel.setBackground(Color.white);
		GridBagConstraints constraint = new GridBagConstraints();	//setting constraints for the layout
		constraint.gridheight = 1;
		constraint.gridwidth = 1;
                constraint.gridx = 0;
                constraint.gridy = 0;
                constraint.insets.top = 5;
                constraint.insets.bottom = 5;
                constraint.insets.left = 5;
                constraint.insets.right = 8;
                constraint.anchor = GridBagConstraints.CENTER;
		
                heading = new JLabel("Court Case Monitoring System - Punjab");	//heading label
		northPanel.add(heading, constraint);
		constraint.gridx = 0;
		constraint.gridy++;
		welcome = new JLabel("------ Welcome: "+username+" ------ User Group: "+user_group+"------");	//welcome label
		northPanel.add(welcome, constraint);
		constraint.gridx = constraint.gridx + 50;
		logout = new JButton("Logout");	//logout button
		northPanel.add(logout, constraint);
		mainFrame.add(northPanel, BorderLayout.NORTH);
	/**north panel layout completed*/
	
	/**west panel layout begins*/
		westPanel = new JPanel();
		westPanelLayout = new GridLayout(2, 1);
		westPanel.setLayout(westPanelLayout);
				
		/**temporary*/
		/**---------*/
		
		/**summary panel layout begins*/
		summaryPanel = new JPanel();
		summaryPanelLayout = new BorderLayout();
		summaryPanel.setLayout(summaryPanelLayout);	//summaryPanel
		summaryPanel.setBackground(Color.orange);	//set background color
		
		northSummaryLabel = new JLabel("Summary of the cases");
		summaryPanel.add(northSummaryLabel, BorderLayout.NORTH);
		
		detailPanel = new JPanel();	//panel in the center of summaryPanel
		detailPanelLayout = new BoxLayout(detailPanel, BoxLayout.Y_AXIS);	//box layout
		detailPanel.setLayout(detailPanelLayout);
		detailPanel.setBackground(Color.lightGray);
		
		refresh = new JButton("Refresh");
                detailPanel.add(refresh);
                
		summaryPanel.add(detailPanel, BorderLayout.CENTER);
		westPanel.add(summaryPanel);
		/**summary panel layout completed*/
				
		/**lawyer panel layout begins*/
		lawyerPanel = new JPanel();	//lawyerPanel
		lawyerPanelLayout = new BorderLayout();
		lawyerPanel.setLayout(lawyerPanelLayout);
		lawyerPanel.setBackground(Color.orange);
		
		northLawyerLabel = new JLabel("Send E-Mail");
		lawyerPanel.add(northLawyerLabel, BorderLayout.NORTH);
		
		lawyerCenterPanel = new JPanel();	//panel in the center of lawyerPanel
		lawyerCenterPanelLayout = new GridBagLayout();
		lawyerCenterPanel.setLayout(lawyerCenterPanelLayout);
		lawyerCenterPanel.setBackground(Color.lightGray);
                
		GridBagConstraints lawyerConstraint = new GridBagConstraints();	//grid bag constraints for lawyerCenterPanel
                lawyerConstraint.gridheight = 1;
                lawyerConstraint.gridwidth = 1;
                lawyerConstraint.gridx = 0;
                lawyerConstraint.gridy = 0;
                lawyerConstraint.insets.top = 5;
                lawyerConstraint.insets.bottom = 5;
                lawyerConstraint.insets.left = 5;
                lawyerConstraint.insets.right = 8;
                lawyerConstraint.anchor = GridBagConstraints.WEST;
		
		lawyerEnterEmail = new Label("To:");
		lawyerCenterPanel.add(lawyerEnterEmail, lawyerConstraint);
		lawyerConstraint.gridx = 0;
		lawyerConstraint.gridy++;
		
		toField = new JTextField(10);	//enter email field
		lawyerCenterPanel.add(toField, lawyerConstraint);
                ArrayList<String> lawyerEmailList = new DatabaseHandling().getEmailList();
                AutoSuggestor suggestor = new AutoSuggestor(toField, mainFrame, lawyerEmailList, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f);
		lawyerConstraint.gridx = 0;
		lawyerConstraint.gridy++;
                
                subjectLabel = new Label("Subject");
                lawyerCenterPanel.add(subjectLabel, lawyerConstraint);
		lawyerConstraint.gridx = 0;
		lawyerConstraint.gridy++;
                
                subjectField = new JTextField(10);
                lawyerCenterPanel.add(subjectField, lawyerConstraint);
		lawyerConstraint.gridx = 0;
		lawyerConstraint.gridy++;
		
		lawyerEnterMessage = new Label("Enter message:");
		lawyerCenterPanel.add(lawyerEnterMessage, lawyerConstraint);
		lawyerConstraint.gridx = 0;
		lawyerConstraint.gridy++;
		
		enterMessageArea = new JTextArea(2, 10);	//enter message area of size 13 rows and 15 columns
		scroll = new JScrollPane(enterMessageArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	//setting scroll pane to show vertical scroll bar as needed	
		enterMessageArea.setLineWrap(true);	//lines will be wrapped if they are too long to fit in allocated space
		enterMessageArea.setWrapStyleWord(true);	//lines will be wrapped at whitespaces if they are too long
		lawyerCenterPanel.add(scroll, lawyerConstraint);	//note: we add scroll not enterMessageArea
		
                attachment = new JButton("Attach");
		sendMessage = new JButton("Send");	//send button for south
                lawyerButtonPanel = new JPanel();
                lawyerButtonPanel.add(attachment);
                lawyerButtonPanel.add(sendMessage);
                lawyerPanel.add(lawyerButtonPanel, BorderLayout.SOUTH);
		lawyerPanel.add(lawyerCenterPanel, BorderLayout.CENTER);
		/**lawyer panel layout completed*/
		
		westPanel.add(lawyerPanel);
	
		mainFrame.add(westPanel, BorderLayout.WEST);
	/**west panel layout completed*/
	
	/**south panel layout begins*/
		southPanel = new JPanel();
		southPanelLayout = new GridBagLayout();
		southPanel.setLayout(southPanelLayout);
		southPanel.setBackground(Color.white);
		
		GridBagConstraints southConstraint = new GridBagConstraints();	//grid bag constraints for south panel
                southConstraint.gridheight = 1;
                southConstraint.gridwidth = 1;
                southConstraint.gridx = 0;
                southConstraint.gridy = 0;
                southConstraint.insets.top = 5;
                southConstraint.insets.bottom = 5;
                southConstraint.insets.left = 5;
                southConstraint.insets.right = 8;
                southConstraint.anchor = GridBagConstraints.CENTER;
		
		link1 = new JButton("punjabgovt.nic.in");
		southPanel.add(link1, southConstraint);
                
		southConstraint.gridx++;
		
		link2 = new JButton("highcourtchd.gov.in");
		southPanel.add(link2, southConstraint);
		southConstraint.gridx++;
		
		link3 = new JButton("supremecourtofindia.nic.in");
		southPanel.add(link3, southConstraint);
                
                southConstraint.gridy++;
                southConstraint.gridx = 0;
                southConstraint.gridwidth=3;
                southPanel.add(new JLabel("Designed and Developed by CSE Department, CCET, Chandigarh"), southConstraint);
		
		mainFrame.add(southPanel, BorderLayout.SOUTH);
	/**south panel layout completed*/
	
	/**east panel layout begins*/
		eastPanel = new JPanel();
		eastPanelLayout = new GridBagLayout();
		eastPanel.setLayout(eastPanelLayout);
		eastPanel.setBackground(Color.lightGray);
		
		GridBagConstraints eastConstraint = new GridBagConstraints();	//grid bag constraints for east panel
                eastConstraint.gridheight = 1;
                eastConstraint.gridwidth = 1;
                eastConstraint.gridx = 0;
                eastConstraint.gridy = 0;
                eastConstraint.insets.top = 5;
                eastConstraint.insets.bottom = 5;
                eastConstraint.insets.left = 5;
                eastConstraint.insets.right = 8;
                eastConstraint.anchor = GridBagConstraints.CENTER;
		
		notifications = new JLabel("NOTIFICATION CENTER");
		eastPanel.add(notifications, eastConstraint);
                eastConstraint.gridx = 0;
                eastConstraint.gridy++;
                                
                notificationRefreshForOneDay = new JButton("Next Day");
                eastPanel.add(notificationRefreshForOneDay, eastConstraint);
                eastConstraint.gridx = 0;
                eastConstraint.gridy++;
                
                notificationRefreshForSevenDays = new JButton("Seven Days");
                eastPanel.add(notificationRefreshForSevenDays, eastConstraint);
                eastConstraint.gridx = 0;
                eastConstraint.gridy++;
                
                notificationRefreshForFifteenDays = new JButton("Fifteen Days");
                eastPanel.add(notificationRefreshForFifteenDays, eastConstraint);
		
		eastConstraint.gridy++;
                
//                        DatabaseHandling object = new DatabaseHandling();
//                        notificationTable = object.makeNotificationTable(username);
//                        notificationScrollPane = new JScrollPane(notificationTable);
////                        preferredSize = new Dimension(800, 600);
////                        notificationScrollPane.setPreferredSize(preferredSize);
//                        eastPanel.add(notificationScrollPane, eastConstraint);
//                preferredSize = new Dimension(500, 700);
//                eastPanel.setPreferredSize(preferredSize);
		mainFrame.add(eastPanel, BorderLayout.EAST);
	/**east panel layout completed*/
	
	/**center panel layout begins*/
		centerPanel = new JPanel(new BorderLayout());
                tabbedPane = new JTabbedPane();
              //  tabbedPane.setSize(800, 800);
		tabbedPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                
//                operationsPanel = new JPanel();
             		
		mainFrame.add(centerPanel, BorderLayout.CENTER);
                
                northPanel.setVisible(true);
                southPanel.setVisible(true);
                eastPanel.setVisible(true);
                centerPanel.setVisible(true);
                lawyerButtonPanel.setVisible(true);
                lawyerPanel.setVisible(true);
                detailPanel.setVisible(true);
                summaryPanel.setVisible(true);
                westPanel.setVisible(true);
                mainFrame.setVisible(true);	//mainFrame is now visible
                
                refresh.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            DatabaseHandling obj = new DatabaseHandling();
                            casesEntered = obj.getTotalCases();
                            adjournedCases = obj.getAdjournedCases();
                            orderedCases = obj.getOrderedCases();
                                                   
                            detailPanel.removeAll();
                            
                            casesEnteredLabel = new Label("Total Cases Entered: "+casesEntered);
                            detailPanel.add(casesEnteredLabel);

                            adjournedCasesLabel = new Label("Total Pending Cases: "+adjournedCases);    //check that variable adjurned cases shows pending+adjourned cases
                            detailPanel.add(adjournedCasesLabel);

                            orderedCasesLabel = new Label("Total Disposed Off Cases: "+orderedCases);
                            detailPanel.add(orderedCasesLabel);
                            
                            detailPanel.add(refresh);
                            detailPanel.validate();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                caseItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        centerPanel.removeAll();
                        centerPanel.add(new JLabel("CASE DETAILS"),BorderLayout.NORTH);
                    
                        try {
                            MainPage object = new MainPage();
                            centerPanel.add(object.prepareGUI(username, user_group),BorderLayout.CENTER);
//                            ArrayList<String> fileNumberList = new DatabaseHandling().getFileNumberList();
//                            AutoSuggestor suggestor = new AutoSuggestor(object.fileNumberField, mainFrame, fileNumberList, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f);
                            centerPanel.validate();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                reportItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            centerPanel.removeAll();
                            centerPanel.add(new JLabel("PARAMETERIZED REPORT"),BorderLayout.NORTH);
                            JScrollPane reportScroll = new JScrollPane(new ParameterizedReportView().prepareGUI(username, user_group), ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_AS_NEEDED);
                            centerPanel.add(reportScroll,BorderLayout.CENTER);
                            centerPanel.validate();
                        } catch (SQLException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                            
                changePassword.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        centerPanel.removeAll();
                        centerPanel.add(new JLabel("CHANGE PASSWORD"),BorderLayout.NORTH);
                        centerPanel.add( new ChangePassword().prepareGUI(username, user_group));
                        centerPanel.validate();
                    }
                });
                
                if(user_group.equals("Administrator")){
                    userManagement.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            centerPanel.removeAll();
                            centerPanel.add(new JLabel("USER MANAGEMENT"),BorderLayout.NORTH);
                            centerPanel.add( new UserManagement().prepareGUI());
                            centerPanel.validate();
                        }
                    });
                    
                    assignPermission.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        centerPanel.removeAll();
                        centerPanel.add(new JLabel("ASSIGN PERMISSIONS"),BorderLayout.NORTH);
                        centerPanel.add( new AssignPermissions().prepareGUI(username, user_group));
                        centerPanel.validate();
                        }
                    });
                }
                
                manual.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        centerPanel.removeAll();
                        centerPanel.add(new JLabel("USER MANUAL"),BorderLayout.NORTH);
                        try {
                            centerPanel.add( new UserManual().prepareGUI());
                            centerPanel.validate();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                //links open in internet explorer
                link1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            URI link = new URI("http://punjabgovt.nic.in");
                            Desktop.getDesktop().browse(link);
//                            String command = "C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE http://punjabgovt.nic.in" ;
//                            Process link = Runtime.getRuntime().exec(command); 
                        }
                        catch(Exception ex){
                            System.out.println("cannot execute command. " +ex); 
                        }
                    }
                });
               
                link2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            URI link = new URI("http://highcourtchd.gov.in");
                            Desktop.getDesktop().browse(link);
//                            String command = "C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE http://highcourtchd.gov.in" ;
//                            Process link = Runtime.getRuntime().exec(command);
                        }
                        catch(Exception ex){
                            System.out.println("cannot execute command. " +ex); 
                        }
                    }
                });
                
                link3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            URI link = new URI("http://supremecourtofindia.nic.in");
                            Desktop.getDesktop().browse(link);
//                            String command = "C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE http://supremecourtofindia.nic.in" ;
//                            Process link = Runtime.getRuntime().exec(command);
                        }
                        catch(Exception ex){
                            System.out.println("cannot execute command. " +ex); 
                        }
                    }
                });
                
                notificationRefreshForOneDay.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("clicked");
//                        eastPanel.removeAll();
//                        eastPanel.add(notifications, eastConstraint);
//                        eastConstraint.gridy++;
//
//                        eastPanel.add(notificationRefresh, eastConstraint);
//                        notificationTable.removeAll();
                        
                        eastConstraint.gridy++;
                        DatabaseHandling object = new DatabaseHandling();
                        try {
                            notificationTable = object.makeNotificationTable(username, 1);
                        } catch (SQLException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (MessagingException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        notificationScrollPane = new JScrollPane(notificationTable);
                        JFrame notificationFrame = new JFrame("Approaching Cases");
                        notificationFrame.add(notificationScrollPane);
                        notificationFrame.setSize(500, 300);
//                        notificationFrame.setDefaultCloseOperation(notificationFrame.EXIT_ON_CLOSE);
                        notificationFrame.setVisible(true);
//                        eastPanel.add(notificationScrollPane, eastConstraint);
                        eastPanel.validate();
                    }
                });
                             
                notificationRefreshForSevenDays.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("clicked");
//                        eastPanel.removeAll();
//                        eastPanel.add(notifications, eastConstraint);
//                        eastConstraint.gridy++;
//
//                        eastPanel.add(notificationRefresh, eastConstraint);
//                        notificationTable.removeAll();
                        
                        eastConstraint.gridy++;
                        DatabaseHandling object = new DatabaseHandling();
                        try {
                            notificationTable = object.makeNotificationTable(username, 7);
                        } catch (SQLException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (MessagingException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        notificationScrollPane = new JScrollPane(notificationTable);
                        JFrame notificationFrame = new JFrame("Approaching Cases");
                        notificationFrame.add(notificationScrollPane);
                        notificationFrame.setSize(500, 300);
//                        notificationFrame.setDefaultCloseOperation(notificationFrame.EXIT_ON_CLOSE);
                        notificationFrame.setVisible(true);
//                        eastPanel.add(notificationScrollPane, eastConstraint);
                        eastPanel.validate();
                    }
                });
                
                notificationRefreshForFifteenDays.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("clicked");
//                        eastPanel.removeAll();
//                        eastPanel.add(notifications, eastConstraint);
//                        eastConstraint.gridy++;
//
//                        eastPanel.add(notificationRefresh, eastConstraint);
//                        notificationTable.removeAll();
                        
                        eastConstraint.gridy++;
                        DatabaseHandling object = new DatabaseHandling();
                        try {
                            notificationTable = object.makeNotificationTable(username, 15);
                        } catch (SQLException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (MessagingException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        notificationScrollPane = new JScrollPane(notificationTable);
                        JFrame notificationFrame = new JFrame("Approaching Cases");
                        notificationFrame.add(notificationScrollPane);
                        notificationFrame.setSize(500, 300);
//                        notificationFrame.setDefaultCloseOperation(notificationFrame.EXIT_ON_CLOSE);
                        notificationFrame.setVisible(true);
//                        eastPanel.add(notificationScrollPane, eastConstraint);
                        eastPanel.validate();
                    }
                });
                               
                logout.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            new DatabaseHandling().changeStatus(username, "OFFLINE");
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        new Login().prepareGUI();
                        mainFrame.dispose();
                    }
                });
                
                attachment.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fc = new JFileChooser();
                        int returnVal = fc.showOpenDialog(lawyerPanel); //Where frame is the parent component

                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            attachmentFileName = fc.getSelectedFile().getAbsolutePath();
                            attachment.setText(fc.getSelectedFile().getName());
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "No File Selected");
                            attachmentFileName = "";
                            attachment.setText("Attach");
                            //User did not choose a valid file
                        }
                    }
                });
                
                sendMessage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DatabaseHandling object = new DatabaseHandling();
                        try {
                            if(toField.getText().isEmpty() || subjectField.getText().isEmpty() || enterMessageArea.getText().isEmpty()){
                                JOptionPane.showMessageDialog(new JFrame(), "Fill all the Text Fields");
                                return;
                            }
                            object.sendEmail(toField.getText(), subjectField.getText(), enterMessageArea.getText(), username, attachmentFileName);
                            toField.setText("");
                            subjectField.setText("");
                            enterMessageArea.setText("");
                            attachment.setText("Attach");
                            attachmentFileName = "";
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (MessagingException ex) {
                            Logger.getLogger(LoginHome.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
    }
}