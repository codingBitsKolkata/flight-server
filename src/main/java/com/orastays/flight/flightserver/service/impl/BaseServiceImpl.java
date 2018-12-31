package com.orastays.flight.flightserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.orastays.flight.flightserver.validation.FlightValidation;

public abstract class BaseServiceImpl {

	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;
	
	@Value("${flight.email}")
	protected String emailId;

	@Value("${flight.password}")
	protected String password;
	
	@Value("${flight.key}")
	protected String apiKey;
	
	@Autowired
	protected FlightValidation flightValidation;
	
}
