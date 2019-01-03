package com.orastays.flight.flightserver.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.orastays.flight.flightserver.helper.DbConnection;

@Repository
public class BaseDAO {

	@Autowired
	protected DbConnection dbConnection;
}
