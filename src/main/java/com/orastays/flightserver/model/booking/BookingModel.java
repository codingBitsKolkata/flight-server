/**
 * @author Ora Dev
 */
package com.orastays.flightserver.model.booking;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.orastays.flightserver.model.CommonModel;
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

	@JsonProperty("oraBookingId")
	private String oraBookingId;

	@JsonProperty("userId")
	private String userId;
	
	//To keep the status at every step
	@JsonProperty("progress")
	private String progress;
	
	@JsonProperty("pricingId")
	private String pricingId;
	
	@JsonProperty("superPnr")
	private String superPnr;
	
	//searchId and msid will be same
	@JsonProperty("searchId")
	private String searchId;
	
    @JsonProperty("failureURL")
    private String failureURL;
	
    @JsonProperty("successURL")
    private String successURL;
    
    @JsonProperty("gateway")
    private GatewayModel gatewayModel;
    
	@JsonProperty("bookingVsFlights")
	private List<BookingVsFlightModel> bookingVsFlightModels;
	
	@JsonProperty("bookingVsPayments")
	private List<BookingVsPaymentModel> bookingVsPaymentModels;
	
	@JsonProperty("cancellations")
	private CancellationModel cancellationModel;
	
	@JsonProperty("bookingVsUserDetails")
	private BookingVsUserDetailsModel bookingVsUserDetailsModel;
}