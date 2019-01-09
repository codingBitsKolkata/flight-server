package com.orastays.flight.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flight.flightserver.entity.BookingVsPaymentEntity;

@Repository
public class BookingVsPaymentDAO extends GenericDAO<BookingVsPaymentEntity, Long> {

	private static final long serialVersionUID = 6412618599484770910L;

	public BookingVsPaymentDAO() {
		super(BookingVsPaymentEntity.class);

	}
}
