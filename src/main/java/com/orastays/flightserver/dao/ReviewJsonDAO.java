package com.orastays.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flightserver.entity.ReviewJsonEntity;

@Repository
public class ReviewJsonDAO extends GenericDAO<ReviewJsonEntity, Long> {

	private static final long serialVersionUID = -7064215542039389476L;

	public ReviewJsonDAO() {
		super(ReviewJsonEntity.class);

	}
}
