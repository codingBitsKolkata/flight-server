package com.orastays.flight.flightserver.service;

import org.json.JSONException;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.FlightSearchModel;

public interface FlightService {

	String fetchOneWayFlights(FlightSearchModel flightSearchModel) throws FormExceptions, JSONException;

	String fetchRoundTripFlights(FlightSearchModel flightSearchModel) throws FormExceptions;

	String fetchMultiCityFlights(FlightSearchModel flightSearchModel) throws FormExceptions;
}