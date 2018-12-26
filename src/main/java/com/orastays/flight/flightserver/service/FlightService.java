package com.orastays.flight.flightserver.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.FlightSearchModel;

public interface FlightService {

	String fetchOneWayFlights(FlightSearchModel flightSearchModel) throws FormExceptions, JSONException;

	List<FlightSearchModel> fetchRoundTripFlights(FlightSearchModel flightSearchModel) throws FormExceptions;

	List<FlightSearchModel> fetchMultiCityFlights(FlightSearchModel flightSearchModel) throws FormExceptions;
}