package com.github.framework.evo.code.generator;


import java.util.Arrays;

public class GenParam {
    private String module;
    private String[] tables;

    public GenParam(String module, String[] tables) {
        this.tables = tables;
        this.module = module;
    }

    public String getModule() {
        return this.module;
    }

    public String[] getTables() {
        return this.tables;
    }

    public void setModule(final String module) {
        this.module = module;
    }

    public void setTables(final String[] tables) {
        this.tables = tables;
    }


    @Override
    public String toString() {
        return "GenParam(module=" + this.getModule() + ", tables=" + Arrays.deepToString(this.getTables()) + ")";
    }
}
