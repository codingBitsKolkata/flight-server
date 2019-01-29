package com.orastays.flight.flightserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orastays.flight.flightserver.model.BookingModel;
import com.orastays.flight.flightserver.model.CommonModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class BookingVsFlightModel extends CommonModel {

	@JsonProperty("bookingVsFlightId")
	private String bookingVsFlightId;

	@JsonProperty("pnrNumber")
	private String pnrNumber;
	
	@JsonProperty("journeyDate")
	private String journeyDate;

	@JsonProperty("origin")
	private String origin;

	@JsonProperty("destination")
	private String destination;
	
	@JsonProperty("fltSchedule")
	private String fltSchedule;

	@JsonProperty("flightClass")
	private String flightClass;

	@JsonProperty("airlineCode")
	private String airlineCode;
	
	@JsonProperty("airlineNumber")
	private String airlineNumber;
	
	@JsonProperty("flightNumber")
	private String flightNumber;

	@JsonProperty("bookings")
	private BookingModel bookingModel;

	/*@JsonProperty("cancellationVsRooms")
	private CancellationVsRoomModel cancellationVsRoomModel;*/
	
	
}
