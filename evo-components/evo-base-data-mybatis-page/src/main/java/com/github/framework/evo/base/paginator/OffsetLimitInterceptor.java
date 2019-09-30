package com.github.framework.evo.base.paginator;

import com.github.framework.evo.base.Page;
import com.github.framework.evo.base.PageParam;
import com.github.framework.evo.base.paginator.dialect.Dialect;
import com.github.framework.evo.base.paginator.domain.Paginator;
import com.github.framework.evo.base.util.SqlHelper;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)})
public class OffsetLimitInterceptor implements Interceptor {
    private static final Logger log = LoggerFactory.getLogger(OffsetLimitInterceptor.class);
    private static final int MAPPED_STATEMENT_INDEX = 0;
    private static final int PARAMETER_INDEX = 1;
    private static final int ROWBOUNDS_INDEX = 2;
    private String dialectClass;
    @Value("${saa.power:false}")
    private boolean power;
    public static final ThreadLocal<String> powerSql = new ThreadLocal();

    public OffsetLimitInterceptor() {
    }

    public Object intercept(final Invocation invocation) throws Throwable {
        Object[] queryArgs = invocation.getArgs();
        RowBounds rowBounds = (RowBounds)queryArgs[2];
        PageParam pageParam = new PageParam(rowBounds);
        if (pageParam.getOffset() == 0 && pageParam.getLimit() == 2147483647 && pageParam.getOrders().isEmpty()) {
            return invocation.proceed();
        } else {
            MappedStatement ms = (MappedStatement)queryArgs[0];
            Object parameter = queryArgs[1];
            BoundSql boundSql = ms.getBoundSql(parameter);
            /*
            boolean skipDataPower = (Boolean)PowerIndicator.get("skipDataPower", false);
            if (this.power && !skipDataPower) {
                Object obj = Springs.getBean("saaDataPowerService");
                String sql = ((DataPowerService)obj).processSQLWithPower(boundSql.getSql());
                powerSql.set(sql);
                ReflectHelper.setFieldValue(boundSql, "sql", sql);
                ms = copyFromMappedStatement(ms, new OffsetLimitInterceptor.BoundSqlSqlSource(boundSql));
            }
            */

            Dialect dialect;
            try {
                Class clazz = Class.forName(this.dialectClass);
                Constructor constructor = clazz.getConstructor(MappedStatement.class, Object.class, PageParam.class);
                dialect = (Dialect)constructor.newInstance(ms, parameter, pageParam);
            } catch (Exception var15) {
                throw new ClassNotFoundException("Cannot create dialect instance: " + this.dialectClass, var15);
            }

            queryArgs[0] = this.copyFromNewSql(dialect);
            queryArgs[2] = new RowBounds(0, 2147483647);
            List result = (List)invocation.proceed();
            Cache cache = ms.getCache();
            Executor executor = (Executor)invocation.getTarget();
            Integer count;
            if (cache != null && ms.isUseCache() && ms.getConfiguration().isCacheEnabled()) {
                CacheKey cacheKey = executor.createCacheKey(ms, parameter, new PageParam(), copyCountFromBoundSql(dialect));
                count = (Integer)cache.getObject(cacheKey);
                if (count == null) {
                    count = SqlHelper.getCount(ms, executor.getTransaction(), parameter, boundSql, dialect);
                    cache.putObject(cacheKey, count);
                }
            } else {
                count = SqlHelper.getCount(ms, executor.getTransaction(), parameter, boundSql, dialect);
            }

            Paginator paginator = new Paginator(pageParam.getPage(), pageParam.getLimit(), count);
            return new Page(result, paginator);
        }
    }

    private MappedStatement copyFromNewSql(Dialect dialect) {
        BoundSql newBoundSql = new BoundSql(dialect.getMappedStatement().getConfiguration(), dialect.getPageSQL(), dialect.getParameterMappings(), dialect.getParameterObject());
        Iterator var3 = dialect.getParameterMappings().iterator();

        while(var3.hasNext()) {
            ParameterMapping mapping = (ParameterMapping)var3.next();
            String prop = mapping.getProperty();
            newBoundSql.setAdditionalParameter(prop, dialect.getAdditionalParameters().get(prop));
            log.debug("In copyFromNewSql,parameter [{}]=[{}]", prop, dialect.getAdditionalParameters().get(prop));
        }

        return copyFromMappedStatement(dialect.getMappedStatement(), new OffsetLimitInterceptor.BoundSqlSqlSource(newBoundSql));
    }

    private static BoundSql copyCountFromBoundSql(Dialect dialect) {
        BoundSql newBoundSql = new BoundSql(dialect.getMappedStatement().getConfiguration(), dialect.getCountSQL(), dialect.getParameterMappings(), dialect.getParameterObject());
        Iterator var2 = dialect.getParameterMappings().iterator();

        while(var2.hasNext()) {
            ParameterMapping mapping = (ParameterMapping)var2.next();
            String prop = mapping.getProperty();
            newBoundSql.setAdditionalParameter(prop, dialect.getAdditionalParameters().get(prop));
            log.debug("In copyCountFromBoundSql,parameter [{}]=[{}]", prop, dialect.getAdditionalParameters().get(prop));
        }

        return newBoundSql;
    }

    private static MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            String[] var4 = ms.getKeyProperties();
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String keyProperty = var4[var6];
                keyProperties.append(keyProperty).append(",");
            }

            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }

        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        String dialectClassName = properties.getProperty("dialectClass");
        this.setDialectClass(dialectClassName);
    }

    public void setDialectClass(String dialectClass) {
        log.debug("dialectClass: {} ", dialectClass);
        this.dialectClass = dialectClass;
    }

    public static class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return this.boundSql;
        }
    }
}
