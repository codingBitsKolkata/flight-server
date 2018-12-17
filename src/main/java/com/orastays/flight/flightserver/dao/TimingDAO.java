package com.orastays.flight.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flight.flightserver.entity.TimingEntity;

@Repository
public class TimingDAO extends GenericDAO<TimingEntity, Long> {

	private static final long serialVersionUID = -7437932741939919410L;

	public TimingDAO() {
		super(TimingEntity.class);

	}
}
