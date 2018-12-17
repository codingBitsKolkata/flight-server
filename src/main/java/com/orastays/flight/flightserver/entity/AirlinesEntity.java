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
@Table(name = "master_airlines")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AirlinesEntity extends CommonEntity {

	private static final long serialVersionUID = 2336933232053047317L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "airlines_id")
	@JsonProperty("airlinesId")
	private Long airlinesId;

	@Column(name = "airlines_code")
	@JsonProperty("airlinesCode")
	private String airlinesCode;

	@Column(name = "airlines_name")
	@JsonProperty("airlinesName")
	private String airlinesName;

	@Override
	public String toString() {
		return Long.toString(airlinesId);

	}
}
