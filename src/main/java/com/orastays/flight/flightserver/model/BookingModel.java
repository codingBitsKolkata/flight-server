package com.orastays.flight.flightserver.model;

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
	
	@JsonProperty("pricingId")
	private String pricingId;
	
	@JsonProperty("superPnr")
	private String superPnr;
	
	@JsonProperty("reviewJson")
	public ReviewJsonModel reviewJsonModel;

	@JsonProperty("commision")
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
	private String masterPassenger;
	
	@JsonProperty("conveniences")
	private ConvenienceModel convenienceModel;
	
	@JsonProperty("bookingInfos")
	private BookingInfoModel bookingInfoModel;

	@JsonProperty("convenienceAmtWgst")
	private String convenienceAmtWgst;
	
	@JsonProperty("bookingVsPayments")
	private List<BookingVsPaymentModel> bookingVsPaymentModels;
	
	@JsonProperty("userInfo")
	private UserInfo userInfo;
	
	@JsonProperty("cancellations")
	private CancellationModel cancellationModel;
}



