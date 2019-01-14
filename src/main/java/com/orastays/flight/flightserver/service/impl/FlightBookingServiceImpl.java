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
import com.orastays.flight.flightserver.helper.FlightConstant;
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

			flightBookingModel.reviewJsonModel.globalParamsModel.setChannel(FlightConstant.CHANNEL);
			flightBookingModel.reviewJsonModel.globalParamsModel.setProduct(FlightConstant.PRODUCT);
			flightBookingModel.reviewJsonModel.globalParamsModel.setIsPartial(FlightConstant.IS_PARTIAL);
			flightBookingModel.reviewJsonModel.globalParamsModel.setEbsAccountId(FlightConstant.EBS_ACCOUNTID);
			flightBookingModel.reviewJsonModel.globalParamsModel.setMoProfileType(FlightConstant.MO_PROFILE_TYPE);
			String orginCountryCode = searchParameterDAO.fetchCountryCode(flightBookingModel.getReviewJsonModel().getGlobalParamsModel().getOrg());
			String destCountryCode = searchParameterDAO.fetchCountryCode(flightBookingModel.getReviewJsonModel().getGlobalParamsModel().getDest());
			if(orginCountryCode.equals(destCountryCode)) {
				flightBookingModel.reviewJsonModel.globalParamsModel.setChildTenant(FlightConstant.DOM_TENANT_NAME);
			} else {
				flightBookingModel.reviewJsonModel.globalParamsModel.setChildTenant(FlightConstant.INT_TENANT_NAME);
			}
			flightBookingModel.reviewJsonModel.globalParamsModel.setVariation(FlightConstant.VARIATION);
			//flightBookingModel.reviewJsonModel.globalParamsModel.setChildTenant(FlightConstant.DOM_TENANT_NAME);
			
			flightBookingModel.getReviewJsonModel().getUserParamsModel().getAdditionalContactModel().setEmail(FlightConstant.ADTL_EMAIL);
			flightBookingModel.getReviewJsonModel().getUserParamsModel().getAdditionalContactModel().setMobile(FlightConstant.ADTL_MOBILE);
			flightBookingModel.getReviewJsonModel().getUserParamsModel().getAdditionalContactModel().setMobileISD(FlightConstant.ADTL_MOBILE_ISD);
			flightBookingModel.getReviewJsonModel().getUserParamsModel().setEmailId(FlightConstant.EMAIL);
			flightBookingModel.getReviewJsonModel().getUserParamsModel().setMobileNo(FlightConstant.MOBILE);
			flightBookingModel.getReviewJsonModel().getUserParamsModel().setUserId(FlightConstant.USER_ID);
			flightBookingModel.getReviewJsonModel().getUserParamsModel().setFirstName(FlightConstant.FIRST_NAME);
			flightBookingModel.getReviewJsonModel().getUserParamsModel().setLastName(FlightConstant.LAST_NAME);
			flightBookingModel.getReviewJsonModel().getUserParamsModel().setMobileNoISD(FlightConstant.ADTL_MOBILE_ISD);
			
			String url = messageUtil.getBundle("flight.booking.server.url");
			request = new HttpEntity<FlightBookingModel>(flightBookingModel, headers);
			ResponseModel responseModel = restTemplate.postForObject(url, request, ResponseModel.class);
			System.out.println("responseModel::"+responseModel);
			Gson gson = new Gson();
			String jsonString = gson.toJson(responseModel.getResponseBody());
			//To store the whole list because list contains object as well as array
			flightBookingModels = gson.fromJson(jsonString,new TypeToken<List<FlightBookingModel>>(){
			private static final long serialVersionUID = 6432872879861274827L;}.getType());
			System.out.println("flightBookingModels::"+flightBookingModels);
			
			//STORE THE RESPONSE DATA IN DB

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