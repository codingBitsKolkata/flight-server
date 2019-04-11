package com.orastays.flightserver.service;

import org.springframework.stereotype.Service;

import com.orastays.flightserver.exceptions.FormExceptions;
import com.orastays.flightserver.model.PaymentModel;
import com.orastays.flightserver.model.booking.BookingModel;

@Service
public interface BookingService {
	
	PaymentModel bookFlights(BookingModel bookingModel) throws FormExceptions;
}
