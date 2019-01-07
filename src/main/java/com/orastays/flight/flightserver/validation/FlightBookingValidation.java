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
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.FlightBookingModel;
import com.orastays.flight.flightserver.model.MultiCityModel;
import com.orastays.flight.flightserver.model.TravellerDetailsModel;
import com.orastays.flight.flightserver.model.TravellerParamModel;

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
			//GlobalParams
			if(Objects.isNull(flightBookingModel.getGlobalParamsModel())) {
				exceptions.put(messageUtil.getBundle("globalParams.null.code"), new Exception(messageUtil.getBundle("globalParams.null.message")));
			} else {
				if (StringUtils.isBlank(flightBookingModel.getGlobalParamsModel().getPricingId())) {
					exceptions.put(messageUtil.getBundle("pricingId.null.code"), new Exception(messageUtil.getBundle("pricingId.null.message")));
				}
				if (StringUtils.isBlank(flightBookingModel.getGlobalParamsModel().getPrq())) {
					exceptions.put(messageUtil.getBundle("searchId.null.code"), new Exception(messageUtil.getBundle("searchId.null.message")));
				}
				if (StringUtils.isBlank(flightBookingModel.getGlobalParamsModel().getSuperPnr())) {
					exceptions.put(messageUtil.getBundle("superPnr.null.code"), new Exception(messageUtil.getBundle("superPnr.null.message")));
				}
				if (StringUtils.isBlank(flightBookingModel.getGlobalParamsModel().getChannel())) {
					exceptions.put(messageUtil.getBundle("channel.null.code"), new Exception(messageUtil.getBundle("channel.null.message")));
				}
				if (StringUtils.isBlank(flightBookingModel.getGlobalParamsModel().getProduct())) {
					exceptions.put(messageUtil.getBundle("product.null.code"), new Exception(messageUtil.getBundle("product.null.message")));
				}
				if (StringUtils.isBlank(flightBookingModel.getGlobalParamsModel().getFtype())) {
					exceptions.put(messageUtil.getBundle("ftype.null.code"), new Exception(messageUtil.getBundle("ftype.null.message")));
				} else {
					if (!StringUtils.equals(flightBookingModel.getGlobalParamsModel().getFtype(), FlightConstant.ONEWAY) && 
							!StringUtils.equals(flightBookingModel.getGlobalParamsModel().getFtype(), FlightConstant.ROUNDTRIP) &&
							!StringUtils.equals(flightBookingModel.getGlobalParamsModel().getFtype(), FlightConstant.MULTICITY)) {

						exceptions.put(messageUtil.getBundle("ftype.invalid.code"), new Exception(messageUtil.getBundle("ftype.invalid.message")));
					}
				}
				if (StringUtils.isBlank(flightBookingModel.getGlobalParamsModel().getOrg())) {
					exceptions.put(messageUtil.getBundle("origin.null.code"), new Exception(messageUtil.getBundle("origin.null.message")));
				}
				if (StringUtils.isBlank(flightBookingModel.getGlobalParamsModel().getDest())) {
					exceptions.put(messageUtil.getBundle("destination.null.code"), new Exception(messageUtil.getBundle("destination.null.message")));
				}
				if (StringUtils.isBlank(flightBookingModel.getGlobalParamsModel().getDest())) {
					exceptions.put(messageUtil.getBundle("destination.null.code"), new Exception(messageUtil.getBundle("destination.null.message")));
				}
				if (StringUtils.isBlank(flightBookingModel.getGlobalParamsModel().getDest())) {
					exceptions.put(messageUtil.getBundle("destination.null.code"), new Exception(messageUtil.getBundle("destination.null.message")));
				}
				if (StringUtils.isBlank(flightBookingModel.getGlobalParamsModel().getChildTenant())) {
					exceptions.put(messageUtil.getBundle("child.tenant.null.code"), new Exception(messageUtil.getBundle("child.tenant.null.message")));
				}
			}

			//UserParams
			if(Objects.isNull(flightBookingModel.getUserParamsModel())) {
				exceptions.put(messageUtil.getBundle("userParams.null.code"), new Exception(messageUtil.getBundle("userParams.null.message")));
			} else {
				if (Objects.isNull(flightBookingModel.getUserParamsModel().getAdditionalContactModel())) {
					exceptions.put(messageUtil.getBundle("adtl.contact.null.code"), new Exception(messageUtil.getBundle("adtl.contact.null.message")));
				} else {
					if (StringUtils.isBlank(flightBookingModel.getUserParamsModel().getAdditionalContactModel().getEmail())) {
						exceptions.put(messageUtil.getBundle("email.null.code"), new Exception(messageUtil.getBundle("email.null.message")));
					} else {
						if(!Util.emailValidator(flightBookingModel.getUserParamsModel().getAdditionalContactModel().getEmail())) {
							exceptions.put(messageUtil.getBundle("email.invalid.code"), new Exception(messageUtil.getBundle("email.invalid.message")));
						}
					}
					if (StringUtils.isBlank(flightBookingModel.getUserParamsModel().getAdditionalContactModel().getMobile())) {
						exceptions.put(messageUtil.getBundle("mobile.null.code"), new Exception(messageUtil.getBundle("mobile.null.message")));
					} else {
						if (!Util.isNumeric(flightBookingModel.getUserParamsModel().getAdditionalContactModel().getMobile())) {
							exceptions.put(messageUtil.getBundle("mobile.invalid.code"), new Exception(messageUtil.getBundle("mobile.invalid.message")));
						}
					}
					if (StringUtils.isBlank(flightBookingModel.getUserParamsModel().getAdditionalContactModel().getMobileISD())) {
						exceptions.put(messageUtil.getBundle("mobile.isd.null.code"), new Exception(messageUtil.getBundle("mobile.isd.null.message")));
					} else {
						if (!Util.isNumeric(flightBookingModel.getUserParamsModel().getAdditionalContactModel().getMobileISD())) {
							exceptions.put(messageUtil.getBundle("mobile.isd.invalid.code"), new Exception(messageUtil.getBundle("mobile.isd.invalid.message")));
						}
					}
				}
				if(StringUtils.isBlank(flightBookingModel.getUserParamsModel().getEmailId())) {
					exceptions.put(messageUtil.getBundle("email.null.code"), new Exception(messageUtil.getBundle("email.null.message")));
				} else {
					if(!Util.emailValidator(flightBookingModel.getUserParamsModel().getEmailId())) {
						exceptions.put(messageUtil.getBundle("email.invalid.code"), new Exception(messageUtil.getBundle("email.invalid.message")));
					}
					if (StringUtils.isBlank(flightBookingModel.getUserParamsModel().getMobileNo())) {
						exceptions.put(messageUtil.getBundle("mobile.null.code"), new Exception(messageUtil.getBundle("mobile.null.message")));
					} else {
						if (!Util.isNumeric(flightBookingModel.getUserParamsModel().getMobileNo())) {
							exceptions.put(messageUtil.getBundle("mobile.invalid.code"), new Exception(messageUtil.getBundle("mobile.invalid.message")));
						}
					}
					if(StringUtils.isBlank(flightBookingModel.getUserParamsModel().getUserId())) {
						exceptions.put(messageUtil.getBundle("user.id.null.code"), new Exception(messageUtil.getBundle("user.id.null.message")));
					}
					if(StringUtils.isBlank(flightBookingModel.getUserParamsModel().getFirstName())) {
						exceptions.put(messageUtil.getBundle("first.name.null.code"), new Exception(messageUtil.getBundle("first.name.null.message")));
					}
					if(StringUtils.isBlank(flightBookingModel.getUserParamsModel().getMobileNoISD())) {
						exceptions.put(messageUtil.getBundle("mobile.isd.null.code"), new Exception(messageUtil.getBundle("mobile.isd.null.message")));
					} else {
						if (!Util.isNumeric(flightBookingModel.getUserParamsModel().getMobileNoISD())) {
							exceptions.put(messageUtil.getBundle("mobile.isd.invalid.code"), new Exception(messageUtil.getBundle("mobile.isd.invalid.message")));
						}
					}
				} 
			}
			//hotelCrosssellParams
			if(Objects.isNull(flightBookingModel.getHotelCrossSellParamsModel())) {
				exceptions.put(messageUtil.getBundle("hotelCross.null.code"), new Exception(messageUtil.getBundle("hotelCross.null.message")));
			}
			//productParams
			if(Objects.isNull(flightBookingModel.getProductParamsModel())) {
				exceptions.put(messageUtil.getBundle("productParams.null.code"), new Exception(messageUtil.getBundle("productParams.null.message")));
			} else {
				if (StringUtils.isBlank(flightBookingModel.getProductParamsModel().getTripType())) {
					exceptions.put(messageUtil.getBundle("trip.type.null.code"), new Exception(messageUtil.getBundle("trip.type.null.code")));
				} else {
					if (!StringUtils.equals(flightBookingModel.getGlobalParamsModel().getFtype(), FlightConstant.ONEWAY) && 
							!StringUtils.equals(flightBookingModel.getGlobalParamsModel().getFtype(), FlightConstant.ROUNDTRIP) &&
							!StringUtils.equals(flightBookingModel.getGlobalParamsModel().getFtype(), FlightConstant.MULTICITY)) {

						exceptions.put(messageUtil.getBundle("trip.type.invalid.code"), new Exception(messageUtil.getBundle("trip.type.invalid.code")));
					}
				}
			}
			//promoParams
			if(Objects.isNull(flightBookingModel.getPromoParamsModel())) {
				exceptions.put(messageUtil.getBundle("promoParams.null.code"), new Exception(messageUtil.getBundle("promoParams.null.message")));
			}
			//travellerParams
			if(Objects.isNull(flightBookingModel.getTravellerParamModels())) {
				exceptions.put(messageUtil.getBundle("travellerParams.null.code"), new Exception(messageUtil.getBundle("travellerParams.null.message")));
				for (TravellerParamModel travellerParamModel : flightBookingModel.getTravellerParamModels()) {
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
				
			} else {}
			//gstDetails
			if(Objects.isNull(flightBookingModel.getGstDetailsModel())) {
				exceptions.put(messageUtil.getBundle("gstDetails.null.code"), new Exception(messageUtil.getBundle("gstDetails.null.message")));
			}
			//totalBreakup
			if(Objects.isNull(flightBookingModel.getTotalBreakupModel())) {
				exceptions.put(messageUtil.getBundle("total.breakup.null.code"), new Exception(messageUtil.getBundle("total.breakup.null.message")));
			}
			//totalBreakup
			if(Objects.isNull(flightBookingModel.getTourCodeModels())) {
				exceptions.put(messageUtil.getBundle("tour.code.null.code"), new Exception(messageUtil.getBundle("tour.code.null.message")));
			}
			//gaResponse
			if(Objects.isNull(flightBookingModel.getGaResponseModel())) {
				exceptions.put(messageUtil.getBundle("ga.response.null.code"), new Exception(messageUtil.getBundle("ga.response.null.message")));
			} 
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
