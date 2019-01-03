package com.orastays.flight.flightserver.controller;

import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.helper.FlightConstant;
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.FlightBookingModel;
import com.orastays.flight.flightserver.model.FlightPriceModel;
import com.orastays.flight.flightserver.model.FlightSearchModel;
import com.orastays.flight.flightserver.model.ResponseModel;
import com.orastays.flight.flightserver.model.SearchParameterModel;

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

	@GetMapping(value = "/fetch-search-details", produces = "application/json")
	@ApiOperation(value = "Fetch Search Details", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!") })
	public ResponseEntity<ResponseModel> fetchSearchDetails() {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchSearchDetails -- START");
		}
		
		ResponseModel responseModel = new ResponseModel();
		Util.printLog(null, FlightConstant.INCOMING, "Fetch Search Details", request);
		
		try {
			List<SearchParameterModel> searchParameterModels = flightService.fetchSearchDetails();
			responseModel.setResponseBody(searchParameterModels);
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_MESSAGE));
		}  catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch Search Details -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, FlightConstant.OUTGOING, "Fetch Search Details", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchSearchDetails -- END");
		}

		if (responseModel.getResponseCode().equals(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}	
	
	@PostMapping(value = "/fetch-one-way-flights", produces = "application/json")
	@ApiOperation(value = "Fetch OneWay flights", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code=1700, message="Please select tenant!!"),
			@ApiResponse(code=1701, message="Please select trip type!!"),
			@ApiResponse(code=1702, message="Please provide segments!!"),
			@ApiResponse(code=1703, message="Please select number of adults!!"),
			@ApiResponse(code=1704, message="Please select class!!"),
			@ApiResponse(code=1706, message="Please select an origin!!"),
			@ApiResponse(code=1707, message="Please select a destination!!"),
			@ApiResponse(code=1708, message="Please select a origin country!!"),
			@ApiResponse(code=1709, message="Please select destination country!!"),
			@ApiResponse(code=1710, message="Please select departure date!!")})
	public ResponseEntity<ResponseModel> fetchOneWayFlights(@RequestBody FlightSearchModel flightSearchModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchOneWayFlights -- START");
		}
		
		ResponseModel responseModel = new ResponseModel();
		Util.printLog(flightSearchModel, FlightConstant.INCOMING, "Fetch OneWay flights", request);
		
		try {
			String response = flightService.fetchOneWayFlights(flightSearchModel);
			responseModel.setResponseBody(response);
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Fetch OneWay flights -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch OneWay flights -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, FlightConstant.OUTGOING, "Fetch OneWay flights", request);

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
	@ApiOperation(value = "Fetch RoundTrip flights", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code=1700, message="Please select tenant!!"),
			@ApiResponse(code=1701, message="Please select trip type!!"),
			@ApiResponse(code=1702, message="Please provide segments!!"),
			@ApiResponse(code=1703, message="Please select number of adults!!"),
			@ApiResponse(code=1704, message="Please select class!!"),
			@ApiResponse(code=1705, message="Please select arrival date!!"),
			@ApiResponse(code=1706, message="Please select an origin!!"),
			@ApiResponse(code=1707, message="Please select a destination!!"),
			@ApiResponse(code=1708, message="Please select a origin country!!"),
			@ApiResponse(code=1709, message="Please select destination country!!"),
			@ApiResponse(code=1710, message="Please select departure date!!")})
	public ResponseEntity<ResponseModel> fetchRoundTripFlights(@RequestBody FlightSearchModel flightSearchModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchRoundTripFlights -- START");
		}
		
		ResponseModel responseModel = new ResponseModel();
		Util.printLog(flightSearchModel, FlightConstant.INCOMING, "Fetch RoundTrip flights", request);
		
		try {
			String response = flightService.fetchRoundTripFlights(flightSearchModel);
			responseModel.setResponseBody(response);
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Fetch RoundTrip flights -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch RoundTrip flights -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, FlightConstant.OUTGOING, "Fetch RoundTrip flights", request);

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
	@ApiOperation(value = "Fetch MultiCity flights", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code=1700, message="Please select tenant!!"),
			@ApiResponse(code=1701, message="Please select trip type!!"),
			@ApiResponse(code=1702, message="Please provide segments!!"),
			@ApiResponse(code=1703, message="Please select number of adults!!"),
			@ApiResponse(code=1704, message="Please select class!!"),
			@ApiResponse(code=1706, message="Please select an origin!!"),
			@ApiResponse(code=1707, message="Please select a destination!!"),
			@ApiResponse(code=1708, message="Please select a origin country!!"),
			@ApiResponse(code=1709, message="Please select destination country!!"),
			@ApiResponse(code=1710, message="Please select departure date!!")})
	public ResponseEntity<ResponseModel> fetchMultiCityFlights(@RequestBody FlightSearchModel flightSearchModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchMultiCityFlights -- START");
		}
		
		ResponseModel responseModel = new ResponseModel();
		Util.printLog(flightSearchModel, FlightConstant.INCOMING, "Fetch MultiCity flights", request);
		
		try {
			String response = flightService.fetchMultiCityFlights(flightSearchModel);
			responseModel.setResponseBody(response);
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Fetch MultiCity flights -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch MultiCity flights -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, FlightConstant.OUTGOING, "Fetch Multi City flights", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchMultiCityFlights -- END");
		}

		if (responseModel.getResponseCode().equals(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/fetch-one-way-pricing", produces = "application/json")
	@ApiOperation(value = "Fetch OneWay Pricing", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code=1700, message="Please select tenant!!"),
			@ApiResponse(code=1706, message="Please select an origin!!"),
			@ApiResponse(code=1707, message="Please select a destination!!"),
			@ApiResponse(code=1712, message="Please provide search ID!!"),
			@ApiResponse(code=1713, message="Please provide msid!!"),
			@ApiResponse(code=1714, message="Please provide request mode!!"),
			@ApiResponse(code=1715, message="Please provide BPC!!"),
			@ApiResponse(code=1716, message="Please provide SR!!"),
			@ApiResponse(code=1717, message="Please provide UNIQUE!!"),
			@ApiResponse(code=1718, message="Please provide variation!!"),
			@ApiResponse(code=1719, message="Please provide supplier code!!"),
			@ApiResponse(code=1720, message="Please provide flight number!!"),
			@ApiResponse(code=1721, message="Please provide date of travel!!") })
	public ResponseEntity<ResponseModel> fetchOneWayPricing(@RequestBody FlightPriceModel flightPriceModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchOneWayPricing -- START");
		}
		
		ResponseModel responseModel = new ResponseModel();
		Util.printLog(flightPriceModel, FlightConstant.INCOMING, "Fetch OneWay Pricing", request);
		
		try {
			String response = flightService.fetchOneWayPricing(flightPriceModel);
			responseModel.setResponseBody(response);
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Fetch OneWay Pricing -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch OneWay Pricing -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, FlightConstant.OUTGOING, "Fetch OneWay Pricing", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchOneWayPricing -- END");
		}

		if (responseModel.getResponseCode().equals(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/fetch-round-trip-pricing", produces = "application/json")
	@ApiOperation(value = "Fetch RoundTrip Pricing", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code=1700, message="Please select tenant!!"),
			@ApiResponse(code=1706, message="Please select an origin!!"),
			@ApiResponse(code=1707, message="Please select a destination!!"),
			@ApiResponse(code=1712, message="Please provide search ID!!"),
			@ApiResponse(code=1713, message="Please provide msid!!"),
			@ApiResponse(code=1714, message="Please provide request mode!!"),
			@ApiResponse(code=1715, message="Please provide BPC!!"),
			@ApiResponse(code=1716, message="Please provide SR!!"),
			@ApiResponse(code=1717, message="Please provide UNIQUE!!"),
			@ApiResponse(code=1718, message="Please provide variation!!"),
			@ApiResponse(code=1719, message="Please provide supplier code!!"),
			@ApiResponse(code=1720, message="Please provide flight number!!"),
			@ApiResponse(code=1721, message="Please provide date of travel!!") })
	public ResponseEntity<ResponseModel> fetchRoundTripPricing(@RequestBody FlightPriceModel flightPriceModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchRoundTripPricing -- START");
		}
		
		ResponseModel responseModel = new ResponseModel();
		Util.printLog(flightPriceModel, FlightConstant.INCOMING, "Fetch RoundTrip Pricing", request);
		
		try {
			String response = flightService.fetchRoundTripPricing(flightPriceModel);
			responseModel.setResponseBody(response);
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Fetch RoundTrip Pricing -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch OneWay Pricing -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, FlightConstant.OUTGOING, "Fetch OneWay Pricing", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchRoundTripPricing -- END");
		}

		if (responseModel.getResponseCode().equals(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/fetch-multi-city-pricing", produces = "application/json")
	@ApiOperation(value = "Fetch MultiCity Pricing", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code=1700, message="Please select tenant!!"),
			@ApiResponse(code=1706, message="Please select an origin!!"),
			@ApiResponse(code=1707, message="Please select a destination!!"),
			@ApiResponse(code=1712, message="Please provide search ID!!"),
			@ApiResponse(code=1713, message="Please provide msid!!"),
			@ApiResponse(code=1714, message="Please provide request mode!!"),
			@ApiResponse(code=1715, message="Please provide BPC!!"),
			@ApiResponse(code=1716, message="Please provide SR!!"),
			@ApiResponse(code=1717, message="Please provide UNIQUE!!"),
			@ApiResponse(code=1718, message="Please provide variation!!"),
			@ApiResponse(code=1719, message="Please provide supplier code!!"),
			@ApiResponse(code=1720, message="Please provide flight number!!"),
			@ApiResponse(code=1721, message="Please provide date of travel!!") })
	public ResponseEntity<ResponseModel> fetchMultiCityPricing(@RequestBody FlightPriceModel flightPriceModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchMultiCityPricing -- START");
		}
		
		ResponseModel responseModel = new ResponseModel();
		Util.printLog(flightPriceModel, FlightConstant.INCOMING, "Fetch MultiCity Pricing", request);
		
		try {
			String response = flightService.fetchMultiCityPricing(flightPriceModel);
			responseModel.setResponseBody(response);
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Fetch MultiCity Pricing -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Fetch MultiCity Pricing -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, FlightConstant.OUTGOING, "Fetch OneWay Pricing", request);

		if (logger.isInfoEnabled()) {
			logger.info("fetchMultiCityPricing -- END");
		}

		if (responseModel.getResponseCode().equals(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/save-review-details", produces = "application/json")
	@ApiOperation(value = "Save Review-Details", response = ResponseModel.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 201, message = "Please Try after Sometime!!!"),
			@ApiResponse(code=1700, message="Please select tenant!!"),
			@ApiResponse(code=1701, message="Please select trip type!!"),
			@ApiResponse(code=1702, message="Please provide segments!!"),
			@ApiResponse(code=1703, message="Please select number of adults!!"),
			@ApiResponse(code=1704, message="Please select class!!"),
			@ApiResponse(code=1706, message="Please select an origin!!"),
			@ApiResponse(code=1707, message="Please select a destination!!"),
			@ApiResponse(code=1708, message="Please select a origin country!!"),
			@ApiResponse(code=1709, message="Please select destination country!!"),			
			@ApiResponse(code=1710, message="Please select departure date!!")})
	public ResponseEntity<ResponseModel> saveReviewDetails(@RequestBody FlightBookingModel flightBookingModel) {
		
		if (logger.isInfoEnabled()) {
			logger.info("saveReviewDetails -- START");
		}
		
		ResponseModel responseModel = new ResponseModel();
		Util.printLog(flightBookingModel, FlightConstant.INCOMING, "Save Review-Details", request);
		
		try {
			flightService.saveReviewDetails(flightBookingModel);
			responseModel.setResponseBody(response);
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_MESSAGE));
		} catch (FormExceptions fe) {

			for (Entry<String, Exception> entry : fe.getExceptions().entrySet()) {
				responseModel.setResponseCode(entry.getKey());
				responseModel.setResponseMessage(entry.getValue().getMessage());
				if (logger.isInfoEnabled()) {
					logger.info("FormExceptions in Save Review-Details -- "+Util.errorToString(fe));
				}
				break;
			}
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in Save Review-Details -- "+Util.errorToString(e));
			}
			responseModel.setResponseCode(messageUtil.getBundle(FlightConstant.COMMON_ERROR_CODE));
			responseModel.setResponseMessage(messageUtil.getBundle(FlightConstant.COMMON_ERROR_MESSAGE));
		}
		
		Util.printLog(responseModel, FlightConstant.OUTGOING, "Save Review-Details", request);

		if (logger.isInfoEnabled()) {
			logger.info("saveReviewDetails -- END");
		}

		if (responseModel.getResponseCode().equals(messageUtil.getBundle(FlightConstant.COMMON_SUCCESS_CODE))) {
			return new ResponseEntity<>(responseModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
		}
	}
}
