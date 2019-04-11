package com.orastays.flightserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.flightserver.entity.TravellerVsBaggageEntity;
import com.orastays.flightserver.helper.Util;
import com.orastays.flightserver.model.booking.TravellerVsBaggageModel;

@Component
public class TravellerVsBaggageConverter extends CommonConverter
		implements BaseConverter<TravellerVsBaggageEntity, TravellerVsBaggageModel> {

	private static final long serialVersionUID = 6654889744051718454L;
	
	private static final Logger logger = LogManager.getLogger(TravellerVsBaggageConverter.class);

	@Override
	public TravellerVsBaggageEntity modelToEntity(TravellerVsBaggageModel m) {

		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}

		TravellerVsBaggageEntity travellerVsBaggageEntity = new TravellerVsBaggageEntity();

		travellerVsBaggageEntity = (TravellerVsBaggageEntity) Util.transform(modelMapper, m, travellerVsBaggageEntity);
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		return travellerVsBaggageEntity;
	}

	@Override
	public TravellerVsBaggageModel entityToModel(TravellerVsBaggageEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		TravellerVsBaggageModel travellerVsBaggageModel = new TravellerVsBaggageModel();
		travellerVsBaggageModel = (TravellerVsBaggageModel) Util.transform(modelMapper, e, travellerVsBaggageModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return travellerVsBaggageModel;
	}

	@Override
	public List<TravellerVsBaggageModel> entityListToModelList(List<TravellerVsBaggageEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<TravellerVsBaggageModel> travellerVsBaggageModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			travellerVsBaggageModels = new ArrayList<>();
			for (TravellerVsBaggageEntity travellerVsBaggageEntity : es) {
				travellerVsBaggageModels.add(entityToModel(travellerVsBaggageEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return travellerVsBaggageModels;
	}

}
