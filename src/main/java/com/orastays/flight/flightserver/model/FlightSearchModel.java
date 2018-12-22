package com.orastays.flight.flightserver.model;

import java.util.List;

import org.apache.commons.collections.map.MultiKeyMap;

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

	@JsonProperty("multiCities")
	List<MultiCityModel> multiCityModels;
}
