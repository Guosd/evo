package com.ritoinfo.framework.evo.sample.dao;

import com.ritoinfo.framework.evo.sample.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-07-12 13:12
 */
public interface CountryDao extends JpaRepository<Country, Long> {
	List<Country> findByCode(String code);
	Country getByCode(String code);
}
