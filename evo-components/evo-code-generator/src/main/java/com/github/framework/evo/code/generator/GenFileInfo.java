package com.github.framework.evo.code.generator;


public class GenFileInfo {
    private String name;
    private String packageName;
    private String path;
    private String keyName;

    public String getName() {
        return this.name;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getPath() {
        return this.path;
    }

    public String getKeyName() {
        return this.keyName;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public void setKeyName(final String keyName) {
        this.keyName = keyName;
    }


    protected boolean canEqual(final Object other) {
        return other instanceof GenFileInfo;
    }

    public GenFileInfo(final String name, final String packageName, final String path, final String keyName) {
        this.name = name;
        this.packageName = packageName;
        this.path = path;
        this.keyName = keyName;
    }
}
