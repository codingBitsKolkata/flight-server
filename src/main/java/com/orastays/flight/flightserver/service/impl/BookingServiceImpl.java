package com.orastays.flight.flightserver.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orastays.flight.flightserver.converter.BookingConverter;
import com.orastays.flight.flightserver.dao.BookingDAO;
import com.orastays.flight.flightserver.dao.BookingInfoDAO;
import com.orastays.flight.flightserver.entity.BookingEntity;
import com.orastays.flight.flightserver.entity.BookingInfoConverter;
import com.orastays.flight.flightserver.entity.BookingInfoEntity;
import com.orastays.flight.flightserver.entity.ConvenienceEntity;
import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.helper.BookingStatus;
import com.orastays.flight.flightserver.helper.Status;
import com.orastays.flight.flightserver.helper.SynchronizedRoomBooking;
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.BookingModel;
import com.orastays.flight.flightserver.model.PaymentModel;
import com.orastays.flight.flightserver.service.BookingService;
import com.orastays.flight.flightserver.service.ConvenienceService;
import com.orastays.flight.flightserver.validation.FlightBookingValidation;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	private static final Logger logger = LogManager.getLogger(BookingServiceImpl.class);

	@Autowired
	protected BookingDAO bookingDAO;

	@Autowired
	protected BookingConverter bookingConverter;

	@Autowired
	protected ConvenienceService convenienceService;

	@Autowired
	protected BookingInfoConverter bookingInfoConverter;
	
	@Autowired
	protected FlightBookingValidation flightBookingValidation;

	@Autowired
	protected BookingInfoDAO bookingInfoDAO;

	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;

	@Autowired
	protected SynchronizedRoomBooking synchronizedRoomBooking;

	@Override
	public PaymentModel addBooking(BookingModel bookingModel) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("addBooking -- START");
		}

		flightBookingValidation.validateBookingBeforePayment(bookingModel);
		PaymentModel paymentModel = populateBookingEntityForPayment(bookingModel);
		
		if (logger.isInfoEnabled()) {
			logger.info("addBooking -- END");
		}

		return paymentModel;

	}

	PaymentModel populateBookingEntityForPayment(BookingModel bookingModel) {

		BookingEntity bookingEntity = bookingConverter.modelToEntity(bookingModel);
		PaymentModel paymentModel = null;
		// set booking master attributes
		try {
			bookingEntity.setOrabookingId("ORA" + new Date().getTime());
			bookingEntity.setCreatedBy(Long.parseLong(bookingModel.getUserId()));
			bookingEntity.setCreatedDate(Util.getCurrentDateTime());
			bookingEntity.setStatus(BookingStatus.INACTIVE.ordinal());

			ConvenienceEntity convenienceEntity = convenienceService.getActiveConvenienceEntity();
			bookingEntity.setConvenienceEntity(convenienceEntity);
			Double convenienceAmountWithGst = Double.parseDouble(convenienceEntity.getAmount());
			convenienceAmountWithGst = Util.calculateGstPayableAmount(convenienceAmountWithGst,
					Double.parseDouble(convenienceEntity.getGstPercentage()));

			bookingEntity.setConvenienceAmtWgst(Util.roundTo2Places((convenienceAmountWithGst)));

			Double totalPayableWithoutGst = 0.0;
			Double totalPayableWithGst = 0.0;

			Long bookingId = (Long) bookingDAO.save(bookingEntity);
			BookingEntity bookingEntity2 = bookingDAO.find(bookingId);

			BookingInfoEntity bookingInfoEntity = bookingInfoConverter.modelToEntity(bookingModel.getBookingInfoModel());
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
			bookingEntity2.setTotalPaybleWithoutGST(Util.roundTo2Places(totalPayableWithoutGst));
			bookingEntity2.setTotalPaybleWithGST(Util.roundTo2Places(totalPayableWithGst));
			bookingEntity2.setGrandTotal(Util.roundTo2Places(totalPayableWithGst + convenienceAmountWithGst));

			bookingDAO.update(bookingEntity2);

			//set booking vs payment
			paymentModel = synchronizedRoomBooking.bookRoomForCashLessPayments(bookingModel, bookingEntity2);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return paymentModel;
	}
}
