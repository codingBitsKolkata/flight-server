package com.orastays.flight.flightserver.service;

import java.util.List;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.BaggageModel;

public interface BaggageService {

	List<BaggageModel> baggageInfo(BaggageModel baggageModel) throws FormExceptions;
}