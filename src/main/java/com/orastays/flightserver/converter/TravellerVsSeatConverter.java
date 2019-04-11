package com.orastays.flightserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.flightserver.entity.TravellerVsSeatEntity;
import com.orastays.flightserver.helper.Util;
import com.orastays.flightserver.model.booking.TravellerVsSeatModel;

@Component
public class TravellerVsSeatConverter extends CommonConverter
		implements BaseConverter<TravellerVsSeatEntity, TravellerVsSeatModel> {
	
	private static final long serialVersionUID = 4111806755985102604L;
	
	private static final Logger logger = LogManager.getLogger(TravellerVsSeatConverter.class);

	@Override
	public TravellerVsSeatEntity modelToEntity(TravellerVsSeatModel m) {

		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}

		TravellerVsSeatEntity travellerVsSeatEntity = new TravellerVsSeatEntity();

		travellerVsSeatEntity = (TravellerVsSeatEntity) Util.transform(modelMapper, m, travellerVsSeatEntity);
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		return travellerVsSeatEntity;
	}

	@Override
	public TravellerVsSeatModel entityToModel(TravellerVsSeatEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		TravellerVsSeatModel travellerVsSeatModel = new TravellerVsSeatModel();
		travellerVsSeatModel = (TravellerVsSeatModel) Util.transform(modelMapper, e, travellerVsSeatModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return travellerVsSeatModel;
	}

	@Override
	public List<TravellerVsSeatModel> entityListToModelList(List<TravellerVsSeatEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<TravellerVsSeatModel> travellerVsSeatModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			travellerVsSeatModels = new ArrayList<>();
			for (TravellerVsSeatEntity travellerVsSeatEntity : es) {
				travellerVsSeatModels.add(entityToModel(travellerVsSeatEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return travellerVsSeatModels;
	}

}
