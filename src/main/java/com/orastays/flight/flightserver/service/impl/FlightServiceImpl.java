package com.orastays.flight.flightserver.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;
import javax.ws.rs.core.UriBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.helper.FlightConstant;
import com.orastays.flight.flightserver.helper.MessageUtil;
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.FlightPriceModel;
import com.orastays.flight.flightserver.model.FlightSearchModel;
import com.orastays.flight.flightserver.model.MultiCityModel;
import com.orastays.flight.flightserver.model.SearchParameterModel;
import com.orastays.flight.flightserver.service.FlightService;

@Service
@Transactional
public class FlightServiceImpl extends BaseServiceImpl implements FlightService {

	@Autowired
	protected MessageUtil messageUtil;
	
	private static final Logger logger = LogManager.getLogger(FlightServiceImpl.class);

	@Override
	public List<SearchParameterModel> searchAirportDetails(String keyword) {
		
		if (logger.isInfoEnabled()) {
			logger.info("searchAirportDetails -- START");
		}
		
		List<SearchParameterModel> searchParameterModels = new ArrayList<>();

		try {
	
			searchParameterModels = searchParameterConverter.entityListToModelList(searchParameterDAO.fetchSearchDetails(keyword));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in searchAirportDetails -- "+Util.errorToString(e));
			}
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("searchAirportDetails -- END");
		}
		
