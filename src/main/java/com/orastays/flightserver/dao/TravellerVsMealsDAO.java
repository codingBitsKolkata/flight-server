package com.orastays.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flightserver.entity.TravellerVsMealsEntity;

@Repository
public class TravellerVsMealsDAO extends GenericDAO<TravellerVsMealsEntity, Long> {

	private static final long serialVersionUID = 396499752410917666L;

	public TravellerVsMealsDAO() {
		super(TravellerVsMealsEntity.class);

	}
}
