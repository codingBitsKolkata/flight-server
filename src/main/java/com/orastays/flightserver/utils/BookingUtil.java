package com.orastays.flightserver.utils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.orastays.flightserver.constants.BookingStatus;
import com.orastays.flightserver.constants.PaymentStatus;
import com.orastays.flightserver.constants.Status;
import com.orastays.flightserver.entity.BookingEntity;
import com.orastays.flightserver.entity.BookingInfoEntity;
import com.orastays.flightserver.entity.BookingVsPaymentEntity;
import com.orastays.flightserver.entity.ConvenienceEntity;
import com.orastays.flightserver.entity.GatewayEntity;
import com.orastays.flightserver.exceptions.FormExceptions;
import com.orastays.flightserver.helper.FlightConstant;
import com.orastays.flightserver.helper.Util;
import com.orastays.flightserver.model.BookingModel;
import com.orastays.flightserver.model.PaymentModel;

@Component
public class BookingUtil extends BaseUtil {
	private static final Logger logger = LogManager.getLogger(BookingUtil.class);

	public BookingEntity generateBookingEntity(BookingModel bookingModel) throws FormExceptions {
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		try {
			BookingEntity bookingEntity = bookingConverter.modelToEntity(bookingModel);
			// set booking master attributes
			bookingEntity.setOraBookingId("ORA" + new Date().getTime());
			bookingEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
			bookingEntity.setCreatedDate(Util.getCurrentDateTime());
			bookingEntity.setStatus(BookingStatus.INACTIVE.ordinal());
			bookingEntity.setProgress(FlightConstant.BEFORE_PAYMENT);

			/*	base_fare
				fuels_surcharges
				other_charges
				yatra_gst
				passenger_fee	
				user_dev_fee
				booking_fee
				igst
				total_fare(calculated from above 8 fields from bf)
				*/
			
			Long bookingId = (Long) bookingDAO.save(bookingEntity);
			//BookingEntity bookingEntity2 = bookingDAO.find(bookingId);

			//List<BookingVsRoomEntity> bookingVsRoomEntities = new ArrayList<>();
			
			/*bookingModel.getBookingVsRoomModels().forEach(room -> {
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
			return bookingEntity2;*/
			
			 ConvenienceEntity convenienceEntity = convenienceService.getActiveConvenienceEntity();
             bookingEntity.setConvenienceEntity(convenienceEntity);
             Double convenienceAmountWithGst = Double.parseDouble(convenienceEntity.getAmount());
             convenienceAmountWithGst = Util.calculateGstPayableAmount(convenienceAmountWithGst,
                             Double.parseDouble(convenienceEntity.getGstPercentage()));

             //bookingEntity.setConvenienceAmtWgst(Util.roundTo2Places((convenienceAmountWithGst)));

             Double totalPayableWithoutGst = 0.0;
             Double totalPayableWithGst = 0.0;
             
             BookingEntity bookingEntity2 = bookingDAO.find(bookingId);

             BookingInfoEntity bookingInfoEntity = null/*bookingInfoConverter.modelToEntity(bookingModel.getBookingInfoModel())*/;
             // set booking vs info
             if(bookingInfoEntity == null) {
                     bookingInfoEntity = new BookingInfoEntity();
             }
             bookingInfoEntity.setCreatedDate(Util.getCurrentDateTime());
             bookingInfoEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
             bookingInfoEntity.setStatus(Status.ACTIVE.ordinal());
             bookingInfoEntity.setBookingEntity(bookingEntity2);

             bookingInfoDAO.save(bookingInfoEntity);

             //update booking entity
             //bookingEntity2.setTotalPaybleWithoutGST(Util.roundTo2Places(totalPayableWithoutGst));
             //bookingEntity2.setTotalPaybleWithGST(Util.roundTo2Places(totalPayableWithGst));
             //bookingEntity2.setGrandTotal(Util.roundTo2Places(totalPayableWithGst + convenienceAmountWithGst));

             bookingDAO.update(bookingEntity2);
			
			return bookingEntity2;
		} catch (Exception e) {
			// throw new FormExceptions(exceptions)
			e.printStackTrace();
			exceptions.put(genericErrorCode, new Exception(genericErrorMessage));
			throw new FormExceptions(exceptions);
		}
	}

	public PaymentModel bookFlightForCashLessPayments(BookingModel bm, BookingEntity be) throws FormExceptions {

		logger.debug("bookFlightForCashLessPayments -- Start");

		BookingVsPaymentEntity bookingVsPaymentEntity = new BookingVsPaymentEntity();
		bookingVsPaymentEntity.setBookingEntity(be);
		bookingVsPaymentEntity.setCreatedBy(Long.parseLong(bm.getUserId()));
		bookingVsPaymentEntity.setCreatedDate(Util.getCurrentDateTime());
		bookingVsPaymentEntity.setOrderId("ORA_TRNS" + new Date().getTime());
		bookingVsPaymentEntity.setPercentage(Util.roundTo2Places(100.00)); // remove hardcode
		GatewayEntity gatewayEntity = gatewayService.getGatewayEntity(FlightConstant.CASHFREE);
		bookingVsPaymentEntity.setGatewayEntity(gatewayEntity);
		//bookingVsPaymentEntity.setOrderAmount(be.getUserFinalPrice());
		bookingVsPaymentEntity.setAmountPaid(String.valueOf(Status.ZERO.ordinal()));
		bookingVsPaymentEntity.setStatus(PaymentStatus.ACTIVE.ordinal());

		PaymentModel paymentModel = cashFreeApi.getPaymentLink(bm, be, bookingVsPaymentEntity);

		logger.debug("bookFlightForCashLessPayments -- End");
		return paymentModel;
	}
}
