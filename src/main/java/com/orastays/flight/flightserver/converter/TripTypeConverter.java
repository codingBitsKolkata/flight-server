package com.orastays.flight.flightserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.flight.flightserver.entity.TripTypeEntity;
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.TripTypeModel;

@Component
public class TripTypeConverter extends CommonConverter implements BaseConverter<TripTypeEntity, TripTypeModel> {

	private static final long serialVersionUID = 3578176011701797485L;
	private static final Logger logger = LogManager.getLogger(TripTypeConverter.class);

	@Override
	public TripTypeEntity modelToEntity(TripTypeModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripTypeModel entityToModel(TripTypeEntity e) {
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		TripTypeModel tripTypeModel = new TripTypeModel();
		tripTypeModel = (TripTypeModel) Util.transform(modelMapper, e, tripTypeModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return tripTypeModel;
	}

	@Override
	public List<TripTypeModel> entityListToModelList(List<TripTypeEntity> es) {
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<TripTypeModel> tripTypeModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			tripTypeModels = new ArrayList<>();
			for (TripTypeEntity tripTypeEntity : es) {
				tripTypeModels.add(entityToModel(tripTypeEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return tripTypeModels;
	}

}
