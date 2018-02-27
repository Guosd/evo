package com.ritoinfo.framework.evo.sp.base.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * User: Kyll
 * Date: 2018-02-13 13:36
 */
public class ReadSqlProvider {
	public String readById(MappedStatement mappedStatement) {
		return new SQL() {{
			SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME");
		}}.toString();
	}

	public String readAll() {
		return null;
	}

	public String countAll() {
		return null;
	}
}
