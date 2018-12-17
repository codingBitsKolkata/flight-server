package com.orastays.flight.flightserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.flight.flightserver.entity.CityEntity;
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.CityModel;

@Component
public class CityConverter extends CommonConverter implements BaseConverter<CityEntity, CityModel> {

	private static final long serialVersionUID = 1768276365836052827L;
	private static final Logger logger = LogManager.getLogger(CityConverter.class);

	@Override
	public CityEntity modelToEntity(CityModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityModel entityToModel(CityEntity e) {
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		CityModel cityModel = new CityModel();
		cityModel = (CityModel) Util.transform(modelMapper, e, cityModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return cityModel;
	}

	@Override
	public List<CityModel> entityListToModelList(List<CityEntity> es) {
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<CityModel> cityModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			cityModels = new ArrayList<>();
			for (CityEntity cityEntity : es) {
				cityModels.add(entityToModel(cityEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return cityModels;
	}

}
