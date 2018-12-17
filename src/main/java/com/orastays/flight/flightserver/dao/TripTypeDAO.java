package com.orastays.flight.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flight.flightserver.entity.TripTypeEntity;

@Repository
public class TripTypeDAO extends GenericDAO<TripTypeEntity, Long> {

	private static final long serialVersionUID = -943350541615724909L;

	public TripTypeDAO() {
		super(TripTypeEntity.class);

	}
}
