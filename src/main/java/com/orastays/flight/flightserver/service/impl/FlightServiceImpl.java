package com.orastays.flight.flightserver.service.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;
import javax.ws.rs.core.UriBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.helper.FlightConstant;
import com.orastays.flight.flightserver.model.FlightSearchModel;
import com.orastays.flight.flightserver.model.MultiCityModel;
import com.orastays.flight.flightserver.model.ResultDataModel;
import com.orastays.flight.flightserver.model.YatraResponseModel;
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

		flightValidation.validateOneWayData(flightSearchModel);
		try {
			String searchResponse = oneWayFetch(flightSearchModel);
			System.out.println("RESPONSE::"+searchResponse);
			
			Gson gson = new Gson();
			String jsonString = gson.toJson(searchResponse);
			System.out.println("jsonString::"+jsonString);
			YatraResponseModel yatraResponseModel = gson.fromJson(jsonString, YatraResponseModel.class);
			System.out.println("yatraResponseModel::"+yatraResponseModel);
			
		    for (ResultDataModel resultDataModel:yatraResponseModel.getResultDatas()) {
		    	String searchId = resultDataModel.getFltSchedule().getScid();
		    	System.out.println("searchId is::"+searchId);
		    }
			
			//Parse the json
			//JSONObject jsonObject = new JSONObject(response);
			//JSONArray jsonArray = new JSONArray();
			/*if (jsonObject.has("eagerFetch")) {
				if (jsonObject.getString("eagerFetch") == "true") {
					JSONArray array = jsonObject.getJSONArray("resultData");
					String fltSchedule= array.getJSONObject(i).getString("fltSchedule");
					for(int i = 0 ; i < array.length() ; i++){
						for (int j=0; j<=array.getJSONObject(i).getString("fltSchedule").length();j++) {
							
							
						}
					}
				}
			}*/
			
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

		flightValidation.validateRoundTripData(flightSearchModel);
		try {
			//HttpEntity<String> response = roundTripFetch(flightSearchModel);
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

		flightValidation.validateMulticityData(flightSearchModel);
		try {
			//HttpEntity<String> response = multiCityFetch(flightSearchModel);
		} catch (Exception e) {
		}

		if (logger.isInfoEnabled()) {
			logger.info("fetchRoundTripFlights -- END");
		}
		
		return null;
	}
	
	public String oneWayFetch(FlightSearchModel flightSearchModel) {

		if (logger.isInfoEnabled()) {
			logger.info("oneWayFetch -- START");
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("emailId", FlightConstant.EMAILID);
		headers.add("password", FlightConstant.PASSWORD);
		headers.add("apikey", FlightConstant.APIKEY);

		Map<String, String> newModel = new HashMap<>();
		for(MultiCityModel multiCityModel:flightSearchModel.getMultiCityModels()) {
			newModel.put("origin", multiCityModel.getOrigin());
			newModel.put("originCountry", multiCityModel.getOriginCountry());
			newModel.put("destination", multiCityModel.getDestination());
			newModel.put("destinationCountry", multiCityModel.getDestinationCountry());
			newModel.put("flight_depart_date", multiCityModel.getFlightDepartDate());
		}

		String tenantName = flightSearchModel.getTenantName();
		String tripType = flightSearchModel.getTripType();
		String viewName = "normal";
		String noOfSegments = flightSearchModel.getNoOfSegments();
		String ADT = flightSearchModel.getNoOfAdults();
		String CHD = flightSearchModel.getNoOfChild();
		String INF = flightSearchModel.getNoOfInfants();
		String classType = flightSearchModel.getClassType();

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
			logger.info("Error in oneWayFetch response -- END");
			e.getStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("oneWayFetch -- END");
		}

		return responseEntity.getBody();
	}
	
	public HttpEntity<String> roundTripFetch(FlightSearchModel flightSearchModel) {

		if (logger.isInfoEnabled()) {
			logger.info("roundTripFetch -- START");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("emailId", FlightConstant.EMAILID);
		headers.add("password", FlightConstant.PASSWORD);
		headers.add("apiKey", FlightConstant.APIKEY);

		Map<String, String> newModel = new HashMap<>();
		for(MultiCityModel multiCityModel:flightSearchModel.getMultiCityModels()) {
			newModel.put("origin", multiCityModel.getOrigin());
			newModel.put("originCountry", multiCityModel.getOriginCountry());
			newModel.put("destination", multiCityModel.getDestination());
			newModel.put("destinationCountry", multiCityModel.getDestinationCountry());
			newModel.put("flight_depart_date", multiCityModel.getFlightDepartDate());
		}
		
		String tenantName = flightSearchModel.getTenantName();
		String tripType = flightSearchModel.getTripType();
		String viewName = "normal";
		String noOfSegments = flightSearchModel.getNoOfSegments();
		String arrivalDate = flightSearchModel.getArrivalDate();
		String ADT = flightSearchModel.getNoOfAdults();
		String CHD = flightSearchModel.getNoOfChild();
		String INF = flightSearchModel.getNoOfInfants();
		String classType = flightSearchModel.getClassType();
		
		UriBuilder builder = UriBuilder
				.fromPath(FlightConstant.BASE_URL+"/"+tenantName)
				.queryParam("type", tripType)
		        .queryParam("viewName", viewName)
		        .queryParam("noOfSegments", noOfSegments)
		        .queryParam("origin", newModel.containsKey("origin"))
		        .queryParam("originCountry", newModel.containsKey("originCountry"))
		        .queryParam("destination", newModel.containsKey("destination"))
		        .queryParam("destinationCountry", newModel.containsKey("destinationCountry"))
		        .queryParam("flight_depart_date", newModel.containsKey("flight_depart_date"))
		        .queryParam("arrivalDate", arrivalDate)
		        .queryParam("ADT", ADT)
		        .queryParam("CHD", CHD)
		        .queryParam("INF", INF)
		        .queryParam("class", classType);

		URI uri = builder.build();

		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
		HttpEntity<String> response = restTemplate.exchange(
				uri, 
		        HttpMethod.GET, 
		        entity, 
		        String.class);
		
		
		if (logger.isInfoEnabled()) {
			logger.info("roundTripFetch -- END");
		}
		
		return null;
	}
	
	public HttpEntity<String> multiCityFetch(FlightSearchModel flightSearchModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("multiCityFetch -- START");
		}
		
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

		UriBuilder builder = UriBuilder
				.fromPath(FlightConstant.BASE_URL+"/"+tenantName)
				.queryParam("viewName", viewName)
				.queryParam("flexi", flexi)
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

		HttpEntity<?> entity = new HttpEntity<>(headers);
		//URI uri = UriComponentsBuilder.fromUriString(BASE_URL+tenantName).build().encode().toUri();
		/*RequestEntity<String> requestEntity = new RequestEntity<>(headers,
				HttpMethod.GET, uri);
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,String.class);
		String responseData = responseEntity.getBody();*/
		
		RequestEntity<String> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
		HttpEntity<String> response = restTemplate.exchange(
				uri, 
		        HttpMethod.GET, 
		        entity, 
		        String.class);
		System.out.println(response);
		
		if (logger.isInfoEnabled()) {
			logger.info("multiCityFetch -- END");
		}
		
		return response;
	}
}