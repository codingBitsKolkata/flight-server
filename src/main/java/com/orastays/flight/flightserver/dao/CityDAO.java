package com.orastays.flight.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flight.flightserver.entity.CityEntity;

@Repository
public class CityDAO extends GenericDAO<CityEntity, Long> {

	private static final long serialVersionUID = 5817043566542910768L;

	public CityDAO() {
		super(CityEntity.class);

	}
}
