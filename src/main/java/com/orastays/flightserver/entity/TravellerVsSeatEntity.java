package com.orastays.flightserver.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "traveller_vs_seat")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class TravellerVsSeatEntity extends CommonEntity {

	private static final long serialVersionUID = 6454122990760355340L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "traveller_vs_seat_id")
	@JsonProperty("travellerVsSeatId")
	private Long travellerVsSeatId;
	
	@Column(name = "seatValue")
	@JsonProperty("seatValue")
	private String seatValue;
	
	@Column(name = "uid")
	@JsonProperty("uid")
	private String uid;
	
	@Column(name = "trip")
	@JsonProperty("trip")
	private String trip;

	@Column(name = "isdata")
	@JsonProperty("isdata")
	private String isdata;
	
	@Column(name = "pax")
	@JsonProperty("pax")
	private String pax;
	
	@Column(name = "price")
	@JsonProperty("price")
	private String price;
	
	@Column(name = "deploc")
	@JsonProperty("deploc")
	private String deploc;
	
	@Column(name = "rph")
	@JsonProperty("rph")
	private String rph;
	
	@Column(name = "arrloc")
	@JsonProperty("arrloc")
	private String arrloc;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "flight_vs_traveller_id", nullable = false)
	@JsonProperty("flightVsTraveller")
	private FlightVsTravellerEntity flightVsTravellerEntity;
	
	@Override
	public String toString() {
		return Long.toString(travellerVsSeatId);
	}
}
