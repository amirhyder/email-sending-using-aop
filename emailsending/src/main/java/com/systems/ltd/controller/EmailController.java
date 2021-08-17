package com.systems.ltd.controller;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.systems.ltd.aspect.EmailAspect;
import com.systems.ltd.model.Email;
import com.systems.ltd.service.EmailService;

@RestController
public class EmailController {
	

	@Autowired
	private EmailService emailServie;
	
	@Autowired
	EmailAspect aspect;
	
	@PostMapping("/email")
	public String sendEmail() throws IOException {
		
		
		Email email=aspect.sendEmailAdvice();
		String to=email.getTo();
		String content=email.getContent();
		String subject=email.getSubject();
		String from=email.getFrom();
	
		boolean check= emailServie.sendEmail(to, content, subject,from);
		
		if(check) {
			return "Email sent successfully";
		}else {
			return "Email not sent";
		}
	}
	
	
}
