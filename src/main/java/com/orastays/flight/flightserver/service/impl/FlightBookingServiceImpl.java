package com.orastays.flight.flightserver.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.helper.MessageUtil;
import com.orastays.flight.flightserver.model.FlightBookingModel;
import com.orastays.flight.flightserver.model.ResponseModel;
import com.orastays.flight.flightserver.service.FlightBookingService;

@Service
@Transactional
public class FlightBookingServiceImpl extends BaseServiceImpl implements FlightBookingService {

	@Autowired
	protected MessageUtil messageUtil;

	@Autowired
	protected RestTemplate restTemplate;

	private static final Logger logger = LogManager.getLogger(FlightBookingServiceImpl.class);

	@Override
	public List<FlightBookingModel> bookFlights(FlightBookingModel flightBookingModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("bookFlights -- START");
		}

		flightBookingValidation.validateBookingDetails(flightBookingModel);
		List<FlightBookingModel> flightBookingModels = null;
		HttpEntity<FlightBookingModel> request = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("emailId", messageUtil.getBundle("flight.email"));
			headers.add("password", messageUtil.getBundle("flight.password"));
			headers.add("apikey", messageUtil.getBundle("flight.key"));


			String url = messageUtil.getBundle("flight.booking.server.url");
			request = new HttpEntity<FlightBookingModel>(flightBookingModel, headers);
			ResponseModel responseModel = restTemplate.postForObject(url, request, ResponseModel.class);
			//ResponseModel responseModel = restTemplate.postForObject(url, flightBookingModel, ResponseModel.class);
			Gson gson = new Gson();
			String jsonString = gson.toJson(responseModel.getResponseBody());
			//To store the whole list because list contains object as well as array
			flightBookingModels = gson.fromJson(jsonString,new TypeToken<List<FlightBookingModel>>(){
			private static final long serialVersionUID = 6432872879861274827L;}.getType());

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("bookFlights -- END");
		}
		
		return flightBookingModels;
	}
	
	@Override
	public String fetchBookingList(FlightBookingModel flightBookingModel) throws FormExceptions {


		if (logger.isInfoEnabled()) {
			logger.info("fetchBookingList -- START");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		flightBookingValidation.validateBookingList(flightBookingModel);
		String searchResponse = null;
		try {
			//searchResponse = oneWayFetch(flightBookingModel);
			JSONObject jsonObj = new JSONObject(searchResponse);
			boolean eagerFetch = jsonObj.getBoolean("eagerFetch");
			if (!eagerFetch) {
				boolean eagerFetchStop=false;
				for (int i=0;i<=2;i++) {
					//searchResponse = oneWayFetch(flightBookingModel);
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
}