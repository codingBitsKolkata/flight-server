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
@Table(name = "booking_vs_user_details")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookingVsUserDetailsEntity extends CommonEntity {

	private static final long serialVersionUID = -4057088302903918510L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_vs_user_details_id")
	@JsonProperty("bookingVsUserDetailsId")
	private Long bookingVsUserDetailsId;

	@Column(name = "user_title")
	@JsonProperty("userTitle")
	private String userTitle;
	
	@Column(name = "user_first_name")
	@JsonProperty("userFirstName")
	private String userFirstName;
	
	@Column(name = "user_last_name")
	@JsonProperty("userLastName")
	private String userLastName;
	
	@Column(name = "user_email")
	@JsonProperty("userEmail")
	private String userEmail;
	
	@Column(name = "user_mobile")
	@JsonProperty("userMobile")
	private String userMobile;
	
	@Column(name = "user_mobile_isd")
	@JsonProperty("userMobileIsd")
	private String userMobileIsd;
	
	@Column(name = "user_id")
	@JsonProperty("userId")
	private String userId;
	
	@Column(name = "adtl_contact_email")
	@JsonProperty("adtlContactEmail")
	private String adtlContactEmail;
	
	@Column(name = "adtl_contact_mobile")
	@JsonProperty("adtlContactMobile")
	private String adtlContactMobile;
	
	@Column(name = "adtl_contact_mobile_isd")
	@JsonProperty("adtlContactMobileIsd")
	private String adtlContactMobileIsd;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "booking_id", nullable = false)
	@JsonProperty("booking")
	private BookingEntity bookingEntity;
	
	@Override
	public String toString() {
		return Long.toString(bookingVsUserDetailsId);
	}
}
