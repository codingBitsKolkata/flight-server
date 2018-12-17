package com.orastays.flight.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flight.flightserver.entity.AirlinesEntity;

@Repository
public class AirlinesDAO extends GenericDAO<AirlinesEntity, Long> {

	private static final long serialVersionUID = -5993000447878571825L;

	public AirlinesDAO() {
		super(AirlinesEntity.class);

	}
}
