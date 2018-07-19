/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;
//here we plan to generate dynamic text fields

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author HP
 */
public class DynamicTextField {
    int i =0; 
    int Count = 20;
    JFrame frame;
    JPanel panelRoot,basePanel;
    JTextField[] fieldObj = new JTextField[Count]; 
    JButton addButton;
    GridLayout forTextField;
    
    private void createGUI(){
        
        forTextField = new GridLayout(Count,1);
        frame = new JFrame();
        basePanel = new JPanel(new BorderLayout());
        panelRoot = new JPanel();
        addButton = new JButton("Add");
        panelRoot.setLayout(forTextField);
        
        frame.setSize(300,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(basePanel);
        basePanel.add(addButton,BorderLayout.NORTH);
        basePanel.add(panelRoot,BorderLayout.CENTER);
        
        
        for(int j = 0;j<Count;j++){
        fieldObj[j] = new JTextField(30);
        }
        
//        addButton.addActionListener(new ActionListener(){
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(i < Count){
//                    panelRoot.add(fieldObj[i]);   
//                    i = i+1;
//                }
//            }
//        });
        for(i = 0;i<Count;i++)
        {
            panelRoot.add(fieldObj[i]);   
        }
    }
}
