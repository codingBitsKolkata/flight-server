package com.orastays.flight.flightserver.service;

import java.util.List;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.FlightPriceModel;
import com.orastays.flight.flightserver.model.FlightSearchModel;
import com.orastays.flight.flightserver.model.SearchParameterModel;

public interface FlightService {

	List<SearchParameterModel> searchAirportDetails(String keyword) throws FormExceptions;
	
	String fetchOneWayFlights(FlightSearchModel flightSearchModel) throws FormExceptions;

	String fetchRoundTripFlights(FlightSearchModel flightSearchModel) throws FormExceptions;

	String fetchMultiCityFlights(FlightSearchModel flightSearchModel) throws FormExceptions;

	String fetchOneWayPricing(FlightPriceModel flightPriceModel) throws FormExceptions;

	String fetchRoundTripPricing(FlightPriceModel flightPriceModel) throws FormExceptions;

	String fetchMultiCityPricing(FlightPriceModel flightPriceModel) throws FormExceptions;
}