package com.orastays.flightserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.flightserver.entity.BookingVsUserDetailsEntity;
import com.orastays.flightserver.helper.Util;
import com.orastays.flightserver.model.booking.BookingVsUserDetailsModel;

@Component
public class BookingVsUserDetailsConverter extends CommonConverter
		implements BaseConverter<BookingVsUserDetailsEntity, BookingVsUserDetailsModel> {

	private static final long serialVersionUID = -568674956078219768L;
	
	private static final Logger logger = LogManager.getLogger(BookingVsUserDetailsConverter.class);

	@Override
	public BookingVsUserDetailsEntity modelToEntity(BookingVsUserDetailsModel m) {

		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}

		BookingVsUserDetailsEntity bookingVsUserDetailsEntity = new BookingVsUserDetailsEntity();

		bookingVsUserDetailsEntity = (BookingVsUserDetailsEntity) Util.transform(modelMapper, m, bookingVsUserDetailsEntity);
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		return bookingVsUserDetailsEntity;
	}

	@Override
	public BookingVsUserDetailsModel entityToModel(BookingVsUserDetailsEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		BookingVsUserDetailsModel bookingVsUserDetailsModel = new BookingVsUserDetailsModel();
		bookingVsUserDetailsModel = (BookingVsUserDetailsModel) Util.transform(modelMapper, e, bookingVsUserDetailsModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return bookingVsUserDetailsModel;
	}

	@Override
	public List<BookingVsUserDetailsModel> entityListToModelList(List<BookingVsUserDetailsEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<BookingVsUserDetailsModel> bookingVsUserDetailsModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			bookingVsUserDetailsModels = new ArrayList<>();
			for (BookingVsUserDetailsEntity bookingVsUserDetailsEntity : es) {
				bookingVsUserDetailsModels.add(entityToModel(bookingVsUserDetailsEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return bookingVsUserDetailsModels;
	}

}
