package com.systems.ltd.aspect;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.systems.ltd.model.Email;


@Aspect
@Component
public class EmailAspect {
	
	@Autowired
	Properties properties;
	
	@Before("execution (public boolean sendEmail(String,String,String,String))")
	public Email sendEmailAdvice() throws IOException {
			
		Properties properties = new Properties();
	    InputStream inputStream = 
	    getClass().getClassLoader().getResourceAsStream("application.properties");
	    properties.load(inputStream);
	    
	    String from= properties.getProperty("from");
	    String to= properties.getProperty("to");
	    String subject= properties.getProperty("subject");
	    String content= properties.getProperty("content");
	   
	    Email email=new Email();
	   
	    email.setContent(content);
	    email.setFrom(from);
	    email.setSubject(subject);
	    email.setTo(to);
	   
	    System.out.println("values from aspect:"+to+ ":"+content+": "+subject+":"+from);
	   
	    return email;
		
		
	}
	



}
