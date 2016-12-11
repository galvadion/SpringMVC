package com.servicesImpl;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component("MailAuxiliar")

public class MailAuxiliarService {
	
	@Autowired
    private JavaMailSender mailSender;
	
	public void sendMailWithHtmlText(String text, String recipentMail,String subject){
		mailSender.send(new MimeMessagePreparator() {
      	  public void prepare(MimeMessage mimeMessage) throws MessagingException {
      	    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
      	    message.setFrom("info.rentuy@gmail.com");
      	    message.setTo(recipentMail);
      	    message.setSubject(subject);
      	    message.setText(text, true);
      	  }
      	});
	}
	
	public void sendMailWithoutHtml(String text, String recipentMail,String subject){
		SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipentMail);
        email.setSubject(subject);
        email.setText(text);
       mailSender.send(email);
	}

}
