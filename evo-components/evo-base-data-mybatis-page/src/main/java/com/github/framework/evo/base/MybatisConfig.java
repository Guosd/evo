package com.github.framework.evo.base;

import com.github.framework.evo.base.paginator.OffsetLimitInterceptor;
import com.github.framework.evo.base.paginator.dialect.MySQLDialect;
import com.github.framework.evo.base.paginator.dialect.OracleDialect;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {
    private static final Logger log = LoggerFactory.getLogger(MybatisConfig.class);
    @Autowired
    private DataSource dataSource;

    public MybatisConfig() {
    }

    @Bean
    public OffsetLimitInterceptor offsetLimitInterceptor() throws SQLException {
        OffsetLimitInterceptor offsetLimitInterceptor = new OffsetLimitInterceptor();
        Connection connection = this.dataSource.getConnection();
        Throwable var3 = null;

        try {
            DatabaseMetaData metaData = connection.getMetaData();
            String databaseProductName = metaData.getDatabaseProductName();
            String dbName = databaseProductName.toLowerCase(Locale.US);
            if (dbName.contains("mysql")) {
                offsetLimitInterceptor.setDialectClass(MySQLDialect.class.getName());
            } else if (dbName.contains("oracle")) {
                offsetLimitInterceptor.setDialectClass(OracleDialect.class.getName());
            } /*else if (dbName.contains("db2")) {
                offsetLimitInterceptor.setDialectClass(DB2Dialect.class.getName());
            } else if (dbName.contains("postgresql")) {
                offsetLimitInterceptor.setDialectClass(PostgreSQLDialect.class.getName());
            } else if (dbName.contains("sql server")) {
                offsetLimitInterceptor.setDialectClass(SQLServerDialect.class.getName());
            } else if (dbName.contains("h2")) {
                offsetLimitInterceptor.setDialectClass(H2Dialect.class.getName());
            } else if (dbName.contains("hsql")) {
                offsetLimitInterceptor.setDialectClass(HSQLDialect.class.getName());
            } else if (dbName.contains("sybase")) {
                offsetLimitInterceptor.setDialectClass(SybaseDialect.class.getName());
            } else if (dbName.indexOf("sinoregal") != -1) {
                offsetLimitInterceptor.setDialectClass(InformixDialect.class.getName());
            } else if (dbName.indexOf("argon") != -1) {
                offsetLimitInterceptor.setDialectClass(InformixDialect.class.getName());
            } else if (dbName.indexOf("ibm informix") != -1) {
                offsetLimitInterceptor.setDialectClass(InformixDialect.class.getName());
            } */else {
                /*if (dbName.indexOf("informix") == -1) {*/
                    throw new IllegalArgumentException("Unsupport Database [" + databaseProductName + "]");
               /* }

                offsetLimitInterceptor.setDialectClass(InformixDialect.class.getName());*/
            }

            log.info("Current databaseProductName is [" + databaseProductName + "]");
        } catch (Throwable var14) {
            var3 = var14;
            throw var14;
        } finally {
            if (connection != null) {
                if (var3 != null) {
                    try {
                        connection.close();
                    } catch (Throwable var13) {
                        var3.addSuppressed(var13);
                    }
                } else {
                    connection.close();
                }
            }

        }

        return offsetLimitInterceptor;
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(this.dataSource);
    }

    @Bean
    public DatabaseIdProvider getDatabaseIdProvider() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider() {
        };
        Properties p = new Properties();
        p.setProperty("Oracle", "oracle");
        p.setProperty("SQL Server", "sqlserver");
        p.setProperty("DB2", "db2");
        p.setProperty("MySQL", "mysql");
        p.setProperty("PostgreSQL", "postgresql");
        p.setProperty("Informix Dynamic Server", "informix");
        databaseIdProvider.setProperties(p);
        return databaseIdProvider;
    }
}
