package com.orastays.flight.flightserver.service;

import java.util.List;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.FlightSearchModel;

public interface FlightService {

	List<FlightSearchModel> fetchOneWayFlights(FlightSearchModel flightSearchModel) throws FormExceptions;

	List<FlightSearchModel> fetchRoundTripFlights(FlightSearchModel flightSearchModel) throws FormExceptions;

	List<FlightSearchModel> fetchMultiCityFlights(FlightSearchModel flightSearchModel) throws FormExceptions;
}