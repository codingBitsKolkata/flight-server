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
public class TravellerVsMealsModel extends CommonModel {

	@JsonProperty("travellerVsMealsId")
	private String travellerVsMealsId;

	@JsonProperty("uid")
	private String uid;
	
	@JsonProperty("dispAmt")
	private String dispAmt;
	
	@JsonProperty("trip")
	private String trip;
	
	@JsonProperty("isdata")
	private String isdata;
	
	@JsonProperty("pax")
	private String pax;
	
	@JsonProperty("amt")
	private String amt;
	
	@JsonProperty("typ")
	private String typ;
	
	@JsonProperty("rph")
	private String rph;

	@JsonProperty("curr")
	private String curr;

	@JsonProperty("convamt")
	private String convamt;

	@JsonProperty("mealsDesc")
	private String mealsDesc;
	
	@JsonProperty("flightVsTraveller")
	private FlightVsTravellerModel flightVsTravellerModel;
}
