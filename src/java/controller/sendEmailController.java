/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author anhtuong
 */
@Controller
public class sendEmailController {

    @RequestMapping(value = "/guiemail", method = RequestMethod.POST)
    public String guiemail(ModelMap mm, @RequestParam(value = "EmailNhan") String EmailNhan, @RequestParam(value = "Subject") String Subject, @RequestParam(value = "NoiDung") String NoiDung) {

        String user = "nguyencaotri1995@gmail.com";
        String pass = "19951879tri3";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {

            /* Create an instance of MimeMessage, 
	        	      it accept MIME types and headers 
             */
            MimeMessage me = new MimeMessage(session);
            me.setFrom(new InternetAddress(user));
            me.addRecipient(Message.RecipientType.TO, new InternetAddress(EmailNhan));
            me.setSubject(Subject);
            me.setText(NoiDung);

            /* Transport class is used to deliver the message to the recipients */
            Transport.send(me);
            mm.put("thongbao", "<h2 style=" + "color:green" + "><b>sent successfully &#x2713;</b></h2>");
        } catch (Exception e) {
            e.printStackTrace();
            mm.put("thongbao", "<h2 style=" + "color:red" + "><b>! send failed</b></h2>");
        }

        return "redirect:quanly1.ute";
    }
}
