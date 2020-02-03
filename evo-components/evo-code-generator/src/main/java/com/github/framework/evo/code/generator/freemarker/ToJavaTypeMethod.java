package com.github.framework.evo.code.generator.freemarker;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import com.github.framework.evo.code.generator.util.TypeUtils;
import java.util.List;

public class ToJavaTypeMethod implements TemplateMethodModelEx {
    public ToJavaTypeMethod() {
    }

    public Object exec(List args) throws TemplateModelException {
        if (args.size() != 1) {
            throw new TemplateModelException("Wrong arguments");
        } else {
            return TypeUtils.processType(args.get(0).toString());
        }
    }
}
