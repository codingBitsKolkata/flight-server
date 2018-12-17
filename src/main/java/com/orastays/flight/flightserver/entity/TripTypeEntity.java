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
@Table(name = "master_trip_type")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class TripTypeEntity extends CommonEntity {

	private static final long serialVersionUID = 4078828397055925550L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trip_type_id")
	@JsonProperty("tripTypeId")
	private Long tripTypeId;

	@Column(name = "trip_type_name")
	@JsonProperty("tripTypeName")
	private String tripTypeName;

	@Override
	public String toString() {
		return Long.toString(tripTypeId);

	}
}
