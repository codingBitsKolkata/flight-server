package com.orastays.flightserver.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "traveller_vs_baggages")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class TravellerVsBaggageEntity extends CommonEntity {

	private static final long serialVersionUID = 1303333634819251142L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "traveller_vs_baggage_id")
	@JsonProperty("travellerVsBaggageId")
	private Long travellerVsBaggageId;
	
	@Column(name = "typ")
	@JsonProperty("typ")
	private String typ;
	
	@Column(name = "baggage_desc")
	@JsonProperty("baggageDesc")
	private String baggageDesc;
	
	@Column(name = "amt")
	@JsonProperty("amt")
	private String amt;
	
	@Column(name = "curr")
	@JsonProperty("curr")
	private String curr;
	
	@Column(name = "convamt")
	@JsonProperty("convamt")
	private String convamt;
	
	@Column(name = "isdata")
	@JsonProperty("isdata")
	private String isdata;
	
	@Column(name = "dispAmt")
	@JsonProperty("dispAmt")
	private String dispAmt;
	
	@Column(name = "pax")
	@JsonProperty("pax")
	private String pax;
	
	@Column(name = "trip")
	@JsonProperty("trip")
	private String trip;
	
	@Column(name = "uid")
	@JsonProperty("uid")
	private String uid;
	
	@Column(name = "rph")
	@JsonProperty("rph")
	private String rph;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "flight_vs_traveller_id", nullable = false)
	@JsonProperty("flightVsTraveller")
	private FlightVsTravellerEntity flightVsTravellerEntity;
	
	@Override
	public String toString() {
		return Long.toString(travellerVsBaggageId);
	}
}
