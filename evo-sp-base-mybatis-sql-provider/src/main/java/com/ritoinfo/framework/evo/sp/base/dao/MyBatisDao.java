package com.ritoinfo.framework.evo.sp.base.dao;

import com.ritoinfo.framework.evo.sp.base.condition.BaseCondition;
import com.ritoinfo.framework.evo.sp.base.dao.provider.CreateSqlProvider;
import com.ritoinfo.framework.evo.sp.base.dao.provider.DeleteSqlProvider;
import com.ritoinfo.framework.evo.sp.base.dao.provider.ReadSqlProvider;
import com.ritoinfo.framework.evo.sp.base.dao.provider.UpdateSqlProvider;
import com.ritoinfo.framework.evo.sp.base.entity.BaseEntity;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.io.Serializable;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-09 16:54
 */
public interface MyBatisDao<E extends BaseEntity<PK>, PK extends Serializable, C extends BaseCondition> extends BaseDao<E, PK, C> {
	@InsertProvider(type = CreateSqlProvider.class, method = "create")
	PK create(E entity);

	@SelectProvider(type = ReadSqlProvider.class, method = "readByPK")
	E readByPK(PK id);

	@SelectProvider(type = ReadSqlProvider.class, method = "readAll")
	List<E> readAll();

	@SelectProvider(type = ReadSqlProvider.class, method = "readByCondition")
	List<E> readByCondition(C condition);

	@SelectProvider(type = ReadSqlProvider.class, method = "readPage")
	PageList<E> readPage(C condition, PageList<E> pageList);

	@UpdateProvider(type = UpdateSqlProvider.class, method = "update")
	int update(E entity);

	@UpdateProvider(type = DeleteSqlProvider.class, method = "delete")
	int delete(PK id);

	@SelectProvider(type = ReadSqlProvider.class, method = "countAll")
	int countAll();

	@SelectProvider(type = ReadSqlProvider.class, method = "countByCondition")
	int countByCondition(C condition);
}
