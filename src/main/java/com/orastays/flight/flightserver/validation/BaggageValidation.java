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
import com.orastays.flight.flightserver.helper.FlightConstant;
import com.orastays.flight.flightserver.model.BaggageModel;
import com.orastays.flight.flightserver.model.FareRulesModel;

@Component
@Transactional
public class BaggageValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(BaggageValidation.class);

	public BaggageModel validateBaggageInfo(BaggageModel baggageModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateBaggageInfo -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		/*if(Objects.nonNull(baggageModel)) {

			if(StringUtils.isBlank(baggageModel.getTenantName())) {
				exceptions.put(messageUtil.getBundle("tenant.null.code"), new Exception(messageUtil.getBundle("tenant.null.message")));
			} else {
				if (!baggageModel.getTenantName().equals(FlightConstant.DOM_TENANT_NAME) && !baggageModel.getTenantName().equals(FlightConstant.INT_TENANT_NAME)) {
					exceptions.put(messageUtil.getBundle("tenant.invalid.code"), new Exception(messageUtil.getBundle("tenant.invalid.message")));
				}
			}
		}*/

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("validateBaggageInfo -- End");
		}	
		return baggageModel;
	}
}
