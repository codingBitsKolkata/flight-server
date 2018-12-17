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
@Table(name = "customer_gst_details")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class CustomerGstDetailEntity extends CommonEntity {

	private static final long serialVersionUID = -6123902068842269088L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cgd_id")
	@JsonProperty("cgdId")
	private Long cgdId;

	@Column(name = "gst_number")
	@JsonProperty("gstNumber")
	private String gstNumber;

	@Column(name = "name")
	@JsonProperty("name")
	private String name;

	@Column(name = "email_id")
	@JsonProperty("emailId")
	private String emailId;

	@Column(name = "address")
	@JsonProperty("address")
	private String address;

	@Column(name = "city")
	@JsonProperty("city")
	private String city;

	@Column(name = "pincode")
	@JsonProperty("pincode")
	private String pincode;

	@Column(name = "state")
	@JsonProperty("state")
	private String state;

	@Column(name = "phone_number")
	@JsonProperty("phoneNumber")
	private String phoneNumber;

	@Override
	public String toString() {
		return Long.toString(cgdId);

	}
}
