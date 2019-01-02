package com.orastays.flight.flightserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.orastays.flight.flightserver.validation.FlightValidation;

public abstract class BaseServiceImpl {

	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;
	
	@Autowired
	protected FlightValidation flightValidation;
	
}
