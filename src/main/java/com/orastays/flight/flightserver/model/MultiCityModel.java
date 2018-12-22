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
public class MultiCityModel extends CommonModel{
	
	@JsonProperty("origin")
	private String origin;
	
	@JsonProperty("originCountry")
	private String originCountry;
	
	@JsonProperty("destination")
	private String destination;
	
	@JsonProperty("destinationCountry")
	private String destinationCountry;
	
	@JsonProperty("flightDepartDate")
	private String flightDepartDate;
	
/*	@JsonProperty("origin0")
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
	
	@JsonProperty("origin3")
	private String origin3;
	
	@JsonProperty("originCountry3")
	private String originCountry3;
	
	@JsonProperty("destination3")
	private String destination3;
	
	@JsonProperty("destinationCountry3")
	private String destinationCountry3;
	
	@JsonProperty("flightDepartDate3")
	private String flightDepartDate3;*/
}
