package com.orastays.flightserver.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "booking_vs_flight")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookingVsFlightEntity extends CommonEntity {

	private static final long serialVersionUID = 6334466440815813007L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_vs_flight_id")
	@JsonProperty("bookingVsFlightId")
	private Long bookingVsFlightId;

	@Column(name = "pnr_number")
	@JsonProperty("pnrNumber")
	private String pnrNumber;
	
	@Column(name = "journey_date")
	@JsonProperty("journeyDate")
	private String journeyDate;

	@Column(name = "origin")
	@JsonProperty("origin")
	private String origin;

	@Column(name = "destination")
	@JsonProperty("destination")
	private String destination;
	
	@Column(name = "flt_schedule")
	@JsonProperty("fltSchedule")
	private String fltSchedule;

	@Column(name = "flight_class")
	@JsonProperty("flightClass")
	private String flightClass;

	@Column(name = "airline_code")
	@JsonProperty("airlineCode")
	private String airlineCode;
	
	@Column(name = "airline_number")
	@JsonProperty("airlineNumber")
	private String airlineNumber;
	
	@Column(name = "flight_number")
	@JsonProperty("flightNumber")
	private String flightNumber;
	
	@Column(name = "base_fare")
	@JsonProperty("baseFare")
	private String baseFare;
	
	@Column(name = "fuel_surcharges")
	@JsonProperty("fuelSurcharges")
	private String fuelSurcharges;

	@Column(name = "other_charges")
	@JsonProperty("otherCharges")
	private String otherCharges;

	@Column(name = "yatra_gst")
	@JsonProperty("yatraGst")
	private String yatraGst;

	@Column(name = "passenger_fee")
	@JsonProperty("passengerFee")
	private String passengerFee;

	@Column(name = "user_dev_fee")
	@JsonProperty("userDevFee")
	private String userDevFee;

	@Column(name = "booking_fee")
	@JsonProperty("bookingFee")
	private String bookingFee;
	
	@Column(name = "igst")
    @JsonProperty("igst")
    private String igst;

	@Column(name = "total_fare")
    @JsonProperty("totalFare")
    private String totalFare;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "convenience_id", nullable = false)
    @JsonProperty("conveniences")
    private ConvenienceEntity convenienceEntity;
	
	@Column(name = "total_fare_with_convenience")
    @JsonProperty("totalFareWithConvenience")
    private String totalFareWithConvenience;
	
	@Column(name = "ora_commission")
    @JsonProperty("oraCommission")
    private String oraCommission;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "booking_id", nullable = false)
	@JsonProperty("booking")
	private BookingEntity bookingEntity;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookingVsFlightEntity", cascade = { CascadeType.ALL })
	@JsonProperty("flightVsTravellers")
	private List<FlightVsTravellerEntity> flightVsTravellerEntities;
	
	@Override
	public String toString() {
		return Long.toString(bookingVsFlightId);
	}
}
