package com.orastays.flight.flightserver.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.orastays.flight.flightserver.constants.BookingStatus;
import com.orastays.flight.flightserver.constants.PaymentStatus;
import com.orastays.flight.flightserver.constants.Status;
import com.orastays.flight.flightserver.entity.BookingEntity;
import com.orastays.flight.flightserver.entity.BookingInfoEntity;
import com.orastays.flight.flightserver.entity.BookingVsFlightEntity;
import com.orastays.flight.flightserver.entity.BookingVsPaymentEntity;
import com.orastays.flight.flightserver.entity.GatewayEntity;
import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.helper.FlightConstant;
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.BookingModel;
import com.orastays.flight.flightserver.model.PaymentModel;

@Component
public class BookingUtil extends BaseUtil {
	private static final Logger logger = LogManager.getLogger(BookingUtil.class);

	public BookingEntity generateBookingEntity(BookingModel bookingModel) throws FormExceptions {
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		try {
			BookingEntity bookingEntity = bookingConverter.modelToEntity(bookingModel);
			// set booking master attributes
			bookingEntity.setOrabookingId("ORA" + new Date().getTime());
			bookingEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
			bookingEntity.setCreatedDate(Util.getCurrentDateTime());
			bookingEntity.setStatus(BookingStatus.INACTIVE.ordinal());

			Long bookingId = (Long) bookingDAO.save(bookingEntity);
			BookingEntity bookingEntity2 = bookingDAO.find(bookingId);

			List<BookingVsFlightEntity> bookingVsRoomEntities = new ArrayList<>();

			bookingModel.getBookingVsRoomModels().forEach(room -> {
				BookingVsRoomEntity bookingVsRoomEntity = bookingVsRoomConverter.modelToEntity(room);

				bookingVsRoomEntity.setStatus(RoomStatus.INACTIVE.ordinal());
				bookingVsRoomEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
				bookingVsRoomEntity.setCreatedDate(Util.getCurrentDateTime());
				bookingVsRoomEntity.setSacCodeEntity(sacService.getActiveSacCodeEntity());
				bookingVsRoomEntity.setBookingEntity(bookingEntity2);

				bookingVsRoomDAO.save(bookingVsRoomEntity);
				bookingVsRoomEntities.add(bookingVsRoomEntity);
			});

			BookingInfoEntity bookingInfoEntity = bookingInfoConverter
					.modelToEntity(bookingModel.getBookingInfoModel());
			bookingInfoEntity.setCreatedDate(Util.getCurrentDateTime());
			bookingInfoEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
			bookingInfoEntity.setStatus(Status.ACTIVE.ordinal());
			bookingInfoEntity.setBookingEntity(bookingEntity2);

			bookingInfoDAO.save(bookingInfoEntity);

			bookingEntity2.setBookingInfoEntity(bookingInfoEntity);
			bookingEntity2.setBookingVsRoomEntities(bookingVsRoomEntities);
			return bookingEntity2;
		} catch (Exception e) {
			// throw new FormExceptions(exceptions)
			e.printStackTrace();
			exceptions.put(genericErrorCode, new Exception(genericErrorMessage));
			throw new FormExceptions(exceptions);
		}
	}

	public PaymentModel bookRoomForCashLessPayments(BookingModel bm, BookingEntity be) throws FormExceptions {

		logger.debug("bookRoomForCashLessPayments -- Start");

		BookingVsPaymentEntity bookingVsPaymentEntity = new BookingVsPaymentEntity();
		bookingVsPaymentEntity.setBookingEntity(be);
		bookingVsPaymentEntity.setCreatedBy(Long.parseLong(bm.getUserId()));
		bookingVsPaymentEntity.setCreatedDate(Util.getCurrentDateTime());
		bookingVsPaymentEntity.setOrderId("ORA_TRNS" + new Date().getTime());
		bookingVsPaymentEntity.setPercentage(Util.roundTo2Places(100.00)); // remove hardcode
		GatewayEntity gatewayEntity = gatewayService.getGatewayEntity(FlightConstant.CASHFREE);
		bookingVsPaymentEntity.setGatewayEntity(gatewayEntity);
		bookingVsPaymentEntity.setOrderAmount(be.getUserFinalPrice());
		bookingVsPaymentEntity.setAmountPaid(String.valueOf(Status.ZERO.ordinal()));
		bookingVsPaymentEntity.setStatus(PaymentStatus.ACTIVE.ordinal());

		PaymentModel paymentModel = cashFreeApi.getPaymentLink(bm, be, bookingVsPaymentEntity);

		logger.debug("bookRoomForCashLessPayments -- End");
		return paymentModel;
	}
}
