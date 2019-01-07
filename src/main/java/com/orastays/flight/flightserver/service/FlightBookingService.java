package com.orastays.flight.flightserver.service;

import java.util.List;

import org.json.JSONException;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.FlightBookingModel;

public interface FlightBookingService {

	String fetchBookingList(FlightBookingModel flightBookingModel) throws FormExceptions, JSONException;

	List<FlightBookingModel> bookFlights(FlightBookingModel flightBookingModel) throws FormExceptions;
}