package com.ritoinfo.framework.evo.sp.base.starter.bizz;

import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.starter.assist.BaseHelper;
import com.ritoinfo.framework.evo.sp.base.starter.dao.BaseXmlDao;
import com.ritoinfo.framework.evo.sp.base.starter.dto.PageDto;
import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseXmlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-09 16:52
 */
public abstract class BaseXmlBizz<Dao extends BaseXmlDao, E extends BaseXmlEntity, PK extends Serializable, Dto> extends BaseBizz<Dao, E, PK, Dto> {
	@Autowired
	protected Dao dao;

	@SuppressWarnings("unchecked")
	public Dto get(PK id) {
		E e = (E) dao.get(id);
		return e == null ? null : toDto(e);
	}

	@SuppressWarnings("unchecked")
	public Dto getOne(Object condition) {
		E e = (E) dao.getOne(condition);
		return e == null ? null : toDto(e);
	}

	@SuppressWarnings("unchecked")
	public List<Dto> find() {
		return toDto(dao.find(null));
	}

	@SuppressWarnings("unchecked")
	public List<Dto> find(Object condition) {
		return toDto(dao.find(condition));
	}

	@SuppressWarnings("unchecked")
	public PageList<Dto> findPage(PageDto condition) {
		PageList<Dto> pageList = new PageList<>();

		int count = dao.countLike(condition.count());
		BaseHelper.copyPage(pageList, count, condition);

		if (count > 0) {
			pageList.setDataList(toDto(dao.findLike(condition.page())));
		}

		return pageList;
	}

	public int count() {
		return dao.count(null);
	}

	public int count(Object condition) {
		return dao.count(condition);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public PK create(Dto dto) {
		E entity = toEntity(dto);

		entity.setCreateBy(getUserContextId());
		entity.setUpdateBy(entity.getCreateBy());
		entity.setCreateTime(DateUtil.now());
		entity.setUpdateTime(entity.getCreateTime());

		dao.insert(entity);

		return (PK) entity.getId();
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public void update(Dto dto) {
		E entity = toEntity(dto);

		entity.setUpdateBy(getUserContextId());
		entity.setUpdateTime(DateUtil.now());

		dao.update(entity);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public void delete(PK id) {
		dao.delete(id);
	}
}
