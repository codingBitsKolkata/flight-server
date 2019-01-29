package com.orastays.flight.flightserver.helper;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orastays.flight.flightserver.constants.PaymentStatus;
import com.orastays.flight.flightserver.constants.Status;
import com.orastays.flight.flightserver.dao.BookingDAO;
import com.orastays.flight.flightserver.dao.BookingVsPaymentDAO;
import com.orastays.flight.flightserver.entity.BookingEntity;
import com.orastays.flight.flightserver.entity.BookingVsPaymentEntity;
import com.orastays.flight.flightserver.entity.GatewayEntity;
import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.BookingModel;
import com.orastays.flight.flightserver.model.PaymentModel;
import com.orastays.flight.flightserver.service.BookingVsPaymentService;
import com.orastays.flight.flightserver.service.GatewayService;

@Component
public class SynchronizedRoomBooking {

	private static final Logger logger = LogManager.getLogger(SynchronizedRoomBooking.class);

	@Autowired
	protected BookingVsPaymentService bookingVsPaymentService;

	@Autowired
	protected BookingDAO bookingDAO;

	@Autowired
	protected BookingVsPaymentDAO bookingVsPaymentDAO;

	@Autowired
	protected GatewayService gatewayService;

	@Autowired
	protected CashFreeApi cashFreeApi;

	@Autowired
	protected MessageUtil messageUtil;


	public PaymentModel bookRoomForCashLessPayments(BookingModel bm, BookingEntity be) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("bookRoomForCashLessPayments -- Start");
		}
		BookingVsPaymentEntity bookingVsPaymentEntity = new BookingVsPaymentEntity();
		bookingVsPaymentEntity.setBookingEntity(be);
		bookingVsPaymentEntity.setCreatedBy(Long.parseLong(bm.getUserId()));
		bookingVsPaymentEntity.setCreatedDate(Util.getCurrentDateTime());
		bookingVsPaymentEntity.setOrderId("ORA_TRNS" + new Date().getTime());
		bookingVsPaymentEntity.setPercentage(Util.roundTo2Places(100.00)); // remove hardcode
		GatewayEntity gatewayEntity = gatewayService.getGatewayEntity(FlightConstant.MODE_CASHLESS);
		bookingVsPaymentEntity.setGatewayEntity(gatewayEntity);
		//bookingVsPaymentEntity.setOrderAmount(be.getGrandTotal());
		bookingVsPaymentEntity.setAmountPaid(String.valueOf(Status.ZERO.ordinal()));
		bookingVsPaymentEntity.setStatus(PaymentStatus.ACTIVE.ordinal());

		PaymentModel paymentModel = cashFreeApi.getPaymentLink(bm, be, bookingVsPaymentEntity);
		if (logger.isInfoEnabled()) {
			logger.info("bookRoomForCashLessPayments -- End");
		}
		return paymentModel;
	}
}
