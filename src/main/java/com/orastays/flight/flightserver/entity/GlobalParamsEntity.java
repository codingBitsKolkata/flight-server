package com.orastays.flight.flightserver.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "global_param")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class GlobalParamsEntity extends CommonEntity {

	private static final long serialVersionUID = 8817468007365861161L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "global_params_id")
	@JsonProperty("globalParamsId")
	private Long globalParamsId;
	
	@JsonProperty("pricing_id")
	public String pricingId;
	
	@JsonProperty("search_id")
	public String searchId;
	
	@JsonProperty("super_pnr")
	public String superPnr;

	@JsonProperty("ftype")
	public String ftype;
	
	@JsonProperty("org")
	public String org;
	
	@JsonProperty("dest")
	public String dest;

	@JsonProperty("child_tenant")
	public String childTenant;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "globalParamsEntity", cascade = { CascadeType.ALL })
	@JsonProperty("reviewJson")
	private ReviewJsonEntity reviewJsonEntity;
}
