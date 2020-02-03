package com.github.framework.evo.code.generator.util;

import java.util.Locale;

class Word {
    private String key = "";
    private String name = "";
    private String desc = "";

    public Word(String key, String lineContext) {
        this.name = key;
        if (lineContext != null && "".equals(lineContext)) {
            this.desc = lineContext;
        }

        this.key = key.toLowerCase(Locale.getDefault());
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "key=" + this.key + ",name=" + this.name + ",desc=" + this.desc;
    }
}
