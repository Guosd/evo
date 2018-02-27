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
	private static final String SQL_ID_READ = "select";
	private static final String SQL_ID_UPDATE = "update";
	private static final String SQL_ID_DELETE = "delete";
	private static final String SQL_ID_COUNT = "count";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public PK create(E entity) {
		sqlSession.insert(getStatement(SQL_ID_CREATE), entity);
		return entity.getId();
	}

	@Override
	public E readById(PK id) {
		return sqlSession.selectOne(getStatement(SQL_ID_READ), id);
	}

	@Override
	public List<E> readAll() {
		return sqlSession.selectList(getStatement(SQL_ID_READ));
	}

	@Override
	public List<E> readByCondition(C condition) {
		return sqlSession.selectList(getStatement(SQL_ID_READ), condition);
	}

	@Override
	public PageList<E> readPage(C condition) {
		PageList<E> pageList = new PageList<>();
		pageList.setPageNo(condition.getPageNo());
		pageList.setPageSize(condition.getPageSize());

		int totalRecord = countByCondition(condition);
		pageList.setTotalRecord(totalRecord);

		if (totalRecord > 0) {
			pageList.setDataList(readByCondition(condition));
		}

		return pageList;
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
		Map<String, Object> map = sqlSession.selectMap(getStatement(SQL_ID_COUNT), "count");
		return ((BigDecimal) map.get("count")).intValue();
	}

	@Override
	public int countByCondition(C condition) {
		Map<String, Object> map = sqlSession.selectMap(getStatement(SQL_ID_COUNT), condition, "count");
		return ((BigDecimal) map.get("count")).intValue();
	}

	private String getStatement(String sqlId) {
		return getClass().getName() + "." + sqlId;
	}
}
