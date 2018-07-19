/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Aditya Sharma
 */
public class UserManual {
    JPanel mainPanel;
    JTextArea manualField;
    JScrollPane scroll;
    
    JPanel prepareGUI() throws FileNotFoundException, IOException{
        manualField = new JTextArea(25, 50);	//enter message area of size 100 rows and 50 columns
        scroll = new JScrollPane(manualField, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	//setting scroll pane to show vertical scroll bar as needed	
	manualField.setLineWrap(true);	//lines will be wrapped if they are too long to fit in allocated space
        manualField.setWrapStyleWord(true);
        manualField.setTabSize(4);
        
        FileInputStream fstream = new FileInputStream("M-CCMS_UserManual.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                manualField.append(strLine+"\n");
            }
            //Close the input stream
        }
        manualField.setEditable(false);
        
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.orange,2,true));
        mainPanel.add(scroll);
        
        return mainPanel;
    }
}