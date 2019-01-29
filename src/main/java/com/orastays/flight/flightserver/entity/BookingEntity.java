/**
 * @author Ora Dev
 */
package com.orastays.flight.flightserver.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	private static final long serialVersionUID = -2175231074975404425L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	@JsonProperty("bookingId")
	private Long bookingId;

	@Column(name = "orabooking_id")
	@JsonProperty("orabookingId")
	private String orabookingId;

	@Column(name = "user_id")
	@JsonProperty("userId")
	private Long userId;
	
	@Column(name = "pricing_id")
	@JsonProperty("pricingId")
	private String pricingId;
	
	@Column(name = "super_pnr")
	@JsonProperty("superPnr")
	private String superPnr;
	
	@Column(name = "reviewJson")
	@JsonProperty("reviewJson")
	public ReviewJsonEntity reviewJsonEntity;
	
	@Column(name = "user_final_price")
	@JsonProperty("userFinalPrice")
	private String userFinalPrice;
	
	@Column(name = "ora_special_offer_perc")
	@JsonProperty("oraSpecialOfferPerc")
	private String oraSpecialOfferPerc;

	@Column(name = "ora_special_offer_amt")
	@JsonProperty("oraSpecialOfferAmt")
	private String oraSpecialOfferAmt;

	@Column(name = "convenience_fee_perc")
	@JsonProperty("convenienceFeePerc")
	private String convenienceFeePerc;

	@Column(name = "convenience_fee_amt")
	@JsonProperty("convenienceFeeAmt")
	private String convenienceFeeAmt;

	@Column(name = "convenience_gst_amt")
	@JsonProperty("convenienceGstAmt")
	private String convenienceGstAmt;

	@Column(name = "total_price")
	@JsonProperty("totalPrice")
	private String totalPrice;
	
	@Column(name = "convenience_amt_wgst")
	@JsonProperty("convenienceAmtWgst")
	private String convenienceAmtWgst;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("bookingInfos")
	private BookingInfoEntity bookingInfoEntity;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("bookingVsPayments")
	private List<BookingVsPaymentEntity> bookingVsPaymentEntities;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("cancellations")
	private CancellationEntity cancellationEntity;

	@Column(name = "failure_url")
	@JsonProperty("failureURL")
	private String failureURL;
	
	@Column(name = "success_url")
	@JsonProperty("successURL")
	private String successURL;
	
	@Override
	public String toString() {
		return Long.toString(bookingId);
	}
}