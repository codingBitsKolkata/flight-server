package com.orastays.flight.flightserver.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.helper.MessageUtil;
import com.orastays.flight.flightserver.model.BaggageModel;
import com.orastays.flight.flightserver.model.ResponseModel;
import com.orastays.flight.flightserver.service.BaggageService;

@Service
@Transactional
public class BaggageServiceImpl extends BaseServiceImpl implements BaggageService {

	@Autowired
	protected MessageUtil messageUtil;

	@Autowired
	protected RestTemplate restTemplate;

	private static final Logger logger = LogManager.getLogger(BaggageServiceImpl.class);

	@Override
	public List<BaggageModel> baggageInfo(BaggageModel baggageModel) throws FormExceptions {

		if (logger.isInfoEnabled()) {
			logger.info("baggageInfo -- START");
		}

		baggageValidation.validateBaggageInfo(baggageModel);
		List<BaggageModel> baggageModels = null;
		HttpEntity<BaggageModel> request = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("emailId", messageUtil.getBundle("flight.email"));
			headers.add("password", messageUtil.getBundle("flight.password"));
			headers.add("apikey", messageUtil.getBundle("flight.key"));


			String url = messageUtil.getBundle("flight.booking.server.url");
			request = new HttpEntity<BaggageModel>(baggageModel, headers);
			ResponseModel responseModel = restTemplate.postForObject(url, request, ResponseModel.class);
			//ResponseModel responseModel = restTemplate.postForObject(url, flightBookingModel, ResponseModel.class);
			Gson gson = new Gson();
			String jsonString = gson.toJson(responseModel.getResponseBody());
			//To store the whole list because list contains object as well as array
			//baggageModels = gson.fromJson(jsonString,new TypeToken<List<BaggageModel>>(){}.getType());
			baggageModels = gson.fromJson(jsonString,new TypeToken<List<BaggageModel>>(){
				private static final long serialVersionUID = 2091852737977171051L;}.getType());

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (logger.isInfoEnabled()) {
			logger.info("baggageInfo -- END");
		}
		
		return baggageModels;
	}
}