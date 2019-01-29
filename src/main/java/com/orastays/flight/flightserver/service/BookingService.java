package com.orastays.flight.flightserver.service;

import org.springframework.stereotype.Service;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.BookingModel;
import com.orastays.flight.flightserver.model.PaymentModel;

@Service
public interface BookingService {
	
	PaymentModel addBooking(BookingModel bookingModel) throws FormExceptions;
}