		return searchParameterModels;
	}
	
	@Override
	public String fetchOneWayFlights(FlightSearchModel flightSearchModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("fetchOneWayFlights -- START");
		}
		
		flightValidation.validateOneWayData(flightSearchModel);
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();
		String searchResponse = null;
		try {
			searchResponse = oneWayFetch(flightSearchModel);
			JSONObject jsonObj = new JSONObject(searchResponse);
			boolean eagerFetch = jsonObj.getBoolean("eagerFetch");
			if (!eagerFetch) {
				boolean eagerFetchStop=false;
				for (int i=0;i<=2;i++) {
					searchResponse = oneWayFetch(flightSearchModel);
					JSONObject jsonObj1 = new JSONObject(searchResponse);
					eagerFetchStop = jsonObj1.getBoolean("eagerFetch");
					if (eagerFetchStop) {
						return searchResponse;
					} 
				} 
				//Check response code
				if (!eagerFetchStop){
					exceptions.put(messageUtil.getBundle("common.error.code"), new Exception(messageUtil.getBundle("common.error..message")));
				} 
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchOneWayFlights -- END");
		}
		
		return searchResponse;
	}

	@Override
	public String fetchRoundTripFlights(FlightSearchModel flightSearchModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("fetchRoundTripFlights -- START");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		flightValidation.validateRoundTripData(flightSearchModel);
		String response = null;
		try {
			response = roundTripFetch(flightSearchModel);
			JSONObject jsonObj = new JSONObject(response);
			boolean eagerFetch = jsonObj.getBoolean("eagerFetch");
			if (!eagerFetch) {
				boolean eagerFetchStop=false;
				for (int i=0;i<=2;i++) {
					response = roundTripFetch(flightSearchModel);
					JSONObject jsonObj1 = new JSONObject(response);
					eagerFetchStop = jsonObj1.getBoolean("eagerFetch");
					if (eagerFetchStop) {
						return response;
					} 
				} 
				//Check response code
				if (!eagerFetchStop){
					exceptions.put(messageUtil.getBundle("common.error.code"), new Exception(messageUtil.getBundle("common.error..message")));
				} 
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchRoundTripFlights -- END");
		}

		return response;
	}

	@Override
	public String fetchMultiCityFlights(FlightSearchModel flightSearchModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("fetchMultiCityFlights -- START");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		flightValidation.validateMulticityData(flightSearchModel);
		String response = null;
		try {
			response = multiCityFetch(flightSearchModel);
			JSONObject jsonObj = new JSONObject(response);
			boolean eagerFetch = jsonObj.getBoolean("eagerFetch");
			if (!eagerFetch) {
				boolean eagerFetchStop=false;
				for (int i=0;i<=2;i++) {
					response = multiCityFetch(flightSearchModel);
					JSONObject jsonObj1 = new JSONObject(response);
					eagerFetchStop = jsonObj1.getBoolean("eagerFetch");
					if (eagerFetchStop) {
						return response;
					} 
				} 
				//Check response code
				if (!eagerFetchStop){
					exceptions.put(messageUtil.getBundle("common.error.code"), new Exception(messageUtil.getBundle("common.error..message")));
				} 
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);

		if (logger.isInfoEnabled()) {
			logger.info("fetchMultiCityFlights -- END");
		}

		return response;
	}

	//Call one way search api
	public String oneWayFetch(FlightSearchModel flightSearchModel) {

		if (logger.isInfoEnabled()) {
			logger.info("oneWayFetch -- START");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("emailId", messageUtil.getBundle("flight.email"));
		headers.add("password", messageUtil.getBundle("flight.password"));
		headers.add("apikey", messageUtil.getBundle("flight.key"));
		
		Map<String, String> newModel = new HashMap<>();
		for(MultiCityModel multiCityModel:flightSearchModel.getMultiCityModels()) {
			newModel.put("origin", multiCityModel.getOrigin());
			newModel.put("originCountry", multiCityModel.getOriginCountry());
			newModel.put("destination", multiCityModel.getDestination());
			newModel.put("destinationCountry", multiCityModel.getDestinationCountry());
			newModel.put("flight_depart_date", multiCityModel.getFlightDepartDate());
		}
		
		String tenantName = null;
		for(MultiCityModel multiCityModel:flightSearchModel.getMultiCityModels()) {
			String originCountryCode = searchParameterDAO.fetchCountryCode(multiCityModel.getOrigin());
			String destCountryCode = searchParameterDAO.fetchCountryCode(multiCityModel.getDestination());
			if (originCountryCode.equals(destCountryCode)) {
				tenantName = FlightConstant.DOM_TENANT_NAME;
			} else {
				tenantName = FlightConstant.INT_TENANT_NAME;
			}
		}
		
		String tripType = FlightConstant.ONEWAY;
		String viewName = FlightConstant.VIEW_NAME;
		String noOfSegments = FlightConstant.SEGMENTS_ONE_WAY;
		String ADT = flightSearchModel.getNoOfAdults();
		String CHD = flightSearchModel.getNoOfChild();
		String INF = flightSearchModel.getNoOfInfants();
		String classType = flightSearchModel.getClassType();

		String createUrl = messageUtil.getBundle("flight.search.server.url")+tenantName+"/search?"+"type="+tripType+"&viewName="+viewName+"&noOfSegments="+noOfSegments+
				"&origin="+newModel.get("origin")+"&originCountry="+newModel.get("originCountry")+"&destination="+newModel.get("destination")+
				"&destinationCountry="+newModel.get("destinationCountry")+"&flight_depart_date="+newModel.get("flight_depart_date")+"&ADT="+ADT+
				"&CHD="+CHD+"&INF="+INF+"&class="+classType;

		System.out.println("createUrl::"+createUrl);
		ResponseEntity<String> responseEntity = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = UriComponentsBuilder.fromUriString(createUrl).build().encode().toUri();
			RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
			responseEntity = restTemplate.exchange(requestEntity, String.class);

		} catch (Exception e) {
			logger.info("Error in oneWayFetch response");
			e.getStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("oneWayFetch -- END");
		}

		return responseEntity.getBody();
	}

	public String roundTripFetch(FlightSearchModel flightSearchModel) {

		if (logger.isInfoEnabled()) {
			logger.info("roundTripFetch -- START");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("emailId", messageUtil.getBundle("flight.email"));
		headers.add("password", messageUtil.getBundle("flight.password"));
		headers.add("apikey", messageUtil.getBundle("flight.key"));

		Map<String, String> newModel = new HashMap<>();
		for(MultiCityModel multiCityModel:flightSearchModel.getMultiCityModels()) {
			newModel.put("origin", multiCityModel.getOrigin());
			newModel.put("originCountry", multiCityModel.getOriginCountry());
			newModel.put("destination", multiCityModel.getDestination());
			newModel.put("destinationCountry", multiCityModel.getDestinationCountry());
			newModel.put("flight_depart_date", multiCityModel.getFlightDepartDate());
		}

		String tenantName = null;
		for(MultiCityModel multiCityModel:flightSearchModel.getMultiCityModels()) {
			String originCountryCode = searchParameterDAO.fetchCountryCode(multiCityModel.getOrigin());
			String destCountryCode = searchParameterDAO.fetchCountryCode(multiCityModel.getDestination());
			if (originCountryCode.equals(destCountryCode)) {
				tenantName = FlightConstant.DOM_TENANT_NAME;
			} else {
				tenantName = FlightConstant.INT_TENANT_NAME;
			}
		}
		String tripType = FlightConstant.ROUNDTRIP;
		String viewName = FlightConstant.VIEW_NAME;
		String noOfSegments = FlightConstant.SEGMENTS_ROUND_TRIP;
		String arrivalDate = flightSearchModel.getArrivalDate();
		String ADT = flightSearchModel.getNoOfAdults();
		String CHD = flightSearchModel.getNoOfChild();
		String INF = flightSearchModel.getNoOfInfants();
		String classType = flightSearchModel.getClassType();

		String createUrl = messageUtil.getBundle("flight.search.server.url")+tenantName+"/search?"+"type="+tripType+"&viewName="+viewName+"&noOfSegments="+noOfSegments+
				"&origin="+newModel.get("origin")+"&originCountry="+newModel.get("originCountry")+"&destination="+newModel.get("destination")+
				"&destinationCountry="+newModel.get("destinationCountry")+"&flight_depart_date="+newModel.get("flight_depart_date")+"&arrivalDate="+arrivalDate+
				"&ADT="+ADT+"&CHD="+CHD+"&INF="+INF+"&class="+classType;
		System.out.println("createUrl::"+createUrl);
		ResponseEntity<String> responseEntity = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = UriComponentsBuilder.fromUriString(createUrl).build().encode().toUri();
			RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
			responseEntity = restTemplate.exchange(requestEntity, String.class);

		} catch (Exception e) {
			logger.info("Error in roundTripFetch response");
			e.getStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("roundTripFetch -- END");
		}

		return responseEntity.getBody();
	}

	public String multiCityFetch(FlightSearchModel flightSearchModel) {

		if (logger.isInfoEnabled()) {
			logger.info("multiCityFetch -- START");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("emailId", messageUtil.getBundle("flight.email"));
		headers.add("password", messageUtil.getBundle("flight.password"));
		headers.add("apikey", messageUtil.getBundle("flight.key"));

		Map<String, String> newModel = new HashMap<>();
		int i=0;
		for(MultiCityModel multiCityModel:flightSearchModel.getMultiCityModels()) {
			newModel.put("origin_"+i, multiCityModel.getOrigin());
			newModel.put("originCountry_"+i, multiCityModel.getOriginCountry());
			newModel.put("destination_"+i, multiCityModel.getDestination());
			newModel.put("destinationCountry_"+i, multiCityModel.getDestinationCountry());
			newModel.put("flight_depart_date_"+i, multiCityModel.getFlightDepartDate());
			i++;
		}

		String tenantName = null;
		for(MultiCityModel multiCityModel:flightSearchModel.getMultiCityModels()) {
			String originCountryCode = searchParameterDAO.fetchCountryCode(multiCityModel.getOrigin());
			String destCountryCode = searchParameterDAO.fetchCountryCode(multiCityModel.getDestination());
			if (originCountryCode.equals(destCountryCode)) {
				tenantName = FlightConstant.DOM_TENANT_NAME;
			} else {
				tenantName = FlightConstant.INT_TENANT_NAME;
			}
		}
		String viewName = FlightConstant.VIEW_NAME;
		String tripType = FlightConstant.MULTICITY;
		String ADT = flightSearchModel.getNoOfAdults();
		String CHD = flightSearchModel.getNoOfChild();
		String INF = flightSearchModel.getNoOfInfants();
		String classType = flightSearchModel.getClassType();
		String noOfSegments = FlightConstant.SEGMENTS_MULTICITY;

		UriBuilder builder = UriBuilder
				.fromPath(messageUtil.getBundle("flight.search.server.url")+tenantName+"/search")
				.queryParam("viewName", viewName)
				.queryParam("type", tripType)
				.queryParam("ADT", ADT)
				.queryParam("CHD", CHD)
				.queryParam("INF", INF)
				.queryParam("class", classType)
				.queryParam("noOfSegments", noOfSegments);

		for (Entry<String, String> entry : newModel.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}

		URI uri = builder.build();
		ResponseEntity<String> responseEntity = null;
		try {
			RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
			RestTemplate restTemplate = new RestTemplate();
			responseEntity = restTemplate.exchange(requestEntity, String.class);

		} catch (Exception e) {
			logger.info("Error in multiCityFetch response");
			e.getStackTrace();
		}		
		if (logger.isInfoEnabled()) {
			logger.info("multiCityFetch -- END");
		}

		return responseEntity.getBody();
	}

	@Override
	public String fetchOneWayPricing(FlightPriceModel flightPriceModel) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchOneWayPricing -- START");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();

		flightValidation.validateOneWayPricing(flightPriceModel);
		String response = null;
		try {
			response = callOneWayPricing(flightPriceModel);
			JSONObject jsonObj = new JSONObject(response);
			boolean shouldRetry = jsonObj.getBoolean("shouldRetry");
			if (shouldRetry) {
				boolean shouldRetryStop=true;
				for (int i=0;i<=2;i++) {
					response = callOneWayPricing(flightPriceModel);
					JSONObject jsonObj1 = new JSONObject(response);
					shouldRetryStop = jsonObj1.getBoolean("shouldRetry");
					if (!shouldRetryStop) {
						return response;
					}
				}
				if(shouldRetry) {
					exceptions.put(messageUtil.getBundle("common.error.code"), new Exception(messageUtil.getBundle("common.error..message")));
				}
			}
		} catch (Exception e) {
			logger.info("Error in fetchOneWayPricing response -- END");
			e.getStackTrace();
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchOneWayPricing -- END");
		}

		return response;
	}
	public String callOneWayPricing(FlightPriceModel flightPriceModel) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("callOneWayPricing -- START");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("emailId", messageUtil.getBundle("flight.email"));
		headers.add("password", messageUtil.getBundle("flight.password"));
		headers.add("apikey", messageUtil.getBundle("flight.key"));

		String tenantName = flightPriceModel.getTenantName();
		String searchId = flightPriceModel.getSearchId();
		String msid = flightPriceModel.getMsid();
		String requestMode = FlightConstant.REQUEST_MODE; 
		String flightId = flightPriceModel.getFlightId();
		String flightPrice = flightPriceModel.getFlightPrice();
		String supplierCode = flightPriceModel.getSupplierCode();

		String createUrl = messageUtil.getBundle("flight.search.server.url")+tenantName+"/price?"+"searchId="+searchId+"&msid="+msid+"&mode="+requestMode+
				"&bpc="+FlightConstant.BPC+"&isSR="+FlightConstant.ISSR+"&unique="+FlightConstant.UNIQUE+"&variation="+FlightConstant.VARIATION+
				"&flightIdCSV="+flightId+"&flightPrice="+flightPrice+"&sc="+supplierCode;

		ResponseEntity<String> responseEntity = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = UriComponentsBuilder.fromUriString(createUrl).build().encode().toUri();
			RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
			responseEntity = restTemplate.exchange(requestEntity, String.class);

		} catch (Exception e) {
			logger.info("Error in CallOneWayPricing response -- END");
			e.getStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("callOneWayPricing -- END");
		}

		return responseEntity.getBody();
	}
	
	@Override
	public String fetchRoundTripPricing(FlightPriceModel flightPriceModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("fetchRoundTripPricing -- START");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		flightValidation.validateRoundTripPricing(flightPriceModel);
		String response = null;
		try {
			response = callRoundTripPricing(flightPriceModel);
			JSONObject jsonObj = new JSONObject(response);
			boolean shouldRetry = jsonObj.getBoolean("shouldRetry");
			if (shouldRetry) {
				boolean shouldRetryStop=true;
				for (int i=0;i<=2;i++) {
					response = callRoundTripPricing(flightPriceModel);
					JSONObject jsonObj1 = new JSONObject(response);
					shouldRetryStop = jsonObj1.getBoolean("shouldRetry");
					if (!shouldRetryStop) {
						return response;
					}
				}
				if(shouldRetry) {
					exceptions.put(messageUtil.getBundle("common.error.code"), new Exception(messageUtil.getBundle("common.error..message")));
				}
			}
		} catch (Exception e) {
			logger.info("Error in fetchRoundTripPricing response -- END");
			e.getStackTrace();
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchRoundTripPricing -- END");
		}

		return response;
	}

	public String callRoundTripPricing(FlightPriceModel flightPriceModel) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("callRoundTripPricing -- START");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("emailId", messageUtil.getBundle("flight.email"));
		headers.add("password", messageUtil.getBundle("flight.password"));
		headers.add("apikey", messageUtil.getBundle("flight.key"));

		String tenantName = flightPriceModel.getTenantName();
		String searchId = flightPriceModel.getSearchId();
		String msid = flightPriceModel.getMsid();
		String requestMode = FlightConstant.REQUEST_MODE; 
		String flightId = flightPriceModel.getFlightId();
		String flightPrice = flightPriceModel.getFlightPrice();
		String supplierCode = flightPriceModel.getSupplierCode();

		String createUrl = messageUtil.getBundle("flight.search.server.url")+tenantName+"/price?"+"searchId="+searchId+"&msid="+msid+"&mode="+requestMode+
				"&bpc="+FlightConstant.BPC+"&isSR="+FlightConstant.ISSR+"&unique="+FlightConstant.UNIQUE+"&variation="+FlightConstant.VARIATION+
				"&flightIdCSV="+flightId+"&flightPrice="+flightPrice+"&sc="+supplierCode;

		ResponseEntity<String> responseEntity = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = UriComponentsBuilder.fromUriString(createUrl).build().encode().toUri();
			RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
			responseEntity = restTemplate.exchange(requestEntity, String.class);

		} catch (Exception e) {
			logger.info("Error in callRoundTripPricing response -- END");
			e.getStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("callRoundTripPricing -- END");
		}

		return responseEntity.getBody();
	}
	
	@Override
	public String fetchMultiCityPricing(FlightPriceModel flightPriceModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("fetchMultiCityPricing -- START");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();
		
		flightValidation.validateMultiCityPricing(flightPriceModel);
		String response = null;
		try {
			response = callMultiCityPricing(flightPriceModel);
			JSONObject jsonObj = new JSONObject(response);
			boolean shouldRetry = jsonObj.getBoolean("shouldRetry");
			if (shouldRetry) {
				boolean shouldRetryStop=true;
				for (int i=0;i<=2;i++) {
					response = callMultiCityPricing(flightPriceModel);
					JSONObject jsonObj1 = new JSONObject(response);
					shouldRetryStop = jsonObj1.getBoolean("shouldRetry");
					if (!shouldRetryStop) {
						return response;
					}
				}
				if(shouldRetry) {
					exceptions.put(messageUtil.getBundle("common.error.code"), new Exception(messageUtil.getBundle("common.error..message")));
				}
			}
		} catch (Exception e) {
			logger.info("Error in fetchMultiCityPricing response -- END");
			e.getStackTrace();
		}

		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchMultiCityPricing -- END");
		}

		return response;
	}

	public String callMultiCityPricing(FlightPriceModel flightPriceModel) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("callMultiCityPricing -- START");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("emailId", messageUtil.getBundle("flight.email"));
		headers.add("password", messageUtil.getBundle("flight.password"));
		headers.add("apikey", messageUtil.getBundle("flight.key"));

		String tenantName = flightPriceModel.getTenantName();
		String searchId = flightPriceModel.getSearchId();
		String msid = flightPriceModel.getMsid();
		String requestMode = FlightConstant.REQUEST_MODE; 
		String flightId = flightPriceModel.getFlightId();
		String flightPrice = flightPriceModel.getFlightPrice();
		String supplierCode = flightPriceModel.getSupplierCode();

		String createUrl = messageUtil.getBundle("flight.search.server.url")+tenantName+"/price?"+"searchId="+searchId+"&msid="+msid+"&mode="+requestMode+
				"&bpc="+FlightConstant.BPC+"&isSR="+FlightConstant.ISSR+"&unique="+FlightConstant.UNIQUE+"&variation="+FlightConstant.VARIATION+
				"&flightIdCSV="+flightId+"&flightPrice="+flightPrice+"&sc="+supplierCode;

		ResponseEntity<String> responseEntity = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = UriComponentsBuilder.fromUriString(createUrl).build().encode().toUri();
			RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
			responseEntity = restTemplate.exchange(requestEntity, String.class);

		} catch (Exception e) {
			logger.info("Error in callMultiCityPricing -- END");
			e.getStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("callMultiCityPricing -- END");
		}

		return responseEntity.getBody();
	}
}