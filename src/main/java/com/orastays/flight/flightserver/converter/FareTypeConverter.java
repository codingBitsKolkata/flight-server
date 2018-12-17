package com.orastays.flight.flightserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.flight.flightserver.entity.FareTypeEntity;
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.FareTypeModel;

@Component
public class FareTypeConverter extends CommonConverter implements BaseConverter<FareTypeEntity, FareTypeModel> {

	private static final long serialVersionUID = -4076035895580199665L;
	private static final Logger logger = LogManager.getLogger(FareTypeConverter.class);

	@Override
	public FareTypeEntity modelToEntity(FareTypeModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FareTypeModel entityToModel(FareTypeEntity e) {
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		FareTypeModel fareTypeModel = new FareTypeModel();
		fareTypeModel = (FareTypeModel) Util.transform(modelMapper, e, fareTypeModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return fareTypeModel;
	}

	@Override
	public List<FareTypeModel> entityListToModelList(List<FareTypeEntity> es) {
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<FareTypeModel> fareTypeModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			fareTypeModels = new ArrayList<>();
			for (FareTypeEntity fareTypeEntity : es) {
				fareTypeModels.add(entityToModel(fareTypeEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return fareTypeModels;
	}

}
