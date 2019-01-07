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
public class BaggageModel extends CommonModel {

	@JsonProperty("tenantName")
	private String tenantName;
	
	@JsonProperty("searchId")
	private String searchId;
	
	@JsonProperty("flightIdCSV")
    public String flightIdCSV;
	
	@JsonProperty("sc")
    public String sc;

}
