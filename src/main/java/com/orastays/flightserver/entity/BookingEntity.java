/**
 * @author Ora Dev
 */
package com.orastays.flightserver.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_booking")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookingEntity extends CommonEntity {

	private static final long serialVersionUID = 6715453926945816673L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	@JsonProperty("bookingId")
	private Long bookingId;

	@Column(name = "ora_booking_id")
	@JsonProperty("oraBookingId")
	private String oraBookingId;

	@Column(name = "user_id")
	@JsonProperty("userId")
	private Long userId;
	
	@Column(name = "progress")
	@JsonProperty("progress")
	private String progress;
	
	@Column(name = "pricing_id")
	@JsonProperty("pricingId")
	private String pricingId;
	
	@Column(name = "super_pnr")
	@JsonProperty("superPnr")
	private String superPnr;
	
	//searchId and msid will be same
	@Column(name = "search_id")
	@JsonProperty("searchId")
	private String searchId;
	
	@Column(name = "failure_url")
    @JsonProperty("failureURL")
    private String failureURL;
	
	@Column(name = "success_url")
    @JsonProperty("successURL")
    private String successURL;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "gateway_id", nullable = false)
    @JsonProperty("gateway")
    private GatewayEntity gatewayEntity;
		
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("bookingVsFlights")
	private List<BookingVsFlightEntity> bookingVsFlightEntities;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("bookingVsPayments")
	private List<BookingVsPaymentEntity> bookingVsPaymentEntities;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("cancellations")
	private CancellationEntity cancellationEntity;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("bookingVsUserDetails")
	private BookingVsUserDetailsEntity bookingVsUserDetailsEntity;
	
	@Override
	public String toString() {
		return Long.toString(bookingId);
	}
}