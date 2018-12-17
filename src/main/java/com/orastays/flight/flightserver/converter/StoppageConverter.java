package com.orastays.flight.flightserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.flight.flightserver.entity.StoppageEntity;
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.StoppageModel;

@Component
public class StoppageConverter extends CommonConverter implements BaseConverter<StoppageEntity, StoppageModel> {

	private static final long serialVersionUID = -5582127918616996075L;
	private static final Logger logger = LogManager.getLogger(StoppageConverter.class);

	@Override
	public StoppageEntity modelToEntity(StoppageModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoppageModel entityToModel(StoppageEntity e) {
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		StoppageModel stoppageModel = new StoppageModel();
		stoppageModel = (StoppageModel) Util.transform(modelMapper, e, stoppageModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return stoppageModel;
	}

	@Override
	public List<StoppageModel> entityListToModelList(List<StoppageEntity> es) {
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<StoppageModel> stoppageModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			stoppageModels = new ArrayList<>();
			for (StoppageEntity stoppageEntity : es) {
				stoppageModels.add(entityToModel(stoppageEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return stoppageModels;
	}

}
