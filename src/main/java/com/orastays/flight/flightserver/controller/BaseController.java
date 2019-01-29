package com.orastays.flight.flightserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.orastays.flight.flightserver.helper.MessageUtil;
import com.orastays.flight.flightserver.service.BaggageService;
import com.orastays.flight.flightserver.service.FareRulesService;
import com.orastays.flight.flightserver.service.FlightBookingService;
import com.orastays.flight.flightserver.service.FlightService;

public class BaseController {

	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpServletResponse response;
	
	@Value("${common.success.code}")
	protected String commonSuccessCode;
	
	@Value("${common.success.message}")
	protected String commonSuccessMessage;
	
	@Value("${common.error.code}")
	protected String commonErrorCode;
	
	@Value("${common.error.message}")
	protected String commonErrorMessage;
	
	@Autowired
	protected MessageUtil messageUtil;

	@Autowired
	protected FlightService flightService;
	
	@Autowired
	protected FlightBookingService flightBookingService;
	
	@Autowired
	protected FareRulesService fareRulesService;
	
	@Autowired
	protected BaggageService baggageService;
	
	@Autowired
	protected FlightBookingService bookingService;
}
