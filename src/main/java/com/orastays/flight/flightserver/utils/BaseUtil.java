package com.orastays.flight.flightserver.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.orastays.flight.flightserver.converter.BookingConverter;
import com.orastays.flight.flightserver.converter.BookingInfoConverter;
import com.orastays.flight.flightserver.dao.BookingDAO;
import com.orastays.flight.flightserver.dao.BookingInfoDAO;
import com.orastays.flight.flightserver.helper.CashFreeApi;
import com.orastays.flight.flightserver.service.ConvenienceService;
import com.orastays.flight.flightserver.service.GatewayService;

public class BaseUtil {
	@Autowired
	protected BookingConverter bookingConverter;
	
	@Autowired
	protected BookingDAO bookingDAO;
	
	@Autowired
	protected BookingInfoConverter bookingInfoConverter;
	
	@Autowired
	protected BookingInfoDAO bookingInfoDAO;
	
	@Autowired
	protected ConvenienceService convenienceService;
	
	@Value("${generic.error.code}")
	protected String genericErrorCode;
	
	@Value("${generic.error.message}")
	protected String genericErrorMessage;
	
	@Autowired
	protected CashFreeApi cashFreeApi;
	
	@Autowired
	protected GatewayService gatewayService;
}
