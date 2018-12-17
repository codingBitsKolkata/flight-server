package com.orastays.flight.flightserver.dao;

import org.springframework.stereotype.Repository;

import com.orastays.flight.flightserver.entity.CustomerGstDetailEntity;

@Repository
public class CustomerGstDetailDAO extends GenericDAO<CustomerGstDetailEntity, Long> {

	private static final long serialVersionUID = -3967000943349204203L;

	public CustomerGstDetailDAO() {
		super(CustomerGstDetailEntity.class);

	}
}
