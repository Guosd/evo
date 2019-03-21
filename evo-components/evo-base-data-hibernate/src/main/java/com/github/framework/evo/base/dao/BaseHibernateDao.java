package com.github.framework.evo.base.dao;

import com.github.framework.evo.base.entity.BaseHibernateEntity;
import com.github.framework.evo.common.uitl.BeanUtil;
import com.github.framework.evo.common.uitl.CollectionUtil;
import lombok.Getter;
import org.hibernate.LockMode;
import org.hibernate.ReplicationMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-11-16 18:03
 */
public abstract class BaseHibernateDao<E extends BaseHibernateEntity, PK extends Serializable> implements BaseDao<E, PK> {
	@Autowired
	@Getter private HibernateTemplate hibernateTemplate;

	private Class entityClass;

	@Override
	public E get(PK id) {
		return hibernateTemplate.get(getEntityClass(), id);
	}

	public E get(PK id, LockMode lockMode) {
		return hibernateTemplate.get(getEntityClass(), id, lockMode);
	}

	public E load(PK id) {
		return hibernateTemplate.load(getEntityClass(), id);
	}

	public E load(PK id, LockMode lockMode) {
		return hibernateTemplate.load(getEntityClass(), id, lockMode);
	}

	public List<E> loadAll() {
		return hibernateTemplate.loadAll(getEntityClass());
	}

