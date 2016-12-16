package com.servicesImpl;

import java.io.File;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component("MailAuxiliar")

public class MailAuxiliarService {
	
	@Autowired
    private JavaMailSender mailSender;
	 @Autowired 
	 HttpServletRequest request;
	
	public void sendMailWithHtmlText(String text, String recipentMail,String subject){
		String path = request.getSession().getServletContext().getRealPath("/static/images")+"/logo.png";
		System.out.println(path);
		FileSystemResource res = new FileSystemResource(new File(request.getSession().getServletContext().getRealPath("/static/images")+"/logo-wide-transparent.png"));
		System.out.println(res);
		mailSender.send(new MimeMessagePreparator() {
      	  public void prepare(MimeMessage mimeMessage) throws MessagingException {
      	    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
      	    message.setFrom("info.rentuy@gmail.com");
      	    message.setTo(recipentMail);
      	    message.setSubject(subject);
      	    message.setText("<html><body><img src='cid:logo'><br>"+text+"</body></html>", true);
      	    message.addInline("logo", res);
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
