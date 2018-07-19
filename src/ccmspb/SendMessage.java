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

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SendMessage {
 // Find your Account Sid and Token at twilio.com/user/account
 public String ACCOUNT_SID = "ACe9a932f90f6c6b8b802d5edc914a50a0";
 public String AUTH_TOKEN = "8cc635e24b50d51713e03376f11febd4";

 public void messageFunction(String messageText, String toNumber) {
     
   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(new PhoneNumber(toNumber),
        new PhoneNumber("+19095750311"), 
        messageText).create();
    if(!message.equals(null)){
        JOptionPane.showMessageDialog(new JPanel(), "Message Sent");
    }else{
        JOptionPane.showMessageDialog(new JPanel(), "Error in sending message");
    }
    System.out.println(message.getSid());
 }
}