	@Override
	public E getOne(Object condition) {
		List<E> list = find(condition);
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> find(Object condition) {
		return (List<E>) hibernateTemplate.findByCriteria(toDetachedCriteria(condition));
	}

	@SuppressWarnings("unchecked")
	public List<E> find(Object condition, int firstResult, int maxResults) {
		return (List<E>) hibernateTemplate.findByCriteria(toDetachedCriteria(condition), firstResult, maxResults);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findLike(Object condition) {
		return (List<E>) hibernateTemplate.findByCriteria(toLikeDetachedCriteria(condition));
	}

	@SuppressWarnings("unchecked")
	public List<E> findLike(Object condition, int firstResult, int maxResults) {
		return (List<E>) hibernateTemplate.findByCriteria(toDetachedCriteria(condition), firstResult, maxResults);
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		return (List<E>) hibernateTemplate.findByCriteria(toDetachedCriteria());
	}

	@SuppressWarnings("unchecked")
	public List<E> findByHql(String hql, Object... values) {
		return hibernateTemplate.execute((HibernateCallback<List<E>>) session -> {
			Query query = session.createQuery(hql, getEntityClass());

			if (values != null) {
				for(int i = 0; i < values.length; ++i) {
					query.setParameter(i, values[i]);
				}
			}

			return query.list();
		});
	}

	public List<E> findBySql(String sql) {
		return hibernateTemplate.execute(session -> session.createNativeQuery(sql, getEntityClass()).list());
	}

	public List<Map<String, Object>> findBySqlForMap(String sql) {
		List<Map> list = hibernateTemplate.execute(session -> session.createNativeQuery(sql, Map.class).list());

		List<Map<String, Object>> resultList = new ArrayList<>();

		if (list != null) {
			for (Map map : list) {
				Map<String, Object> resultMap = new HashMap<>();
				for (Object key : map.keySet()) {
					resultMap.put((String) key, map.get(key));
				}
				resultList.add(resultMap);
			}
		}

		return resultList;
	}

	@SuppressWarnings("unchecked")
	public int count() {
		return ((List<Long>) hibernateTemplate.findByCriteria(toDetachedCriteria().setProjection(Projections.rowCount()))).get(0).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int count(Object condition) {
		return ((List<Long>) hibernateTemplate.findByCriteria(toDetachedCriteria(condition).setProjection(Projections.rowCount()))).get(0).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int countLike(Object condition) {
		return ((List<Long>) hibernateTemplate.findByCriteria(toLikeDetachedCriteria(condition).setProjection(Projections.rowCount()))).get(0).intValue();
	}

	@Override
	public int insert(E entity) {
		hibernateTemplate.save(entity);
		return 0;
	}

	public Serializable save(E entity) {
		return hibernateTemplate.save(entity);
	}

	public void saveOrUpdate(E entity) {
		hibernateTemplate.saveOrUpdate(entity);
	}

	@Override
	public int update(E entity) {
		hibernateTemplate.update(entity);
		return 0;
	}

	public void update(E entity, LockMode lockMode) {
		hibernateTemplate.update(entity, lockMode);
	}

	@Override
	public int delete(PK id) {
		hibernateTemplate.delete(get(id));
		return 0;
	}

	public int delete(PK id, LockMode lockMode) {
		hibernateTemplate.delete(get(id), lockMode);
		return 0;
	}

	public int delete(E entity) {
		hibernateTemplate.delete(entity);
		return 0;
	}

	public int delete(E entity, LockMode lockMode) {
		hibernateTemplate.delete(entity, lockMode);
		return 0;
	}

	public int deleteAll(Collection<E> entities) {
		hibernateTemplate.deleteAll(entities);
		return 0;
	}

	public void clear() {
		hibernateTemplate.clear();
	}

	public void evict(E entity) {
		hibernateTemplate.evict(entity);
	}

	public List<E> findByExample(E exampleEntity) {
		return hibernateTemplate.findByExample(exampleEntity);
	}

	public List<E> findByExample(E exampleEntity, int firstResult, int maxResults) {
		return hibernateTemplate.findByExample(exampleEntity, firstResult, maxResults);
	}

	public void flush() {
		hibernateTemplate.flush();
	}

	public int getFetchSize() {
		return hibernateTemplate.getFetchSize();
	}

	public String[] getFilterNames() {
		return hibernateTemplate.getFilterNames();
	}

	public int getMaxResults() {
		return hibernateTemplate.getMaxResults();
	}

	public String getQueryCacheRegion() {
		return hibernateTemplate.getQueryCacheRegion();
	}

	public SessionFactory getSessionFactory() {
		return hibernateTemplate.getSessionFactory();
	}

	public void initialize(Object proxy) {
		hibernateTemplate.initialize(proxy);
	}

	public boolean isCacheQueries() {
		return hibernateTemplate.isCacheQueries();
	}

	public boolean isCheckWriteOperations() {
		return hibernateTemplate.isCheckWriteOperations();
	}

	public boolean isExposeNativeSession() {
		return hibernateTemplate.isExposeNativeSession();
	}

	public void lock(Object entity, LockMode lockMode) {
		hibernateTemplate.lock(entity, lockMode);
	}

	public void lock(String entityName, Object entity, LockMode lockMode) {
		hibernateTemplate.lock(entityName, entity, lockMode);
	}

	public E merge(E entity) {
		return hibernateTemplate.merge(entity);
	}

	public void persist(E entity) {
		hibernateTemplate.persist(entity);
	}

	public void refresh(E entity) {
		hibernateTemplate.refresh(entity);
	}

	public void refresh(E entity, LockMode lockMode) {
		hibernateTemplate.refresh(entity, lockMode);
	}

	public void replicate(E entity, ReplicationMode replicationMode) {
		hibernateTemplate.replicate(entity, replicationMode);
	}

	public void setCacheQueries(boolean cacheQueries) {
		hibernateTemplate.setCacheQueries(cacheQueries);
	}

	public void setCheckWriteOperations(boolean checkWriteOperations) {
		hibernateTemplate.setCheckWriteOperations(checkWriteOperations);
	}

	public void setExposeNativeSession(boolean exposeNativeSession) {
		hibernateTemplate.setExposeNativeSession(exposeNativeSession);
	}

	public void setFetchSize(int fetchSize) {
		hibernateTemplate.setFetchSize(fetchSize);
	}

	public void setFilterNames(String... filterNames) {
		hibernateTemplate.setFilterNames(filterNames);
	}

	public void setMaxResults(int maxResults) {
		hibernateTemplate.setMaxResults(maxResults);
	}

	public void setQueryCacheRegion(String queryCacheRegion) {
		hibernateTemplate.setQueryCacheRegion(queryCacheRegion);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate.setSessionFactory(sessionFactory);
	}

	protected Class<E> getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class) BeanUtil.getGenericTypes(this)[0];
		}
		return entityClass;
	}

	protected DetachedCriteria toDetachedCriteria() {
		return DetachedCriteria.forClass(getEntityClass());
	}

	protected DetachedCriteria toDetachedCriteria(Object condition) {
		Map<String, Object> map = BeanUtil.beanToMap(condition);
		CollectionUtil.trim(map);
		return toDetachedCriteria().add(Restrictions.allEq(map));
	}

	protected DetachedCriteria toLikeDetachedCriteria(Object condition) {
		Map<String, Object> map = BeanUtil.beanToMap(condition);

		DetachedCriteria detachedCriteria = toDetachedCriteria();
		for (String key : map.keySet()) {
			detachedCriteria.add(Restrictions.ilike(key, map.get(key)));
		}

		return detachedCriteria;
	}
}
