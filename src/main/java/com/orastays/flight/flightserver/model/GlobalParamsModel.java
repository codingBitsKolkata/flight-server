package com.orastays.flight.flightserver.model;

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
public class GlobalParamsModel extends CommonModel {

	public String prq;
	public String aDTcreator;
	public String pricingId;
	public String searchId;
	public String superPnr;
	public String channel;
	public String product;
	public String ftype;
	public String org;
	public String dest;
	public Boolean isPartial;
	public String ebsAccountId;
	public String ebsSessionId;
	public String moProfileType;
	public String childTenant;
	public Integer variation;
}
