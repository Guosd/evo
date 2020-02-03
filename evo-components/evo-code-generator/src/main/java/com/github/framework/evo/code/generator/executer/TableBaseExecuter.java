package com.github.framework.evo.code.generator.executer;


import com.github.framework.evo.code.generator.GenConfig;
import com.github.framework.evo.code.generator.schema.Table;
import java.io.IOException;

public abstract class TableBaseExecuter {
    protected static final String JAVA_SUFFIX = ".java";
    protected static final String XML_SUFFIX = ".xml";
    protected GenConfig genConfig;

    public TableBaseExecuter(GenConfig genConfig) {
        this.genConfig = genConfig;
    }

    public abstract void build(Table table) throws IOException;
}
