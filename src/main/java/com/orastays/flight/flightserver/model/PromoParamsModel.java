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
public class PromoParamsModel extends CommonModel {

	@JsonProperty("promoStatus")
    public String promoStatus;
	
	@JsonProperty("promoCode")
    public String promoCode;
	
	@JsonProperty("isReadonly")
    public String isReadonly;
	
	@JsonProperty("amount")
    public String amount;
	
	@JsonProperty("ecashAmount")
    public String ecashAmount;
	
	@JsonProperty("promoType")
    public String promoType;
	
	@JsonProperty("msg")
    public String msg;
	
	@JsonProperty("authCode")
    public String authCode;
	
	@JsonProperty("category")
    public String category;
}
