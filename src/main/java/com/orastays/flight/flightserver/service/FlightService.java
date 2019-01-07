package com.orastays.flight.flightserver.service;

import java.util.List;

import org.json.JSONException;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.FlightPriceModel;
import com.orastays.flight.flightserver.model.FlightSearchModel;
import com.orastays.flight.flightserver.model.SearchParameterModel;

public interface FlightService {

	Object fetchOneWayFlights(FlightSearchModel flightSearchModel) throws FormExceptions, JSONException;

	String fetchRoundTripFlights(FlightSearchModel flightSearchModel) throws FormExceptions, JSONException;

	String fetchMultiCityFlights(FlightSearchModel flightSearchModel) throws FormExceptions, JSONException;

	String fetchOneWayPricing(FlightPriceModel flightPriceModel) throws FormExceptions, JSONException;

	String fetchRoundTripPricing(FlightPriceModel flightPriceModel) throws FormExceptions, JSONException;

	String fetchMultiCityPricing(FlightPriceModel flightPriceModel) throws FormExceptions, JSONException;

	List<SearchParameterModel> searchAirportDetails(String keyword) throws FormExceptions;;
}