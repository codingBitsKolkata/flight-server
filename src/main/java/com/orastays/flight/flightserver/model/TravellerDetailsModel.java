package com.orastays.flight.flightserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonInclude(Include.NON_NULL)
public class TravellerDetailsModel extends CommonModel {

	public Integer id;
	public String title;
	public String firstName;
	public String middleName;
	public String lastName;
	public String paxClass;
	public String passengerClass;
	public String dateOfBirth;
    public PassportModel passportModel;
    public FrequentFlyerModel frequentFlyerModel;
}
