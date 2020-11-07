/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papa.jee18.notification;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class SmtpServerHelper {

    public static void sendMail(String recipient, String subject, String content,
            Session mailSession)
            throws MessagingException {
        Message msg = new MimeMessage(mailSession);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(recipient, false));
        msg.setText(content);
        Transport.send(msg);
    }

}
