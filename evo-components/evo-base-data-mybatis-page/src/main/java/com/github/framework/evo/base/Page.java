package com.github.framework.evo.base;


import com.github.framework.evo.base.paginator.domain.Paginator;

import java.util.ArrayList;
import java.util.Collection;

public class Page<E> extends ArrayList<E> {
    private static final long serialVersionUID = 1L;
    private Paginator paginator;

    public Page() {
    }

    public Page(Collection<? extends E> c) {
        super(c);
    }

    public Page(Collection<? extends E> c, Paginator p) {
        super(c);
        this.paginator = p;
    }

    public Page(Paginator p) {
        this.paginator = p;
    }

    public Paginator getPaginator() {
        return this.paginator;
    }

    public int getPageSize() {
        return this.paginator != null ? this.paginator.getLimit() : 0;
    }

    public int getPageNo() {
        return this.paginator != null ? this.paginator.getPage() : 0;
    }

    public int getTotalCount() {
        return this.paginator != null ? this.paginator.getTotalCount() : 0;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        } else if (obj.getClass() != Page.class) {
            return false;
        } else {
            Page<E> fobj = (Page)obj;
            return this.paginator != null ? this.paginator.equals(fobj.getPaginator()) : false;
        }
    }

    public int hashCode() {
        return this.paginator != null ? this.getPaginator().hashCode() : super.hashCode();
    }
}

