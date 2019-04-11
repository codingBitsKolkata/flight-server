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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "flight_vs_traveller")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class FlightVsTravellerEntity extends CommonEntity {

	private static final long serialVersionUID = -7209821872652876283L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_vs_traveller_id")
	@JsonProperty("flightVsTravellerId")
	private Long flightVsTravellerId;

	@Column(name = "id")
	@JsonProperty("id")
	private String id;
	
	@Column(name = "traveller_title")
	@JsonProperty("travellerTitle")
	private String travellerTitle;

	@Column(name = "traveller_first_name")
	@JsonProperty("travellerFirstName")
	private String travellerFirstName;
	
	@Column(name = "traveller_middle_name")
	@JsonProperty("travellerMiddleName")
	private String travellerMiddleName;
	
	@Column(name = "traveller_last_name")
	@JsonProperty("travellerLastName")
	private String travellerLastName;
	
	@Column(name = "traveller_pax_class")
	@JsonProperty("travellerPaxClass")
	private String travellerPaxClass;
	
	@Column(name = "passenger_class")
	@JsonProperty("passengerClass")
	private String passengerClass;
	
	@Column(name = "date_of_birth")
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	
	@Column(name = "passport_nationality")
	@JsonProperty("passportNationality")
	private String passportNationality;
	
	@Column(name = "passport_number")
	@JsonProperty("passportNumber")
	private String passportNumber;
	
	@Column(name = "passport_issuing_country_code")
	@JsonProperty("passportIssuingCountryCode")
	private String passportIssuingCountryCode;
	
	@Column(name = "passport_issuing_country_name")
	@JsonProperty("passportIssuingCountryName")
	private String passportIssuingCountryName;
	
	@Column(name = "passport_expiry_date")
	@JsonProperty("passportExpiryDate")
	private String passportExpiryDate;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "booking_vs_flight_id", nullable = false)
	@JsonProperty("bookingVsFlight")
	private BookingVsFlightEntity bookingVsFlightEntity;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flightVsTravellerEntity", cascade = { CascadeType.ALL })
	@JsonProperty("travellerVsMeals")
	private List<TravellerVsMealsEntity> travellerVsMealsEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flightVsTravellerEntity", cascade = { CascadeType.ALL })
	@JsonProperty("travellerVsBaggages")
	private List<TravellerVsBaggageEntity> travellerVsBaggageEntities;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "flightVsTravellerEntity", cascade = { CascadeType.ALL })
	@JsonProperty("travellerVsSeat")
	private TravellerVsSeatEntity travellerVsSeatEntity;
	
	@Override
	public String toString() {
		return Long.toString(flightVsTravellerId);
	}
}
