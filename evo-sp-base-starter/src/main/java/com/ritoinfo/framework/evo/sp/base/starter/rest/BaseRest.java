package com.ritoinfo.framework.evo.sp.base.starter.rest;

import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseBizz;
import com.ritoinfo.framework.evo.sp.base.starter.condition.BaseCondition;
import com.ritoinfo.framework.evo.sp.base.starter.dao.BaseDao;
import com.ritoinfo.framework.evo.sp.base.starter.dto.BaseDto;
import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseEntity;
import com.ritoinfo.framework.evo.sp.base.starter.validate.group.CreateGroup;
import com.ritoinfo.framework.evo.sp.base.starter.validate.group.ListGroup;
import com.ritoinfo.framework.evo.sp.base.starter.validate.group.PageGroup;
import com.ritoinfo.framework.evo.sp.base.starter.validate.group.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-09 15:58
 */
public abstract class BaseRest<B extends BaseBizz<D, E, PK, C, T>, D extends BaseDao<E, PK, C>, E extends BaseEntity<PK>, PK extends Serializable, C extends BaseCondition<PK>, T extends BaseDto<PK>> {
	@Autowired
	protected B bizz;

	@GetMapping("/{id}")
	public ServiceResponse<T> get(@PathVariable PK id) {
		return ServiceResponse.ok(bizz.get(id));
	}

	@GetMapping("/all")
	public ServiceResponse<List<T>> find() {
		return ServiceResponse.ok(bizz.find());
	}

	@GetMapping
	public ServiceResponse<List<T>> find(@Validated(ListGroup.class) @RequestBody C condition) {
		return ServiceResponse.ok(bizz.find(condition));
	}

	@GetMapping("/page")
	public ServiceResponse<PageList<T>> findPage(@Validated(PageGroup.class) @RequestBody C condition) {
		return ServiceResponse.ok(bizz.findPage(condition));
	}

	@PostMapping
	public ServiceResponse create(@Validated(CreateGroup.class) @RequestBody T dto) {
		bizz.create(dto);
		return ServiceResponse.ok();
	}

	@PutMapping
	public ServiceResponse update(@Validated(UpdateGroup.class) @RequestBody T dto) {
		bizz.update(dto);
		return ServiceResponse.ok();
	}

	@DeleteMapping("/{id}")
	public ServiceResponse delete(@PathVariable PK id) {
		bizz.delete(id);
		return ServiceResponse.ok();
	}
}
