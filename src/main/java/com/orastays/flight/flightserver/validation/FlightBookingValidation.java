package com.orastays.flight.flightserver.validation;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.FlightBookingModel;
import com.orastays.flight.flightserver.model.GlobalParamsModel;

@Component
@Transactional
public class FlightBookingValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(FlightBookingValidation.class);

	public FlightBookingModel validateBookingList(FlightBookingModel flightBookingModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateBookingList -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("validateBookingList -- End");
		}	
		return flightBookingModel;
	}

	public void validateReviewDetails(FlightBookingModel flightBookingModel) throws FormExceptions {
		
		if (logger.isDebugEnabled()) {
			logger.debug("validateReviewDetails -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if(Objects.nonNull(flightBookingModel)) {
			
			if(StringUtils.isBlank(flightBookingModel.getPricingId())) {
				exceptions.put(messageUtil.getBundle("pricing.id.null.code"), new Exception(messageUtil.getBundle("pricing.id.null.message")));
			} 
			if(StringUtils.isBlank(flightBookingModel.getSuperPnr())) {
				exceptions.put(messageUtil.getBundle("superPnr.null.code"), new Exception(messageUtil.getBundle("superPnr.null.message")));
			}
			if(Objects.nonNull(flightBookingModel.getGlobalParamsModel())) {
				for(GlobalParamsModel globalParamsModel:flightBookingModel.getGlobalParamsModel()) {}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("validateReviewDetails -- End");
		}	
	}
}
