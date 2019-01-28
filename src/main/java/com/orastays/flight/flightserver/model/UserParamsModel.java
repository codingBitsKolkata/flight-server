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
public class UserParamsModel extends CommonModel {

	@JsonProperty("userParamsId")
    public String userParamsId;
	
	@JsonProperty("additionalContact")
    public AdditionalContactModel additionalContactModel;
	
	@JsonProperty("emailId")
    public String emailId;
	
	@JsonProperty("mobileNo")
    public String mobileNo;
	
	@JsonProperty("userId")
    public String userId;
	
	@JsonProperty("title")
    public String title;
	
	@JsonProperty("firstName")
    public String firstName;
	
	@JsonProperty("lastName")
    public String lastName;
	
	@JsonProperty("mobileNoISD")
    public String mobileNoISD;
}
