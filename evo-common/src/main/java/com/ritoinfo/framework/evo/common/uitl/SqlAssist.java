package com.ritoinfo.framework.evo.common.uitl;

/**
 * User: Kyll
 * Date: 2018-02-28 10:08
 */
public class SqlAssist {
	public static String toPageForOracle(String sql, int pageNo, int pageSize) {
		int offset = (pageNo - 1) * pageSize + 1;
		return "select * from (select p.*, rownum rn from (" + sql + ") p where rownum < " + (offset + pageSize) + ") where rn >= " + offset;
	}

	public static String toPageForMySql(String sql, int pageNo, int pageSize) {
		int offset = (pageNo - 1) * pageSize;
		return sql + " limit " + offset + "," + pageSize;
	}
}
