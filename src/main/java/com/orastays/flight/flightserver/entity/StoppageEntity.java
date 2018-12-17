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
@Table(name = "master_stoppage")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class StoppageEntity extends CommonEntity {

	private static final long serialVersionUID = -3321171667614938684L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stoppage_id")
	@JsonProperty("stoppageId")
	private Long stoppageId;

	@Column(name = "stoppage_name")
	@JsonProperty("stoppageName")
	private String stoppageName;

	@Override
	public String toString() {
		return Long.toString(stoppageId);

	}
}
