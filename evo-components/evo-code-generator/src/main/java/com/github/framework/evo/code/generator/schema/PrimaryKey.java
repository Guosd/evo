package com.github.framework.evo.code.generator.schema;


public class PrimaryKey {
    private String pkName;
    private int keySeq;
    private String columnName;

    public PrimaryKey() {
    }

    public String getPkName() {
        return this.pkName;
    }

    public int getKeySeq() {
        return this.keySeq;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setPkName(final String pkName) {
        this.pkName = pkName;
    }

    public void setKeySeq(final int keySeq) {
        this.keySeq = keySeq;
    }

    public void setColumnName(final String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String toString() {
        return "PrimaryKey(pkName=" + this.getPkName() + ", keySeq=" + this.getKeySeq() + ", columnName=" + this.getColumnName() + ")";
    }
}
