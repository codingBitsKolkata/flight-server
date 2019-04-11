package com.orastays.flightserver.model.booking;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.orastays.flightserver.model.CommonModel;
import com.orastays.flightserver.model.ConvenienceModel;
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
public class BookingVsFlightModel extends CommonModel {

	@JsonProperty("bookingVsFlightId")
	private String bookingVsFlightId;

	@JsonProperty("pnrNumber")
	private String pnrNumber;
	
	@JsonProperty("journeyDate")
	private String journeyDate;

	@JsonProperty("origin")
	private String origin;

	@JsonProperty("destination")
	private String destination;
	
	@JsonProperty("fltSchedule")
	private String fltSchedule;

	@JsonProperty("flightClass")
	private String flightClass;

	@JsonProperty("airlineCode")
	private String airlineCode;
	
	@JsonProperty("airlineNumber")
	private String airlineNumber;
	
	@JsonProperty("flightNumber")
	private String flightNumber;
	
	@JsonProperty("baseFare")
	private String baseFare;
	
	@JsonProperty("fuelSurcharges")
	private String fuelSurcharges;

	@JsonProperty("otherCharges")
	private String otherCharges;

	@JsonProperty("yatraGst")
	private String yatraGst;

	@JsonProperty("passengerFee")
	private String passengerFee;

	@JsonProperty("userDevFee")
	private String userDevFee;

	@JsonProperty("bookingFee")
	private String bookingFee;
	
    @JsonProperty("igst")
    private String igst;

    @JsonProperty("totalFare")
    private String totalFare;
    
    @JsonProperty("conveniences")
    private ConvenienceModel convenienceModel;
    
    @JsonProperty("totalFareWithConvenience")
    private String totalFareWithConvenience;
    
    @JsonProperty("oraCommission")
    private String oraCommission;
    
	@JsonProperty("booking")
	private BookingModel bookingModel;
	
	@JsonProperty("flightVsTravellers")
	private List<FlightVsTravellerModel> flightVsTravellerModels;
}
