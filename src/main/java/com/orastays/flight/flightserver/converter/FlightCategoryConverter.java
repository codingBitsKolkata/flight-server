package com.orastays.flight.flightserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.flight.flightserver.entity.FlightCategoryEntity;
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.FlightCategoryModel;

@Component
public class FlightCategoryConverter extends CommonConverter
		implements BaseConverter<FlightCategoryEntity, FlightCategoryModel> {

	private static final long serialVersionUID = -2997702885471720668L;
	private static final Logger logger = LogManager.getLogger(FlightCategoryConverter.class);

	@Override
	public FlightCategoryEntity modelToEntity(FlightCategoryModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlightCategoryModel entityToModel(FlightCategoryEntity e) {
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		FlightCategoryModel flightCategoryModel = new FlightCategoryModel();
		flightCategoryModel = (FlightCategoryModel) Util.transform(modelMapper, e, flightCategoryModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return flightCategoryModel;
	}

	@Override
	public List<FlightCategoryModel> entityListToModelList(List<FlightCategoryEntity> es) {
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<FlightCategoryModel> flightCategoryModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			flightCategoryModels = new ArrayList<>();
			for (FlightCategoryEntity flightCategoryEntity : es) {
				flightCategoryModels.add(entityToModel(flightCategoryEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return flightCategoryModels;
	}

}
