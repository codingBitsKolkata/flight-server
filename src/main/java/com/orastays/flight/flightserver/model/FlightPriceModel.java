package com.orastays.flight.flightserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
public class FlightPriceModel {
	
	@JsonProperty("searchId")
	private String searchId;
	
	@JsonProperty("msid")
	private String msid;
	
	@JsonProperty("requestMode")
	private String requestMode;
	
	@JsonProperty("origin")
	private String origin;
	
	@JsonProperty("destination")
	private String destination;
	
	@JsonProperty("supplierCode")
	private String supplierCode;
	
	@JsonProperty("flightNumber")
	private String flightNumber;
	
	@JsonProperty("dateOfTravel")
	private String dateOfTravel;	
	
	@JsonProperty("flightPrice")
	private String flightPrice;
	
	@JsonProperty("bpc")
	private String bpc;
	
	@JsonProperty("isSR")
	private String isSR;
	
	@JsonProperty("unique")
	private String unique;
	
	@JsonProperty("variation")
	private String variation;
	
	@JsonProperty("sc")
	private String sc;
}
