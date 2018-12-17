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
@Table(name = "master_fare_type")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class FareTypeEntity extends CommonEntity {

	private static final long serialVersionUID = 8859098315950521360L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fare_type_id")
	@JsonProperty("fareTypeId")
	private Long fareTypeId;

	@Column(name = "fare_type_name")
	@JsonProperty("fareTypeName")
	private String fareTypeName;

	@Override
	public String toString() {
		return Long.toString(fareTypeId);

	}
}
