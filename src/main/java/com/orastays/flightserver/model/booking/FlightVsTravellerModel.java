package com.orastays.flightserver.model.booking;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.orastays.flightserver.model.CommonModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class FlightVsTravellerModel extends CommonModel {

	@JsonProperty("flightVsTravellerId")
	private String flightVsTravellerId;

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("travellerTitle")
	private String travellerTitle;

	@JsonProperty("travellerFirstName")
	private String travellerFirstName;
	
	@JsonProperty("travellerMiddleName")
	private String travellerMiddleName;
	
	@JsonProperty("travellerLastName")
	private String travellerLastName;
	
	@JsonProperty("travellerPaxClass")
	private String travellerPaxClass;
	
	@JsonProperty("passengerClass")
	private String passengerClass;
	
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	
	@JsonProperty("passportNationality")
	private String passportNationality;
	
	@JsonProperty("passportNumber")
	private String passportNumber;
	
	@JsonProperty("passportIssuingCountryCode")
	private String passportIssuingCountryCode;
	
	@JsonProperty("passportIssuingCountryName")
	private String passportIssuingCountryName;
	
	@JsonProperty("passportExpiryDate")
	private String passportExpiryDate;
	
	@JsonProperty("bookingVsFlight")
	private BookingVsFlightModel bookingVsFlightModel;
	
	@JsonProperty("travellerVsMeals")
	private List<TravellerVsMealsModel> travellerVsMealsModels;
	
	@JsonProperty("travellerVsBaggages")
	private List<TravellerVsBaggageModel> travellerVsBaggageModels;
	
	@JsonProperty("travellerVsSeat")
	private TravellerVsSeatModel travellerVsSeatModel;
	
	/*
	 * @JsonProperty("travellerVsOthers") private TravellerVsOthersModel
	 * travellerVsOthersModel;
	 */
}
