package com.github.framework.evo.base.util;

import com.github.framework.evo.base.Page;
import com.github.framework.evo.base.PageParam;
import com.github.framework.evo.base.PageResult;
import com.github.framework.evo.base.dto.PageBaseDto;

/**
 * Created by sino on 2019/9/19.
 */
public class PageHelper {

    public static <V> PageResult<V> convert(Page page) {
        return new PageResult((long)page.getPageNo(), (long)page.getPageSize(), (long)page.getTotalCount(), page);
    }

    public static PageParam getPageParam(PageBaseDto pageBase) {
        int pageNum = pageBase.getPageNum();
        if(pageNum < 1) {
            pageNum = 1;
        }

        int pageSize = pageBase.getPageSize();
        if(pageSize < 0) {
            pageSize = 10;
        }

        if(pageSize > 3000) {
            throw new IllegalArgumentException("pageSize exceeded maxPageSize[3000]");
        }
        return new PageParam(pageNum, pageSize);
    }

}
