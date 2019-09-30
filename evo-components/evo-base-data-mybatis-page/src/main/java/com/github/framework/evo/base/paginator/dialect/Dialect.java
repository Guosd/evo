package com.github.framework.evo.base.paginator.dialect;

import com.github.framework.evo.base.Order;
import com.github.framework.evo.base.PageParam;
import com.github.framework.evo.base.util.SqlHelper;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.mapping.ParameterMapping.Builder;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.type.SimpleTypeRegistry;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.util.*;

public class Dialect {
    protected TypeHandlerRegistry typeHandlerRegistry;
    protected MappedStatement mappedStatement;
    protected PageParam pageParam;
    protected Object parameterObject;
    protected List<ParameterMapping> parameterMappings;
    protected Map<String, Object> additionalParameters = new HashMap();
    private String pageSQL;
    private String countSQL;

    public Dialect(MappedStatement mappedStatement, Object parameterObject, PageParam pageParam) {
        this.mappedStatement = mappedStatement;
        this.parameterObject = parameterObject;
        this.pageParam = pageParam;
        this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
        this.init();
    }

    protected void init() {
        BoundSql boundSql = this.mappedStatement.getBoundSql(this.parameterObject);
        this.parameterMappings = new ArrayList(boundSql.getParameterMappings());
        if (this.parameterObject instanceof Map) {
            this.additionalParameters.putAll((Map)this.parameterObject);
        } else if (this.parameterObject != null) {
            Class<?> cls = this.parameterObject.getClass();
            if (!cls.isPrimitive() && !cls.isArray() && !SimpleTypeRegistry.isSimpleType(cls) && !Enum.class.isAssignableFrom(cls) && !Collection.class.isAssignableFrom(cls)) {
                MetaObject metaObject = this.mappedStatement.getConfiguration().newMetaObject(this.parameterObject);
                ObjectWrapper wrapper = metaObject.getObjectWrapper();
                Iterator var5 = this.parameterMappings.iterator();

                while(var5.hasNext()) {
                    ParameterMapping parameterMapping = (ParameterMapping)var5.next();
                    PropertyTokenizer prop = new PropertyTokenizer(parameterMapping.getProperty());
                    String name = parameterMapping.getProperty();
                    if (name.indexOf(46) != -1) {
                        name = name.substring(0, name.indexOf(46));
                        this.additionalParameters.put(name, wrapper.findProperty(name, true));
                    } else {
                        this.additionalParameters.put(parameterMapping.getProperty(), wrapper.get(prop));
                    }
                }
            } else {
                Iterator var3 = this.parameterMappings.iterator();

                while(var3.hasNext()) {
                    ParameterMapping parameterMapping = (ParameterMapping)var3.next();
                    this.additionalParameters.put(parameterMapping.getProperty(), this.parameterObject);
                }
            }
        }

        this.initSQL(boundSql.getSql().trim());
    }

    private void initSQL(String inSQL) {
        String sql = inSQL;
        if (inSQL.endsWith(";")) {
            sql = inSQL.substring(0, inSQL.length() - 1);
        }

        this.pageSQL = sql;
        if (this.pageParam.getOrders() != null && !this.pageParam.getOrders().isEmpty()) {
            this.pageSQL = this.getSortString(sql, this.pageParam.getOrders());
        }

        if (this.pageParam.getOffset() != 0 || this.pageParam.getLimit() != 2147483647) {
            this.pageSQL = this.getLimitString(this.pageSQL, "__offset", this.pageParam.getOffset(), "__limit", this.pageParam.getLimit());
        }

        this.countSQL = this.getCountString(sql);
    }

    public MappedStatement getMappedStatement() {
        return this.mappedStatement;
    }

    public List<ParameterMapping> getParameterMappings() {
        return this.parameterMappings;
    }

    public Object getParameterObject() {
        return this.parameterObject;
    }

    public String getPageSQL() {
        return this.pageSQL;
    }

    protected void setPageParameter(String name, Object value, Class<?> type) {
        ParameterMapping parameterMapping = (new Builder(this.mappedStatement.getConfiguration(), name, type)).build();
        this.parameterMappings.add(parameterMapping);
        this.additionalParameters.put(name, value);
    }

    protected void setPageParameter(String name, Object value, Class<?> type, int index) {
        ParameterMapping parameterMapping = (new Builder(this.mappedStatement.getConfiguration(), name, type)).build();
        this.parameterMappings.add(index, parameterMapping);
        this.additionalParameters.put(name, value);
    }

    public String getCountSQL() {
        return this.countSQL;
    }

    public Map<String, Object> getAdditionalParameters() {
        return this.additionalParameters;
    }

    protected String getLimitString(String sql, String offsetName, int offset, String limitName, int limit) {
        throw new UnsupportedOperationException("paged queries not supported");
    }

    protected String getCountString(String sql) {
        StringBuilder buf = new StringBuilder();
        buf.append("select count(1) from (");
        buf.append(SqlHelper.removeOrders(sql));
        buf.append(") tmp_count");
        return buf.toString();
    }

    protected String getSortString(String sql, List<Order> orders) {
        if (orders != null && !orders.isEmpty()) {
            List<ResultMap> resultMaps = this.mappedStatement.getResultMaps();
            Map<String, String> propertyColumn = null;
            Iterator var6;
            if (resultMaps != null && resultMaps.get(0) != null) {
                propertyColumn = new HashMap();
                List<ResultMapping> resultMappings = ((ResultMap)resultMaps.get(0)).getResultMappings();
                var6 = resultMappings.iterator();

                while(var6.hasNext()) {
                    ResultMapping resultMap = (ResultMapping)var6.next();
                    propertyColumn.put(resultMap.getProperty(), resultMap.getColumn());
                }
            }

            StringBuilder buffer = (new StringBuilder("select * from (")).append(sql).append(") temp_order order by ");
            var6 = orders.iterator();

            while(var6.hasNext()) {
                Order order = (Order)var6.next();
                if (order != null) {
                    if (propertyColumn != null && propertyColumn.containsKey(order.getProperty())) {
                        order.setProperty((String)propertyColumn.get(order.getProperty()));
                    }

                    buffer.append(order.toString()).append(", ");
                }
            }

            buffer.delete(buffer.length() - 2, buffer.length());
            return buffer.toString();
        } else {
            return sql;
        }
    }
}

