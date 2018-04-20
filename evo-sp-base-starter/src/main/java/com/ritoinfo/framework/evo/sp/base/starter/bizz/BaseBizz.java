package com.ritoinfo.framework.evo.sp.base.starter.bizz;

import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.starter.assist.BaseHelper;
import com.ritoinfo.framework.evo.sp.base.starter.condition.BaseCondition;
import com.ritoinfo.framework.evo.sp.base.starter.dao.BaseDao;
import com.ritoinfo.framework.evo.sp.base.starter.dto.BaseDto;
import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseEntity;
import com.ritoinfo.framework.evo.sp.base.starter.session.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-09 16:52
 */
public abstract class BaseBizz<D extends BaseDao<E, PK, C>, E extends BaseEntity<PK>, PK extends Serializable, C extends BaseCondition<PK>, T extends BaseDto<PK>> {
	@Autowired
	protected D dao;

	public T get(PK id) {
		return BaseHelper.toDto(dao.get(id));
	}

	public List<T> find() {
		return BaseHelper.toDto(dao.find(null));
	}

	public List<T> find(C condition) {
		return BaseHelper.toDto(dao.find(condition));
	}

	public PageList<T> findPage(C condition) {
		PageList<T> pageList = new PageList<>();

		int count = dao.count(condition.count());
		BaseHelper.copyPage(pageList, count, condition);

		if (count > 0) {
			pageList.setDataList(BaseHelper.toDto(dao.find(condition.page())));
		}

		return pageList;
	}

	public int count() {
		return dao.count(null);
	}

	public int count(C condition) {
		return dao.count(condition);
	}

	@Transactional
	public PK create(T dto) {
		E entity = BaseHelper.toEntity(dto);

		entity.setCreateBy(SessionHolder.getUserContext().getId(BeanUtil.getGenericClass(entity)));
		entity.setUpdateBy(entity.getCreateBy());
		entity.setCreateTime(DateUtil.now());
		entity.setUpdateTime(entity.getCreateTime());

		dao.insert(entity);

		return entity.getId();
	}

	@Transactional
	public void update(T dto) {
		E entity = BaseHelper.toEntity(dto);

		entity.setUpdateBy(SessionHolder.getUserContext().getId(BeanUtil.getGenericClass(entity)));
		entity.setUpdateTime(DateUtil.now());

		dao.update(entity);
	}

	@Transactional
	public void delete(PK id) {
		dao.delete(id);
	}
}
