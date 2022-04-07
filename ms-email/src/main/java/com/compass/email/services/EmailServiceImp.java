package com.compass.email.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.compass.email.entities.Email;
import com.compass.email.enums.StatusEmail;
import com.compass.email.repositories.EmailRepository;

@Service
public class EmailServiceImp implements EmailService {

	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private JavaMailSender emailSender;

	@Override
	public Email sendEmail(Email email) {
		email.setSendDateEmail(null);
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(email.getEmailFrom());
			message.setTo(email.getEmailTo());
			message.setSubject(email.getSubject());
			message.setText(email.getText());
			emailSender.send(message);
			
			email.setStatusEmail(StatusEmail.SENT);
		} catch (MailException e) {
			email.setStatusEmail(StatusEmail.ERROR);
		} 
		return emailRepository.save(email);
	}
}
