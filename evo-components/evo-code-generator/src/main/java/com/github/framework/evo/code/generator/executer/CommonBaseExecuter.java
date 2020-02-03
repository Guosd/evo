package com.github.framework.evo.code.generator.executer;

import com.github.framework.evo.code.generator.GenConfig;

import java.io.IOException;

public abstract class CommonBaseExecuter {
    protected static final String JAVA_SUFFIX = ".java";
    protected static final String XML_SUFFIX = ".xml";
    protected GenConfig genConfig;

    public CommonBaseExecuter(GenConfig genConfig) {
        this.genConfig = genConfig;
    }

    public abstract void build() throws IOException;
}

