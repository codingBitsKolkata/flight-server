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
public class GstDetailsModel extends CommonModel {

	@JsonProperty("gstState")
    public String gstState;
	
	@JsonProperty("gstMobileIsd")
    public String gstMobileIsd;
	
	@JsonProperty("gstNumber")
    public String gstNumber;
	
	@JsonProperty("gstCompanyName")
    public String gstCompanyName;
	
	@JsonProperty("gstCompanyAddress")
    public String gstCompanyAddress;
	
	@JsonProperty("gstCity")
    public String gstCity;
	
	@JsonProperty("gstPinCode")
    public String gstPinCode;
	
	@JsonProperty("gstEmailId")
    public String gstEmailId;
	
	@JsonProperty("gstMobileNo")
    public String gstMobileNo;
}
