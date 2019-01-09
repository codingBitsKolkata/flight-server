package com.orastays.flight.flightserver.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.orastays.flight.flightserver.entity.SearchParameterEntity;

@Repository
public class SearchParameterDAO extends GenericDAO<SearchParameterEntity, Long> {

	private static final long serialVersionUID = 6924832187551965231L;
	private static final Logger logger = LogManager.getLogger(SearchParameterDAO.class);

	public SearchParameterDAO() {
		super(SearchParameterEntity.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<SearchParameterEntity> fetchSearchDetails(String searchField) {
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchSearchDetails -- START");
		}
		
		String hql="FROM SearchParameterEntity as se WHERE CONCAT(se.airportCode, se.airportName, se.cityCode, se.cityName) LIKE ? ORDER BY se.searchParamId ASC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, "%"+searchField+"%");
		List<SearchParameterEntity> results = query.list();
		
		if (logger.isInfoEnabled()) {
			logger.info("fetchSearchDetails -- START");
		}
		return results;
	}

}
