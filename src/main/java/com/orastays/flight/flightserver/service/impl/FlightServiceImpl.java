package com.orastays.flight.flightserver.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.helper.FlightConstant;
import com.orastays.flight.flightserver.model.FlightSearchModel;
import com.orastays.flight.flightserver.model.MultiCityModel;
import com.orastays.flight.flightserver.service.FlightService;

@Service
@Transactional
public class FlightServiceImpl extends BaseServiceImpl implements FlightService {

	private static final Logger logger = LogManager.getLogger(FlightServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<FlightSearchModel> fetchOneWayFlights(FlightSearchModel flightSearchModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("fetchOneWayFlights -- START");
		}

		flightValidation.validateSearchData(flightSearchModel);
		try {
			HttpEntity<String> response = oneWayFetch(flightSearchModel);
			
		} catch (Exception e) {
		}

		if (logger.isInfoEnabled()) {
			logger.info("fetchOneWayFlights -- END");
		}
		
		return null;
	}

	@Override
	public List<FlightSearchModel> fetchRoundTripFlights(FlightSearchModel flightSearchModel) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchRoundTripFlights -- START");
		}

		flightValidation.validateSearchData(flightSearchModel);
		try {
			HttpEntity<String> response = roundTripFetch(flightSearchModel);
		} catch (Exception e) {
		}

		if (logger.isInfoEnabled()) {
			logger.info("fetchRoundTripFlights -- END");
		}
		
		return null;
	}
	
	@Override
	public List<FlightSearchModel> fetchMultiCityFlights(FlightSearchModel flightSearchModel) throws FormExceptions {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchRoundTripFlights -- START");
		}

		//flightValidation.validateSearchData(flightSearchModel);
		try {
			HttpEntity<String> response = multiCityFetch(flightSearchModel);
		} catch (Exception e) {
		}

		if (logger.isInfoEnabled()) {
			logger.info("fetchRoundTripFlights -- END");
		}
		
		return null;
	}
	
	public HttpEntity<String> oneWayFetch(FlightSearchModel flightSearchModel) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("emailId", FlightConstant.EMAILID);
		headers.add("password", FlightConstant.PASSWORD);
		headers.add("apiKey", FlightConstant.APIKEY);

		String tenantName = flightSearchModel.getTenantName();
		String tripType = flightSearchModel.getTripType();
		String viewName = "normal";
		//String flexi = "0";
		String noOfSegments = flightSearchModel.getNoOfSegments();
		String origin = flightSearchModel.getOrigin();
		String originCountry = flightSearchModel.getOriginCountry();
		String destination = flightSearchModel.getDestination();
		String destinationCountry = flightSearchModel.getDestinationCountry();
		String flight_depart_date = flightSearchModel.getFlightDepartDate();
		String ADT = flightSearchModel.getNoOfAdults();
		String CHD = flightSearchModel.getNoOfChild();
		String INF = flightSearchModel.getNoOfInfants();
		String classType = flightSearchModel.getClassType();
		//String hb = "0";
		//String source= "fresco-home";
		//String bookingtype = "official";
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(FlightConstant.BASE_URL+"/"+tenantName)
		        .queryParam("type", tripType)
		        .queryParam("viewName", viewName)
		        .queryParam("noOfSegments", noOfSegments)
		        .queryParam("origin", origin)
		        .queryParam("originCountry", originCountry)
		        .queryParam("destination", destination)
		        .queryParam("destinationCountry", destinationCountry)
		        .queryParam("flight_depart_date", flight_depart_date)
		        .queryParam("ADT", ADT)
		        .queryParam("CHD", CHD)
		        .queryParam("INF", INF)
		        .queryParam("class", classType);
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		//URI uri = UriComponentsBuilder.fromUriString(BASE_URL+tenantName).build().encode().toUri();
		/*RequestEntity<String> requestEntity = new RequestEntity<>(headers,
				HttpMethod.GET, uri);
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,String.class);
		String responseData = responseEntity.getBody();*/
		
		HttpEntity<String> response = restTemplate.exchange(
		        builder.toUriString(), 
		        HttpMethod.GET, 
		        entity, 
		        String.class);
		
		return response;
	}
	
	public HttpEntity<String> roundTripFetch(FlightSearchModel flightSearchModel) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("emailId", FlightConstant.EMAILID);
		headers.add("password", FlightConstant.PASSWORD);
		headers.add("apiKey", FlightConstant.APIKEY);

		String tenantName = flightSearchModel.getTenantName();
		String tripType = flightSearchModel.getTripType();
		String viewName = "normal";
		//String flexi = "0";
		String noOfSegments = flightSearchModel.getNoOfSegments();
		String origin = flightSearchModel.getOrigin();
		String originCountry = flightSearchModel.getOriginCountry();
		String destination = flightSearchModel.getDestination();
		String destinationCountry = flightSearchModel.getDestinationCountry();
		String flight_depart_date = flightSearchModel.getFlightDepartDate();
		String arrivalDate = flightSearchModel.getArrivalDate();
		String ADT = flightSearchModel.getNoOfAdults();
		String CHD = flightSearchModel.getNoOfChild();
		String INF = flightSearchModel.getNoOfInfants();
		String classType = flightSearchModel.getClassType();
		String hb = "0";
		String source= "fresco-home";
		String bookingtype = "official";
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(FlightConstant.BASE_URL+"/"+tenantName)
		        .queryParam("type", tripType)
		        .queryParam("viewName", viewName)
		        .queryParam("noOfSegments", noOfSegments)
		        .queryParam("origin", origin)
		        .queryParam("originCountry", originCountry)
		        .queryParam("destination", destination)
		        .queryParam("destinationCountry", destinationCountry)
		        .queryParam("flight_depart_date", flight_depart_date)
		        .queryParam("arrivalDate", arrivalDate)
		        .queryParam("ADT", ADT)
		        .queryParam("CHD", CHD)
		        .queryParam("INF", INF)
		        .queryParam("class", classType)
		        .queryParam("hb", hb)
		        .queryParam("source", source)
		        .queryParam("booking-type", bookingtype);
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		//URI uri = UriComponentsBuilder.fromUriString(BASE_URL+tenantName).build().encode().toUri();
		/*RequestEntity<String> requestEntity = new RequestEntity<>(headers,
				HttpMethod.GET, uri);
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,String.class);
		String responseData = responseEntity.getBody();*/
		
		HttpEntity<String> response = restTemplate.exchange(
		        builder.toUriString(), 
		        HttpMethod.GET, 
		        entity, 
		        String.class);
		
		return response;
	}
	
	public HttpEntity<String> multiCityFetch(FlightSearchModel flightSearchModel) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("emailId", FlightConstant.EMAILID);
		headers.add("password", FlightConstant.PASSWORD);
		headers.add("apiKey", FlightConstant.APIKEY);

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

		String tenantName = flightSearchModel.getTenantName();
		String viewName = "normal";
		String flexi = "0";
		String tripType = flightSearchModel.getTripType();
		String ADT = flightSearchModel.getNoOfAdults();
		String CHD = flightSearchModel.getNoOfChild();
		String INF = flightSearchModel.getNoOfInfants();
		String classType = flightSearchModel.getClassType();
		String noOfSegments = flightSearchModel.getNoOfSegments();
		
		newModel.keySet().stream()
		.forEach(System.out::println);
		
		for ( String key : newModel.keySet() ) {
		    System.out.println( key );
		}
		
		newModel.forEach((key, value) -> {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(FlightConstant.BASE_URL+"/"+tenantName)
			        .queryParam("viewName", viewName)
			        .queryParam("flexi", flexi)
			        .queryParam("type", tripType)
			        .queryParam("ADT", ADT)
			        .queryParam("CHD", CHD)
			        .queryParam("INF", INF)
			        .queryParam("class", classType)
			        .queryParam("noOfSegments", noOfSegments)
			        .queryParam(key, newModel.get("flight_depart_date_0"))
			        /*.queryParam("origin_0", newModel.get("origin_0"))
			        .queryParam("originCountry_0", newModel.get("originCountry_0"))
			        .queryParam("destination_0", newModel.get("destination_0"))
			        .queryParam("destinationCountry_0", newModel.get("destinationCountry_0"))
			        .queryParam("flight_depart_date_1", newModel.get(""))*/
			        /*.queryParam("origin_1", multiCityMo)
			        .queryParam("originCountry_1", multiCityModel.getOriginCountry1())
			        .queryParam("destination_1", multiCityModel.getDestination1())
			        .queryParam("destinationCountry_1", multiCityModel.getDestinationCountry1())*/;
			
			System.out.println("BUILDER::"+builder.buildAndExpand().toUri());
		});
		
		/*UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(FlightConstant.BASE_URL+"/"+tenantName)
		        .queryParam("viewName", viewName)
		        .queryParam("flexi", flexi)
		        .queryParam("type", tripType)
		        .queryParam("ADT", ADT)
		        .queryParam("CHD", CHD)
		        .queryParam("INF", INF)
		        .queryParam("class", classType)
		        .queryParam("noOfSegments", noOfSegments)
		        .queryParam("flight_depart_date_0", multiCityModel.getFlightDepartDate0())
		        .queryParam("origin_0", multiCityModel.getOrigin0())
		        .queryParam("originCountry_0", multiCityModel.getOriginCountry0())
		        .queryParam("destination_0", multiCityModel.getDestination0())
		        .queryParam("destinationCountry_0", multiCityModel.getDestinationCountry0())
		        .queryParam("flight_depart_date_1", multiCityModel.getFlightDepartDate1())
		        .queryParam("origin_1", multiCityModel.getOrigin1())
		        .queryParam("originCountry_1", multiCityModel.getOriginCountry1())
		        .queryParam("destination_1", multiCityModel.getDestination1())
		        .queryParam("destinationCountry_1", multiCityModel.getDestinationCountry1());*/
		
		HttpEntity<?> entity = new HttpEntity<>(headers);
		//URI uri = UriComponentsBuilder.fromUriString(BASE_URL+tenantName).build().encode().toUri();
		/*RequestEntity<String> requestEntity = new RequestEntity<>(headers,
				HttpMethod.GET, uri);
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,String.class);
		String responseData = responseEntity.getBody();*/
		
		/*HttpEntity<String> response = restTemplate.exchange(
		        builder.toUriString(), 
		        HttpMethod.GET, 
		        entity, 
		        String.class);*/
		
		return null;
	}
}