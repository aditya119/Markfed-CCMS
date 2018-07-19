/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Aditya Sharma
 */
public class EmailDemo {
    public void sender(String from, String password, String to, String subject, String message, String filename) throws MessagingException {
        if( !EMailSender.sendMail(from,
                password,
                message,
                to, subject, filename))JOptionPane.showMessageDialog(new JPanel(), "Mail ERROR");
        else JOptionPane.showMessageDialog(new JPanel(), "Mail Sent");
    }
}