package com.orastays.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flightserver.entity.TravellerVsBaggageEntity;

@Repository
public class TravellerVsBaggageDAO extends GenericDAO<TravellerVsBaggageEntity, Long> {

	private static final long serialVersionUID = 8300169643621209096L;

	public TravellerVsBaggageDAO() {
		super(TravellerVsBaggageEntity.class);

	}
}
