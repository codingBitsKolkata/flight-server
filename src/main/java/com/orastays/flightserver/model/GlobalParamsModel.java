package com.orastays.flightserver.model;

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
public class GlobalParamsModel /*extends CommonModel*/ {

	@JsonProperty("globalParamsId")
	private String globalParamsId;
	
	private String prq;
	private String aDTcreator;
	
	@JsonProperty("pricingId")
	private String pricingId;
	@JsonProperty("searchId")
	private String searchId;
	@JsonProperty("superPnr")
	private String superPnr;
	
	private String channel;
	private String product;
	
	@JsonProperty("ftype")
	private String ftype;
	@JsonProperty("org")
	private String org;
	@JsonProperty("dest")
	private String dest;
	private Boolean isPartial;
	private String ebsAccountId;
	private String ebsSessionId;
	private String moProfileType;
	@JsonProperty("childTenant")
	private String childTenant;
	private Integer variation;
	
	@JsonProperty("reviewJson")
	private ReviewJsonModel reviewJsonModel;
}
