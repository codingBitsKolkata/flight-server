package com.orastays.flight.flightserver.service;

import org.json.JSONException;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.FlightBookingModel;

public interface FlightBookingService {

	String fetchBookingList(FlightBookingModel flightBookingModel) throws FormExceptions, JSONException;

	void saveReviewDetails(FlightBookingModel flightBookingModel) throws FormExceptions;
}