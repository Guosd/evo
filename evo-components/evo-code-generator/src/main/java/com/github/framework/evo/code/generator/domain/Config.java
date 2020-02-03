package com.github.framework.evo.code.generator.domain;


import com.github.framework.evo.code.generator.GenType;

import java.util.Arrays;

public class Config {
    private String basePackage;
    private boolean keepPoPrefix;
    private String currentFunc;
    private boolean defaultCache;
    private String saveDir;
    private String[] ignoreTablePrefixs;
    private GenType[] genTypes;

    public Config() {
    }

    public String getBasePackage() {
        return this.basePackage;
    }

    public boolean isKeepPoPrefix() {
        return this.keepPoPrefix;
    }

    public String getCurrentFunc() {
        return this.currentFunc;
    }

    public boolean isDefaultCache() {
        return this.defaultCache;
    }

    public String getSaveDir() {
        return this.saveDir;
    }

    public String[] getIgnoreTablePrefixs() {
        return this.ignoreTablePrefixs;
    }

    public GenType[] getGenTypes() {
        return this.genTypes;
    }

    public void setBasePackage(final String basePackage) {
        this.basePackage = basePackage;
    }

    public void setKeepPoPrefix(final boolean keepPoPrefix) {
        this.keepPoPrefix = keepPoPrefix;
    }

    public void setCurrentFunc(final String currentFunc) {
        this.currentFunc = currentFunc;
    }

    public void setDefaultCache(final boolean defaultCache) {
        this.defaultCache = defaultCache;
    }

    public void setSaveDir(final String saveDir) {
        this.saveDir = saveDir;
    }

    public void setIgnoreTablePrefixs(final String[] ignoreTablePrefixs) {
        this.ignoreTablePrefixs = ignoreTablePrefixs;
    }

    public void setGenTypes(final GenType[] genTypes) {
        this.genTypes = genTypes;
    }


    @Override
    public String toString() {
        return "Config(basePackage=" + this.getBasePackage() + ", keepPoPrefix=" + this.isKeepPoPrefix() + ", currentFunc=" + this.getCurrentFunc() + ", defaultCache=" + this.isDefaultCache() + ", saveDir=" + this.getSaveDir() + ", ignoreTablePrefixs=" + Arrays.deepToString(this.getIgnoreTablePrefixs()) + ", genTypes=" + Arrays.deepToString(this.getGenTypes()) + ")";
    }
}
