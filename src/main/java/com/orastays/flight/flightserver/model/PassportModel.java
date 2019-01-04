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
public class PassportModel extends CommonModel {

	@JsonProperty("nationality")
    public String nationality;
	
	@JsonProperty("number")
    public String number;
	
	@JsonProperty("issuingCountryCode")
    public String issuingCountryCode;
	
	@JsonProperty("issuingCountryName")
    public String issuingCountryName;
	
	@JsonProperty("expiryDate")
    public String expiryDate;

}
