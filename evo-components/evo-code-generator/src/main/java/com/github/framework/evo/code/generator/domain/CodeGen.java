package com.github.framework.evo.code.generator.domain;


import java.util.Arrays;

public class CodeGen {
    private Config config;
    private Db db;
    private Module[] modules;

    public CodeGen() {
    }

    public Config getConfig() {
        return this.config;
    }

    public Db getDb() {
        return this.db;
    }

    public Module[] getModules() {
        return this.modules;
    }

    public void setConfig(final Config config) {
        this.config = config;
    }

    public void setDb(final Db db) {
        this.db = db;
    }

    public void setModules(final Module[] modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "CodeGen(config=" + this.getConfig() + ", db=" + this.getDb() + ", modules=" + Arrays.deepToString(this.getModules()) + ")";
    }
}
