package com.ritoinfo.framework.evo.sp.base.starter.rest;

import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseBizz;
import com.ritoinfo.framework.evo.sp.base.starter.dto.PageDto;
import com.ritoinfo.framework.evo.sp.base.starter.validate.group.CreateGroup;
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

/**
 * User: Kyll
 * Date: 2018-02-09 15:58
 */
public abstract class BaseRest<B extends BaseBizz, PK extends Serializable, D, PD extends PageDto> {
	@Autowired
	protected B bizz;

	@GetMapping("/id/{id}")
	@SuppressWarnings("unchecked")
	public ServiceResponse<D> get(@PathVariable PK id) {
		return ServiceResponse.ok((D) bizz.get(id));
	}

	@PostMapping("/page")
	@SuppressWarnings("unchecked")
	public ServiceResponse<PageList<D>> findPage(@Validated(PageGroup.class) @RequestBody PD pageDto) {
		return ServiceResponse.ok(bizz.findPage(pageDto));
	}

	@PostMapping
	@SuppressWarnings("unchecked")
	public ServiceResponse<PK> create(@Validated(CreateGroup.class) @RequestBody D dto) {
		return ServiceResponse.ok((PK) bizz.create(dto));
	}

	@PutMapping
	@SuppressWarnings("unchecked")
	public ServiceResponse update(@Validated(UpdateGroup.class) @RequestBody D dto) {
		bizz.update(dto);
		return ServiceResponse.ok();
	}

	@DeleteMapping("/{id}")
	@SuppressWarnings("unchecked")
	public ServiceResponse delete(@PathVariable PK id) {
		bizz.delete(id);
		return ServiceResponse.ok();
	}
}
