package com.orastays.flightserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class PromoParamsModel {

	public Boolean status;
	public String promoCode;
	public Boolean isReadonly;
	public Integer amount;
	public Integer ecashAmount;
	public String promoType;
	public String msg;
	public String authCode;
	public String category;
}