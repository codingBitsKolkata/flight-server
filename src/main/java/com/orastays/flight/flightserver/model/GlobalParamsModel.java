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

	private String globalParamsId;
	private String prq;
	private String aDTcreator;
	private String pricingId;
	private String searchId;
	private String superPnr;
	private String channel;
	private String product;
	private String ftype;
	private String org;
	private String dest;
	private Boolean isPartial;
	private String ebsAccountId;
	private String ebsSessionId;
	private String moProfileType;
	private String childTenant;
	private Integer variation;
}
