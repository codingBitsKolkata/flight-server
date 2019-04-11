package com.orastays.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flightserver.entity.FlightVsTravellerEntity;

@Repository
public class BookingVsTravellerDAO extends GenericDAO<FlightVsTravellerEntity, Long> {

	private static final long serialVersionUID = 3136876097336978477L;

	public BookingVsTravellerDAO() {
		super(FlightVsTravellerEntity.class);
	}
}
