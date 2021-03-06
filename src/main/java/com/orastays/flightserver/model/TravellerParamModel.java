package com.orastays.flightserver.model;

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
public class TravellerParamModel /*extends CommonModel*/ {

	@JsonProperty("paxID")
	public Integer paxID;
	
	@JsonProperty("travellerDetails")
    public TravellerDetailsModel travellerDetailsModel;
	
	@JsonProperty("ssrDetails")
    public SsrDetailsModel ssrDetailsModel;

}
