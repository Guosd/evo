package com.github.framework.evo.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private long pageNum;
    private long pageSize;
    private long totalCount;
    private List<T> data;

    public PageResult(final long pageNum, final long pageSize, final long totalCount, final List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.data = data;
    }

    public PageResult() {
    }

}
