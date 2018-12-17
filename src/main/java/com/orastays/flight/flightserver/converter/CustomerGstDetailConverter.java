package com.orastays.flight.flightserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.orastays.flight.flightserver.entity.CustomerGstDetailEntity;
import com.orastays.flight.flightserver.helper.Util;
import com.orastays.flight.flightserver.model.CustomerGstDetailModel;

@Component
public class CustomerGstDetailConverter extends CommonConverter
		implements BaseConverter<CustomerGstDetailEntity, CustomerGstDetailModel> {

	private static final long serialVersionUID = 314934082856401759L;
	private static final Logger logger = LogManager.getLogger(CustomerGstDetailConverter.class);

	@Override
	public CustomerGstDetailEntity modelToEntity(CustomerGstDetailModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerGstDetailModel entityToModel(CustomerGstDetailEntity e) {
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		CustomerGstDetailModel customerGstDetailModel = new CustomerGstDetailModel();
		customerGstDetailModel = (CustomerGstDetailModel) Util.transform(modelMapper, e, customerGstDetailModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return customerGstDetailModel;
	}

	@Override
	public List<CustomerGstDetailModel> entityListToModelList(List<CustomerGstDetailEntity> es) {
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<CustomerGstDetailModel> customerGstDetailModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			customerGstDetailModels = new ArrayList<>();
			for (CustomerGstDetailEntity customerGstDetailEntity : es) {
				customerGstDetailModels.add(entityToModel(customerGstDetailEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return customerGstDetailModels;
	}

}
