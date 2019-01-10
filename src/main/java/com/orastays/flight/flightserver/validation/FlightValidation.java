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
import com.orastays.flight.flightserver.model.FlightPriceModel;
import com.orastays.flight.flightserver.model.FlightSearchModel;
import com.orastays.flight.flightserver.model.MultiCityModel;

@Component
@Transactional
public class FlightValidation extends AuthorizeUserValidation {

	private static final Logger logger = LogManager.getLogger(FlightValidation.class);

	public FlightSearchModel validateOneWayData(FlightSearchModel flightSearchModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateOneWayData -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		if(Objects.nonNull(flightSearchModel)) {

			if(StringUtils.isBlank(flightSearchModel.getTripType())) {
				exceptions.put(messageUtil.getBundle("trip.type.null.code"), new Exception(messageUtil.getBundle("trip.type.null.message")));
			}

			if(StringUtils.isBlank(flightSearchModel.getNoOfSegments())) {
				exceptions.put(messageUtil.getBundle("segments.null.code"), new Exception(messageUtil.getBundle("segments.null.message")));
			}

			if(StringUtils.isBlank(flightSearchModel.getNoOfAdults())) {
				exceptions.put(messageUtil.getBundle("adult.number.null.code"), new Exception(messageUtil.getBundle("adult.number.null.message")));
			} else {
				int totalTravellers = Integer.parseInt(flightSearchModel.getNoOfAdults())+Integer.parseInt(flightSearchModel.getNoOfChild());
				if (totalTravellers>FlightConstant.MAX_TRAVELLERS) {
					exceptions.put(messageUtil.getBundle("max.traveller.exceed.code"), new Exception(messageUtil.getBundle("max.traveller.exceed.message")));
				}
				//Check INF<=ADT
				int numberOfAdult=Integer.parseInt(flightSearchModel.getNoOfAdults());
				int numberOfInfant=Integer.parseInt(flightSearchModel.getNoOfInfants());
				if (numberOfInfant>numberOfAdult) {
					exceptions.put(messageUtil.getBundle("infant.invalid.code"), new Exception(messageUtil.getBundle("infant.invalid.message")));
				}
			}

			if(StringUtils.isBlank(flightSearchModel.getClassType())) {
				exceptions.put(messageUtil.getBundle("class.type.null.code"), new Exception(messageUtil.getBundle("class.type.null.message")));
			}

			if(Objects.nonNull(flightSearchModel.getMultiCityModels())) {
				for(MultiCityModel multiCityModel:flightSearchModel.getMultiCityModels()) {
					if(StringUtils.isBlank(multiCityModel.getOrigin())) {
						exceptions.put(messageUtil.getBundle("origin.null.code"), new Exception(messageUtil.getBundle("origin.null.message")));
					}
					if(StringUtils.isBlank(multiCityModel.getDestination())) {
						exceptions.put(messageUtil.getBundle("destination.null.code"), new Exception(messageUtil.getBundle("destination.null.message")));
					}
					if(StringUtils.isBlank(multiCityModel.getOriginCountry())) {
						exceptions.put(messageUtil.getBundle("origin.country.null.code"), new Exception(messageUtil.getBundle("origin.country.null.message")));
					}
					if(StringUtils.isBlank(multiCityModel.getDestinationCountry())) {
						exceptions.put(messageUtil.getBundle("destination.country.null.code"), new Exception(messageUtil.getBundle("destination.country.null.message")));
					}
					if(StringUtils.isBlank(multiCityModel.getFlightDepartDate())) {
						exceptions.put(messageUtil.getBundle("depart.date.null.code"), new Exception(messageUtil.getBundle("depart.date.null.message")));
					}
					if(StringUtils.equals(multiCityModel.getOrigin(), multiCityModel.getDestination())) {
						exceptions.put(messageUtil.getBundle("origin.dest.same.code"), new Exception(messageUtil.getBundle("origin.dest.same.message")));
					}
				}
			}
		}	

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("validateOneWayData -- End");
		}	
		return flightSearchModel;
	}

	public FlightSearchModel validateRoundTripData(FlightSearchModel flightSearchModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateRoundTripData -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		if(Objects.nonNull(flightSearchModel)) {

			if(StringUtils.isBlank(flightSearchModel.getTripType())) {
				exceptions.put(messageUtil.getBundle("trip.type.null.code"), new Exception(messageUtil.getBundle("trip.type.null.message")));
			}

			if(StringUtils.isBlank(flightSearchModel.getNoOfSegments())) {
				exceptions.put(messageUtil.getBundle("segments.null.code"), new Exception(messageUtil.getBundle("segments.null.message")));
			}

			if(StringUtils.isBlank(flightSearchModel.getNoOfAdults())) {
				exceptions.put(messageUtil.getBundle("adult.number.null.code"), new Exception(messageUtil.getBundle("adult.number.null.message")));
			} else {
				int totalTravellers = Integer.parseInt(flightSearchModel.getNoOfAdults())+Integer.parseInt(flightSearchModel.getNoOfChild());
				if (totalTravellers>FlightConstant.MAX_TRAVELLERS) {
					exceptions.put(messageUtil.getBundle("max.traveller.exceed.code"), new Exception(messageUtil.getBundle("max.traveller.exceed.message")));
				}
				//Check INF<=ADT
				int numberOfAdult=Integer.parseInt(flightSearchModel.getNoOfAdults());
				int numberOfInfant=Integer.parseInt(flightSearchModel.getNoOfInfants());
				if (numberOfInfant>numberOfAdult) {
					exceptions.put(messageUtil.getBundle("infant.invalid.code"), new Exception(messageUtil.getBundle("infant.invalid.message")));
				}
			}

			if(StringUtils.isBlank(flightSearchModel.getClassType())) {
				exceptions.put(messageUtil.getBundle("class.type.null.code"), new Exception(messageUtil.getBundle("class.type.null.message")));
			}

			if(StringUtils.isBlank(flightSearchModel.getArrivalDate())) {
				exceptions.put(messageUtil.getBundle("arrival.date.null.code"), new Exception(messageUtil.getBundle("arrival.date.null.message")));
			}

			if(Objects.nonNull(flightSearchModel.getMultiCityModels())) {
				for(MultiCityModel multiCityModel:flightSearchModel.getMultiCityModels()) {
					if(StringUtils.isBlank(multiCityModel.getOrigin())) {
						exceptions.put(messageUtil.getBundle("origin.null.code"), new Exception(messageUtil.getBundle("origin.null.message")));
					}
					if(StringUtils.isBlank(multiCityModel.getDestination())) {
						exceptions.put(messageUtil.getBundle("destination.null.code"), new Exception(messageUtil.getBundle("destination.null.message")));
					}
					if(StringUtils.isBlank(multiCityModel.getOriginCountry())) {
						exceptions.put(messageUtil.getBundle("origin.country.null.code"), new Exception(messageUtil.getBundle("origin.country.null.message")));
					}
					if(StringUtils.isBlank(multiCityModel.getDestinationCountry())) {
						exceptions.put(messageUtil.getBundle("destination.country.null.code"), new Exception(messageUtil.getBundle("destination.country.null.message")));
					}
					if(StringUtils.isBlank(multiCityModel.getFlightDepartDate())) {
						exceptions.put(messageUtil.getBundle("depart.date.null.code"), new Exception(messageUtil.getBundle("depart.date.null.message")));
					}
					if(StringUtils.equals(multiCityModel.getOrigin(), multiCityModel.getDestination())) {
						exceptions.put(messageUtil.getBundle("origin.dest.same.code"), new Exception(messageUtil.getBundle("origin.dest.same.message")));
					}
				}
			}
		}	

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("validateRoundTripData -- End");
		}	
		return flightSearchModel;
	}

	public FlightSearchModel validateMulticityData(FlightSearchModel flightSearchModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateMulticityData -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		if(Objects.nonNull(flightSearchModel)) {

			if(StringUtils.isBlank(flightSearchModel.getTripType())) {
				exceptions.put(messageUtil.getBundle("trip.type.null.code"), new Exception(messageUtil.getBundle("trip.type.null.message")));
			}

			if(StringUtils.isBlank(flightSearchModel.getNoOfSegments())) {
				exceptions.put(messageUtil.getBundle("segments.null.code"), new Exception(messageUtil.getBundle("segments.null.message")));
			}

			if(StringUtils.isBlank(flightSearchModel.getNoOfAdults())) {
				exceptions.put(messageUtil.getBundle("adult.number.null.code"), new Exception(messageUtil.getBundle("adult.number.null.message")));
			} else {
				int totalTravellers = Integer.parseInt(flightSearchModel.getNoOfAdults())+Integer.parseInt(flightSearchModel.getNoOfChild());
				if (totalTravellers>FlightConstant.MAX_TRAVELLERS) {
					exceptions.put(messageUtil.getBundle("max.traveller.exceed.code"), new Exception(messageUtil.getBundle("max.traveller.exceed.message")));
				}
				//Check INF<=ADT
				int numberOfAdult=Integer.parseInt(flightSearchModel.getNoOfAdults());
				int numberOfInfant=Integer.parseInt(flightSearchModel.getNoOfInfants());
				if (numberOfInfant>numberOfAdult) {
					exceptions.put(messageUtil.getBundle("infant.invalid.code"), new Exception(messageUtil.getBundle("infant.invalid.message")));
				}
			}

			if(StringUtils.isBlank(flightSearchModel.getClassType())) {
				exceptions.put(messageUtil.getBundle("class.type.null.code"), new Exception(messageUtil.getBundle("class.type.null.message")));
			}

			if(Objects.nonNull(flightSearchModel.getMultiCityModels())) {
				for(MultiCityModel multiCityModel:flightSearchModel.getMultiCityModels()) {
					if(StringUtils.isBlank(multiCityModel.getOrigin())) {
						exceptions.put(messageUtil.getBundle("origin.null.code"), new Exception(messageUtil.getBundle("origin.null.message")));
					}
					if(StringUtils.isBlank(multiCityModel.getDestination())) {
						exceptions.put(messageUtil.getBundle("destination.null.code"), new Exception(messageUtil.getBundle("destination.null.message")));
					}
					if(StringUtils.isBlank(multiCityModel.getOriginCountry())) {
						exceptions.put(messageUtil.getBundle("origin.country.null.code"), new Exception(messageUtil.getBundle("origin.country.null.message")));
					}
					if(StringUtils.isBlank(multiCityModel.getDestinationCountry())) {
						exceptions.put(messageUtil.getBundle("destination.country.null.code"), new Exception(messageUtil.getBundle("destination.country.null.message")));
					}
					if(StringUtils.isBlank(multiCityModel.getFlightDepartDate())) {
						exceptions.put(messageUtil.getBundle("depart.date.null.code"), new Exception(messageUtil.getBundle("depart.date.null.message")));
					}
					if(StringUtils.equals(multiCityModel.getOrigin(), multiCityModel.getDestination())) {
						exceptions.put(messageUtil.getBundle("origin.dest.same.code"), new Exception(messageUtil.getBundle("origin.dest.same.message")));
					}
				}
			}
		}	

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("validateMulticityData -- End");
		}	
		return flightSearchModel;
	}

	public FlightPriceModel validateOneWayPricing(FlightPriceModel flightPriceModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateOneWayPricing -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		if(Objects.nonNull(flightPriceModel)) {

			if(StringUtils.isBlank(flightPriceModel.getTenantName())) {
				exceptions.put(messageUtil.getBundle("tenant.null.code"), new Exception(messageUtil.getBundle("tenant.null.message")));
			} else {
				if (!flightPriceModel.getTenantName().equals(FlightConstant.DOM_TENANT_NAME) && !flightPriceModel.getTenantName().equals(FlightConstant.INT_TENANT_NAME)) {
					exceptions.put(messageUtil.getBundle("tenant.invalid.code"), new Exception(messageUtil.getBundle("tenant.invalid.message")));
				}
			}

			if(StringUtils.isBlank(flightPriceModel.getSearchId())) {
				exceptions.put(messageUtil.getBundle("search.id.null.code"), new Exception(messageUtil.getBundle("search.id.null.message")));
			}

			if(StringUtils.isBlank(flightPriceModel.getMsid())) {
				exceptions.put(messageUtil.getBundle("msid.null.code"), new Exception(messageUtil.getBundle("msid.null.message")));
			}

			if(StringUtils.isBlank(flightPriceModel.getRequestMode())) {
				exceptions.put(messageUtil.getBundle("mode.null.code"), new Exception(messageUtil.getBundle("mode.null.message")));
			}

			if(StringUtils.isBlank(flightPriceModel.getFlightPrice())) {
				exceptions.put(messageUtil.getBundle("flight.price.null.code"), new Exception(messageUtil.getBundle("flight.price.null.message")));
			}

			if (StringUtils.isBlank(flightPriceModel.getSupplierCode())) {
				exceptions.put(messageUtil.getBundle("supplier.code.null.code"), new Exception(messageUtil.getBundle("supplier.code.null.message")));
			}	
			
			if (StringUtils.isBlank(flightPriceModel.getFlightId())){
				exceptions.put(messageUtil.getBundle("flight.id.null.code"), new Exception(messageUtil.getBundle("flight.id.null.message")));
			}
		}	

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("validateOneWayPricing -- End");
		}	
		return flightPriceModel;
	}

	public FlightPriceModel validateRoundTripPricing(FlightPriceModel flightPriceModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateRoundTripPricing -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		if(Objects.nonNull(flightPriceModel)) {


			if(StringUtils.isBlank(flightPriceModel.getTenantName())) {
				exceptions.put(messageUtil.getBundle("tenant.null.code"), new Exception(messageUtil.getBundle("tenant.null.message")));
			} else {
				if (flightPriceModel.getTenantName() != FlightConstant.DOM_TENANT_NAME || flightPriceModel.getTenantName() != FlightConstant.INT_TENANT_NAME) {
					exceptions.put(messageUtil.getBundle("tenant.invalid.code"), new Exception(messageUtil.getBundle("tenant.invalid.message")));
				}
			}

			if(StringUtils.isBlank(flightPriceModel.getSearchId())) {
				exceptions.put(messageUtil.getBundle("search.id.null.code"), new Exception(messageUtil.getBundle("search.id.null.message")));
			}

			if(StringUtils.isBlank(flightPriceModel.getMsid())) {
				exceptions.put(messageUtil.getBundle("msid.null.code"), new Exception(messageUtil.getBundle("msid.null.message")));
			}

			if(StringUtils.isBlank(flightPriceModel.getRequestMode())) {
				exceptions.put(messageUtil.getBundle("mode.null.code"), new Exception(messageUtil.getBundle("mode.null.message")));
			}

			if(StringUtils.isBlank(flightPriceModel.getFlightPrice())) {
				exceptions.put(messageUtil.getBundle("flight.price.null.code"), new Exception(messageUtil.getBundle("flight.price.null.message")));
			}

			if (StringUtils.isBlank(flightPriceModel.getSupplierCode())) {
				exceptions.put(messageUtil.getBundle("supplier.code.null.code"), new Exception(messageUtil.getBundle("supplier.code.null.message")));
			}	
			//flightIdCSV
			if (StringUtils.isBlank(flightPriceModel.getFlightId())){
				exceptions.put(messageUtil.getBundle("flight.id.null.code"), new Exception(messageUtil.getBundle("flight.id.null.message")));
			}
		}	

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("validateRoundTripPricing -- End");
		}	
		return flightPriceModel;
	}
	
	public FlightPriceModel validateMultiCityPricing(FlightPriceModel flightPriceModel) throws FormExceptions {

		if (logger.isDebugEnabled()) {
			logger.debug("validateMultiCityPricing -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		if(Objects.nonNull(flightPriceModel)) {

			if(StringUtils.isBlank(flightPriceModel.getTenantName())) {
				exceptions.put(messageUtil.getBundle("tenant.null.code"), new Exception(messageUtil.getBundle("tenant.null.message")));
			} else {
				if (!flightPriceModel.getTenantName().equals(FlightConstant.DOM_TENANT_NAME) || !flightPriceModel.getTenantName().equals(FlightConstant.INT_TENANT_NAME)) {
					exceptions.put(messageUtil.getBundle("tenant.invalid.code"), new Exception(messageUtil.getBundle("tenant.invalid.message")));
				}
			}

			if(StringUtils.isBlank(flightPriceModel.getSearchId())) {
				exceptions.put(messageUtil.getBundle("search.id.null.code"), new Exception(messageUtil.getBundle("search.id.null.message")));
			}

			if(StringUtils.isBlank(flightPriceModel.getMsid())) {
				exceptions.put(messageUtil.getBundle("msid.null.code"), new Exception(messageUtil.getBundle("msid.null.message")));
			}

			if(StringUtils.isBlank(flightPriceModel.getRequestMode())) {
				exceptions.put(messageUtil.getBundle("mode.null.code"), new Exception(messageUtil.getBundle("mode.null.message")));
			}

			if(StringUtils.isBlank(flightPriceModel.getFlightPrice())) {
				exceptions.put(messageUtil.getBundle("flight.price.null.code"), new Exception(messageUtil.getBundle("flight.price.null.message")));
			}

			if (StringUtils.isBlank(flightPriceModel.getSupplierCode())) {
				exceptions.put(messageUtil.getBundle("supplier.code.null.code"), new Exception(messageUtil.getBundle("supplier.code.null.message")));
			}	
			
			if (StringUtils.isBlank(flightPriceModel.getFlightId())){
				exceptions.put(messageUtil.getBundle("flight.id.null.code"), new Exception(messageUtil.getBundle("flight.id.null.message")));
			}	
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isDebugEnabled()) {
			logger.debug("validateMultiCityPricing -- End");
		}	
		
		return flightPriceModel;
	}
}
