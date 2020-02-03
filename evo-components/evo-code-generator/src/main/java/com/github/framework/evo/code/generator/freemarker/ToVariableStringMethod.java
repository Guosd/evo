package com.github.framework.evo.code.generator.freemarker;

import java.util.List;

import com.github.framework.evo.code.generator.util.WordFileUtils;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class ToVariableStringMethod implements TemplateMethodModelEx {
    public ToVariableStringMethod() {
    }

    @Override
    public Object exec(List args) throws TemplateModelException {
        if (args.size() != 1) {
            throw new TemplateModelException("Wrong arguments");
        } else {
            return WordFileUtils.getBeautyInstanceName(args.get(0).toString());
        }
    }
}