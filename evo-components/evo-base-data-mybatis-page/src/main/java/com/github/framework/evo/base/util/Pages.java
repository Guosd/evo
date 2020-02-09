package com.github.framework.evo.base.util;


import com.github.framework.evo.base.Page;
import com.github.framework.evo.base.PageResult;
import com.github.framework.evo.common.uitl.BeanCopyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author programmer
 */
public class Pages {
    private static final String WHERE = " where ";
    private static final String WHERE_ONE_EQ_ONE = " where 1=1 ";

    private Pages() {
    }


    public static String genQueryString(String queryStringIn) {
        String queryString = queryStringIn.replaceAll("\\s+", " ");
        String powerHql = " and Valid_Status='0' ";
        if ("".equals(powerHql)) {
            return queryString;
        } else {
            queryString = queryString.replaceAll("\\s+", " ");
            int limitIndex;
            if (queryString.toLowerCase(Locale.US).indexOf(" group by ") != -1) {
                limitIndex = queryString.toLowerCase(Locale.US).indexOf("group by");
                if (queryString.toLowerCase(Locale.US).indexOf(" where ") != -1) {
                    queryString = queryString.substring(0, limitIndex) + powerHql + queryString.substring(limitIndex);
                } else {
                    queryString = queryString.substring(0, limitIndex) + " where 1=1 " + powerHql + queryString.substring(limitIndex);
                }

                return queryString;
            } else if (queryString.toLowerCase(Locale.US).indexOf(" order by ") != -1) {
                limitIndex = queryString.toLowerCase(Locale.US).indexOf("order by");
                if (queryString.toLowerCase(Locale.US).indexOf(" where ") != -1) {
                    queryString = queryString.substring(0, limitIndex) + powerHql + queryString.substring(limitIndex);
                } else {
                    queryString = queryString.substring(0, limitIndex) + " where 1=1 " + powerHql + queryString.substring(limitIndex);
                }

                return queryString;
            } else if (queryString.toLowerCase(Locale.US).indexOf(" limit ") != -1) {
                limitIndex = queryString.toLowerCase(Locale.US).indexOf("limit");
                if (queryString.toLowerCase(Locale.US).indexOf(" where ") != -1) {
                    queryString = queryString.substring(0, limitIndex) + powerHql + queryString.substring(limitIndex);
                } else {
                    queryString = queryString.substring(0, limitIndex) + " where 1=1 " + powerHql + queryString.substring(limitIndex);
                }

                return queryString;
            } else {
                return queryString.toLowerCase(Locale.US).indexOf(" where ") != -1 ? queryString + powerHql : queryString + " where 1=1 " + powerHql;
            }
        }
    }
}
