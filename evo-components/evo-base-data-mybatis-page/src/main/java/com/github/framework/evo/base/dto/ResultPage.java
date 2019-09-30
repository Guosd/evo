package com.github.framework.evo.base.dto;


import java.io.Serializable;
import java.util.List;

public class ResultPage<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private long pageNo;
    private long perPage;
    private long totalCount;
    private List<T> data;

    public long getPageNo() {
        return this.pageNo;
    }

    public long getPerPage() {
        return this.perPage;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setPageNo(final long pageNo) {
        this.pageNo = pageNo;
    }

    public void setPerPage(final long perPage) {
        this.perPage = perPage;
    }

    public void setTotalCount(final long totalCount) {
        this.totalCount = totalCount;
    }

    public void setData(final List<T> data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResultPage)) {
            return false;
        } else {
            ResultPage<?> other = (ResultPage)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getPageNo() != other.getPageNo()) {
                return false;
            } else if (this.getPerPage() != other.getPerPage()) {
                return false;
            } else if (this.getTotalCount() != other.getTotalCount()) {
                return false;
            } else {
                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ResultPage;
    }

    public int hashCode() {
        int result = 1;
        long $pageNo = this.getPageNo();
        result = result * 59 + (int)($pageNo >>> 32 ^ $pageNo);
        long $perPage = this.getPerPage();
        result = result * 59 + (int)($perPage >>> 32 ^ $perPage);
        long $totalCount = this.getTotalCount();
        result = result * 59 + (int)($totalCount >>> 32 ^ $totalCount);
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "ResultPage(pageNo=" + this.getPageNo() + ", perPage=" + this.getPerPage() + ", totalCount=" + this.getTotalCount() + ", data=" + this.getData() + ")";
    }

    public ResultPage(final long pageNo, final long perPage, final long totalCount, final List<T> data) {
        this.pageNo = pageNo;
        this.perPage = perPage;
        this.totalCount = totalCount;
        this.data = data;
    }

    public ResultPage() {
    }
}
