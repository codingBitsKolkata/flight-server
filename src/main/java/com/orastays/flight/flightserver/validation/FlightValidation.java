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
			
			if(StringUtils.isBlank(flightSearchModel.getTenantName())) {
				exceptions.put(messageUtil.getBundle("tenant.null.code"), new Exception(messageUtil.getBundle("tenant.null.message")));
			}
			
			if(StringUtils.isBlank(flightSearchModel.getTripType())) {
				exceptions.put(messageUtil.getBundle("trip.type.null.code"), new Exception(messageUtil.getBundle("trip.type.null.message")));
			}
			
			if(StringUtils.isBlank(flightSearchModel.getNoOfSegments())) {
				exceptions.put(messageUtil.getBundle("segments.null.code"), new Exception(messageUtil.getBundle("segments.null.message")));
			}
			
			if(StringUtils.isBlank(flightSearchModel.getNoOfAdults())) {
				exceptions.put(messageUtil.getBundle("adult.number.null.code"), new Exception(messageUtil.getBundle("adult.number.null.message")));
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
						exceptions.put(messageUtil.getBundle("depart.date.null.code"), new Exception(messageUtil.getBundle("depart.date.country.null.message")));
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
			
			if(StringUtils.isBlank(flightSearchModel.getTenantName())) {
				exceptions.put(messageUtil.getBundle("tenant.null.code"), new Exception(messageUtil.getBundle("tenant.null.message")));
			}
			
			if(StringUtils.isBlank(flightSearchModel.getTripType())) {
				exceptions.put(messageUtil.getBundle("trip.type.null.code"), new Exception(messageUtil.getBundle("trip.type.null.message")));
			}
			
			if(StringUtils.isBlank(flightSearchModel.getNoOfSegments())) {
				exceptions.put(messageUtil.getBundle("segments.null.code"), new Exception(messageUtil.getBundle("segments.null.message")));
			}
			
			if(StringUtils.isBlank(flightSearchModel.getNoOfAdults())) {
				exceptions.put(messageUtil.getBundle("adult.number.null.code"), new Exception(messageUtil.getBundle("adult.number.null.message")));
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
						exceptions.put(messageUtil.getBundle("depart.date.null.code"), new Exception(messageUtil.getBundle("depart.date.country.null.message")));
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
			
			if(StringUtils.isBlank(flightSearchModel.getTenantName())) {
				exceptions.put(messageUtil.getBundle("tenant.null.code"), new Exception(messageUtil.getBundle("tenant.null.message")));
			}
			
			if(StringUtils.isBlank(flightSearchModel.getTripType())) {
				exceptions.put(messageUtil.getBundle("trip.type.null.code"), new Exception(messageUtil.getBundle("trip.type.null.message")));
			}
			
			if(StringUtils.isBlank(flightSearchModel.getNoOfSegments())) {
				exceptions.put(messageUtil.getBundle("segments.null.code"), new Exception(messageUtil.getBundle("segments.null.message")));
			}
			
			if(StringUtils.isBlank(flightSearchModel.getNoOfAdults())) {
				exceptions.put(messageUtil.getBundle("adult.number.null.code"), new Exception(messageUtil.getBundle("adult.number.null.message")));
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
						exceptions.put(messageUtil.getBundle("depart.date.null.code"), new Exception(messageUtil.getBundle("depart.date.country.null.message")));
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

}
