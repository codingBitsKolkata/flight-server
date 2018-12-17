package com.orastays.flight.flightserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.flight.flightserver.entity.AirlinesEntity;
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.AirlinesModel;

@Component
public class AirlinesConverter extends CommonConverter implements BaseConverter<AirlinesEntity, AirlinesModel> {

	private static final long serialVersionUID = -3052775297811840623L;
	private static final Logger logger = LogManager.getLogger(AirlinesConverter.class);

	@Override
	public AirlinesEntity modelToEntity(AirlinesModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AirlinesModel entityToModel(AirlinesEntity e) {
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		AirlinesModel airlinesModel = new AirlinesModel();
		airlinesModel = (AirlinesModel) Util.transform(modelMapper, e, airlinesModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return airlinesModel;
	}

	@Override
	public List<AirlinesModel> entityListToModelList(List<AirlinesEntity> es) {
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<AirlinesModel> airlinesModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			airlinesModels = new ArrayList<>();
			for (AirlinesEntity airlinesEntity : es) {
				airlinesModels.add(entityToModel(airlinesEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return airlinesModels;
	}

}
