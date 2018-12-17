package com.orastays.flight.flightserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_timing")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class TimingEntity extends CommonEntity {

	private static final long serialVersionUID = 2000361140383216202L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "timing_id")
	@JsonProperty("timingId")
	private Long timingId;

	@Column(name = "from_time")
	@JsonProperty("fromTime")
	private String fromTime;

	@Column(name = "to_time")
	@JsonProperty("toTime")
	private String toTime;

	@Column(name = "timing_name")
	@JsonProperty("timingName")
	private String timingName;

	@Override
	public String toString() {
		return Long.toString(timingId);

	}
}
