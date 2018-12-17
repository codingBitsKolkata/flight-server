package com.orastays.flight.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flight.flightserver.entity.FlightCategoryEntity;

@Repository
public class FlightCategoryDAO extends GenericDAO<FlightCategoryEntity, Long> {

	private static final long serialVersionUID = 5106975371925748751L;

	public FlightCategoryDAO() {
		super(FlightCategoryEntity.class);

	}
}
