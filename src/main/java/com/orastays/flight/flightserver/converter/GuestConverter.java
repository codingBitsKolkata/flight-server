package com.orastays.flight.flightserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.flight.flightserver.entity.GuestEntity;
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.GuestModel;

@Component
public class GuestConverter extends CommonConverter implements BaseConverter<GuestEntity, GuestModel> {

	private static final long serialVersionUID = -5183523007861035991L;
	private static final Logger logger = LogManager.getLogger(GuestConverter.class);

	@Override
	public GuestEntity modelToEntity(GuestModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuestModel entityToModel(GuestEntity e) {
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		GuestModel guestModel = new GuestModel();
		guestModel = (GuestModel) Util.transform(modelMapper, e, guestModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return guestModel;
	}

	@Override
	public List<GuestModel> entityListToModelList(List<GuestEntity> es) {
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<GuestModel> guestModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			guestModels = new ArrayList<>();
			for (GuestEntity guestEntity : es) {
				guestModels.add(entityToModel(guestEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return guestModels;
	}

}
