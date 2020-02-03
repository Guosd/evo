package com.github.framework.evo.code.generator.schema;


public class Column {
    private String name;
    private String type;
    private int size;
    private String defaultValue;
    private String comment;
    private boolean nullable;
    private boolean autoIncrement;

    public Column() {
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getSize() {
        return this.size;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public String getComment() {
        return this.comment;
    }

    public boolean isNullable() {
        return this.nullable;
    }

    public boolean isAutoIncrement() {
        return this.autoIncrement;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public void setNullable(final boolean nullable) {
        this.nullable = nullable;
    }

    public void setAutoIncrement(final boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    @Override
    public String toString() {
        return "Column(name=" + this.getName() + ", type=" + this.getType() + ", size=" + this.getSize() + ", defaultValue=" + this.getDefaultValue() + ", comment=" + this.getComment() + ", nullable=" + this.isNullable() + ", autoIncrement=" + this.isAutoIncrement() + ")";
    }
}
