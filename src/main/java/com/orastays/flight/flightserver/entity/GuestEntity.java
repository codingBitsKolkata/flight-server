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
@Table(name = "master_guest")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class GuestEntity extends CommonEntity {

	private static final long serialVersionUID = 6658741436880013200L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "guest_id")
	@JsonProperty("guestId")
	private Long guestId;

	@Column(name = "guest_name")
	@JsonProperty("guestName")
	private String guestName;

	@Column(name = "mobile_number")
	@JsonProperty("mobileNumber")
	private String mobileNumber;

	@Column(name = "email_id")
	@JsonProperty("emailId")
	private String emailId;

	@Column(name = "dob")
	@JsonProperty("dob")
	private String dob;

	@Override
	public String toString() {
		return Long.toString(guestId);

	}
}
