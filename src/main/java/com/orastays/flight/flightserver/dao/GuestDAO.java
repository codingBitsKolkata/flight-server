package com.orastays.flight.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flight.flightserver.entity.GuestEntity;

@Repository
public class GuestDAO extends GenericDAO<GuestEntity, Long> {

	private static final long serialVersionUID = -8578558441421355994L;

	public GuestDAO() {
		super(GuestEntity.class);

	}
}
