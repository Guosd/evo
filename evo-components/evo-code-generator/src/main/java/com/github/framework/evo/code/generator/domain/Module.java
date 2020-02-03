package com.github.framework.evo.code.generator.domain;


public class Module {
    private String name;
    private String[] tables;

    public Module() {
    }

    public String getName() {
        return this.name;
    }

    public String[] getTables() {
        return this.tables;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setTables(final String[] tables) {
        this.tables = tables;
    }

}
