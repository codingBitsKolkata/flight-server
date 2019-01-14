package com.orastays.flight.flightserver.model;

import java.util.List;

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
public class GaResponseModel extends CommonModel {

	
    public List<DataModel> dataModels;
	public String action;
	public String actionData;
	public ActionObjModel actionObjModel;
	public String event;

}
