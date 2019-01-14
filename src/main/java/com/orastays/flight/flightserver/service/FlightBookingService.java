package com.orastays.flight.flightserver.service;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.FlightBookingModel;

public interface FlightBookingService {

	String fetchBookingList(FlightBookingModel flightBookingModel) throws FormExceptions;

	String bookFlights(FlightBookingModel flightBookingModel) throws FormExceptions;
}