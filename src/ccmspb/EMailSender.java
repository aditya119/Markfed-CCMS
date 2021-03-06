/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccmspb;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Aditya Sharma
 */
public class EMailSender {
    public static boolean sendMail(String from,  String password, String message, String to, String sub, String filename) throws MessagingException{
        String host = "smtp.gmail.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage mimeMessage = new MimeMessage(session);
        Multipart multipart = new MimeMultipart();
 
        // creates body part for the message
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");

        if(!filename.equals("")){
            // creates body part for the attachment
            MimeBodyPart attachPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filename);
            attachPart.setDataHandler(new DataHandler(source));
            attachPart.setFileName(source.getName());
            multipart.addBodyPart(attachPart);
        }
        // adds parts to the multipart
        multipart.addBodyPart(messageBodyPart);
        
        System.out.println("From "+from+" To "+to);
        
        try{
            mimeMessage.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress();
            //for(int i = 0; i < to.length; i++){
                toAddress = new InternetAddress(to);    
            //}
            //for(int i = 0; i < toAddress.length; i++){
                mimeMessage.setRecipient(RecipientType.TO, toAddress);
            //}
            mimeMessage.setSubject(sub);
//            mimeMessage.setText(message);
//            if(!filename.equals("")){
//                DataSource source = new FileDataSource(filename);
//                mimeMessage.setDataHandler(new DataHandler(source));
//                mimeMessage.setFileName(filename);
//            }

            // sets the multipart as message's content
            mimeMessage.setContent(multipart);
            
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
            return true;
            
        }catch(MessagingException me){
            me.printStackTrace();
        }
        return false;
    }
}
