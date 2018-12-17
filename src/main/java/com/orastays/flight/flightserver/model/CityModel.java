/**
 * @author SUDEEP
 */
package com.orastays.flight.flightserver.model;

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
public class CityModel extends CommonModel {

	@JsonProperty("cityId")
	private Long cityId;

	@JsonProperty("cityCode")
	private String cityCode;

	@JsonProperty("cityName")
	private String cityName;

	@JsonProperty("countryCode")
	private String countryCode;

	@JsonProperty("countryName")
	private String countryName;

}