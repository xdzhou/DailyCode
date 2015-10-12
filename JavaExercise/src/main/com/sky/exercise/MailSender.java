package com.sky.exercise;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.org.apache.commons.logging.Log;

public class MailSender
{
	public static void main(String[] args)
	{
		new MailSender().sendMail();
	}
	
	public void sendMail()
	{
		String username = "xdzhou.loic@yahoo.com";
		String password = "***********";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		props.put("mail.smtp.host", "smtp.mail.yahoo.com");
		props.put("mail.smtp.port", "587");
		
		Session mailSession = Session.getInstance(props,
        new javax.mail.Authenticator() 
		{
        	protected PasswordAuthentication getPasswordAuthentication() 
        	{
        		return new PasswordAuthentication(username, password);
        	}
        });
		mailSession.setDebug(true);
		Message msg = new MimeMessage(mailSession);
		
		try
		{
			msg.setFrom(new InternetAddress("xdzhou.loic@yahoo.com"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress("helene.li.xu@gmail.com"));
		    msg.setSubject( "邮件标题：" +"Test");
		    //((MimeMessage)msg).setSubject(subject, "GBK"); //设置中文标题
		    msg.setSentDate(new Date());
		    msg.setContent("Content", "text/html; charset=utf-8");
		    Transport.send(msg);
		} 
		catch (AddressException e)
		{
			e.printStackTrace();
			System.out.println("ERROR : "+e);
		} 
		catch (MessagingException e)
		{
			e.printStackTrace();
			System.out.println("ERROR : "+e);
		}
	}
}
