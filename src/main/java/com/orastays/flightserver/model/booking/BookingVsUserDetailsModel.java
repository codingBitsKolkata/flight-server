package com.orastays.flightserver.model.booking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orastays.flightserver.entity.BookingEntity;
import com.orastays.flightserver.model.CommonModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class BookingVsUserDetailsModel extends CommonModel {

	@JsonProperty("bookingVsUserDetailsId")
	private String bookingVsUserDetailsId;

	@JsonProperty("userTitle")
	private String userTitle;
	
	@JsonProperty("userFirstName")
	private String userFirstName;
	
	@JsonProperty("userLastName")
	private String userLastName;
	
	@JsonProperty("userEmail")
	private String userEmail;
	
	@JsonProperty("userMobile")
	private String userMobile;
	
	@JsonProperty("userMobileIsd")
	private String userMobileIsd;
	
	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("adtlContactEmail")
	private String adtlContactEmail;
	
	@JsonProperty("adtlContactMobile")
	private String adtlContactMobile;
	
	@JsonProperty("adtlContactMobileIsd")
	private String adtlContactMobileIsd;
	
	@JsonProperty("booking")
	private BookingEntity bookingEntity;
}
