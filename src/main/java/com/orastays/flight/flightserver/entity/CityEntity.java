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
@Table(name = "master_city")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CityEntity extends CommonEntity {

	private static final long serialVersionUID = 7789159073550369173L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	@JsonProperty("cityId")
	private Long cityId;

	@Column(name = "city_code")
	@JsonProperty("cityCode")
	private String cityCode;

	@Column(name = "city_name")
	@JsonProperty("cityName")
	private String cityName;

	@Column(name = "country_code")
	@JsonProperty("countryCode")
	private String countryCode;

	@Column(name = "country_name")
	@JsonProperty("countryName")
	private String countryName;

	@Override
	public String toString() {
		return Long.toString(cityId);

	}
}
