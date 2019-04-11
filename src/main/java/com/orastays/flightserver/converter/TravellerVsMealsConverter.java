package com.orastays.flightserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.flightserver.entity.TravellerVsMealsEntity;
import com.orastays.flightserver.helper.Util;
import com.orastays.flightserver.model.booking.TravellerVsMealsModel;

@Component
public class TravellerVsMealsConverter extends CommonConverter
		implements BaseConverter<TravellerVsMealsEntity, TravellerVsMealsModel> {
	
	private static final long serialVersionUID = 4625668709291180989L;
	
	private static final Logger logger = LogManager.getLogger(TravellerVsMealsConverter.class);

	@Override
	public TravellerVsMealsEntity modelToEntity(TravellerVsMealsModel m) {

		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}

		TravellerVsMealsEntity travellerVsMealsEntity = new TravellerVsMealsEntity();

		travellerVsMealsEntity = (TravellerVsMealsEntity) Util.transform(modelMapper, m, travellerVsMealsEntity);
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		return travellerVsMealsEntity;
	}

	@Override
	public TravellerVsMealsModel entityToModel(TravellerVsMealsEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		TravellerVsMealsModel travellerVsMealsModel = new TravellerVsMealsModel();
		travellerVsMealsModel = (TravellerVsMealsModel) Util.transform(modelMapper, e, travellerVsMealsModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return travellerVsMealsModel;
	}

	@Override
	public List<TravellerVsMealsModel> entityListToModelList(List<TravellerVsMealsEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<TravellerVsMealsModel> travellerVsMealsModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			travellerVsMealsModels = new ArrayList<>();
			for (TravellerVsMealsEntity travellerVsMealsEntity : es) {
				travellerVsMealsModels.add(entityToModel(travellerVsMealsEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return travellerVsMealsModels;
	}

}
