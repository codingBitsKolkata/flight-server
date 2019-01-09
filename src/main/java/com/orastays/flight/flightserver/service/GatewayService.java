package com.orastays.flight.flightserver.service;

import com.orastays.flight.flightserver.entity.GatewayEntity;

public interface GatewayService {
	GatewayEntity getGatewayEntity(String gatewayName);
}
