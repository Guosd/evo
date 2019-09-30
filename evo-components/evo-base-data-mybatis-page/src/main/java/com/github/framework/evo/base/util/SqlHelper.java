package com.github.framework.evo.base.util;


import com.github.framework.evo.base.paginator.dialect.Dialect;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlHelper {
    private static final Logger log = LoggerFactory.getLogger(SqlHelper.class);

    private SqlHelper() {
    }

    public static int getCount(final MappedStatement mappedStatement, final Transaction transaction, final Object parameterObject, final BoundSql boundSql, Dialect dialect) throws SQLException {
        String countSql = dialect.getCountSQL();
        log.debug("Total count SQL [{}] ", countSql);
        log.debug("Total count Parameters: {} ", parameterObject);
        Connection connection = transaction.getConnection();
        int count = 0;
        PreparedStatement countStmt = connection.prepareStatement(countSql);
        Throwable var9 = null;

        try {
            DefaultParameterHandler handler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
            handler.setParameters(countStmt);
            ResultSet rs = countStmt.executeQuery();
            Throwable var12 = null;

            try {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            } catch (Throwable var35) {
                var12 = var35;
                throw var35;
            } finally {
                if (rs != null) {
                    if (var12 != null) {
                        try {
                            rs.close();
                        } catch (Throwable var34) {
                            var12.addSuppressed(var34);
                        }
                    } else {
                        rs.close();
                    }
                }

            }
        } catch (Throwable var37) {
            var9 = var37;
            throw var37;
        } finally {
            if (countStmt != null) {
                if (var9 != null) {
                    try {
                        countStmt.close();
                    } catch (Throwable var33) {
                        var9.addSuppressed(var33);
                    }
                } else {
                    countStmt.close();
                }
            }

        }

        log.debug("Total count: {}", count);
        return count;
    }

    public static String removeOrders(String sql) {
        Assert.hasText(sql, "sql must have value");
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", 2);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();

        while(m.find()) {
            m.appendReplacement(sb, "");
        }

        m.appendTail(sb);
        return sb.toString();
    }

    public static boolean isIncludeDistinct(String sql) {
        String hqlLowerCase = sql.toLowerCase(Locale.US);
        hqlLowerCase = hqlLowerCase.replace(" ", "");
        return hqlLowerCase.startsWith("selectdistinct");
    }
}