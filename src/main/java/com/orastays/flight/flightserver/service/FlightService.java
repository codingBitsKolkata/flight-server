package com.orastays.flight.flightserver.service;

import org.json.JSONException;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.FlightBookingModel;
import com.orastays.flight.flightserver.model.FlightPriceModel;
import com.orastays.flight.flightserver.model.FlightSearchModel;

public interface FlightService {

	String fetchOneWayFlights(FlightSearchModel flightSearchModel) throws FormExceptions, JSONException;

	String fetchRoundTripFlights(FlightSearchModel flightSearchModel) throws FormExceptions, JSONException;

	String fetchMultiCityFlights(FlightSearchModel flightSearchModel) throws FormExceptions, JSONException;

	String fetchOneWayPricing(FlightPriceModel flightPriceModel) throws FormExceptions, JSONException;

	String fetchRoundTripPricing(FlightPriceModel flightPriceModel) throws FormExceptions, JSONException;

	String fetchMultiCityPricing(FlightPriceModel flightPriceModel) throws FormExceptions, JSONException;

	void saveReviewDetails(FlightBookingModel flightBookingModel) throws FormExceptions;
}