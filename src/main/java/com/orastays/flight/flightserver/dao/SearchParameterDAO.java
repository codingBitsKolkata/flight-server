package com.orastays.flight.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flight.flightserver.entity.SearchParameterEntity;

@Repository
public class SearchParameterDAO extends GenericDAO<SearchParameterEntity, Long> {

	private static final long serialVersionUID = 6924832187551965231L;

	public SearchParameterDAO() {
		super(SearchParameterEntity.class);
	}

}
