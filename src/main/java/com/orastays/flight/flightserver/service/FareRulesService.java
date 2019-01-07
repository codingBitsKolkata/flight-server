package com.orastays.flight.flightserver.service;

import java.util.List;

import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.FareRulesModel;

public interface FareRulesService {

	List<FareRulesModel> fareRulesDom(FareRulesModel fareRulesModel) throws FormExceptions;

	List<FareRulesModel> fareRulesInt(FareRulesModel fareRulesModel) throws FormExceptions;

	List<FareRulesModel> baggageInfo(FareRulesModel fareRulesModel) throws FormExceptions;
}