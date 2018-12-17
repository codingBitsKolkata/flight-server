package com.orastays.flight.flightserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_flight_category")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class FlightCategoryEntity extends CommonEntity {

	private static final long serialVersionUID = -4187458282740362452L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_category_id")
	@JsonProperty("flightCategoryId")
	private Long flightCategoryId;

	@Column(name = "flight_category_name")
	@JsonProperty("flightCategoryName")
	private String flightCategoryName;

	@Override
	public String toString() {
		return Long.toString(flightCategoryId);

	}
}
