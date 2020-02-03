package com.github.framework.evo.code.generator.freemarker;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import com.github.framework.evo.code.generator.util.WordFileUtils;
import java.util.List;

public class ToTypeStringMethod implements TemplateMethodModelEx {
    public ToTypeStringMethod() {
    }

    public Object exec(List args) throws TemplateModelException {
        if (args.size() != 1) {
            throw new TemplateModelException("Wrong arguments");
        } else {
            return WordFileUtils.getBeautyObjectName(args.get(0).toString());
        }
    }
}
