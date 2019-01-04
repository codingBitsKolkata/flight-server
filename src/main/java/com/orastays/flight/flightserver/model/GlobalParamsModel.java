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
public class GlobalParamsModel extends CommonModel {

	@JsonProperty("prq")
	public String prq;

	@JsonProperty("aDTcreator")
	public String aDTcreator;

	@JsonProperty("pricingId")
	public String pricingId;

	@JsonProperty("searchId")
	public String searchId;

	@JsonProperty("superPnr")
	public String superPnr;

	@JsonProperty("channel")
	public String channel;

	@JsonProperty("product")
	public String product;

	@JsonProperty("ftype")
	public String ftype;

	@JsonProperty("org")
	public String org;

	@JsonProperty("dest")
	public String dest;

	@JsonProperty("isPartial")
	public String isPartial;

	@JsonProperty("ebsAccountId")
	public String ebsAccountId;

	@JsonProperty("ebsSessionId")
	public String ebsSessionId;

	@JsonProperty("moProfileType")
	public String moProfileType;

	@JsonProperty("childTenant")
	public String childTenant;

	@JsonProperty("variation")
	public String variation;
}
