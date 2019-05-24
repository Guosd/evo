package com.github.framework.evo.controller.dao;

import com.github.framework.evo.controller.entity.ConfigProperty;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Kyll
 * Date: 2019-05-24 13:53
 */
public interface ConfigPropertyDao extends JpaRepository<ConfigProperty, Long> {
}
