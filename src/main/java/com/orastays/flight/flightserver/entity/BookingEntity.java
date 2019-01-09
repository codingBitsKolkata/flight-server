/**
 * @author SUDEEP
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

	private static final long serialVersionUID = -1532920959025074277L;

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
	
	@Column(name = "booking_approval")
	@JsonProperty("bookingApproval")
	private String bookingApproval;
	
	@Column(name = "convenience_amt_wgst")
	@JsonProperty("convenienceAmtWgst")
	private String convenienceAmtWgst;
	
	@Column(name = "total_payble_without_gst")
	@JsonProperty("totalPaybleWithoutGST")
	private String totalPaybleWithoutGST;

	@Column(name = "total_payble_with_gst")
	@JsonProperty("totalPaybleWithGST")
	private String totalPaybleWithGST;

	@Column(name = "grand_total")
	@JsonProperty("grandTotal")
	private String grandTotal;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "convenience_id", nullable = false)
	@JsonProperty("conveniences")
	private ConvenienceEntity convenienceEntity;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("bookingInfos")
	private BookingInfoEntity bookingInfoEntity;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("bookingVsPayments")
	private List<BookingVsPaymentEntity> bookingVsPaymentEntities;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "bookingEntity", cascade = { CascadeType.ALL })
	@JsonProperty("cancellations")
	private CancellationEntity cancellationEntity;
	
	@Override
	public String toString() {
		return Long.toString(bookingId);
	}
}