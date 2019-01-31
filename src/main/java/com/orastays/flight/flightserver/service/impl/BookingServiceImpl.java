package com.orastays.flight.flightserver.service.impl;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.orastays.flight.flightserver.entity.BookingEntity;
import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.BookingModel;
import com.orastays.flight.flightserver.model.PaymentModel;
import com.orastays.flight.flightserver.service.BookingService;

@Service
@Transactional
public class BookingServiceImpl extends BaseServiceImpl implements BookingService {
	private static final Logger logger = LogManager.getLogger(BookingServiceImpl.class);
	@Override
	public PaymentModel addBooking(BookingModel bookingModel) throws FormExceptions {
		
		logger.info("addBooking -- START");
		
		BookingEntity bookingEntity = bookingUtil.generateBookingEntity(bookingModel);
		
		PaymentModel paymentModel = null;
		//proceed with online payment
		paymentModel = bookingUtil.bookFlightForCashLessPayments(bookingModel, bookingEntity);
		
		logger.info("addBooking -- END");
		return paymentModel;
	}
}
