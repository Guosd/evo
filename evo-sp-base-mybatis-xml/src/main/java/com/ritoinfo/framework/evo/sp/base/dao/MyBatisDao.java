package com.ritoinfo.framework.evo.sp.base.dao;

import com.ritoinfo.framework.evo.sp.base.condition.BaseCondition;
import com.ritoinfo.framework.evo.sp.base.entity.BaseEntity;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-02-09 16:54
 */
public abstract class MyBatisDao<E extends BaseEntity<PK>, PK extends Serializable, C extends BaseCondition> implements BaseDao<E, PK, C> {
	private static final String SQL_ID_CREATE = "insert";
	private static final String SQL_ID_READ_BY_PK = "select";
	private static final String SQL_ID_READ_ALL = "select";
	private static final String SQL_ID_READ_BY_CONDITION = "select";
	private static final String SQL_ID_READ_PAGE = "select";
	private static final String SQL_ID_UPDATE = "update";
	private static final String SQL_ID_DELETE = "delete";
	private static final String SQL_ID_COUNT_ALL = "count";
	private static final String SQL_ID_COUNT_BY_CONDITION = "count";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public PK create(E entity) {
		sqlSession.insert(getStatement(SQL_ID_CREATE), entity);
		return entity.getId();
	}

	@Override
	public E readByPK(PK id) {
		return sqlSession.selectOne(getStatement(SQL_ID_READ_BY_PK), id);
	}

	@Override
	public List<E> readAll() {
		return sqlSession.selectList(getStatement(SQL_ID_READ_ALL));
	}

	@Override
	public List<E> readByCondition(C condition) {
		return sqlSession.selectList(getStatement(SQL_ID_READ_BY_CONDITION), condition);
	}

	@Override
	public PageList<E> readPage(C condition, PageList<E> pageList) {
		return null;
	}

	@Override
	public int update(E entity) {
		return sqlSession.update(getStatement(SQL_ID_UPDATE), entity);
	}

	@Override
	public int delete(PK id) {
		return sqlSession.delete(getStatement(SQL_ID_DELETE), id);
	}

	@Override
	public int countAll() {
		Map<String, Object> map = sqlSession.selectMap(getStatement(SQL_ID_COUNT_ALL), "count");
		return ((BigDecimal) map.get("count")).intValue();
	}

	@Override
	public int countByCondition(C condition) {
		Map<String, Object> map = sqlSession.selectMap(getStatement(SQL_ID_COUNT_BY_CONDITION), "count");
		return ((BigDecimal) map.get("count")).intValue();
	}

	private String getStatement(String sqlId) {
		return getClass().getName() + "." + sqlId;
	}
}
