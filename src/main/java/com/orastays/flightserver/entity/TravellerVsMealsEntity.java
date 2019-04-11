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
@Table(name = "traveller_vs_meals")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class TravellerVsMealsEntity extends CommonEntity {

	private static final long serialVersionUID = 6461670485785356796L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "traveller_vs_meals_id")
	@JsonProperty("travellerVsMealsId")
	private Long travellerVsMealsId;

	@Column(name = "uid")
	@JsonProperty("uid")
	private String uid;
	
	@Column(name = "dispAmt")
	@JsonProperty("dispAmt")
	private String dispAmt;
	
	@Column(name = "trip")
	@JsonProperty("trip")
	private String trip;
	
	@Column(name = "isdata")
	@JsonProperty("isdata")
	private String isdata;
	
	@Column(name = "pax")
	@JsonProperty("pax")
	private String pax;
	
	@Column(name = "amt")
	@JsonProperty("amt")
	private String amt;
	
	@Column(name = "typ")
	@JsonProperty("typ")
	private String typ;
	
	@Column(name = "rph")
	@JsonProperty("rph")
	private String rph;

	@Column(name = "curr")
	@JsonProperty("curr")
	private String curr;

	@Column(name = "convamt")
	@JsonProperty("convamt")
	private String convamt;

	@Column(name = "meals_desc")
	@JsonProperty("mealsDesc")
	private String mealsDesc;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "flight_vs_traveller_id", nullable = false)
	@JsonProperty("flightVsTraveller")
	private FlightVsTravellerEntity flightVsTravellerEntity;
	
	@Override
	public String toString() {
		return Long.toString(travellerVsMealsId);
	}
}
