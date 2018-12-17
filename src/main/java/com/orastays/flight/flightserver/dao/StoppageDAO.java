package com.orastays.flight.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flight.flightserver.entity.StoppageEntity;

@Repository
public class StoppageDAO extends GenericDAO<StoppageEntity, Long> {

	private static final long serialVersionUID = 5973857513303282026L;

	public StoppageDAO() {
		super(StoppageEntity.class);

	}
}
