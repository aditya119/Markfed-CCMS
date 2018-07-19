/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author HP
 */
public class ParameterizedReportView {
 
	JPanel centerPanel;
	JTabbedPane tabbedPane;
	//AllPendingCases pendingCasesObject;
	CaseHistory caseHistoryObject;
	//HearingStatistics hearingStatisticsObject;
	
//	JPanel casesAppealedAgainst;
	
	//LinkedCasesInformation linkedCasesInformationObject;
	
//	MasterDataList masterDataListObject;
	
//	DailyCaseDeadline caseDeadlineObject;
	
  //      AllDisposedCases disposedCasesObject;
	
    //    CauseList causelistObject;
        
	//JPanel stayOrdersPassed;
	
//	public static void main(String[] args){
//		ParameterizedReportView object = new ParameterizedReportView();
//		object.prepareGUI();
//	}
	JPanel prepareGUI(String username, String userGroup) throws SQLException, ClassNotFoundException{
            /**center panel layout begins*/
		centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
	//	tabbedPane = new JTabbedPane();
	//	tabbedPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		/**AllPendingCases panel begins*/
	//	pendingCasesObject = new AllPendingCases();
	//	pendingCasesObject.prepareGUI();
		/**AllPendingCases panel completed*/
                
                /**CaseHistory panel begins*/
		caseHistoryObject = new CaseHistory();
		caseHistoryObject.prepareGUI(username, userGroup);
                centerPanel.add(caseHistoryObject.mainPanel);
		/**CaseHistory panel completed*/
		
                /**HearingStatistics panel begins*/
        //        hearingStatisticsObject = new HearingStatistics();
        //        hearingStatisticsObject.prepareGUI();
		/**HearingStatistics panel completed*/
		
	//	casesAppealedAgainst = new JPanel();    //to be checked
		
		/**LinkedCasesInformation panel begins*/
	//	linkedCasesInformationObject = new LinkedCasesInformation();
	//	linkedCasesInformationObject.prepareGUI();
		/**LinkedCasesInformation panel completed*/
                
		/**MasterDataList panel begins*/
        //      masterDataListObject = new MasterDataList();
	//	masterDataListObject.prepareGUI();
                /**MasterDataList panel completed*/
                
		/**DailyCaseDeadline panel begins*/
        //      caseDeadlineObject = new DailyCaseDeadline();
        //      caseDeadlineObject.prepareGUI();
                /**DailyCaseDeadline panel completed*/
		
                /**AllDisposedCases panel begins*/
        //      disposedCasesObject = new AllDisposedCases();
        //      disposedCasesObject.prepareGUI();
		/**AllDisposedCases panel layout completed*/
		
                /**Cause list panel begins*/
	//	causelistObject = new Causelist();
        //      causelistObject.prepareGUI();
                /**Cause list panel completed*/
                
        //      stayOrdersPassed = new JPanel();    //to be checked
		
		
	/*	tabbedPane.addTab("Pending Cases", pendingCasesObject.centerPanel);
		tabbedPane.addTab("History of Case", caseHistoryObject.mainPanel);
                tabbedPane.addTab("Hearing Statistics", hearingStatisticsObject.mainPanel);
		tabbedPane.addTab("Cases Appealed Against", casesAppealedAgainst);
		tabbedPane.addTab("Information of Linked Cases", linkedCasesInformationObject.mainPanel);
		tabbedPane.addTab("Master Data List", masterDataListObject.mainPanel);
		tabbedPane.addTab("Deadline Expired", caseDeadlineObject.centerPanel);
		tabbedPane.addTab("Disposed Off Cases", disposedCasesObject.centerPanel);
                tabbedPane.addTab("Stay orders passed", stayOrdersPassed);
                tabbedPane.addTab("Causelist", causelistObject.centerPanel);
		
		centerPanel.add(tabbedPane, BorderLayout.CENTER);*/
				
		//mainFrame.add(centerPanel, BorderLayout.CENTER);
	/**center panel layout completed*/	
                return centerPanel;
	}
}
    

