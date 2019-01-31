package com.orastays.flight.flightserver.service;

import com.orastays.flight.flightserver.entity.ConvenienceEntity;
import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.ConvenienceModel;

public interface ConvenienceService {

	ConvenienceEntity getActiveConvenienceEntity();
	ConvenienceModel getActiveConvenienceModel() throws FormExceptions;
}