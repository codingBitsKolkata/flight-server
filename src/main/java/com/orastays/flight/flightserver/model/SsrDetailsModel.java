package com.orastays.flight.flightserver.model;

import java.util.List;

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
public class SsrDetailsModel extends CommonModel {

	@JsonProperty("ssrMealDetails")
    public List<SsrMealDetailsModel> ssrMealDetailsModels;
	
	@JsonProperty("ssrBaggageDetails")
    public List<SsrBaggageDetailsModel> SsrBaggageDetailsModels;
	
	@JsonProperty("ssrSeatDetails")
    public List<SsrSeatDetailsModel> ssrSeatDetailsModel;
	
	@JsonProperty("ssrOtherDetails")
    public List<SsrOtherDetailsModel> ssrOtherDetailsModels;
}
