package com.github.framework.evo.sample.dao;

import com.github.framework.evo.base.dao.BaseHibernateDao;
import com.github.framework.evo.sample.entity.Country;
import org.springframework.stereotype.Repository;

/**
 * User: Kyll
 * Date: 2018-07-12 13:12
 */
@Repository
public class CountryDao extends BaseHibernateDao<Country, Long> {
}
