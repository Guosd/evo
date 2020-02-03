package com.github.framework.evo.code.generator.schema;


import com.github.framework.evo.code.generator.util.GeneratorUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Table {
    private String name;
    private String comment;
    private List<Column> columns = new ArrayList();
    private List<PrimaryKey> primaryKeys = new ArrayList();
    private List<Column> keyColumns = new ArrayList();
    private List<Column> nonKeyColumns = new ArrayList();

    public void setColumns(List<Column> columns) {
        this.columns = columns;
        this.keyColumns = GeneratorUtils.getTablePrimaryKeysColumns(this);
        this.nonKeyColumns.clear();
        Iterator var2 = columns.iterator();

        while(var2.hasNext()) {
            Column column = (Column)var2.next();
            if (!this.keyColumns.contains(column)) {
                this.nonKeyColumns.add(column);
            }
        }

    }

    public void setPrimaryKeys(List<PrimaryKey> primaryKeys) {
        this.primaryKeys = primaryKeys;
        this.keyColumns = GeneratorUtils.getTablePrimaryKeysColumns(this);
        this.nonKeyColumns.clear();
        Iterator var2 = this.columns.iterator();

        while(var2.hasNext()) {
            Column column = (Column)var2.next();
            if (!this.keyColumns.contains(column)) {
                this.nonKeyColumns.add(column);
            }
        }

    }

    public Table() {
    }

    public String getName() {
        return this.name;
    }

    public String getComment() {
        return this.comment;
    }

    public List<Column> getColumns() {
        return this.columns;
    }

    public List<PrimaryKey> getPrimaryKeys() {
        return this.primaryKeys;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public void setKeyColumns(final List<Column> keyColumns) {
        this.keyColumns = keyColumns;
    }

    public void setNonKeyColumns(final List<Column> nonKeyColumns) {
        this.nonKeyColumns = nonKeyColumns;
    }


    @Override
    public String toString() {
        return "Table(name=" + this.getName() + ", comment=" + this.getComment() + ", columns=" + this.getColumns() + ", primaryKeys=" + this.getPrimaryKeys() + ", keyColumns=" + this.getKeyColumns() + ", nonKeyColumns=" + this.getNonKeyColumns() + ")";
    }

    public List<Column> getKeyColumns() {
        return this.keyColumns;
    }

    public List<Column> getNonKeyColumns() {
        return this.nonKeyColumns;
    }
}
