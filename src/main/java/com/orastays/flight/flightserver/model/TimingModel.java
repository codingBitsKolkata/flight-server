/**
 * @author SUDEEP
 */
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
public class TimingModel extends CommonModel {

	@JsonProperty("timingId")
	private Long timingId;

	@JsonProperty("fromTime")
	private String fromTime;

	@JsonProperty("toTime")
	private String toTime;

	@JsonProperty("timingName")
	private String timingName;
}