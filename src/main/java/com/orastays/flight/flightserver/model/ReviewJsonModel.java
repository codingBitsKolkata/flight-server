package com.orastays.flight.flightserver.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class ReviewJsonModel extends CommonModel {

	@JsonProperty("rvJsonId")
	private String rvJsonId;
	
	@JsonProperty("globalParams")
	public GlobalParamsModel globalParamsModel;
	
	@JsonProperty("addOnParams")
	public List<AddOnParamsModel> addOnParamsModels;
	
	@JsonProperty("hotelCrossSellParams")
	public HotelCrossSellParamsModel hotelCrossSellParamsModel;
	
	@JsonProperty("productParams")
	public ProductParamsModel productParamsModel;
	
	@JsonProperty("promoParams")
	public PromoParamsModel promoParamsModel;
	
	@JsonProperty("userParams")
	public UserParamsModel UserParamsModel;
	
	@JsonProperty("travellerParams")
	public List<TravellerParamModel> travellerParamModels;
	
	@JsonProperty("gstDetails")
	public GstDetailsModel gstDetailsModel;
	
	@JsonProperty("discountParams")
	public DiscountParamsModel discountParamsModels;
	
	@JsonProperty("totalBreakup")
	public TotalBreakupModel totalBreakupModel;
	
	@JsonProperty("tourCodes")
	public List<TourCodeModel> tourCodeModels;
	
	@JsonProperty("advancedPricing")
	public AdvancedPricingModel advancedPricingModel;
	
	@JsonProperty("upSellParam")
	public List<UpSellParamsModel> upSellParamsModel;
	
	@JsonProperty("gaResponse")
	public GaResponseModel gaResponseModel;
}
