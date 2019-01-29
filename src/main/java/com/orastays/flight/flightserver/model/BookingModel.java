package com.orastays.flight.flightserver.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orastays.flight.flightserver.entity.BookingInfoEntity;
import com.orastays.flight.flightserver.entity.BookingVsPaymentEntity;
import com.orastays.flight.flightserver.entity.CancellationEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class BookingModel extends CommonModel {

	@JsonProperty("bookingId")
	private String bookingId;
	
	@JsonProperty("orabookingId")
	private String orabookingId;

	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("pricingId")
	private String pricingId;
	
	@JsonProperty("superPnr")
	private String superPnr;
	
	@JsonProperty("reviewJson")
	public ReviewJsonModel reviewJsonModel;

	/*@JsonProperty("commision")
	private String commision;

	@JsonProperty("tds")
	private String tds;

	@JsonProperty("totalAmount")
	private String totalAmount;

	@JsonProperty("emailId")
	private String emailId;
	
	@JsonProperty("mobileNumber")
	private String mobileNumber;
	
	@JsonProperty("masterPassenger")
	private String masterPassenger;*/
	
	@JsonProperty("userFinalPrice")
	private String userFinalPrice;

	@JsonProperty("oraSpecialOfferPerc")
	private String oraSpecialOfferPerc;

	@JsonProperty("oraSpecialOfferAmt")
	private String oraSpecialOfferAmt;

	@JsonProperty("convenienceFeePerc")
	private String convenienceFeePerc;

	@JsonProperty("convenienceFeeAmt")
	private String convenienceFeeAmt;

	@JsonProperty("convenienceGstAmt")
	private String convenienceGstAmt;

	@JsonProperty("totalPrice")
	private String totalPrice;

	@JsonProperty("convenienceAmtWgst")
	private String convenienceAmtWgst;
	
	@JsonProperty("bookingInfos")
	private BookingInfoEntity bookingInfoEntity;

	@JsonProperty("bookingVsPayments")
	private List<BookingVsPaymentEntity> bookingVsPaymentEntities;

	@JsonProperty("cancellations")
	private CancellationEntity cancellationEntity;

	@JsonProperty("failureURL")
	private String failureURL;
	
	@JsonProperty("successURL")
	private String successURL;
}



