package com.orastays.flight.flightserver.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orastays.flight.flightserver.helper.FlightConstant;
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.FlightSearchModel;
import com.orastays.flight.flightserver.model.ResponseModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Api(value = "Flight", tags = "Flight")
public class FlightController extends BaseController {
	
	private static final Logger logger = LogManager.getLogger(FlightController.class);
	
	@PostMapping(value = "/fetch-one-way-flights", produces = "application/json")
	@ApiOperation(value = "Fetch OW flights", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code=1700, message="Please select an origin!!"),
			@ApiResponse(code=1701, message="Please select a destination!!"),
			@ApiResponse(code=1702, message="Please select departure date!!"),
			@ApiResponse(code=1704, message="Please select class!!"),
			@ApiResponse(code=1705, message="Please select number of adults!!"),
			@ApiResponse(code=1706, message="Please provide segments!!"),
			@ApiResponse(code=1707, message="Please select tenant!!"),
			@ApiResponse(code=1708, message="Please select class!!"),
			@ApiResponse(code=1709, message="Please provide multicity details")})
	public ResponseEntity<ResponseModel> fetchOneWayFlights(@RequestBody FlightSearchModel flightSearchModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchOneWayFlights -- START");
		}
		
		ResponseModel responseModel = new ResponseModel();
		Util.printLog(flightSearchModel, FlightConstant.INCOMING, "Fetch OW flights", request);
		
		try {
			List<FlightSearchModel> flightSearchModels = flightService.fetchOneWayFlights(flightSearchModel);
			responseModel.setResponseBody(flightSearchModels);
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_MESSAGE));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch OW flights -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, FlightConstant.OUTGOING, "Fetch OW flights", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchOneWayFlights -- END");
		}

		if (responseModel.getResponseCode().equals(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}	
	
	@PostMapping(value = "/fetch-round-trip-flights", produces = "application/json")
	@ApiOperation(value = "Fetch RT flights", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code=1700, message="Please select an origin!!"),
			@ApiResponse(code=1701, message="Please select a destination!!"),
			@ApiResponse(code=1702, message="Please select departure date!!"),
			@ApiResponse(code=1703, message="Please select arrival date!!"),
			@ApiResponse(code=1704, message="Please select class!!"),
			@ApiResponse(code=1705, message="Please select number of adults!!"),
			@ApiResponse(code=1706, message="Please provide segments!!"),
			@ApiResponse(code=1707, message="Please select tenant!!"),
			@ApiResponse(code=1708, message="Please select class!!"),
			@ApiResponse(code=1709, message="Please provide multicity details")})
	public ResponseEntity<ResponseModel> fetchRoundTripFlights(@RequestBody FlightSearchModel flightSearchModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchRoundTripFlights -- START");
		}
		
		ResponseModel responseModel = new ResponseModel();
		Util.printLog(flightSearchModel, FlightConstant.INCOMING, "Fetch RT flights", request);
		
		try {
			List<FlightSearchModel> genericFLightSearchModels = flightService.fetchRoundTripFlights(flightSearchModel);
			responseModel.setResponseBody(genericFLightSearchModels);
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_MESSAGE));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch RT flights -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, FlightConstant.OUTGOING, "Fetch RT flights", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchRoundTripFlights -- END");
		}

		if (responseModel.getResponseCode().equals(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/fetch-multi-city-flights", produces = "application/json")
	@ApiOperation(value = "Fetch MCT flights", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code=1700, message="Please select an origin!!"),
			@ApiResponse(code=1701, message="Please select a destination!!"),
			@ApiResponse(code=1702, message="Please select departure date!!"),
			@ApiResponse(code=1704, message="Please select class!!"),
			@ApiResponse(code=1705, message="Please select number of adults!!"),
			@ApiResponse(code=1706, message="Please provide segments!!"),
			@ApiResponse(code=1707, message="Please select tenant!!"),
			@ApiResponse(code=1708, message="Please select class!!"),
			@ApiResponse(code=1709, message="Please provide multicity details")})
	public ResponseEntity<ResponseModel> fetchMultiCityFlights(@RequestBody FlightSearchModel flightSearchModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchMultiCityFlights -- START");
		}
		
		ResponseModel responseModel = new ResponseModel();
		Util.printLog(flightSearchModel, FlightConstant.INCOMING, "Fetch MCT flights", request);
		
		try {
			List<FlightSearchModel> genericFLightSearchModels = flightService.fetchMultiCityFlights(flightSearchModel);
			responseModel.setResponseBody(genericFLightSearchModels);
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_MESSAGE));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch MCT flights -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, FlightConstant.OUTGOING, "Fetch MCT flights", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchMultiCityFlights -- END");
		}

		if (responseModel.getResponseCode().equals(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}