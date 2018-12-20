package com.orastays.flight.flightserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class FlightSearchModel extends CommonModel {

	//b2bdom, b2bint
	@JsonProperty("tenantName")
	private String tenantName;
	
	//R,O,M
	@JsonProperty("tripType")
	private String tripType;
	
	//Number of segments based on trip type(one way, round trip, multicity)
	@JsonProperty("noOfSegments")
	private String noOfSegments;
	
	@JsonProperty("origin")
	private String origin;
	
	@JsonProperty("originCountry")
	private String originCountry;
	
	@JsonProperty("destination")
	private String destination;
	
	@JsonProperty("destinationCountry")
	private String destinationCountry;
	
	@JsonProperty("flight_depart_date")
	private String flightDepartDate;
	
	@JsonProperty("arrival_date")
	private String arrivalDate;
	
	@JsonProperty("noOfAdults")
	private String noOfAdults;
	
	@JsonProperty("noOfChild")
	private String noOfChild;

	@JsonProperty("noOfInfants")
	private String noOfInfants;
	
	@JsonProperty("classType")
	private String classType;
	
	@JsonProperty("viewName")
	private String viewName;
	
	@JsonProperty("flexi")
	private String flexi;

	//In case of multicity search
	@JsonProperty("multicityCount")
	private String multicityCount;
	
	@JsonProperty("origin0")
	private String origin0;
	
	@JsonProperty("originCountry0")
	private String originCountry0;
	
	@JsonProperty("destination0")
	private String destination0;
	
	@JsonProperty("destinationCountry0")
	private String destinationCountry0;
	
	@JsonProperty("flightDepartDate0")
	private String flightDepartDate0;
	
	@JsonProperty("origin1")
	private String origin1;
	
	@JsonProperty("originCountry1")
	private String originCountry1;
	
	@JsonProperty("destination1")
	private String destination1;
	
	@JsonProperty("destinationCountry1")
	private String destinationCountry1;
	
	@JsonProperty("flightDepartDate1")
	private String flightDepartDate1;
	
	@JsonProperty("origin2")
	private String origin2;
	
	@JsonProperty("originCountry2")
	private String originCountry2;
	
	@JsonProperty("destination2")
	private String destination2;
	
	@JsonProperty("destinationCountry2")
	private String destinationCountry2;
	
	@JsonProperty("flightDepartDate2")
	private String flightDepartDate2;
}
