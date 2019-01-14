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

@Component
@Transactional
public class FlightBookingValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(FlightBookingValidation.class);

	public void validateBookingDetails(FlightBookingModel flightBookingModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateBookingDetails -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		if(Objects.isNull(flightBookingModel)) { 
			exceptions.put(messageUtil.getBundle("list.null.code"), new Exception(messageUtil.getBundle("list.null.message")));
		} else {
			if(StringUtils.isBlank(flightBookingModel.getPricingId())) {
				exceptions.put(messageUtil.getBundle("pricing.id.null.code"), new Exception(messageUtil.getBundle("pricing.id.null.message")));
			} 
			if(StringUtils.isBlank(flightBookingModel.getSuperPnr())) {
				exceptions.put(messageUtil.getBundle("superPnr.null.code"), new Exception(messageUtil.getBundle("superPnr.null.message")));
			}
			//ReviewJson
			if (Objects.isNull(flightBookingModel.getReviewJson())) {
				exceptions.put(messageUtil.getBundle("reviewJson.null.code"), new Exception(messageUtil.getBundle("reviewJson.null.message")));
			} else {/*
				//GlobalParams
				if(Objects.isNull(flightBookingModel.getReviewJsonModel().getGlobalParamsModel())) {
					exceptions.put(messageUtil.getBundle("globalParams.null.code"), new Exception(messageUtil.getBundle("globalParams.null.message")));
				} else {
					if (StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getGlobalParamsModel().getPricingId())) {
						exceptions.put(messageUtil.getBundle("pricing.id.null.code"), new Exception(messageUtil.getBundle("pricing.id.null.message")));
					}
					if (StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getGlobalParamsModel().getPrq())) {
						exceptions.put(messageUtil.getBundle("search.id.null.code"), new Exception(messageUtil.getBundle("search.id.null.message")));
					}
					if (StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getGlobalParamsModel().getSuperPnr())) {
						exceptions.put(messageUtil.getBundle("superPnr.null.code"), new Exception(messageUtil.getBundle("superPnr.null.message")));
					}
					if (StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getGlobalParamsModel().getFtype())) {
						exceptions.put(messageUtil.getBundle("trip.type.null.code"), new Exception(messageUtil.getBundle("trip.type.null.message")));
					} else {
						if (!StringUtils.equals(flightBookingModel.getReviewJsonModel().getGlobalParamsModel().getFtype(), FlightConstant.ONEWAY) && 
								!StringUtils.equals(flightBookingModel.getReviewJsonModel().getGlobalParamsModel().getFtype(), FlightConstant.ROUNDTRIP) &&
								!StringUtils.equals(flightBookingModel.getReviewJsonModel().getGlobalParamsModel().getFtype(), FlightConstant.MULTICITY)) {

							exceptions.put(messageUtil.getBundle("trip.type.invalid.code"), new Exception(messageUtil.getBundle("trip.type.invalid.message")));
						}
					}
					if (StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getGlobalParamsModel().getOrg())) {
						exceptions.put(messageUtil.getBundle("origin.null.code"), new Exception(messageUtil.getBundle("origin.null.message")));
					}
					if (StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getGlobalParamsModel().getDest())) {
						exceptions.put(messageUtil.getBundle("destination.null.code"), new Exception(messageUtil.getBundle("destination.null.message")));
					}
					if (StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getGlobalParamsModel().getEbsSessionId())) {
						exceptions.put(messageUtil.getBundle("ebs.session.null.code"), new Exception(messageUtil.getBundle("ebs.session.null.message")));
					}
				}

				//UserParams should be handled from backend
				if(Objects.isNull(flightBookingModel.getReviewJsonModel().getUserParamsModel())) {
					exceptions.put(messageUtil.getBundle("userParams.null.code"), new Exception(messageUtil.getBundle("userParams.null.message")));
				} else {
					if (Objects.isNull(flightBookingModel.getReviewJsonModel().getUserParamsModel().getAdditionalContactModel())) {
						exceptions.put(messageUtil.getBundle("adtl.contact.null.code"), new Exception(messageUtil.getBundle("adtl.contact.null.message")));
					} else {
						if (StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getUserParamsModel().getAdditionalContactModel().getEmail())) {
							exceptions.put(messageUtil.getBundle("email.null.code"), new Exception(messageUtil.getBundle("email.null.message")));
						} else {
							if(!Util.emailValidator(flightBookingModel.getReviewJsonModel().getUserParamsModel().getAdditionalContactModel().getEmail())) {
								exceptions.put(messageUtil.getBundle("email.invalid.code"), new Exception(messageUtil.getBundle("email.invalid.message")));
							}
						}
						if (StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getUserParamsModel().getAdditionalContactModel().getMobile())) {
							exceptions.put(messageUtil.getBundle("mobile.null.code"), new Exception(messageUtil.getBundle("mobile.null.message")));
						} else {
							if (!Util.isNumeric(flightBookingModel.getReviewJsonModel().getUserParamsModel().getAdditionalContactModel().getMobile())) {
								exceptions.put(messageUtil.getBundle("mobile.invalid.code"), new Exception(messageUtil.getBundle("mobile.invalid.message")));
							}
						}
						if (StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getUserParamsModel().getAdditionalContactModel().getMobileISD())) {
							exceptions.put(messageUtil.getBundle("mobile.isd.null.code"), new Exception(messageUtil.getBundle("mobile.isd.null.message")));
						} else {
							if (!Util.isNumeric(flightBookingModel.getReviewJsonModel().getUserParamsModel().getAdditionalContactModel().getMobileISD())) {
								exceptions.put(messageUtil.getBundle("mobile.isd.invalid.code"), new Exception(messageUtil.getBundle("mobile.isd.invalid.message")));
							}
						}
					}
					if(StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getUserParamsModel().getEmailId())) {
						exceptions.put(messageUtil.getBundle("email.null.code"), new Exception(messageUtil.getBundle("email.null.message")));
					} else {
						if(!Util.emailValidator(flightBookingModel.getReviewJsonModel().getUserParamsModel().getEmailId())) {
							exceptions.put(messageUtil.getBundle("email.invalid.code"), new Exception(messageUtil.getBundle("email.invalid.message")));
						}
						if (StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getUserParamsModel().getMobileNo())) {
							exceptions.put(messageUtil.getBundle("mobile.null.code"), new Exception(messageUtil.getBundle("mobile.null.message")));
						} else {
							if (!Util.isNumeric(flightBookingModel.getReviewJsonModel().getUserParamsModel().getMobileNo())) {
								exceptions.put(messageUtil.getBundle("mobile.invalid.code"), new Exception(messageUtil.getBundle("mobile.invalid.message")));
							}
						}
						if(StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getUserParamsModel().getUserId())) {
							exceptions.put(messageUtil.getBundle("user.id.null.code"), new Exception(messageUtil.getBundle("user.id.null.message")));
						}
						if(StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getUserParamsModel().getFirstName())) {
							exceptions.put(messageUtil.getBundle("first.name.null.code"), new Exception(messageUtil.getBundle("first.name.null.message")));
						}
						if(StringUtils.isBlank(flightBookingModel.getReviewJsonModel().getUserParamsModel().getMobileNoISD())) {
							exceptions.put(messageUtil.getBundle("mobile.isd.null.code"), new Exception(messageUtil.getBundle("mobile.isd.null.message")));
						} else {
							if (!Util.isNumeric(flightBookingModel.getReviewJsonModel().getUserParamsModel().getMobileNoISD())) {
								exceptions.put(messageUtil.getBundle("mobile.isd.invalid.code"), new Exception(messageUtil.getBundle("mobile.isd.invalid.message")));
							}
						}
					} 
				}
				//travellerParams
				if(Objects.isNull(flightBookingModel.getReviewJsonModel().getTravellerParamModels())) {
					exceptions.put(messageUtil.getBundle("travellerParams.null.code"), new Exception(messageUtil.getBundle("travellerParams.null.message")));
				} else {
					for (TravellerParamModel travellerParamModel : flightBookingModel.reviewJsonModel.getTravellerParamModels()) {
						if (StringUtils.isBlank(travellerParamModel.getPaxID())) {
							exceptions.put(messageUtil.getBundle("paxId.null.code"), new Exception(messageUtil.getBundle("paxId.null.message")));
						}
						if (Objects.isNull(travellerParamModel.getTravellerDetailsModel())) {
							exceptions.put(messageUtil.getBundle("traveller.details.null.code"), new Exception(messageUtil.getBundle("traveller.details.null.message")));
						} else {
							if (StringUtils.isBlank(travellerParamModel.getTravellerDetailsModel().getId())) {
								exceptions.put(messageUtil.getBundle("traveller.id.null.code"), new Exception(messageUtil.getBundle("traveller.id.null.message")));
							}
							if (StringUtils.isBlank(travellerParamModel.getTravellerDetailsModel().getTitle())) {
								exceptions.put(messageUtil.getBundle("title.null.code"), new Exception(messageUtil.getBundle("title.null.message")));
							}
							if (StringUtils.isBlank(travellerParamModel.getTravellerDetailsModel().getFirstName())) {
								exceptions.put(messageUtil.getBundle("first.name.null.code"), new Exception(messageUtil.getBundle("first.name.null.message")));
							}
							if (StringUtils.isBlank(travellerParamModel.getTravellerDetailsModel().getLastName())) {
								exceptions.put(messageUtil.getBundle("last.name.null.code"), new Exception(messageUtil.getBundle("last.name.null.message")));
							}
							if (StringUtils.isBlank(travellerParamModel.getTravellerDetailsModel().getPaxClass())) {
								exceptions.put(messageUtil.getBundle("pax.class.null.code"), new Exception(messageUtil.getBundle("pax.class.null.message")));
							}
							if (StringUtils.isBlank(travellerParamModel.getTravellerDetailsModel().getPassengerClass())) {
								exceptions.put(messageUtil.getBundle("passenger.class.null.code"), new Exception(messageUtil.getBundle("passenger.class.null.message")));
							}
						}
					}
				}
			*/}
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("validateBookingDetails -- End");
		}	
	}

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
}
