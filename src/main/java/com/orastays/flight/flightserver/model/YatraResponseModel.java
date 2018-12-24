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
public class YatraResponseModel {

	@JsonProperty("eagerFetch")
	private String eagerFetch;
	
	@JsonProperty("resultDatas")
	List<ResultDataModel> resultDatas;
}
