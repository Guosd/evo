//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.github.framework.evo.base.paginator.dialect;

import com.github.framework.evo.base.PageParam;
import org.apache.ibatis.mapping.MappedStatement;

public class MySQLDialect extends Dialect {
    public MySQLDialect(MappedStatement mappedStatement, Object parameterObject, PageParam pageParam) {
        super(mappedStatement, parameterObject, pageParam);
    }

    @Override
    protected String getLimitString(String sql, String offsetName, int offset, String limitName, int limit) {
        StringBuilder buffer = (new StringBuilder(sql.length() + 20)).append(sql);
        if (offset > 0) {
            buffer.append(" limit ?, ?");
            this.setPageParameter(offsetName, offset, Integer.class);
            this.setPageParameter(limitName, limit, Integer.class);
        } else {
            buffer.append(" limit ?");
            this.setPageParameter(limitName, limit, Integer.class);
        }

        return buffer.toString();
    }
}
