package com.ritoinfo.framework.evo.sp.demo.dao;

import com.ritoinfo.framework.evo.sp.demo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-07-12 13:12
 */
public interface CountryDao extends JpaRepository<Country, Long> {
	List<Country> findByCountryCode(String countryCode);
	Country getByCountryCode(String countryCode);
}
