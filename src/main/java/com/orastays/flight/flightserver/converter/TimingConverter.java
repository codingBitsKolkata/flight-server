package com.orastays.flight.flightserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.flight.flightserver.entity.TimingEntity;
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.TimingModel;

@Component
public class TimingConverter extends CommonConverter implements BaseConverter<TimingEntity, TimingModel> {

	private static final long serialVersionUID = 2369332296544990400L;
	private static final Logger logger = LogManager.getLogger(TimingConverter.class);

	@Override
	public TimingEntity modelToEntity(TimingModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimingModel entityToModel(TimingEntity e) {
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		TimingModel timingModel = new TimingModel();
		timingModel = (TimingModel) Util.transform(modelMapper, e, timingModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return timingModel;
	}

	@Override
	public List<TimingModel> entityListToModelList(List<TimingEntity> es) {
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<TimingModel> timingModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			timingModels = new ArrayList<>();
			for (TimingEntity timingEntity : es) {
				timingModels.add(entityToModel(timingEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return timingModels;
	}

}
