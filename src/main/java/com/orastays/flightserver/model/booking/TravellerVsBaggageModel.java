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
public class TravellerVsBaggageModel extends CommonModel {

	@JsonProperty("travellerVsBaggageId")
	private String travellerVsBaggageId;
	
	@JsonProperty("typ")
	private String typ;
	
	@JsonProperty("baggageDesc")
	private String baggageDesc;
	
	@JsonProperty("amt")
	private String amt;
	
	@JsonProperty("curr")
	private String curr;
	
	@JsonProperty("convamt")
	private String convamt;
	
	@JsonProperty("isdata")
	private String isdata;
	
	@JsonProperty("dispAmt")
	private String dispAmt;
	
	@JsonProperty("pax")
	private String pax;
	
	@JsonProperty("trip")
	private String trip;
	
	@JsonProperty("uid")
	private String uid;
	
	@JsonProperty("rph")
	private String rph;
	
	@JsonProperty("flightVsTravellers")
	private List<FlightVsTravellerModel> flightVsTravellerModels;
}
