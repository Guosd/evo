package com.github.framework.evo.base.util;

import com.github.framework.evo.base.Page;
import com.github.framework.evo.base.PageParam;
import com.github.framework.evo.base.PageResult;
import com.github.framework.evo.base.dto.PageBaseDto;
import com.github.framework.evo.common.uitl.BeanCopyUtils;

/**
 *
 * @author sino
 * @date 2019/9/19
 */
public class PageHelper<V> {

    public static PageResult convert(Page page,Class<?> clazz) {

        return new PageResult((long)page.getPageNo(), (long)page.getPageSize(), (long)page.getTotalCount(),  BeanCopyUtils.cloneList(page,clazz));
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
