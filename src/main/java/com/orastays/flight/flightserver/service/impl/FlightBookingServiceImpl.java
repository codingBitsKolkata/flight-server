package com.orastays.flight.flightserver.service.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.helper.FlightConstant;
import com.orastays.flight.flightserver.helper.MessageUtil;
import com.orastays.flight.flightserver.model.FlightBookingModel;
import com.orastays.flight.flightserver.model.MultiCityModel;
import com.orastays.flight.flightserver.service.FlightBookingService;

@Service
@Transactional
public class FlightBookingServiceImpl extends BaseServiceImpl implements FlightBookingService {

	@Autowired
	protected MessageUtil messageUtil;
	
	private static final Logger logger = LogManager.getLogger(FlightBookingServiceImpl.class);

	@Override
	public String fetchBookingList(FlightBookingModel flightBookingModel) throws FormExceptions, JSONException {


		if (logger.isInfoEnabled()) {
			logger.info("fetchBookingList -- START");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();

		flightBookingValidation.validateBookingList(flightBookingModel);
		String searchResponse = null;
		try {
			searchResponse = oneWayFetch(flightBookingModel);
			JSONObject jsonObj = new JSONObject(searchResponse);
			boolean eagerFetch = jsonObj.getBoolean("eagerFetch");
			if (!eagerFetch) {
				boolean eagerFetchStop=false;
				for (int i=0;i<=2;i++) {
					searchResponse = oneWayFetch(flightBookingModel);
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
			logger.info("fetchBookingList -- END");
		}
		return searchResponse;
	}

	@Override
	public void saveReviewDetails(FlightBookingModel flightBookingModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("saveReviewDetails -- START");
		}
		
		Map<String, Exception> exceptions = new LinkedHashMap<>();

		flightBookingValidation.validateReviewDetails(flightBookingModel);

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("emailId", messageUtil.getBundle("flight.email"));
			headers.add("password", messageUtil.getBundle("flight.password"));
			headers.add("apikey", messageUtil.getBundle("flight.key"));
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (exceptions.size() > 0)
			throw new FormExceptions(exceptions);
		
		if (logger.isInfoEnabled()) {
			logger.info("saveReviewDetails -- END");
		}
	}
	
	private String oneWayFetch(FlightBookingModel flightBookingModel) {

		if (logger.isInfoEnabled()) {
			logger.info("oneWayFetch -- START");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("emailId", messageUtil.getBundle("flight.email"));
		headers.add("password", messageUtil.getBundle("flight.password"));
		headers.add("apikey", messageUtil.getBundle("flight.key"));
		
		Map<String, String> newModel = new HashMap<>();
		for(MultiCityModel multiCityModel:flightBookingModel.getMultiCityModels()) {
			newModel.put("origin", multiCityModel.getOrigin());
			newModel.put("originCountry", multiCityModel.getOriginCountry());
			newModel.put("destination", multiCityModel.getDestination());
			newModel.put("destinationCountry", multiCityModel.getDestinationCountry());
			newModel.put("flight_depart_date", multiCityModel.getFlightDepartDate());
		}

		String tenantName = flightBookingModel.getTenantName();
		String tripType = flightBookingModel.getTripType();
		String viewName = FlightConstant.VIEW_NAME;
		String noOfSegments = flightBookingModel.getNoOfSegments();
		String ADT = flightBookingModel.getNoOfAdults();
		String CHD = flightBookingModel.getNoOfChild();
		String INF = flightBookingModel.getNoOfInfants();
		String classType = flightBookingModel.getClassType();

		String createUrl = FlightConstant.BASE_URL+"/"+tenantName+"/search?"+"type="+tripType+"&viewName="+viewName+"&noOfSegments="+noOfSegments+
				"&origin="+newModel.get("origin")+"&originCountry="+newModel.get("originCountry")+"&destination="+newModel.get("destination")+
				"&destinationCountry="+newModel.get("destinationCountry")+"&flight_depart_date="+newModel.get("flight_depart_date")+"&ADT="+ADT+
				"&CHD="+CHD+"&INF="+INF+"&class="+classType;

		ResponseEntity<String> responseEntity = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = UriComponentsBuilder.fromUriString(createUrl).build().encode().toUri();
			RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
			responseEntity = restTemplate.exchange(requestEntity, String.class);

		} catch (RestClientResponseException e) {
			logger.info("Error in oneWayFetch response");
			e.getStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("oneWayFetch -- END");
		}

		return responseEntity.getBody();
	}
}