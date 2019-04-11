package com.orastays.flightserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.flightserver.entity.FlightVsTravellerEntity;
import com.orastays.flightserver.helper.Util;
import com.orastays.flightserver.model.booking.FlightVsTravellerModel;

@Component
public class FlightVsTravellerConverter extends CommonConverter
		implements BaseConverter<FlightVsTravellerEntity, FlightVsTravellerModel> {

	private static final long serialVersionUID = -5655366867452415417L;
	
	private static final Logger logger = LogManager.getLogger(FlightVsTravellerConverter.class);

	@Override
	public FlightVsTravellerEntity modelToEntity(FlightVsTravellerModel m) {

		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}

		FlightVsTravellerEntity flightVsTravellerEntity = new FlightVsTravellerEntity();

		flightVsTravellerEntity = (FlightVsTravellerEntity) Util.transform(modelMapper, m, flightVsTravellerEntity);
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		return flightVsTravellerEntity;
	}

	@Override
	public FlightVsTravellerModel entityToModel(FlightVsTravellerEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		FlightVsTravellerModel flightVsTravellerModel = new FlightVsTravellerModel();
		flightVsTravellerModel = (FlightVsTravellerModel) Util.transform(modelMapper, e, flightVsTravellerModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return flightVsTravellerModel;
	}

	@Override
	public List<FlightVsTravellerModel> entityListToModelList(List<FlightVsTravellerEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<FlightVsTravellerModel> flightVsTravellerModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			flightVsTravellerModels = new ArrayList<>();
			for (FlightVsTravellerEntity flightVsTravellerEntity : es) {
				flightVsTravellerModels.add(entityToModel(flightVsTravellerEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return flightVsTravellerModels;
	}

}
