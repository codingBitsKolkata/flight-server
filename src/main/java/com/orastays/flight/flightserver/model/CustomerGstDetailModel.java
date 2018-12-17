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
public class CustomerGstDetailModel extends CommonModel {

	@JsonProperty("cgdId")
	private Long cgdId;

	@JsonProperty("gstNumber")
	private String gstNumber;

	@JsonProperty("name")
	private String name;

	@JsonProperty("emailId")
	private String emailId;

	@JsonProperty("address")
	private String address;

	@JsonProperty("city")
	private String city;

	@JsonProperty("pincode")
	private String pincode;

	@JsonProperty("state")
	private String state;

	@JsonProperty("phoneNumber")
	private String phoneNumber;

}