package com.orastays.flightserver.model.booking;

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
public class TravellerVsSeatModel extends CommonModel {

	@JsonProperty("travellerVsSeatId")
	private String travellerVsSeatId;
	
	@JsonProperty("seatValue")
	private String seatValue;
	
	@JsonProperty("uid")
	private String uid;
	
	@JsonProperty("trip")
	private String trip;

	@JsonProperty("isdata")
	private String isdata;
	
	@JsonProperty("pax")
	private String pax;
	
	@JsonProperty("price")
	private String price;
	
	@JsonProperty("deploc")
	private String deploc;
	
	@JsonProperty("rph")
	private String rph;
	
	@JsonProperty("arrloc")
	private String arrloc;
	
	@JsonProperty("flightVsTraveller")
	private FlightVsTravellerModel flightVsTravellerModel;
}
