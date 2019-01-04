package com.orastays.flight.flightserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.orastays.flight.flightserver.converter.SearchParameterConverter;
import com.orastays.flight.flightserver.dao.SearchParameterDAO;
import com.orastays.flight.flightserver.validation.FlightBookingValidation;
import com.orastays.flight.flightserver.validation.FlightValidation;

public abstract class BaseServiceImpl {

	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;
	
	@Autowired
	protected FlightValidation flightValidation;
	
	@Autowired
	protected FlightBookingValidation flightBookingValidation;
	
	@Autowired
	protected SearchParameterConverter searchParameterConverter;
	
	@Autowired
	protected SearchParameterDAO searchParameterDAO;
	
}
