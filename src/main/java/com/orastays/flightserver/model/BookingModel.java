package com.orastays.flightserver.model;

import java.util.List;

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
public class BookingModel extends CommonModel {

	@JsonProperty("bookingId")
	private String bookingId;
	
	@JsonProperty("orabookingId")
	private String orabookingId;

	@JsonProperty("userId")
	private String userId;
	
	@JsonProperty("progress")
	private String progress;
	
	@JsonProperty("pricingId")
	private String pricingId;
	
	@JsonProperty("superPnr")
	private String superPnr;
	
	@JsonProperty("reviewJson")
	public ReviewJsonModel reviewJsonModel;

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
	private BookingInfoModel bookingInfoModel;

	@JsonProperty("bookingVsPayments")
	private List<BookingVsPaymentModel> bookingVsPaymentModels;

	@JsonProperty("cancellations")
	private CancellationModel cancellationModel;

	@JsonProperty("failureURL")
	private String failureURL;
	
	@JsonProperty("successURL")
	private String successURL;
}



