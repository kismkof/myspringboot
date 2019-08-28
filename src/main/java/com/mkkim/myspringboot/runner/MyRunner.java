package com.mkkim.myspringboot.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.mkkim.myspringboot.property.MkkimProperties;

@Component
public class MyRunner implements ApplicationRunner{
	
	@Value("${mkkim.name}")
	private String name;
	@Value("${mkkim.age}")
	private int age;
	@Value("${spring.profiles.active}")
	private String operationType;
	
	@Autowired
	MkkimProperties mkkimProperties;
	
	@Autowired
	private String hello;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("operationType : " + operationType);
		logger.debug("Program arguments : " + args.containsOption("bar"));
		logger.debug("VM arguments : " + args.containsOption("foo"));
		
		logger.debug(name);
		logger.debug(mkkimProperties.getName());
		logger.debug(mkkimProperties.getFullName());
		logger.debug("Property class Name : " + hello);
		logger.debug("현재 적용된 Property = " + hello);
	}
}
