package com.orastays.flight.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flight.flightserver.entity.FareTypeEntity;

@Repository
public class FareTypeDAO extends GenericDAO<FareTypeEntity, Long> {

	private static final long serialVersionUID = -6762980084167779064L;

	public FareTypeDAO() {
		super(FareTypeEntity.class);

	}
}
