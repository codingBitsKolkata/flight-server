package com.orastays.flight.flightserver.model;

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
public class DatumModel extends CommonModel {

	@JsonProperty("id")
    public String id;
	
	@JsonProperty("name")
    public String name;
	
	@JsonProperty("category")
    public String category;
	
	@JsonProperty("brand")
    public String brand;
	
	@JsonProperty("variant")
    public String variant;
	
	@JsonProperty("price")
    public String price;
}
