package com.orastays.flight.flightserver.service;

import com.orastays.flight.flightserver.entity.BookingVsPaymentEntity;

public interface BookingVsPaymentService {
	BookingVsPaymentEntity getBookingVsPaymentEntityByOrderId(String orderId);
}
