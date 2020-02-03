package com.github.framework.evo.code.generator.freemarker;

import java.util.Map;

public final class FreeMarkerUtils {
    private FreeMarkerUtils() {
    }

    public static void registMethod(Map<Object, Object> root) {
        root.put("toJavaType", new ToJavaTypeMethod());
        root.put("toTypeString", new ToTypeStringMethod());
        root.put("toVariableString", new ToVariableStringMethod());
    }
}
